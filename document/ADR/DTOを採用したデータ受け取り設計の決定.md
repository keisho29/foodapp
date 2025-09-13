# ADR-003: DTOを採用したデータ受け取り設計の決定

## Status
Accepted

## Context
ユーザーがWebフォームに入力した食材名や摂取量などのデータをサーバー側に送信し、PFCおよびカロリーを計算・保存する必要がある。

その際、HTMLフォームの内容を直接Entityにバインドしてしまうと、以下のような問題が生じる：
- DB構造とフォーム構造の強い結合
- バリデーションや変換処理の柔軟性が損なわれる
- Entityクラスがフォーム仕様に引きずられる

## Decision
- HTMLフォームから受け取るデータは、**DTO（Data Transfer Object）** で受け取る
- DTOクラスにはバリデーションアノテーション（例：`@NotBlank`、`@Positive`など）を使用
- 必要に応じてDTO→Entityの変換処理をApplicationService層で実施する

## Consequences

### Positive
- バリデーションロジックをDTOに集約できる
- Entityとフォームの構造を分離でき、保守性が向上
- 将来的にAPI対応する場合でも再利用しやすい
- セキュリティ上、Entityを直接外部入力に晒さずに済む

### Negative
- DTOとEntityの変換コードが増える
- クラス数が増えることで、プロジェクトがやや複雑に見える可能性がある
