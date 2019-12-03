var ve = new Vue({
    el: '#main-container',
    data: {

        obj:{},

    },
    methods: {
        selectArea: function () {
            console.log(layer);
            let index = layer.open({
                type:2,
                title:'修改上级区域',
                content:'html/area/select.html',
                area:['80%','60%'],
                end: () => {//将then函数中的this传递到end的回调函数中
                    console.log(this.obj)

                }
            });
        },
        toUpdate: function (id) {

        },
        update: function () {
            console.log(this.obj);
            this.obj.parentIds='0,1,3,';
            axios({
                url:"manager/area/update",
                method:"post",
                data:this.obj
            }).then(response=>{
                let index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
                parent.layer.msg(response.data.msg);
            }).catch(function (error) {
                console.log(error)

            })
        },
        toDelete: function (id) {

        },
        deleteById: function () {

        },
        save: function () {

        },
        selectIcon:function(){
            console.log(layer)
            layer.icon = '';
            let index = layer.open({
                type:2,
                title:'区域修改',
                content:'html/modules/font-awesome.html',
                area:['80%','80%'],
                end: () => {//将then函数中的this传递到end的回调函数中
                    console.log(this.obj);
                    this.obj.icon = layer.icon;//将替换掉的icon值给vue
                }
            });
        }
    },
    created: function () {
        this.obj=parent.layer.obj;
    }
});
    