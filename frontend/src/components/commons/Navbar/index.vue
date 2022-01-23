<template>
  <div class="navbar">
    <nav>
      <ul class="category-container">
        <li v-for="(mainItem, mainIndex) in categories" :key="mainIndex">
          <div class="main-category">
            <hm-ui-icon name="icon-angle-down-bk" class="main-icon" @icon-click="onClicCollapseIcon('main', mainIndex)" />
            <div @click.prevent="onClickCategoryItem(mainItem.categoryId)">
              {{ mainItem.label }}
            </div>
          </div>
          <ul :class="['middle-categories', { active: mainActiveList[mainIndex]}]">
            <li v-for="(middleItem, middleIndex) in mainItem.subcategories" :key="middleIndex">
              <div class="middle-category">
                <hm-ui-icon name="icon-angle-down-bk" class="middle-icon" size="16" @icon-click="onClicCollapseIcon('middle', mainsIndex, middleIndex)" />
                <div @click.prevent="onClickCategoryItem(middleItem.categoryId)">
                  {{ middleItem.label }}
                </div>
              </div>
              <ul :class="['subcategories', { active: middelActiveList[mainIndex][middleIndex]}]">
                <li class="subcategory" v-for="(subItem, subIndex) in middleItem.subcategories" :key="subIndex" @click.prevent="onClickCategoryItem(subItem.categoryId)">
                {{ subItem.label }}
                </li>
              </ul>
            </li>
          </ul>
        </li>
      </ul>
    </nav>
    <hm-ui-icon class="btn-close" name="icon-close-w" @icon-click="onClickCloseIcon" />
    <div class="overlay" @click.prevent="onClickCloseIcon"></div>
  </div>
</template>

<script>
import categoryData from '@/assets/categoryData.json'

export default {
  name: 'Navbar',
  components: {
  },
  created () {
    var mainArr = []
    var arr = []
    categoryData.map((mainItem, index) => {
      var middleArr = []
      mainItem.subcategories.map((middleItem, index) => {
        middleArr.push(false)
      })
      mainArr.push(middleArr)
      arr.push(false)
    })
    this.middelActiveList = mainArr
    this.mainActiveList = arr
    this.getCategories()
  },
  data () {
    return {
      categories: [],
      mainActiveList: [],
      middelActiveList: []
    }
  },
  methods: {
    getCategories () {
      this.categories = categoryData
    },
    onClickCloseIcon () {
      this.$eventBus.$emit('toggleNavbar', false)
    },
    onClickCategoryItem (categoryId) {
      if (this.$route.name === 'category') {
        this.$router.replace({
          query: {
            category_id: categoryId
          }
        })
      } else {
        this.$router.push({
          name: 'category',
          query: {
            category_id: categoryId
          }
        })
      }
      this.$eventBus.$emit('toggleNavbar', false)
    },
    onClicCollapseIcon (type, index1, index2) {
      if (type === 'main') {
        this.mainActiveList[index1] = !this.mainActiveList[index1]
      } else if (type === 'middle') {
        this.middelActiveList[index1][index2] = !this.middelActiveList[index1][index2]
      }
    }
  }
}
</script>

<style lang="scss">
.navbar {
  nav {
    max-width: 318px;
    width: 85%;
    background-color: #ffffff;
    border-radius: 0px 20px 20px 0px;
    padding-top: 90px;
    padding-left: 25px;
    min-height: 100vh;
    height: 100%;
    position: fixed;
    top: 0;
    // left: 0;
    z-index: 9;
    box-sizing: border-box;

    .category-container {
      > li {
        margin-bottom: 17px;
      }
    }

    .main-category {
      display: flex;
      line-height: 25px;
      font-size: 17px;
      margin-bottom: 19px;

      .main-icon {
        margin-right: 8px;
      }

    }

    .middle-categories {
      margin-left: 33px;

      &.active {
        background-color: yellow;
      }
    }

    .middle-category {
      display: flex;
      font-size: 14px;
      line-height: 16px;
      margin-bottom: 10px;

      .middle-icon {
        margin-right: 2px;
      }
    }

    .subcategories {
      margin-left: 16px;
    }

    .subcategory {
      font-size: 12px;
      margin-bottom: 10px;
    }
  }

  .overlay {
    position: fixed;
    left: 0;
    right: 0;
    top: 0;
    bottom: 0;
    background-color: rgba(0, 0, 0, 0.5);
  }

  .btn-close {
    position: fixed;
    top: 50px;
    left: 330px;
    z-index: 10;
  }
}

</style>
