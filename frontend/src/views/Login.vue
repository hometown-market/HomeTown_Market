<template>
  <div>
    <form @submit.prevent="formSubmit" class="login-form">
      <p class="text-login">환영합니다!<br/>회원 서비스 이용을 위해<br/>로그인 해주세요</p>
      <hm-ui-user-input
        v-model="email"
        @update="(value) => { email = value }"
        :error="validation.hasError('email')"
        :error-message="validation.firstError('email')"
        placeholder="아이디 입력"
        type="text"
      ></hm-ui-user-input>
      <hm-ui-user-input
        v-model="password"
        @update="(value) => { password = value, reset() }"
          :error="validation.hasError('password')"
          :error-message="validation.firstError('password')"
        placeholder="비밀번호 입력"
        type="password"
      ></hm-ui-user-input>
      <hm-ui-text label="회원가입" @click="signup"></hm-ui-text>
      <hm-ui-button label="로그인하기" color="primary" size="w100" @click="onSubmit"></hm-ui-button>
    </form>
  </div>
</template>

<script>
import Vue from 'vue'
import SimpleVueValidator from 'simple-vue-validator'
const Validator = SimpleVueValidator.Validator

Vue.use(SimpleVueValidator)

export default {
  data () {
    return {
      email: '',
      password: '',
      error: {
        hasError: false,
        message: undefined
      }
    }
  },
  // computed: {
  //   emailErrorMessage () {
  //     if (this.error.hasError) {
  //       return this.error.message
  //     } else {
  //       return this.validation.firstError('email')
  //     }
  //   }
  // },
  validators: {
    email: function (value) {
      return Validator.value(value).required('E-mail을 입력해 주세요.').email('이메일 형식으로 작성해주세요.')
    },
    password: function (value) {
      return Validator.value(value).required('비밀번호를 입력해 주세요.').minLength(8, '8자리 이상 입력해 주세요.').maxLength(16, '16자리 이하로 입력해 주세요.')
    }
  },
  methods: {
    reset () {
      this.error.hasError = false
      this.error.message = undefined
    },
    async onSubmit () {
      const success = await this.$validate()
      const loginObj = {
        email: this.email,
        password: this.password
      }
      if (success) {
        this.$store.dispatch('login', loginObj)
      }
    },
    signup () {
      this.$router.push('/signup')
    }
  }
}
</script>

<style lang="scss">
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
  }
}
</style>
