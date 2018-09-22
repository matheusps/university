// Declare app level module which depends on views, and components
angular.module('app', ['ui.router', 'restangular', 'data-table']);
angular.module('app').config(['$stateProvider', function($stateProvider) {
        $stateProvider

            .state('allTasks', {
                url:'/allTasks',
                templateUrl: 'app/components/all-tasks-cmp/all-tasks.html',
                controller: 'all-tasks.ctrl'
            })

            .state('taskList', {
                url:'/taskList/:taskListID',
                templateUrl: 'app/components/task-list-cmp/task-list.html',
                controller: 'task-list.ctrl'
            })

            .state('categories', {
                url:'/categories',
                templateUrl: 'app/components/categories-cmp/categories.html',
                controller: 'categories.ctrl'
            })

            .state('tasklists', {
                url:'/tasklists',
                templateUrl: 'app/components/task-list-list-cmp/task-list-list.html',
                controller: 'task-list-list.ctrl'
            })

            .state('about', {
                url:'/about',
                templateUrl: 'app/components/about-cmp/about.html'
            })

    }]);
angular.module('app').config(function(RestangularProvider) {
    RestangularProvider.setBaseUrl('https://lab-04-todolist-si1.herokuapp.com/');
});
angular.module('app').component('sidenav', {

    templateUrl: 'app/components/shared/sidenav-cmp/sidenav.html',

    controller: function () {
        this.text = 'Sidenav';
    }
})
angular.module('app').component('navbar', {

    templateUrl: 'app/components/shared/navbar-cmp/navbar.html',

    controller: function () {
        this.title = 'Navbar';
    }
})
angular.module('app').controller('categories.ctrl', function ($scope, Restangular) {

    var database = Restangular.all('category');

    // --- Scope visible variables ---
    $scope.pageTitle = "Categories";
    $scope.allCategories = [];
    $scope.newCategory = {};
    $scope.currentCategory = {};
    $scope.options = {
        rowHeight: 90,
        headerHeight: 50,
        footerHeight: false,
        scrollbarV: false,
        columnMode: "force"
    };

    // ----- OnInit function -----
    getCategories();
    // ---------------------------


    // --- Scope visible functions ---
    $scope.createCategory = function(){
        saveCategory($scope.newCategory);
    };

    $scope.setAsCurrent = function(category) {
        $scope.currentCategory = category;
    };

    $scope.editCategory = function () {
        database.customPUT({
            "id": $scope.currentCategory.id,
            "title": $scope.currentCategory.title
        });
    };

    $scope.deleteCategory = function(id){
        deleteCategory(id);
    };

    $scope.deleteAllCategories = function () {
        var isConfirmed = confirm('Are you sure?');
        if(isConfirmed){
            database.remove().then(function () {
                console.log("ALL CATEGORIES REMOVED");
                getCategories();
            })
        }else{
            console.log("REFUSED");
        }
    };

    $scope.refreshCategories = function () {
        getCategories();
    };


    // --- Help functions ---
    function resetCategory() {
        $scope.newCategory = {};
    };


    // --- Database Operations ---
    function getCategories() {
        $scope.allCategories = database.getList().$object;
    };

    function saveCategory(newCategory) {
        database.post(newCategory).then(function () {
            getCategories();
            resetCategory();
        });
    };

    function deleteCategory(id) {
        database.customDELETE(id).then(function () {
            getCategories();
        });
    };

});
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
        pdfMake.createPdf(docDefinition).open();
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
angular.module('app').directive('sidebarToggle', [function() {
	return {
		restrict: 'A',
		link: function (scope, iElement, iAttrs) {

			scope.element = iElement[0];
			scope.body = document.body;

			scope.element.addEventListener("click", function() {
				var _element = document.getElementById(iAttrs.sidebarToggle);
				_element.classList.toggle("sidebar--is-open");

				var _bgSidebar = document.getElementById(iAttrs.sidebarToggle + "-bg");
				_bgSidebar.classList.toggle("sidebar-bg--is-visible");

				//control body
				if (scope.body.style.overflowY == "auto") {
					scope.body.style.overflowY = "hidden";
				} else {
					scope.body.style.overflowY = "auto";
				}
			});

		}
	};
}]);

angular.module('app').directive('sidebar', ['$compile', function($compile) {
	return {
		restrict: 'E',
		link: function (scope, iElement, iAttrs) {

			scope.body = document.body;
			scope.element = iElement[0];

			//renderize sidebar
			scope.element.style.display = "block";

			//add width on sidebar
			function isNumber(n) {
				return !isNaN(parseFloat(n)) && isFinite(n);
			}

			if (isNumber(iAttrs.size)) {
				scope.element.style.maxWidth = iAttrs.size + 'px';
			} else {
				scope.element.style.maxWidth = iAttrs.size;
			}

			//add class to position on sidebar
			scope.element.classList.add("sidebar--" + iAttrs.position);

			//renderize bg-sidebar
			var bgSidebar = document.createElement("div");
			bgSidebar.setAttribute("class", "sidebar-bg");
			bgSidebar.setAttribute("id", iAttrs.id + "-bg");
			bgSidebar.setAttribute("sidebar-toggle", iAttrs.id);
			scope.body.appendChild(bgSidebar);

			$compile(bgSidebar)(scope);

		}
	};
}]);