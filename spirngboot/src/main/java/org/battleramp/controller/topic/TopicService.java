package org.battleramp.controller.topic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

@Service
public class TopicService {

	private List<Topic> topics = new ArrayList<>(Arrays.asList(new Topic("1", "spring1", "description1"),
			new Topic("2", "spring2", "description2"), new Topic("3", "spring3", "description3")));

	public List<Topic> getAllTopics() {
		return topics;
	}

	public Topic getTopic(String id) {
		return topics.stream().filter(t -> t.getId().equalsIgnoreCase(id)).findFirst().get() ;
	}

	public void addTopic(Topic topic) {
		topics.add(topic);
	}

	public void updateTopic(Topic topic) {
		AtomicInteger atomicInteger = new AtomicInteger(0);
		topics.stream().forEach(t -> {
			if (t.getId().equalsIgnoreCase(topic.getId())) {
				topics.set(atomicInteger.get(), topic);
			}
			atomicInteger.getAndIncrement();
		});
	}

	public void deleteTopic(String id) {
		topics.removeIf(t -> t.getId().equalsIgnoreCase(id));
	}
}
