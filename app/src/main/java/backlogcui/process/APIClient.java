package backlogcui.process;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
/**
 * API実行クラス
 */
public class APIClient implements IAPIClient {

    /**
     * APIを実行する
     * @param uri APIのURI
     * @return    レスポンスボディ
     * @throws IOException, InterruptedException -> APIの実行失敗
     */
    public String sendRequest(String uri)throws IOException, InterruptedException {
        HttpResponse<String> response = null;
        HttpClient client             = HttpClient.newHttpClient();
        HttpRequest request           = HttpRequest.newBuilder()
                                                   .uri(URI.create(uri))
                                                   .build();
        response                      = client.send(request, BodyHandlers.ofString());

        return (String)response.body();
    }
}
