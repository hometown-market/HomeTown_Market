<template>
  <header>
    <div><img src="@/assets/icons/icon-bars-bk.svg"/></div>
    <div><router-link to="/"><img src="@/assets/logo.svg"/></router-link></div>
    <div>
      <LoginModal v-if="isLoginModal" @close-modal="isLoginModal = false">
      </LoginModal>
      <button class="login" @click="isLoginModal = true">로그인</button>
    </div>
  </header>
</template>

<script>
import LoginModal from '@/components/LoginModal'

export default {
  name: 'Header',
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

<style lang="scss" scoped>
header {
  display: flex;
  padding: 20px;
  justify-content: space-between;
  .login {
    all: unset;
    padding: 7px 0 0 0;
    color: #B4B4B4;
    font-weight: bold;
  }
}

</style>
