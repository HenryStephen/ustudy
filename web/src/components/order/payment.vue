<template>
  <div>
    <div class="zg_gouwu">
      <div style="clear:both; height:50px;">
	      <div class="offcngwc_kongcon1_0">
		    	<dl id="Car">
            <dd class="car_1" id="car_1">
              <div class="stepBar3">
                &nbsp; 
              </div>
              <div>
                <span class="gwc_tit1">课程详情</span>
                <span class="gwc_tit1">填写核对订单</span>
                <span class="gwc_tit1">成功提交订单</span>
              </div>
            </dd>
          </dl>
        </div>
      </div>
      <div class="zg_gouwu_dingdan">
	      <p>
          <img src="http://xue.ujiuye.com/public/images/zg_gou.jpg" width="19" height="16">订单提交成功！请您尽快支付!<br>当前账号为：<font color="red" style="font-size:30px;margin-right:15px">17349869382</font>
          <em style="padding:0px;">
            <a href="/orders/myorders/" class="orange">查看我的订单</a>
          </em>
        </p>
	    </div>
      <div class="zg_gouwu_yhka">
        <div class="zg_gerenmain_right_bt" style="height:34px;">
	      	<a href="javascript:void(0)" :class="curPayMed == 'Alipay' ? 'hover':''" id="xz3" @click="zfbPay" style="border-left:solid 1px #ddd;">支付宝帐号</a>
          <a href="javascript:void(0)" id="xz4" :class="curPayMed == 'WeChat' ? 'hover':''" @click="weixinPay">微信扫码支付</a>
          <a @click="cxkPay" id="xz1" :class="curPayMed == 'cxk' ? 'hover':''">储蓄卡</a>
          <a @click="xykPay" id="xz2" :class="curPayMed == 'xyk' ? 'hover':''">信用卡</a>
        </div>
        <div class="end_box">
          <div v-if="isCxk" class="fs_zf" id="fs1" style="display: none;">
          <p> </p>
          <div class="tab_bk">
            <ul>
              <li><a href="javascript:;" name="alitype" value="JSYH"><img src="http://xue.ujiuye.com/public/images/pay/zg_cx_07.jpg" width="106" height="37" alt="银行"></a></li>
              <li><a href="javascript:;" name="alitype" value="JTYH"><img src="http://xue.ujiuye.com/public/images/pay/zg_cx_11.jpg" width="106" height="37" alt="银行"></a></li>
              <li><a href="javascript:;" name="alitype" value="ZGYH"><img src="http://xue.ujiuye.com/public/images/pay/zg_cx_13.jpg" width="106" height="37" alt="银行"></a></li>
              <li><a href="javascript:;" name="alitype" value="GSYH"><img src="http://xue.ujiuye.com/public/images/pay/zg_cx_23.jpg" width="106" height="37" alt="银行"></a></li>
              <li><a href="javascript:;" name="alitype" value="BJYH"><img src="http://xue.ujiuye.com/public/images/pay/zg_cx_26.jpg" width="106" height="37" alt="银行"></a></li>
              <li><a href="javascript:;" name="alitype" value="PFYH"><img src="http://xue.ujiuye.com/public/images/pay/zg_cx_37.jpg" width="106" height="37" alt="银行"></a></li>
              <li><a href="javascript:;" name="alitype" value="GFYH"><img src="http://xue.ujiuye.com/public/images/pay/zg_cx_38.jpg" width="106" height="37" alt="银行"></a></li>
              <li><a href="javascript:;" name="alitype" value="NYYH"><img src="http://xue.ujiuye.com/public/images/pay/zg_cx_43.jpg" width="106" height="37" alt="银行"></a></li>
              <li><a href="javascript:;" name="alitype" value="YZCX"><img src="http://xue.ujiuye.com/public/images/pay/zg_cx_46.jpg" width="106" height="37" alt="银行"></a></li>
            </ul>
          </div>
        </div>
        <div v-if="isXyk" class="fs_zf" id="fs2" style="display: none;">
          <p> </p>
          <div class="tab_bk">
            <ul>
              <li><a href="javascript:;" name="alitype" value="JSYH"><img src="http://xue.ujiuye.com/public/images/pay/zg_cx_07.jpg" width="106" height="37" alt="银行"></a></li>
              <li><a href="javascript:;" name="alitype" value="ZGYH"><img src="http://xue.ujiuye.com/public/images/pay/zg_cx_13.jpg" width="106" height="37" alt="银行"></a></li>
              <li><a href="javascript:;" name="alitype" value="GSYH"><img src="http://xue.ujiuye.com/public/images/pay/zg_cx_23.jpg" width="106" height="37" alt="银行"></a></li>
              <li><a href="javascript:;" name="alitype" value="NYYH"><img src="http://xue.ujiuye.com/public/images/pay/zg_cx_43.jpg" width="106" height="37" alt="银行"></a></li>
            </ul>
          </div>
        </div>
        <div v-if="isZfb" class="fs_zf" id="fs3" style="">
          <p> </p>
          <div class="tab_bk" id="zfb_tab">
            <ul>
              <li>
                <a href="javascript:;" name="alitype" value="" class="zg_click_kc edu_click">
                <img src="http://xue.ujiuye.com/public/images/pay/zfb_07.jpg" width="140" height="37" alt="支付宝">
                </a>
              </li>
            </ul>
          </div>
        </div>
        <!-- 微信 -->
        <div v-if="isWx" class="fs_zf" id="fs4">
          <div class="offcn_erweima" id="weixin_tab">
            <!-- <h3>微信支付</h3> -->
            <p class="picbox">
              <span class="ts" style="display: none;">网速好慢，二维码加载中...</span>
              <img id="ewm" alt="网速好慢，二维码加载中..." src="@/assets/images/order/wxcode.png" style="display: block;">
            </p>
            <span class="img">请使用微信扫描二维码<br>以完成支付</span>
            <div class="success">
              <a href="/orders/orders_detail_new/order_id/38638915/" target="">我已完成支付</a>
            </div>
        </div>
      </div>
      <!-- 微信end -->
      <a @click="payOrder" class="qr_zf_fs car_btn" id="getUrllong" target="_blank" style="display: block;">确认支付方式</a>
    </div>
    <div class="zg_zhifu" id="zfu" style="display: none;">
      <div class="zg_zhifu_bt tck_bg">
        <span onclick="javascript:turnoff('zfu');javascript:turnoff('zhifu')">×</span>支付
      </div>
      <p>请您在新打开的网上银行页面进行支付，支付完成前<br>请不要关闭该窗口。</p>
      <p style="padding-left:0; text-align:center;">
        <a href="/orders/orders_detail_new/order_id/38638915/" class="qued tck_bg">支付已完成</a>
	    </p>
      <p>
        <a href="javascript:;" class="orange" onclick="$('#zfu').hide();$('#zhifu').hide();">返回重新选择银行</a>
      </p>
    </div>
    <div class="popIframe" id="zhifu" style="display: none;"></div>
  </div>
    <input type="hidden" id="hrefs" value="/orders/getOrderNew/order_id/38638915/">
    </div>
  </div>
