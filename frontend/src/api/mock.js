/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
export const accounts=[
  {code:'FLOW-CONTRACT',name:'合同评审流程',bank:'法务 · 财务 · 交付联合评审',type:'业务流程',balance:286,available:278,status:'已发布'},
  {code:'FLOW-PURCHASE',name:'采购申请流程',bank:'申请 · 询价 · 审批 · 下单',type:'采购流程',balance:128,available:124,status:'已发布'},
  {code:'FLOW-SEAL',name:'印章使用流程',bank:'用印申请与归档留痕',type:'行政流程',balance:86,available:82,status:'草稿'},
  {code:'FLOW-CHANGE',name:'项目变更流程',bank:'范围、成本和计划协同',type:'项目流程',balance:76,available:69,status:'维护中'}]
export const receivables=[
  {no:'PI-260731-018',customer:'合同评审',source:'华东智造数字化项目 · 交付合同',amount:8.6,received:5.0,due:'08-03',owner:'顾清禾',status:'审批中'},
  {no:'PI-260731-023',customer:'采购申请',source:'数据平台服务器扩容采购',amount:12.6,received:8.0,due:'08-01',owner:'许知遥',status:'待审批'},
  {no:'PI-260731-097',customer:'项目变更',source:'仓储项目接口范围调整',amount:23.8,received:21.0,due:'07-31',owner:'林清越',status:'已逾期'},
  {no:'PI-260731-036',customer:'用印申请',source:'南京客户验收文件盖章',amount:3.2,received:3.0,due:'08-02',owner:'唐予安',status:'审批中'},
  {no:'PI-260731-041',customer:'费用报销',source:'客户现场调研差旅报销',amount:1.8,received:1.8,due:'08-04',owner:'苏景行',status:'已完成'}]
export const payables=[
  {no:'TASK-2607-112',supplier:'合同法务复核',source:'PI-260731-018 · 合同评审',amount:18.6,paid:0,due:'今天 17:00',applicant:'江叙',status:'待审批'},
  {no:'TASK-2607-108',supplier:'采购预算确认',source:'PI-260731-023 · 采购申请',amount:9.8,paid:4.9,due:'今天 15:30',applicant:'苏景行',status:'审批中'},
  {no:'TASK-2607-119',supplier:'项目成本复核',source:'PI-260731-097 · 项目变更',amount:2.8,paid:0,due:'已超时 42 分钟',applicant:'温书屿',status:'已逾期'},
  {no:'TASK-2607-126',supplier:'档案归档确认',source:'PI-260731-036 · 用印申请',amount:6.5,paid:0,due:'明天 12:00',applicant:'陆嘉言',status:'待审批'}]
export const expenses=[
  {no:'TASK-260731-034',person:'温书屿',dept:'咨询交付部',category:'合同评审',purpose:'南京客户实施合同交付条款复核',amount:'剩余 1h 26m',date:'10:30',status:'待审批'},
  {no:'TASK-260731-029',person:'江叙',dept:'研发中心',category:'采购申请',purpose:'测试环境服务器扩容采购确认',amount:'剩余 3h 10m',date:'09:42',status:'审批中'},
  {no:'TASK-260730-086',person:'顾呈',dept:'产品中心',category:'项目变更',purpose:'主数据治理范围调整影响评估',amount:'已办结',date:'昨天',status:'已完成'},
  {no:'TASK-260729-061',person:'唐予安',dept:'数据服务部',category:'权限申请',purpose:'生产数据只读权限开通',amount:'退回修改',date:'昨天',status:'已驳回'}]
export const budgets=[
  {dept:'合同评审',subject:'法务与财务联合审批',annual:320,occupied:36,actual:296,rate:92.5,status:'正常'},
  {dept:'采购申请',subject:'预算与采购协同',annual:268,occupied:28,actual:241,rate:89.9,status:'正常'},
  {dept:'项目变更',subject:'范围与成本评审',annual:120,occupied:16.8,actual:94,rate:78.3,status:'预警'},
  {dept:'用印申请',subject:'行政与档案协同',annual:96,occupied:9.2,actual:91,rate:94.8,status:'正常'},
  {dept:'权限申请',subject:'数据安全审批',annual:148,occupied:12.5,actual:139,rate:93.9,status:'正常'}]
export const cashflow=[92,118,105,136,124,158,145,172,151,188,176,204]
