var ve = new Vue({
    el: '#main-container',
    data: {
        obj:{},
        wastes:[],
        office:{
            updateWastes:[]
        }

    },
    methods: {
        update: function () {

        },
        selectWaste:function(e,param){
            console.log(e.target);//事件源
            console.log(param.selected);//当前选中的选项的value值
            if(param) {
                axios({
                    url: "manager/waste/selectWaste",

                    params: {selected: param.selected}
                }).then(response => {
                    console.log(response.data);
                    //根据当前返回数据，更新vue中的wastes数据
                    this.wastes = response.data; //vue自动更新网页节点
                    //更新chosen节点信息
                    $("#waste").trigger("chosen:updated");


                }).catch(function (error) {
                    console.log(error)
                })
            }
        }

    },

    created:function () {
        this.obj=parent.layer.obj;
    },
    mounted:function () {
        $("#wasteType").chosen({width:'100%'});
        $("#waste").chosen({width:'100%',disable_search:true});
        $("#chosen-select").chosen({width:'100%'});
        $("#wasteType").on("change",this.selectWaste);
    }
});
    