var ve = new Vue({
    el: '#main-container',
    data: function(){
       return {
           obj:{},
           setting:{
               data:{
                   simpleData:{
                       enable:true,
                       pIdKey:'parentId'
                   }
               },
               callback:{
                   onClick:this.onClick
               },
               view:{
                   fontCss:this.setCss
               },
               check:{
                   enable: true
               }

           },
           menuSetting:{
               data: {
                   simpleData: {
                       enable: true,
                       pIdKey: 'parentId'//根据node节点中的parentId属性来作为pId属性值
                   }
               },
               callback:{
                   onClick:this.onClick
               },
               view:{
                   fontCss:this.setCss
               },
               check:{
                   enable:true
               }
           },
           nodes: [],
           treeObj: '',
           treeNode:{},
           menuTreeObj:{},
           menuNodes:[],
           rid:''

       }
    },
    methods: {

        changeScope:function(){

            let option = $("#chosenSelectEdit option:selected");
            if(option.val()==9){

                if(this.treeObj==''){//未初始化公司树对象
                    this.initTree();
                }
                $("#treeSelectOfficeEdit").css("display","inline-block");

            }else{
                $("#treeSelectOfficeEdit").css("display","none");
            }

        },
        initMenuTree:function(){
            axios({
                url:'manager/menu/selectAll'
            }).then(response => {
                this.menuNodes = response.data;
                axios({
                    url:'manager/menu/selectByRid',
                    params:{rid:this.obj.id}
                }).then(response => {
                    let roleMenus = response.data;
                    for (let i = 0; i < this.menuNodes.length; i++) {
                        for (let j = 0; j < roleMenus.length; j++) {
                            if(this.menuNodes[i].id==roleMenus[j].id){
                                this.menuNodes[i].checked=true;
                            }
                        }
                    }
                    //初始化菜单
                    this.menuTreeObj =   $.fn.zTree.init($("#select-treetreeSelectResEdit"),this.menuSetting,this.menuNodes);

                }).catch(function (error) {
                    layer.msg(error);
                })

            }).catch(function (error) {
                layer.msg(error);
            })
        },
        initTree:function () {
            axios({
                url:'manager/office/list'
            }).then(response => {
                this.nodes = response.data;
                this.nodes[this.nodes.length]={
                    "id": 0,
                    "name": "所有机构"
                };
                this.treeObj =   $.fn.zTree.init($("#select-treetreeSelectOfficeEdit"),this.setting,this.nodes);
                console.log(this.treeObj)  ;
            }).catch(function (error) {
                layer.msg(error);
            })
        },
        onClick:function(event, treeId, treeNode){
            this.treeNode = treeNode;
            let treeNodes = this.treeObj.transformToArray(this.treeObj.getNodes());
            //清除原高亮标记
            for (let index in treeNodes) {
                if(treeNodes[index].id==treeNode.id){
                    treeNodes[index].higtLine = true;//设置高亮标记
                }else{
                    treeNodes[index].higtLine=false;
                }
                this.treeObj.updateNode(treeNodes[index]);//更新节点，自动调用清除css
            }


        },
        setCss:function(treeId,treeNode){
            return treeNode.higtLine?{color:"red"}:{color:''};//根据标记显示高亮
        }
    },
    created: function () {
        this.obj=parent.layer.obj;
    },
    mounted:function () {
        this.initMenuTree();
        $("#chosenSelectEdit").chosen({width: "40%",search_contains: true});
        //绑定点击触发处理
        $("#chosenSelectEdit").on("change",() =>{
            this.changeScope();//this在jq中是元素对象
        })

        //如果角色的dataScope是按明细查询，则要显示公司树
        if(this.obj.dataScope==9){
            $("#treeSelectOfficeEdit").css("display","inline-block");
            this.treeObj = this.initTree();//初始化公司树
        }
    }
});
