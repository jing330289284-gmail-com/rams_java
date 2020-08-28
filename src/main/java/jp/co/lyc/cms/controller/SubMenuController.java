package jp.co.lyc.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import jp.co.lyc.cms.common.BaseController;
import jp.co.lyc.cms.model.EmployeeModel;
@Controller
@RequestMapping(value = "/subMenu")
public class SubMenuController extends BaseController {
	@RequestMapping(value = "/init", method = RequestMethod.POST)
	@ResponseBody
	public EmployeeModel init() {
		if(null == getRequest().getSession(false)) {
			return null;
		}else{
			EmployeeModel employeeModel = new EmployeeModel();
			employeeModel.setEmployeeName((String)getSession().getAttribute("employeeName"));
			employeeModel.setEmployeeNo((String)getSession().getAttribute("employeeNo"));
			employeeModel.setAuthorityCode((String)getSession().getAttribute("authorityCode"));
			employeeModel.setAuthorityName((String)getSession().getAttribute("authorityName"));
			return employeeModel;
		}
	}
}
