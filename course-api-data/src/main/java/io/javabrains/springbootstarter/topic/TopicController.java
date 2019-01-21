package io.javabrains.springbootstarter.topic;

import org.springframework.web.bind.annotation.RestController;



import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class TopicController {
	
	private static final Logger log = LoggerFactory.getLogger(TopicController.class);
	@Autowired
	private TopicService topicService;
	
	
    
	@RequestMapping("/topics")
	public List<Topic> getAllTopics() {
		/*
		 * LOGGER.trace("doStuff needed more information - {}");
		 * LOGGER.debug("doStuff needed to debug - {}");
		 * LOGGER.info("doStuff took input - {}");
		 * LOGGER.warn("doStuff needed to warn - {}");
		 * LOGGER.error("doStuff encountered an error with value - {}");
		 */
		
        
        log.info("GET request in getAllTopics()");
        
		return topicService.getAllTopics();
	}
	
	@RequestMapping("/topics/{id}") // /topics/{id} gives the variable portion as {token}
	public Optional<Topic> topicId(@PathVariable String id) { //pathVariable tell that above token be used in args
		
        log.info("GET request in topicId(), with UUID");
        
		return topicService.getTopic(id);
		
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/topics")
	public void addTopic(@RequestBody Topic topic) {
		
        
        log.info("POST request in addTopic()");
        
		topicService.addTopic(topic);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/topics/{id}")
	public void putTopic(@RequestBody Topic topic, @PathVariable String id) {
		
        
        log.info("PUT request in putTopic()");
        
		topicService.putTopic(topic, id);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/topics/{id}")
	public void delTopic(@PathVariable String id) {
		
        
        log.info("DELETE request in delTopics()");
        
		topicService.delTopic(id);
	}
}
