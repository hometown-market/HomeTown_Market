<template>
  <header>
    <hm-ui-icon name="icon-bars-bk" @icon-click="onClickMenuIcon"></hm-ui-icon>
    <div><router-link to="/"><img src="@/assets/logo.svg"/></router-link></div>
    <div>
      <button class="login" @click="onClickLogin">로그인</button>
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
    }
  },
  methods: {
    onClickLogin () {
      this.$eventBus.$emit('showLoginModal')
    },
    onClickMenuIcon () {
      this.$eventBus.$emit('toggleNavbar', true)
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
