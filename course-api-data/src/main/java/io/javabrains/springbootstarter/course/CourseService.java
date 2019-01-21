package io.javabrains.springbootstarter.course;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class CourseService {
	
	@Autowired
	private CourseRepository courseRepository;
	
	public List<Course> getAllCourses(String topicId){
		//return topics;
		List<Course> courses = new ArrayList<>();
		courseRepository.findByTopicId(topicId)
		.forEach(courses :: add);
		return courses;
	}

	public Optional<Course> getCourse(String id) {
		//return topics.stream().filter(t -> t.getId().equals(Id)).findFirst().get();
		return courseRepository.findById(id);
	}
	
	public void addCourse(Course course) {
		//topics.add(topic);
		courseRepository.save(course);
	}

	public void putCourse(Course course) {
		courseRepository.save(course);
	}

	public void delCourse(String id) {
		courseRepository.deleteById(id);
	}
	
}
