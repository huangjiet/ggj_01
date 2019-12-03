var ve = new Vue({
    el: '#main-container',
    data: function() {
        return{
            pageInfo: {
                pageNum: 1,
                pageSize: 5
            },
            setting:{
              data:{
                  simpleData:{
                      enable:true,
                      pIdKey:'parentId'
                  }
              },
                callback:{
                  onClick:this.onClick,
                    beforeRemove:this.beforeRemove,
                    beforeEditName:this.beforeEditName
                },
                edit:{
                  enable: true,
                    removeTitle:'删除公司',
                    renameTitle:'修改公司'
                },
                view:{
                  addHoverDom:this.addHoverDom,
                    removeHoverDom:this.removeHoverDom
                }
            },
            nodes:[],
            treeObj:{},
            params: {
                pageNum: '',
                pageSize: '',
                areaName: '',
                aid: 0
            }
        }

    },
    methods: {
        selectAll: function (pageNum, pageSize) {
            this.params.pageNum=pageNum;
            this.params.pageSize=pageSize;
            axios({
                url:'manager/office',
                method:'post',
                data:this.params
            }).then( response=>{
                this.pageInfo=response.data;

            }).catch(function (error) {
                console.log(error);
            })
        },
        toUpdate: function (id) {
            axios({
                url:'manager/office/toUpdate',
                params: {id:id}
            }).then(response => {
                layer.obj = response.data;//返回数据，绑定到layer上，传递给子窗口
                console.log(layer);
                let index = layer.open({
                    type:2,
                    title:'区域修改',
                    content:'html/office/update.html',
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
        detail:function(){},
        save: function () {

        },
        initTree:function(){//初始化
            //获取nodes
            axios({
                url:'manager/office/list'
            }).then(response => {
                this.nodes = response.data;//   this.setNodes(.....)
                this.nodes[this.nodes.length]={
                    "id": 0,
                    "name": "所有机构"
                };
                this.treeObj =   $.fn.zTree.init($("#treeMenu"),this.setting,this.nodes);
                console.log(this.treeObj)  ;

            }).catch(function (error) {
                layer.msg(error);
            })
        },
        onClick:function(event, treeId, treeNode){
            this.params.aid=treeNode.id;
            this.selectAll(this.pageInfo.pageNum, this.pageInfo.pageSize)
            // console.log(11)
        },
        beforeRemove: function (treeId,treeNode) {  //树的id  和  当前的修改按键对应的node对象  
            console.log(treeNode);
            return true;
        },
        beforeEditName: function (treeId,treeNode) {
            console.log(treeNode);
            this.toUpdate(treeNode.id);
            return false;
        },
        addHoverDom: function (treeId,treeNode) {
            let aObj = $("#" + treeNode.tId +"_a");
            if ($("#diyBtn_space_"+treeNode.id).length>0){ return;}//如果已经存在有addspan  则不操作

            let editStr = "<span id='diyBtn_space_" +treeNode.id+ "' class='button add'> </span>";
            aObj.append(editStr);
            let span = $("#diyBtn_space_"+treeNode.id);
            if (span) span.on("click", function(){console.log("diy Button for " + treeNode.name);});
        },
        removeHoverDom:function(treeId,treeNode){
            $("#diyBtn_space_"+treeNode.id).unbind().remove();
        }
    },
    created: function () {
        this.selectAll(this.pageInfo.pageNum, this.pageInfo.pageSize);
    },
    mounted:function(){
        this.initTree();
    }
});
    