<template>
  <div class="category-container">
    <div class="category-header">
      {{ categoryList.label }}
      <hm-ui-icon name="icon-back-bk" class="btn-back" @icon-click="onClickBackIcon"></hm-ui-icon>
    </div>
    <div class="category-list-container" v-if="categoryList">
      <ul>
        <li class="category-item" v-for="(item, index) in categoryList.subCategories" :key="index" @click="onClickItem(item.categoryId)">
          <span>{{ item.label }}</span>
          <span>25</span>
        </li>
      </ul>

    </div>
    <ProductList :productList="productList" :total="total" :keyword="keyword"/>
  </div>
</template>
<script>
import ProductList from '@/components/product-list'
import productList from '@/assets/productList.json'
import categoryList from '@/assets/categoryList.json'

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
    this.fetchCategoryList()
  },
  data () {
    return {
      productList: [],
      total: undefined,
      order: undefined,
      categoryList: {}
    }
  },
  methods: {
    onClickBackIcon () {
      this.$router.go(-1)
    },
    fetchProductList () {
      // axios get product_list?recent_type=99&uid=00000000000&order=date
      this.total = 30
      this.productList = productList
    },
    fetchCategoryList () {
      this.categoryList = categoryList
    },
    onClickItem (id) {
      this.$router.replace({
        query: {
          category_id: id
        }
      })
    }
  }
}
</script>

<style lang="scss">
.category-container {
  .category-header {
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

  .category-list-container {
    padding-left: 19px;
    padding-right: 19px;
    margin-bottom: 25px;
    ul {
      display: grid;
      grid-template-columns: 1fr 1fr;
      grid-gap: 4px 3px;

      .category-item {
        background-color: #F0F0F0;
        padding: 12px 17px;
        border-radius: 10px;
        font-size: 13px;
        cursor: pointer;
        display: flex;
        justify-content: space-between;
      }
    }
  }
}
</style>
