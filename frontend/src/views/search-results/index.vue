<template>
  <div class="search-results-container">
    <hm-ui-search-input
      v-model="keyword"
      @back-icon-click="onClickBackIcon"
      @search-icon-click="onClickSearchIcon"
      @keyup.enter="onClickSearchIcon"
      @focus="onClickSearchIcon"
      @update="(value) => keyword = value"
      type="search-readonly"
    />
    <ProductList :productList="productList" :total="total" :keyword="keyword"/>
  </div>
</template>
<script>
import ProductList from '@/components/product-list'

export default {
  name: 'SearchResults',
  components: {
    ProductList
  },
  watch: {
    '$route.params': {
      deep: true,
      handler (val) {
        this.order = this.$route.query.order
        this.fetchProductList()
      }
    }
  },
  created () {
    this.keyword = this.$route.query.keyword
    this.fetchProductList()
  },
  data () {
    return {
      keyword: undefined,
      productList: [],
      total: undefined,
      order: undefined
    }
  },
  methods: {
    onClickBackIcon () {
      this.$router.go(-1)
    },
    onClickSearchIcon () {
      this.$router.push({
        name: 'search',
        query: {
          keyword: this.keyword
        }
      })
    },
    fetchProductList () {
      // axios get product_list?recent_type=99&uid=00000000000&order=date
      this.total = 30
      this.productList = [
        {
          product_id: 123,
          likes: 0,
          product_img: 'https://media.bunjang.co.kr/product/175067065_1_1641306224_w354.jpg',
          product_title: this.keyword + '상품 이름 1',
          product_price: 10000,
          create_time: 1607110465663,
          locate_authorization: true,
          likes_number: 16
        },
        {
          product_id: 123,
          likes: 0,
          product_img: 'https://media.bunjang.co.kr/product/175067065_1_1641306224_w354.jpg',
          product_title: this.keyword + '상품 이름 2',
          product_price: 10000,
          create_time: 1607110465663,
          locate_authorization: true,
          likes_number: 16
        },
        {
          product_id: 123,
          likes: 0,
          product_img: 'https://media.bunjang.co.kr/product/175067065_1_1641306224_w354.jpg',
          product_title: this.keyword + '상품 이름 3',
          product_price: 10000,
          create_time: 1607110465663,
          locate_authorization: true,
          likes_number: 16
        },
        {
          product_id: 123,
          likes: 0,
          product_img: 'https://media.bunjang.co.kr/product/175067065_1_1641306224_w354.jpg',
          product_title: this.keyword + '상품 이름',
          product_price: 10000,
          create_time: 1607110465663,
          locate_authorization: true,
          likes_number: 16
        },
        {
          product_id: 123,
          likes: 0,
          product_img: 'https://media.bunjang.co.kr/product/103269118_1_1626911735_w354.jpg',
          product_title: this.keyword + '상품 이름',
          product_price: 10000,
          create_time: 1607110465663,
          locate_authorization: true,
          likes_number: 16
        },
        {
          product_id: 123,
          likes: 0,
          product_img: 'https://media.bunjang.co.kr/product/103269118_1_1626911735_w354.jpg',
          product_title: this.keyword + '상품 이름',
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
