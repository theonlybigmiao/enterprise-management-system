<template>
<div>
<el-container style="height: 600px; border: 1px solid #eee">
	<el-aside width="200px" style="background-color: rgb(238, 241, 246)">
		<el-menu :default-openeds="['1', '2']" default-active="2-1">
			<el-submenu index="1">
			        <template slot="title"><i class="el-icon-message"></i>导航一</template>
			        <el-menu-item-group>
			          <template slot="title">个人信息管理</template>
			          <el-menu-item index="1-1" @click="toBasic">基本资料</el-menu-item>
					  <el-menu-item index="1-2" @click="toPersonnel">个人主页</el-menu-item>
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
					  <el-menu-item index="2-1">部门管理</el-menu-item>
			        </el-menu-item-group>
			</el-submenu>
		</el-menu>
	 </el-aside>
	 
	 <el-container>
	     <el-header style="text-align: right; font-size: 12px;margin-top: -80px;">
				<span style="margin-right:10px;">部门名称:</span>
				<el-input prefix-icon="el-icon-search" v-model="targetdepname" clearable style="width: 200px;margin-right: 40px;" placeholder="请输入部门名称"/>
				<span style="margin-right: 10px;">状态</span>
				  <el-select v-model="selectedstate" placeholder="部门状态" @change="handleStateChange" style="width:200px;margin-right: 30px;">
				    <el-option
				      v-for="item in states"
				      :key="item.value"
				      :label="item.label"
				      :value="item.value">
				    </el-option>
				  </el-select>
				 <el-button :icon="Search" size="small"  type="primary plain" @click="SearchByDepName" style="margin-right: 40px;width:70px;">搜索</el-button>
				<el-button :icon="Refresh" size="small"  plain type="primary plain" @click="reset" style="margin-right: 450px;width:70px;">重置</el-button>
	     </el-header>
		 <div style="display:flex;margin-top: 20px;padding-left: 40px;height: 40px;">
		 		<el-button :icon="Plus" type="primary"  plain size="small" style="margin-right: 40px;width:120px;"  @click="addNew">新增</el-button>
				<el-button-group style="margin-right: 80px">
				    <el-button color="#b3e19d" :icon="Expand" style="width:60px;" @click="IsFold(true)">展开</el-button>
				    <el-button color="#626aef"  :icon="Fold" style="width:60px;" @click="IsFold(false)">
				      折叠
				    </el-button>
				  </el-button-group>
		 		<!-- <el-button :icon="Expand" size="small" plain style="margin-right: 80px;width:90px;"  @click="IsFold(true)">展开</el-button>
				<el-button :icon="Fold" size="small" plain style="margin-right: 20px;width:90px;"  @click="IsFold(false)">折叠</el-button> -->
		 </div>
		 <el-container style="display: flex; flex-direction: column;align-items: center;">
			 <el-main style="height: 540px;margin-left: 50px;width:90%">
			 		       <el-table ref="DepTable" :data="departments" border style="width: 100%;margin-top: -30px;" height="400" row-key="date" :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
			 			   :expand-row-keys="expandedRowKeys" default-expand-all >
			 		         <el-table-column prop="name" label="部门名称" style="width: 15%;">
			 		         </el-table-column>
			 		         <el-table-column prop="priority"  label="排序" width="100">
			 		         </el-table-column>
			 		         <el-table-column prop="state" label="状态" width="120">
			 					 <template #default="scope">
			 					        <el-tag size="medium" :type="scope.row.state === '正常' ? 'primary' : 'warning'"
			           disable-transitions>{{ scope.row.state }}
			 							</el-tag>
			 					</template>
			 		         </el-table-column>
			 				 <el-table-column prop="date" label="创建时间" width="300">
			 				 </el-table-column>
			 					 
			 					 <el-table-column
			 					   style="align-items: center;"
			 					   fixed="right"
			 					   label="操作"
			 					   width="260">
			 					   <template #default="scope">
			 					     <el-button
			 					       type="text"
			 					       :icon="Edit"
			 					       size="small"
			 								   style="width: 50px;"
			 					       @click="handleEdit(scope.$index)">修改</el-button>
			 					     <el-button
			 					       type="text"
			 					       :icon="Plus"
			 					       size="small"
			 								   style="width: 50px;"
			 					       @click="handleAdd(scope.$index)">新增</el-button>
			 					     <el-button
			 					       v-if="scope.$index !== 0"
			 					       size="small"
			 					       type="text"
			 					       :icon="Delete"
			 								   style="width: 50px;"
			 					       @click="handleDelete(scope.$index)">删除</el-button>
			 					   </template>
			 					 </el-table-column>
			 		       </el-table>	
			 				
			  		     </el-main>
						 <!-- <el-pagination layout="prev, pager, next" :total="100" style="margin-top: 40px;"/> -->
		 </el-container>
