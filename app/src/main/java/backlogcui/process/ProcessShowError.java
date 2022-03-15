package backlogcui.process;

public class ProcessShowError implements ProcessExecutor {
    public String errorMessage;

    /**
     * @param errorMessage エラー事由
     */
    public ProcessShowError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * エラーメッセージを表示して初期状態に戻る
     */
    public void execute() {
        printError();
        executeNextProcess();
    }

    /**
     * エラーメッセージを表示する
     */
    private void printError() {
        System.out.println(errorMessage);
    }

    /**
     * 次のプロセスを実行する
     */
    private void executeNextProcess() {
        ProcessExecutor nextProcess = new ProcessShowAllCommands();
        nextProcess.execute();
        nextProcess = null;
    }
}
