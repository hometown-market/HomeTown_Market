<template>
  <header>
    <hm-ui-icon name="icon-bars-bk" @icon-click="onClickMenuIcon"></hm-ui-icon>
    <div><router-link to="/"><img src="@/assets/logo/logo-main.svg"/></router-link></div>
    <div v-if="login && state === false">
      <button class="login" @click="onClickLogin">로그인</button>
    </div>
    <div v-else-if="!login || status !== false" style="diplay: flex;">
      <button class="login" @click="onLogout">로그아웃</button>
      <hm-ui-icon name="icon-user-bk" @icon-click="onIconClick"></hm-ui-icon>
    </div>
  </header>
</template>

<script>
export default {
  name: 'Header',
  components: {
  },
  data () {
    return {
      state: false
    }
  },
  methods: {
    onClickLogin () {
      this.$eventBus.$emit('showLoginModal')
    },
    onClickMenuIcon () {
      this.$eventBus.$emit('toggleNavbar', true)
    },
    onLogout () {
      this.$store.dispatch('logout')
    },
    onIconClick () {
      this.$router.push('/shop')
    },
    login () {
      this.state = false
      if (localStorage.getItem('access_token') !== '') {
        this.state = true
      }
    }
  }
}
</script>

<style lang="scss" scoped>
header {
  display: flex;
  padding: 20px;
  justify-content: space-between;
  box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.1);
  .login {
    all: unset;
    padding: 7px 0 0 0;
    color: #B4B4B4;
    font-weight: bold;
  }
}

</style>