<!-- 		 <div>{{options}}</div> -->
<!-- 					 <div>{{departments}}</div> -->
<!-- 					 <div>{{parentDep}}</div> -->
					<!-- <div>{{selectedstate}}</div> -->
<!-- 					<div>{{allDepartments}}</div>
					<div>{{searchdepartments}}</div> -->
				<!-- <el-dialog
				    :title="dialogTitle"
 				    :visible.sync="dialogVisible" 
				    width="40%"
				  > -->
				  <el-dialog
				      :title="dialogTitle"
				      v-model="dialogVisible" 
				      width="40%"
				    >
				    <el-form :model="CompanyForm" :rules="rules" style="margin-top: -20px;">
					  
					  <el-row>
					          <el-col :span="24">
					            <el-form-item label="上级部门" prop="parentDepartment" required>
					      
					              <el-select v-model="CompanyForm.parentDepartment" placeholder="请选择上级部门" style="width: 80%;" filterable allow-create  default-first-option>
					
					                <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"> 
					          		</el-option>
					              </el-select>
					            </el-form-item>
					          </el-col>
					    </el-row>
					  
					  <el-row>
					          <el-col :span="12">
					            <el-form-item label="部门名称" prop="departmentName" required>
					              <el-input autocomplete="off" v-model="CompanyForm.departmentName" placeholder="请输入部门名称" style="width: 70%;"></el-input>
					            </el-form-item>
					          </el-col>
					          <el-col :span="12">
					            <el-form-item label="显示排序" prop="displayOrder" required>
					              <el-input-number v-model="CompanyForm.displayOrder" style="width: 60%;" :min="1" :max="10"></el-input-number>
					            </el-form-item>
					          </el-col>
					    </el-row>
						
						<el-row>
						        <el-col :span="10">
						          <el-form-item label="负责人">
						            <el-input autocomplete="off" v-model="CompanyForm.chargePerson" placeholder="请输入负责人" style="width: 70%;"></el-input>
						          </el-form-item>
						        </el-col>
						        <el-col :span="14">
						          <el-form-item label="联系电话">
						            <el-input autocomplete="off" v-model="CompanyForm.contactPhone" placeholder="请输入联系电话" style="width: 70%;"></el-input>
						          </el-form-item>
						        </el-col>
						  </el-row>
						  
						  <el-row>
						          <el-col :span="14">
						            <el-form-item label="邮箱">
						              <el-input autocomplete="off" v-model="CompanyForm.email" placeholder="请输入邮箱" style="width: 70%;"></el-input>
						            </el-form-item>
						          </el-col>
						          <el-col :span="10">
						            <el-form-item label="部门状态">
						              <el-radio-group v-model="CompanyForm.departmentStatus">
						                <el-radio :label="1">正常</el-radio>
						                <el-radio :label="2">停用</el-radio>
						              </el-radio-group>
						            </el-form-item>
						          </el-col>
						    </el-row>
							<el-row  style="margin-top: 20px;margin-bottom: 15px;">
								<el-col :span="12">
									<el-button type="primary" style="width: 180px;margin-left: 60px;" @click="confirmAction">确定</el-button>
								</el-col>
								<el-col :span="12">
									<el-button @click="dialogVisible = false" style="width: 180px;">取消</el-button>
								</el-col>
							</el-row>
						</el-form>
<!-- 							<span slot="footer" class="dialog-footer">
									<el-button type="primary"  @click="confirmAction">确定</el-button>
								  <el-button @click="dialogVisible = false">取消</el-button>
							</span> -->
<!-- 							<div>{{Result}}</div> -->
<!-- 							<div>{{CompanyForm.parentDepartment}}</div> -->
				  </el-dialog>
	</el-container>
		 
			  
			  

</el-container>
  </div>
	
</template>

