angular.module('app').controller('task-list.ctrl', function ($scope, Restangular, $stateParams) {

    var recivedID = $stateParams.taskListID;
    var taskList = Restangular.one('taskList', recivedID);
    var taskDatabase = Restangular.all('task');
    var categoriesDatabase = Restangular.all('category');
    var subtaskDatabase = Restangular.all('subtask');

    // --- Scope Visible variables ---
    $scope.pageTitle = "Task";
    $scope.allCategories = [];
    $scope.allTasks = [];
    $scope.taskList = {};
    $scope.newTask = {};
    $scope.newCategory = {};
    $scope.currentTask = {};
    $scope.newSubtask = {};

    // ----- OnInit function -----
    getCategories();
    getTaskList();
    // ---------------------------


    //--- Scope visible functions ---
    $scope.setAsCurrent = function (task) {
      $scope.currentTask = task;
    };

    $scope.createTask = function () {
        var category = {
            "id": $scope.newTask.category
        };

        var taskList = {
            "id": recivedID
        };

        $scope.newTask.category = category;
        $scope.newTask.taskList = taskList;
        $scope.newTask.isDone = false;


        saveTask($scope.newTask);

    };

    $scope.updateTask = function (task) {
        console.log(task);

        if(task.done){
            task.done = false;
        }else{
            task.done = true;
        }

        task.taskList = {
            "id": recivedID
        };

        taskDatabase.customPUT(task).then(function () {
            getTaskList();
        });
    };

    $scope.editTask = function () {
        console.log($scope.currentTask.category);
        taskDatabase.customPUT(
            {
                "id": $scope.currentTask.id,
                "title": $scope.currentTask.title,
                "description": $scope.currentTask.description,
                "done": $scope.currentTask.done,

                "priority": $scope.currentTask.priority,
                "category":{
                    "id": $scope.currentTask.category
                },
                "taskList": {
                    "id": recivedID
                }
            }
        ).then(function () {
            getTaskList();
            console.log("EDITED");
            $scope.currentTask = {};
        })
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

    $scope.showCreateCategory = function(){
        if($scope.newTask.category === 'NEW' || $scope.currentTask.category === 'NEW'){
            return true;
        }else{
            return false;
        }
    };

    $scope.createCategory = function () {
        categoriesDatabase.post($scope.newCategory).then(function () {
            console.log($scope.newCategory + "::Created!");
            getCategories();
        })
    };

    $scope.deleteTask = function (id) {
        taskDatabase.customDELETE(id).then(function () {
            getTaskList();
        })
    };

    $scope.createSubtask = function () {
        $scope.newSubtask.task = {
            "id": $scope.currentTask.id
        };
        $scope.currentTask.subtasks.push($scope.newSubtask);
        subtaskDatabase.post($scope.newSubtask).then(function () {
            $scope.newSubtask = {};
            getTaskList();
        });
    };

    $scope.updateSubtask = function (subtask) {
        console.log(subtask);

        if(subtask.done){
            subtask.done = false;
        }else{
            subtask.done = true;
        }

        subtask.task = {
            "id": $scope.currentTask.id
        };

        subtaskDatabase.customPUT(subtask).then(function () {
            getTaskList();
        });
    };

    $scope.deleteSubtask = function (id, index) {
        subtaskDatabase.customDELETE(id).then(function () {
            $scope.currentTask.subtasks.splice(index, 1);
        });
    };

    // --- Help functions ---

    // --- Database operations --
    function getTaskList() {
        taskList.get().then(function (taskList) {
            $scope.taskList = taskList;
            console.log($scope.taskList);
            $scope.allTasks = $scope.taskList.tasks;
            console.log($scope.allTasks);
            $scope.pageTitle = taskList.title;
        });
    }

    function getCategories() {
        $scope.allCategories = categoriesDatabase.getList().$object;
    }
    
    function saveTask(task) {
        taskDatabase.post(task).then(function () {
            console.log(task + "::Created!");
            $scope.newTask = {}
            getTaskList();
        });
    }

    $scope.exportPDF = function () {
        console.log('called exportPDF');
        var docDefinition = { content: stringfyMyList()};
        pdfMake.createPdf(docDefinition).download();
    };
    
    function stringfyMyList() {
        var output = "Task list title: " + $scope.taskList.title + "\n"
            + "*************** TASKS ***************\n";

        for (var i = 0; i < $scope.allTasks.length; i++) {

            var task = $scope.allTasks[i];

            output += "Task title: " + task.title + "\n"
                    + "Task description: " + task.description + "\n"
                    + "Task priority: " + task.priority + "\n"
                    + "Subtasks:\n";

            for (var j = 0; j < task.subtasks.length; j++) {

                var subtask = task.subtasks[j];

                output += "-- " + subtask.title + "\n";
            }

            output += "**************************************\n";
        }

        return output;
    }



});