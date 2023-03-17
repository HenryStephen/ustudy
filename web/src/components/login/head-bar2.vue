<template>
  <div class='eTop_nav'>
    <div class="menu">
      <Menu mode="horizontal" active-name="1">
        <MenuITem>
          <img src="@/assets/images/head/logo_it.jpg" class="eLogo" />
        </MenuITem>
        <MenuItem name="1" to='/'>首页</MenuItem>
        <MenuItem name="2">在线就业班</MenuItem>
        <Submenu name="3">
          <template slot="title">精品课程</template>
          <MenuGroup>
            <MenuItem name="3-1">免费课程</MenuItem>
            <MenuItem name="3-2">精品网课</MenuItem>
          </MenuGroup>
        </Submenu>
        <MenuItem name="4">在线直播</MenuItem>
        <MenuItem name="5">IT培训机构</MenuItem>
        <MenuItem name="6">
          <div class="e_nd appXZ">
            <a target="_blank" rel="nofollow" style="color:#ff4905;border:1px solid #ff4905;border-radius: 6px;">
              APP下载
            </a>
            <dl class="zg_uxEwm">
              <dt><img src="@/assets/images/head/ujyx_16.jpg" alt="IT优学APP"></dt>
              <dd>扫码下载<br>IT优学APP</dd>
            </dl>
          </div>
        </MenuItem>
      </Menu>
    </div>
      <div class="eTopRight">
        <div class="search_input">
          <Input search placeholder="请输入" />
        </div>
        <div class="eUser">
          <p class="eLogin" v-if="!isLogin">
            <a href="/login/" rel="nofollow">登录</a>
            <a href="/register/" rel="nofollow">注册</a>
          </p>
          <a v-if="isLogin" @click="userOpen = !userOpen">
            <span id="eUserpic">
            <img src="@/assets/images/home/dftouxiang.jpg" width="30" height="30">
            </span>
          </a>
          <div class="eUserBox" v-if="userOpen" >
            <a href="/myCourse" rel="nofollow">我的课程</a>
            <a href="/collection" rel="nofollow">我的收藏</a>
            <a href="/myOrder" rel="nofollow">我的订单</a>
            <a href="/user/index" rel="nofollow">帐号设置</a>
            <a @click="outlogin()" href="javascript:;" rel="nofollow">退出登录</a>
          </div>
        </div>
        <div class="zg_index_sys">
          <span>&nbsp;</span>
          <div class="zg_index_ewm">
            <div class="zg_index_de">
              <img src="@/assets/images/home/ujyx_16.jpg" alt="IT优学APP">
              <p>扫码下载<br>IT优学APP</p>
            </div>
            <div class="zg_index_de">
              <img src="@/assets/images/home/ujyx_17.jpg" alt="中公优就业">
              <p>扫码关注<br>中公优就业</p>
            </div>
          </div>
        </div>
        <div class="eTopShopCart">
          <a class="eTopcen" rel="nofollow" href="/shopcart">
            <span id="J-shoping-num"></span>
          </a>
        </div>
      </div>
  </div>
</template>
<script>
export default {
  mounted () {
    if (localStorage.getItem('userId')) {
      this.isLogin = true
    }
    else this.isLogin = false
    this.$forceUpdate()
  },
  data () {
    return {
      isLogin: false,
      userOpen: false
    }
  },
  methods: {
    outlogin () {
      localStorage.setItem('userId', '')
      localStorage.setItem('token', '')
      this.$router.push('/login')
      this.isLogin = false
    }
  }
}
</script>

<style lang='less'>
@import './login.less';
.eTop_nav { position: relative; left: -30px; margin-bottom: -7px;}
.eTop_nav li{ font-size: 16px; color: #ff4905}
.eLogo{ padding-top: 10px;margin-right: 50px;}
.appXZ a{ position: relative;margin-right: 0;width: 85px;height: 30px;display: inline-block;text-align: center;line-height: 30px;}
</style>
