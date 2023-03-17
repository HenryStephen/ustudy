<template>
  <div>
    <hr color="#e6e6e6" size='1' />
    <div class="login">
      <login-form @on-success-valid="handleSubmit"></login-form>
    </div>
  </div>
</template>

<script>
import LoginForm from '@/components/login/login-form.vue'
import { login } from '@/api/index.js'
export default {
  name: 'Login',
  components: {
    LoginForm
  },
  data () {
    return {
      id: '',
      data: {}
    }
  },
  mounted () {
    this.$forceUpdate()
},
  methods: {
    handleSubmit ({ phone, password }) {
      this.data.phone = phone
      this.data.password = password
      login(phone, password).then(res => {
        if (res.data.status === 200) {
          window.localStorage['token'] = res.data.data.token
          window.localStorage['userId'] = res.data.data.user_id
          if (localStorage.getItem('token')) {
            this.$Message.success('登录成功')
            this.$router.push('/')
          }
          else {
            this.$Message.error('登录失败')
          }
        }
      })
    }
  }
}
</script>

<style lang='less'>
.login {
  display: inline;
}
</style>
