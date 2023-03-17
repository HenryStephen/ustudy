<style lang='less'>
  @import './detail.less';
</style>

<template>
  <div class="fr offcn19_xqright">
    <Modal v-model="modal" width="300px" :styles="{top: '200px'}">
      <p>咨询电话18515485507</p>
    </Modal>
    <div class="offcn19_kcsp ">
      <div class="offcn19_spbox">
        <img :src="courseData.previewPicture" :alt="courseData.name"
                style="height: 196px;width: 350px;">
      </div>
      <p class="offcn19_price">
        <i class="zgmoreone" more="1.00">
          <i>￥</i>{{courseData.presentPrice}}</i>          
        <span>原价￥{{courseData.originalPrice}}</span>
      </p>
      <div class="offcn19_bmnum clearfix">
        101人已报名
        <em id="offcn19_bmnum">
        </em>
        <a class="fr offcn19_starb " id="house" data-id="">收藏
        </a>
        <div class="offcn19_share fr">
          <span>分享</span>
          <div class="lh_sildewm">
            <span class="lh_sanjiao"></span>
            <p id="qrcodeCanvas"></p>
            <p>打开微信，点击发现<br>使用扫一扫分享</p>
          </div>
        </div>
      </div>
      <p class="offcn19_ccouple"></p>
      <div id="woyaobaoming">
        <a class="offcn19_signbtn offcn19_fenleibtn Q-directbuy-btn" @click="submitOrder"
            data-package_type="" data-agge_id="">我要报名</a>
      </div>
      <a class="offcn19_signbtn offcn19_fenleibtn Q-buy-btn" href="javascript:void(0);"
        data-package_type="" data-agge_id="">加入购物车</a>
      <div class="offcn19_zxbtn">
        <a @click="modal = true" class="offcn19_zxtel">电话咨询</a>
        <a href="javascript:void(0);" target="_blank">在线咨询</a>
      </div>
      <div class="offcn19_plat">本课程支持以下设备缓存听课
        <div class="offcn19_plats">
          <span class="offcn19_plat1"></span>
          <div>
            <em></em>
            <img src="@/assets/images/home/ujyx_26.jpg" alt="">
          </div>
        </div>
        <div class="offcn19_plats">
          <span class="offcn19_plat2"></span>
          <div>
            <em></em>
            <img src="@/assets/images/home/ujyx_26.jpg" alt="">
          </div>
        </div>
        <div class="offcn19_plats">
          <span class="offcn19_plat3"></span>
          <div>
            <em></em>
            <img src="@/assets/images/home/ujyx_26.jpg" alt="">
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'DetailCard',
  props: {
    courseData: {
      type: Object,
      default: function () {
        return {}
      }
    }
  },
  data () {
    return {
      modal: false
    }
  },
  methods: {
    submitOrder () {
      this.$router.push({
        name: 'SubmitOrder',
        params: {
          courseDetail: this.courseData
        }
      })
    }
  }
}
</script>
