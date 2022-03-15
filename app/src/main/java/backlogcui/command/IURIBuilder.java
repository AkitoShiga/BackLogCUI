package backlogcui.command;

/**
 * API実行用URIを組み立てるインターフェース
 */
public interface IURIBuilder {
    /**
     * API実行のURIを組み立てる
     * @param typedArguments ユーザーが入力したコマンド引数
     * @return               組み立てられたURI
     */
    public String buildURI(String typedArguments);
}
