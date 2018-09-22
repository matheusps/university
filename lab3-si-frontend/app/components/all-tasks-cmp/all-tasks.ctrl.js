angular.module('app').controller('all-tasks.ctrl', function ($scope, Restangular) {

    var taskDatabase = Restangular.all('task');
    var categoriesDatabase = Restangular.all('category');

    // --- Scope Visible variables ---
    $scope.pageTitle = "All Tasks";
    $scope.allTasks = {};
    $scope.allCategories = {};

    // ----- OnInit function -----
    getTasks();
    getCategories();
    // ---------------------------


    //--- Scope visible functions ---

    $scope.updateTask = function (task) {
        console.log(task);

        if(task.done){
            task.done = false;
        }else{
            task.done = true;
        }

        taskDatabase.customPUT(task).then(function () {
            getTasks();
        });
    };

    $scope.deleteAllTasks = function () {
        var isConfirmed = confirm('Are you sure?');
        if(isConfirmed){
            taskDatabase.remove().then(function () {
                console.log("ALL TASKS REMOVED");
                getTasks();
            })
        }else{
            console.log("REFUSED");
        }
    };

    $scope.getNumberOfDoneTasks= function (filteredTasks) {
        var count = 0;

        filteredTasks.forEach(function (task) {
            if(task.done){
                count = count + 1;
            }
        });

        return count;
    };

    // --- Help functions ---

    // --- Database operations --
    function getTasks() {
        $scope.allTasks = taskDatabase.getList().$object;
    }

    function getCategories() {
        $scope.allCategories = categoriesDatabase.getList().$object;
    }

});