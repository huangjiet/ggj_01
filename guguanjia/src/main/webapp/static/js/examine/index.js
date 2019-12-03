var ve = new Vue({
    el: '#main-container',
    data: function() {
        return {
            isShow: false,

            pageInfo: {
                pageNum: 1,
                pageSize: 5
            },
            params:{
                pageNum:'',
                pageSize:'',
                type:''
            },

            treeObj:{},

            setting: {
                data: {
                    key:{
                        title:"fullName"
                    },

                    simpleData: {
                        enable: true,
                        pIdKey:"parent_id"
                    }
                },
                callback: {
                    onClick: this.onClick//点击事件触发是执行
                },
                view:{
                    fontCss:this.setCss
                }

            },
            nodes:[],
            name:'',
            node:''
        }
    },
        methods:{
            initTree:function() {
                axios({
                    url:"manager/office/list"
                }).then(response=>{
                    this.nodes=response.data;
                    let treeObj = $.fn.zTree.init($("#pullDownTreeone"),this.setting,this.nodes);
                    treeObj.expandAll(true);
                    this.treeObj=treeObj;
                }).catch(function (error) {
                    console.log(error)
                });

            },

            selectAll:function (pageNum,pageSize) {
                this.params.pageNum=pageNum;
                this.params.pageSize=pageSize;
                axios({
                    url:"manager/examine/index",
                    method:"post",
                    data:this.params
                }).then(response=>{
                    this.pageInfo=response.data;
                }).catch(function (error) {
                    layer.msg(error);
                })
            },

            toUpdate:function (id) {

            },
            onClick: function (event,treeId,treeNode) {
                this.name=treeNode.name;
                this.node=treeNode;
            }
            ,search:function () {
                let nodes = this.treeObj.getNodesByParamFuzzy("name",this.name,null);

                console.log(nodes+"==========================================================");

                let treeNodes= this.treeObj.transformToArray(this.treeObj.getNodes());
                
                for (let index in treeNodes){
                    treeNodes[index].highLine=false;
                    this.treeObj.updateNode(treeNodes[index]);
                }
                
                for (let index in treeNodes){

                    for (let nodeIndex in nodes) {

                        if (treeNodes[index].id==nodes[nodeIndex].id){
                            treeNodes[index].highLine = true;
                            this.treeObj.updateNode(treeNodes[index]);
                        }
                    }
                }
            },
            setCss:function (treeId,treeNode) {
                return treeNode.highLine?{color:"green"}:{color:''};
            }

        },
    created: function() {
        this.selectAll(this.pageInfo.pageNum, this.pageInfo.pageSize);
    },
    mounted:function () {
        this.initTree();
    }
});
    