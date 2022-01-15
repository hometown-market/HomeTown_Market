<template>
  <div class="hm-search-input">
    <div class="left-container" v-if="!readonly">
      <hm-ui-icon name="icon-back-bk" @icon-click="onBackIconClick"></hm-ui-icon>
    </div>
    <div class="input-container">
      <input
        @input="e => $emit('input', e.target.value)"
        @blur="e => $emit('blur', e)"
        @keyup="e => $emit('keyup', e)"
        @keydown="e => $emit('keydown', e)"
        @keypress="e => $emit('keypress', e)"
        placeholder="검색어를 입력해주세요"
        :disabled="readonly"
      />
      <hm-ui-icon name="icon-search-pc" @icon-click="onSearchIconClick"></hm-ui-icon>
    </div>
    <div class="right-container" v-if="readonly">
      <hm-ui-icon name="icon-like-on" @icon-click="onLikeIconClick"></hm-ui-icon>
    </div>
  </div>
</template>

<script>
export default {
  name: 'hm-ui-search-input',
  props: {
    readonly: {
      type: Boolean,
      default: false
    }
  },
  methods: {
    onBackIconClick () {
      this.$emit('back-icon-click')
    },
    onLikeIconClick () {
      this.$emit('like-icon-click')
    },
    onSearchIconClick () {
      this.$emit('search-icon-click')
    }
  }
}
</script>

<style lang="scss">
.hm-search-input {
  display: flex;
  height: 40px;
  padding: 16px 20px;

  .input-container {
    background-color: #F0F0F0;
    border-radius: 19px;
    padding: 12px 15px;
    display: flex;
    flex: 1;
    input {
      border: none;
      background-color: transparent;
      height: 100%;
      width: 100%;

      &:focus {
        outline: none;
      }
    }
  }

  .left-container {
    align-self: center;
    margin-right: 5px;
  }

  .right-container {
    align-self: center;
    margin-left: 5px;
  }
}
</style>
