<script setup lang="ts">
	import axios from 'axios';
	import {
		ref,reactive
	} from 'vue';
	import { ElMessage } from 'element-plus';
	import { useRouter } from 'vue-router';
	axios.defaults.withCredentials = true;
	const router = useRouter();
	const signupForm = reactive({
	  ent: '',
	  pwd: '',
	  phone: '',
	  checkCode: '',
	});
	const verificationCodeApiUrl = './verificationCode.json'; 
	const rules = ref({
		ent:[{
			required:true,
			message:'企业名称不能为空!',
			trigger:'blur'
		}],
		pwd:[{
			required:true,
			message:'密码不能为空!',
			trigger:'blur'
		},{
			trigger:"blur",
			validator:(rule,value,callback)=>{
				let passworddreg=/(?=.*\d)(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{6,12}/;
				if(!passworddreg.test(value)){
					callback(
						new Error("密码必须由数字、字母、特殊符号组成，请输入6-12位之间的密码")
					);
				}else{
					callback();
				}
			}
		}],
		checkCode:[
			{
				required:true,
				message:'验证码不能为空!',
				trigger:'blur'
			},
			{max:4,message:"验证码为4位数字",trigger:'blur'}
		],
		phone:[{
			required:true,
			message:'电话不能为空!',
			trigger:'blur'
		}]
	});
	let show = ref(true);
	let count = ref(0);
	let timer = ref(null);
	// const $message = ref(null);
	let Result = ref('');
	const signup = () => {
	  if (signupForm.checkCode === Result.value) {
	    const fd = new FormData();
	    fd.append("ent", signupForm.ent);
	    fd.append("phone", signupForm.phone);
	    fd.append("pwd", signupForm.pwd);
		// 传回注册信息
	    axios.post('http://localhost:8090/renter/signup', fd)
	      .then((res) => {
	        if (res.data.isOK) {
				if(res.data.isSignup){
					ElMessage('提示:该企业已注册，请返回登陆');
				}
	          else{
				  ElMessage({
				      message: '恭喜注册成功!请返回登陆',
				      type: 'success',
				    });
			  }
	          router.replace('/'); // 使用 router 进行跳转
	        } else {
	          ElMessage.error('注册失败，请稍后再试或联系管理员');
	        }
	      })
	      .catch((error) => {
				ElMessage.error(error.message);
	      });
	  } else {
		  ElMessage.error("验证码输入错误，请检查");
	  }
	};
	const sendVerificationCode = () => {
		const TIME_COUNT = 60;
	    if (!timer.value) { // 使用 timer.value 替代 this.timer
	      count.value = TIME_COUNT; // 使用 count.value 替代 this.count
	      show.value = false; // 使用 show.value 替代 this.show
	      timer.value = setInterval(() => { // 使用 timer.value 替代 this.timer
	        if (count.value > 0 && count.value <= TIME_COUNT) {
	          count.value--; // 递减 count
	        } else {
	          show.value = true; // 重置 show
	          clearInterval(timer.value); // 清除定时器
	          timer.value = null; // 将 timer 设置为 null
	        }
	      }, 1000);
	      getCode(); // 调用 getCode 方法
	    }
	};
	
	const getCode = () => {
		axios.get('./verificationCode.json').then((res)=>{
			axios.get(verificationCodeApiUrl).then((res) => {
			    if (res.status === 200) {
			      setTimeout(() => {
			        const randomIndex = Math.floor(Math.random() * res.data.length);
			        const selectedCode = res.data[randomIndex].code;
			        // $message({
			        //   showClose: true,
			        //   message: '您的验证码是: ' + selectedCode,
			        //   type: 'success'
			        // });
					 ElMessage.success({
					          message: '您的验证码是: ' + selectedCode,
					          type: 'success'
					});
			        Result.value = selectedCode; // 使用 value 属性访问响应式引用的值
					show.value=true;
					clearInterval(timer.value);
					timer.value=null;
			      }, 3000);
			}
		})
	})
};

	// const signupForm = ref<HTMLFormElement | null>(null);
</script>



<template>
	<div class="signup-wrapper">
		<el-main>
			<p style="margin-bottom:20px;">注册</p>
			<el-form :model="signupForm" :rules="rules" label-width="80px">
				<el-form-item label="企业名称" prop="ent">
					<el-input prefix-icon="el-icon-user-solid" maxlength=10 v-model="signupForm.ent" clearable placeholder="请输入企业名称:"></el-input>
				</el-form-item>
				<el-form-item label="联系方式" prop="phone">
					<el-input prefix-icon="el-icon-phone" clearable v-model="signupForm.phone"
						placeholder="请输入本企业的官方联系方式:"></el-input>
				</el-form-item>
				<el-form-item label="密码" prop="pwd">
					<el-input prefix-icon="el-icon-edit" maxlength=12 show-password clearable v-model="signupForm.pwd"
						placeholder="请输入密码:"></el-input>
				</el-form-item>
				<el-form-item prop="checkCode" label="验证码:" class="checkCode" style="margin-top:40px;">
					<el-input style="width:180px;" clearable v-model="signupForm.checkCode"
						placeholder="请输入验证码"></el-input>
					<el-button @click.stop="sendVerificationCode" size="mini" type="primary" style="margin-left:15px;width: 120px;"
						v-if="show">发送验证码</el-button>
					<el-button size="mini" type="primary" style="margin-left:15px;width: 120px;" v-if="!show"
						disabled>{{count}}秒后重发</el-button>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" style="success;width:280px;height:auto;" @click.prevent="signup()"
						round>注册</el-button>
				</el-form-item>
			</el-form>
		</el-main>
	</div>
</template>
<style scoped>
	.signup-wrapper {
		background-image: url('/picture/背景图.jpg');
		width: 100%;
		height: 100%;
		background-size: 100% 100%;
	}

	.center {
		text-align: center;
	}

	.el-container {
		padding-top: 40px;
	}

	.el-input {
		width: 300px;
	}

	.el-main {
		width: 100%;
		height: 600px;
		margin-left: 30px;
		margin-top: 40px;
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
		/* 		background-color: #D3D3D3; */
	}
</style>