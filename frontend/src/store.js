import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import router from '@/router'

Vue.use(Vuex)
export default new Vuex.Store({
  state: {},
  mutations: {
    logout () {
      localStorage.removeItem('access_token')
      router.push('/').catch(() => {})
    },
    settoken (state, { token }) {
      localStorage.setItem('access_token', token)
    }
  },
  actions: {
    // 로그인 시도
    login ({ commit }, loginObj) {
      axios
        .post('/login', loginObj)
        .then((res) => {
          const token = res.headers.authorization
          commit('settoken', { token })
          router.go(-1)
        })
        .catch(() => {
          alert('아이디와 비밀번호를 확인해 주세요.')
        })
    },
    // 회원가입 시도
    signup ({ commit }, signupObj) {
      axios
        .post('/join', signupObj)
        .then(res => {
          const token = res.headers.authorization
          commit('settoken', { token })
          router.go(-2)
        })
        .catch(() => {
          alert('회원가입에 실패하였습니다.')
          router.push('/signup')
        })
    },
    // 로그아웃
    logout ({ commit }) {
      commit('logout')
    }
  },
  modules: {
  },
  getters: {
  }
})
