<style lang="less">
  @import './detail.less';
</style>

<template>
  <div>
    <div class="fl offcn19_zqleft">
      <div class="breadcrumb">
        <Breadcrumb separator=">" v-if="courseData.courseCategories">
          <BreadcrumbItem to="/">首页</BreadcrumbItem>
          <BreadcrumbItem to="/components/breadcrumb">全部课程</BreadcrumbItem>
          <BreadcrumbItem>{{courseData.courseCategories[1].name}}</BreadcrumbItem>
          <BreadcrumbItem>{{courseData.courseCategories[0].name}}</BreadcrumbItem>
        </Breadcrumb>
      </div>
      <div class="offcn19_showtop">
        <table class="offcn19_htab" width="730" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td width="730">
                <div class="offcn19_htabn">
                  <h1 class="offcn19_showtit" :title="courseData.name">{{courseData.name}}</h1>
                  <p class="offcn19_abstract" title=""></p>
                  <hr>
                  <div class="offcn19_show_box clearfix">
                    <b class="fl offcn19_showlf">课程安排 </b>
                    <div class="fr offcn19_showtime">
                      <strong>有效期{{courseData.validDays}}天 </strong>
                      <span>|</span>{{courseData.classHour}}课时
                    </div>
                  </div>
                </div>
            </td>
          </tr>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'DetailTop',
  props: {
    courseData: {
      type: Object,
      default: function () {
        return {}
      }
    }
  }
}
</script>
