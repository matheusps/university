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