package backlogcui.util;

/**
 * API実行URIの設定をするクラス
 */
public class URIConfigurator {
    /**
     * API実行URIの設定をする
     * @param uri 実行するURI
     * @return    設定後のURI
     */
    public static String configureURI(String uri) {
         return addAPIKey(addWorkSpace(uri));
    }

    /**
     * URIのワークスペースを追加する
     * @param  uri APIのURI
     * @return     ワークスペースを追加した URI
     */
    private static String addWorkSpace(String uri) {
        String workSpace = Config.getInstance().getWorkSpaceParameter();
        return uri.replace(":workSpace", workSpace);
    }

    /**
     * URIのAPIKeyを追加する
     * @param uri
     * @return
     */
    private static String addAPIKey(String uri) {
        String apiKey = Config.getInstance().getKeyParameter();
        return uri + "?" + "apiKey" + "=" + apiKey;
    }
}
