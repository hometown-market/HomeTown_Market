<template>
  <div class="main-container">
    메인내용입니다.
    <div class="item">아이템</div>
    <div class="item">아이템</div>
    <div class="item">아이템</div>
    <div class="item font-size">아이템</div>
  </div>
</template>
<script>
export default {
  name: 'Main',
  components: {
  },
  data () {
  }
}
</script>

<style lang="scss">
$color: #333333;
$color-red: #FF0000;

.main-container {
  background-color: $color;

  .item {
    background: $color-red;

    &.font-size {
      font-size: 50px;
    }
  }
}

</style>
