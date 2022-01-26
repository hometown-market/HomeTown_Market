<template>
  <button :class="['hm-button', color, size, { shadow: shadow} ]" @click.prevent="$emit('click')">
    <div class="btn-label">{{ label }}</div>
  </button>
</template>

<script>

export default {
  name: 'hm-ui-button',
  props: {
    label: { type: String },
    color: { type: String },
    size: { type: String },
    shadow: { type: Boolean }
  }
}
</script>

<style lang="scss">
$primary: #FED07A;

.hm-button {
  border: none;
  display: flex;
  padding: 16px 27px;
  border-radius: 16px;
  justify-content: center;
  margin-top: 180px;

  &.white {
    background-color: #ffffff;
    color: $primary;
    border: 1px solid #E0E0E0;
  }

  &.primary {
    background-color: $primary;
    color: #ffffff;
  }

  &.w100 {
    width: 100%;
  }

  &.shadow {
    box-shadow: 2px 2px 2px rgba(0, 0, 0, 0.25);
  }
}
</style>
