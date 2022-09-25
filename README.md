# ディレクトリ構成
```
com.example.githubusers  
┣ core  
┃ ┃ Model,View,ViewModelどこでも使いそうなもの  
┃ ┣ data DataClass  
┃ ┣ dummy 開発用ダミーデータ  
┣ model  
┃ ┃ MVVMのModel  
┃ ┣ local ローカルDB（Room,SheardPreference）との接続  
┃ ┣ remote サーバーとの通信 APIとかFirebaseとか  
┃ ┣ repository remoteとlocalの振り分け  
┣ ui  
┃ ┃ MVVMのView相当  
┃ ┣ destination 画面遷移を管理 Navigationを使用  
┃ ┣ page 画面単位のComposable  
┃ ┣ component 部品単位のCompose  
┃ ┣ theme アプリ全体のテーマ定義  
┣ viewModel  
┃ ┃ MVVMのViewModel  
```

# 機能追加方針
 - コスパ（実装コストとユーザーへの影響）
 - ユーザーが意識しないで享受できる機能（いわゆるクオリティ）が優先
   - Youtubeで追加された機能例
     - 前回再生した箇所を記憶する
     - 広告後に1~2秒戻して広告前後が繋がりやすくする
     - よく再生開始された箇所を表示する
 - ページを増やす、とかのユーザーに意識的に取捨選択させる機能は優先度下がる

# TODO
## 優先度高
 - Material You
 - Dark Theme
 - Animation
   - アイコンを[ヒーローアニメーション](https://youtu.be/Be9UH1kXFDw)でつなげる
 - フォント

## 優先度中
 - 検索
 - お気に入り

## 優先度低
 - Githubログイン
 - ソート
 - 多言語対応
   - Google翻訳とかで自動生成する
 - 色切り替え
   - LightとDark
   - 彩度は統一で、マテリアルデザインの基準色から選べるようにする

## やり残し
 - カードのelevationを調整してクリックできる感を出す
 - リスト一番上の余白調整
 - リストにbioを表示
 - APIリクエストが二回行われているのを修正
 - NavHostを別ファイルに分割
 - UserListの取得タイミングを変更