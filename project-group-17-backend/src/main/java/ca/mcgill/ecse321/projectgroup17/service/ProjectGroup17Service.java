package ca.mcgill.ecse321.projectgroup17.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.projectgroup17.dao.*;
import ca.mcgill.ecse321.projectgroup17.model.*;


@Service
public class ProjectGroup17Service {

	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	PersonRepository personRepository;
<<<<<<< HEAD
	
	@Autowired
	ReviewRepository reviewRepository;

=======
>>>>>>> 682fb894939366c655dd5a77f04f17e15efaa36c
	
	@Autowired
	AvailabilityRepository availabilityRepository;


	@Transactional
	public Course createCourse(String courseID, String name, String level, String subject) {
		
		Course course = new Course();
		course.setCourseID(courseID);
		course .setLevel(level);
		course.setName(name);
		courseRepository.save(course);
		return course;

	}
	
	@Transactional
	public Course getCourseByID(String courseID) {
		Course course = courseRepository.findCourseByID(courseID);
		return course;
	}

	@Transactional
	public List<Course> getAllCourses() {
		return toList(courseRepository.findAll());
	}

	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
		
	}
	
	
	@Transactional
	public Person createPerson(String personType, String firstName, String lastName, String username, String password, String email) {

		Person person;

		String error = "";
		if (personType == null || personType.trim().length() == 0 || ! (personType.equals("Tutor") || personType.equals("Student"))) {
			error = error + "Person type must be either 'Student' or 'Tutor'! ";
		}

		if (firstName == null || firstName.trim().length() == 0) {
			error = error + "First name cannot be empty! ";
		}

		if (lastName == null ||lastName.trim().length() == 0) {
			error = error + "Last name cannot be empty! ";
		}

		if (username == null || username.trim().length() == 0) {
			error = error + "Username cannot be empty! ";
		}

		if (password == null || password.trim().length() == 0) {
			error = error + "Password cannot be empty! ";
		}

		if (email == null || email.trim().length() == 0) {
			error = error + "Email cannot be empty! ";
		}

		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}

		if (personType.equals("Tutor")) {
			person = new Tutor();
		}

		else {
			person = new Student();
		}

		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.setUsername(username);
		person.setPassword(password);
		person.setEmail(email);
		person.setCreated_date(new java.sql.Date(Calendar.getInstance().getTime().getTime()));

		personRepository.save(person);
		return person;
	}

	@Transactional
	public Person getPersonByUsername(String username) {
		Person person = personRepository.findByUsername(username);
		return person;
	}

	@Transactional
	public List<Person> getPersonByFirstName(String firstName) {
		List<Person> persons = personRepository.findByFirstName(firstName);
		return persons;
	}

	@Transactional
	public List<Person> getPersonByFirstNameAndLastName(String firstName, String lastName) {
		List<Person> persons = personRepository.findByFirstNameAndLastName(firstName, lastName);
		return persons;
	}

	@Transactional
	public List<Person> getPersonByLastName(String lastName) {
		List<Person> persons = personRepository.findByLastName(lastName);
		return persons;
	}

	@Transactional
	public Person getPersonByEmail(String email) {
		Person person = personRepository.findByEmail(email);
		return person;
	}

	@Transactional
	public List<Person> getPersonByPersonType(String personType) {
		List<Person> persons = personRepository.findByPersonType(personType);
		return persons;
	}

	@Transactional
	public List<Person> getAllPersons() {
		return personRepository.findAll();
	}
	
