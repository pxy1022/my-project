import request from "@/utils/request";

const {ElMessage} = require("element-plus");

export default {
    name: "FeeInfo",
    components: {},
    data() {
        // 金额验证
        const checkAmount = (rule, value, callback) => {
            if (!value && value !== 0) {
                return callback(new Error("费用金额不能为空"));
            }
            setTimeout(() => {
                if (value < 0) {
                    callback(new Error("费用金额不能为负数"));
                } else {
                    callback();
                }
            }, 100);
        };
        return {
            loading: true,
            disabled: false,
            judge: false,
            dialogVisible: false,
            search: "",
            currentPage: 1,
            pageSize: 10,
            total: 0,
            tableData: [],
            form: {},
            rules: {
                feeType: [
                    {required: true, message: "请选择费用类型", trigger: "change"},
                ],
                dormBuildId: [
                    {required: true, message: "请输入楼宇号", trigger: "blur"},
                ],
                dormRoomId: [
                    {required: true, message: "请输入房间号", trigger: "blur"},
                ],
                amount: [
                    {required: true, validator: checkAmount, trigger: "blur"},
                ],
                paymentStatus: [
                    {required: true, message: "请选择缴费状态", trigger: "change"},
                ],
            },
        };
    },
    created() {
        this.load();
        this.loading = true;
        setTimeout(() => {
            //设置延迟执行
            this.loading = false;
        }, 1000);
    },
    methods: {
        async load() {
            request.get("/fee/find", {
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
        reset() {
            this.search = ''
            request.get("/fee/find", {
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
        filterTag(value, row) {
            return row.paymentStatus === value;
        },
        add() {
            this.dialogVisible = true;
            this.$nextTick(() => {
                this.$refs.form.resetFields();
                this.disabled = false;
                this.form = {};
                this.judge = false;
            });
        },
        save() {
            this.$refs.form.validate(async (valid) => {
                if (valid) {
                    if (this.judge === false) {
                        //新增
                        await request.post("/fee/add", this.form).then((res) => {
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
                        await request.put("/fee/update", this.form).then((res) => {
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
            this.dialogVisible = false;
        },
        handleEdit(row) {
            //修改
            this.judge = true;
            this.dialogVisible = true;
            this.$nextTick(() => {
                this.$refs.form.resetFields();
                // 深拷贝
                this.form = JSON.parse(JSON.stringify(row));
                this.disabled = true;
            });
        },
        async handleDelete(id) {
            console.log(id);
            request.delete("/fee/delete/" + id).then((res) => {
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

