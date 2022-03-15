package backlogcui.command;

import org.json.simple.JSONObject;

import backlogcui.result.Result;

/**
 * APIの実行結果オブジェクトを生成するインターフェース
 * @param resultElement API実行結果のJSON
 * @return              API実行結果オブジェクト
 */
public interface IResultGenerator {
    /**
     * APIの実行結果オブジェクトを生成する
     * @param resultElement API実行結果のJSON
     * @return              API実行結果オブジェクト
     */
    public Result generateResult(JSONObject resultElement);
}
