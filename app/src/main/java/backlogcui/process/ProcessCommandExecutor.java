package backlogcui.process;

import java.io.IOException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import backlogcui.command.Command;
import backlogcui.result.Result;

/**
 * コマンド実行の実装クラス
 */
public class ProcessCommandExecutor implements ProcessExecutor {

    private static final int INDEX_FIRST_JSON = 0;
    private Command command;
    private String typedArguments;
    private APIClient client;

    /**
     * APIを実行して結果を取得する
     * @param command   コマンドオブジェクト
     * @param arguments コマンド実行時引数
     */
    public ProcessCommandExecutor(Command command, String typedArguments) {
        this.command        = command;
        this.typedArguments = typedArguments;
        client              = new APIClient();
    }

    /**
     * コマンドを実行する
     * エラーのJSONを表示するプロセスと、エラーを通知するプロセスは
     * 概念が類似しているが厳密には異なるためそれぞれ個別の処理として実装
     * @throws ParseException
     */
    public void execute() {
        try {
            String uri           = buildURI(typedArguments);
            String responseBody  = sendRequest(uri);
            List<Result> results = generateResults(responseBody);

            executeNextProcess(results);
        } catch (IOException | InterruptedException e) {
            System.err.println(e); // TODO
            System.out.println(e); // TODO
            executeErrorProcess("コマンドの実行に失敗");
        } catch (ParseException e) {
            executeErrorProcess("コマンド実行結果の読み取りに失敗");
        }
    }

    /**
     * レスポンスの結果に応じて結果を生成する
     * @param  responseBody  APIの実行結果
     * @return               結果オブジェクトのリスト
     * @throws ParseException -> JSONパースに失敗
     */
    private List<Result> generateResults(String responseBody) throws ParseException {
        if(checkResponse(responseBody)) {
            return generateNormalResult(responseBody);
        } else {
            return generateErrorResult(extractErrorMessage(responseBody));
        }
    }

    /**
     * リクエストするURIを組み立てる
     * @param  arguments APIの引数
     * @return           組み立てられたURI
     */
    private String buildURI(String typedArguments) {
        return command.buildURI(typedArguments);
    }

    /**
     * APIの実行を移譲する
     * @param uri
     * @return
     * @throws InterruptedException
     * @throws IOException
     */
    private String sendRequest(String uri) throws IOException, InterruptedException {
        return client.sendRequest(uri);
    }

    /**
     * APIリクエストの結果が取得されているかを判定する
     * ステータスコードが200でもクエリパラメーターが不適切等の理由で
     * エラーを示すJsonが返ってくる場合があるため、その判定をしている
     * @param responseBody    リクエストボディ
     * @return                判定結果
     * @throws ParseException JSONのパースに失敗
     */
    private boolean checkResponse(String responseBody) throws ParseException {
            return extractErrorMessage(responseBody) == null;
    }

    /**
     * APIの実行結果からオブジェクトを生成する
     * @param responseBody    実行したAPIのレスポンスボディ
     * @return                実行した機能の結果コレクション
     * @throws ParseException JSONのパースに失敗
     */
    private List<Result> generateNormalResult(String responseBody)throws ParseException {
            IGenerator generator = new NormalResultGenerator(command, responseBody);

            return generator.generate();
    }

    /**
     * エラー結果オブジェクトを生成する
     * @param responseBody    実行したAPIのレスポンスボディ
     * @return                実行した機能の結果コレクション
     * @throws ParseException JSONのパースに失敗
     */
    private List<Result> generateErrorResult(String responseBody)throws ParseException {
            IGenerator generator = new ErrResultGenerator(responseBody);

            return generator.generate();
    }

    /**
     * @param 次の処理を実行する
     */
    private void executeNextProcess(List<Result> results) {
        ProcessExecutor nextProcess = new ProcessShowResult(results);
        nextProcess.execute();
        nextProcess = null;
    }

    /**
     * @param responseBody 実行したAPIのレスポンスボディ
     * @return             エラー事由
     * @throws ParseException -> JSONの読み取りに失敗
     */
    private String extractErrorMessage(String responseBody) throws ParseException {
        JSONParser parser          = new JSONParser();
        if(parser.parse(responseBody) instanceof JSONArray) {
            return null;
        } else {
            JSONObject jsonObject      = (JSONObject)parser.parse(responseBody);
            JSONArray errorJsonArray   = (JSONArray)jsonObject.get("errors");
            JSONObject errorJsonObject = (JSONObject)errorJsonArray.get(INDEX_FIRST_JSON);
            return (String)errorJsonObject.get("message");
        }
    }

    /**
     * エラー表示処理を実行する
     * @param errorMessage
     */
    private void executeErrorProcess(String errorMessage) {
        ProcessExecutor nextProcess = new ProcessShowError(errorMessage);
        nextProcess.execute();
    }
}
