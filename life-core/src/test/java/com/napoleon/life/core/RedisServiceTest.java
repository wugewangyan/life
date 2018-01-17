package com.napoleon.life.core;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.napoleon.life.core.entity.LifeWaist;
import com.napoleon.life.core.service.LifeWaistService;
import com.napoleon.life.core.service.TestRedisService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext-*.xml")
public class RedisServiceTest {
    private static final Logger logger = LoggerFactory.getLogger(RedisServiceTest.class);
    
    @Autowired
    private TestRedisService testRedisService;
    
    @Autowired
    private LifeWaistService lifeWaistService;
    
    private int firstnums = 5;
    
    private int secondnums = 1000;
    
    long[] times = new long[firstnums + secondnums];
    
    private CountDownLatch firstLatch = new CountDownLatch(firstnums);
    
    private CountDownLatch secondLatch = new CountDownLatch(secondnums);
    
    private LifeWaist waistInfo = null;
    
    @Before
    public void setUp() throws Exception {
        logger.info("start");
        
        waistInfo = new LifeWaist();
        waistInfo.setUserNo("wonderful_life_180520613339754496");
        waistInfo.setWaist(new BigDecimal("80.00"));
        waistInfo.setBfr(new BigDecimal("15.49"));
        waistInfo.setWhr(new BigDecimal("0.48"));
        waistInfo.setMeasurementTime(new Timestamp(new Date().getTime()));
        waistInfo.setYear(2017);
        waistInfo.setMonth(3);
        waistInfo.setDay(2);
        waistInfo.setWeek(7);
		lifeWaistService.insertOrUpdate(waistInfo);
    }

    @After
    public void tearDown() throws Exception {
        logger.info("test end");
        if(waistInfo != null){
        	this.lifeWaistService.delete(waistInfo.getId());
        }
        
        waistInfo = this.lifeWaistService.findByWaistId(waistInfo.getId());
        Assert.isNull(waistInfo);
    }
    
    
    class ExecuteThread implements Runnable{
    	
    	private CountDownLatch latch;
    	
    	private int index;
    	
    	public ExecuteThread(int index, CountDownLatch latch){
    		this.index = index;
    		this.latch = latch;
    	}
    	
    	@Override
    	public void run() {
    		try {
				latch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    		
    		Long startTime = System.currentTimeMillis();
    		testRedisService.queryByTemplate(waistInfo.getId(), 10);
    		Long endTime = System.currentTimeMillis();
    		
    		times[index] = endTime - startTime;
    		logger.info("Thread " + index + " cost time is " + (endTime - startTime));
    	}
    }

    @Test
    public void testRedis() throws Exception{
    	for(int i = 0; i < firstnums; i++){
    		Thread r = new Thread(new ExecuteThread(i, firstLatch));
    		r.start();
    		firstLatch.countDown();
    	}
    	
    	Thread.sleep(1000);
    	
    	for(int i = firstnums; i < secondnums + firstnums; i++){
    		Thread r = new Thread(new ExecuteThread(i, secondLatch));
    		r.start();
    		secondLatch.countDown();
    	}
    	
    	Thread.sleep(5000);
    	
    	while(this.times.length != (firstnums + secondnums)){
    		logger.info("main Thread still sleep");
    		Thread.sleep(2000);
    	}
    	
    	Long total = 0L;
    	for(Long time : times){
    		total = total + time;
    	}
    	
    	logger.info("total cost is : " + total);
    }
    
}