<script setup lang="ts">
import { ref, reactive, onMounted, watch,nextTick } from 'vue';
import { ElMessageBox,  ElInput, ElButton, ElSelect, ElOption, ElTable, ElTableColumn, ElDialog,ElMessage } from 'element-plus';
import { Search,Refresh,Fold,Plus,Edit,Delete,Expand } from '@element-plus/icons-vue'
import { useRouter } from 'vue-router';
import axios from 'axios';
axios.defaults.withCredentials = true;
const router = useRouter();
const departments = reactive([]);
const DepTable = ref(null);
const states=reactive([
				  {
					  value:'正常',
					  label:'正常'
				  },
				  {
					  value:'停用',
					  label:'停用'
				  }
			  ]);
// let options=reactive([{
// 			            value: 'DEaaaaa',
// 			            label: '测盟汇'
// 			          }, {
// 			            value: 'DEavcbd',
// 			            label: '深圳总公司'
// 			          }, {
// 			            value: 'DEeupec',
// 			            label: '长沙分公司'
// 			          }, {
// 			            value: 'DEhjklm',
// 			            label: '河南分公司'},
// 				]);
let options=reactive([]);
let selectedstate=ref('');
// let searchdepartments = reactive([]);
let expandedRowKeys = reactive([]);
let pid=ref('无');
let targetdepname=ref('');
let Result=ref([]);
// const rightdepts = reactive([]);
// let allDepartments=reactive([]);
let dialogVisible=ref(false);
let dialogTitle:String = '新增部门';
let currentRowIndex=ref(null);
const CompanyForm=reactive({
		parentDepartment: '测盟汇',
		departmentName: '',
		displayOrder: '',
		chargePerson: '',
		contactPhone: '',
		email: '',
		departmentStatus: 1,
		id:''
});
const rules=ref({
				 parentDepartment: [
				    { required: true, message: '请选择上级部门', trigger: 'change' }
				],
				departmentName: [
							{ required: true, message: '请输入部门名称', trigger: 'blur' }
				],
				displayOrder: [
				    { required: true, message: '请输入显示排序', trigger: 'blur' }
				]
			});
watch(targetdepname, (newVal) => {
  if (!newVal) {
    loadData();
  }
});
const loadData=()=>{
			  	axios.post('http://localhost:8090/department/list')
			  	  .then((res) => {
			  	    if(res.data.isOK){
						// 拷贝后端传来的数据到table的数据源
			  			Object.assign(departments, res.data.depts);
						pid.value=res.data.superior;
						// 这里是结，构建树形结构
						const parentdep=[];
						for(let i=0;i<departments.length;++i){
							if(departments[i].id==pid.value){
								parentdep.push(departments[i]);
								break;
							}
						}	
						// 找到以当前登录的用户所在部门信息
						const treedata = (departments: any[], pid: string) => {
						          const treeData2 = [];
								  // treeData2.push(departments.filter(item => item.id === pid));
						          const children = departments.filter(item => item.superior === pid); // 筛选子级
						          // 按优先级对子级进行排序
						          children.sort((a, b) => a.priority - b.priority);
						
						          children.forEach((item) => {
						            item.children = treedata(departments, item.id); // 递归获取子级的子级
						            treeData2.push(item);
						          });
						          return treeData2;
						        };
						parentdep[0].children=treedata(departments, pid.value);
						// 将当前登录的用户所在部门信息作为树根,下面是其所有子级及子级的子级
						options = getDepartmentsWithChildren(parentdep);
						departments.splice(0, departments.length, ...parentdep);//更新数据源
			  		}
			  		else{
						ElMessage.error(res.data.msg);
			  		}
			  	  })
			  	  .catch((error) => {
					  ElMessage.error(error.message);
			  	  });
			  };
function getDepartmentsWithChildren(treeData: any[]) {
	const result = [];
	
	  function traverse(nodes) {
	    nodes.forEach(node => {
	      if (node.children && node.children.length > 0) {
	        // 只有当node有子级时才添加到结果中
	        result.push({
	          value: node.name, // 假设每个部门对象都有一个唯一的id属性
	          label: node.name // 假设部门对象有一个name属性表示部门名称
	        });
	      }
	      // 如果当前节点还有子级，递归遍历子级
	      if (node.children) {
	        traverse(node.children);
	      }
	    });
	  }
	
	  traverse(treeData); // 从树的根节点开始遍历
	  return result;
}
const handleStateChange=(newValue: string)=>{
			      if (newValue === '正常') {
					  selectByState('正常');
			        // 执行正常状态下的逻辑
			      } else if (newValue === '停用') {
					  selectByState('停用');
			        // 执行停用状态下的逻辑
			      }
			      // ...其他条件分支
			};
