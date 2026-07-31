<!-- Copyright 2026 上海如静知华信息科技有限公司 -->
<script setup>import StatusBadge from '../../components/StatusBadge.vue';import {expenses} from '../../api/mock'</script>
<template><div class="employee-mobile"><header><div class="mobile-brand"><span>ZH</span><div><b>知华审批</b><small>ZHUATECH BPM</small></div></div><button>♢</button></header><main>
<section class="mobile-welcome"><p>7 月 31 日 · 星期五</p><h1>下午好，温书屿</h1><span>你的流程待办和时效预警已更新</span></section>
<section class="expense-wallet"><header><span>本月流程任务</span><button>查看明细 →</button></header><strong>126<small> 项</small></strong><div><span><b>4 项</b><small>待我办理</small></span><span><b>12 项</b><small>处理中</small></span><span><b>110 项</b><small>已办结</small></span></div></section>
<section class="quick-actions"><button><i>＋</i><span>发起申请</span><small>智能表单预填</small></button><button><i>↗</i><span>用印申请</span><small>合同与文件场景</small></button><button><i>▤</i><span>我的草稿</span><small>3 份待提交</small></button></section>
<section class="mobile-section"><header><h2>最近流程</h2><button>全部 8 条 →</button></header><article v-for="x in expenses.slice(0,3)" :key="x.no"><span class="expense-icon">{{x.category[0]}}</span><div><b>{{x.purpose}}</b><small>{{x.date}} · {{x.category}} · {{x.no}}</small></div><aside><b>{{x.amount}}</b><StatusBadge :value="x.status"/></aside></article></section>
<section class="policy-tip"><i>i</i><div><b>流程办理提醒</b><p>请关注任务截止时间；转交、退回和加签操作均会完整留痕。</p></div><button>查看制度</button></section>
</main><nav><a class="active"><i>⌂</i><span>首页</span></a><a><i>▤</i><span>待办</span></a><button>＋</button><a><i>◎</i><span>发起</span></a><a><i>◇</i><span>我的</span></a></nav></div></template>
