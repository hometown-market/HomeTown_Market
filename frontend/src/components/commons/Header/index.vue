<template>
  <header>
    <hm-ui-icon name="icon-bars-bk" @icon-click="onClickMenuIcon"></hm-ui-icon>
    <div><router-link to="/"><img src="@/assets/logo/logo-main.svg"/></router-link></div>
    <div v-if="loggedIn === false">
      <button class="login" @click="login">로그인</button>
    </div>
    <div v-else style="display: flex">
      <hm-ui-icon name="icon-user-bk" @icon-click="onMyshop"></hm-ui-icon>
      <button class="login" @click="logout">로그아웃</button>
    </div>
  </header>
</template>

<script>
export default {
  name: 'Header',
  data () {
    return {
      loggedIn: false
    }
  },
  watch: {
    '$route.query': {
      deep: true,
      handler (val) {
        if (val.login) {
          this.checkIsLogin()
        }
      }
    }
  },
  created () {
    this.checkIsLogin()
  },
  methods: {
    login () {
      this.$eventBus.$emit('showLoginModal')
    },
    onClickMenuIcon () {
      this.$eventBus.$emit('toggleNavbar', true)
    },
    logout () {
      this.$store.dispatch('logout')
      this.$router.go()
    },
    onMyshop () {
      this.$router.push('/shop')
    },
    checkIsLogin () {
      if (localStorage.getItem('access_token') !== null) {
        this.loggedIn = true
      } else {
        this.loggedIn = false
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
