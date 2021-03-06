package ca.mcgill.ecse321.projectgroup17.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.projectgroup17.dao.*;
import ca.mcgill.ecse321.projectgroup17.model.*;
import ca.mcgill.ecse321.projectgroup17.model.Appointment.AppointmentStatus;
import ca.mcgill.ecse321.projectgroup17.model.Course.Level;

@Service
public class ProjectGroup17Service {


	//Autowired repositories
	
	@Autowired
	PersonRepository personRepository;

	@Autowired
	ReviewRepository reviewRepository;

	@Autowired
	SpecificCourseRepository specificCourseRepository;
	
	@Autowired
	AppointmentRepository appointmentRepository;

	@Autowired
	AvailabilityRepository availabilityRepository;
	
	@Autowired
	RoomRepository roomRepository;
	
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	MessageRepository messageRepository;
	
	@Autowired
	ReplyRepository replyRepository;
	

	/*------------------------------*/

	/**
	 * Creates SpecificCourse and persists it in the database
	 * @param tutor
	 * @param course
	 * @param hourlyRate
	 * @return SpecificCourse
	 */
	@Transactional
	public SpecificCourse createSpecificCourse(Tutor tutor, Course course, Double hourlyRate) {
		
		
		SpecificCourse specificCourse;
		
		String error = "";

		if (tutor == null) {
			error += "Tutor cannot be null! ";
		}
		
		if (course == null) {
			error += "Course cannot be null! ";
		}
		
		if (hourlyRate == null) {
			error += "HourlyRate must be above minimum wage! ";
		}
		
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		
		specificCourse = new SpecificCourse();
		specificCourse.setHourlyRate(hourlyRate);
		specificCourse.setCourse(course);
		specificCourse.setTutor(tutor);
		specificCourseRepository.save(specificCourse);
		
		return specificCourse;
	}

	@Transactional
	public SpecificCourse getSpecificCourseByID(Long courseID) {
		SpecificCourse specificCourse = specificCourseRepository.findBySpecificCourseID(courseID);
		return specificCourse;
	}
	@Transactional
	public List<SpecificCourse> getSpecificCourseByTutor(String tutorUsername) {
		List<SpecificCourse> specificCourses = specificCourseRepository.findByTutorUsername(tutorUsername);
		return specificCourses;
	}
	@Transactional
	public List<SpecificCourse> getSpecificCourseByCourse(Course course) {
		List<SpecificCourse> specificCourses = specificCourseRepository.findByCourse(course);
		return specificCourses;
	}
	@Transactional
	public List<SpecificCourse> getAllSpecificCourses() {
		List<SpecificCourse> specificCourses = toList(specificCourseRepository.findAll());
		return specificCourses;
	}
	
	@Transactional
	public SpecificCourse deleteSpecificCourse(long specificCourseID) {
		SpecificCourse sc = specificCourseRepository.findBySpecificCourseID(specificCourseID);
		specificCourseRepository.delete(sc);
		return sc;
		
	}

	/*-------------------------------------*/


