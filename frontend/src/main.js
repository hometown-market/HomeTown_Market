import Vue from 'vue'
import App from '@/App.vue'
import router from '@/router.js'
import store from '@/store.js'
import Components from '@/components/UI'
import _ from 'lodash'

_.forEach(_.toPairs(Components), ([name, component]) => {
  Vue.component(name, component)
})
Vue.prototype.$eventBus = new Vue()

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
