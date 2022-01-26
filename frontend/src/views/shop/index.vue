<template>
  <div class="inner">
    <div class="inner-main border">
      <p class="mypage">마이페이지</p>
      <div class="icon-group">
        <hm-ui-icon class="icon-bell" name="icon-bell-bk"></hm-ui-icon>
        <hm-ui-icon class="icon-setting" name="icon-setting-bk"></hm-ui-icon>
      </div>
    </div>

    <div class="inner-main" style="padding: 15px 19px;">
      <div class="flex">
        <div class="user-image"><hm-ui-icon name="icon-user"></hm-ui-icon></div>
        <div>
          <p class="user-shopname padding">상점이름</p>
          <div class="padding">상점문의</div>
          <div class="flex">
            <hm-ui-icon name="icon-star"></hm-ui-icon>
            <hm-ui-icon name="icon-star"></hm-ui-icon>
            <hm-ui-icon name="icon-star"></hm-ui-icon>
            <hm-ui-icon name="icon-star"></hm-ui-icon>
            <hm-ui-icon name="icon-star"></hm-ui-icon>
          </div>
        </div>
      </div>
      <hm-ui-icon class="icon-menu" name="icon-angle-right-bk"></hm-ui-icon>
    </div>

    <div class="inner-advertisement">
      광고 베너
    </div>

    <div class="inner-main border">
      <div class="box-grey"><hm-ui-icon class="icon-menu" name="icon-like-on"></hm-ui-icon>찜</div>
      <div class="box-grey"><hm-ui-icon class="icon-menu" name="icon-bill-bk"></hm-ui-icon>후기</div>
      <div class="box-grey"><hm-ui-icon class="icon-menu" name="icon-review-bk"></hm-ui-icon>거래내역</div>
      <div class="box-grey"><hm-ui-icon class="icon-menu" name="icon-service-bk"></hm-ui-icon>고객센터</div>
    </div>

    <div>
      <div>
        <button>판매중</button>
        <button>판매예약 . 완료</button>
      </div>
      <div>
        <hm-ui-icon name="icon-product-none-g"></hm-ui-icon>
      </div>
    </div>
  </div>
</template>

<script>
export default {

}
</script>

<style lang="scss">
.border {
  border: 1px solid #F5F5F5;
  border-width: 0 0 1px 0;
}
.padding {
  padding-bottom: 5px;
}
.inner-main {
  display: flex;
  padding: 0 19px;
  justify-content: space-between;
  align-items: center;
  .mypage {
    font-family: Roboto;
    font-size: 18px;
    line-height: 21px;
    font-weight: bold;
    letter-spacing: -0.24px;
    margin: 17px 0;
  }
  .icon-group {
    display: flex;
  }
  .icon-bell {
    width: 21px;
    margin-right: 11px;
  }
  .icon-setting {
    margin-right: 2px;
  }
  .box-grey {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }
  .icon-menu {
    margin-bottom: 7px;
  }
  .user-image {
    margin-right: 18px;
  }
  .user-shopname {
    font-family: Roboto;
    font-style: normal;
    font-weight: 500;
    font-size: 22px;
    line-height: 26px;
    letter-spacing: -0.24px;
  }
  .flex {
    display: flex;
  }
}
.inner-advertisement {
  width: 100%;
  background: #F5F5F5;
  height: 30px;
}
</style>
