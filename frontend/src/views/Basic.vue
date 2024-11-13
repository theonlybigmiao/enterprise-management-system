<template>
	<div>
	<el-container>
		<el-aside width="200px" style="background-color: rgb(238, 241, 246)">
			<el-menu :default-openeds="['1', '2']" default-active="1-1">
				<el-submenu index="1">
				        <template slot="title"><i class="el-icon-message"></i>导航一</template>
				        <el-menu-item-group>
				          <template slot="title">个人信息管理</template>
				          <el-menu-item index="1-1">基本资料</el-menu-item>
						  <el-menu-item index="1-2" @click="toMine">个人主页</el-menu-item>
				        </el-menu-item-group>
				        <el-menu-item-group title="用户管理">
				          <el-menu-item index="1-3">租户管理</el-menu-item>
						  <el-menu-item index="1-4">其他用户管理</el-menu-item>
				        </el-menu-item-group>
				</el-submenu>
				<el-submenu index="2">
				        <template slot="title"><i class="el-icon-message"></i>导航二</template>
				        <el-menu-item-group>
				          <template slot="title">部门管理</template>
						  <el-menu-item index="2-1" @click="toDept">部门管理</el-menu-item>
				        </el-menu-item-group>
				</el-submenu>
			</el-menu>
		 </el-aside>
		<el-main style="margin-left:60px;">
			<p style="margin-left: -1000px;margin-top: auto;">基本资料</p>
			<hr style="width: 100%;margin-top: 0px;">
			<el-tabs v-model="activeName" style="width: 100%;">
				<el-tab-pane label="基本资料" name="first"></el-tab-pane>
				<el-tab-pane label="修改密码" name="second"></el-tab-pane>
			</el-tabs>
			<el-container v-if="activeName === 'first'">
			<el-form :model="curuser" :rules="rules" label-width="80px">
				<el-form-item label="用户昵称" prop="usm" required style="margin-top: 0px;">
					<el-input autocomplete="off" v-model="curuser.usm" placeholder="请输入用户昵称:"></el-input>
				</el-form-item>
				<el-form-item label="部门" prop="dept" required>
					<el-input autocomplete="off" v-model="curuser.dept" placeholder="请输入用户部门:"></el-input>
				</el-form-item>
				<el-form-item label="手机号码" prop="phone" required>
					<el-input autocomplete="off" v-model="curuser.phone" placeholder="请输入手机号码:"></el-input>
				</el-form-item>
				<el-form-item label="邮箱" prop="email" required>
					<el-input autocomplete="off" v-model="curuser.email" placeholder="请输入邮箱:"></el-input>
				</el-form-item>
				<el-form-item label="性别" prop="gender">
					<el-radio-group v-model="curuser.gender">
					  <el-radio label="男"></el-radio>
					  <el-radio label="女"></el-radio>
					</el-radio-group>
				</el-form-item>
				<div style="margin-top:40px;">
					<el-button type="primary" style="width:100px;margin-left:100px;" @click="modifyAll">保存</el-button>
					<el-button  type="danger" style="width:100px;">关闭</el-button>
				</div>
			</el-form>
			</el-container>
			<el-container v-if="activeName === 'second'">
				<el-form :model="modifypwd" :rules="rules" label-width="80px">
					<el-form-item label="原密码" prop="oldpwd" required style="margin-top: 0px;">
						<el-input autocomplete="off" v-model="modifypwd.oldpwd" placeholder="请输入原密码:"></el-input>
					</el-form-item>
					<el-form-item label="新密码" prop="newpwd" maxlength=12 show-password clearable required>
						<el-input autocomplete="off" v-model="modifypwd.newpwd" placeholder="请输入新密码:"></el-input>
					</el-form-item>
					<el-form-item label="确认密码" maxlength=12 show-password clearable prop="confirmpwd" required>
						<el-input autocomplete="off" v-model="modifypwd.confirmpwd" placeholder="请确认新密码:"></el-input>
					</el-form-item>
					<div style="margin-top:40px;">
						<el-button type="primary" style="width:100px;margin-left:100px;" @click="modifyPassWord">修改</el-button>
						<el-button  type="danger" style="width:100px;">取消</el-button>
					</div>
				</el-form>
			</el-container>
		</el-main>
	</el-container>
	  </div>
</template>

