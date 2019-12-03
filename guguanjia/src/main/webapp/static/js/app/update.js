let vm = new Vue({
    el:'#main-container',
    data:{
        appVersion:''
    },
    methods:{
        update:function () {

            axios({
                url:"manager/app/update" ,
                method:"post",
                data:this.appVersion,
            }).then(response => {

                parent.layer.msg(response.data.msg);

                let index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
                console.log('关闭窗口');
            }).catch(function (error){
                console.log(error);
            })
        }
    },
    created:function () {
        this.appVersion = parent.layer.appVersion;
    }

});