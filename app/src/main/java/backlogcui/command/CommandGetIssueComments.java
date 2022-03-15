package backlogcui.command;

import org.json.simple.JSONObject;

import backlogcui.result.Result;
import backlogcui.result.ResultGetIssueComments;
import backlogcui.util.URIConfigurator;
/**
 * 課題のコメント一覧を取得する
 */
public class CommandGetIssueComments extends Command {

    public CommandGetIssueComments() {
        commandName      = "get_issue_comments";
        commandArguments = "ID of Issue or ID of Key";
        description      = "Get a list of comments registered in the issue.";
        uri              = "https://:workSpace.backlog.com/api/v2/issues/:issueIdOrKey/comments";
        arguments        = ":issueIdOrKey";
    }

    /**
     * apiを実行するためのURIを組み立てる
     * @param arguments コマンドの引数
     * @return 組み立てられたURI
     */
    public String buildURI(String typedArguments) {
        String configuredURI = URIConfigurator.configureURI(getUri());
        String builtURI      = configuredURI.replace(
                                    getArguments(),
                                    typedArguments
                               );
        return builtURI;
    }

    /**
     * このコマンドの結果オブジェクトを生成する
     * @params jsonObject APIの実行結果
     * @return            結果オブジェクト
     */
    public Result generateResult(JSONObject jsonResult) {
        JSONObject createdUser = (JSONObject) jsonResult.get("createdUser");
        String name            = (String)createdUser.get("name");
        String dateTime        = (String)jsonResult.get("updated");
        String content         = (String)jsonResult.get("content");

        if(content != null) {
            return new ResultGetIssueComments(dateTime, name, content);
        } else {
            return null;
        }
    }
}