</template>

<script>
export default {
  name: 'Payment',
  data () {
    return {
      orderId: '',
      courseData: [],
      curPayMed: 'Alipay',
      isZfb: true,
      isCxk: false,
      isXyk: false,
      isWx: false
    }
  },
  mounted () {
    this.orderId = this.$route.params.orderId
    this.courseData = this.$route.params.courseData
  },
  methods: {
    zfbPay () {
      this.curPayMed = 'Alipay'
      this.isWx = false
      this.isXyk = false
      this.isCxk = false
      this.isZfb = true
    },
    weixinPay () {
      this.curPayMed = 'WeChat'
      this.isWx = true
      this.isZfb = false
      this.isXyk = false
      this.isCxk = false
    },
    cxkPay () {
      this.curPayMed = 'Cxk'
      this.isWx = false
      this.isZfb = false
      this.isXyk = false
      this.isCxk = true
    },
    xykPay () {
      this.curPayMed = 'xyk'
      this.isWx = false
      this.isZfb = false
      this.isXyk = true
      this.isCxk = false
    },
    payOrder () {
      postRequest('/payment', {
        orderId: this.orderId,
        paymentMethod: this.curPayMed
      }).then(res => {
        if (res.data.status === 200){
          this.$Message.success('支付成功')
          this.$router.push('/myOrder')
        }
      })
    }
  }
}
</script>

<style lang="less">
@import './order.less';
</style>
