<template>
  <div class="home">
    <Carousel autoplay v-model="rollNum" radius-dot loop class='carousel'>
      <CarouselItem>
        <div class="demo-carousel">
          <img src='@/assets/images/home/F8E2145460020JGF.jpg' />
        </div>
      </CarouselItem>
      <CarouselItem>
        <div class="demo-carousel">
          <img src='http://xue.ujiuye.com/uploads_it/2007/QuanGuo/F7F2019657532ORB.jpg' />
        </div>
      </CarouselItem>
      <CarouselItem>
        <div class="demo-carousel">
          <img src='http://xue.ujiuye.com/uploads_it/1911/QuanGuo/EB110319475096V7.jpg' />
        </div>
      </CarouselItem>
      <CarouselItem>
        <div class="demo-carousel">
          <img src='http://xue.ujiuye.com/uploads_it/2005/QuanGuo/F5J545534512ASRU.jpg' />
        </div>
      </CarouselItem>
    </Carousel>

    <side></side>

    <div class="zg_banb">
      <ul>
        <li class="l1">
          <a href="https://jq.qq.com/?_wv=1027&k=5kncMvk">
            <img src="@/assets/images/home/EBD2136468178FO3.jpg" title="IT培训课程-IT培训在线课程-培训-视频-教程-优就业"
                    alt="IT培训课程-IT培训在线课程-培训-视频-教程-优就业"/>
          </a>
        </li>
        <li class="l1">
          <a href="/zt/ityxapp/">
            <img src="@/assets/images/home/EBD9574228806KKV.jpg" title="IT培训课程-IT培训在线课程-培训-视频-教程-优就业"
                    alt="IT培训课程-IT培训在线课程-培训-视频-教程-优就业"/>
          </a>
        </li>
        <li class="l1">
          <a href="/ce/">
            <img src="@/assets/images/home/EBD178120205340A.jpg" title="IT培训课程-IT培训在线课程-培训-视频-教程-优就业"
                    alt="IT培训课程-IT培训在线课程-培训-视频-教程-优就业"/>
          </a>
        </li>
        <li class="l1">
          <a href="/list/?price=2">
            <img src="@/assets/images/home//F3R74632989PIUNL.jpg" title="IT培训课程-IT培训在线课程-培训-视频-教程-优就业"
                    alt="IT培训课程-IT培训在线课程-培训-视频-教程-优就业"/>
          </a>
        </li>
      </ul>
    </div>

    <live></live>
    <course-list></course-list>
    <bottom></bottom>
  </div>
</template>

<script>
import Side from '@/components/home/side.vue'
import Live from '@/components/home/live.vue'
import CourseList from '@/components/course/course-list.vue'
import Bottom from '@/components/home/bottom.vue'
export default {
  name: 'Home',
  components: {
    Side,
    Live,
    CourseList,
    Bottom
  },
  data () {
    return {
      rollNum: 0,
      arr: [1, 2, 3],
      data: [
        {
          title: 'Web前端',
          childern: [
            {
              id: 1,
              name: 'HTML'
            },
            {
              id: 2,
              name: 'CSS'
            }
          ]
        },
        {
          parentId: 1,
          title: 'Web前端',
          childern: [
            {
              id: 2,
              name: 'HTML'
            }
          ]
        },
        {
          title: 'Web前端',
          childern: [
            {
              id: 1,
              name: 'HTML'
            }
          ]
        }
      ]
    }
  }
}
</script>

<style lang='less'>
.home {background:#f5f5f5;margin:auto;position: absolute;}
.carousel {width: 1580px; margin: auto;}
.zg_banb{ width: 1200px; height: 121px; margin: 10px auto 28px;}
.zg_banb ul{ width: 960px; height: 101px; margin-left: 240px; background: #fff; padding-top:20px; }
.zg_banb ul li{ width: 238px; height: 81px; float: left; border-right: 2px dashed #f1f1f1;}
.zg_banb ul li.l4{ border-right: none;}
.zg_banb ul li a{ display: block;  width: 190px; height: 82px;}
.zg_banb ul li a img{display:block;}
.zg_banb ul li a:hover{ text-decoration: none;}
</style>
