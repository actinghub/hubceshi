var vm = new Vue({
    el:'#teacherdiv',
    data:{
        teacherlist:[],
        teacher:{hids:[],majorlist:[]},
        titlelist:[],
        collegelist:[],
        hobbylist:[],
        majorlist:[],
        searchEntity:{},
        pageSize:5,
        pageNum:1,
        pageInfo:{}
    },
    methods:{
        changesize:function(obj){
            this.pageSize = obj;
            this.findAllConn();
        },
        paging:function(obj){
            this.pageNum = obj;
            this.findAllConn();
        },
        findAllConn:function(){
            var _this = this;
            axios.post('/teacher/findAllConn?pageNum='+_this.pageNum+"&pageSize="+_this.pageSize,_this.searchEntity).then(function (response) {
                _this.teacherlist = response.data.list;
                _this.pageInfo = response.data;
                _this.pageSize = response.data.pageSize;
                _this.pageNum = response.data.pageNum;
            });
        },
        findAll:function () {
            var _this = this;
            axios.get('/teacher/findAll').then(function (response) {
                _this.teacherlist = response.data;
            });
        },
        addTeacher:function(){
            // alert(this.teacher.tname+"=="+this.teacher.titleId+"=="+this.teacher.collegeId+"=="+this.teacher.majorId+"==="+this.teacher.hids);
            var _this = this;
            axios.post('/teacher/saveTeacher',_this.teacher).then(function (response) {
                if(response.data.flage){
                    alert(response.data.msg);
                    document.getElementById("teachersavediv").style.display="none";
                }else{
                    alert(response.data.msg);
                }
            });
        },
        toAdd:function () {
            this.getHobbyList();
            document.getElementById("teachersavediv").style.display="block";
        },
        getTitleList:function () {
            var _this = this;
            axios.get('/teacher/getTitleList').then(function (response) {
                _this.titlelist = response.data;
            });
        },
        getCollegeList:function () {
            var _this = this;
            axios.get('/teacher/getCollegeList').then(function (response) {
                _this.collegelist = response.data;
            });
        },
        changeCollege:function (collegeId) {
            var _this = this;
            axios.get('/teacher/getMajorListById?id='+collegeId).then(function (response) {
                _this.majorlist = response.data;
            });
        },
        getHobbyList:function () {
            var _this = this;
            axios.get('/teacher/getHobbyList').then(function (response) {
                _this.hobbylist = response.data;
            });
        },
        findOne:function (id) {
            var _this = this;
            axios.get('/teacher/findOne?id='+id).then(function (response) {
                _this.majorlist = response.data.majorlist;
                _this.hids = response.data.hids;
                _this.teacher = response.data;
                _this.getHobbyList();
                document.getElementById("teachersavediv").style.display="block";
            });
        }
    }
});
vm.findAllConn();
vm.getTitleList();
vm.getCollegeList();
