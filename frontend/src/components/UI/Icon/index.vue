<template>
  <div class="hm-icon" @click="$emit('icon-click')">
    <img v-if="type === 'svg'" :src="require(`@/assets/icons/${name}.svg`)" />
    <img v-else-if="type === 'png'" :src="require(`@/assets/icons/${name}.png`)" />
  </div>
</template>

<script>
const TYPE = ['png', 'svg']

export default {
  name: 'hm-ui-icon',
  props: {
    name: { type: String },
    type: {
      type: String,
      default: 'svg',
      validator: type => TYPE.includes(type.toLowerCase())
    },
    size: { type: [String, Number] }
  }
}
</script>

<style lang="scss">
$primary: #FED07A;

.hm-icon {
  display: flex;
  align-items: center;

  img {
    vertical-align: top;
  }
}
</style>