const IsFold=(flag: any)=>{
	nextTick(() => {
	  handleArr(departments,flag);
	});
};
const handleArr=(arr: any[],flag: any)=>{
	arr.forEach((i)=>{
		DepTable.value.toggleRowExpansion(i,flag);
		if(i.children){
			handleArr(i.children,flag);
		}
	})
};
const reset=()=>{
				loadData();
			};
const selectByState=(state: string)=>{
				// 这里是解，即将树状数组扁平化
				const allDepartments = [];	
				// 递归函数，用于扁平化树状数据
				const flattenDepartments = (departments: any[]) => {
				  departments.forEach(dept => {
					const deptCopy = { ...dept, children: undefined };//除去children元素
				    allDepartments.push(deptCopy); // 将当前部门的名称添加到数组
				    if (dept.children && dept.children.length > 0) {
				      flattenDepartments(dept.children); // 递归调用，处理子部门
				    }
				  });
				};		
				// 开始递归遍历
				flattenDepartments(departments);
				const rightdepts = [];	
				for(let i=0;i<allDepartments.length;++i){
					if(allDepartments[i].state==state){
						rightdepts.push(allDepartments[i]);
					}
				}
				// Object.assign(departments, rightdepts);
				departments.splice(0, departments.length, ...rightdepts);
			};
const addNew=()=>{
				dialogTitle = '新增部门';
				dialogVisible.value = true;
				// confirmAction();
			};
const handleEdit=(index: string | number) =>{
				// console.log('handleEdit');
				// console.log(dialogVisible);
				const allDepartments = [];
							
				// 递归函数，用于扁平化树状数据
				const flattenDepartments = (departments: any[]) => {
				  departments.forEach(dept => {
					const deptCopy = { ...dept, children: undefined };
				    allDepartments.push(deptCopy); // 将当前部门的名称添加到数组
				    if (dept.children && dept.children.length > 0) {
				      flattenDepartments(dept.children); // 递归调用，处理子部门
				    }
				  });
				};			
				flattenDepartments(departments);
				currentRowIndex = index;
				console.log(index);
				// 它是树状的,直接拿拿不到，这里要改
				// Object.assign(CompanyForm,allDepartments[index]);
				// Object.assign(Result,allDepartments[index]);
				CompanyForm.chargePerson=allDepartments[index].head;
				CompanyForm.contactPhone=allDepartments[index].phone;
				CompanyForm.departmentName=allDepartments[index].name;
				CompanyForm.displayOrder=allDepartments[index].priority;
				CompanyForm.departmentStatus=allDepartments[index].state=="正常"?1:2;
				CompanyForm.email=allDepartments[index].email;
				CompanyForm.parentDepartment=allDepartments[index].superior;
				CompanyForm.id=allDepartments[index].id;
				dialogTitle = '修改部门';
				dialogVisible.value = true;
			};
const handleAdd=(index: any)=>{
				currentRowIndex = index;
				dialogTitle = '新增部门';
			    dialogVisible.value = true;
				// confirmAction();
			};
const handleDelete=(index: number)=> {
				const allDepartments = [];
							
				// 递归函数，用于扁平化树状数据
				const flattenDepartments = (departments: any[]) => {
				  departments.forEach(dept => {
				    allDepartments.push(dept); // 将当前部门的名称添加到数组
				    if (dept.children && dept.children.length > 0) {
				      flattenDepartments(dept.children); // 递归调用，处理子部门
				    }
				  });
				};
							
				flattenDepartments(departments);
				// this.Result=this.departments[index].name;
				ElMessageBox.confirm('是否确认删除名为' + allDepartments[index].name + '的数据项？', '提示', {
				    confirmButtonText: '确定',
				    cancelButtonText: '取消',
				    type: 'warning',
				    center: true
				  }).then(() => {
				    const fd = new FormData();
				    fd.append("targetid", allDepartments[index].id);
				    axios.post('http://localhost:8090/department/deleteDept', fd)
				      .then((res) => {
				        if (res.data.isOK) {
				          ElMessage({
				              message: '删除成功!.',
				              type: 'success',
				            })
				          departments.splice(index, 1);
				        } else {
				          ElMessage.error(res.data.msg);
				        }
				      })
				      .catch((error) => {
							ElMessage.error(error.message);
				      });
				
				  }).catch(() => {
				      ElMessage('已取消删除')
				  });
			};
