import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router';
import NavBar from './componets/Navbar.vue'
import PriceList from './componets/PriceList.vue'
import Cart from './componets/Cart.vue'


Vue.use(VueRouter);
Vue.component('app-nav',NavBar);
// Vue.component('app-list',PriceList);
// // Vue.component('app-cart',Cart);

const routes = [


  {path:'/cart' , component:Cart},
  {path:'/' , component:PriceList},

];
const router = new VueRouter({
  routes,
  mode:'history'

});
new Vue({
  el: '#app',
  router,
  render: h => h(App)
})
