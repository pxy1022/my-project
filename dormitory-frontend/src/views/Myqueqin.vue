<template>
  <div>
    <el-breadcrumb separator-icon="ArrowRight" style="margin: 16px">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item  >缺勤记录</el-breadcrumb-item>
     
    </el-breadcrumb>
    <el-card style="margin: 15px; min-height: calc(100vh - 111px)">
      <div>
        <!--    功能区-->
        <div style="margin: 10px 0">
          <!--    搜索区（仅管理员和宿管显示）-->
          <div v-if="isAdminOrManager" style="margin: 10px 0">
            <el-input v-model="search" clearable placeholder="请输入学号" prefix-icon="Search" style="width: 20%"/>
            <el-button icon="Search" style="margin-left: 5px" type="primary" @click="load"></el-button>
            <el-button icon="refresh-left" style="margin-left: 10px" type="default" @click="reset"></el-button>
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
<script src="@/assets/js/Myqueqin.js"></script>