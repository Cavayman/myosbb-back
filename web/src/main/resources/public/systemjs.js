/**
 * System configuration
 * Adjust as necessary for your application needs.
 */
(function (global) {
    // map tells the System loader where to look for things
    var map = {
        'bin': 'bin', // 'dist',
        'moment': 'node_modules/moment',
        '@angular': 'node_modules/@angular',
        'angular2-in-memory-web-api': 'node_modules/angular2-in-memory-web-api',
        'rxjs': 'node_modules/rxjs',
		'angular2-jwt': 'node_modules/angular2-jwt',      
	   'angular2-toaster': 'node_modules/angular2-toaster',
	   'angular2-text-mask': 'node_modules/angular2-text-mask/dist',
        'ng2-bs3-modal': 'node_modules/ng2-bs3-modal',
        'ng2-bootstrap': 'node_modules/ng2-bootstrap',
        'ng2-file-upload': 'node_modules/ng2-file-upload',
        'ng2-translate': 'node_modules/ng2-translate',
        'ng2-bs-dropdown': 'node_modules/ng2-bs-dropdown',
        'ng2-datetime-picker': 'node_modules/ng2-datetime-picker',
        'ng2-select': 'node_modules/ng2-select'
    };
    // packages tells the System loader how to load when no filename and/or no extension
    var packages = {
        'bin': {main: 'boot.js', defaultExtension: 'js'},
        'rxjs': {defaultExtension: 'js'},
        'angular2-in-memory-web-api': {main: 'index.js', defaultExtension: 'js'},
        'angular2-toaster': {main: 'angular2-toaster.js', defaultExtension: 'js'},
		       'angular2-text-mask': {main: 'angular2TextMask.js', defaultExtension: 'js'},
		 'angular2-jwt': {main: 'angular2-jwt.js', defaultExtension: 'js'},
        'ng2-bs3-modal': {main: 'ng2-bs3-modal.js', defaultExtension: 'js'},
        'moment': {main: 'moment.js', defaultExtension: 'js'},
        'ng2-bootstrap': {main: 'ng2-bootstrap.js', defaultExtension: 'js'},
        'ng2-file-upload': {main: 'ng2-file-upload.js', defaultExtension: 'js'},
        'ng2-translate': {main: 'ng2-translate.js', defaultExtension: 'js'},
        'ng2-bs-dropdown': {defaultExtension: 'js'},
        'ng2-select': {main: 'ng2-select.js', defaultExtension: 'js'},
        'ng2-datetime-picker': {main: 'dist/index.js', defaultExtension: 'js'}
    };


    var ngPackageNames = [
        'common',
        'compiler',
        'core',
        'forms',
        'http',
        'platform-browser',
        'platform-browser-dynamic',
        'router',
        'router-deprecated',
        'upgrade'
    ];
    // Individual files (~300 requests):
    function packIndex(pkgName) {
        packages['@angular/' + pkgName] = {main: 'index.js', defaultExtension: 'js'};
    }

    // Bundled (~40 requests):
    function packUmd(pkgName) {
        packages['@angular/' + pkgName] = {main: '/bundles/' + pkgName + '.umd.js', defaultExtension: 'js'};
    }

    // Most environments should use UMD; some (Karma) need the individual index files
    var setPackageConfig = System.packageWithIndex ? packIndex : packUmd;
    // Add package entries for angular packages
    ngPackageNames.forEach(setPackageConfig);
    var config = {
        map: map,
        packages: packages
    };
    System.config(config);
})(this);