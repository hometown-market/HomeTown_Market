<template>
  <div class="product-item" @click.prevent="onClickItem">
    <div class="image-container" :style="'background-image: url('+ data.productImgUrl+')'">
    </div>
    <div class="info-container">
      <p class="product-title">{{ data.title}}</p>
      <p class="product_price">{{ data.price }}원</p>
      <span class="time">10초 전</span>
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
    position: relative;

    .product-title {
      font-size: 10px;
      margin-bottom: 1px;
    }

    .price {
      font-size: 15px;
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
      margin-top: 20px;

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
