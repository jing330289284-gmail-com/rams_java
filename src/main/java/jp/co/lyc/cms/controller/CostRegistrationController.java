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
import jp.co.lyc.cms.model.CostRegistrationModel;
import jp.co.lyc.cms.service.CostRegistrationService;

@Controller
@RequestMapping(value = "/costRegistration")
public class CostRegistrationController extends BaseController { 
	public final static String UPLOAD_PATH_PREFIX = "C:"+File.separator+"file"+File.separator;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	CostRegistrationService costRegistrationService;
	/**
	 * 
	 * 検索
	 * @param topCustomerMod
	 * @return
	 */
	@RequestMapping(value = "/selectCostRegistration", method = RequestMethod.POST)
	@ResponseBody
	public List<CostRegistrationModel> selectCostRegistration(CostRegistrationModel costRegistrationModel) {
		costRegistrationModel.setEmployeeNo(getSession().getAttribute("employeeNo").toString());
		logger.info("CostRegistrationController.selectCostRegistration:" + "検索開始");
		List<CostRegistrationModel> checkMod = costRegistrationService.selectCostRegistration(costRegistrationModel);
		logger.info("CostRegistrationController.selectCostRegistration:" + "検索終了");
		return checkMod;
	}
	@RequestMapping(value = "/selectEmployeeName", method = RequestMethod.POST)
	@ResponseBody
	public CostRegistrationModel selectEmployeeName(CostRegistrationModel costRegistrationModel) {
		logger.info("CostRegistrationController.selectEmployeeName:" + "検索開始");
		costRegistrationModel.setEmployeeName(getSession().getAttribute("employeeName").toString());
		logger.info("CostRegistrationController.selectEmployeeName:" + "検索終了");
		return costRegistrationModel;
	}
	/**
	 * 追加
	 * 
	 * @param
	 * @return boolean
---	 */
	@RequestMapping(value = "/insertCostRegistration", method = RequestMethod.POST)
	@ResponseBody
	public boolean insertCostRegistration(@RequestParam(value = "emp", required = false) String JSONEmp,
			@RequestParam(value = "costFile", required = false) MultipartFile costFile) throws Exception {
		logger.info("CostRegistrationController.insertCostRegistration:" + "追加開始");
		JSONObject jsonObject = JSON.parseObject(JSONEmp);
		CostRegistrationModel costRegistrationModel = JSON.parseObject(jsonObject.toJSONString(), new TypeReference<CostRegistrationModel>() {
		});
		costRegistrationModel.setEmployeeNo(getSession().getAttribute("employeeNo").toString());
		costRegistrationModel.setEmployeeName(getSession().getAttribute("employeeName").toString()); 
		costRegistrationModel.setCostFile(getName(costRegistrationModel, costFile));
		String getFilename;

			boolean fla=costRegistrationService.insertCostRegistration(costRegistrationModel);
			if(!fla) {
				return false;
			}
		getFilename=upload(costRegistrationModel,costFile);

		costRegistrationModel.setCostFile(getFilename);
	
		logger.info("CostRegistrationController.insertCostRegistration:" + "追加結束");
		return true;
	}
	/**
	 * 修正
	 * 
	 * @param
	 * @return boolean
---	 */
	@RequestMapping(value = "/updateCostRegistration", method = RequestMethod.POST)
	@ResponseBody
	public boolean updateCostRegistration(@RequestParam(value = "emp", required = false) String JSONEmp,
			@RequestParam(value = "costFile", required = false) MultipartFile costFile) throws Exception {
		logger.info("CostRegistrationController.updateCostRegistration:" + "修正開始");
		JSONObject jsonObject = JSON.parseObject(JSONEmp);
		CostRegistrationModel costRegistrationModel = JSON.parseObject(jsonObject.toJSONString(), new TypeReference<CostRegistrationModel>() {
		});
		costRegistrationModel.setEmployeeNo(getSession().getAttribute("employeeNo").toString());
		costRegistrationModel.setEmployeeName(getSession().getAttribute("employeeName").toString()); 
		String getFilename="";
		if(costFile==null&&!costRegistrationModel.getCostClassificationCode().equals(costRegistrationModel.getOldCostClassificationCode())) {
			costRegistrationModel.setCostFile("");
		}else {
			costRegistrationModel.setCostFile(getName(costRegistrationModel, costFile));
		}
		boolean fla=costRegistrationService.updateCostRegistration(costRegistrationModel);
		//新KEYに変更できない
		if(!fla) {
			return false;
		}
		//ファイルに変更がある
		if(costRegistrationModel.isChangeFile()) {
			//ファイル名に変更がある
			if(!costRegistrationModel.getCostClassificationCode().equals(costRegistrationModel.getOldCostClassificationCode())||
			!costRegistrationModel.getHappendDate().equals(costRegistrationModel.getOldHappendDate())){
					//新しいファイルがない
					if(costFile==null) {
						if(!costRegistrationModel.getCostClassificationCode().equals(costRegistrationModel.getOldCostClassificationCode())){
							if(costRegistrationModel.getCostClassificationCode().equals("1")||costRegistrationModel.getOldCostClassificationCode().equals("1")){
								//区分が変更された,旧ファイル削除
								delete(costRegistrationModel);
								
							}
						}else {
							//区分に変更がない、改名
							getFilename=rename(costRegistrationModel);
						}
						costRegistrationModel.setCostFile(getFilename);
					}else {
						//新しいファイル、旧ファイル削除、新ファイルアップロード
						try {
							delete(costRegistrationModel);
							getFilename=upload(costRegistrationModel,costFile);
							costRegistrationModel.setCostFile(getFilename);
						} catch (Exception e) {
							return false;
						}
					}
			}else {
				costRegistrationModel.setCostFile(costRegistrationModel.getOldCostFile());
			}
		}
		logger.info("CostRegistrationController.updateCostRegistration:" + "修正結束");
		return true;
	}

