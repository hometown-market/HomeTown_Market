<template>
  <div class="main-container">
    <hm-ui-search-input
      type="main-readonly"
      @like-icon-click="onClickLikeIcon"
      @search-icon-click="onClickReadonly"
      @input-click="onClickReadonly"
      @update="(value) => keyword = value"
    />
    <div class="slider">
      <div class="slider-inner">
        <img class="slider-item" :src="require(`@/assets/banner/banner1.png`)" />
        <img class="slider-item" :src="require(`@/assets/banner/banner2.png`)" />
        <img class="slider-item" :src="require(`@/assets/banner/banner3.png`)" />
      </div>
    </div>
    <div class="recent-list-contianer">
      <h4>방금 올라온 상품</h4>
      <ProductList type="recent" :productList="productList" :total="total" />
    </div>
  </div>
</template>
<script>
import ProductList from '@/components/product-list'
import { Rest, RestUrl } from '@/modules/Rest.js'

export default {
  name: 'Main',
  components: {
    ProductList
  },
  data () {
    return {
      productList: [],
      total: undefined,
      pressed: false,
      startx: '',
      x: ''
    }
  },
  created () {
    this.fetchProductList()
  },
  methods: {
    onClickLikeIcon () {
      // 로그인 유무 파악해서
      // 로그인 O -> alert => 내 찜정보 페이지가 준비중입니다.
      // 로그인 X -> 로그인 모달 띄우기
      alert('search like icon click')
    },
    onClickReadonly () {
      this.$router.push({
        name: 'search'
      })
    },
    async fetchProductList () {
      try {
        const response = await Rest.get(RestUrl.ProductList)
        this.productList = response.data.content
        this.total = response.data.totalElements
      } catch (error) {
        console.log(error)
        alert(error)
      }
    },
    //  슬라이더 제어 함수
    async checkboundary () {
      const slider = document.querySelector('.slider')
      const innerSlider = document.querySelector('.slider-inner')
      const outer = slider.getBoundingClientRect()
      const inner = innerSlider.getBoundingClientRect()

      if (parseInt(innerSlider.style.left) > 0) {
        innerSlider.style.left = '0px'
      } else if (inner.right < outer.right) {
        innerSlider.style.left = `-${inner.width - outer.width}px`
      }
    }
  },
  mounted: function () {
    const slider = document.querySelector('.slider')
    const innerSlider = document.querySelector('.slider-inner')
    slider.addEventListener('mousedown', e => {
      this.pressed = true
      this.startx = e.offsetX - innerSlider.offsetLeft
      slider.style.cursor = 'grabbing'
    })
    slider.addEventListener('mouseenter', () => {
      slider.style.cursor = 'grab'
    })

    slider.addEventListener('mouseup', () => {
      slider.style.cursor = 'grab'
    })

    window.addEventListener('mouseup', () => {
      this.pressed = false
    })

    slider.addEventListener('mousemove', e => {
      if (!this.pressed) return
      e.preventDefault()
      this.x = e.offsetX

      innerSlider.style.left = `${this.x - this.startx}px`
      this.checkboundary()
    })
  }
}
</script>

<style lang="scss">
.main-container {
  .slider {
    height: 234px;
    img {
      width: 100%;
    }
  }

  .recent-list-contianer {
    h4 {
      text-align: center;
      font-size: 18px;
      font-weight: normal;
      margin: 31px 0;
    }
  }
}

</style>
