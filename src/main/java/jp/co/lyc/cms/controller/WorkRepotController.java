package jp.co.lyc.cms.controller;

import java.io.File;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import jp.co.lyc.cms.common.BaseController;
import jp.co.lyc.cms.model.WorkRepotModel;
import jp.co.lyc.cms.service.WorkRepotService;

@Controller
@RequestMapping(value = "/workRepot")
public class WorkRepotController extends BaseController { 

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	WorkRepotService workRepotService;
	/**
	 * 登録ボタン
	 * @param topCustomerMod
	 * @return
	 */
	@RequestMapping(value = "/selectWorkRepot", method = RequestMethod.POST)
	@ResponseBody
	public List<WorkRepotModel> selectWorkRepot(WorkRepotModel workRepotModel) {
		workRepotModel.setEmployeeNo(getSession().getAttribute("employeeNo").toString());
		workRepotModel.setEmployeeName(getSession().getAttribute("employeeName").toString()); 	
		logger.info("WorkRepotController.selectCheckWorkRepot:" + "検索開始");
		workRepotService.selectCheckWorkRepot(workRepotModel);
		logger.info("WorkRepotController.selectCheckWorkRepot:" + "検索終了");
		logger.info("WorkRepotController.selectWorkRepot:" + "検索開始");
		List<WorkRepotModel> checkMod = workRepotService.selectWorkRepot(workRepotModel);
		logger.info("WorkRepotController.selectWorkRepot:" + "検索終了");
		return checkMod;
	}

	/**
	 * アップデート
	 * @param topCustomerMod
	 * @return
	 */
	@RequestMapping(value = "/updateworkRepot", method = RequestMethod.POST)
	@ResponseBody
	public boolean updateWorkRepotModel(@RequestBody WorkRepotModel emp){
		emp.setEmployeeNo(getSession().getAttribute("employeeNo").toString());
		logger.info("DutyManagementController.updateworkRepot:" + "アップデート開始");
		boolean result = false;	
		result  = workRepotService.updateWorkRepot(emp);
		logger.info("DutyManagementController.updateworkRepot:" + "アップデート終了");
		return result;	
	}
	/**
	 * 作業報告書アップロード
	 * 
	 * @param
	 * @return boolean
---	 */
	@RequestMapping(value = "/updateWorkRepotFile", method = RequestMethod.POST)
	@ResponseBody
	public boolean updateWorkRepotFile(@RequestParam(value = "emp", required = false) String JSONEmp,
			@RequestParam(value = "workRepotFile", required = false) MultipartFile workRepotFile) throws Exception {
		logger.info("WorkRepotController.insertWorkRepot:" + "追加開始");
		JSONObject jsonObject = JSON.parseObject(JSONEmp);
		WorkRepotModel workRepotModel = JSON.parseObject(jsonObject.toJSONString(), new TypeReference<WorkRepotModel>() {
		});
		workRepotModel.setEmployeeNo(getSession().getAttribute("employeeNo").toString());
		workRepotModel.setEmployeeName(getSession().getAttribute("employeeName").toString()); 
		String getFilename;
		try {
			getFilename=upload(workRepotModel,workRepotFile);
		} catch (Exception e) {
			return false;
		}
		workRepotModel.setWorkingTimeReport(getFilename);
		boolean result  = workRepotService.updateWorkRepotFile(workRepotModel);
		logger.info("WorkRepotController.insertWorkRepot:" + "追加結束");
		return result;
	}
	
	public final static String UPLOAD_PATH_PREFIX = "C:"+File.separator+"file"+File.separator;
	public String upload(WorkRepotModel workRepotModel,MultipartFile workRepotFile) {
		if (workRepotFile== null) {
			return "";
		}
		String realPath = new String(UPLOAD_PATH_PREFIX + "作業報告書フォルダ"+ File.separator+workRepotModel.getAttendanceYearAndMonth().substring(0,4) + File.separator+workRepotModel.getAttendanceYearAndMonth().substring(4));
		File file = new File(realPath);
		if (!file.isDirectory()) {
			file.mkdirs();
		}
		String fileName =workRepotFile.getOriginalFilename();
		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
		String newName = workRepotModel.getEmployeeNo() + "_"+workRepotModel.getAttendanceYearAndMonth()+workRepotModel.getEmployeeName()+ "_作業報告書"+ "." + suffix;
		try {
			File newFile = new File(file.getAbsolutePath() + File.separator + newName);
			workRepotFile.transferTo(newFile);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return realPath+"/"+newName;
	}
}
