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
    fetchProductList () {
      // axios get product_list?recent_type=00&uid=00000000000
      this.total = 100
      this.productList = [
        {
          product_id: 123,
          likes: 0,
          product_img: 'https://media.bunjang.co.kr/product/175067065_1_1641306224_w354.jpg',
          product_title: '상품 이름',
          product_price: 10000,
          create_time: 1607110465663,
          locate_authorization: true,
          likes_number: 16
        },
        {
          product_id: 123,
          likes: 0,
          product_img: 'https://media.bunjang.co.kr/product/175067065_1_1641306224_w354.jpg',
          product_title: '상품 이름',
          product_price: 10000,
          create_time: 1607110465663,
          locate_authorization: true,
          likes_number: 16
        },
        {
          product_id: 123,
          likes: 0,
          product_img: 'https://media.bunjang.co.kr/product/175067065_1_1641306224_w354.jpg',
          product_title: '상품 이름',
          product_price: 10000,
          create_time: 1607110465663,
          locate_authorization: true,
          likes_number: 16
        },
        {
          product_id: 123,
          likes: 0,
          product_img: 'https://media.bunjang.co.kr/product/175067065_1_1641306224_w354.jpg',
          product_title: '상품 이름',
          product_price: 10000,
          create_time: 1607110465663,
          locate_authorization: true,
          likes_number: 16
        },
        {
          product_id: 123,
          likes: 0,
          product_img: 'https://media.bunjang.co.kr/product/103269118_1_1626911735_w354.jpg',
          product_title: '상품 이름',
          product_price: 10000,
          create_time: 1607110465663,
          locate_authorization: true,
          likes_number: 16
        },
        {
          product_id: 123,
          likes: 0,
          product_img: 'https://media.bunjang.co.kr/product/103269118_1_1626911735_w354.jpg',
          product_title: '상품 이름',
          product_price: 10000,
          create_time: 1607110465663,
          locate_authorization: true,
          likes_number: 16
        }
      ]
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
    }
  }
}

</style>
