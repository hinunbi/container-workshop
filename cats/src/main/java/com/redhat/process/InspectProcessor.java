package com.redhat.process;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InspectProcessor implements Processor {

	private static final Logger logger = LoggerFactory.getLogger(InspectProcessor.class);

	@Override
	public void process(Exchange exchange) throws Exception {
		Message in = exchange.getIn();
		logger.info(">>>>>>>>>> " + in.toString());
		logger.info(">>>>>>>>>> " + in.getBody());

	}

}
