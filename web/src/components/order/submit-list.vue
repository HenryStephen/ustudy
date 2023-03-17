<template>
  <div class="offcngwc_kongbox">
    <div class="offcngwc_ddqrcon1">
      <div class="offcngwc_kongcon1_0">
        <dl id="Car">
          <dt>核对订单 </dt>
          <dd class="car_2" id="car_2">
            <div class="stepBar2">
              &nbsp;
              </div>
            <div><span class="gwc_tit1">我的购物车</span><span class="gwc_tit1">填写核对订单</span><span class="gwc_tit2">成功提交订单</span></div>
          </dd>
        </dl>
      </div>
      <div class="offcngwc_ddqrcon2">
        <input type="hidden" name="isuserid" value="13761330">
        <div class="offcngwc_ddqrcon2_1">支付方式 </div>
        <div class="payMethod">
          <ul class="clearfix">
            <li class="on"><input name="pay_type" id="pay_type" type="hidden" value="1" checked="">在线支付</li>
          </ul>
        </div>
        <div class="offcngwc_ddqrcon2_1">课程清单 <a href="/class-165633/" class="blue">[返回课程页面]</a></div>
        <div class="offcngwc_ddqrcon2_4">
          <ul>
            <li v-for="item in courseData" :key="item.id">
              <a href="/class-165633/" target="_blank">{{item.name}}</a>
              <span class="orange">
                <font class="FontFA orange">¥{{item.presentPrice}}</font>
              </span>
            </li>
          </ul>
        </div>
        <!--正式优惠券start-->
        <div class="zg_zf" style="margin-top:22px;padding:0 15px;overflow:hidden;border-bottom:1px solid #ddd;">
          <div class="zg_yhj" style="height:32px;" @click="openCoupon = !openCoupon">使用优惠券</div>
          <div class="zg_cn" v-if="openCoupon" style="overflow:hidden;padding-bottom:63px;">
            <div class="hd" style="height:29px;">
              <li class="on" @click="avalCoupon = true">可用优惠券</li>
              <li @click="avalCoupon = false">不可用优惠券</li>
            </div>
            <div v-if="avalCoupon" id="bounded" class="bd" style="border:1px solid #ddd;padding:14px 0 0 14px;width:674px;height:auto;margin:0;">
              <p style="color:#b2b2b2;height:32px;">当前订单无可用的优惠券</p>
              <!--此处js插入数据-->
              <div class="bd_yh" style="margin-bottom:20px;margin-top:10px;">我有优惠券码/实体卡券
                <input id="newbang" type="text" class="zg_te1" value="">
                <button id="bang" class="button" style="cursor:pointer;">激活优惠券</button>&nbsp;&nbsp;<span id="xianshi"></span>
              </div>
            </div>
            <div v-if="!avalCoupon" class="bd" id="bounded1" style="border:1px solid #ddd;padding:14px 0 0 14px;width:674px;height:auto;margin:0;">
              <p style="color:#b2b2b2;height:32px;">没有不可用优惠券</p>
              <div class="bd_yh">&nbsp;</div>
            </div>
          </div>
          <table cellpadding="0" cellspacing="0" border="0" class="zg_tab2 fr" style="width:272px;margin-right:28px;">
            <tbody>
              <tr>
                <td style="color:#666;">{{courseCount}}个课程，总价格：</td>
                <td class="tab1"><strong class="orange"><font class="FontFA orange">￥ </font>{{totalMoney}}</strong></td>
              </tr>
              <tr>
                <td style="color:#666;">优惠：</td>
                <td class="tab1"><strong class="orange"><font class="FontFA orange">－￥ </font><font id="youhuijia">0.00</font></strong></td>
              </tr>
              <tr>
                <td style="color:#666;">应付总额：</td>
                <td class="tab1"><strong class="orange"><font class="FontFA orange">￥ </font><font id="yingfukuan">{{totalMoney}}</font></strong></td>
              </tr>
          </tbody>
          </table>
        </div>
        <!--优惠券end-->
        <div class="offcngwc_ddqrcon2_6" style="margin-right: 39px;">
          <dl>
            <dd>
              <input type="hidden" id="direct_trade_create_req" value="1">
              <input type="hidden" id="courseId" value="165633">
              <input type="hidden" id="youhui" value="">
              <input type="hidden" id="coupon_code" value="">
              <input type="hidden" id="area_code" value="">
              <input type="hidden" id="agge" value="">
              <input type="hidden" id="scode" value="">
              <input type="hidden" id="pintuan_active_id" value="0">
              <input type="hidden" id="jstype" value="5">
              <p style="margin-bottom: 10px;line-height: 28px;">当前账号为：<font color="red" style="font-size:26px;margin-right:15px">{{curPhone}}</font>下单后订单将与该账号绑定</p>
              <input @click="submitOrder" type="button" name="dosubmit" value="提交订单" class="gwc_ddqrtj orange_bg" id="J_submit_orders">
            </dd>
          </dl>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { getRequest, postRequest } from '@/api/index.js'
export default {
  name: 'SubmitList',
  props: {
    courseData: {
      type: Array,
      default: function () {
        return []
      }
    }
  },
  mounted () {
    getRequest('/user/' + localStorage.getItem('userId')).then(res => {
      if (res.data.status === 200) {
        this.curPhone = res.data.data.telephone
      }
    })
  },
  computed: {
    courseCount () {
      return this.courseData.length
    },
    totalMoney () {
      let money = 0.00
      this.courseData.forEach(item => {
        money = money + item.presentPrice
      })
      return money
    },
    courseIdList () {
      var list = []
      this.courseData.forEach(item => {
        list.push(item.id)
      })
      return list
    }
  },
  data () {
    return {
      openCoupon: false,
      avalCoupon: true,
      curPhone: '',
    }
  },
  methods: {
    submitOrder () {
      postRequest('/order', {
        courses: this.courseIdList
      }).then(res => {
        console.log(res)
        if (res.data.status) {
          this.$router.push({
            name: 'Payment',
            params: {
              orderId: res.data.data,
              courseData: this.courseCount
            }
          })
        }
      })
      
    }
  }
}
</script>

<style lang="less">
@import './order.less';
@import '../shopcart/shopcart.less';
</style>
