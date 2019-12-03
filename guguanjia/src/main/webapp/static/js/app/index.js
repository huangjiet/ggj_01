let vm = new Vue({
    el:'#main-container',
    data:{
        pageInfo:{
            pageNum:1,//默认值
            pageSize:5

        },
        appVersion:''

    },
    methods:{
        selectAll:function (pageNum,pageSize) {
            axios({
                url:'manager/app/index',
                params:{
                    pageNum:pageNum,
                    pageSize:pageSize
                }
            }).then(response => {
            this.pageInfo = response.data;
        }).catch(function (error) {
                console.log(error);
            })
        },

        toUpdate:function (id) {
            axios({
                url: 'manager/app/toUpdate',
                params: {id:id}
            }).then(response =>{
                layer.appVersion = response.data;
                console.log(layer+"-----------？？？-------------");
                let index = layer.open({
                    type:2,
                    title:'更新app',
                    content:'html/app/update.html',
                    area:['80%','80%'],
                    end: () => {
                        this.selectAll(this.pageInfo.pageNum,this.pageInfo.pageSize);
                    }
                })
            }).catch(function (error) {
                console.log(error);
            })
        },
        doDelete:function (id) {
            layer.msg("是否删除？",{
                time:0,//不自动关闭
                btn:['是','否'],
                yes:index => {
                    axios({
                        url: 'manager/app/doDelete',
                        params:{id:id}
                    }).then(response =>{
                        layer.msg(response.data.msg);
                        layer.close(index);//关闭窗口

                        //删除后刷新
                        if (response.data.success){
                            this.selectAll(this.pageInfo.pageNum,this.pageInfo.pageSize)
                        }

                    }).catch(function (error) {
                        console.log(error);
                    })
                }
            })
        }
    },

    created:function (){
        //在vue创建后调用函数返回数据
        this.selectAll();
    }
});