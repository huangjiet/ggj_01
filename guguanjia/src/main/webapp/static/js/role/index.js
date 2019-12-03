var ve = new Vue({
    el: '#main-container',
    data: function(){
        return {
            pageInfo: {
                pageNum: 1,
                pageSize: 5
            },
            params: {
                pageNum: '',
                pageSize: '',
                dataScope:'',//默认值，让下拉出现的时候默认被选中
                oid:'',
                name:'',
                remarks:'',
                officeName:''
            },
            setting:{
                data:{
                    simpleData:{
                        enable:true,
                        pIdKey:'parentId'
                    }
                },

                callback:{
                    onClick:this.onClick

                }
            },
            nodes: [],
            treeObj: {},
        }
    },
    methods: {
        selectAll: function (pageNum, pageSize) {
            this.params.pageNum=pageNum;
            this.params.pageSize=pageSize;
            //查询后台，返回分页数据，更新vue的pageInfo对象
            axios({
                url:'manager/role',
                method:'post',
                data:this.params
            }).then(response => {
                console.log(response.data);
                this.pageInfo = response.data;

            }).catch(function (error) {
                layer.msg(error);
            })
        },
        toUpdate: function (id) {
            axios({
                url:'manager/role/detail',
                params: {id:id}
            }).then( response=>{
                layer.obj=response.data;
                let index= layer.open({
                    type:2,
                    title:'用户修改',
                    content:'html/role/role-save.html',
                    area:['80%','80%'],
                    end:() =>{
                        this.selectAll(this.pageInfo.pageNum,this.pageInfo.pageSize);
                    }
                });

            }).catch(function (error) {
                layer.msg(error);
            })
        },
        update: function () {

        },
        toDelete: function (id) {

        },
        deleteById: function () {

        },
        initTree: function () {
            axios({
                url:'manager/office/list'
            }).then(response=> {
               this.nodes =response.data;
                this.nodes[this.nodes.length]={
                    "id":0,
                    "name":"所有机构"
                };
                this.treeObj = $.fn.zTree.init($("#pullDownTreeone"),this.setting,this.nodes);
            }).catch(function (error) {
                layer.msg(error);
            })
        },

        onClick:function(event, treeId, treeNode){
            if(!treeNode.id==0){
                this.params.oid=treeNode.id;
                this.params.officeName = treeNode.name;
            }else{
                this.params.oid='';
                this.params.officeName = '';
            }

            this.selectAll(this.pageInfo.pageNum, this.pageInfo.pageSize)
        },
        managerUser:function (rid) {
            layer.rid=rid;
                let  index = layer.open({
                        type:2,
                        title: '用户角色授权',
                        content: 'html/role/role-user.html',
                        area:['80%','80%']

                    });
        }
    },
    created: function () {
        this.selectAll(this.pageInfo.pageNum, this.pageInfo.pageSize);
    },
    mounted:function(){
        this.initTree();
    }

});
    