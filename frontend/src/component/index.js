import PageHeader from "./page/header";
import PageBody from "./page/body";
import PageFooter from "./page/footer";
import CardBase from "./cardBase";
import Count from "./count";

const components = [
    PageFooter,PageHeader, PageBody, CardBase, Count
];

const install = function (Vue) {
    if (install.installed) return
    components.map(component => Vue.component(component.name, component))
}

export default {
    install,
    PageFooter, PageHeader, PageBody, CardBase, Count
}
