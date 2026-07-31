# BPM API 摘要

Copyright 2026 上海如静知华信息科技有限公司。

除 `/api/auth/login` 与健康检查外，接口要求 `Authorization: Bearer <token>`；业务前缀为 `/api/bpm`。

| 方法 | 路径 | 说明 |
| --- | --- | --- |
| GET | `/dashboard` | 流程、实例、任务与 SLA 摘要 |
| GET | `/process-definitions` | 流程模型与发布状态 |
| GET / POST | `/process-instances` | 查询或发起流程实例 |
| PATCH | `/process-instances/{id}/progress` | 更新实例办理进度 |
| GET | `/process-tasks` | 流程任务队列 |
| GET / POST | `/approval-tasks` | 个人待办与提交办理结果 |
| GET | `/sla-policies` | 流程时效策略 |

演示角色：`ADMIN`、`PROCESS_MANAGER`、`EMPLOYEE`。生产环境还需补充组织数据权限、代理委托、表单字段权限和不可篡改审计。
