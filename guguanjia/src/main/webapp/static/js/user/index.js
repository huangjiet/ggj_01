var ve = new Vue({
    el: '#main-container',
    data: {
        pageInfo: {
            pageNum: 1,
            pageSize: 5
        },
        params: {
            pageNum: '',
            pageSize: ''
        },
        user:''
    },
    methods: {
        selectAll: function (pageNum, pageSize) {
            this.params.pageNum=pageNum;
            this.params.pageSize=pageSize;
            axios({
                url:"manager/user/index",
                method:"post",
                data:this.params
            }).then( response=>{
                this.pageInfo=response.data;
            }).catch(function (error) {
                layer.msg(error);
            })
        },
        toUpdate: function (id) {
            axios({
                url:'manager/user/toUpdate',
                params: {id:id},
                //data:this.params
            }).then(response => {
                layer.user = response.data;//返回数据，绑定到layer上，传递给子窗口
                console.log(layer);
                let index = layer.open({
                    type:2,
                    title:'编辑',
                    content:'html/user/user-save.html',
                    area:['80%','80%'],
                    end: () => {//将then函数中的this传递到end的回调函数中
                        console.log(".....");
                        //刷新页面数据
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
    },
    created: function () {
        this.selectAll(this.pageInfo.pageNum, this.pageInfo.pageSize);
    }
});
    