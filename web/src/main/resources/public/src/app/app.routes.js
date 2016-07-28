var router_1 = require('@angular/router');
var login_component_1 = require("./login/login.component");
exports.routes = [
    {path: '/login', component: login_component_1.LoginComponent, useAsDefault: true}
];
exports.APP_ROUTER_PROVIDERS = [
    router_1.provideRouter(exports.routes)
];
//# sourceMappingURL=app.routes.js.map