	/*--------------FELIX------------------*/
	/**
	 * Creates Course and persists it to database
	 * @param courseID
	 * @param name
	 * @param level
	 * @param subject
	 * @return Course object
	 */
	@Transactional
	public Course createCourse(String courseID, String name, String level, String subject) {
		
		
		String error = "";
		if(courseID == null || courseID.equals("") || courseID.trim().length() == 0) {
			error += "Course ID must be specified (ie: ECSE321)!";
		}
		if(name == null) {
			error += "Course name must be specified!";
		}
		else if (name.equals("")) {
			error += "Course name must be specified!";
		}
		if(level == null) {
			error += "Course level must be specified!";
		}
		else if (level.equals("")) {
			error += "Course level must be specified!";
		}
//Don't think this makes any sense -Adrian
//		if ((level.toLowerCase().equals("university")) && (level.toLowerCase().equals("cegep")) && (level.toLowerCase().equals("highschool"))) {
//			error += "Invalid course level specified (Highschool, Cegep, University)!";
//		}
		if(subject == null) {
			error += "The course name must be specified!";
		}
		else if (subject.equals("")) {
			error += "The course name must be specified!";
		}
		error = error.trim();
		if(error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		Course course = new Course();
		course.setCourseID(courseID);
		Level courseLevel = Level.valueOf(level.toUpperCase());
		course.setLevel(courseLevel);
		course.setName(name);
		course.setSubject(subject);
		courseRepository.save(course);
		return course;

	}

	@Transactional
	public Course getCourseByID(String courseID) {
		if (courseID == null) {
			throw new IllegalArgumentException("Course ID must be specified (ie: ECSE321)!");
		}
		else if (courseID.equals("") || courseID.trim().length() == 0) {
			throw new IllegalArgumentException("Course ID must be specified (ie: ECSE321)!");
		}
		
		Course course = courseRepository.findByCourseID(courseID);
		return course;
	}

	@Transactional
	public List<Course> getCoursesBySubject(String subject) {
		if (subject == null) {
			throw new IllegalArgumentException("Course ID must be specified (ie: Science)!");
		}
		else if (subject.equals("") || subject.trim().length() == 0) {
			throw new IllegalArgumentException("Course ID must be specified (ie: Science)!");
		}
		List<Course> course = courseRepository.findBySubject(subject);
		return course;
	}

	@Transactional
	public List<Course> getAllCourses() {
		return toList(courseRepository.findAll());
	}

	@Transactional
	public void deleteCourseByCourseID(String courseID) {
		if(courseID == null || courseID.equals("") || courseID.trim().length() == 0) {
			throw new IllegalArgumentException("Course ID must be specified (ie: ECSE321)!");
		}
		if(courseExistsByCourseID(courseID)) {
			courseRepository.deleteByCourseID(courseID);;
		}

	}

	@Transactional
	public boolean courseExistsByCourseID(String courseID) {
		if(courseID == null || courseID.equals("") || courseID.trim().length() == 0) {
			throw new IllegalArgumentException("Course ID must be specified (ie: ECSE321)!");
		}
		boolean exists = courseRepository.existsByCourseID(courseID);
		return exists;
	}

	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;

	}


	/*--------------------------------------*/

	/**
	 * Creates a person and persists it in the database
	 * @param personType
	 * @param firstName
	 * @param lastName
	 * @param username
	 * @param password
	 * @param email
	 * @param sexe
	 * @param age
	 * @return Person object
	 */
	@Transactional
	public Person createPerson(String personType, String firstName, String lastName, String username, String password, String email, String sexe, long age) {
		
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
		person.setSexe(sexe);
		person.setAge(age);

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
		return toList(personRepository.findAll());
	}


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
	public List<Review> getReviewsByReviewer(Person reviewer){
		List<Review> reviews = reviewRepository.findByReviewer(reviewer);
		return reviews;
	}

