<template>
  <div id="register"> 
    <div id="r_form" >
      <Form ref="registerForm" :rules='rules' :model="form" :label-width="80" >
        <FormItem label="手机" prop="phone">
            <Input type="text" v-model="form.phone" placeholder="请输入手机号"></Input>
        </FormItem>
        <FormItem label="密码" prop="password">
            <Input type="password" v-model="form.password" placeholder="请输入密码"></Input>
        </FormItem>
        <FormItem label="确认密码" prop="passwordTest">
            <Input type="password" v-model="form.passwordTest" placeholder="请再次输入密码" />
        </FormItem>
        <FormItem label="验证码" prop="imageCode">
            <Row>
              <Col span="12">
                  <Input type="text" v-model="form.imageCode" placeholder="验证码"/>
              </Col>
              <Col v-if='havePhone' span="12" style="height:32.89px">
                <img
                  :src="codeImageSrc"
                  alt="验证码"
                  style="margin-left:20px;border-radius:7px;width:120px;height:32px;"
                >
              </Col>
           </Row>
        </FormItem>
        <FormItem label="短信验证" prop="verifyCode">
          <Row>
              <Col span="12">
                  <Input type="text" v-model="form.verifyCode" placeholder="请输入验证码"/>
              </Col>
              <Col span="12">
                <Button type="primary" @click="sendVerifyCode">发送验证码</Button>
              </Col>
           </Row>
        </FormItem>
        <FormItem>
          <Button id="register-button" type="primary" @click="handleSubmit">注册</Button>
        </FormItem>
      </Form>
      <div id="link">
        <label>已有帐号，请<a href="/login">登录</a></label>
      </div>
    </div>
    <div id="word">
      <div id="text">
        <div id="strong">用户专享服务：</div>
        <div>极致的视频学习体验</div>
        <div>丰富的直播课程免费学</div>
        <div>开课提醒，不错过每一次精彩</div>
        <div>讲、学、练、考，多种高效学习方式快速提升成绩</div>
      </div>
    </div>
  </div>
</template>

<script>
import { getRequest, register, getSmsCode } from '@/api/index.js'
export default {
  name: 'RegisterForm',
  computed: {
    codeImageSrc() {
      return 'data:image/jpg;base64,' + this.codeImage
    }
  },
  data () {
    const validPhone = (rule, values, callback) => {
      var pattern = /^1[3456789]\d{9}$/
      if (values === '') callback('手机号码不能为空')
      else if (!pattern.test(values)) {
        callback('手机号码格式错误')
      }
      else {
        this.havePhone = true
        getRequest('/code/image',{
          deviceId: values,
        }).then(res => {
          console.log(res)
          if (res.status === 200) {
            this.codeImage = res.data
            this.deviceId = this.form.phone
          }
        })
      }
    };
    return {
      havePhone: false,
      deviceId: '',
      codeImage: '',
      form: {
        phone: '',
        password: '',
        passwordTest: '',
        imageCode: '',
        verifyCode: ''
      },      
      rules: {
        phone: [
          { validator: validPhone, trigger: 'blur'}
        ],
        password: [
          { required: true, message: '请输入密码' },
          { type: 'string', min: 6, message: '密码格式错误', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    // 提交信息注册
    handleSubmit (name) {
      register(this.form.phone, this.form.password, this.form.verifyCode).then(res => {
        if (res.data.status === 200) {
          this.$Message.success('注册成功')
          this.$router.push('/login')
        }
      })
    },
    // 获取短信验证码
    sendVerifyCode () {
      getSmsCode(this.deviceId, this.form.imageCode).then(res => {
        if (res.data.status === 200) {
          this.$Message.success('验证码已发送')
        }
      })
    }
  }
}
</script>
<style lang="less">
#register-button{
  background-color: rgb(255, 74, 0);
  color: white;
  width: 290px;
  margin-left: 30px;
  border: none;
}
#r_form{
  width: 403px;
  position: relative;
  left: 200px;
  top: 50px;
  float: left;
}
#word {
  width: 403px;
  float: left;
  position: relative;
  top :100px;
  left: 450px;
}
#text {
  float: left;
  text-align: left;
  line-height: 220%;
}
#strong {
  font-weight: 550;
}
</style>
