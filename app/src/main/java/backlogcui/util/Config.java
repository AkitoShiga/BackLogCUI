package backlogcui.util;

import java.io.IOException;

import backlogcui.exceptions.InvalidConfigException;

/**
 * BacklogAPIと疎通するために使用するConfigのオブジェクト
 * Configはアプリケーション全体で一意かつ不変となる
 */
public class Config {

    // シングルトンだがマルチスレッドで使用しないためvolatileは不要
    private static Config config;
    private static String keyParameter;
    private static String WorkSpaceParameter;
    private static final String EMPTY_PARAMETER = "";
    private static final int ERROR_STATUS       = 1;

    /**
     * 取得したConfigが有効でない状態では機能しないためアプリケーションを終了する
     */
    private Config() {
        try {
            keyParameter       = loadAPIKey();
            WorkSpaceParameter = loadWorkSpace();
            if(!checkFilledConfig()) {
                throw new InvalidConfigException();
            }
        } catch(IOException | InvalidConfigException e) {
            System.err.println(e.getMessage());
            System.exit(ERROR_STATUS);
        }
    }

    /**
     * Configの値を取得する
     * @return Configの登録値
     * @throws IOException -> 外部からの読み込み失敗
     */
    private String loadAPIKey() throws IOException {
        ConfigLoader loader = new PropertiesConfigLoader();
        return loader.loadAPIKey();
    }

    /**
     * WorkSpaceの値を取得する
     * @return WorkSpaceの登録値
     * @throws IOException -> 外部からの読み込み失敗
     */
    private String loadWorkSpace() throws IOException {
        ConfigLoader loader = new PropertiesConfigLoader();
        return loader.loadWorkSpace();
    }

    /**
     * Configに値がセットされているか判定する
     * @return 判定結果
     */
    public boolean checkFilledConfig() {
        return !getKeyParameter().equals(EMPTY_PARAMETER) &&
               !getWorkSpaceParameter().equals(EMPTY_PARAMETER);
    }

    /**
     * 外部へのインスタンス取得の窓口
     * @return Configインスタンス
     */
    public static Config getInstance() {
        if(config == null) {
            config = new Config();
        }
        return config;
    }

    /**
     * 登録されたAPIKeyの値を返却
     * @return APIKeyの値
     */
    public String getKeyParameter() {
        return keyParameter;
    }

    /**
     * 登録されたWorkSpaceの値を返却
     * @return WorkSpaceの値
     */
    public String getWorkSpaceParameter() {
        return WorkSpaceParameter;
    }
}