<script setup lang="ts">
import axios from 'axios';
	import {
		ref,reactive,onMounted
	} from 'vue';
	import { ElMessage,ElSubMenu } from 'element-plus';
	import { useRouter } from 'vue-router';
	axios.defaults.withCredentials = true;
	const router = useRouter();
	const curuser = reactive({
		usm:'',
		phone:'',
		email:'',
		gender:'',
		dept:''
	});
	let modifypwd = reactive({
		oldpwd:'',
		newpwd:'',
		confirmpwd:''
	});
	let activeName = ref('first');
	const rules = ref({
		newpwd:[{
			required:true,
			message:'密码不能为空!',
			trigger:'blur'
		},{
			trigger:"blur",
			validator:(_rule: any,value: string,callback: (arg0: Error) => void)=>{
				let passworddreg=/(?=.*\d)(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{6,12}/;
				if(!passworddreg.test(value)){
					callback(
						new Error("密码必须由数字、字母、特殊符号组成，请输入6-12位之间的密码")
					);
				}
			}
		}],
		phone:[
			{trigger:"blur",
			validator:(_rule: any,value: string,callback: (arg0: Error) => void)=>{
				let phonedreg= /^(0|86|17951)?(13[0-9]|15[0123456789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
				if(!phonedreg.test(value)){
					callback(
						new Error("请输入有效的联系方式")
					);
				}
			}
		}]
	});

	// const loadData=()=>{
	// 	axios.post('http://localhost:8090/ceuser/show')
	// 	  .then((res) => {
	// 	    if(res.data.isOK){
	// 			curuser.value=res.data.myuser;
	// 		}
	// 		else{
	// 			alert(res.data.msg);
	// 		}
	// 	  })
	// 	  .catch((error) => {
	// 		  alert(error.msg);
	// 	  });
	// }; 
	
	const loadData = async () => {
	  try {
	    const response = await axios.post('http://localhost:8090/ceuser/show');
	    if (response.data.isOK) {
			console.log('response的isOK是true');
	      // 确保 curuser 响应式对象包含所有需要的属性
		  Object.assign(curuser, response.data.myuser);
		 //  Object.keys(curuser).forEach(key => {
		 //    if (response.data.myuser.hasOwnProperty(key)) {
		 //      curuser[key] = response.data.myuser[key];
		 //    }
			// });
		 //  curuser.usm = response.data.myuser.usm;
		 //  curuser.phone = response.data.myuser.phone;
		 // curuser.email = response.data.myuser.email;
		 // curuser.gender = response.data.myuser.gender;
		 // curuser.dept = response.data.myuser.dept;
	      // curuser=response.data.myuser;
	    } else {
		  console.log('response的isOK不是true');
	      ElMessage.error(response.data.msg);
	    }
	  } catch (error) {
	    ElMessage.error(error.message);
		console.error('Error message:', error.message);
	  }
	};
	
	// const loadData = async () => {
	//   try {
	//     const response = await axios.post('http://localhost:8090/ceuser/show');
	//     if (response.data.isOK) {
	//       curuser.value = response.data.myuser;
	//     } else {
	//       ElMessage.error(response.data.msg);
	//     }
	//   } catch (error) {
	//     // 使用 ElMessage 替代 alert
	//     ElMessage.error(error.response.data.msg || 'Unknown error');
	//   }
	// };
	const modifyPassWord=()=>{
		if(modifypwd.confirmpwd!=modifypwd.newpwd){
			alert("确认密码时的输入与新密码不一致，请检查输入!");
		}
		else{
			const fd = new FormData();
			fd.append("newpwd", modifypwd.newpwd);
			axios.post('http://localhost:8090/ceuser/updateCeUserPassWord',fd)
			  .then((res) => {
			    if(res.data.isOK){
					ElMessage({
					    message: '修改密码成功!',
					    type: 'success',
					  });
					// this.Result=res.data.msg;
					// 这里来个弹窗什么的
				}
				else{
					ElMessage.error('修改密码失败');
				}
			  })
			  .catch((error) => {
				  ElMessage.error(error.message);
			  });	
			  
		}
	};
	const modifyAll=()=>{
		const fd = new FormData();
		fd.append("usm", curuser.usm);
		fd.append("phone", curuser.phone);
		fd.append("email", curuser.email);
		fd.append("gender", curuser.gender);
		fd.append("dept", curuser.dept);
		axios.post('http://localhost:8090/ceuser/updateCeUserAccount',fd)
		  .then((res) => {
		    if(res.data.isOK){
				ElMessage({
				    message: '修改个人信息成功!',
				    type: 'success',
				  });
				// this.Result=res.data.msg;
				// 这里来个弹窗什么的
			}
			else{
				ElMessage.error('修改失败!');
			}
		  })
		  .catch((error) => {
			  ElMessage.error(error.message);
		  });
		  // loadData();
	};
	const toMine=()=>{
		router.push('/mine');
	};
	const toDept=()=>{
		router.push('/department');
	};
	onMounted(() => {
	  loadData();
	});
</script>

<style>
	.center{
		text-align: center;
	}
	.el-container{
		padding-top: 40px;
	}
	 .el-main {
		width:50%;
		height:600px;
		display: flex;
		margin-left: 100px; 
		margin-right: 100px;
		flex-direction: column;
		align-items: center;
		justify-content: center;
		border: 1px solid #b8b8b8;
		box-shadow: 0 8px 16px rgba(0, 0, 0, 0.5);
	}
	.el-button{
		width:280px;
		height:auto;
	}
	.el-input{
		width:400px;
	}
	.el-form-item{
		margin-top:30px;
	}
</style>