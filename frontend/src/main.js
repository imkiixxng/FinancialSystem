import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import LikeListPage from './views/LikeListPage.vue'

const app = createApp(LikeListPage)
app.use(ElementPlus)
app.mount('#app')