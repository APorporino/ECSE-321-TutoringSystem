import axios from 'axios'
import Vue from 'vue'
import StarRating from 'vue-star-rating' 

//constructors


var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

Vue.component('star-rating', StarRating);

function StudentDto (firstName, lastName) {
	this.firstName = firstName
	this.lastName = lastName
}

export default {
	name: 'review',
	data() {
		return{
			reviews: [],
			reviewText: '',
			rating: '',
			reviewee: [],
			reviewer: [],
			appointment:[],
			reviewID: '',
			createdDate: '',
			createdTime: '',
			errorReview: [],


			testStudents: [],
			students: []

		}
	},
	created: function(){

		const s1 = new StudentDto("Jim", "Molson")
		const s2 = new StudentDto("Joe", "Morrison")
		this.testStudents = [s1, s2]

		//we have the appointemnt id in app, so we can get the students from that
		AXIOS.get(backendUrl+'/persons/getStudentsByAppointmentID?appointmentID='+appt_id_review)
      	.then(response => {
        // JSON responses are automatically parsed.
        	this.students = response.data
        	this.errorReview = ''
      	})
      	.catch(e => {
        	var errorMsg = e.message
			console.log(errorMsg)
			this.errorReview = errorMsg	
      	});
	},
	methods: {

	}
}