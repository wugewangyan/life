package com.napoleon.life.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.abel533.echarts.Option;
import com.napoleon.life.core.entity.LifeRun;
import com.napoleon.life.core.service.LifeRunService;
import com.napoleon.life.framework.base.BaseController;

@Controller
@RequestMapping("/life/run")
public class LifeRunController extends BaseController{

	@Autowired
	private LifeRunService lifeRunService;
	
	@RequestMapping(value = "/month-run", method = RequestMethod.GET)
    public String findByYearAndMonth(Integer year, Integer month, Model model) {
		year = 2015;
		month = 12;
        List<LifeRun> runs = this.lifeRunService.findByYearAndMonth("243399199@qq.com", year, month);
        model.addAttribute("runs", runs);
        return "run/month-run-list";
    }
	
	@RequestMapping(value = "/findYearRunDetail", method = RequestMethod.GET)
	@ResponseBody
    public Option findYearRunDetail(Integer year) {
        Option result = this.lifeRunService.findByYear("2732650059@qq.com", year);
        return result;
        
    }
	
	@RequestMapping(value = "/groupByYearAndWeek", method = RequestMethod.GET)
	@ResponseBody
    public Option groupByYearAndWeek(Integer year) {
        return this.lifeRunService.groupByYearAndWeek("243399199@qq.com", year);
        
    }
}
