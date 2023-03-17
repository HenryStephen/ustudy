<template>
    <div class="wid1000 offcnlogin" id="loginoffcn1000">
        <div class="offcnloginleft fl">
            <form method="post" action="#" id="info_form">

                <ul>
                    <li><span>手机：</span>
                        <input name="username" v-model="registerInfo.name" @blur="checkName"  type="text" class="input262" placeholder="请输入手机号">
                        <em id="J_usernameTip" v-show="!valid.username.ok">{{valid.username.tip}}</em>
                    </li>
                    <li><span>创建密码：</span>
                        <input name="password" v-model="registerInfo.password" @blur="checkPassword" type="password" class="input262 zg_passw" placeholder="请输入密码" datatype="*6-20" nullmsg="请输入密码！" errormsg="密码应该为6-20位之间！">
                        <em id="J_passwordTip" v-show="!valid.password.ok">{{valid.password.tip}}</em>
                    </li>
                    <li><span>确认密码：</span>
                        <input name="repassword" v-model="registerInfo.repassword"  @blur="checkRePassword"   type="password" class="input262 zg_passw" placeholder="请再次输入密码" datatype="*6-20" nullmsg="请再次输入密码！" errormsg="密码应该为6-20位之间！">
                        <em id="J_repasswordTip" v-show="!valid.rePassword.ok">{{valid.rePassword.tip}}</em>
                    </li>
                    <li class="yzm"><span>验证码：</span>
                        <input name="msgpicverify" v-model="registerInfo.captcha"  @blur="checkCaptcha" id="msgpicverify" type="text" class="input262" placeholder="请输入验证码">
                        <img v-show="valid.username.ok" :src="randomCaptchaURL" @click="changmsgpic" class="yzmimg" align="absmiddle" id="msgpic">
                        <em id="J_verifymsg" v-show="!valid.captcha.ok">{{valid.captcha.tip}}</em>
                    </li>
                    <li class="yzm">
                        <span>短信验证：</span>
                        <input name="verify" v-model="registerInfo.smsCode" id="J_verify" type="text" class="input262" placeholder="请输入验证码">
                        <strong class="yzmimg1" id="J_yzm2" v-show="valid.smsCode.isShowBtn"><a @click.stop="getSmsCode" id="J_phone">免费获取短信</a></strong>
                        <strong class="yzmimg2" id="J_yzm3" v-show="!valid.smsCode.isShowBtn">{{valid.smsCode.counter}}s后重新获取</strong>
                        <em id="J_verifyTip"></em>
                    </li>
                    <li class="loginleft_2"><input name="" @click="registerUser" type="button" value="注册" class="loginbutton1 lgb_bgc"></li>
                    <li class="loginleft_4">已有账号，请<a href="/login/">登录</a></li>
                </ul>
            </form>
        </div>
        <div class="offcnloginright">
            <dl>
                <dt>用户专享服务：</dt>
                <dd>极致的视频学习体验</dd>
                <dd>丰富的直播课程免费学</dd>
                <dd>开课提醒，不错过每一次精彩</dd>
                <dd>讲、学、练、考，多种高效学习方式快速提升成绩</dd>
            </dl>
        </div>
    </div>
</template>

