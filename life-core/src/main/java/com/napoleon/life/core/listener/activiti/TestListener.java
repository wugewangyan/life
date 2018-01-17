package com.napoleon.life.core.listener.activiti;

import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestListener implements ActivitiEventListener{
	
	private Logger logger = LoggerFactory.getLogger(TestListener.class);

	@Override
	public boolean isFailOnException() {
		return true;
	}
	
	@Override
	public void onEvent(ActivitiEvent event) {
		logger.info(event.toString());
	}
}
