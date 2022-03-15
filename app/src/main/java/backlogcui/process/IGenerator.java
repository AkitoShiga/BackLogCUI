package backlogcui.process;

import java.util.List;

import org.json.simple.parser.ParseException;

import backlogcui.result.Result;

/**
 * API実行結果生成インターフェース
 */
public interface IGenerator {
    /**
     * @return 結果オブジェクトのリスト
     * @throws ParseException -> JSONの読み取り失敗
     */
    public List<Result> generate() throws ParseException;
}
