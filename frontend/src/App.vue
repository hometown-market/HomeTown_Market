<template>
  <div class="container">
    hello world
    <LoginModal v-if="isLoginModal" @close-modal="isLoginModal = false">
    </LoginModal>
    <button @click="isLoginModal = true">로그인</button>
    <router-link to="/login">test</router-link>
    <router-view/>
  </div>
</template>

<script>
import LoginModal from '@/components/LoginModal'

export default {
  components: {
    LoginModal
  },
  data () {
    return {
      isLoginModal: false
    }
  }
}
</script>

<style src="./css/common.css">

</style>