	@Transactional
	public List<Review> getReviewsByAppointment(Appointment appointment){
		List<Review> reviews = reviewRepository.findByAppointment(appointment);
		return reviews;
	}

	
	/**
	 * Creates Review and persists it to the database
	 * @param reviewText
	 * @param rating
	 * @param createdTime
	 * @param createdDate
	 * @param reviewee
	 * @param reviewer
	 * @param appointment
	 * @return  Review object
	 */
	@Transactional
	public Review createReview(String reviewText, Double rating, Time createdTime, Date createdDate, 
			Person reviewee, Person reviewer, Appointment appointment) {
		
		

		String error = "";
		if (reviewText == null || reviewText.trim().length() == 0 ) {
			error = error + "A review must containt text.";
		}
		if (rating == null || rating < 0 || rating > 5) {
			error = error + "A rating must be a number between 0 and 5.";
		}
		if(createdTime == null) {
			error = error + "The review must have a time of creation.";
		}
		if(createdDate == null) {
			error = error + "The review must have a date of creation.";
		}
		if(reviewee == null) {
			error = error + "The review must have a reviewee.";
		}
		if(reviewer == null) {
			error = error + "The review must have a reviewer.";
		}
		if(appointment == null) {
			error = error + "The review must have an appointment.";
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
	}

	/*----------------------------*/
	/**
	 * Create Availability and persist it in the database
	 * @param tutor
	 * @param date
	 * @param createdDate
	 * @param startTime
	 * @param endTime
	 * @return Availability object
	 */
	@Transactional
	public Availability createAvailability(Tutor tutor, Date date, Date createdDate, Time startTime, Time endTime) {
		
		
		
		String error = "";

		Availability availability;

		if (tutor == null) {
			error += "Must specify a tutor! ";
		}
		if (date == null) {
			error += "Date cannot be empty! ";
		}
		if (createdDate == null) {
			error += "Created date cannot be empty! ";
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
		availability.setCreatedDate(createdDate);

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
		List<Availability> availabilities = availabilityRepository.findByTutorUsername(tutorUsername);
		return availabilities;
	}
	
	@Transactional
	public Availability getAvailabilityById(long availabilityId) {
		Availability avail = availabilityRepository.findByAvailabilityID(availabilityId);
		return avail;
	}
	
	@Transactional
	public Availability deleteAvailabilityById(long availabilityId) {
		Availability avail = availabilityRepository.findByAvailabilityID(availabilityId);
		availabilityRepository.delete(avail);
		return avail;
	}

	@Transactional
	public List<Availability> getAllAvailabilities() {
		return toList(availabilityRepository.findAll());

	}

	/*----------------------------*/
	/**
	 * Creates appointment and persists it to database
	 * @param date
	 * @param endTime
	 * @param startTime
	 * @param room
	 * @param tutor
	 * @param status 
	 * @param students
	 * @return Appointment object
	 */
	@Transactional 
	public Appointment createAppointment(Date date, Time endTime, Time startTime, 
			Room room, Tutor tutor, String status, Set<Student> students) {
		
				
		String error = "";
		
		if (date == null) {
			error = error + "Appointment date cannot be empty! ";
		}
		if (startTime == null) {
			error = error + "Appointment start time cannot be empty! ";
		}
		if (endTime == null) {
			error = error + "Appointment end time cannot be empty! ";
		}
		if (endTime != null && startTime != null && endTime.before(startTime)) {
			error = error + "Appointment end time cannot be before appointment start time! ";
		}
		if (tutor == null) {
			error = error + "Appointment tutor cannot be null! ";
		}
		if (status == null || ! (status.toLowerCase().equals("requested"))) {
			error = error + "Appointment status cannot be empty and must be 'Requested'! ";
		}
		if(room == null) {
			error = error + "Appointment room cannot be null! ";
		}
		if(students == null) {
			error = error + "Appointment students cannot be null! ";
		}
		if ((students != null) && (students.size() < 0)) {
			error = error + "Appointment students cannot be empty";
		}
		
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		
		Appointment appt = new Appointment();
		appt.setDate(date);
		appt.setStartTime(startTime);
		appt.setEndTime(endTime);
		appt.setRoom(room);
		appt.setTutor(tutor);
		appt.setStudent(students);
		appt.setCreatedDate(new Date(Calendar.getInstance().getTime().getTime()));
		AppointmentStatus apptStatus = AppointmentStatus.valueOf(status.toUpperCase());
		appt.setStatus(apptStatus);
		appointmentRepository.save(appt);
		return appt;

	}
	
	@Transactional
	public Appointment getAppointmentByAppointmentID(long appointmentID) {
		return appointmentRepository.findByAppointmentID(appointmentID);
	}
	
	@Transactional
	public List<Appointment> getAllAppointments() {
		return toList(appointmentRepository.findAll());
	} 
	
	@Transactional
	public Appointment getAppointmentById(long appointmentId) {
		Appointment appt = appointmentRepository.findByAppointmentID(appointmentId);
		return appt;
	}
	
	@Transactional
	public List<Appointment> getAppointmentsByTutor(Tutor tutor) {
		return toList(appointmentRepository.findByTutor(tutor));
	}
	
	@Transactional
	public Appointment changeAppointmentStatus(Appointment appt, String status) {
		appt.setStatus(AppointmentStatus.valueOf(status.toUpperCase()));
		return appt;
	}
	
	@Transactional
	public List<Appointment> getAppointmentsByStudent(Student student) {
		return toList(appointmentRepository.findByStudent(student));
	}
	
	@Transactional
	public List<Appointment> getAppointmentsByStartTimeAndEndTime(Time startTime, Time endTime) {
		return toList(appointmentRepository.findByStartTimeAndEndTime(startTime, endTime));
	}
	
	/*----------------------------*/
	
	@Transactional
	public Room createRoom(long roomID, boolean big) {
		/**
		 * Creates room and persists it in the database
		 * @param roomId
		 * @param big
		 * @return Room object
		 */
		
		String error = "";
		
		if(roomID == 0) {
			error += "RoomID cannot be 0!";
		}
		
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		Room room = new Room();
		room.setBig(big);
		room.setRoomID(roomID);
		roomRepository.save(room);
		return room;
	}
	
	 
	@Transactional
	public List<Room> getAllRooms() {
		return toList(roomRepository.findAll());
	}
	
	@Transactional
	public Room getRoomByRoomID(long roomID) {
		Room room = roomRepository.findByRoomID(roomID);
		return room;
	}
	
	@Transactional
	public List<Room> getRoomByRoomBig(boolean isBig) {
		List<Room> rooms = roomRepository.findByBig(isBig);
		return rooms;
	}
	
	
	/*----------------------------*/

	
	@Transactional
	public Message createMessage(String author, String text) {
		String error = "";
		
		if(author == null || author == "") {
			error += "Author cannot be empty! ";
		}
		
		if(personRepository.findByUsername(author) == null) {
			error += "Author must exist! ";
		}
		
		if(text == null || text == "") {
			error += "Text cannot be empty! ";
		}
		
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		
		Message m = new Message();
		m.setAuthor(author);
		m.setText(text);
		m.setCreatedDate(new Date(Calendar.getInstance().getTime().getTime()));
		messageRepository.save(m);
		return m;
	}
	
	@Transactional
	public Reply createReply(long messageId, String author, String text) {
		String error = "";
		
		if(author == null || author == "") {
			error += "Author cannot be empty! ";
		}
		
		if(messageId == 0) {
			error+= "Message cannot be empty! ";
		}
		
		if(messageRepository.findByMessageId(messageId) == null) {
			error += "Message must exist! ";
		}
		
		if(personRepository.findByUsername(author) == null) {
			error += "Author must exist! ";
		}
		
		
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		Message m = messageRepository.findByMessageId(messageId);
		Reply r = new Reply();
		r.setAuthor(author);
		r.setText(text);
		r.setMessage(m);
		replyRepository.save(r);
		
		return r;
	}
	
	@Transactional
	public void addReplyToMessage(Message m, Reply r) {
		String error = "";
		if (m == null) {
	        error = error + "Message needs to be selected for reply!";
	    } else if (!messageRepository.existsById(m.getMessageId())) {
	        error = error + "Message does not exist!";
	    }
		
		if (r == null) {
	        error = error + "Reply needs to be selected for reply!";
	    } else if (!replyRepository.existsById(r.getReplyId())) {
	        error = error + "Reply does not exist!";
	    }
		
		error = error.trim();

	    if (error.length() > 0) {
	        throw new IllegalArgumentException(error);
	    }
	    List<Reply> replies;
	    if(m.getReplies() == null) replies = new ArrayList<Reply>();
	    else replies = m.getReplies();
	    replies.add(r);
	    m.setReplies(replies);
	    messageRepository.save(m);
	}
	
	@Transactional
	public List<Message> getAllMessages() {
		List<Message> messages = toList(messageRepository.findAll());
		return messages;
	}
	
	@Transactional
	public Message getMessageByMessageId(long messageId) {
		Message m = messageRepository.findByMessageId(messageId);
		return m;
	}
	
}
