package backlogcui;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;

import backlogcui.process.APIClient;
import backlogcui.process.IAPIClient;

/**
 * APIClientのテストクラス
 */
@WireMockTest()
public class APIClientTest {
    private IAPIClient apiClient = new APIClient();
    private WireMockServer wireMockServer;
    
    @BeforeEach
    public void setup() {
        wireMockServer = new WireMockServer();
        wireMockServer.stubFor(get(urlEqualTo("/testWireMock"))
                       .willReturn(aResponse().withHeader("Content-Type", "text/plain")
                       .withStatus(200).withBody("ok")));
        wireMockServer.start();
    }

    @AfterEach
    public void close() {
        wireMockServer.stop();
    }

    /**
     * 確認：BacklogAPIをリクエストして正しくレスポンスボディが取得できているか
     */
    @Test
    void sendRequest_isValid() {
        try {
            String result = apiClient.sendRequest("http:localhost/test");
            assertEquals(result,"ok", result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
