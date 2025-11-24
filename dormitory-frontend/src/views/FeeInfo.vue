<template>
  <div>
    <el-breadcrumb separator-icon="ArrowRight" style="margin: 16px">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>费用管理</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card style="margin: 15px; min-height: calc(100vh - 111px)">
      <div>
        <!--    功能区-->
        <div style="margin: 10px 0">
          <!--    搜索区-->
          <div style="margin: 10px 0">
            <el-input v-model="search" clearable placeholder="请输入费用类型或宿舍号" prefix-icon="Search" style="width: 20%"/>
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
          <el-table-column label="费用类型" prop="feeType" width="150px"/>
          <el-table-column label="楼宇号" prop="dormBuildId" sortable width="120px"/>
          <el-table-column label="房间号" prop="dormRoomId" sortable width="120px"/>
          <el-table-column label="费用金额" prop="amount" sortable width="150px">
            <template #default="scope">
              <span style="color: #f56c6c; font-weight: bold">¥{{ scope.row.amount }}</span>
            </template>
          </el-table-column>
          <el-table-column
              :filter-method="filterTag"
              :filters="[
              { text: '已缴费', value: '已缴费' },
              { text: '未缴费', value: '未缴费' },
            ]"
              filter-placement="bottom-end"
              label="缴费状态"
              prop="paymentStatus"
              sortable
              width="130px"
          >
            <template #default="scope">
              <el-tag
                  :type="scope.row.paymentStatus === '已缴费' ? 'success' : 'danger'"
                  disable-transitions
              >{{ scope.row.paymentStatus }}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column label="缴费时间" prop="paymentTime" sortable width="220px"/>
          <el-table-column label="备注" prop="remark" sortable/>
          <!--      操作栏-->
          <el-table-column label="操作" width="130px">
            <template #default="scope">
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
          <el-dialog v-model="dialogVisible" title="操作" width="30%" @close="cancel">
            <el-form ref="form" :model="form" :rules="rules" label-width="120px">
              <el-form-item label="费用类型" prop="feeType">
                <el-select v-model="form.feeType" clearable placeholder="请选择费用类型" style="width: 50%">
                  <el-option label="水电费" value="水电费"></el-option>
                  <el-option label="网费" value="网费"></el-option>
                  <el-option label="物业费" value="物业费"></el-option>
                  <el-option label="其他" value="其他"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="楼宇号" prop="dormBuildId">
                <el-input v-model="form.dormBuildId" clearable style="width: 50%"></el-input>
              </el-form-item>
              <el-form-item label="房间号" prop="dormRoomId">
                <el-input v-model="form.dormRoomId" clearable style="width: 50%"></el-input>
              </el-form-item>
              <el-form-item label="费用金额" prop="amount">
                <el-input-number v-model="form.amount" :min="0" :precision="2" clearable style="width: 50%"></el-input-number>
              </el-form-item>
              <el-form-item label="缴费状态" prop="paymentStatus">
                <el-radio v-model="form.paymentStatus" label="已缴费">已缴费</el-radio>
                <el-radio v-model="form.paymentStatus" label="未缴费">未缴费</el-radio>
              </el-form-item>
              <el-form-item label="缴费时间" prop="paymentTime">
                <el-date-picker
                    v-model="form.paymentTime"
                    clearable
                    placeholder="选择时间"
                    style="width: 48%"
                    type="datetime"
                    value-format="YYYY-MM-DD HH:mm:ss"
                ></el-date-picker>
              </el-form-item>
              <el-form-item label="备注" prop="remark">
                <el-input
                    v-model="form.remark"
                    :autosize="{ minRows: 3, maxRows: 10 }"
                    autosize
                    clearable
                    style="width: 80%"
                    type="textarea"
                ></el-input>
              </el-form-item>
            </el-form>
            <template #footer>
              <span class="dialog-footer">
                <el-button @click="cancel">取 消</el-button>
                <el-button type="primary" @click="save">确 定</el-button>
              </span>
            </template>
          </el-dialog>
        </div>
      </div>
    </el-card>
  </div>
</template>
<script src="@/assets/js/FeeInfo.js"></script>

