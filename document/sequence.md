```mermaid
sequenceDiagram
    participant User as ユーザー
    participant Controller as TodoController
    participant AppService as TodoApplicationService
    participant DomainService as TodoDomainService
    participant Repository as TodoRepository
    participant JPA as JpaTodoRepository
    participant DB as データベース

    %% 登録処理
    User ->> Controller: 登録フォーム送信（TodoDTO）
    Controller ->> AppService: 登録依頼
    AppService ->> DomainService: ドメインルール適用
    AppService ->> Repository: 保存依頼
    Repository ->> JPA: 保存
    JPA ->> DB: INSERT文
    DB -->> JPA: 結果返却
    JPA -->> Repository: 保存成功
    Repository -->> AppService: 完了応答
    AppService -->> Controller: 完了応答
    Controller -->> User: 登録完了

    %% 更新処理
    User ->> Controller: 更新フォーム送信（TodoDTO）
    Controller ->> AppService: 更新依頼
    AppService ->> DomainService: バリデーション
    AppService ->> Repository: 更新依頼
    Repository ->> JPA: 更新
    JPA ->> DB: UPDATE文
    DB -->> JPA: 結果返却
    JPA -->> Repository: 更新成功
    Repository -->> AppService: 完了応答
    AppService -->> Controller: 完了応答
    Controller -->> User: 更新完了

    %% 検索処理
    User ->> Controller: 検索条件を送信
    Controller ->> AppService: 検索依頼
    AppService ->> Repository: 検索
    Repository ->> JPA: クエリ発行
    JPA ->> DB: SELECT文
    DB -->> JPA: 結果返却
    JPA -->> Repository: 検索結果
    Repository -->> AppService: 結果返却
    AppService -->> Controller: 結果返却
    Controller -->> User: 結果表示

    %% 削除処理
    User ->> Controller: 削除依頼（ID）
    Controller ->> AppService: 削除依頼
    AppService ->> Repository: 削除依頼
    Repository ->> JPA: 削除
    JPA ->> DB: DELETE文
    DB -->> JPA: 結果返却
    JPA -->> Repository: 削除成功
    Repository -->> AppService: 完了応答
    AppService -->> Controller: 完了応答
    Controller -->> User: 削除完了

    %    %% カロリー計算処理（ハイブリッド型）
    User ->> Controller: 食材IDと食べた量を送信
    Controller ->> AppService: 計算依頼（食材IDと量のリスト）
    AppService ->> Repository: 対象食材を取得（IDからマスタ取得）
    Repository ->> JPA: クエリ発行
    JPA ->> DB: SELECT文（食材ID IN (...)）
    DB -->> JPA: 食材マスタ情報を返却
    JPA -->> Repository: 食材マスタ情報を返却
    Repository -->> AppService: 食材情報返却
    AppService ->> DomainService: 食材情報と食べた量を元に計算
    DomainService -->> AppService: 合計カロリー・PFCを返却
    AppService -->> Controller: 計算結果返却
    Controller -->> User: 合計カロリー・PFCを表示