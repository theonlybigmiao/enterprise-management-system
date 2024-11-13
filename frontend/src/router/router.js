import { createRouter, createWebHistory} from "vue-router"
import {ElMessage} from 'element-plus'
import Login from '../views/Login.vue'
import Signup from '../views/Signup.vue'
import Basic from '../views/Basic.vue'
import Mine from '../views/Mine.vue'
import Department from '../views/Department.vue'
// Vue.use(router)
const routes = [
	{
        path: '/',
        name: 'login',
        component: Login
    },
    {
		path: "/signup",
		name: 'signup',
		component: Signup
	},
	{
		path: "/mine",
		name: 'mine',
		component: Mine
	},
	{
		path: "/basic",
		name: 'basic',
		component: Basic
	},
	{
		path: "/department",
		name: 'department',
		component: Department
	}
];
const history = createWebHistory();
const routers = createRouter({
	history,
	routes:routes
});
routers.beforeEach((to,from,next)=>{
	if(to.path=='/'||to.path=='/signup'){
		next();
	}else{
		const token=localStorage.getItem('Authorization');
		if(token===null||token===''){
			ElMessage.error('您还未登录，请先登录！');
			next('/');
		}else{
			next();
		}
	}
})
export default routers;