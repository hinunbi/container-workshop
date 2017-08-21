package com.redhat.cats.process;

import com.redhat.cats.repository.TweetRepository;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RepositoryProcess implements Processor {

	private static volatile long before;
	private static volatile long now;

	@Override
	public void process(Exchange exchange) throws Exception {
		Message in = exchange.getIn();
		in.setHeader("total", countAll());
		in.setHeader("speed", readSpeed());
	}

	@Autowired
	private TweetRepository tweetRepository;

	public Long countAll() {
		now = tweetRepository.count();
		return now;
	}

	public Long readSpeed() {
		long speed = now - before;
		before = now;
		return speed;
	}
}
