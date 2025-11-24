import request from "@/utils/request";
import E from "wangeditor";

const {ElMessage} = require("element-plus");
let editor;
export default {
    name: "BuildingInfo",
    components: {
        E,
    },
    data() {
        return {
            author: "",
            loading: true,
            disabled: false,
            judge: false,
            dialogVisible: false,
            detailDialog: false,
            search: "",
            currentPage: 1,
            pageSize: 10,
            total: 0,
            name:'',
            fangjianId:'',
            louchengId:'',
            louId:'',
            chuangwei:'',
            tableData: [],
            xuehao: [],
            fangjian: [],
            detail: {},
            form: {
                id: "",
                username: "",
                name: this.name,
                fangjianId:this.fangjianId,
                content: "",
                fangjianId:this.fangjianId,
                chuangwei:this.chuangwei,
                louchengId:this.louchengId,
                louId:this.louId,
                sid:'',
                sname:'',
                releaseTime: ""
            },
            rules: {
                username: [{required: true, message: "请选择学号", trigger: "blur"}],
                releaseTime: [
                    {required: true, message: "请选择时间", trigger: "blur"},
                ],
            },
        };
    },
    computed: {
        // 判断是否是管理员或宿管
        isAdminOrManager() {
            try {
                let identityStr = sessionStorage.getItem("identity");
                let identity = identityStr ? JSON.parse(identityStr) : '';
                return identity !== 'stu';
            } catch (e) {
                return false;
            }
        }
    },
    created() {
        this.getSessionInfo();
        this.load();
        
        
        this.loading = true;
        setTimeout(() => {
            //设置延迟执行
            this.loading = false;
        }, 1000);
    },
    mounted() {
    },
    methods: {
        async load() {
            // 获取用户身份
            let identityStr = sessionStorage.getItem("identity");
            let identity = identityStr ? JSON.parse(identityStr) : '';
            let users = JSON.parse(sessionStorage.getItem("user") || '{}');
            
            // 如果是学生，只查询自己的记录；如果是管理员或宿管，查询所有记录
            if (identity === 'stu') {
                // 学生：查询自己的缺勤记录
                request.get("/queqin/finds", {
                    params: {
                        pageNum: this.currentPage,
                        pageSize: this.pageSize,
                        username: users.username
                    },
                }).then((res) => {
                    console.log(res);
                    this.tableData = res.data.records;
                    this.total = res.data.total;
                    this.loading = false;
                });
            } else {
                // 管理员或宿管：查询所有缺勤记录
                request.get("/queqin/find", {
                    params: {
                        pageNum: this.currentPage,
                        pageSize: this.pageSize,
                        search: this.search || ''
                    },
                }).then((res) => {
                    console.log(res);
                    this.tableData = res.data.records;
                    this.total = res.data.total;
                    this.loading = false;
                });
            }
        },


       
        
        reset() {
            // 获取用户身份
            let identityStr = sessionStorage.getItem("identity");
            let identity = identityStr ? JSON.parse(identityStr) : '';
            let users = JSON.parse(sessionStorage.getItem("user") || '{}');
            this.search = '';
            this.currentPage = 1;
            
            // 如果是学生，只查询自己的记录；如果是管理员或宿管，查询所有记录
            if (identity === 'stu') {
                // 学生：查询自己的缺勤记录
                request.get("/queqin/finds", {
                    params: {
                        pageNum: 1,
                        pageSize: this.pageSize,
                        username: users.username
                    },
                }).then((res) => {
                    console.log(res);
                    this.tableData = res.data.records;
                    this.total = res.data.total;
                    this.loading = false;
                });
            } else {
                // 管理员或宿管：查询所有缺勤记录
                request.get("/queqin/find", {
                    params: {
                        pageNum: 1,
                        pageSize: this.pageSize,
                        search: ''
                    },
                }).then((res) => {
                    console.log(res);
                    this.tableData = res.data.records;
                    this.total = res.data.total;
                    this.loading = false;
                });
            }
        },
        getSessionInfo() {
            let user = JSON.parse(sessionStorage.getItem("user"));
            this.author = user.name;
        },
        showDetail(row) {
            this.detailDialog = true;
            this.$nextTick(() => {
                this.detail = row;
            });
        },
        closeDetailDialog() {
            this.detailDialog = false;
        },
        add() {
            this.form.author = this.author;
            console.log(this.form.author);
            this.dialogVisible = true;
            this.$nextTick(() => {
                this.$refs.form.resetFields();
                //加载wangEdit
                editor = new E("#div1");
                editor.create();
                this.disabled = false;
                this.form = {};
                this.judge = false;
            });
        },
       
        cancel() {
            this.$refs.form.resetFields();
            editor.txt.clear();
            editor.destroy();
            this.dialogVisible = false;
        },
     
      
        handleSizeChange(pageSize) {
            //改变每页个数
            this.pageSize = pageSize;
            this.load();
        },
        handleCurrentChange(pageNum) {
            //改变页码
            this.currentPage = pageNum;
            this.load();
        },
    },
};