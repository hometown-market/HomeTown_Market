<template>
  <div class="hm-icon" @click.stop="$emit('icon-click')">
    <img v-if="type === 'svg'" :src="require(`@/assets/icons/${name}.svg`)" />
    <img v-else-if="type === 'png'" :src="require(`@/assets/icons/${name}.png`)" />
  </div>
</template>

<script>
const TYPES = ['png', 'svg']

export default {
  name: 'hm-ui-icon',
  props: {
    name: { typeS: String },
    type: {
      type: String,
      default: 'svg',
      validator: type => TYPES.includes(type.toLowerCase())
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
  cursor: pointer;

  img {
    vertical-align: top;
  }
}
</style>
