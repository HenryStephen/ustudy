<template>
    <div class="zg_gerenmain_right">
        <Modal
            v-model="modal"
            @on-ok="deleteFavorite"
            @on-cancel="modal = false"
            width="400px"
            class-name="vertical-center-modal">
            <p>确认要取消收藏吗？</p>
            </Modal>
        <div class="zg_sckc">
            <dl v-for="item in courseData" :key="item.id">
                <dt><img src="" :alt="item.name" width="180" height="102"></dt>
                <dd><strong><a href="#" target="_blank">{{item.courseName}}</a></strong>
                <p><span>收藏时间:2020-09-21 10:29<a @click="clickDelete(item.courseId)">取消收藏</a></span>
                <font><br><em>课时:{{item.classHour}}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;共有{{item.studentsNumber}}人在学</em></font></p>
                </dd>
            </dl>
        <!-- <div class="zg_page"></div> -->
            <Page class="page" :total="total" />
        </div>
    </div>
</template>

<script>
import { getRequest, deleteRequest } from '@/api/index.js'
export default {
    name: 'CollectionTable',
    data () {
        return {
            courseData: [],
            curCourseId: '',
            modal: false
        }
    },
    computed: {
        total () {
            return this.courseData.length
        }
    },
    mounted () {
        this.getFavorite()
    },
    methods: {
        getFavorite () {
            getRequest('/favorite').then(res => {
            if (res.data.status === 200) {
                this.courseData = res.data.data
                console.log(this.courseData)
            }
        })
       },
       clickDelete (id) {
           this.curCourseId = id
           this.modal = true
       },
       deleteFavorite () {
           deleteRequest('/favorite?courseId=' + this.curCourseId).then(res => {
               console.log(res)
               if (res.data.status === 200) {
                   this.$Message.success('取消收藏成功')
                   this.getFavorite()
               }
           })
       }
    }
}
</script>

<style lang='less'>
.zg_gerenmain_right{ position: relative;left: -200px;width:918px; float:right; border:solid 1px #ddd; background:#fff; padding:38px 29px 0;}

/*收藏的课程*/
.zg_sckc{ text-align: left; width:100%;font: 12px/1.5 "Microsoft YaHei",Tahoma,Arial,Helvetica,sans-serif!important;}
.zg_sckc dl{ width:100%; margin-bottom:20px; border-bottom:solid 1px #ddd; clear:both; overflow:hidden; padding-bottom:15px;}
.zg_sckc dl dt{ width:200px; float:left;}
.zg_sckc dl dd strong{ display:block; height:29px; font-size:14px; font-weight:normal;}
.zg_sckc dl dd strong a{ font-size:14px;}
.zg_sckc dl dd p{ height:95px;}
.zg_sckc dl dd p font{font-size:12px;}
.zg_sckc dl dd p font a{ color:#ff6600; text-decoration:underline;}
.zg_sckc dl dd p font em{ color:#999; font-style:normal;}
.zg_sckc dl dd p span{ float:right; line-height:62px; color:#999;}
.page {float: right;}
</style>
