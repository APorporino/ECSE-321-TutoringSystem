import Vue from 'vue'
import Router from 'vue-router'

import Login from '@/components/Login.vue'
import Signup from '@/components/Signup.vue'
import SpecificCourse from '@/components/SpecificCourse.vue'
import TutorView from '@/components/TutorView.vue'
import Availability from '@/components/Availability.vue'
import Review from '@/components/Review.vue'
import Appointment from '@/components/Appointment.vue'
import ViewStudentReviews from '@/components/ViewStudentReviews.vue';
import Forum from '@/components/Forum.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/signup',
      name: 'Signup',
      component: Signup
    },
    {
      path: '/specificCourse',
      name: 'SpecificCourse',
      component: SpecificCourse
    },
    {
      path: '/tutorView',
      name: 'TutorView',
      component: TutorView
    },
    {
      path: '/availability',
      name: 'Availability',
      component: Availability
    },
    {
      path:'/review/',
      name:'Review',
      component: Review
    },
    {
      path: '/appointment',
      name: 'Appointment',
      component: Appointment
    },
    {
      path: '/viewStudentReviews',
      name:'ReviewStudentReviews',
      component: ViewStudentReviews
    },
    {
      path: '/forum',
      name: 'Forum',
      component: Forum
    }
  ]
})
