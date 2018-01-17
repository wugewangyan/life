package com.napoleon.life.core.controller;  
   
 import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.task.Task;
import org.activiti.image.ProcessDiagramGenerator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.napoleon.life.core.facade.LifeActivitiFacade;
import com.napoleon.life.framework.base.BaseController;
 
//@Controller
//@RequestMapping("/life/cost_type")
public class LifeActivitiController extends BaseController{
	
	private Logger logger = LoggerFactory.getLogger(LifeActivitiController.class);

	@Autowired
	private RepositoryService repositoryService;
	
	@Autowired
	private HistoryService historyService;
	
	@Autowired
	private ProcessEngine processEngine;
	
	@Autowired
	private LifeActivitiFacade activitiFacade;
	
	@Autowired
    private TaskService taskService;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@ResponseBody
	@RequestMapping(value = "/start")
    public String start(String key) {
		return this.activitiFacade.startProcessByKey(key);
    }
	
	@ResponseBody
	@RequestMapping(value = "/next")
    public void next(String key) {
		List<Task> task = this.taskService.createTaskQuery().processInstanceId(key).list();
		Task task1 = task.get(task.size()-1);
        //解开注释就推动到下一环节，对应的在流程图上看到
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("testcondition", "3");
        taskService.complete(task1.getId(), variables);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/test")
    public String test(String procInstanceId) {
		// 1、首先是根据流程ID获取当前任务：
        List<Task> tasks = processEngine.getTaskService().createTaskQuery().processInstanceId(procInstanceId).list();
        String nextId = "";
        for (Task task : tasks) {
            RepositoryService rs = processEngine.getRepositoryService();
            // 2、然后根据当前任务获取当前流程的流程定义，然后根据流程定义获得所有的节点：
            ProcessDefinitionEntity def = (ProcessDefinitionEntity) ((RepositoryServiceImpl) rs)
                    .getDeployedProcessDefinition(task.getProcessDefinitionId());
            List<ActivityImpl> activitiList = def.getActivities(); // rs是指RepositoryService的实例
            // 3、根据任务获取当前流程执行ID，执行实例以及当前流程节点的ID：
            String excId = task.getExecutionId();
            ExecutionEntity execution = (ExecutionEntity) runtimeService.createExecutionQuery().executionId(excId)
                    .singleResult();
            String activitiId = execution.getActivityId();
            // 4、然后循环activitiList
            // 并判断出当前流程所处节点，然后得到当前节点实例，根据节点实例获取所有从当前节点出发的路径，然后根据路径获得下一个节点实例：
            for (ActivityImpl activityImpl : activitiList) {
                String id = activityImpl.getId();
                if (activitiId.equals(id)) {
                    logger.debug("当前任务：" + activityImpl.getProperty("name")); // 输出某个节点的某种属性
                    List<PvmTransition> outTransitions = activityImpl.getOutgoingTransitions();// 获取从某个节点出来的所有线路
                    for (PvmTransition tr : outTransitions) {
                        PvmActivity ac = tr.getDestination(); // 获取线路的终点节点
                        logger.debug("下一步任务任务：" + ac.getProperty("name"));
                        nextId = ac.getId();
                    }
                    break;
                }
            }
        }
        return nextId;
	}
	
    
	    /**
	 * @Author 葛明
	 * @Note 读取流程资源
	 * @Date 2017-1-3 15:11
	 * @param processDefinitionId 流程定义ID
	 * @param resourceName        资源名称
	 */
	@RequestMapping(value = "/read-resource")
	public void readResource(String processDefinitionId, String resourceName,String pProcessInstanceId, HttpServletResponse response)
	        throws Exception {
	    // 设置页面不缓存
	    response.setHeader("Pragma", "No-cache");
	    response.setHeader("Cache-Control", "no-cache");
	    response.setDateHeader("Expires", 0);

	    ProcessDefinitionQuery pdq = repositoryService.createProcessDefinitionQuery();
	    ProcessDefinition pd = pdq.processDefinitionId(processDefinitionId).singleResult();

	    if(resourceName.endsWith(".png") && StringUtils.isEmpty(pProcessInstanceId) == false)
	    {
	        getActivitiProccessImage(pProcessInstanceId,response);
	        //ProcessDiagramGenerator.generateDiagram(pde, "png", getRuntimeService().getActiveActivityIds(processInstanceId));
	    }
	    else
	    {
	        // 通过接口读取
	        InputStream resourceAsStream = repositoryService.getResourceAsStream(pd.getDeploymentId(), resourceName);

	        // 输出资源内容到相应对象
	        byte[] b = new byte[1024];
	        int len = -1;
	        while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
	            response.getOutputStream().write(b, 0, len);
	        }
	    }
	}


