<template>
  <div id="app">



  <div class="navbar brand_color">
    <div class="navbar_tab" @click="routeTo('login')"><div class="img_container"><div style="width:100px;height:100px;background:url('https://raw.githubusercontent.com/wiki/McGill-ECSE321-Fall2019/project-group-17/images/logo_otis.png?token=AE26HBRI4THFSDG2QM2ZACS543GJ4') no-repeat center center;background-size:contain;"></div></div></div>
    <div v-if="logged_in_tutor" class="navbar_tab navbar_tab_hover" @click="routeTo('login')">Home</div>
    <div v-if="logged_in_tutor" class="navbar_tab navbar_tab_hover" @click="routeTo('specificCourse')">Courses</div>
    <div v-if="logged_in_tutor" class="navbar_tab navbar_tab_hover" @click="routeTo('availability')">Availabilities</div>
    <div v-if="logged_in_tutor" class="navbar_tab navbar_tab_hover" @click="routeTo('appointment')">Appointments</div>
    <div v-if="logged_in_tutor" class="navbar_tab navbar_tab_hover" @click="routeTo('viewStudentReviews')">Reviews</div>
    <div v-if="logged_in_tutor" class="navbar_tab navbar_tab_hover" @click="routeTo('forum')">Forum</div>
    <div v-if="logged_in_tutor" class="navbar_tab navbar_tab_hover" @click="logout()">Logout</div>
  </div>

  <!--
    <div class="medium_text wide">Logged in as: {{ logged_in_tutor }} <button class="logout_button align_right brand_color" @click="logout()">Logout</button></div>


    <nav class="nav_links">
      <router-link to="/">Home</router-link>
      <router-link to="/login">Login</router-link>
      <router-link to="/signup">Signup</router-link>
      <router-link to="/specificCourse">SpecificCourse</router-link>
      <router-link to="/availability">Availability</router-link>
      <router-link to="/tutorView">TutorView</router-link>
      <router-link to="/review">Review</router-link>
      <router-link to="/appointment">Appointment</router-link>
      <router-link to="/viewStudentReviews">Student Reviews</router-link>
      <router-link to="/forum">Forum</router-link>

      <router-view />
    </nav>
    -->

    <router-view />

  </div>
</template>

<script src="./main.js"></script>
<script>

import Login from './components/Login.vue'
import Signup from './components/Signup.vue'
import SpecificCourse from './components/SpecificCourse.vue'
import Availability from './components/Availability.vue'
import TutorView from './components/TutorView.vue'
import Review from './components/Review.vue'
import Appointment from './components/Appointment.vue'
import ViewStudentReviews from './components/ViewStudentReviews.vue'
import Forum from './components/Forum.vue'

export default {
  name: 'app',
  data() {
    return {
      logged_in_tutor: '',
      appt_id_review: 0,
    }
  },
  components: {

    Login, Signup, SpecificCourse, Availability, TutorView, Review, Appointment, ViewStudentReviews, Forum
  },
  methods: {
    logout: function() {
      this.logged_in_tutor = "";
      this.appt_id_review = 0;
      this.$router.push("./login");
    },
    routeTo: function (to) {
      this.$router.push("./"+to);
    }
  }

}


</script>

<style>
#app {
  margin: 0px 0px;
  margin-bottom: 200px;
  padding: 0px 0px;
  background: rgba(245, 245, 245, 0.1);
  font-family: 'Verdana';
}
.nav_links > a {
  padding: 10px;
  text-decoration: none;
  background: white;
  border-radius: 3px;
  font-weight: 600;
}
.styled_router_link, .styled_router_link:active, .styled_router_link:focus, .styled_router_link:hover {
  color: white;
}
.navbar {
  width:100%;
  padding: 0px 0px;
  margin: 0px 0px;
}
.navbar_tab {
  padding: 20px 20px;
  font-size: 18px;
  color: white;
  margin: 0px auto;
}
.navbar_tab_hover {
  font-weight: 500;
  cursor:pointer;
  border-bottom: 2px transparent solid;
  -webkit-transition: all 0.2s ease;
  -moz-transition: all 0.2s ease;
  transition: all 0.2s ease;
}
.navbar_tab_hover:hover {
  border-bottom: 2px white solid;
}
.container {
  width: 100%;
  margin: 0px 0px;
  padding: 0px;
  margin: 0px auto;
  margin-top: 25px;
  background: rgba(255, 140, 26, 0);
  font-family: 'Verdana';
}
.inner_container {
  width: 100%;
  min-height: 100px;
  overflow-y: auto;
  margin: 0px auto;
  padding: 40px 75px;
  margin-top: 0%;
  background: white;
  border-radius: 10px;
}
.input_container {
  margin: 30px auto;
}
.input {
  padding: 15px 10px;
  margin: 5px 0px;
  background: rgba(240, 240, 240, 0.3);
  font-size: 16px;
  font-weight: 500;
  border-radius: 0px;
  border: none;
  border-left: 2px transparent solid;
  -webkit-transition: all 0.2s ease;
	-moz-transition: all 0.2s ease;
	transition: all 0.2s ease;
}
.message {
  padding: 15px 10px;
  margin: 5px 0px;
  background: rgba(240, 240, 240, 0.3);
  font-size: 160px;
  font-weight: 500;
  border-radius: 0px;
  border: none;
  border-left: 2px transparent solid;
  -webkit-transition: all 0.2s ease;
	-moz-transition: all 0.2s ease;
	transition: all 0.2s ease;
}
.wide {
  width: 100%;
}
.half {
  display: inline-block;
  width: 48%;
}
.input:focus {
  border-left: 2px rgba(255, 140, 26, 1) solid;
}
.input_label {
  font-size: 12px;
  margin: 2px 0px;
}
.button {
  padding: 15px 10px;
  border-radius: 3px;
  border: none;
  font-size: 20px;
  font-weight: 600;
  margin: 20px auto;
  color: white;
}

