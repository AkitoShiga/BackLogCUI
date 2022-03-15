package backlogcui.exceptions;
/**
 * 設定情報の値が不正だった場合に呼び出される例外
 */
public class InvalidConfigException extends Exception {

    public InvalidConfigException() {
        super("The Registerd Config Information is invalid.");
    }

}
