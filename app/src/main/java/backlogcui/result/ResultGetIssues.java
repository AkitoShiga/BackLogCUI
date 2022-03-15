package backlogcui.result;

/**
 * 課題の一覧の取得結果クラス
 */
public class ResultGetIssues extends Result {
    private String issueKey;
    private String issueType;
    private String priority;
    private String status;
    private String title;
    private String description;

    /**
     * @param issueKey    課題キー
     * @param issueType   課題種別
     * @param priority    優先度
     * @param status      状態
     * @param title       タイトル
     * @param description 説明
     */
    public ResultGetIssues(String issueKey,
                           String issueType,
                           String priority,
                           String status,
                           String title,
                           String description) {
         this.issueKey    = issueKey;
         this.issueType   = issueType;
         this.priority    = priority;
         this.status      = status;
         this.title       = title;
         this.description = description;
    }

    /**
     * API実行結果を表示する
     * @return 表示するメッセージ
     */
    public String showResult() {
        StringBuilder resultBuilder = new StringBuilder();

        resultBuilder.append("Issue key   : " + issueKey + "\n");
        resultBuilder.append("Issue type  : " + issueType + "\n");
        resultBuilder.append("Priority    : " + priority + "\n");
        resultBuilder.append("Status      : " + status + "\n");
        resultBuilder.append("Title       : " + title + "\n");
        resultBuilder.append("Description : " + "\n" + description + "\n");

        return resultBuilder.toString();
    }
}
