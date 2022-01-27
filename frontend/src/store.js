import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import router from '@/router'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
  },
  mutations: {
    logout () {
      localStorage.removeItem('access_token')
      // location.reload()
    }
  },
  actions: {
    // 로그인 시도
    login (loginObj) {
      axios
        .post('https://15.165.216.62:8080/api/login', loginObj)
        .then((res) => {
          const token = res.data.token
          console.log(token)
          localStorage.setItem('access_token', token)
          router.go(-1)
        })
        .catch((err) => {
          console.log(err)
          alert('아이디와 비밀번호를 확인해 주세요.')
        })
    },
    // 회원가입 시도
    signup (signupObj) {
      axios
        .post('http://localhost:8080/api/v1/signup', signupObj)
        .then(res => {
          const token = res.data.token
          // console.log(token)
          localStorage.setItem('access_token', token)
          router.go(-2)
        })
        .catch((err) => {
          console.log(err)
          alert('회원가입에 실패하였습니다.')
          router.push('/signup')
        })
    },
    // 로그아웃
    logout ({ commit }) {
      commit('logout')
      router.push('/').catch(() => {})
    }
  },
  modules: {
  },
  getters: {
  }
})
