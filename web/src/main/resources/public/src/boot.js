var platform_browser_dynamic_1 = require('@angular/platform-browser-dynamic');
var http_1 = require('@angular/http');
var app_component_1 = require("./app/app.component");
var app_routes_1 = require("./app/app.routes");
platform_browser_dynamic_1.bootstrap(app_component_1.AppComponent, [http_1.HTTP_PROVIDERS, app_routes_1.APP_ROUTER_PROVIDERS])
    .catch(function (err) {
        return console.error(err);
    });
//# sourceMappingURL=boot.js.map