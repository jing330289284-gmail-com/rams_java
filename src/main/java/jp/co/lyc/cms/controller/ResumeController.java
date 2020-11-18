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
import jp.co.lyc.cms.model.ResumeModel;
import jp.co.lyc.cms.service.ResumeService;

@Controller
@RequestMapping(value = "/resume")
public class ResumeController extends BaseController { 

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	ResumeService resumeService;
	/**
	 * 検索
	 * @param topCustomerMod
	 * @return
	 */
	@RequestMapping(value = "/selectResume", method = RequestMethod.POST)
	@ResponseBody
	public List<ResumeModel> selectResume(ResumeModel resumeModel) {
		resumeModel.setEmployeeNo(getSession().getAttribute("employeeNo").toString());
		logger.info("ResumeController.selectResume:" + "検索開始");
		 List<ResumeModel>  checkMod = resumeService.selectResume(resumeModel);
		logger.info("ResumeController.selectResume:" + "検索終了");
		return checkMod;
	}

	/**
	 * アップデート
	 * @param topCustomerMod
	 * @return
	 */
	@RequestMapping(value = "/updateResume", method = RequestMethod.POST)
	@ResponseBody
	public boolean updateResumeModel(@RequestBody ResumeModel emp){
		emp.setEmployeeNo(getSession().getAttribute("employeeNo").toString());
		logger.info("DutyManagementController.updateresume:" + "アップデート開始");
		boolean result = false;	
		result  = resumeService.updateResume(emp);
		logger.info("DutyManagementController.updateresume:" + "アップデート終了");
		return result;	
	}
	/**
	 * 作業報告書アップロード
	 * 
	 * @param
	 * @return boolean
---	 */
	@RequestMapping(value = "/updateResumeFile", method = RequestMethod.POST)
	@ResponseBody
	public boolean updateResumeFile(@RequestParam(value = "emp", required = false) String JSONEmp,
			@RequestParam(value = "resumeFile", required = false) MultipartFile resumeFile) throws Exception {
		logger.info("ResumeController.insertResume:" + "追加開始");
		JSONObject jsonObject = JSON.parseObject(JSONEmp);
		ResumeModel resumeModel = JSON.parseObject(jsonObject.toJSONString(), new TypeReference<ResumeModel>() {
		});
		resumeModel.setEmployeeNo(getSession().getAttribute("employeeNo").toString());
		resumeModel.setEmployeeName(getSession().getAttribute("employeeName").toString()); 
		String getFilename;
		try {
			getFilename=upload(resumeModel,resumeFile);
		} catch (Exception e) {
			return false;
		}
		resumeModel.setResumeInfo1(getFilename);
		boolean result  = resumeService.updateResumeFile(resumeModel);
		logger.info("ResumeController.insertResume:" + "追加結束");
		return result;
	}
	
	public final static String UPLOAD_PATH_PREFIX = "C:"+File.separator+"file"+File.separator;
	public String upload(ResumeModel resumeModel,MultipartFile resumeFile) {
		if (resumeFile== null) {
			return "";
		}
		String realPath = new String(UPLOAD_PATH_PREFIX + "作業報告書フォルダ"+ File.separator);
		File file = new File(realPath);
		if (!file.isDirectory()) {
			file.mkdirs();
		}
		String fileName =resumeFile.getOriginalFilename();
		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
		String newName = resumeModel.getEmployeeNo() + "_"+ "_作業報告書"+ "." + suffix;
		try {
			File newFile = new File(file.getAbsolutePath() + File.separator + newName);
			resumeFile.transferTo(newFile);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return realPath+"/"+newName;
	}
}
