package backlogcui.util;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

/**
 * propertiesファイルに定義されたBacklog 設定情報の読み込みクラス
 */
public class PropertiesConfigLoader implements ConfigLoader {

    private static final String KEY_API_KEY          = "APIKey";
    private static final String KEY_WORKSPACE        = "WorkSpace";
    private static final String PROPERTIES_FILE_PATH
        = System.getProperty("user.dir") + "/common.properties";

    /**
    * propertiesファイルに定義されたBacklog APIKeyの読み込みの実装
    * @return Backlog APIKey
    * @throws IOException -> propertiesファイルが存在しない場合
    */
    public String loadAPIKey() throws IOException {
        Reader reader         = new FileReader(PROPERTIES_FILE_PATH);
        Properties properties = new Properties();

        properties.load(reader);

        return properties.getProperty(KEY_API_KEY);
    }

    /**
    * propertiesファイルに定義されたBacklog WorkSpaceの読み込みの実装
    * @return Backlog WorkSpace
    * @throws IOException -> propertiesファイルが存在しない場合
    */
    public String loadWorkSpace() throws IOException {
        Reader reader         = new FileReader(PROPERTIES_FILE_PATH);
        Properties properties = new Properties();

        properties.load(reader);

        return properties.getProperty(KEY_WORKSPACE);
    }
 }
