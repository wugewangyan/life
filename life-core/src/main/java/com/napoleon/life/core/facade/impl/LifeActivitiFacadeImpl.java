package com.napoleon.life.core.facade.impl;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.napoleon.life.core.facade.LifeActivitiFacade;


//@Service
public class LifeActivitiFacadeImpl implements LifeActivitiFacade {

	private static final Logger logger = LoggerFactory.getLogger(LifeActivitiFacadeImpl.class);
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private IdentityService identityService;
	
	@Autowired
	private RepositoryService repositoryService;
	
	@Override
	public String startProcessByKey(String key) {
		ProcessInstance instance = this.runtimeService.startProcessInstanceByKey(key);
		return instance.getProcessInstanceId();
	}

	
}
