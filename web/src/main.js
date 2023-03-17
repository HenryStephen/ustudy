import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ViewUI from 'view-design'
import VueRouter from 'vue-router'
import config from '@/config'
import 'view-design/dist/styles/iview.css'
import axios from 'axios'

Vue.use(VueRouter)
Vue.use(ViewUI)
Vue.prototype.$config = config
Vue.prototype.$axios = axios
Vue.config.productionTip = false

router.beforeEach((to, from, next) => {
  // 路由发生变化修改页面title
  if (to.meta.title) {
    document.title = to.meta.title
  }
  next()
})

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
