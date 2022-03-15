package backlogcui.command;

import org.json.simple.JSONObject;

import backlogcui.result.Result;
import backlogcui.result.ResultGetIssues;
import backlogcui.util.URIConfigurator;
/**
 * 課題の一覧を取得する
 */
public class CommandGetIssues extends Command {

    public CommandGetIssues() {
        commandName      = "get_issues";
        commandArguments = "None";
        description      = "Get a issues registerd in the work space.";
        uri              = "https://:workSpace.backlog.com/api/v2/issues";
        arguments        = "None";
    }

    /**
     * apiを実行するためのURIを組み立てる
     * @param arguments コマンドの引数
     * @return 組み立てられたURI
     */
    public String buildURI(String typedArguments) {
        String configuredURI = URIConfigurator.configureURI(getUri());
        return configuredURI;
    }

    /**
     * このコマンドの結果オブジェクトを生成する
     * @params jsonObject APIの実行結果
     * @return            結果オブジェクト
     */
    public Result generateResult(JSONObject jsonResult) {
        JSONObject issueType = (JSONObject) jsonResult.get("issueType");
        JSONObject priority  = (JSONObject) jsonResult.get("priority");
        JSONObject status    = (JSONObject) jsonResult.get("status");
        String issueKey      = (String)jsonResult.get("issueKey");
        String issueTypeName = (String) issueType.get("name");
        String priorityName  = (String) priority.get("name");
        String statusName    = (String) status.get("name");
        String summary       = (String)jsonResult.get("summary");
        String description   = (String)jsonResult.get("description");
        return new ResultGetIssues(issueKey,
                                   issueTypeName,
                                   priorityName,
                                   statusName,
                                   summary,
                                   description);
    }
}
