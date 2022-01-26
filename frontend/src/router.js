import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '@/views/Login.vue'
import Signup from '@/views/Signup.vue'

import PageContainer from '@/containers/PageContainer'

import Main from '@/views/main/index.vue'
import Search from '@/views/search/index.vue'
import SearchResults from '@/views/search-results/index.vue'
import Category from '@/views/category/index.vue'
import Detail from '@/views/detail/index.vue'
import Shop from '@/views/shop/index.vue'

import Component from '@/views/component/index.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    component: PageContainer,
    children: [
      {
        // 메인
        name: 'main',
        path: '',
        component: Main
      },
      {
        // 검색 화면
        name: 'search',
        path: '/search',
        component: Search
      },
      {
        // 검색 결과 화면
        name: 'search-results',
        path: '/search-results',
        component: SearchResults
      },
      {
        // 카테고리 화면
        name: 'category',
        path: '/category',
        component: Category
      },
      {
        // 상품 상세 화면
        name: 'detail',
        path: '/detail/:productId',
        component: Detail
      },
      {
        // 로그인
        name: 'login',
        path: '/login',
        component: Login
      },
      {
        // 회원가입
        name: 'signup',
        path: '/signup',
        component: Signup
      },
      {
        // 내 상점
        name: 'shop',
        path: '/shop',
        component: Shop
      },
      {
        // (임시) 컴포넌트
        name: 'component',
        path: '/component',
        component: Component
      }
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
