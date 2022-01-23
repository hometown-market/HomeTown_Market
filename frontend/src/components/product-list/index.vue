<template>
  <div class="product-list">
    <div class="top-container" v-if="type === 'nomal'">
      <div>{{ numberWithCommas(total) }}개</div>
      <hm-ui-select v-model="selected" @update="(value) => selected = value"></hm-ui-select>
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
    },
    productList: {
      type: Array
    },
    total: {
      type: Number
    },
    keyword: {
      type: String
    }
  },
  components: {
    ProductItem
  },
  watch: {
    selected () {
      this.$router.replace({
        query: {
          keyword: this.keyword,
          order: this.selected
        }
      })
    }
  },
  data () {
    return {
      selected: 'date'
    }
  },
  methods: {
    numberWithCommas (x) {
      return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')
    }
  }
}
</script>

<style lang="scss">
.product-list {
  padding-right: 19px;
  padding-left: 19px;

  .top-container {
    display: flex;
    justify-content: space-between;
    margin-bottom: 16px;
    font-size: 20px;
    font-weight: bold;
  }
  .list-container {
    display: grid;
    grid-template-columns: 1fr 1fr;
    grid-gap: 53px 15px;
  }
}

</style>
