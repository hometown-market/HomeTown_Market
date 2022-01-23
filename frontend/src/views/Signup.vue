<template>
  <div>
    <form @submit.prevent="formSubmit" class="signup-form">
      <div v-if="session === 0">
        <p class="text-signup">아이디를 <br/>입력해주세요.</p>
        <hm-ui-user-input v-model="email" @update="(value) => email = value" placeholder="아이디 입력" type="text"></hm-ui-user-input>
        <!-- <input class="user-input" v-model="email" type="text" placeholder="아이디 입력"> -->
        <hm-ui-text label="E-mail 주소를 입력해 주세요." size="w100"></hm-ui-text>
        <hm-ui-button label="계속하기" color="primary" size="w100" @click="session = (this.session + 1) % 5"></hm-ui-button>
        <button class="">로그인하기</button>
      </div>
      <div v-if="session === 1">
        <p class="text-signup">비밀번호를 <br/>입력해주세요.</p>
        <hm-ui-user-input v-model="password" placeholder="비밀번호 입력" type="password"></hm-ui-user-input>
        <hm-ui-text label="8자리 이상 입력해 주세요." size="w100"></hm-ui-text>
        <hm-ui-user-input placeholder="비밀번호 확인" type="password"></hm-ui-user-input>
        <hm-ui-text label="다시 한 번 입력해 주세요." size="w100"></hm-ui-text>
        <hm-ui-button label="계속하기" color="primary" size="w100"></hm-ui-button>
      </div>
      <div v-if="session === 2">
        <p class="text-signup">전화번호를 <br/>입력해주세요.</p>
        <hm-ui-user-input></hm-ui-user-input>
        <hm-ui-text label="- 없이 입력해 주세요." size="w100"></hm-ui-text>
        <hm-ui-user-input></hm-ui-user-input>
        <hm-ui-button label="계속하기" color="primary" size="w100"></hm-ui-button>
      </div>
      <div v-if="session === 3">
        <p class="text-signup">주소를 <br/>입력해주세요.</p>
        <hm-ui-user-input></hm-ui-user-input>
        <hm-ui-text label="ex)서초동" size="w100"></hm-ui-text>
        <hm-ui-button label="계속하기" color="primary" size="w100"></hm-ui-button>
      </div>
      <div v-if="session === 4">
        <p class="text-signup">우리동네 <br/>설정이 완료되었어요!</p>
        <hm-ui-user-input>{{address}}</hm-ui-user-input>
        <hm-ui-button label="회원가입 완료" color="primary" size="w100"></hm-ui-button>
      </div>
    </form>
  </div>
</template>

<script>
export default {
  data () {
    return {
      session: 0,
      email: '',
      password: '',
      checkpassword: '',
      phone: '',
      address: '',
      state: false,
      signupObj: [this.email, this.password, this.checkpassword, this.phone, this.address]
    }
  },
  methods: {
    // async formSubmit (event) {
    //   console.log(event)
    //   if (event) {
    //     if (this.session === 0 && this.email === '') {
    //       console.log('아이디를 입력해 주세요')
    //     }
    //   }
    // switch (event) {
    //   case 0:

    //     // 아이디 값이 없을 때
    //     console.log('입력된 값이 없습니다')
    //     break
    //   case (this.session === 0 || this.email !== null):
    //     // 아이디 값이 있을 때
    //     this.$store.dispatch('emailCheck', [this.email, this.session])
    //     this.session = (this.session + 1) % 5
    //     console.log(this.email)
    //     break
    //   case (this.session === 1 || (this.password === null && this.checkpassword === null)):
    //     // 비밀번호 또는 비밀번호 확인의 값이 없을 때
    //     console.log('비밀번호와 비밀번호 확인을 입력해 주세요.')
    //     break
    //   case (this.session === 1 || (this.password !== null || this.checkpassword !== null)):
    //     // 비밀번호 또는 비밀번호 확인의 값이 있을 때
    //     if (this.password !== this.checkpassword) {
    //       console.log('비밀번호가 일치하지 않습니다.')
    //     } else if (this.password === this.checkpassword) {
    //       console.log(this.password, this.checkpassword)
    //       this.session = (this.session + 1) % 5
    //     }
    //     break
    //   case (this.session === 2 || this.phone === null):
    //     // 전화번호의 값이 없을 때
    //     console.log('전화번호를 입력해 주세요.')
    //     break
    //   case (this.session === 2 || this.phone !== null):
    //     // 전화번호의 값이 있을 때
    //     console.log(this.phone)
    //     this.session = (this.session + 1) % 5
    //     break
    //   case (this.session === 3 || this.address === null):
    //     // 주소의 값이 없을 때
    //     console.log('주소를 입력해 주세요.')
    //     break
    //   case (this.session === 3 || this.addres !== null):
    //     // 주소의 값이 있을 때
    //     console.log(this.address)
    //     this.session = (this.session + 1) % 5
    //     break
    //   case (this.session === 4):
    //     // 모든 값을 입력했을 때
    //     this.$store.dispatch('signup', this.signupObj)
    //     break
    //   default:
    //     // this.$router.push('/signup')
    //     break
    // }
    // }
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
