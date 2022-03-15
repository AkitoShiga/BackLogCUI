package backlogcui.process;

import backlogcui.util.ProcessUtil;

/**
 * エラーが発生した時にその内容を表示したあとにハンドリングするプロセス
 */
public class ProcessError implements ProcessExecutor {
    private String message;

    public ProcessError(String message) {
        this.message = message;
    }

    /**
     * コマンドの実行結果を表示する
     */
    public void execute() {
        showMessage();
        waitPressEnter();
        executeNextProcess();
    }

    /**
     * エラー内容を表示する
     */
    private void showMessage() {
        System.out.println(message);
    }

    /**
     * ユーザーのエンターキー押下を待つ
     */
    private void waitPressEnter() {
        ProcessUtil.waitPressEnter();
    }

    /**
     * コマンドの実行結果を表示する
     */
    private void executeNextProcess() {
        ProcessExecutor nextProcess = new ProcessShowAllCommands();
        nextProcess.execute();
        nextProcess = null;
    }
}
