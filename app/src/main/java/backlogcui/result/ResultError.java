package backlogcui.result;

/**
 * 各コマンドのの取得結果がエラーだった場合の結果クラス
 */
public class ResultError extends Result{
    private String message;

    /**
     * @param message メッセージの内容
     */
    public ResultError(String message) {
        this.message  = message;
    }

    /**
     * API実行結果を表示する
     * @return 表示するメッセージ
     */
    public String showResult() {
        return "Error occured becouse of " + message + "\n";
    }
}
