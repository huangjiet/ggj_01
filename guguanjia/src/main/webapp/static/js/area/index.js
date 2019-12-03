var ve = new Vue({
    el: '#main-container',
    data: function(){
        return {
            pageInfo: {
                pageNum: 1,
                pageSize: 5
            },

            setting:{
                data:{
                    simpleData: {
                        enable: true,
                        pIdKey: 'parentId'
                    }
                },
                callback:{
                    onClick:this.onClick
                }
            },
            params: {
                pageNum: '',
                pageSize: '',
                areaName:'',
                aid:0
            },
            nodes:[],
            treeObj:{}

        }
    },
    methods: {
        selectAll: function (pageNum, pageSize) {
            this.params.pageNum=pageNum;
            this.params.pageSize=pageSize;
            axios({
                url:"manager/area/selectPage",
                method:"post",
                data:this.params
            }).then(response=>{
                console.log(response.data);
                this.pageInfo=response.data;
            }).catch(function (error) {
                console.log(error)
            })
        },
        toUpdate: function (id) {
            axios({
                url:'manager/area/toUpdate',
                params: {id:id}
            }).then(response => {

                layer.obj = response.data;//返回数据，绑定到layer上，传递给子窗口
                console.log(layer);
                let index = layer.open({
                    type:2,
                    title:'区域修改',
                    content:'html/area/save.html',
                    area:['80%','80%'],
                    end: () => {//将then函数中的this传递到end的回调函数中
                        console.log(".....");
                        //刷新页面数据    1.直接查询selectAll实现    2.获取layer.appVersion更新当前pageInfo的该数据
                        this.selectAll(this.pageInfo.pageNum,this.pageInfo.pageSize);
                    }
                });
            }).catch(function (error) {
                console.log(error);
            })


        },
        update: function () {

        },
        toDelete: function (id) {

        },
        deleteById: function () {

        },
        save: function () {

        },
        detail:function(){},

        exportExcel:function(){
            location.href='manager/area/exportExcel';
        },

        initTree:function () {//初始化zTree
            axios({
                url:"manager/area/index"
            }).then(response=>{
                this.nodes=response.data;

                this.treeObj= $.fn.zTree.init($("#treeMenu"),this.setting,this.nodes);
            }).catch(function (error) {
                layer.msg(error);
            })
        },
        onClick: function (event,treeId,treeNode) {
            this.params.aid=treeNode.id;
            this.selectAll(this.pageInfo.pageNum, this.pageInfo.pageSize);
        },
        importExcel:function (e) {
            console.log(e.target);//获取事件源对象   input
            let file = e.target.files[0];//获取上传的文件对象
            let form = new FormData();//构建表单对象
            form.append("file",file);//绑定file对象到key file上，该key必须与后台的接收参数名一致
            //获取nodes
            axios({
                url:'manager/area/importExcel',
                method:"post",
                headers:{"content-type":'multipart/form-data'},//设置请求头为文件上传
                data:form
            }).then(response => {
                layer.msg(response.data.msg);
            }).catch(function (error) {
                layer.msg(error);
            })
        }
    },
    created: function () {
        this.selectAll(this.pageInfo.pageNum, this.pageInfo.pageSize);
    },
    mounted:function () {
        this.initTree();
    }
});
    