var gulp            = require('gulp');
var sass            = require('gulp-sass');
var minifyCss       = require('gulp-minify-css');
var rename          = require('gulp-rename');
var bulkSass        = require('gulp-sass-bulk-import');

var clean 			= require('gulp-clean');
var concat 			= require('gulp-concat');
var ngdocs 			= require('gulp-ngdocs');
var uglify 			= require('gulp-uglify');
var runSequence 	= require('run-sequence');

var depsJS 			= [ 'node_modules/pdfmake/build/pdfmake.js',
                        'node_modules/pdfmake/build/vfs_fonts.js',
                        'node_modules/lodash/lodash.js',
                        'node_modules/restangular/dist/restangular.js',
                        'node_modules/angular-ui-router/release/angular-ui-router.js'];

var staticCSSLibs   = [ 'app/assets/css/main.css',
                        'app/assets/css/libs/demo.css',
                        'app/assets/css/libs/bootstrap.min.css',
                        'app/assets/css/libs/animate.min.css',
                        'app/assets/css/libs/light-bootstrap-dashboard.css',
                        'app/assets/css/libs/sidebar.css'];

var staticJSLibs    = [ 'app/assets/js/jquery-1.10.2.js',
                        'app/assets/js/bootstrap.min.js',
                        'app/assets/js/bootstrap-checkbox-radio-switch.js',
                        'app/assets/js/light-bootstrap-dashboard.js',
                        'app/assets/js/light-bootstrap-dashboard.js',
                        'app/assets/js/demo.js'];

var appJS           =[  'app/app.js',
                        'app/config/routes.config.js',
                        'app/config/restangular.config.js',
                        'app/components/shared/sidenav-cmp/sidenav.js',
                        'app/components/shared/navbar-cmp/navbar.js',
                        'app/components/categories-cmp/categories.ctrl.js',
                        'app/components/task-list-list-cmp/task-list-list.ctrl.js',
                        'app/components/task-list-cmp/task-list.ctrl.js',
                        'app/components/all-tasks-cmp/all-tasks.ctrl.js',
                        'app/components/task-list-cmp/sidebar.directive.js'];


gulp.task('buildDeps', function ()
{
    var depsjs = gulp.src(depsJS);
    return depsjs.pipe(concat('todolist_deps.js'))
        .pipe(gulp.dest('src'));
});

gulp.task('buildCSS', function ()
{
    var staticcss = gulp.src(staticCSSLibs);
    return staticcss.pipe(concat('todolist_static.css'))
        .pipe(gulp.dest('src'));
});

gulp.task('buildJS', function () {
    var staticjs = gulp.src(staticJSLibs);
    return staticjs.pipe(concat('todolist_static.js'))
        .pipe(gulp.dest('src'));

});

gulp.task('buildApp', function ()
{
    var js = gulp.src(appJS);
    return js.pipe(concat('todolist.js'))
        .pipe(gulp.dest('src'));
});

var paths = {
    sass: ['./app/**/*.scss']
};

gulp.task('default', ['sass', 'buildDeps', 'buildCSS', 'buildJS', 'buildApp']);

gulp.task('sass', function(done) {
    gulp.src('./app/assets/sass/main.scss')
        .pipe(bulkSass())
        .pipe(sass())
        .on('error', sass.logError)
        .pipe(gulp.dest('./app/assets/css/'))
        .pipe(minifyCss({
            keepSpecialComments: 0
        }))
        .pipe(rename({ extname: '.min.css' }))
        .pipe(gulp.dest('./app/assets/css/release/'))
        .on('end', done);
});

gulp.task('watch-sass', function() {
    gulp.watch(paths.sass, ['sass']);
});