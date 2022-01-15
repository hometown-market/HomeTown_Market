<template>
  <div class="product-item">
    Product Item
    {{ data.product_title}}
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
  }
}
</script>

<style lang="scss">
.product-item {
}
</style>
