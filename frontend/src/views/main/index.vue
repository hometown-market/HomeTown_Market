<template>
  <div class="main-container">
    메인내용입니다.
    <!-- hm-ui 사용법 -->
    <hm-ui-button label="우리동네 버튼" color="primary" size="w100" @click="onClickButton"></hm-ui-button>
    <hm-ui-button label="우리동네 버튼" color="white" shadow></hm-ui-button>
    <hm-ui-icon name="icon-user-bk" @icon-click="onIconClick"></hm-ui-icon>
    <hm-ui-icon name="icon-back-bk" @icon-click="onIconClick"></hm-ui-icon>
  </div>
</template>
<script>
export default {
  name: 'Main',
  components: {
  },
  methods: {
    onClickButton () {
      alert('button click')
    },
    onIconClick () {
      alert('icon click')
    }
  }
}
</script>

<style lang="scss">
.main-container {
}

</style>
