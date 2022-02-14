import Vue from 'vue'
import Vuex from 'vuex'
import Axios from 'axios'
import router from '@/router'

Axios.defaults.baseURL = 'http://15.165.216.62:8080'
Axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'
Axios.defaults.headers['Access-Control-Allow-Origin'] = '*'

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
      Axios
        .post('/login', loginObj)
        .then((res) => {
          const token = res.headers.authorization
          commit('settoken', { token })
          router.push('/?login=true')
        })
        .catch(() => {
          alert('아이디와 비밀번호를 확인해 주세요.')
        })
    },
    // 회원가입 시도
    signup ({ commit }, signupObj) {
      Axios
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
