angular.module('app').controller('task-list-list.ctrl', function ($scope, Restangular, $state) {

    var database = Restangular.all('taskList');

    // --- Scope visible variables --/
    $scope.pageTitle = "Task Lists";
    $scope.allTaskLists = [];
    $scope.newTaskList = {};
    $scope.currentTaskList = {};
    $scope.options = {
        rowHeight: 90,
        headerHeight: 50,
        footerHeight: false,
        scrollbarV: false,
        columnMode: "force"
    };

    // ----- OnInit function -----
    getTaskLists();
    // ---------------------------


    // --- Scope visible functions ---
    $scope.deleteTaskList = function(id){
        deleteTaskList(id);
    };

    $scope.gotoList = function (id) {
        $state.go('taskList', {'taskListID' : id});
    };

    $scope.deleteAllTaskLists = function () {
      var isConfirmed = confirm('Are you sure?');
      if(isConfirmed){
          database.remove().then(function () {
              console.log("ALL TASK LISTS REMOVED");
              getTaskLists();
          })
      }else{
          console.log("REFUSED");
      }
    };

    $scope.createTaskList = function(){
        saveTaskList($scope.newTaskList);
    };

    $scope.setAsCurrent = function (tasklist) {
        $scope.currentTaskList = tasklist;
    };

    $scope.editTaskList = function () {
        database.customPUT({
            "id": $scope.currentTaskList.id,
            "title": $scope.currentTaskList.title
        }).then(function () {
            getTaskLists();
        });
    };

    $scope.refreshTaskLists = function(){
        getTaskLists();
    };


    // --- Help functions ---
    function resetTaskList() {
        $scope.newTaskList = {};
    };


    // --- Database Operations ---
    function getTaskLists() {
        $scope.allTaskLists = database.getList().$object;
    };

    function saveTaskList(newTaskList) {
        database.post(newTaskList).then(function () {
            getTaskLists();
            resetTaskList();
        });
    };

    function deleteTaskList(id) {
        database.customDELETE(id).then(function () {
            getTaskLists();
        });
    };

});