const confirmAction=() =>{
			      if (dialogTitle === '修改部门') {
			        modifyDept();
					CompanyForm.chargePerson='';
					CompanyForm.contactPhone='';
					CompanyForm.departmentName='';
					CompanyForm.displayOrder='';
					CompanyForm.departmentStatus=1;
					CompanyForm.email='';
					CompanyForm.parentDepartment='';
					CompanyForm.id='';
					loadData();
			      } else {
					addDept();
					loadData();
			      }
			};
			
const modifyDept=()=>{
				const fd = new FormData();
				fd.append("superior", CompanyForm.parentDepartment);
				fd.append("name", CompanyForm.departmentName);
				fd.append("priority", CompanyForm.displayOrder);
				fd.append("head", CompanyForm.chargePerson);
				fd.append("phone", CompanyForm.contactPhone);
				fd.append("status", CompanyForm.departmentStatus==1?"正常":"停用");
				fd.append("email", CompanyForm.email);
				fd.append("id", CompanyForm.id);
				axios.post('http://localhost:8090/department/updateDept',fd)
				  .then((res) => {
				    if(res.data.isOK){
						ElMessage({
						    message: '修改成功!',
						    type: 'success',
						  });
					}
					else{
						ElMessage.error('修改失败!');
					}
				  })
				  .catch((error) => {
					ElMessage.error(error.message);
				  });
				dialogVisible.value = false;
			};
			
const SearchByDepName=() =>{
			    // 用于存储搜索结果的数组
			    let searchdepartments = [];
			
			    // 用于存储所有部门名称的数组
			    let allDepartments = [];
			    // 递归函数，用于扁平化树状数据
				const flattenDepartments = (departments: any[]) => {
				  departments.forEach(dept => {
					const deptCopy = { ...dept, children: undefined };
				    allDepartments.push(deptCopy); // 将当前部门的名称添加到数组
				    if (dept.children && dept.children.length > 0) {
				      flattenDepartments(dept.children); // 递归调用，处理子部门
				    }
				  });
				};
			    // 开始递归遍历
			    flattenDepartments(departments);
				searchdepartments = allDepartments.filter(dept =>
				  dept.name.includes(targetdepname.value)
				);
			    // 执行模糊搜索
				// Object.assign(departments, searchdepartments);
				// this.searchdepartments=[];
			    // 根据搜索结果判断并提示用户
			    if (searchdepartments.length > 0) {
					departments.splice(0, departments.length, ...searchdepartments);
					ElMessage({
					    message: '查询成功!',
					    type: 'success',
					  })
			    } else {
					ElMessage('查询失败，什么都没找到');
			    }
				// searchdepartments=[];
			  };
			
const addDept=()=>{
				const  fd = new FormData();
				fd.append("superior", CompanyForm.parentDepartment);
				fd.append("name", CompanyForm.departmentName);
				fd.append("priority", CompanyForm.displayOrder);
				fd.append("head", CompanyForm.chargePerson);
				fd.append("phone", CompanyForm.contactPhone);
				fd.append("status", CompanyForm.departmentStatus==1?"正常":"停用");
				fd.append("email", CompanyForm.email);
				axios.post('http://localhost:8090/department/addDept',fd)
				  .then((res) => {
				    if(res.data.isOK){
						ElMessage({
						    message: res.data.msg,
						    type: 'success',
						  })
					}
					else{
						ElMessage.error(res.data.msg);
					}
				  })
				  .catch((error) => {
					  ElMessage.error(error.message);
				  });
				dialogVisible.value = false;
				// 以弹窗方式展示新增和修改界面
			};
			
			
const toBasic=()=>{
				router.replace('/basic');
	};
const toPersonnel=()=>{
		router.replace('/mine');		
	};
onMounted(() => {
	  loadData();
	});

</script>
<style>
	.center{
		text-align: center;
	}
	.el-header {
	    background-color: #B3C0D1;
	    color: #333;
	    line-height: 60px;
	  }
	  
	  .el-aside {
	    color: #333;
	  }
</style>