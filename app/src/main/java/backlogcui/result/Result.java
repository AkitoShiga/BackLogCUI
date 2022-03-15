package backlogcui.result;

/**
 * API実行結果の基底クラス
 */
public abstract class Result implements IShowResult {

    /**
     * API実行結果を表示する
     * @return 表示する内容
     */
    public abstract String showResult();
}
