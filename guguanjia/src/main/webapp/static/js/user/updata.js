var ve = new Vue({
    el: '#main-container',
    data: {
        pageInfo: {
            pageNum: 1,
            pageSize: 5
        },
        params: {
            pageNum: '',
            pageSize: '',
            type: '',
            check: ''
        }
    },
    methods: {
        selectAll: function (pageNum, pageSize) {

        },
        toUpdate: function (id) {

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
    