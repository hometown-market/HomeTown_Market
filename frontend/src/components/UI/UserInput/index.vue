<template>
  <div class="hm-user-input">
    <input
      :class="[ 'hm-input-test', placeholder, type, {error: error} ]"
      v-model="$value"
      :placeholder="placeholder"
      :type="type"
    >
    <hm-ui-text :label="errorMessage" size="w100"></hm-ui-text>
  </div>
</template>

<script>
export default {
  name: 'hm-ui-user-input',
  model: {
    prop:
      'value'
  },
  props: {
    placeholder: {
      type: String
    },
    type: {
      type: String
    },
    value: {
      type: String
    },
    error: {
      type: Boolean
    },
    errorMessage: {
      type: String,
      default: ''
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
  }
}
</script>

<style lang="scss">
.hm-user-input {
  display: flex;
  flex-direction: column;
  input{
    border-left: none;
    border-right: none;
    border-top: none;
    border-bottom: 1px solid;
    font-size: 15px;
    color: #C8C8C8;
    margin-top: 31px;
    outline: none;
    padding-bottom: 4px;
  }

  .error {
    color: red; // 임시 error css
  }
}
</style>
