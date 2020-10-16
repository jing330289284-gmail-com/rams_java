package jp.co.lyc.cms.controller;

import java.io.File;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import jp.co.lyc.cms.common.BaseController;
import jp.co.lyc.cms.model.CostRegistrationModel;
import jp.co.lyc.cms.service.CostRegistrationService;

@Controller
@RequestMapping(value = "/costRegistration")
public class CostRegistrationController extends BaseController { 

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	CostRegistrationService costRegistrationService;
	/**
	 * 登録ボタン
	 * @param topCustomerMod
	 * @return
	 */
	@RequestMapping(value = "/selectCostRegistration", method = RequestMethod.POST)
	@ResponseBody
	public List<CostRegistrationModel> selectCostRegistration(CostRegistrationModel costRegistrationModel) {
		costRegistrationModel.setEmployeeNo(getSession().getAttribute("employeeNo").toString());
		costRegistrationModel.setEmployeeName(getSession().getAttribute("employeeName").toString()); 	
		logger.info("CostRegistrationController.selectCheckCostRegistration:" + "検索開始");
		costRegistrationService.selectCheckCostRegistration(costRegistrationModel);
		logger.info("CostRegistrationController.selectCheckCostRegistration:" + "検索終了");
		logger.info("CostRegistrationController.selectCostRegistration:" + "検索開始");
		List<CostRegistrationModel> checkMod = costRegistrationService.selectCostRegistration(costRegistrationModel);
		logger.info("CostRegistrationController.selectCostRegistration:" + "検索終了");
		return checkMod;
	}
	/**
	 * 作業報告書アップロード
	 * 
	 * @param
	 * @return boolean
---	 */
	@RequestMapping(value = "/updatecostRegistration", method = RequestMethod.POST)
	@ResponseBody
	public boolean updateCostRegistrationFile(@RequestParam(value = "emp", required = false) String JSONEmp,
			@RequestParam(value = "costRegistrationFile", required = false) MultipartFile costRegistrationFile) throws Exception {
		logger.info("CostRegistrationController.insertCostRegistration:" + "追加開始");
		JSONObject jsonObject = JSON.parseObject(JSONEmp);
		CostRegistrationModel costRegistrationModel = JSON.parseObject(jsonObject.toJSONString(), new TypeReference<CostRegistrationModel>() {
		});
		costRegistrationModel.setEmployeeNo(getSession().getAttribute("employeeNo").toString());
		costRegistrationModel.setEmployeeName(getSession().getAttribute("employeeName").toString()); 
		String getFilename;
		try {
			getFilename=upload(costRegistrationModel,costRegistrationFile);
		} catch (Exception e) {
			return false;
		}
		costRegistrationModel.setCostFile(getFilename);
		boolean result  = costRegistrationService.updateCostRegistration(costRegistrationModel);
		logger.info("CostRegistrationController.insertCostRegistration:" + "追加結束");
		return result;
	}
	
	public final static String UPLOAD_PATH_PREFIX = "C:"+File.separator+"file"+File.separator;
	public String upload(CostRegistrationModel costRegistrationModel,MultipartFile costRegistrationFile) {
		if (costRegistrationFile== null) {
			return "";
		}
		String realPath = new String(UPLOAD_PATH_PREFIX + "作業報告書フォルダ"+ File.separator+costRegistrationModel.getHappendDate().substring(0,4) + File.separator+costRegistrationModel.getHappendDate().substring(4));
		File file = new File(realPath);
		if (!file.isDirectory()) {
			file.mkdirs();
		}
		String fileName =costRegistrationFile.getOriginalFilename();
		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
		String newName = costRegistrationModel.getEmployeeNo() + "_"+costRegistrationModel.getHappendDate()+costRegistrationModel.getEmployeeName()+ "_作業報告書"+ "." + suffix;
		try {
			File newFile = new File(file.getAbsolutePath() + File.separator + newName);
			costRegistrationFile.transferTo(newFile);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return realPath+"/"+newName;
	}
}
