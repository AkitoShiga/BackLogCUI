package backlogcui.process;

import backlogcui.command.Command;
import backlogcui.command.Commands;

/**
 * 登録されている全てのコマンドを表示する
 */
public class ProcessShowAllCommands implements ProcessExecutor {

    private final String COMMAND_NAME_HEADER = "[Command name]";
    private final String COMMAND_ARGS_HEADER = "[Arguments]";
    private final String DESCRIPTION_HEADER  = "[Description]";
    private final String SEPARATOR           = " | ";
    private int maxCommandNameLength         = COMMAND_NAME_HEADER.length();
    private int maxCommandArgumentsLength    = COMMAND_ARGS_HEADER.length();
    private int maxDescriptionLength         = DESCRIPTION_HEADER.length();

    /**
     * 登録されている全てのコマンドを表示する実装
     */
    public void execute() {
        setCommandInfoMaxLength();
        StringBuilder commandInfoBuilder = new StringBuilder();
        buildHeader(commandInfoBuilder);
        buildCommandInfo(commandInfoBuilder);
        printCommandInfo(commandInfoBuilder.toString());
        executeNextProcess();
    }

    /**
     * 表示内容の整形に使用する、各表示要素の最大文字数を取得する
     */
    private void setCommandInfoMaxLength() {
        for(Commands commands : Commands.values()) {

            Command command = commands.getCommand();

            maxCommandNameLength = Math.max(
                    maxCommandNameLength,
                    command.getCommandName().length()
            );
            maxCommandArgumentsLength = Math.max(
                    maxCommandArgumentsLength,
                    command.getCommandArguments().length()
            );
            maxDescriptionLength = Math.max(
                    maxDescriptionLength,
                    command.getDescription().length()
            );
        }
    }

    /**
     * コマンドのヘッダー部分を組み立てる
     * @param commandInfoBuilder
     */
    private void buildHeader(StringBuilder commandInfoBuilder) {
            commandInfoBuilder.append(SEPARATOR);

            commandInfoBuilder.append(COMMAND_NAME_HEADER);
            appendSpace(
                commandInfoBuilder,
                maxCommandNameLength - COMMAND_NAME_HEADER.length()
            );
            commandInfoBuilder.append(SEPARATOR);

            commandInfoBuilder.append(COMMAND_ARGS_HEADER);
            appendSpace(
                commandInfoBuilder,
                maxCommandArgumentsLength - COMMAND_ARGS_HEADER.length()
            );
            commandInfoBuilder.append(SEPARATOR);

            commandInfoBuilder.append(DESCRIPTION_HEADER);
            appendSpace(
                commandInfoBuilder,
                maxDescriptionLength - DESCRIPTION_HEADER.length()
            );
            commandInfoBuilder.append(SEPARATOR);
            commandInfoBuilder.append("\n");
    }

    /**
     * コマンドの説明分を組み立てる
     * @param commandInfoBuilder コマンド説明用SB
     */
    private void buildCommandInfo(StringBuilder commandInfoBuilder) {
        for(Commands commands : Commands.values()) {
            Command command         = commands.getCommand();
            String commandName      = command.getCommandName();
            String commandArguments = command.getCommandArguments();
            String description      = command.getDescription();

            commandInfoBuilder.append(SEPARATOR);

            commandInfoBuilder.append(commandName);
            appendSpace(
                commandInfoBuilder,
                maxCommandNameLength - commandName.length()
            );

            commandInfoBuilder.append(SEPARATOR);

            commandInfoBuilder.append(commandArguments);
            appendSpace(
                commandInfoBuilder,
                maxCommandArgumentsLength - commandArguments.length()
            );

            commandInfoBuilder.append(SEPARATOR);

            commandInfoBuilder.append(description);
            appendSpace(
                commandInfoBuilder,
                maxDescriptionLength - description.length()
            );

            commandInfoBuilder.append(SEPARATOR);
            commandInfoBuilder.append("\n");
        }
    }

    /**
     * 説明分整形用にスペースを追加
     * @param  commandInfoBuilder コマンド説明用SB
     * @param  spaceLength        スペースを追加する数
     */
    private void appendSpace(StringBuilder commandInfoBuilder, int spaceLength) {
        for(int i = 0; i < spaceLength; i++) {
            commandInfoBuilder.append(" ");
        }
    }

    /**
     * コマンドの説明分を表示する
     * @param commandInfo コマンドの説明文
     */
    private void printCommandInfo(String commandInfo) {
        System.out.println(commandInfo);
    }

    /**
     * 次のコマンドを起動する
     */
    public void executeNextProcess() {
        ProcessExecutor nextProcess = new ProcessCommandInputReception();
        nextProcess.execute();
        nextProcess = null;
    }
}
