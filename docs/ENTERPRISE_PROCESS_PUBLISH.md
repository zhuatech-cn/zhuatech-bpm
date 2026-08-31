# 企业级流程版本发布治理

流程发布前检查模型、角色、职责分离、SLA、存量实例迁移、回滚方案和流程所有者批准。

`POST /api/enterprise/bpm/process-version-publish` 返回 `PUBLISH / APPROVAL_REQUIRED / HOLD` 决策，防止破坏性版本直接影响运行实例。
