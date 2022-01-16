<template>
  <div class="detail-container">
    <div class="image-container" :style="'background-image: url('+ productInfo.product_img +')'">
      <div class="icon-container">
        <hm-ui-icon name="icon-back-w" @icon-click="onClickBackIcon"></hm-ui-icon>
        <hm-ui-icon name="icon-search-pc" @icon-click="onClickSearchIcon"></hm-ui-icon>
      </div>
    </div>
    <div class="info-container">
      <div class="top-container">
        <p>{{ productInfo.product_title }}</p>
        <p>{{ productInfo.product_price }}원</p>
        <div class="icon-container">
          <div class="like-container">
            <hm-ui-icon name="icon-like-g"/> 0
          </div>
          <div class="check-container">
            <hm-ui-icon name="icon-check"/> 인증
          </div>
          <div class="time-container">
            10초 전
          </div>
        </div>
      </div>
      <div class="bottom-container">
        {{ productInfo.product_description }}
      </div>

    </div>
  </div>
</template>
<script>
export default {
  name: 'Detail',
  components: {
  },
  watch: {
    '$route.params': {
      deep: true,
      handler (val) {
        this.order = this.$route.query.order
        this.fetchProductInfo()
      }
    }
  },
  created () {
    this.keyword = this.$route.query.keyword
    this.fetchProductInfo()
  },
  data () {
    return {
      productInfo: {}
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
    fetchProductInfo () {
      // axios get product_detail?product_id=1234&uid=00000000000
      this.productInfo = {
        product_id: 123,
        likes: 0,
        product_img: 'https://media.bunjang.co.kr/product/175067065_1_1641306224_w354.jpg',
        product_title: this.keyword + '상품 이름 1',
        product_price: 10000,
        create_time: 1607110465663,
        locate_authorization: true,
        likes_number: 16,
        product_description: `남성 매킨토시 트렌치코드입니다.
          사이즈 38(100)입니다.
          직거래 가능합니다.
          택배비는 별도입니다.
        `
      }
    }
  }
}
</script>

<style lang="scss">
.detail-container {
  .image-container {
    height: 100vw;
    max-height: 481px;
    background-size: cover;
    background-repeat: no-repeat;
    background-position: center;
    position: relative;

    .icon-container {
      display: flex;
      justify-content: space-between;
      position: absolute;
      top:0;
      right: 0;
      left: 0;
      padding: 18px 19px;
    }
  }

  .info-container {
    padding: 15px 19px;
    .top-container {
      position: relative;
      padding-bottom: 20px;
      border-bottom: 1px solid #C4C4C4;
      .icon-container {
        display: flex;
        width: 120px;
        justify-content: space-between;
        position: absolute;
        bottom: 13px;
        right: 0;

        >div {
          display: flex;
        }

        .like-container {
          color: #B4B4B4;
          font-size: 11px;
          font-weight: bold;
        }

        .check-container {
          color: #4DC166;
          font-size: 10px;
          font-weight: bold;
        }

        .time-container {
          color: #B4B4B4;
          font-size: 11px;
          font-weight: bold;
        }
      }
    }

    .bottom-container {
      padding-top: 25px;
    }
  }
}
</style>
