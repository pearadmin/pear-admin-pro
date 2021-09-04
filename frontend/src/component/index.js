import Header from "./header";
import Layout from "./layout";
import Footer from "./footer";
import CardBase from "./cardBase";
import Count from "./count";
import Table from './table';
import Query from './query';
import Authority from './authority';
import iconPicker from './iconPicker';
import icon from './icon';
import Dictionary from './dictionary';

const components = [
    Footer,Header,Layout,CardBase,Count,Table,Query,Authority,iconPicker,icon,Dictionary
];

const install = function (Vue) {
    if (install.installed) return
    components.map(component => Vue.component(component.name, component))
}

export default {
    install,
    Footer,Header,Layout, CardBase, Count,Table,Query,Authority,iconPicker,icon,Dictionary
}