<template>
  <div>
    <div id="login">
      <div id="form">
        <Form ref="loginForm" :rules='rules' :model="form" :label-width="80">
          <FormItem label="手机" prop="phone">
            <Input type="text" v-model="form.phone" placeholder="请输入用户名">
                <Icon type="ios-person-outline" slot="prepend"></Icon>
            </Input>
          </FormItem>
          <FormItem label="密码" prop="password">
            <Input type="password" v-model="form.password" placeholder="请输入密码">
                <Icon type="ios-lock-outline" slot="prepend"></Icon>
            </Input>
          </FormItem>
          <FormItem>
            <Button id="login-button" type="text" @click="handleSubmit">登录</Button>
          </FormItem>
        </Form>
      </div>
      <div id="link">
        <a>找回密码</a>
        <a id="register">注册</a>
      </div>
    </div>
    <div class="offcnloginright">
      <div id="link_right">
        <p>使用合作账号一键登录：</p>
        <p>
          <a href="http://login.offcn.com/connect2/c/oauth/index.php?type=5&amp;uri=http://xue.ujiuye.com" class="dsf_qq"></a>
          <a href="http://login.offcn.com/connect2/x/index.php?type=5&amp;uri=http://xue.ujiuye.com" class="dsf_qx"></a>
          <a href="http://login.offcn.com/connect2/w/index.php?type=5&amp;uri=http://xue.ujiuye.com" class="dsf_wb"></a>
        </p>
      </div>
      <div id="text1">
        <div>用户专享服务：</div>
        <div>极致的视频学习体验</div>
        <div>丰富的直播课程免费学</div>
        <div>开课提醒，不错过每一次精彩</div>
        <div>讲、学、练、考，多种高效学习方式快速提升成绩</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'LoginForm',
  data () {
    return {
      form: {
        phone: '',
        password: ''
      },
      rules: {
        phone: [
          { required: true, message: '请输入手机号码' },
          { pattern: /^1[3456789]\d{9}$/, message: '手机号码格式错误', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码' },
          { type: 'string', min: 6, message: '密码格式错误', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    handleSubmit () {
      this.$refs.loginForm.validate((valid) => {
        if (valid) {
          this.$emit('on-success-valid', {
            phone: this.form.phone,
            password: this.form.password
          })
        }
      })
    }
  }
}
</script>

<style lang="less">
#login {
  width: 403px;
  position: relative;
  left: 200px;
  top: 50px;
  float: left;
}
#form {
  width: 400px;
}
#link {
  position: absolute;
  right: 8px;
  bottom: -28px;
}
#login-button {
  background-color: rgb(255, 74, 0);
  color: white;
  width: 290px;
  margin-left: 30px;
}
#register {
  margin-left: 20px;
}
#link_right {
  position: relative;
  left: 0px;
  top: 50px;
}
.dsf_qq {
  background: url(../../assets/images/login/dsf_03.jpg) no-repeat;
}
.offcnloginright p img{margin:0 15px 15px 0;}
.offcnloginright p .dsf_qx {
  background: url(../../assets/images/login/dsf_07.jpg) no-repeat;
}
.offcnloginright p .dsf_wb {
  background: url(../../assets/images/login/dsf_05.jpg) no-repeat;
}
.offcnloginright p a{
  display: inline-block;
  width: 45px;
  height: 45px;
  margin: 15px 15px 10px 0;
}
a {
  color: #333;
  cursor: pointer;
  text-decoration: none;
}
#text1 {
  text-align: left;
  line-height: 220%;
  position: relative;
  left: 895px;
  top: -17px;
  height: 300px;
  width: 300px;
}
</style>
