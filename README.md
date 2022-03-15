# BacklogCUI
## 概要
* BacklogAPIを利用してコマンドライン上でバックログを操作する
* 実行するコマンドを容易に追加出来る仕様になっている
## コマンドの追加方法
1. backlogcui.command.Command を継承したクラスを実装
2. 列挙型のbacklogcui.command.Commandsに1で実装したクラスを登録
3. backlogcui.result.Resultを継承して1.で作成したクラスに紐づくAPI実行結果クラスを作成
## 前提
* 実行するホストにバージョン11以上のJVMがインストールされていること
