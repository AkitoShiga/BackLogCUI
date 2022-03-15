package backlogcui.process;
/**
 * API実行インターフェース
 */
public interface IAPIClient {

    /**
     * APIを実行する
     * @param uri APIのURI
     * @return    レスポンスボディ
     */
    public String sendRequest(String uri) throws Exception;

}
