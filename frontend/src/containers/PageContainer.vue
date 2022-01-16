<template>
  <div class='page-wrapper'>
    <Header />
    <div class='page-container'>
      <router-view></router-view>
    </div>
    <LoginModal v-if="isLoginModal" @close-modal="isLoginModal = false">
    </LoginModal>
  </div>
</template>
<script>
import Header from '@/components/commons/Header/index.vue'
import LoginModal from '@/components/LoginModal'

export default {
  name: 'PageContainer',
  components: {
    Header,
    LoginModal
  },
  created () {
    this.$eventBus.$on('showLoginModal', () => {
      this.isLoginModal = true
    })
  },
  data () {
    return {
      isLoginModal: false
    }
  }
}
</script>
