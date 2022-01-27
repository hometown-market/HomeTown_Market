<template>
  <div class='modal'>
    <div class='overlay'>
      <div class="modal-card">
        <div class="close-modal" @click="$emit('close-modal')"><img src="@/assets/icons/icon-close-bk.svg"/></div>
        <div class="title">우동마켓</div>
        <div class="btn-list">
          <button class="btn-style" href="http://15.165.216.62:8080/api/oauth/google">
            <img class="btn-image" src="@/assets/logo/logo-google.svg"/>
            구글로 시작하기
          </button>
          <button class="btn-style" href="http://15.165.216.62:8080/api/oauth/naver">
            <img class="btn-image" src="@/assets/logo/logo-naver.svg"/>
            네이버로 시작하기
          </button>
          <button class="btn-style" href="http://15.165.216.62:8080/api/oauth/facebook">
            <img class="btn-image" src="@/assets/logo/logo-facebook.svg"/>
            페이스북으로 시작하기
          </button>
          <button class="btn-style last" v-on:click="login">
            <img src="@/assets/logo/logo-main.svg" style="width: 77px"/>
            으로 시작하기
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  methods: {
    login: function (event) {
      if (event) {
        this.$router.push('/login')
        this.$emit('close-modal')
      }
    }
  }
}
</script>

<style scope lang="scss">
.modal,
.overlay {
  width: 100%;
  height: 100%;
  position: fixed;
  left: 0;
  top: 0;
}
.overlay {
  background-color: rgba(0, 0, 0, 0.5);
}
.modal-card {
  position: relative;
  max-width: 420px;
  margin: auto;
  margin-top: 50px;
  padding: 20px 20px 50px 20px;
  background-color: white;
  z-index: 10;
  opacity: 1;
  border-radius: 20px;
}
.btn-list {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.btn-style {
  margin: 7px 12px;
  background-color: white;
  width: 85%;
  padding: 14px;
  border-radius: 20px;
  border: white;
  font-size: 14px;
  line-height: 19px;
  box-shadow: 2px 2px 2px rgba(0, 0, 0, 0.25);
  display: flex;
  justify-content: center;
  align-items: center;
  .btn-image {
    margin-right: 6px;
  }
}
.title {
  display: flex;
  justify-content: center;
  font-size: 2.5em;
  font-weight: bold;
  color: rgb(255, 196, 0);
  margin-bottom: 50px;
}
.close-modal {
  display: flex;
  justify-content: flex-end;
  margin-bottom: 40px;
  width: 100%;
  height: 25px;
  z-index: 19;
}
@media screen and (max-width: 460px) {
  .modal-card {
    margin: 50px 10px 0 10px;
  }
}
</style>
