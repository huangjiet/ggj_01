var ve = new Vue({
    el: '#main-container',
    data: function() {
        return {
            setting: {
                data: {
                    simpleData: {
                        enable: true,
                        pIdKey: 'parentId'//根据node节点中的parentId属性来作为pId属性值
                    }
                },
                callback: {
                    onClick: this.onClick

                },
                view:{
                    fontCss:this.setCss
                }
            },
            nodes: [],
            treeObj: {},
            rid:'',//授权角色的id
            checkedUser:[],//已授权角色的用户
            showClass:'hide',//显示隐藏移除授权按钮
            companyUsers:[],//公司未授权当前角色的用户
            companyShowClass:'hide',////显示隐藏授权按钮
            uids:[],//需要移除角色授权的人员id数组
            cids:[],
            treeNode:{}
        }
    },
    methods: {

        initTree:function(){//初始化ztree

            //获取nodes
            axios({
                url:'manager/office/list'
            }).then(response => {
                this.nodes = response.data;//   this.setNodes(.....)
                this.nodes[this.nodes.length]={
                    "id": 0,
                    "name": "所有机构"
                }
                this.treeObj =   $.fn.zTree.init($("#treeOffice"),this.setting,this.nodes);
                // console.log(this.treeObj)  ;

            }).catch(function (error) {
                layer.msg(error);
            })
        },
        onClick:function(event, treeId, treeNode){
            this.treeNode = treeNode;
             let  treeNodes= this.treeObj.transformToArray(this.treeObj.getNodes());
            for (let index in treeNodes ){
                if (treeNodes[index].id == treeNode.id){
                    treeNodes[index].highLine=true;
                }else{
                    treeNodes[index].highLine=false;
                }
                this.treeObj.updateNode(treeNodes[index])
            }

            this.dxUser();
        },

        setCss:function (treeId,treeNode) {
            return treeNode.highLine?{color:"red"}:{color:""};
        },
        yxUser:function () {
            axios({
                url:'manager/user/selectByRid',
                params:{rid:this.rid}
            }).then(response=>{
                this.checkedUser =response.data;

                for (let i = 0; i <this.checkedUser.length ; i++) {
                    this.checkedUser[i].show=false;
                }
            }).catch(function (error) {
                layer.msg(error);
            })
        },
        changeShow:function (id) {
              for (let i=0;i<this.checkedUser.length;i++){
                    if (this.checkedUser[i].id==id){
                        this.checkedUser[i].show=!this.checkedUser[i].show;
                         if (this.checkedUser[i].show){
                             this.uids.push(this.checkedUser[i].id);
                             this.showClass='show';
                             return;
                         }
                    }
              }

            if($("#yxuser input:checked").length==0){//如果没有任何的input被选中
                this.showClass='hide';//隐藏提交按钮
            }

        },

        removeUsers:function () {
            let params = {rid:this.rid,uids:this.uids};

            axios({
                url:'manager/role/updateByUids',
                method:"post",
                data:params
            }).then(response=>{
                this.yxUser();
                this.showClass='hide';
              layer.msg(response.data.msg);
            }).catch(function (error) {
                layer.msg(error)
            })
        },
        dxUser:function () {
            axios({
                url:'manager/user/selectNoRole',
                params: {oid:this.treeNode.id,rid:this.rid}
            }).then(response=>{
                this.companyUsers=response.data;
                for (let i=0;i<this.companyUsers.length;i++){
                    this.companyUsers[i].show=false;
                }
            }).catch(function (error) {
                layer.msg(error);
            })
        },
        changeCompanyShow:function (id) {
            for (let i=0; i<this.companyUsers.length;i++){
                if (this.companyUsers[i].id==id){
                    this.companyUsers[i].show=!this.companyUsers[i].show;

                    if (this.companyUsers[i].show){
                        this.cids.push(this.companyUsers[i].id);
                        this.companyShowClass='show';
                        return;
                    }
                }
            }
            if ($("#dxuser input:checked").length==0){
                this.companyShowClass='hide';
            }
        },
        insertUsers:function () {
            let  params={cids:this.cids,rid:this.rid};
            axios({
                url:'manager/role/insertBatch',
                data:params,
                method:"post"
            }).then(response=>{
                layer.msg(response.data.msg);
                this.dxUser();
                this.companyShowClass='hide';

            }).catch(function (error) {
                layer.msg(error)
            })
        }


    },
    created: function () {
        this.rid=parent.layer.rid;
    },mounted:function(){
        this.initTree();//初始化公司树
        this.yxUser();
    }
});
    