<template>
  <div class="container">
    <form @submit.prevent="formSubmit" class="login-form">
      <p class="text-login">환영합니다!<br/>회원 서비스 이용을 위해<br/>로그인 해주세요</p>
      <input v-model="id" class="input-login" type='text' placeholder="아이디">
      <input v-model="password" class="input-login" type='password' placeholder="비밀번호">
      <div class="link-signup" v-on:click="signup">회원가입</div>
      <button class="btn-login" type="submit">로그인하기</button>
    </form>
  </div>
</template>

<script>
export default {
  data () {
    return {
      id: '',
      password: ''
    }
  },
  methods: {
    async formSubmit (event) {
      if (event) {
        if (this.id === '' || this.password === '') {
          alert('아이디와 비밀번호를 입력해 주세요')
        } else if (this.id !== '' && this.password !== '') {
          alert('통과')
          // vuex의 action에 등록되어있는 axios 비동기 통신연결
        }
      }
    },
    signup () {
      this.$router.push('/signup')
    }
  }
}
</script>

<style lang="scss" scope>
.container {
  .login-form {
    display: flex;
    flex-direction: column;
    padding: 0 73px;
    .text-login {
      margin-top: 25%;
      font-weight: 400;
      font-size: 17px;
      line-height: 23.07px;
      color: #000000;
    }
    .input-login {
      border-left: none;
      border-right: none;
      border-top: none;
      border-bottom: 1px solid;
      font-size: 15px;
      color: #C8C8C8;
      margin-top: 31px;
      outline: none;
    }
    .link-signup {
      color: #C8C8C8;
      font-size: 12px;
      line-height: 14px;
      font-weight: bold;
      display: flex;
      justify-content: flex-end;
      margin-top: 5px;
    }
    .btn-login {
      display: flex;
      margin: 100px auto 0 auto;
      justify-content: space-around;
      align-items: center;
      color: #ffffff;
      background-color: #FED07A;
      width: 100%;
      height: 59px;
      border-radius: 20px;
      font-size: 20px;
      font-weight: 700;
      border: none;
    }
  }
}
</style>
