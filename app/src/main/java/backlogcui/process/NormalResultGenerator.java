package backlogcui.process;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import backlogcui.command.Command;
import backlogcui.result.Result;

/**
 * API実行結果を生成するクラス
 */
public class NormalResultGenerator implements IGenerator{
    Command command;
    String responseBody;

    public NormalResultGenerator(Command command, String responseBody) {
        this.command      = command;
        this.responseBody = responseBody;
    }

    /**
     * responseBodyの型に応じてResultオブジェクトのリストを返却
     * @return API実行結果オブジェクト
     * @throws ParseException -> JSONパースに失敗
     */
    public List<Result> generate() throws  ParseException {
        JSONParser parser = new JSONParser();

        if(parser.parse(responseBody) instanceof JSONArray) {
            return generateMultiple((JSONArray)parser.parse(responseBody));
        } else {
            return generateSingle((JSONObject)parser.parse(responseBody));
        }
    }

    /**
     * 単一の結果を生成する
     * @return API実行結果オブジェクト
     * @return API実行結果オブジェクトのリスト 処理の共通化の都合で単一でもリストに格納
     */
    private List<Result> generateSingle(JSONObject resultJson) throws ParseException{
        List<Result> resultList = new ArrayList<>();
        resultList.add(command.generateResult(resultJson));

        return resultList;
    }

    /**
     * 複数の結果を生成する
     * @return API実行結果オブジェクト
     * @return API実行結果オブジェクトのリスト
     */
    @SuppressWarnings("unchecked")
    private List<Result> generateMultiple(JSONArray resultJsonArray) throws ParseException {
        List<Result> resultList             = new  ArrayList<>();
        Iterator<JSONObject> resultIterator = resultJsonArray.iterator();

        while(resultIterator.hasNext()) {
            Result result = command.generateResult(resultIterator.next());

            if(result != null) {
                resultList.add(result);
            }
        }
        return resultList;
    }
}
