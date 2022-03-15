package backlogcui.process;

import java.util.Scanner;

import backlogcui.command.Command;
import backlogcui.util.ProcessUtil;
/**
 * コマンドの入力を受け付けるクラス
 */
public class ProcessCommandInputReception implements ProcessExecutor{

    private final int NUMBER_COMMAND           = 1; // 引数の数を数えるための定数
    private final int INDEX_COMMAND_NAME       = 0; // コマンド抽出用の定数
    private final int INDEX_ARGUMENTS          = 1; // コマンド抽出用の定数
    private final String NO_ARGUMENTS          = "";
    private final String PROMPT_TYPE_COMMAND   = "Please type the command : ";
    private final String INVALID_TYPED_COMMAND = "The input command is invalid";

    /**
     * コマンドの入力を受け付ける
     */
    public void execute() {
        printGuideMessage();
        String typedCommand = getTypedCommand();
        String commandName  = extractCommandName(typedCommand);
        String arguments    = extractArguments(typedCommand);
        if(checkCommandRegisterd(commandName) &&
           checkArgumentEntered(commandName, arguments)) {
            executeNextProcess(commandName, arguments);
        } else {
            executeFirstProcess();
        }
    }

    /**
     * コマンド入力をガイドするメッセージを出力
     */
    private void printGuideMessage() {
        System.out.print(PROMPT_TYPE_COMMAND);
    }

    /**
     * ユーザーの入力値を取得する
     * @return ユーザーの入力値
     */
    @SuppressWarnings("resource")
    private String getTypedCommand() {
        return new Scanner(System.in).nextLine();
    }

    /**
     * 入力されたコマンドが登録されているか判定する
     * @param  commandName ユーザーの入力値
     * @return              判定結果
     **/
    private boolean checkCommandRegisterd(String commandeName) {
        return ProcessUtil.getCommand(commandeName) instanceof Command;
    }

    /**
     * 引数が入力されているか判定する
     * @param  commandName コマンド名
     * @param  arguments   引数
     * @return              判定結果
     **/
    private boolean checkArgumentEntered(String commandName, String arguments) {
        if(ProcessUtil.getCommand(commandName).getArguments() == "None") {
            return true;
        } else {
            return arguments != NO_ARGUMENTS;
        }
    }

    /**
     * 次のプロセスを起動する
     * @param commandName
     * @param arguments
     */
    private void executeNextProcess(String commandName, String arguments) {
        ProcessExecutor nextProcess =
            new ProcessCommandExecutor(ProcessUtil.getCommand(commandName), arguments);
        nextProcess.execute();
        nextProcess = null;
    }

    /**
     * 次のプロセスを起動する
     */
    private void executeFirstProcess() {
        System.out.println(INVALID_TYPED_COMMAND);
        ProcessUtil.waitPressEnter();
        ProcessExecutor process = new ProcessShowAllCommands();
        process.execute();
    }

    /**
     * コマンド名を抽出する
     * @param  typedCommand ユーザーの入力値
     * @return 引数
     */
    private String extractCommandName(String typedCommand) {
        return typedCommand.split(" ")[INDEX_COMMAND_NAME];
    }

    /**
     * 引数を抽出する
     * @param  typedCommand ユーザーの入力値
     * @return 引数
     */
    private String extractArguments(String typedCommand) {
        String[] typedArray = typedCommand.split(" ");
        if(typedArray.length == NUMBER_COMMAND) {
            return NO_ARGUMENTS;
        } else {
            return typedArray[INDEX_ARGUMENTS];
        }
    }
}
