<template>
  <div class='page-wrapper'>
    <Header />
    <div class='page-container'>
      <router-view></router-view>
    </div>
    <Navbar v-if="isNavbar"/>
    <LoginModal v-if="isLoginModal" @close-modal="isLoginModal = false">
    </LoginModal>
  </div>
</template>
<script>
import Header from '@/components/commons/Header/index.vue'
import Navbar from '@/components/commons/Navbar/index.vue'
import LoginModal from '@/components/commons/LoginModal/index.vue'

export default {
  name: 'PageContainer',
  components: {
    Header,
    Navbar,
    LoginModal
  },
  created () {
    this.$eventBus.$on('showLoginModal', () => {
      this.isLoginModal = true
    })
    this.$eventBus.$on('toggleNavbar', (value) => {
      this.isNavbar = value
    })
  },
  data () {
    return {
      isLoginModal: false,
      isNavbar: false
    }
  }
}
</script>