.button_small {
  padding: 5px 10px;
  border-radius: 3px;
  border: none;
  font-size: 15px;
  font-weight: 400;
  margin: 20px auto;
  color: white;
}
.logout_button {
  padding: 5px 10px;
  border-radius: 3px;
  border: none;
  font-size: 15px;
  font-weight: 400;
  margin: 0px auto;
  color: white;

}
.align_right{
  float: right;
}
.button_dark {
  background: rgba(0, 0, 0, 0.7);
}
.button_red {
  background: rgba(255, 51, 51, 1);
}
.button_green {
  background: rgba(51, 200, 51, 1);
}
.img_container {
  background: rgba(240, 240, 240, 0);
  width: 100px;
  height: 100px;
  margin: 10px auto;
  border: none;
  border-radius: 0px;
  cursor: pointer;
}
.img_media {
  width: 100%;
  height: 100%;
  margin: 0px auto;
}
.brand_color {
  background: rgba(255, 140, 26, 1);
}
.brand_color_text {
  color: rgb(255, 140, 26);
}
.small_text {
  font-size: 13px;
  font-weight: 400;
  margin: 5px 0px;
}
.medium_text {
  font-size: 20px;
  font-weight: 700;
  padding: 15px 0px;
}
.large_text {
  font-size: 30px;
  font-weight: 600;
  padding: 10px 0px;
}
.center_text {
  text-align: center;
}
.review {
  padding: 5px 10px;
  border-radius: 3px;
  border: none;
  font-size: 20px;
  font-weight: 600;
  margin: 20px auto;
}
.space{
  margin: 10px
}
.shadow {
	box-shadow: 1px 2px 10px -2px rgba(0,0,0,0.2);
}
.review_box {
  margin: 20px auto;
  border: none;
  border-radius: 8px;
  padding: 15px;
}
.profile_info {
  padding: 10px 0px;
  margin: 20px auto;
  border: none;
  border-radius: 8px;
}
.profile_info_table {
  margin: 20px auto;
  font-size: 16px;
  width: 100%;
  text-align:left;
  border-collapse: collapse;
}
.profile_info_table td, .profile_info_table th {
  border: none;
  padding: 10px 0px;
}

.table {
  margin: 20px auto;
  font-size: 16px;
  width: 100%;
  border-collapse: collapse;
}
.table td, .table th {
  border: 1px #e6e6e6 solid;
  padding: 10px 10px;
}
.table tr:nth-child(even){background-color: #f2f2f2;}
.table tr:hover {background-color: rgba(255, 140, 26, 0.4);}
.table th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: rgba(255, 140, 26, 0.8);
  color: white;
}
.grey_text {
  color: #d6d6d6;
  font-size: 14px;
}
input:focus {
   outline: none;
}
input::placeholder {
	color: #d6d6d6;
	font-size: 14px;
	font-weight: 400;
}
textarea:focus {
   outline: none;
}
button:focus {
    outline: 0;
}
select {
  cursor: pointer;
  width: 150px;
  padding: 5px 35px 5px 5px;
  font-size: 16px;
  border: 1px solid #CCC;
  border-radius: 0px;
  height: 40px;
}
input:focus, textarea:focus {
    outline: none !important;
}
textarea {
  resize: none;
  border: none;
}
a {
    text-decoration: none;
}
a:link {
    text-decoration: none;
}
a:visited {
    text-decoration: none;
}
a:hover {
    text-decoration: none;
}
a:active {
    text-decoration: none;
}
</style>
