package io.javabrains.springbootstarter.topic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class TopicService {
	
	private static final Logger log = LoggerFactory.getLogger(TopicService.class);
	
	@Autowired
	private TopicRepository topicRepository;
	
	public List<Topic> getAllTopics(){
		//return topics;
		List<Topic> topics = new ArrayList<>();
		topicRepository.findAll()
		.forEach(topics :: add);
		
		log.info("getAllTopics() in TopicService: SUCCESSFUL");
		return topics;
	}

	public Optional<Topic> getTopic(String id) {
		//return topics.stream().filter(t -> t.getId().equals(Id)).findFirst().get();
		
		log.info("getTopic() in TopicService: SUCCESSFUL");
		
		return topicRepository.findById(id);
	}
	
	public void addTopic(Topic topic) {
		//topics.add(topic);
		log.info("addTopic() in TopicService: SUCCESSFUL");
		
		topicRepository.save(topic);
	}

	public void putTopic(Topic topic, String id) {
		log.info("putTopip() in TopicService: SUCCESSFUL");
		
		topicRepository.save(topic);
	}

	public void delTopic(String id) {
		log.info("delTopic() in TopicService: SUCCESSFUL");
		
		topicRepository.deleteById(id);
	}
	
}
