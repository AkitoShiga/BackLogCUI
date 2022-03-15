package backlogcui.result;
/**
 * 課題のコメント一覧の取得結果クラス
 */
public class ResultGetIssueComments extends Result{
    private String dateTime;
    private String name;
    private String content;

    public ResultGetIssueComments(String dateTime, String name, String content) {
        this.dateTime = dateTime;
        this.name     = name;
        this.content  = content;
    }

    /**
     * API実行結果を表示する
     * @return 表示するメッセージ
     */
    public String showResult() {
        StringBuilder resultBuilder = new StringBuilder();

        resultBuilder.append("DateTime : " + dateTime + "\n");
        resultBuilder.append("Name     : " + name + "\n");
        resultBuilder.append("Content  : " + "\n" + content + "\n");

        return resultBuilder.toString();
    }
}
