import Header from "./header";
import Layout from "./layout";
import Footer from "./footer";
import CardBase from "./cardBase";
import Count from "./count";
import Table from './table';
import Search from './search';
import ButtonContainer from "./button-container";
const components = [
    Footer,Header,Layout, CardBase, Count,Table, Search,ButtonContainer
];

const install = function (Vue) {
    if (install.installed) return
    components.map(component => Vue.component(component.name, component))
}

export default {
    install,
    Footer,Header,Layout, CardBase, Count,Table, Search,ButtonContainer
}
