# ADR-004: 計算ロジックをDomainServiceに配置する判断

## Status
Accepted

## Context
アプリでは、食材ごとのPFC・カロリー（100gあたり）と、摂取量（g）をもとに、実際の摂取量に応じた栄養素・カロリーを計算する必要がある。

このような計算は、ビジネス上のルールに基づく重要な処理であり、アプリケーションの中核的ドメインに属すると考えられる。

一方で、計算ロジックをControllerやServiceに直接書くと、責務が曖昧になり再利用・テスト・保守が困難になる。

## Decision
- PFCおよびカロリーの合計計算は、**DomainService** に集約する
- DomainService内で、`(摂取量 ÷ 100) × 栄養価` の計算処理を実装
- ApplicationServiceは、DTOから値を受け取り、計算処理はDomainServiceに委譲する

## Consequences

### Positive
- 計算処理を集約し、再利用性が高まる
- テストがしやすくなる（DomainService単体テスト可能）
- ControllerやApplicationServiceが軽量化し、責務が明確になる
- ドメインロジックをドメイン層に閉じ込めることで、設計として一貫性がある

### Negative
- 小規模アプリでは、DomainServiceという層の導入が「設計過剰」に見える可能性がある
- 単純な計算であっても、毎回サービスを経由する必要があるため、手間が増える場合がある
