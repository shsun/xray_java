package org.ibase4j.web;

import java.util.Map;

import base.core.Parameter;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import base.core.AbstractMSAController;
import org.ibase4j.model.SysDic;
import org.ibase4j.model.SysUser;
import org.ibase4j.model.TaskFireLog;
import org.ibase4j.provider.ISysProvider;
import org.ibase4j.service.TaskFireLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@Api(value = "字典管理", description = "字典管理")
@RequestMapping(value = "/dic")
public class SysDicController extends AbstractMSAController<ISysProvider> {

    public String getService() {
        return "sysDicService";
    }


    @Autowired
    TaskFireLogService taskFireLogService;


    @ApiOperation(value = "查询字典项")
    @RequiresPermissions("sys.base.dic.read")
    @PutMapping(value = "/read/list")
    public Object query(HttpServletRequest request, HttpServletResponse response, ModelMap map, SysUser user, @RequestBody Map<String, Object> param) {

        TaskFireLog log = taskFireLogService.queryById(1L);
        System.out.println("done");
        String s = log != null ? log.toString() : "no-data";


        Parameter parameter = new Parameter(getService(), "query").setMap(param);
        Page<?> list = provider.execute(parameter).getPage();



        param.put("orderBy", "sort_no");
        Object o = super.query(request, response, map, user, param);
        return o;
    }

    @ApiOperation(value = "字典项详情")
    @RequiresPermissions("sys.base.dic.read")
    @PutMapping(value = "/read/detail")
    public Object get(HttpServletRequest request, HttpServletResponse response, ModelMap map, SysUser user, @RequestBody SysDic param) {
        return super.get(request, response, map, user, param);
    }

    @PostMapping
    @ApiOperation(value = "修改字典项")
    @RequiresPermissions("sys.base.dic.update")
    public Object update(HttpServletRequest request, HttpServletResponse response, ModelMap map, SysUser user, @RequestBody SysDic param) {
        return super.update(request, response, map, user, param);
    }

    @DeleteMapping
    @ApiOperation(value = "删除字典项")
    @RequiresPermissions("sys.base.dic.delete")
    public Object delete(HttpServletRequest request, HttpServletResponse response, ModelMap map, SysUser user, @RequestBody SysDic param) {
        return super.delete(request, response, map, user, param);
    }
}
