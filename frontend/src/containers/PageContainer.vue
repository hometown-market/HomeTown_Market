<template>
  <div class='page-wrapper'>
    <Header />
    <div class='page-container'>
      <router-view></router-view>
    </div>
  </div>
</template>
<script>
import Header from '@/components/commons/Header/index.vue'

export default {
  name: 'PageContainer',
  components: {
    Header
  },
  data () {
  }
}
</script>