<script>
    import {captchaURL,checkCaptchaAPI,getSmsCodeAPI} from "../network/captcha";
    import {userSignUpAPI} from "../network/usercenter";

    export default {
        name:'UserRegister',
        created() {
            this.$emit("showCate",false)
            },
        data:function () {
            return {
                randomValue:0,
                registerInfo:{
                    name:'',
                    password:'',
                    repassword:'',
                    captcha:'',
                    smsCode:''
                },
                valid:{
                    username:{
                        ok:false,
                        tip:''
                    },
                    password:{
                        ok:false,
                        tip:''
                    },
                    rePassword:{
                        ok:false,
                        tip:''
                    },
                    captcha:{
                        ok:false,
                        tip:''
                    },
                    smsCode:{
                        isShowBtn:true,
                        counter:60
                    }

                }
            }
        },
        computed:{
            randomCaptchaURL:function () {
                return captchaURL()+'?r='+this.randomValue+'&phone='+this.registerInfo.name
            }
        },
        methods:{
            checkName:function () {
                console.log("验证手机号")
                let reg=/^1[3456789]\d{9}$/
                if(this.registerInfo.name===''||!reg.test(this.registerInfo.name)){
                    this.valid.username.ok=false
                    this.valid.username.tip='请输入有效手机号'
                }else {
                    this.valid.username.ok=true
                    this.valid.username.tip=''
                }
            },
            checkPassword:function () {
                if(this.registerInfo.password.length<6){
                    this.valid.password.ok=false
                    this.valid.password.tip="密码长度最小为6位"
                }else{
                    this.valid.password.ok=true
                    this.valid.password.tip=""
                }
            },
            checkRePassword:function () {
                if(this.registerInfo.password===this.registerInfo.repassword){
                  this.valid.rePassword.ok=true
                }else{
                   this.valid.rePassword.ok=false
                    this.valid.rePassword.tip='两次密码输入不一致'
                }
            },
            checkCaptcha:function(){
                if(this.registerInfo.captcha.trim().length===0){
                    this.valid.captcha.ok=false
                    this.valid.captcha.tip='请输入验证码'
                }
                //调用后台API
                checkCaptchaAPI(this.registerInfo.name,this.registerInfo.captcha)
                .then(res=>{
                    this.valid.captcha.ok=true
                    this.valid.captcha.tip=''
                }).catch(error=>{
                    this.valid.captcha.ok=false
                    this.valid.captcha.tip='请输入验证码'
                })


            },
            changmsgpic:function () {
                this.randomValue = Math.random()
            },
            getSmsCode:function () {
                //调用短信API
                if(this.valid.username.ok){
                    getSmsCodeAPI(this.registerInfo.name)
                    .then(res=>{
                        this.valid.smsCode.isShowBtn=false
                        //倒计时
                        let counterTimer = setInterval(()=>{
                            this.valid.smsCode.counter--
                            if(this.valid.smsCode.counter===0){
                                clearInterval(counterTimer)
                                this.valid.smsCode.isShowBtn=true
                            }
                        },1000)

                    }).catch(error=>{
                        this.valid.smsCode.isShowBtn=true
                    })
                }
            },
            registerUser:function () {
                //提交注册
                if(this.valid.username.ok && this.valid.password.ok && this.valid.captcha.ok ){
                    //调用API
                    userSignUpAPI(this.registerInfo)
                    .then(res=>{
                        //得到Token
                        console.log(res);
                        localStorage.setItem('TOKEN',res.token)

                        this.$router.replace("/")
                        this.$emit('showCate',true)

                        //刷新用户状态
                        this.$store.dispatch('refreshUserInfo')

                    })
                    .catch(error=>{
                        alert('注册失败'+error.message)
                    })

                }else{
                    alert('表单输入错误')
                }


            }
        }

    }
</script>

<style>
    .offcnlogin {
        height: auto;
        overflow: hidden;
        padding: 65px 0 50px 0;
    }
    .wid1000 {
        width: 1000px;
        margin: 0 auto;
    }
    textarea, input {
        background: none;
        border: none;
        outline: none;
    }
    ul {
        display: block;
        list-style-type: disc;
        margin-block-start: 1em;
        margin-block-end: 1em;
        margin-inline-start: 0px;
        margin-inline-end: 0px;
        padding-inline-start: 40px;
    }
    .offcnloginleft ul li {
        margin-bottom: 15px;
        position: relative;
    }
    .offcnloginleft ul li span {
        display: inline-block;
        width: 80px!important;
        text-align: right;
        font-size: 14px;
        color: #333;
    }
    .input262 {
        width: 260px;
        height: 36px;
        border: 1px solid #ddd;
        color: #999;
        padding: 0 0 0 2px;
        vertical-align: middle;
    }
    .offcnloginleft ul .yzmimg {
        position: absolute;
        left: 220px;
        top: 5px;
    }
    .offcnloginleft ul .yzmimg1 {
        position: absolute;
        left: 253px;
        top: 12px;
        font-weight: normal;
    }
    .offcnloginleft ul .loginleft_2 {
        padding: 0 0 0 75px;
    }
    .loginbutton1 {
        width: 265px;
        height: 42px;
        border: none;
        color: #fff;
        font-size: 14px;
        font-weight: bold;
        text-align: center;
        cursor: pointer;
    }
    .lgb_bgc {
        background-color: #ff4a00;
    }
    .offcnloginright {
        width: 365px;
        padding: 0 0 0 102px;
        overflow: hidden;
    }
    .offcnloginleft ul .loginleft_4 {
        padding: 0 0 0 240px;
        color: #666;
    }
    .fl {
        float: left;
    }
    .offcnloginleft {
        width: 529px;
        border-right: 1px solid #e7e7e7;
        overflow: hidden;
    }
    .offcnloginright dl dt {
        font-size: 14px;
        color: #333;
        margin-bottom: 10px;
    }
    .offcnloginright dl dd {
        height: 30px;
        line-height: 30px;
        font-size: 14px;
        color: #666;
    }
</style>