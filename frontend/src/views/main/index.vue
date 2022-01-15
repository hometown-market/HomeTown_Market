<template>
  <div class="main-container">
    <hm-ui-search-input
      readonly
      @like-icon-click="onClickLikeIcon"
      @search-icon-click="onClickReadonly"
    />
    <div class="slider">
      <img :src="require(`@/assets/banner/banner1.png`)" />
    </div>
    <div class="recent-list-contianer">
      <h4>방금 올라온 상품</h4>
      <ProductList type="recent" />
    </div>
  </div>
</template>
<script>
import ProductList from '@/components/product-list'

export default {
  name: 'Main',
  components: {
    ProductList
  },
  data () {
    return {
      keyword: ''
    }
  },
  methods: {
    onClickLikeIcon () {
      // 로그인 유무 파악해서
      // 로그인 O -> alert => 내 찜정보 페이지가 준비중입니다.
      // 로그인 X -> 로그인 모달 띄우기
      alert('search like icon click')
    },
    onClickReadonly () {
      alert('검색창으로 넘어갈꺼임!')
    }
  }
}
</script>

<style lang="scss">
.main-container {
  .slider {
    img {
      width: 100%;
    }
  }

  .recent-list-contianer {
    h4 {
      text-align: center;
    }
  }
}

</style>
