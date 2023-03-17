<style lang="less">
@import './detail.less';
</style>

<template>
  <div>
    <div class="decription_menu">
      <Menu mode="horizontal" active-name="1">
        <MenuItem name="1">
          <router-link to="/detail/introduce">课程介绍</router-link>
        </MenuItem>
        <MenuItem name="2">
          <router-link to="/detail/arrangement">课程安排</router-link>
        </MenuItem>
        <MenuItem name="3">
           <router-link to="/detail/evaluation">课程评价</router-link>
        </MenuItem>
        <MenuItem name="4">
           <router-link to="/detail/problem">常见问题</router-link>
        </MenuItem>
      </Menu>
    </div>
    <router-view :courseData='courseData'></router-view>
  </div>
</template>

<script>
export default {
  name: 'DetailDescription',
  props: {
    courseData: {
      type: Object,
      default: function () {
        return {}
      }
    }
  },
  methods: {
  }
}
</script>
