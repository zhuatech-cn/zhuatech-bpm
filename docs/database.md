# BPM 数据库

Copyright 2026 上海如静知华信息科技有限公司。MySQL 8，结构由 Flyway 管理。

| 表 | 用途 |
| --- | --- |
| `sys_user` | 演示用户与角色 |
| `bpm_process_definition` | 流程模型及版本状态 |
| `bpm_process_instance` | 流程实例和业务摘要 |
| `bpm_process_task` | 节点任务与处理人 |
| `bpm_approval_task` | 审批办理记录 |
| `bpm_sla_policy` | 流程时效策略与达标统计 |

所有实体继承 `BaseEntity`，保存主键和创建/更新时间。生产扩展建议增加租户、组织、业务主键、乐观锁、软删除、流程变量 JSON、历史快照和审计事件表。
