<template>
<div class="mine-wrapper">

<el-container style="height: 800px;">
	<el-aside width="200px" style="background-color: rgb(238, 241, 246)">
		<el-menu :default-openeds="['1', '2']" default-active="1-2">
			<el-submenu index="1">
			        <template slot="title"><i class="el-icon-message"></i>导航一</template>
			        <el-menu-item-group>
			          <template slot="title">个人信息管理</template>
			          <el-menu-item index="1-1" @click="toBasic">基本资料</el-menu-item>
					  <el-menu-item index="1-2">个人主页</el-menu-item>
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
	 <el-container style="display: flex;flex-direction: column;height:180px;margin-top: -60px;">
	 	 <el-container style="border-bottom: 1px solid #b8b8b8;">
	 		 <div class="demo-basic--circle" style="margin-top:-40px;width: 100%;">
	 		    <div class="block">
					<el-upload
					    style="margin-left:600px;margin-bottom: 20px;"
					    action="http://localhost:8090/ceuser/uploadAvatar"
					    :show-file-list="false"
					    :on-success="handleAvatarSuccess"
					    :before-upload="beforeAvatarUpload"
					  >
					    <el-avatar v-if="myuser.url" :src="myuser.url" :size="140" />
						<el-icon v-else :size="140"><Plus /></el-icon>
					  </el-upload>
<!-- 	 				<el-avatar :size="140" :src="myuser.url" style="margin-left:600px;margin-bottom: 20px;"></el-avatar> -->
					
	 			</div>
	 		</div>
	 	</el-container>
<!-- 		<div>{{myuser.url}}</div> -->
	 	<el-container>
	 		<el-main style="height:560px;">
	 				<el-row style="width:100%;height:40px;border-bottom: 1px solid #b8b8b8;margin-top: -150px;">
	 					<el-col :span="12" style="text-align: left;">
	 					    <div class="label">
	 							<i style="margin-left:200px;" class="el-icon-user-solid"></i>
	 							<span style="margin-left:3px;">用户名称</span>
	 					    </div>
	 					 </el-col>
	 					 <el-col :span="12" style="text-align: right;">
	 					 	<span style="margin-right:200px;">{{myuser.usm}}</span>
	 					  </el-col>
	 				</el-row>
	 				<el-row style="width:100%;height:40px;margin-top:15px;border-bottom: 1px solid #b8b8b8;">
	 					<el-col :span="12" style="text-align: left;">
	 					    <div class="label">
	 							<i style="margin-left:200px;" class="el-icon-phone"></i>
	 							<span style="margin-left:3px;">手机号码</span>
	 					    </div>
	 					 </el-col>
	 					 <el-col :span="12" style="text-align: right;">
	 					 	<span style="margin-right:200px;">{{myuser.phone}}</span>
	 					  </el-col>
	 				</el-row>
	 				<el-row style="width:100%;height:40px;margin-top:15px;border-bottom: 1px solid #b8b8b8;">
	 					<el-col :span="12" style="text-align: left;">
	 					    <div class="label">
	 							<i style="margin-left:200px;" class="el-icon-message"></i>
	 							<span style="margin-left:3px;">用户邮箱</span>
	 					    </div>
	 					 </el-col>
	 					 <el-col :span="12" style="text-align: right;">
	 					 	<span style="margin-right:200px;">{{myuser.email}}</span>
	 					  </el-col>
	 				</el-row>
	 				<el-row style="width:100%;height:40px;margin-top:15px;border-bottom: 1px solid #b8b8b8;">
	 					<el-col :span="12" style="text-align: left;">
	 					    <div class="label">
	 							<i style="margin-left:200px;" class="el-icon-menu"></i>
	 							<span style="margin-left:3px;">所属部门</span>
	 					    </div>
	 					 </el-col>
	 					 <el-col :span="12" style="text-align: right;">
	 					 	<span style="margin-right:200px;">{{myuser.dept}}</span>
	 					  </el-col>
	 				</el-row>
	 				<el-row style="width:100%;height:40px;margin-top:15px;border-bottom: 1px solid #b8b8b8;">
	 					<el-col :span="12" style="text-align: left;">
	 					    <div class="label">
	 							<i style="margin-left:200px;" class="el-icon-question"></i>
	 							<span style="margin-left:3px;">所属角色</span>
	 					    </div>
	 					 </el-col>
	 					 <el-col :span="12" style="text-align: right;">
	 					 	<span style="margin-right:200px;">{{myuser.role}}</span>
	 					  </el-col>
	 				</el-row>
	 				<el-row style="width:100%;height:40px;margin-top:15px;border-bottom: 1px solid #b8b8b8;">
	 					<el-col :span="12" style="text-align: left;">
	 					    <div class="label">
	 							<i style="margin-left:200px;" class="el-icon-date"></i>
	 							<span style="margin-left:3px;">创建日期</span>
	 					    </div>
	 					 </el-col>
	 					 <el-col :span="12" style="text-align: right;">
	 					 	<span style="margin-right:200px;">{{myuser.date}}</span>
	 					  </el-col>
	 				</el-row>
					<el-button type="primary" size="medium" style="margin-top:60px;margin-left:30px;margin-bottom: -60px;" plain @click="logout">安全退出</el-button>
	 		</el-main>
	 	</el-container>
	 </el-container> 
