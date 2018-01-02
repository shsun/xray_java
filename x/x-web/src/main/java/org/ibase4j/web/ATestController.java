package org.ibase4j.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import base.core.BaseController;
import org.ibase4j.core.support.HttpCode;
import org.ibase4j.model.TaskFireLog;
import org.ibase4j.service.TaskFireLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class ATestController extends BaseController {

    @Autowired
    TaskFireLogService taskFireLogService;

    @RequestMapping("/test_1")
    @ResponseBody
    public Object test_1(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        TaskFireLog log = taskFireLogService.queryById(1L);
        System.out.println("done");
        String s = log != null ? log.toString() : "no-data";




        return setModelMap(modelMap, HttpCode.OK, log);

    }
}
