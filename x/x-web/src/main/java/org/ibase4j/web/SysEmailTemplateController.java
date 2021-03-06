package org.ibase4j.web;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import base.core.AbstractMSAController;
import org.ibase4j.model.SysEmailTemplate;
import org.ibase4j.model.SysUser;
import org.ibase4j.provider.ISysProvider;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 */
@RestController
@Api(value = "邮件模版管理", description = "邮件模版管理")
@RequestMapping(value = "emailTemplate")
public class SysEmailTemplateController extends AbstractMSAController<ISysProvider> {
	public String getService() {
		return "sysEmailTemplateService";
	}

	@ApiOperation(value = "查询邮件模版")
	@RequiresPermissions("sys.email.template.read")
	@RequestMapping(value = "/read/list", method = RequestMethod.PUT)
	public Object query(HttpServletRequest request, HttpServletResponse response, ModelMap map, SysUser user, @RequestBody Map<String, Object> param) {
		return super.query(request, response, map, user, param);
	}

	@ApiOperation(value = "邮件模版详情")
	@RequiresPermissions("sys.email.template.read")
	@RequestMapping(value = "/read/detail", method = RequestMethod.PUT)
	public Object get(HttpServletRequest request, HttpServletResponse response, ModelMap map, SysUser user, @RequestBody SysEmailTemplate param) {
		return super.get(request, response, map, user, param);
	}

	@ApiOperation(value = "修改邮件模版")
	@RequiresPermissions("sys.email.template.update")
	@RequestMapping(method = RequestMethod.POST)
	public Object update(HttpServletRequest request, HttpServletResponse response, ModelMap map, SysUser user, @RequestBody SysEmailTemplate param) {
		return super.update(request, response, map, user, param);
	}

	@ApiOperation(value = "删除邮件模版")
	@RequiresPermissions("sys.email.template.delete")
	@RequestMapping(method = RequestMethod.DELETE)
	public Object delete(HttpServletRequest request, HttpServletResponse response, ModelMap map, SysUser user, @RequestBody SysEmailTemplate param) {
		return super.delete(request, response, map, user, param);
	}
}