</el-container>
  </div>

</template>

<script setup lang="ts">
	import axios from 'axios';
	import {
		reactive,onMounted,ref
	} from 'vue';
	import { ElMessage, genFileId, UploadInstance, UploadRawFile } from 'element-plus';
	import type { UploadProps } from 'element-plus';
	// import { ElMessage } from 'element-plus';
	import { useRouter } from 'vue-router';
	axios.defaults.withCredentials = true;
	const router = useRouter();
	
const myuser = reactive({
  url:'picture/2.jpeg',
  usm: '',
  phone:'',
  email:'',
  dept:'',
  role:'',
  date:''
});

// const upload = ref<UploadInstance>();

const loadPersonnel = async () => {
  try {
    const response = await axios.post('http://localhost:8090/ceuser/show');
    if (response.data.isOK) {
      Object.assign(myuser, response.data.myuser);
    } else {
      alert(response.data.msg);
    }
  } catch (error) {
	alert(error.msg);
  }
};

const logout = () => {
  var result=confirm("确定退出?");
  if(result){
  	axios.post('http://localhost:8090/ceuser/logout')
  	  .then((res) => {
  		if(res.data.isOK){
  			router.replace('/');
			ElMessage({
			    message: '已成功退出',
			    type: 'success',
			  });
  		}
  		else{
  			ElMessage.error("退出失败");
  		}
  	})
  	.catch((error) => {
  		ElMessage.error(error.message);
  	});	  		
  }
};

const toBasic = () => {
  router.push('/basic');
};

const toDept = () => {
  router.push('/department');
};

onMounted(() => {
  loadPersonnel();
});

// function uploadFn(){
	
// };
const handleAvatarSuccess: UploadProps['onSuccess'] = (
  _response,
  uploadFile
) => {
	console.log('上传成功!');
  myuser.url = URL.createObjectURL(uploadFile.raw!);
}


// const handleExceed: UploadProps['onExceed'] = (files) => {
//   upload.value!.clearFiles()
//   const file = files[0] as UploadRawFile
//   file.uid = genFileId()
//   upload.value!.handleStart(file)
// }


const beforeAvatarUpload = (rawFile) => {
  // 定义一个包含允许上传的图片格式的数组
  const allowedTypes = ['image/jpeg', 'image/jpg', 'image/png'];
  
  // 检查文件类型是否在允许的类型数组中
  if (!allowedTypes.includes(rawFile.type)) {
    ElMessage.error('Avatar picture must be JPG, JPEG, or PNG format!');
    return false;
  }
  
  // 检查文件大小是否超过 2MB
  if (rawFile.size / 1024 / 1024 > 5) {
    ElMessage.error('Avatar picture size cannot exceed 5MB!');
    return false;
  }
  
  // 如果文件类型和大小都符合要求，返回 true 允许上传
  return true;
}
</script>

<style>
	.mine-wrapper {
		background-image: url('/picture/背景图.jpg');
		width: 100%;
		height: 100%;
		background-size: 100% 100%;
	}
</style>