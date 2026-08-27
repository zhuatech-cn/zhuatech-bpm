/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
import {createRouter,createWebHistory} from 'vue-router'
import LoginView from '../views/LoginView.vue'
import FinanceCockpit from '../views/admin/FinanceCockpit.vue'
import TreasuryCenter from '../views/admin/TreasuryCenter.vue'
import SettlementLedger from '../views/admin/SettlementLedger.vue'
import BudgetExpense from '../views/admin/BudgetExpense.vue'
import ExpenseWorkbench from '../views/member/ExpenseWorkbench.vue'
import WorkflowGovernance from '../views/admin/WorkflowGovernance.vue'

const router=createRouter({history:createWebHistory(),routes:[
  {path:'/',redirect:'/admin/cockpit'}, {path:'/login',component:LoginView},
  {path:'/admin/cockpit',component:FinanceCockpit}, {path:'/admin/treasury',component:TreasuryCenter},
  {path:'/admin/settlement',component:SettlementLedger}, {path:'/admin/budget',component:BudgetExpense},
  {path:'/admin/reports',component:FinanceCockpit}, {path:'/admin/governance',component:WorkflowGovernance}, {path:'/employee/expenses',component:ExpenseWorkbench}
]})
router.beforeEach(to=>{if(import.meta.env.VITE_DEMO_MODE!=='true'&&to.path!=='/login'&&!localStorage.getItem('zhuatech_bpm_token'))return'/login'})
export default router
