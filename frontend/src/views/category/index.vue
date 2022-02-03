<template>
  <div class="category-container">
    <div class="category-header">
      {{ categoryList.categoryName }}
      <hm-ui-icon name="icon-back-bk" class="btn-back" @icon-click="onClickBackIcon"></hm-ui-icon>
    </div>
    <div class="category-list-container" v-if="categoryList">
      <ul>
        <li class="category-item" v-for="(item, index) in subCategories" :key="index" @click="onClickItem(item.categoryId)">
          <span>{{ item.categoryName }}</span>
          <span>25</span>
        </li>
      </ul>

    </div>
    <ProductList :productList="productList" :total="total" :keyword="keyword"/>
  </div>
</template>
<script>
import { Rest, RestUrl } from '@/modules/Rest.js'
import ProductList from '@/components/product-list'
// import categoryList from '@/assets/categoryList.json'

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
      productList: [],
      total: undefined,
      order: undefined,
      categoryList: {},
      subCategories: []
    }
  },
  computed: {
    categortId () {
      return this.$route.query.category_id
    }
  },
  methods: {
    onClickBackIcon () {
      this.$router.go(-1)
    },
    async fetchProductList () {
      try {
        const response = await Rest.get(RestUrl.CategoryProductList.replace(':categoryId', this.categortId))
        this.productList = response.data[0].content
        this.total = response.data[0].totalElements
        this.categoryList = response.data[2]
        this.subCategories = response.data[3]
      } catch (error) {
        console.log(error)
        alert(error)
      }
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
