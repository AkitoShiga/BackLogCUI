package backlogcui.process;

import java.util.List;

import backlogcui.result.Result;
import backlogcui.util.ProcessUtil;

/**
 * コマンドの実行結果を表示するプロセス
 */
public class ProcessShowResult implements ProcessExecutor {
    List<Result> results;

    public ProcessShowResult(List<Result> results) {
        this.results = results;
    }

    /**
     * コマンドの実行結果を表示する
     */
    public void execute() {
        showResult();
        waitPressEnter();
        executeNextProcess();
    }

    /**
     * コマンドの実行結果を表示する
     */
    private void showResult() {
        for(Result result : results) {
            System.out.println(result.showResult());
        }
    }

    /**
     * ユーザーのエンターキー押下を待つ
     */
    private void waitPressEnter() {
        ProcessUtil.waitPressEnter();
    }

    /**
     * 次のプロセスを実行する
     */
    private void executeNextProcess() {
        ProcessExecutor nextProcess = new ProcessShowAllCommands();
        nextProcess.execute();
        nextProcess =  null;
    }
}
