package backlogcui.command;

import org.json.simple.JSONObject;

import backlogcui.result.Result;
/**
 * 使用するコマンドの基底クラス
 * 責務の分離と凝集度を天秤にかけた際に、各コマンドのロジックが集中した方がわかりやすいと考えたためこのような実装になっています。
 */
public abstract class Command implements IURIBuilder, IResultGenerator {

    protected  String commandName      = null;
    protected  String commandArguments = null;
    protected  String description      = null;
    protected  String uri              = null;
    protected  String arguments        = null;

    /* Getter */
    public String getCommandName() {
        return commandName;
    }
    public String getCommandArguments() {
        return commandArguments;
    }

    public String getDescription() {
        return description;
    }
    public String getUri() {
        return uri;
    }

    public String getArguments() {
        return arguments;
    }

    /**
     * API実行のURIを組み立てる
     * @param typedArguments ユーザーが入力したコマンド引数
     * @return               組み立てられたURI
     */
    public abstract String buildURI(String typedArguments);

    /**
     * APIの実行結果オブジェクトを生成する
     * @param resultElement API実行結果のJSON
     * @return              API実行結果オブジェクト
     */
    public abstract Result generateResult(JSONObject resultElement);
}
