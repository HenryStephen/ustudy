<template>
  <div>
    <div id="register_form">
      <register-form></register-form>
    </div>
  </div>
</template>

<script>
import RegisterForm from '@/components/register/register-form.vue'
export default {
  name: 'Register',
  components: {
    RegisterForm
  },
  data () {
    return {
      id: '',
      data: {}
    }
  },
  methods: {

  }
}
</script>
<style lang="less">

</style>
