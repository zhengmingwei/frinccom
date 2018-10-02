(function () {
// start
    var options = {

        el: '#user-userinfo',
        data: {
            passwordRequired: true,
            /**
             * 菜单选项 add by wangbo  true 表示此时选择一个用户 false 表示未选择或多个选择
             * */
            displaybtn: false,

            /**
             * 选中的用户 add by wangbo  displaybtn = true 时才有数据意义
             * */
            selectUser: {},
            addOrUpdateTitle: "",

            organizationList: [],
            departmentList: [],
            staffList: [],
            userType: [],
            userInfo: {
                disable: false
            },
            roleList: [],
            sysUserId: null,
            queryDto: {
                organizationId: null,
                userInfo: null,
                page: 0,
                size: 10,
            },
            pageNo: 1, //当前页
            totalPages: 0, //总页数
            totalElements: 0, //总条数

            userTable: [],
        },

        created: function () {
            this.init();
           /* this.$http.get(ctx + "/v1/role/organization/list").then(function (response) {
                var result = response.data.data;
                this.organizationList = result;
                this.flushTable();
            });*/
        },

        mounted: function () {


            $("#userInfoForm").validate({
                rules: {
                    password: {
                        minlength: 6
                    },

                    repeatPassword: {
                        equalTo: "#password"
                    },
                },
                messages: {
                    password: {
                        minlength: "密码不能小于6个字符",
                    },

                    repeatPassword: {
                        equalTo: "两次输入密码不一致"
                    },
                }
            });
        },

        methods: {
            init: function () {
                var that = this;
                that.selAllFlag = false;
                that.displaybtn = false,
                that.$http.get(ctx + "/v1/role/organization/list").then(function (response) {
                    var result = response.data.data;
                    that.organizationList = result;
                    that.flushTable();
                });
            },

            clickInformationPage : function( p ) {
                var page = p || 1;
                var reg = /^\d+$/;
                if (!reg.test(this.queryDto.size)) {
                    this.$alert("请输入合法分页数量", "提示");
                    this.queryDto.size = 10;
                }
                this.init();
            },
            /**
             * 分页
             * @param pageNo
             */
            page: function (pageNo) {
                var that = this;
                switch (pageNo) {
                    case "first" :
                        that.queryDto.page = 0;
                        break;
                    case "left" :
                        if (that.queryDto.page > 0) {
                            that.queryDto.page--;
                        }
                        ;
                        break;
                    case "right" :
                        if (that.queryDto.page < that.totalPages - 1) {
                            that.queryDto.page++;
                        }
                        ;
                        break;
                    case "last" :
                        if (that.totalPages > 0) {
                            that.queryDto.page = that.totalPages - 1;
                        }
                        break;
                }
                that.flushTable();
                this.selAllFlag = false;
            },
            cleanQueryInfo: function () {
                this.queryDto = {
                    organizationId: null,
                    userInfo: null,
                    page: 0,
                    size: 10,
                }
                this.init();//重置查询条件 采用页面初始化操作
                this.selAllFlag = false;
            },

            getOrganizationList_user: function () {
                this.addOrUpdateTitle = "新增用户";
                this.passwordRequired = true;
                this.userInfo = {
                    disable: false
                };
                this.$http.get(ctx + "/v1/sysuser/user_type/list").then(function (response) {
                    var result = response.data;
                    this.userType = result;
                });
            },

            getDepartmentList: function () {
                this.userInfo.departmentId = null;
                this.userInfo.staffId = null;
                this.departmentList = {};
                this.staffList = {};
                var organizationId = this.userInfo.organizationId;
                if (undefined == organizationId) {
                    return;
                }
                this.$http.get(ctx + "/v1/sysuser/department/list/" + organizationId).then(function (response) {
                    var result = response.data.data;
                    // console.log(result);
                    this.departmentList = result;
                });
            },

            getstaffList: function () {
                var departmentId = this.userInfo.departmentId;
                if (undefined == departmentId) {
                    return;
                }
                this.$http.get(ctx + "/v1/sysuser/staff/list/" + departmentId).then(function (response) {
                    var result = response.data.data;
                    this.staffList = result;
                });
            },
            checkTable: function () {
                if (!this.displaybtn) {
                    this.$alert('请选择一个要编辑的项目!');
                    return;
                }
                if (null == this.selectUser || null == this.selectUser.id) {
                    this.$alert('选择的数据异常!');
                    return;
                }

                //获得该用户所有的角色信息
                var that = this;
                that.$http.get(ctx + "/v1/sysuser/role/" + this.selectUser.id).then(function (response) {
                    var result = response.data.data;
                    //console.log(Vue.dump(result));
                    that.roleList = result;
                    //console.log(that.selectUser);
                    that.roleList.forEach(function (item, index) {
                        item.checked = item.id == that.selectUser.roleId;
                    });
                    displaybtn = false;
                });
            },

            assignedRole: function () {
                if (null == this.selectUser || null == this.selectUser.id) {
                    this.$alert('用户数据异常，请刷新!');
                    return;
                }
                var that = this;
                var ids = [];
                that.roleList.forEach(function (item, index) {
                    if (item.checked) {
                        ids.push(item.id);
                    }
                });

                if (ids.length > 1) {
                    this.$alert('用户最多只能分配一个角色!');
                    return;
                }

                var dto = {};
                dto.sysUserId = this.selectUser.id;
                dto.roleIds = ids;

                this.$http.post(ctx + "/v1/sysuser/role", dto).then(function (response) {
                    //this.$alert("保存成功");
                    this.$message({type: 'success', message: '保存成功!'});
                    $('#closeABtn').click();
                    this.displaybtn = false;
                    this.flushTable();
                }, function (response) {
                    //this.$alert("保存失败");
                    this.$message({type: 'success', message: '保存失败!'});
                    $('#closeABtn').click();
                    this.displaybtn = false;
                });

            },

            //需要阻止bootstrap的弹窗效果  -- add by wangbo2017.2.13
            checkselect: function (model) {
                var that = this;
                var ids = [];
                that.userTable.forEach(function (item, index) {
                    if (item.checked == true) {
                        ids.push(item.id);
                    }
                });
                that.displaybtn = ids.length == 1;
                if (that.displaybtn) {
                    this.selectUser = model;
                    //console.log(this.selectUser);
                }
                else {
                    this.selectUser = {};
                }
            },

            findOne: function () {
                var that = this;
                var ids = [];
                that.userTable.forEach(function (item, index) {
                    if (item.checked) {
                        ids.push(item.id);
                    }
                });

                // if (ids.length == 0) {
                //     that.$alert('请选择要编辑的项目!');
                //     return;
                // }
                //
                // if (ids.length > 1) {
                //     that.$alert('只能选择一个进行编辑!');
                //     return;
                // }

                this.addOrUpdateTitle = "修改用户";
                this.passwordRequired = false;

                this.$http.get(ctx + "/v1/sysuser/" + ids[0]).then(function (response) {
                    //console.log(Vue.dump(this.organizationList));
                    this.userInfo = response.data.data;
                    //console.log(Vue.dump(this.userInfo));
                    var organizationId = this.userInfo.organizationId;
                    var staffId = this.userInfo.staffId;
                    var departmentId = this.userInfo.departmentId;
                    if (organizationId != undefined) {
                        this.$http.get(ctx + "/v1/sysuser/department/list/" + organizationId).then(function (response) {
                            var result = response.data.data;
                            this.departmentList = result;
                            this.userInfo.departmentId = departmentId;
                        });
                    }
                    if (departmentId != undefined) {
                        //console.log(this.userInfo.staffId);
                        this.$http.get(ctx + "/v1/sysuser/staff/list/" + departmentId).then(function (response) {
                            var result2 = response.data.data;
                            this.staffList = result2;
                            this.userInfo.staffId = staffId;
                            //console.log(Vue.dump(this.staffList));
                        });
                    }
                    //默认两个密码一致
                    this.userInfo.repeatPassword = this.userInfo.password;
                    //console.log(this.userInfo.staffId);
                    //console.log(Vue.dump(this.userInfo));
                });
            },
            changeParameter:function(){
                var code = this.userInfo.code;
                var username = this.userInfo.username;
                var that = this;
                that.$http.get(ctx + "/v1/sysuserByParameter/" + code+"/"+ username+"/").then(function (response) {
                    var result = response.data;
                    if (result.data != undefined) {
                        if('YHMYCZ'==response.data.data){
                            that.$alert( "用户名已存在", '提示');//弹出框提示方式
                        }else if('BMYBSY'==response.data.data){
                            that.$alert( "编码已被使用", '提示');//弹出框提示方式
                        }else if('BMHYHMYBLYYHTSSY'==response.data.data){
                            that.$alert( "编码和用户名已被另一用户同时使用", '提示');//弹出框提示方式
                        }else{
                            this.userInfo = result.data;
                        }
                    }
                });
            },
            save: function () {
                var valid = $("#userInfoForm").valid();
                if (!valid)
                    return;
                var code = this.userInfo.code;
                var username = this.userInfo.username;
                var that = this;
                that.$http.get(ctx + "/v1/sysuserByParameter/" + code+"/"+ username+"/").then(function (response) {
                    var result = response.data;
                    if (result.data != undefined) {
                        if('YHMYCZ'==response.data.data){
                            that.$alert( "用户名已存在", '提示');//弹出框提示方式
                        }else if('BMYBSY'==response.data.data){
                            that.$alert( "编码已被使用", '提示');//弹出框提示方式
                        }else if('BMHYHMYBLYYHTSSY'==response.data.data){
                            // that.$alert( "编码和用户名已被另一用户同时使用", '提示');//弹出框提示方式
                            this.$http.put(ctx + "/v1/sysuser", this.userInfo).then(function (response) {
                                var result = response.data.data;
                                
                                if(response.status == 200){
                                    //this.queryDto.userInfo = result.code;
                                    //this.$alert("保存成功");
                                    this.$message({type: 'success', message: '保存成功!'});
                                }else{
                                    //this.$alert("保存失败");
                                    this.$message({type: 'success', message: '保存失败!'});
                                }
                                $('#cancelNBtn').click();
                                this.flushTable();
                            });
                            $("#userInfoForm").resetForm();
                        }else{
                        }
                    }else{
                        this.$http.put(ctx + "/v1/sysuser", this.userInfo).then(function (response) {
                            var result = response.data.data;
                            if(response.status == 200){
                                //this.queryDto.userInfo = result.code;
                                //this.$alert("保存成功");
                                this.$message({type: 'success', message: '保存成功!'});
                            }else{
                                //this.$alert("保存失败");
                                this.$message({type: 'success', message: '保存失败!'});
                            }
                            $('#cancelNBtn').click();
                            this.flushTable();
                        });
                        $("#userInfoForm").resetForm();
                    }
                });
            },

            del: function () {

                var that = this;
                var ids = [];
                that.userTable.forEach(function (item, index) {
                    if (item.checked) {
                        ids.push(item.id);
                    }
                });
                //**************************************** edit by wangbo 2017.2.13
                if (null == ids || ids.length == 0) {
                    this.$alert("请选择删除项");
                    return;
                }
                //**************************************** end
                that.$confirm('此操作将永久删除此数据, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(function () {
                    //alert("ids:"+ids);
                    that.$http.post(ctx + "/v1/sysuser", ids).then(function (response) {
                        if(response.status == 200){
                            that.$message({type: 'success', message: '删除成功!'});
                            this.flushTable();
                        }
                    });
                }).catch(function () {
                    // that.$message({type: 'info', message: '已取消删除'});
                });
            },

            flushTable: function () {
                this.$http.post(ctx + "/v1/sysuser/list", this.queryDto).then(function (response) {
                    var result = response.data.data;
                    this.userTable = result.data;
                    this.displaybtn = false;
                    this.totalPages = result.totalPages;
                    this.totalElements = result.totalElements;
                });
            },
        }
    };

    var page = new Vue(options);
// end;
})();