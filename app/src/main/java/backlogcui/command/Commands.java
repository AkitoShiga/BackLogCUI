package backlogcui.command;

/**
 * 登録しているコマンドの列挙型
 * ここに登録したコマンドがアプリケーションでの利用可能対象になる
 */
public enum Commands {
    /* ここにコマンドを追加することでアプリに登録される */
    GET_ISSUES(new CommandGetIssues()),
    GET_ISSUE_COMMENTS(new CommandGetIssueComments());

    private Command command;

    /**
     * @param command
     */
    private Commands(Command command) {
        this.command = command;
    }
    /**
     * @return コマンドオブジェクト
     */
    public Command getCommand() {
        return command;
    }
}
