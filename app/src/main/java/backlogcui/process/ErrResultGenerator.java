package backlogcui.process;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.ParseException;

import backlogcui.result.Result;
import backlogcui.result.ResultError;

/**
 * エラーのAPI実行結果を生成するクラス
 */
public class ErrResultGenerator implements IGenerator {
    String message;

    public ErrResultGenerator(String message) {
        this.message = message;
    }

    /**
     * responseBodyの型に応じてResultオブジェクトのリストを返却
     * @return API実行結果オブジェクト
     * @throws ParseException -> JSONパースに失敗
     */
    public List<Result> generate() throws ParseException {
        List<Result> resultList = new ArrayList<>();
        resultList.add(new ResultError(message));
        return resultList;
    }
}