	/**
	 * 获取流程图像，已执行节点和流程线高亮显示
	 */
	public void getActivitiProccessImage(String pProcessInstanceId, HttpServletResponse response)
	{
	    //logger.info("[开始]-获取流程图图像");
	    try {
	        //  获取历史流程实例
	        HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
	                .processInstanceId(pProcessInstanceId).singleResult();

	        if (historicProcessInstance == null) {
	            //throw new BusinessException("获取流程实例ID[" + pProcessInstanceId + "]对应的历史流程实例失败！");
	        }
	        else
	        {
	            // 获取流程定义
	            ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
	                    .getDeployedProcessDefinition(historicProcessInstance.getProcessDefinitionId());

	            // 获取流程历史中已执行节点，并按照节点在流程中执行先后顺序排序
	            List<HistoricActivityInstance> historicActivityInstanceList = historyService.createHistoricActivityInstanceQuery()
	                    .processInstanceId(pProcessInstanceId).orderByHistoricActivityInstanceId().asc().list();

	            // 已执行的节点ID集合
	            List<String> executedActivityIdList = new ArrayList<String>();
	            int index = 1;
	            //logger.info("获取已经执行的节点ID");
	            for (HistoricActivityInstance activityInstance : historicActivityInstanceList) {
	                executedActivityIdList.add(activityInstance.getActivityId());

	                //logger.info("第[" + index + "]个已执行节点=" + activityInstance.getActivityId() + " : " +activityInstance.getActivityName());
	                index++;
	            }

	            BpmnModel bpmnModel = repositoryService.getBpmnModel(historicProcessInstance.getProcessDefinitionId());

	            // 已执行的线集合
	            List<String> flowIds = new ArrayList<String>();
	            // 获取流程走过的线 (getHighLightedFlows是下面的方法)
	            flowIds = getHighLightedFlows(bpmnModel,processDefinition, historicActivityInstanceList);



	            // 获取流程图图像字符流
	            ProcessDiagramGenerator pec = processEngine.getProcessEngineConfiguration().getProcessDiagramGenerator();
	            //配置字体
	            InputStream imageStream = pec.generateDiagram(bpmnModel, "png", executedActivityIdList, flowIds,"宋体","微软雅黑","黑体",null,2.0);

	            response.setContentType("image/png");
	            OutputStream os = response.getOutputStream();
	            int bytesRead = 0;
	            byte[] buffer = new byte[8192];
	            while ((bytesRead = imageStream.read(buffer, 0, 8192)) != -1) {
	                os.write(buffer, 0, bytesRead);
	            }
	            os.close();
	            imageStream.close();
	        }
	        //logger.info("[完成]-获取流程图图像");
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	        //logger.error("【异常】-获取流程图失败！" + e.getMessage());
	        //throw new BusinessException("获取流程图失败！" + e.getMessage());
	    }
	}

	public List<String> getHighLightedFlows(BpmnModel bpmnModel,ProcessDefinitionEntity processDefinitionEntity,List<HistoricActivityInstance> historicActivityInstances)
	{
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //24小时制
	    List<String> highFlows = new ArrayList<String>();// 用以保存高亮的线flowId

	    for (int i = 0; i < historicActivityInstances.size() - 1; i++)
	    {
	        // 对历史流程节点进行遍历
	        // 得到节点定义的详细信息
	        FlowNode activityImpl = (FlowNode)bpmnModel.getMainProcess().getFlowElement(historicActivityInstances.get(i).getActivityId());


	        List<FlowNode> sameStartTimeNodes = new ArrayList<FlowNode>();// 用以保存后续开始时间相同的节点
	        FlowNode sameActivityImpl1 = null;

	        HistoricActivityInstance activityImpl_ = historicActivityInstances.get(i);// 第一个节点
	        HistoricActivityInstance activityImp2_ ;

	        for(int k = i + 1 ; k <= historicActivityInstances.size() - 1; k++)
	        {
	            activityImp2_ = historicActivityInstances.get(k);// 后续第1个节点

	            if ( activityImpl_.getActivityType().equals("userTask") && activityImp2_.getActivityType().equals("userTask") &&
	                    df.format(activityImpl_.getStartTime()).equals(df.format(activityImp2_.getStartTime()))   ) //都是usertask，且主节点与后续节点的开始时间相同，说明不是真实的后继节点
	            {

	            }
	            else
	            {
	                sameActivityImpl1 = (FlowNode)bpmnModel.getMainProcess().getFlowElement(historicActivityInstances.get(k).getActivityId());//找到紧跟在后面的一个节点
	                break;
	            }

	        }
	        sameStartTimeNodes.add(sameActivityImpl1); // 将后面第一个节点放在时间相同节点的集合里
	        for (int j = i + 1; j < historicActivityInstances.size() - 1; j++)
	        {
	            HistoricActivityInstance activityImpl1 = historicActivityInstances.get(j);// 后续第一个节点
	            HistoricActivityInstance activityImpl2 = historicActivityInstances.get(j + 1);// 后续第二个节点

	            if (df.format(activityImpl1.getStartTime()).equals(df.format(activityImpl2.getStartTime()))  )
	            {// 如果第一个节点和第二个节点开始时间相同保存
	                FlowNode sameActivityImpl2 = (FlowNode)bpmnModel.getMainProcess().getFlowElement(activityImpl2.getActivityId());
	                sameStartTimeNodes.add(sameActivityImpl2);
	            }
	            else
	            {// 有不相同跳出循环
	                break;
	            }
	        }
	        List<SequenceFlow> pvmTransitions = activityImpl.getOutgoingFlows() ; // 取出节点的所有出去的线

	        for (SequenceFlow pvmTransition : pvmTransitions)
	        {// 对所有的线进行遍历
	            FlowNode pvmActivityImpl = (FlowNode)bpmnModel.getMainProcess().getFlowElement( pvmTransition.getTargetRef());// 如果取出的线的目标节点存在时间相同的节点里，保存该线的id，进行高亮显示
	            if (sameStartTimeNodes.contains(pvmActivityImpl)) {
	                highFlows.add(pvmTransition.getId());
	            }
	        }

	    }
	    return highFlows;

	}
}
 
