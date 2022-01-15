<template>
  <select v-model="$value" class="hm-select">
    <option v-for="item in options" :label="item.label" :value="item.id" :key="item.id" ></option>
  </select>
</template>

<script>

export default {
  name: 'hm-ui-select',
  props: {
    value: String
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
      options: [
        {
          id: 'price_asc',
          label: '높은 가격순'
        },
        {
          id: 'price_desc',
          label: '낮은 가격순'
        },
        {
          id: 'date',
          label: '최신순'
        },
        {
          id: 'views',
          label: '조회순'
        }
      ]
    }
  }
}
</script>

<style lang="scss">
$primary: #FED07A;

.hm-select {
}
</style>
