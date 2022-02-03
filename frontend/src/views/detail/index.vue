<template>
  <div class="detail-container">
    <div class="image-container" :style="'background-image: url('+ productInfo.productImgUrl +')'">
      <div class="icon-container">
        <hm-ui-icon name="icon-back-w" @icon-click="onClickBackIcon"></hm-ui-icon>
        <hm-ui-icon name="icon-search-pc" @icon-click="onClickSearchIcon"></hm-ui-icon>
      </div>
    </div>
    <div class="info-container">
      <div class="top-container">
        <p class="product-title">{{ productInfo.title }}</p>
        <p class="product-price">{{ numberWithCommas(productInfo.price) }}원</p>
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
        {{ productInfo.text }}
      </div>

    </div>
  </div>
</template>
<script>
import { Rest, RestUrl } from '@/modules/Rest.js'
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
  computed: {
    productId () {
      return this.$route.params.productId
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
    async fetchProductInfo () {
      try {
        const response = await Rest.get(RestUrl.ProductDetail.replace(':productId', this.productId))
        this.productInfo = response.data
      } catch (error) {
        console.log(error)
        alert(error)
      }
    },
    numberWithCommas (x) {
      return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')
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

      .product-title {
        font-size: 18px;
        margin-bottom: 6px;
      }

      .product-price {
        font-size: 23px;
        font-weight: bold;
      }
      .icon-container {
        display: flex;
        width: 120px;
        justify-content: space-between;
        position: absolute;
        bottom: 13px;
        right: 0;

        >div {
          display: flex;

          .hm-icon {
            margin-right: 2px;
          }
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
      line-height: 22px;
    }
  }
}
</style>
