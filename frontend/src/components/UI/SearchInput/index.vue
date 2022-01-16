<template>
  <div class="hm-search-input">
    <div class="left-container" v-if="type !== 'main-readonly'">
      <hm-ui-icon name="icon-back-bk" @icon-click="onBackIconClick"></hm-ui-icon>
    </div>
    <div class="input-container" @click="onClickInputContainer">
      <input
        ref="$input"
        v-model="$value"
        @input="e => $emit('input', e.target.value)"
        @focus="e => $emit('focus', e)"
        @blur="e => $emit('blur', e)"
        @keyup="e => $emit('keyup', e)"
        @keydown="e => $emit('keydown', e)"
        @keypress="e => $emit('keypress', e)"
        placeholder="검색어를 입력해주세요"
        :disabled="readonly"
      />
      <hm-ui-icon name="icon-search-pc" @icon-click="onSearchIconClick"></hm-ui-icon>
    </div>
    <div class="right-container" v-if="type === 'main-readonly'">
      <hm-ui-icon name="icon-like-on" @icon-click="onLikeIconClick"></hm-ui-icon>
    </div>
  </div>
</template>

<script>
const TYPES = ['nomal', 'main-readonly', 'search-readonly']

export default {
  name: 'hm-ui-search-input',
  props: {
    type: {
      type: String,
      default: 'nomal',
      validator: type => TYPES.includes(type.toLowerCase())
    },
    value: {
      type: [String, Number]
    }
  },
  computed: {
    $value: {
      get () {
        return this.value
      },
      set (newValue) {
        this.$emit('update', newValue)
      }
    }
  },
  data () {
    return {
      readonly: false
    }
  },
  created () {
    if (this.type === 'main-readonly' || this.type === 'search-readonly') {
      this.readonly = true
    }
  },
  mounted () {
    if (!this.readonly) {
      this.$refs.$input.focus()
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
    },
    onClickInputContainer () {
      if (this.readonly) {
        this.onSearchIconClick()
      }
    }
  }
}
</script>

<style lang="scss">
.hm-search-input {
  display: flex;
  height: 40px;
  padding: 16px 19px;

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
