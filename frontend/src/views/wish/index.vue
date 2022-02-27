<template>
  <div class="wish-container">
    <div class="wish-header">
      찜목록
      <hm-ui-icon name="icon-back-bk" class="btn-back" @icon-click="onClickBackIcon"></hm-ui-icon>
    </div>
    <ProductList type="wish" :productList="productList"/>
  </div>
</template>

<script>
import { Rest, RestUrl } from '@/modules/Rest.js'
import ProductList from '@/components/product-list'

export default {
  name: 'Wish',
  components: {
    ProductList
  },
  created () {
    this.fetchProductList()
  },
  data () {
    return {
      productList: []
    }
  },
  methods: {
    async fetchProductList () {
      try {
        const response = await Rest.get(RestUrl.WishProductList)
        console.log(response)
        this.productList = response.data.content
      } catch (error) {
        console.log(error)
        alert(error)
      }
    },
    onClickBackIcon () {
      this.$router.go(-1)
    }
  }
}
</script>

<style lang="scss">
.wish-container {
  .wish-header {
    text-align: center;
    position: relative;
    padding: 25px 0;
    font-size: 18px;
    font-weight: 700;

    .btn-back {
      position: absolute;
      top: 20.5px;
      left: 20px;

    }
  }
}

</style>