	/**
	 * 削除
	 * 
	 * @param
	 * @return boolean
---	 */
	@RequestMapping(value = "/deleteCostRegistration", method = RequestMethod.POST)
	@ResponseBody
	public boolean deleteCostRegistration(@RequestBody CostRegistrationModel emp) {
		logger.info("CostRegistrationController.deleteCostRegistration:" + "削除開始");
		emp.setEmployeeNo(getSession().getAttribute("employeeNo").toString());
		boolean flag=false;
		if(emp.getOldCostFile()!=null) {
			try {
				flag=delete(emp);
			} catch (Exception e) {
				return flag;
			}
		}
		boolean result  = costRegistrationService.deleteCostRegistration(emp);
		logger.info("CostRegistrationController.deleteCostRegistration:" + "削除結束");
		return result;
	}
	//ファイルアップロード

	public String upload(CostRegistrationModel costRegistrationModel,MultipartFile costFile) {
		if (costFile== null) {
			return "";
		}
		String realPath = new String(UPLOAD_PATH_PREFIX + "作業報告書フォルダ"+ File.separator+costRegistrationModel.getHappendDate().substring(0,4) + File.separator+costRegistrationModel.getHappendDate().substring(4,6));
		File file = new File(realPath);
		if (!file.isDirectory()) {
			file.mkdirs();
		}
		String fileName =costFile.getOriginalFilename();
		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
		String newName =costRegistrationModel.getHappendDate().substring(4,8)+costRegistrationModel.getCostClassificationName()+ "." + suffix;
	
		try {
			File newFile = new File(file.getAbsolutePath() + File.separator + newName);
			costFile.transferTo(newFile);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return realPath+File.separator+newName;
	}
	//ファイル削除
	public boolean delete(CostRegistrationModel costRegistrationModel) {
		boolean flag = false; 
		File file = new File(costRegistrationModel.getOldCostFile());
	    if (file.isFile() && file.exists()) {
	        file.delete();  
	        flag = true;  
	    }
	    return flag;
	}
	//ファイルリネーム
	private String rename(CostRegistrationModel costRegistrationModel) {
		String realPath = new String(UPLOAD_PATH_PREFIX + "作業報告書フォルダ"+ File.separator+costRegistrationModel.getHappendDate().substring(0,4) + File.separator+costRegistrationModel.getHappendDate().substring(4,6));
		String oldRealPath= new String(costRegistrationModel.getOldCostFile());
		
		File oldFile = new File(oldRealPath);
		String suffix = oldRealPath.substring(oldRealPath.lastIndexOf(".") + 1);
		String newName =costRegistrationModel.getHappendDate().substring(4,8)+costRegistrationModel.getCostClassificationName()+ "." + suffix;
		try {
		oldFile.renameTo(new File(realPath+File.separator + newName));
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return realPath+File.separator+newName;

	}
	private String getName(CostRegistrationModel costRegistrationModel,MultipartFile costFile) {
		String realPath = new String(UPLOAD_PATH_PREFIX + "作業報告書フォルダ"+ File.separator+costRegistrationModel.getHappendDate().substring(0,4) + File.separator+costRegistrationModel.getHappendDate().substring(4,6));
		//新ファイル
		if (costFile!=null) {
			String fileName =costFile.getOriginalFilename();
			String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
			if (suffix.equals("")) {
				return "";
			}
			String newName =costRegistrationModel.getHappendDate().substring(4,8)+costRegistrationModel.getCostClassificationName()+ "." + suffix;
			return realPath+File.separator+newName;
		}else {
			//新ファイル名
			String oldRealPath= new String(costRegistrationModel.getOldCostFile());
			String suffix = oldRealPath.substring(oldRealPath.lastIndexOf(".") + 1);
			if (suffix.equals("")) {
				return "";
			}
			String newName =costRegistrationModel.getHappendDate().substring(4,8)+costRegistrationModel.getCostClassificationName()+ "." + suffix;
			return realPath+File.separator+newName;
		}
	}
}