<<<<<<< HEAD
	// -----------------------------------------------------------
	//CHARLES BOURBEAU
	//REVIEW REPOSITORY METHODS 
	// -----------------------------------------------------------
	
	@Transactional
	public List<Review> getAllReviews(){
		return reviewRepository.findAll();
	}
	
	@Transactional
	public Review getReviewByReviewID(long reviewID) {
		Review review = reviewRepository.findByReviewID(reviewID);
		return review;
	}
	
	@Transactional
	public List<Review> getReviewsByReviewee(Person reviewee){
		List<Review> reviews = reviewRepository.findByReviewee(reviewee);
		return reviews;
	}
	
	@Transactional
	public List<Review> getReviewsByByReviewer(Person reviewer){
		List<Review> reviews = reviewRepository.findByReviewer(reviewer);
		return reviews;
	}
	
	@Transactional
	public List<Review> getReviewsByAppointment(Appointment appointment){
		List<Review> reviews = reviewRepository.findByAppointment(appointment);
		return reviews;
	}
	
	@Transactional
	public Review createReview(String reviewText, Integer rating, Time createdTime, Date createdDate, 
			Person reviewee, Person reviewer, Appointment appointment) {
		
		String error = "";
		if (reviewText == null || reviewText.trim().length() == 0 ) {
			error = error + "A review must containt text. ";
		}
		if (rating == null || rating < 0 || rating > 5) {
			error = error + "A rating must be a number between 0 and 5. ";
		}
		if(createdTime == null) {
			error = error + "The review must have a time of creation. ";
		}
		if(createdDate == null) {
			error = error + "The review must have a date of creation. ";
		}
		if(reviewee == null) {
			error = error + "The review must have a reviewee. ";
		}
		if(reviewer == null) {
			error = error + "The review must have a reviewer. ";
		}
		if(appointment == null) {
			error = error + "The review must have an appointment. ";
		}

		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		
		Review review = new Review();
		
		review.setReviewText(reviewText);
		review.setRating(rating);
		review.setCreatedTime(createdTime);
		review.setCreatedDate(createdDate);
		review.setReviewee(reviewee);
		review.setReviewer(reviewer);
		review.setAppointment(appointment);
		
		
		reviewRepository.save(review);
		return review;
	}
	
	@Transactional
	public void deleteReview(Long reviewID) {
		String error= "";
		
		boolean reviewExists = reviewRepository.existsByReviewID(reviewID);
		
		if(!reviewExists) {
			error = error + "This review does not exist. ";
		}
		
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		
		// the review exists
		
		reviewRepository.deleteById(reviewID);		
	}
	
	@Transactional 
	public void deleteAllReviews(){
		
		List<Review> reviews = reviewRepository.findAll();
		
		if(reviews.size() == 0) {
			return;
		}
		
		for(Review review : reviews) {
			long reviewID = review.getReviewID();
			deleteReview(reviewID);
		}
=======
	//Availability functions
	
	@Transactional
	public Availability createAvailability(Tutor tutor, Date date, Time startTime, Time endTime) {
		
		String error = "";
		
		Availability availability;

		if (tutor == null) {
			error += "Must specify a tutor! ";
		}
		if (date == null) {
			error += "Date cannot be empty! ";
		}
		if (startTime == null) {
			error += "Start time cannot be empty! ";
		}
		if (endTime == null) {
			error += "End time cannot be empty! ";
		}
		if ((endTime != null) && (startTime != null) && (startTime.compareTo(endTime) > 0)) {
			error += "End time cannot be before startTime! ";
		}
		
		error = error.trim();
	    if (error.length() > 0) {
	        throw new IllegalArgumentException(error);
	    }
	    else {
	    	availability = new Availability();
	    }
	    
	    availability.setTutor(tutor);
		availability.setDate(date);
		availability.setStartTime(startTime);
		availability.setEndTime(endTime);
		
		availabilityRepository.save(availability);
		
		return availability;
	}
	
	@Transactional
	public List<Availability> getAvailabilityByDate(Date date) {
		List<Availability> availabilities = availabilityRepository.findByDate(date);
		return availabilities;
	}
	@Transactional
	public List<Availability> getAvailabilityByTutorUsername(String tutorUsername) {
		List<Availability> availabilities = availabilityRepository.findByTutor(tutorUsername);
		return availabilities;
	}

	public List<Availability> getAllAvailabilities() {
		return availabilityRepository.findAll();
>>>>>>> 682fb894939366c655dd5a77f04f17e15efaa36c
	}
	
}
