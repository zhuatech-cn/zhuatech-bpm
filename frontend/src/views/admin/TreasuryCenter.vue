<!-- Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ -->
<script setup>
import AdminShell from '../../components/AdminShell.vue'
import StatusBadge from '../../components/StatusBadge.vue'
import {accounts} from '../../api/mock'
const events=[
 {time:'15:42',summary:'合同评审流程 v3.2 发布',account:'陆嘉言',income:'新增会签节点',out:'2 条规则',balance:'已生效',status:'已发布'},
 {time:'14:18',summary:'采购申请条件路由调整',account:'江叙',income:'金额分支',out:'4 个条件',balance:'待验证',status:'待复核'},
 {time:'11:06',summary:'用印申请表单字段更新',account:'顾清禾',income:'新增附件校验',out:'1 个字段',balance:'草稿',status:'维护中'},
 {time:'09:35',summary:'项目变更流程灰度上线',account:'唐予安',income:'10% 流量',out:'无异常',balance:'观察中',status:'正常'}]
</script>
<template><AdminShell><template #title>流程设计器</template><div class="page-heading"><div><p>PROCESS DESIGNER</p><h1>流程模型库</h1><span>流程版本、节点规则、表单与权限集中管理</span></div><div><button class="button secondary">导入 BPMN</button><button class="button primary">＋ 新建流程</button></div></div>
<section class="treasury-hero"><div><span>已发布流程模型</span><strong>42<small> 个</small></strong><p>草稿 8 个 · 待验证 3 个 · 本月发布 6 个</p></div><div class="allocation"><article><span>人工审批节点</span><b>58%</b><i><em style="width:58%"></em></i></article><article><span>自动服务节点</span><b>31%</b><i><em style="width:31%"></em></i></article><article><span>条件与并行网关</span><b>11%</b><i><em style="width:11%"></em></i></article></div><aside><small>模型健康指数</small><b>92</b><span>无阻断级错误</span></aside></section>
<section class="account-grid"><article v-for="(x,i) in accounts" :key="x.code" class="account-card"><header><span class="bank-logo">{{i+1}}</span><div><b>{{x.name}}</b><small>{{x.bank}}</small></div><button>•••</button></header><div><span>当前版本</span><strong>v{{i+2}}.{{i+1}}</strong></div><footer><span>近 30 日实例 <b>{{x.balance.toFixed(0)}}</b></span><span>{{x.type}}</span><StatusBadge :value="x.status"/></footer></article></section>
<section class="card transaction-card"><header><div><h2>模型变更记录</h2><p>最近更新 2026-07-31 15:42:18</p></div><div><button>版本对比</button><button>导出清单</button></div></header><table><thead><tr><th>时间</th><th>变更内容</th><th>操作人</th><th>变更项</th><th>影响范围</th><th>发布阶段</th><th>状态</th></tr></thead><tbody><tr v-for="x in events" :key="x.time"><td>{{x.time}}</td><td><b>{{x.summary}}</b></td><td>{{x.account}}</td><td class="positive">{{x.income}}</td><td>{{x.out}}</td><td>{{x.balance}}</td><td><StatusBadge :value="x.status"/></td></tr></tbody></table></section>
</AdminShell></template>
