package backlogcui.util;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import backlogcui.command.Command;
import backlogcui.command.Commands;

/**
 * 各プロセスが使用するユーティリティクラス
 */
public class ProcessUtil {

    private static final String PROMPT_PRESS_ENTER = "Please press enter";

    /**
     * 引数が入力値に応じたコマンドのインスタンスを返却する
     * @param  typedCommand ユーザーの入力値
     * @return              紐づくコマンドのインスタンス
     **/
    public static Command getCommand(String typedCommand) {
        for(Commands commands : Commands.values()) {
            Command command = commands.getCommand();
            if(command.getCommandName().equals(typedCommand)) {
                return command;
            }
        }
        return null;
    }

    /**
     * ユーザーの入力値の引数の数を確認する
     * @param  typedCommand ユーザーの入力値
     * @return              引数の数
     **/
    public static List<String> getArgs(String typedCommand) {
        return Arrays.asList(typedCommand.split(" "));
    }

    /**
     * エンターキーを押下するまで処理を中断する
     **/
    @SuppressWarnings("resource")
    public static void waitPressEnter() {
        System.out.print(PROMPT_PRESS_ENTER);
        Scanner waiter  = new Scanner(System.in);
        waiter.nextLine();
    }
}
