import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import * as axios from 'axios'

createApp.prototype.$axios = axios

createApp(App).use(store).use(router).use(axios).mount('#app')
