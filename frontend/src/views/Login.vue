<template>
	<el-container class="login-wrapper">
		<el-main>
			<p style="text-align: center;margin-top: -60px;">企业管理系统 DEMO</p>
			 <el-row style="width:40%;align-items: center;margin-top: 20px;">
				 <el-col :span="12">
					 <el-radio v-model="radio" value="超级管理员" style="margin-left:160px;">超级管理员</el-radio>
				 </el-col>
				 <el-col :span="12">
					 <el-radio v-model="radio" value="企业管理员" style="margin-left:40px;">企业管理员</el-radio>
				 </el-col>
			 </el-row>
			 
			<el-form :model="userForm" :rules="rules" style="margin-top: 20px;">
				<el-form-item label="账号" prop="ones">
					<el-input  prefix-icon="el-icon-user-solid" maxlength=15 v-model="userForm.ones" clearable placeholder="请输入您的账号:"></el-input>
				</el-form-item>
				<el-form-item label="密码" prop="password">
					<el-input prefix-icon="el-icon-lock" v-model="userForm.password" maxlength=12 show-password clearable placeholder="请输入您的密码:"></el-input>
				</el-form-item>
				<el-form-item>
					  <el-checkbox v-model="checked">记住密码</el-checkbox>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" style="margin-top:30px;margin-left: 100px;" @click.prevent="login" round>登录</el-button>
				</el-form-item>
				<el-form-item style="text-align:center;justify-content:center;">
					<!-- <el-button type="primary" style="margin-top:30px;background-color:#90EE90;" @click.prevent="tosignup()" round>去注册</el-button> -->
					<a @click="toSignup" style="color: #809080;margin-left: 180px;">没有账号？立即注册</a>
				</el-form-item>
			</el-form>
		</el-main>
	</el-container>
</template>
<script setup lang="ts">
import axios from 'axios';
import {ref,reactive,onMounted} from 'vue';
	import { useRouter } from 'vue-router';
	import { ElCheckbox, ElMessage } from 'element-plus';
	axios.defaults.withCredentials = true;
	const router = useRouter();
	interface UserForm {
	  ones: string;
	  password: string;
	}

	const userForm = reactive<UserForm>({
	  // ones: '深圳总公司',
	  // password: 'aym1314!',
	  ones: '',
	  password: '',
	});
	
	
	// const message = createMessage();
    let Result = ref('');
    let radio = ref('企业管理员');
    let checked = ref(true);
    const rules = ref({
      ones: [{
        required: true,
        message: '账号不能为空!',
        trigger: 'blur',
      }],
      password: [{
        required: true,
        message: '密码不能为空!',
        trigger: 'blur',
      }],
    });
    const userFormRef = ref(null);
	
	// 用户登录的代码
	const login = () => {
      const fd = new FormData();
      fd.append('ones', userForm.ones);
      fd.append('password', userForm.password);
      fd.append('flag', radio.value);
      axios.post('http://localhost:8090/ceuser/login', fd)
	  // 访问路由
          .then((res) => {
            if (res.data.isOK) {
				ElMessage({
				    message: '登录成功!',
				    type: 'success',
				  });
				if(checked.value==true){
					// 设置记住密码的cookie
					 const expireDate = new Date();
					  expireDate.setHours(expireDate.getHours() + 1);
					  document.cookie = `password=${encodeURIComponent(userForm.password)};expires=${expireDate.toUTCString()};path=/`;
				}
				localStorage.setItem('Authorization', res.data.token);
				router.push('/basic');
				// 路由跳转
            } else {
				ElMessage.error('登录失败！请检查输入');
            }
          })
          .catch((error) => {
				ElMessage.error(error.message);
          });
	};
	function toSignup(){
		router.push('/signup');
	}
	onMounted(() => {
	  const cookies = document.cookie.split(';');
	        const passwordCookie = cookies.find((cookie) => cookie.trim().startsWith('password='));
	        if (passwordCookie) {
	          const password = passwordCookie.split('=')[1];
	          userForm.password = decodeURIComponent(password);
	          // this.checked.value = true; // 密码存在，说明用户选择了记住密码
	        }
	});
</script>

<style scoped>
.login-wrapper {
		background-image: url('/picture/背景图.jpg');
		width: 100%;
		height: 100%;
		background-size: 100% 100%;
	}
.center{
 		text-align: center;
 	}
	.el-container{
		padding-top: 60px;
	}
	 .el-main {
		width:80%;
		height:600px;
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
/* 		background-color: #D3D3D3; */
	}
	.el-button{
		width:280px;
		height:auto;
	}
</style>
<!-- aym1314! -->
<!-- aym1312! -->