<template>
  <div class="search-container">
    <hm-ui-search-input
      v-model="keyword"
      @back-icon-click="onClickBackIcon"
      @search-icon-click="onClickSearchIcon"
      @keyup.enter="onClickSearchIcon"
      @update="(value) => keyword = value"
    />

    <div class="keyword-container">
      <h4>최근 검색어</h4>
      <button class="btn-delete" @click.prevent="deleteAll">
        <hm-ui-icon name="icon-trash-g"/>
        최근 검색어 전체 삭제
      </button>

      <div class="list-container">
        <div class="keyword-item" v-for="(item, index) in keywordList" :key="index" @click.prevent="onClickKeywordItem(item)">
          {{ item }}
          <hm-ui-icon name="icon-close-g" @icon-click="deleteItem(index)"/>
        </div>
        <div class="empty-item" v-if="keywordList && keywordList.length === 0">
          최근 검색어가 없습니다.
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: 'Search',
  components: {
  },
  created () {
    this.keyword = this.$route.query.keyword
    this.getKeywordList()
  },
  data () {
    return {
      keyword: undefined,
      keywordList: []
    }
  },
  methods: {
    onClickBackIcon () {
      this.$router.go(-1)
    },
    onClickSearchIcon () {
      if (!this.keyword) {
        alert('검색 키워드를 입력해 주세요.')
        return false
      }
      this.keywordList.push(this.keyword)
      // 키워드 중복 필터링
      const uniqueList = this.keywordList.filter((val, index) => {
        return this.keywordList.indexOf(val) === index
      })
      localStorage.setItem('keywordList', JSON.stringify(uniqueList))
      this.$router.push({
        name: 'search-results',
        query: {
          keyword: this.keyword
        }
      })
    },
    getKeywordList () {
      this.keywordList = JSON.parse(localStorage.getItem('keywordList')) ?? []
    },
    deleteItem (index) {
      this.keywordList.splice(index, 1)
      localStorage.setItem('keywordList', JSON.stringify(this.keywordList))
    },
    deleteAll () {
      this.keywordList = []
      localStorage.setItem('keywordList', JSON.stringify(this.keywordList))
    },
    onClickKeywordItem (item) {
      this.$router.push({
        name: 'search-results',
        query: {
          keyword: item
        }
      })
    }
  }
}
</script>

<style lang="scss">
.search-container {
  .keyword-container {
    padding: 0 19px;
    position: relative;
    margin-top: 44px;
    .btn-delete {
      border: none;
      background-color: transparent;
      display: flex;
      color: #B4B4B4;
      position: absolute;
      top:0;
      right: 20px;
    }

    .list-container {
      margin-top: 14px;
      .keyword-item {
        display: flex;
        justify-content: space-between;
        padding: 15px 17px;
        background-color: #F3F3F3;
        border-radius: 11px;
        align-items: center;
        margin-bottom: 8px;
      }

      .empty-item {
        text-align: center;
        color: #797979;
        font-size: 15px;
        padding-top: 14px;
      }
    }
  }
}

</style>
