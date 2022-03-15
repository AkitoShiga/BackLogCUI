package backlogcui.process;

/**
 * BacklogAPI 実行の各プロセスの実行インターフェース
 * 各プロセスを1つの状態とみなして実行する
 */
public interface ProcessExecutor {

    /**
     * プロセスの実行処理
     */
    public void execute();
}
