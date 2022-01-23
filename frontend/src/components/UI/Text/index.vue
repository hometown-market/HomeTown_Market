<template>
  <div class="hm-text">
    <div class="text-label">{{ label }}</div>
  </div>
</template>

<script>

export default {
  name: 'hm-ui-text',
  props: {
    label: { type: String }
  }
}
</script>

<style lang="scss">

.hm-text {
  color: #C8C8C8;
  font-size: 12px;
  line-height: 14px;
  font-weight: bold;
  display: flex;
  justify-content: flex-end;
  margin-top: 5px;
}
</style>
