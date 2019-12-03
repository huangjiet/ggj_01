var vm = new Vue({
    el: '#main-container',
    data: {
        obj: {

        }
    },
    methods: {

        update:function(check){
            this.obj.check = check;//修改审核状态
            axios({
                url:"manager/qualification/update",
                method: "post",
                data:this.obj
            }).then(response => {
                // console.log(response.data);
                parent.layer.msg(response.data.msg);//通过父窗口layer对象弹框

                // console.log('关闭当前窗口....');
                let index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                parent.layer.close(index); //再执行关闭
            }).catch(function(error){console.log(error)})

        }
    },
    created:function (){
        //在vue对象创建后   获取layer父窗口传递的对象数据
        this.obj=parent.layer.obj;
    }

});