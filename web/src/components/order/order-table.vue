<template>
  <div class="zg_ddanxxi">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="zg_ddanxxi_table">
      <input type="hidden" value="0" id="time_flag">
      <input type="hidden" value="-1" id="order_status">
      <tbody>
        <tr>
          <th width="35%">订单信息</th>
          <th width="13%">订单金额</th>
          <th width="13%">
            <Select v-model="selectTime" style="width:100px">
              <Option v-for="item in timeList" :value="item.value" :key="item.value">{{ item.label }}</Option>
            </Select>
            <!-- <select name="time_flag" @change="check_form(value,'time_flag')">
              <option value="0" selected="">请选择</option>
              <option value="3">最近三个月</option>
              <option value="2">最近两个月</option>
              <option value="1">最近一个月</option>
            </select> -->
          </th>
          <th width="11%">
            
            <Select v-model="selectState" style="width:100px">
              <Option v-for="item in stateList" :value="item.value" :key="item.value">{{ item.label }}</Option>
            </Select>
            <!-- <select name="order_status" onchange="check_form(this.value,'order_status')">
              <option value="-1">全部状态</option>
              <option value="99">未付款</option>
              <option value="1">已完成</option>
              <option value="4">已取消</option>
            </select> -->
          </th>
          <th width="14%">快递单号</th>
          <th width="15%">操作</th>
        </tr>
        <!-- <tr v-for="item in orderData" :key="item.id"> -->
          
        <tr rowspan='2' v-for="item in orderData" :key="item.id">
          <td colspan="6" class="bk">
            <div class="order">
              订单号：{{item.orderId}}
            </div>
            <div class="order_body" v-for="course in item.courseViewDTOList" :key="course.courseId">
              <a href="/class-146627/" style="margin-left:0;" target="_blank">{{course.courseName}}</a>
              <span style="margin-left:50px" class="FontFA">¥{{course.coursePrice}}</span>
              <font style="margin-left:50px">2020-09-14 08:50:44</font>
              <font style="margin-left:36px;">已完成</font>
              <a href="/orders/orders_detail_new/order_id/38012113/" class="body_oper" target="_blank" >查看</a>
            </div>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import { getRequest } from '@/api/index.js'
export default {
  name: 'OrderTable',
  data () {
    return {
      orderData: [],
      selectTime: '',
      selectState: '',
      timeList: [
        { value: 3, label: '最近三个月' },
        { value: 2, label: '最近两个月' },
        { value: 1, label: '最近一个月' }
      ],
      stateList: [
        { value: 'all', label: '全部' },
        { value: 'nopay', label: '未付款' },
        { value: 'finfish', label: '已完成' },
        { value: 'cancel', label: '已取消' }
      ]

    }
  },
  mounted () {
    this.getOrders()
  },
  methods: {
    getOrders () {
      getRequest('/user/' + localStorage.getItem('userId') +'/order').then(res => {
        console.log(res)
        if (res.data.status === 200) {
          this.orderData = res.data.data
        }
      })
    },
    check_form () {
      console.log('select')
    }
  }
}
</script>

<style lang="less">
.zg_ddanxxi{ width:976px; border:solid 1px #ddd; float:right; background:#fff;}
.zg_tuikuan_bot .tijiao:hover{background:#d04000; text-decoration:none;}
.zg_ddanxxi_table{ width:100%;color:#333;}
.zg_ddanxxi_table th{ height:40px; background:#e7e7e7; font-weight:normal;}
.zg_ddanxxi_table td.bk{ background:#f8f8f8; padding-left:32px; text-align:left;}
.zg_ddanxxi_table td{ line-height:25px; padding:14px 0; text-align:center;}
.zg_ddanxxi_table td.xinxi{ padding-left:32px; text-align:left;}
.zg_ddanxxi_table td font{ color:#666;}
.zg_ddanxxi_table td a{  line-height:20px;}
.zg_ddanxxi_table td .on{ display:inline-block; text-decoration:none; background:#ff4a00; padding:0 5px; color:#fff; line-height:20px;}
.zg_ddanxxi_table td .on:hover{background:#ff4a00;}
.order {
  width: 800px;
}
// .order_body {
//   display: inline;
// }
// .order_body *{
//   margin-left: 75px;
// }
.body_oper {
  float: right;
  margin-right: 54px;;
}
</style>
