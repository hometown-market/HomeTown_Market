import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
  },
  mutations: {
    logout () {
      localStorage.removeItem('access_token')
      location.reload()
    }
  },
  actions: {
    // 로그인 시도
    login (loginObj) {
      axios
        .post('https://reqres.in/api/login', loginObj)
        .then(res => {
          const token = res.data.token
          console.log(token)
          localStorage.setItem('access_token', token)
          this.$router.go(-1)
        })
        .catch(err => {
          console.log(err)
          alert('아이디와 비밀번호를 확인해 주세요.')
        })
    },
    // 이메일 중복체크
    emailCheck (email) {
      axios
        .post('http://localhost:8008/signup', email)
        .then((res) => {
          console.log(res)
          alert('사용 가능한 이메일 입니다.')
        })
        .catch((err) => {
          console.log(err)
          alert('사용 불가능한 이메일 입니다.')
        })
    },
    // 회원가입 시도
    signup (signupObj) {
      axios
        .post('http://localhost:8008/signup', signupObj)
        .then(res => {
          const token = res.data.token
          console.log(token)
          localStorage.setItem('access_token', token)
          this.$router.go(-1)
        })
        .catch((err) => {
          console.log(err)
          alert('회원가입에 실패하였습니다.')
        })
    },
    // 로그아웃
    logout ({ commit }) {
      commit('logout')
      this.$router.push('/')
    }
  },
  modules: {
  }
})
