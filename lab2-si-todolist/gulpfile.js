var gulp = require('gulp');
var gutil = require('gulp-util');
var sass = require('gulp-sass');
var minifyCss = require('gulp-minify-css');
var rename = require('gulp-rename');
var bulkSass = require('gulp-sass-bulk-import');

var paths = {
  sass: ['./app/**/*.scss']
};

gulp.task('default', ['sass']);

gulp.task('sass', function(done) {
  gulp.src('./app/assets/modular-css/main.scss')
    .pipe(bulkSass())
    .pipe(sass())
    .on('error', sass.logError)
    .pipe(gulp.dest('./dist/assets/css/'))
    .pipe(minifyCss({
      keepSpecialComments: 0
    }))
    .pipe(rename({ extname: '.min.css' }))
    .pipe(gulp.dest('./dist/assets/css/'))
    .on('end', done);
});

gulp.task('watch-sass', function() {
  gulp.watch(paths.sass, ['sass']);
});
