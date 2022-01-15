<template>
  <div class="product-list">
    <div class="top-container" v-if="type === 'nomal'">
    </div>
    <div class="list-container">
      <ProductItem v-for="(item, index) in productList" :key="index" :data="item"/>
    </div>

  </div>
</template>
<script>
import ProductItem from './ProductItem.vue'

export default {
  name: 'ProductList',
  props: {
    type: {
      type: String,
      default: 'nomal'
    }
  },
  components: {
    ProductItem
  },
  data () {
    return {
      productList: [
        {
          productId: 123,
          likes: 0,
          product_img: 'https://media.bunjang.co.kr/product/103269118_1_1626911735_w354.jpg',
          product_title: '상품 이름',
          product_price: 10000,
          create_time: 1607110465663,
          locate_authorization: true,
          likes_number: 16
        },
        {
          productId: 123,
          likes: 0,
          product_img: 'https://media.bunjang.co.kr/product/103269118_1_1626911735_w354.jpg',
          product_title: '상품 이름',
          product_price: 10000,
          create_time: 1607110465663,
          locate_authorization: true,
          likes_number: 16
        },
        {
          productId: 123,
          likes: 0,
          product_img: 'https://media.bunjang.co.kr/product/103269118_1_1626911735_w354.jpg',
          product_title: '상품 이름',
          product_price: 10000,
          create_time: 1607110465663,
          locate_authorization: true,
          likes_number: 16
        },
        {
          productId: 123,
          likes: 0,
          product_img: 'https://media.bunjang.co.kr/product/103269118_1_1626911735_w354.jpg',
          product_title: '상품 이름',
          product_price: 10000,
          create_time: 1607110465663,
          locate_authorization: true,
          likes_number: 16
        },
        {
          productId: 123,
          likes: 0,
          product_img: 'https://media.bunjang.co.kr/product/103269118_1_1626911735_w354.jpg',
          product_title: '상품 이름',
          product_price: 10000,
          create_time: 1607110465663,
          locate_authorization: true,
          likes_number: 16
        },
        {
          productId: 123,
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
.product-list {
  padding-right: 20px;
  padding-left: 20px;
}

</style>
