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