<template>
  <div class="product-item" @click.prevent="onClickItem">
    <div class="image-container" :style="'background-image: url('+ data.productImgUrl+')'">
    </div>
    <div class="info-container">
      <p class="product-title">{{ data.title}}</p>
      <p class="product-price">{{ numberWithCommas(data.price) }}원</p>
      <span class="time">{{displayedAt}}</span>
      <div class="icon-container">
        <div class="left-container">
          <hm-ui-icon name="icon-like-g" />
          10
        </div>
        <div class="right-container">
          <hm-ui-icon name="icon-check" />
          인증
        </div>

      </div>
    </div>
    <hm-ui-icon class="btn-like" name="icon-like-off" @icon-click="onClickLikeIcon"/>
  </div>
</template>
<script>

export default {
  name: 'ProductItem',
  props: {
    data: { type: Object }
  },
  components: {
  },
  data () {
    return {}
  },
  computed: {
    displayedAt () {
      const createdAt = new Date(this.data.uploadDate)
      const milliSeconds = new Date() - createdAt
      const seconds = milliSeconds / 1000
      if (seconds < 60) return '방금 전'
      const minutes = seconds / 60
      if (minutes < 60) return `${Math.floor(minutes)}분 전`
      const hours = minutes / 60
      if (hours < 24) return `${Math.floor(hours)}시간 전`
      const days = hours / 24
      if (days < 7) return `${Math.floor(days)}일 전`
      const weeks = days / 7
      if (weeks < 5) return `${Math.floor(weeks)}주 전`
      const months = days / 30
      if (months < 12) return `${Math.floor(months)}개월 전`
      const years = days / 365
      return `${Math.floor(years)}년 전`
    }
  },
  methods: {
    onClickItem () {
      this.$router.push({
        name: 'detail',
        params: {
          productId: this.data.id
        }
      })
    },
    onClickLikeIcon () {
      const answer = confirm('로그인 후 찜기능을 사용할 수 있습니다. 로그인 하시겠습니까?')
      if (answer) {
        // 로그인 모달
        this.$eventBus.$emit('showLoginModal')
        return false
      }
    },
    numberWithCommas (x) {
      return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')
    }
  }
}
</script>

<style lang="scss">
.product-item {
  height: 228px;
  border-radius: 10px;
  overflow: hidden;
  box-shadow: 1px 1px 3px rgba(0, 0, 0, 0.25);
  position: relative;

  .image-container {
    height: 160px;
    background-size: cover;
    background-position: center;
    background-repeat: no-repeat;
  }

  .info-container {
    padding: 5px 7px;
    height: 68px;
    box-sizing: border-box;
    position: relative;

    .product-title {
      font-size: 10px;
      margin-bottom: 1px;
      line-height: 12px;
    }

    .product-price {
      font-size: 15px;
      line-height: 18px;
      font-weight: bold;
    }

    .time {
      font-size: 8px;
      position: absolute;
      right: 7px;
      bottom: 32px;
      color: #B4B4B4;
    }

    .icon-container {
      display: flex;
      justify-content: space-between;
      font-weight: bold;
      // margin-top: 20px;
      position: absolute;
      bottom: 9px;
      left: 0;
      right: 0;
      margin: 0 7px;

      >div {
        display: flex;

        .hm-icon {
          margin-right: 2px;
        }
      }

      .left-container {
        font-size: 11px;
        color: #B4B4B4;
      }

      .right-container {
        font-size: 8px;
        color: #4DC166;
      }
    }
  }

  .btn-like {
    position: absolute;
    right: 11px;
    top: 12px;
}
}
</style>
