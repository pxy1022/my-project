<template>
  <div>
    <el-breadcrumb separator-icon="ArrowRight" style="margin: 16px">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item  >缺勤管理</el-breadcrumb-item>
      <el-breadcrumb-item  >上报缺勤</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card style="margin: 15px; min-height: calc(100vh - 111px)">
      <div>
        <!--    功能区-->
        <div style="margin: 10px 0">
          <!--    搜索区-->
          <div style="margin: 10px 0">
            <el-input v-model="search" clearable placeholder="请输入学号" prefix-icon="Search" style="width: 20%"/>
            <el-button icon="Search" style="margin-left: 5px" type="primary" @click="load"></el-button>
            <el-button icon="refresh-left" style="margin-left: 10px" type="default" @click="reset"></el-button>
            <div style="float: right">
              <el-tooltip content="添加" placement="top">
                <el-button icon="plus" style="width: 50px" type="primary" @click="add"></el-button>
              </el-tooltip>
            </div>
          </div>
        </div>
        <!--    表格-->
        <el-table v-loading="loading" :data="tableData" border max-height="705" style="width: 100%">
          <el-table-column label="#" type="index"/>
          <el-table-column :show-overflow-tooltip="true" label="学号" prop="username"/>
          <el-table-column :show-overflow-tooltip="true" label="姓名" prop="name"/>
          <el-table-column :show-overflow-tooltip="true" label="宿舍楼号" prop="louchengId"/>
          <el-table-column :show-overflow-tooltip="true" label="楼层号" prop="louId"/>
          <el-table-column :show-overflow-tooltip="true" label="房间号" prop="fangjianId"/>
          <el-table-column :show-overflow-tooltip="true" label="床位" prop="chuangwei"/>
          <el-table-column :show-overflow-tooltip="true" label="上报人" prop="sid"/>
          <el-table-column label="缺勤时间" prop="releaseTime" sortable width="400px"/>
          <!--      操作栏-->
          <el-table-column label="操作" width="190px">
            <template #default="scope">
              <el-button icon="more-filled" type="default" @click="showDetail(scope.row)"></el-button>
              <el-button icon="Edit" type="primary" @click="handleEdit(scope.row)"></el-button>
              <el-popconfirm title="确认删除？" @confirm="handleDelete(scope.row.id)">
                <template #reference>
                  <el-button icon="Delete" type="danger"></el-button>
                </template>
              </el-popconfirm>
            </template>
          </el-table-column>
        </el-table>
        <!--分页-->
        <div style="margin: 10px 0">
          <el-pagination
              v-model:currentPage="currentPage"
              :page-size="pageSize"
              :page-sizes="[10, 20]"
              :total="total"
              layout="total, sizes, prev, pager, next, jumper"
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
          >
          </el-pagination>
        </div>
        <!--      弹窗-->
        <div>
          <el-dialog v-model="dialogVisible" title="操作"  width="50%" @close="cancel">
            <el-form ref="form" :model="form" :rules="rules" label-width="120px">
    


  <el-form-item label="学号" prop="username">
                
    <el-select  v-model="form.username" placeholder="请选择学号"  @change="username">
    <el-option
      v-for="item in xuehao"
      :key="item.username"
      :label="item.label"
      :value="item.username">
    </el-option>
  </el-select>
              </el-form-item>

              <el-form-item label="姓名" prop="name">
      
                <el-input
  placeholder=""
  v-model="name"
  :disabled="true">
</el-input>
              </el-form-item>
              <el-form-item label="宿舍楼号" prop="louchengId">
      
      <el-input
placeholder=""
v-model="louchengId"
:disabled="true">
</el-input>
    </el-form-item>

    <el-form-item label="楼层号" prop="louId">
      
      <el-input
placeholder=""
v-model="louId"
:disabled="true">
</el-input>
    </el-form-item>


    <el-form-item label="房间号" prop="fangjianId">
      
      <el-input
placeholder=""
v-model="fangjianId"
:disabled="true">
</el-input>
    </el-form-item>


    <el-form-item label="床位号" prop="chuangwei">
      
      <el-input
placeholder=""
v-model="chuangwei"
:disabled="true">
</el-input>
    </el-form-item>
          
              <el-form-item label="情况说明" prop="text">
                <div id="div1" style="width: 74%; margin: 4px 0"></div>
              </el-form-item>
              <el-form-item label="缺勤时间" prop="releaseTime" style="margin-top: 27px">
                <el-date-picker
                    v-model="form.releaseTime"
                    clearable
                    placeholder="选择时间"
                    type="datetime"
                    value-format="YYYY-MM-DD HH:mm:ss"
                ></el-date-picker>
              </el-form-item>
            </el-form>
            <template #footer>
              <span class="dialog-footer">
                <el-button @click="cancel">取 消</el-button>
                <el-button type="primary" @click="save">确 定</el-button>
              </span>
            </template>
          </el-dialog>
          <!--    公告详情-->
          <el-dialog v-model="detailDialog" title="详情" width="50%">
            <el-card>
              <div v-html="detail.content"></div>
            </el-card>
            <template #footer>
              <span class="dialog-footer">
                <el-button type="primary" @click="closeDetailDialog">确 定</el-button>
              </span>
            </template>
          </el-dialog>
        </div>
      </div>
    </el-card>
  </div>
</template>
<script src="@/assets/js/Shangbaoqueqin.js"></script>