<template>
  <div>
    <form @submit.prevent="formSubmit" class="signup-form">
      <div v-if="step === 0">
        <p class="text-signup">아이디를 <br/>입력해주세요.</p>
        <hm-ui-user-input
          v-model="email"
          @update="(value) => { email = value, reset() }"
          :error="validation.hasError('email') || error.hasError"
          :error-message="emailErrorMessage"
          placeholder="아이디 입력"
          type="text"
        ></hm-ui-user-input>
        <hm-ui-button label="계속하기" color="primary" size="w100" @click="onSubmit0"></hm-ui-button>
      </div>
      <div v-if="step === 1">
        <p class="text-signup">비밀번호를 <br/>입력해주세요.</p>
        <hm-ui-user-input
          v-model="password"
          @update="(value) => password = value"
          :error="validation.hasError('password')"
          :error-message="validation.firstError('password')"
          placeholder="비밀번호 입력"
          type="password"
        ></hm-ui-user-input>
        <hm-ui-user-input
          v-model="checkpassword"
          @update="(value) => checkpassword = value"
          :error="validation.hasError('checkpassword')"
          :error-message="validation.firstError('checkpassword')"
          placeholder="비밀번호 확인"
          type="password"
        ></hm-ui-user-input>
        <hm-ui-button label="계속하기" color="primary" size="w100" @click="onSubmit1"></hm-ui-button>
      </div>
      <div v-if="step === 2">
        <!-- 추후 디자인 변경 -->
        <p class="text-signup">전화번호를 <br/>입력해주세요.</p>
        <hm-ui-user-input
          v-model="phone"
          @update="(value) => phone = value"
          :error="validation.hasError('phone')"
          :error-message="validation.firstError('phone')"
          placeholder="전화번호 입력"
          type="number"
        ></hm-ui-user-input>
        <hm-ui-button label="계속하기" color="primary" size="w100" @click="onSubmit2"></hm-ui-button>
      </div>
      <div v-if="step === 3">
        <p class="text-signup">주소를 <br/>입력해주세요.</p>
        <hm-ui-user-input
          v-model="address"
          @update="(value) => address = value"
          :error="validation.hasError('address')"
          :error-message="validation.firstError('address')"
          placeholder="주소 입력"
          type="text"
        ></hm-ui-user-input>
        <hm-ui-button label="계속하기" color="primary" size="w100" @click="onSubmit3"></hm-ui-button>
      </div>
      <div v-if="step === 4">
        <p class="text-signup">우리동네 <br/>설정이 완료되었어요!</p>
        <hm-ui-user-input
          :placeholder= address
          type="text"
          readonly
        >{{this.address}}</hm-ui-user-input>
        <hm-ui-button label="회원가입 완료" color="primary" size="w100" @click="onSubmit4"></hm-ui-button>
      </div>
    </form>
  </div>
</template>

<script>
import Vue from 'vue'
import SimpleVueValidator from 'simple-vue-validator'
import { Rest, RestUrl } from '@/modules/Rest.js'
const Validator = SimpleVueValidator.Validator

Vue.use(SimpleVueValidator)

export default {
  data () {
    return {
      step: 0,
      email: '',
      password: '',
      checkpassword: '',
      phone: '',
      address: '',
      state: false,
      error: {
        hasError: false,
        message: undefined
      },
      submitted: false
    }
  },
  computed: {
    emailErrorMessage () {
      if (this.error.hasError) {
        return this.error.message
      } else {
        return this.validation.firstError('email')
      }
    }
  },
  validators: {
    email: function (value) {
      return Validator.value(value).required('E-mail 주소를 입력해 주세요.').email('이메일 형식으로 작성해주세요.')
    },
    password: function (value) {
      return Validator.value(value).required('비밀번호를 입력해 주세요.').minLength(8, '8자리 이상 입력해 주세요.').maxLength(16, '16자리 이하로 입력해 주세요.')
    },
    'checkpassword, password': function (checkpassword, password) {
      if (this.submitted || this.validation.isTouched('checkpassword')) {
        return Validator.value(checkpassword).required('비밀번호를 입력해 주세요.').match(password, '비밀번호가 일치하지 않습니다.')
      }
    },
    phone: function (value) {
      return Validator.value(value).required('전화번호를 입력해 주세요.').minLength(9, '- 없이 입력해 주세요.').maxLength(11, '알맞은 전화번호를 입력해 주세요.')
    },
    address: function (value) {
      return Validator.value(value).required('주소를 입력해 주세요.').minLength(3, 'ex) 서초동')
    }
  },
  methods: {
    reset () {
      this.error.hasError = false
      this.error.message = undefined
    },
    async onSubmit0 () {
      const success = await this.$validate('email')
      const response = await Rest.post(RestUrl.emailCheck, this.email)
      if (success) {
        response
          .then((res) => {
            if (res.data.status === true) {
              this.step = (this.step + 1)
            } else if (res.data.status === false) {
              this.error.hasError = true
              this.error.message = '사용 불가능한 이메일 입니다.'
            }
          })
          .catch((err) => {
            alert(err)
            // 해당 내용은 api 테스트 완료 이후 제거
            this.error.hasError = true
            this.error.message = '사용 불가능한 이메일 입니다.'
            this.step = (this.step + 1)
          })
      }
    },
    async onSubmit1 () {
      const success = await this.$validate('password', 'checkpassword, password')
      if (success) {
        this.step = (this.step + 1)
      }
    },
    async onSubmit2 () {
      const success = await this.$validate('phone')
      if (success) {
        this.step = (this.step + 1)
      }
    },
    async onSubmit3 () {
      const success = await this.$validate('address')
      if (success) {
        this.step = (this.step + 1)
      }
    },
    async onSubmit4 () {
      const success = await this.$validate()
      const signupObj = {
        email: this.email,
        password: this.password,
        checkpassword: this.checkpassword,
        phone: this.phone,
        address: this.address
      }
      if (success) {
        this.$store.dispatch('signup', signupObj)
      }
    }
  }
}
</script>

<style lang="scss">
.signup-form {
  display: flex;
  flex-direction: column;
  padding: 0 73px;
  .text-signup {
    margin-top: 25%;
    font-weight: 400;
    font-size: 17px;
    line-height: 23.07px;
    color: #000000;
  }
  .hm-ui-button {
    margin: 100px 0 0 0;
  }
}
</style>
