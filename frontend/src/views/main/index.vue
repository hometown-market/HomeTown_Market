<template>
  <div class="main-container">
    <hm-ui-search-input
      type="main-readonly"
      @like-icon-click="onClickLikeIcon"
      @search-icon-click="onClickReadonly"
      @input-click="onClickReadonly"
      @update="(value) => keyword = value"
    />
    <div class="slider">
      <img :src="require(`@/assets/banner/banner1.png`)" />
    </div>
    <div class="recent-list-contianer">
      <h4>방금 올라온 상품</h4>
      <ProductList type="recent" :productList="productList" :total="total" />
    </div>
  </div>
</template>
<script>
import ProductList from '@/components/product-list'
// import { Rest, RestUrl } from '@/modules/Rest.js'
import productListData from '@/assets/productList.json/'

export default {
  name: 'Main',
  components: {
    ProductList
  },
  created () {
    this.fetchProductList()
  },
  data () {
    return {
      productList: [],
      total: undefined
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
      this.$router.push({
        name: 'search'
      })
    },
    async fetchProductList () {
      try {
        // const response = await Rest.get(RestUrl.ProductList)
        // this.productList = response.data.content
        // this.total = response.data.totalElements
        this.productList = productListData
      } catch (error) {
        console.log(error)
        alert(error)
      }
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
      font-size: 18px;
      font-weight: normal;
      margin: 31px 0;
    }
  }
}

</style>
