import Vue from 'vue'
import App from '@/App.vue'
import router from '@/router.js'
import store from '@/store.js'
import Components from '@/components/UI'
import _ from 'lodash'
import SimpleVueValidation from 'simple-vue-validator'

_.forEach(_.toPairs(Components), ([name, component]) => {
  Vue.component(name, component)
})
Vue.prototype.$eventBus = new Vue()

Vue.config.productionTip = false

Vue.use(SimpleVueValidation)

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
