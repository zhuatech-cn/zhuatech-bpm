<!-- Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ -->
<script setup>
const groups=[
  {label:'流程运营',items:[['/admin/cockpit','流程运营台','⌂'],['/admin/treasury','流程设计器','￥'],['/admin/settlement','流程实例','⇄'],['/admin/budget','任务与时效','▤']]},
  {label:'分析与协同',items:[['/admin/reports','流程分析','⌁'],['/employee/expenses','移动审批台','◇']]}
]
</script>
<template><div class="app-shell"><aside class="sidebar">
  <div class="brand-mark"><span>ZH</span><div><b>ZhuaTech BPM</b><small>PROCESS OPERATIONS</small></div></div>
  <button class="tenant"><i></i><span><b>上海如静知华信息科技有限公司</b><small>2026 财年 · CNY</small></span><em>⌄</em></button>
  <section v-for="g in groups" :key="g.label"><p>{{g.label}}</p><nav><RouterLink v-for="x in g.items" :key="x[0]" :to="x[0]"><i>{{x[2]}}</i><span>{{x[1]}}</span><b v-if="x[1]==='流程实例'">3</b></RouterLink></nav></section>
  <div class="license-note"><b>社区源码版</b><span>仅限个人非商业学习交流</span><a href="https://www.zhuatech.cn/">zhuatech.cn ↗</a></div>
  <div class="profile"><span>陆</span><div><b>陆嘉言</b><small>流程平台主管</small></div><button>•••</button></div>
 </aside><main class="workspace"><header class="topbar"><div class="crumb"><small>流程运营中心 /</small><b><slot name="title"/></b></div><label class="search">⌕<input placeholder="搜索单据、客户、供应商或科目"/><kbd>⌘ K</kbd></label><div class="top-actions"><button>?</button><button class="bell">♢<i></i></button><span>2026 / 07 / 31</span></div></header><div class="page"><slot/></div><footer>© 2026 上海如静知华信息科技有限公司 · <a href="https://www.zhuatech.cn/">知华科技</a></footer></main>
</div></template>
