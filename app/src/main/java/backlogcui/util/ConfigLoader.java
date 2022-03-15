package backlogcui.util;

import java.io.IOException;

/**
 * 外部からのBackLog APIKey読み込みインターフェース
 */
public interface ConfigLoader {
    /**
     * APIKeyを読み込む
     * @return Backlog APIKey
     */
    public String loadAPIKey() throws IOException;

    /**
     * ワークスペース情報を読み込む
     * @return Backlog WorkSpace
     */
    public String loadWorkSpace() throws IOException;
}
