<template>
  <div class="search-results-container">
    <hm-ui-search-input
      v-model="keyword"
      @back-icon-click="onClickBackIcon"
      @search-icon-click="onClickSearchIcon"
      @input-click="onClickInput"
      @focus="onFocusInput"
      @update="(value) => keyword = value"
      type="search-readonly"
    />
    <ProductList :productList="productList" :total="total" :keyword="keyword"/>
  </div>
</template>
<script>
import ProductList from '@/components/product-list'
// import { Rest, RestUrl } from '@/modules/Rest.js'
import productListData from '@/assets/productList.json/'

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
        name: 'search'
      })
    },
    onFocusInput () {
      this.$router.push({
        name: 'search',
        query: {
          keyword: this.keyword
        }
      })
    },
    onClickInput () {
      this.onFocusInput()
    },
    async fetchProductList () {
      try {
        // const response = await Rest.get(`${RestUrl.SearchProductList}` + `?keyword=${this.keyword}&page=0`)
        // this.productList = response.data.content
        // this.total = response.data.totalElements
        this.total = 12560
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
.search-result-container {
  .hm-search-input {
    margin-bottom: 14px;
  }
}
</style>
