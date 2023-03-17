import Home from '../views/Home.vue'
import Login from '../views/login.vue'
import Register from '../views/register.vue'
import Detail from '../views/detail.vue'
import CourseList from '../views/course-list.vue'
import ShopCart from '../views/shopcart.vue'
import SubmitOrder from '../views/submitOrder.vue'
import Payment from '../views/payment.vue'
import MyCourse from '../views/my-course.vue'
import Collection from '../views/collection.vue'
import MyOrder from '../views/my-order.vue'
import Account from '../views/account.vue'
import CourseInstroduce from '../components/course-detail/course-introduce.vue'
import CourseArrangement from '../components/course-detail/course-arrangement.vue'
import CourseEvaluation from '../components/course-detail/course-evaluation.vue'
import CommonProblem from '../components/course-detail/common-problem.vue'

export default [
  {
    path: '/',
    name: 'Home',
    meta: {
      title: '优学'
    },
    component: Home
  },
  {
    path: '/login',
    name: 'Login',
    meta: {
      title: '登录'
    },
    component: Login
  },
  {
    path: '/courseList',
    name: 'CourseList',
    meta: {
      title: '课程列表'
    },
    component: CourseList
  },
  {
    path: '/shopcart',
    name: 'ShopCart',
    meta: {
      title: '购物车'
    },
    component: ShopCart
  },
  {
    path: '/submitOrder',
    name: 'SubmitOrder',
    meta: {
      title: '提交订单'
    },
    component: SubmitOrder
  },
  {
    path: '/payment',
    name: 'Payment',
    meta: {
      title: '支付订单'
    },
    component: Payment
  },
  {
    path: '/myCourse',
    name: 'MyCourse',
    meta: {
      title: '我的课程'
    },
    component: MyCourse
  },
  {
    path: '/register',
    name: 'Register',
    meta: {
      title: '注册'
    },
    component: Register
  },
  {
    path: '/collection',
    name: 'Collection',
    meta: {
      title: '收藏'
    },
    component: Collection
  },
  {
    path: '/myOrder',
    name: 'MyOrder',
    meta: {
      title: '订单'
    },
    component: MyOrder
  },
  {
    path: '/account',
    name: 'Account',
    meta: {
      title: '账户设置'
    },
    component: Account
  },
  {
    path: '/introduce',
    name: 'Introduce',
    component: CourseInstroduce
  },
  {
    path: '/detail',
    name: 'Detail',
    meta: {
      title: '课程详情'
    },
    component: Detail,
    children: [
      {
        path: 'introduce',
        name: 'CourseIntroduce',
        component: CourseInstroduce
      },
      {
        path: 'arrangement',
        component: CourseArrangement
      },
      {
        path: 'evaluation',
        component: CourseEvaluation
      },
      {
        path: 'problem',
        component: CommonProblem
      }
    ]
  }
]
