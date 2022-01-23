<template>
  <div class="main-container">
    컴포넌트 페이지 입니다.
    <!-- hm-ui 사용법 -->
    <hm-ui-button label="우리동네 버튼" color="primary" size="w100" @click="onClickButton"></hm-ui-button>
    <hm-ui-button label="우리동네 버튼" color="white" shadow></hm-ui-button>
    <hm-ui-icon name="icon-user-bk" @icon-click="onIconClick"></hm-ui-icon>
    <hm-ui-icon name="icon-back-bk" @icon-click="onIconClick"></hm-ui-icon>
    <hm-ui-search-input
      v-model="keyword"
      @back-icon-click="onClickBackIcon"
      @search-icon-click="onClickSearchIcon"
      @keyup.enter="onClickSearchIcon"
      @update="(value) => keyword = value"
    />
    <hm-ui-search-input
      readonly
      @like-icon-click="onClickLikeIcon"
      @search-icon-click="onClickReadonly"
    />
    <hm-ui-text label="우리동네 간단설명 텍스트"></hm-ui-text>
    <hm-ui-user-input></hm-ui-user-input>
  </div>
</template>
<script>
export default {
  name: 'Main',
  components: {
  },
  data () {
    return {
      keyword: ''
    }
  },
  methods: {
    onClickButton () {
      alert('button click')
    },
    onIconClick () {
      alert('icon click')
    },
    onClickBackIcon () {
      alert('search back icon click')
    },
    onClickLikeIcon () {
      alert('search like icon click')
    },
    onClickSearchIcon () {
      alert('검색한다' + this.keyword)
    },
    onClickReadonly () {
      alert('검색창으로 넘어갈꺼임!')
    }
  }
}
// window.onload = function () {
//   var target = document.querySelector('.user-input')
//   target.setAttribute('placeholder', '아이디를 입력해주세요.')
//   // target.setAttribute('type', 'password')
//   target.setAttribute('type', 'text')
// }
</script>

<style lang="scss">

</style>
