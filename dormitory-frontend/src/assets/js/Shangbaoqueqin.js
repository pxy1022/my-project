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
    created() {
        this.getSessionInfo();
        this.load();
        this.student();
        
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
            request.get("/queqin/find", {
                params: {
                    pageNum: this.currentPage,
                    pageSize: this.pageSize,
                    search: this.search,
                },
            }).then((res) => {
                console.log(res);
                this.tableData = res.data.records;
                this.total = res.data.total;
                this.loading = false;
            });
                  
        },


        async student() {
            request.get("/queqin/liststudent", {
                params: {
                },
            }).then((res) => {
            
                this.xuehao = res.data;
            
            });
            
        },
        async   username()
        {
            

            request.get("/queqin/username", {
                params: {
                    username: this.form.username,
                   
                },
            }).then((res) => {
                
               this.name = res.data.name;
               this.louchengId = res.data.louchengId,
               this. louId= res.data.louId,
               this. fangjianId= res.data.fangjianId,
               this.chuangwei= res.data.chuangwei
            });
        },
        reset() {
            this.search = ''
            request.get("/queqin/find", {
                params: {
                    pageNum: 1,
                    pageSize: this.pageSize,
                    search: this.search,
                },
            }).then((res) => {
                console.log(res);
                this.tableData = res.data.records;
                this.total = res.data.total;
                this.loading = false;
            });
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
        save() {
            let users = JSON.parse(sessionStorage.getItem("user"))
            this.form.content = editor.txt.html(); //获取编辑器内容并赋值
            this.form.name= this.name,
            this.form.fangjianId=this.fangjianId,
            this.form.chuangwei=this.chuangwei,
            this.form.fangjianId=this.fangjianId,
            this.form.chuangwei=this.chuangwei,
            this.form. louchengId=this.louchengId,
            this.form.louId=this.louId,
            this.form.sid=users.username,
            this.form.sname=users.name,
            console.log(this.form.author);
            this.$refs.form.validate((valid) => {
                if (valid) {
                    if (this.judge === false) {
                        //新增
                        request.post("/queqin/add", this.form).then((res) => {
                            console.log(res);
                            if (res.code === "0") {
                                ElMessage({
                                    message: "新增成功",
                                    type: "success",
                                });
                                this.search = "";
                                this.load();
                                this.dialogVisible = false;
                            } else {
                                ElMessage({
                                    message: res.msg,
                                    type: "error",
                                });
                            }
                        });
                    } else {
                        //修改
                        request.put("/queqin/update", this.form).then((res) => {
                            console.log(res);
                            if (res.code === "0") {
                                ElMessage({
                                    message: "修改成功",
                                    type: "success",
                                });
                                this.search = "";
                                this.load();
                                this.dialogVisible = false;
                            } else {
                                ElMessage({
                                    message: res.msg,
                                    type: "error",
                                });
                            }
                        });
                    }
                }
            });
        },
        cancel() {
            this.$refs.form.resetFields();
            editor.txt.clear();
            editor.destroy();
            this.dialogVisible = false;
        },
        handleEdit(row) {
            //修改
            this.judge = true;
            this.dialogVisible = true;
            this.$nextTick(() => {
                this.$refs.form.resetFields();
                //加载wangEdit
                editor = new E("#div1");
                editor.create();
                // 生拷贝
                let users = JSON.parse(sessionStorage.getItem("user"))
                this.form = JSON.parse(JSON.stringify(row));
                this.name=this.form.name,
                this.fangjianId=this.form.fangjianId
                this.chuangwei=this.form.chuangwei
                this.fangjianId=this.form.fangjianId
               this.chuangwei= this.form.chuangwei,
                this.louchengId=this.form.louchengId,
                this.louId= this.form.louId,
                users.username=users.username,
                this.form.sname=users.name,
                editor.txt.html(this.form.content);
                this.disabled = true;
            });
        },
        handleDelete(id) {
            console.log(id);
            request.delete("/queqin/delete/" + id).then((res) => {
                if (res.code === "0") {
                    ElMessage({
                        message: "删除成功",
                        type: "success",
                    });
                    this.search = "";
                    this.load();
                } else {
                    ElMessage({
                        message: res.msg,
                        type: "error",
                    });
                }
            });
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