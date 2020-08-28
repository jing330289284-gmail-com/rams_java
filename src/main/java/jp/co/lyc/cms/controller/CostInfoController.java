package jp.co.lyc.cms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.CrossOrigin;

import jp.co.lyc.cms.common.BaseController;
import jp.co.lyc.cms.model.CostInfoModel;
import jp.co.lyc.cms.service.CostInfoService;
import jp.co.lyc.cms.util.UtilsCheckMethod;

@Controller
@CrossOrigin(origins = "http://127.0.0.1:3000")
@RequestMapping(value = "/subCost")
public class CostInfoController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CostInfoService GCS;
	@Autowired
	UtilsCheckMethod utilsCheckMethod;
	// 画面の初期化の場合、データの取得
	@RequestMapping(value = "/init", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> init(@RequestBody CostInfoModel costModel, Model model) {
		logger.info("LoginController.login:" + "初期化開始");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		ArrayList<CostInfoModel> subCostList = selectData(costModel.getEmployeeNo(),null);
		if(subCostList.size() > 1) {//テーブルの年月
			for(int i = 0 ; i < subCostList.size() ; i++) {
				String yearAndMonthFirst = subCostList.get(i).getReflectYearAndMonth();
				yearAndMonthFirst = yearAndMonthFirst.substring(0,4) + "." + yearAndMonthFirst.substring(4);
				String yearAndMonthSecond = "";
				if(i != subCostList.size() - 1) {
					yearAndMonthSecond = subCostList.get(i+1).getReflectYearAndMonth();
					int year = Integer.parseInt(yearAndMonthSecond.substring(0,4));
					int month = Integer.parseInt(yearAndMonthSecond.substring(4));
					if(month == 1) {
						year -= 1;
						month = 12;
					}else {
						month -= 1;
					}
					yearAndMonthSecond = Integer.toString(year) + "." + Integer.toString(month);
				}
				subCostList.get(i).setDatePeriod(yearAndMonthFirst + "-" + yearAndMonthSecond);
			}
		}else if(subCostList.size() == 1){
			String yearAndMonthFirst = subCostList.get(0).getReflectYearAndMonth();
			yearAndMonthFirst = yearAndMonthFirst.substring(0,4) + "." + yearAndMonthFirst.substring(4);
			subCostList.get(0).setDatePeriod(yearAndMonthFirst + "-");
		}
		resultMap.put("subCostList" , subCostList);
		resultMap.put("checkKadoMap", GCS.checkKado(costModel.employeeNo));
		logger.info("LoginController.login:" + "初期化終了");
		return resultMap;
	}
	
	// 查询方法
	public ArrayList<CostInfoModel> selectData(String employeeNo , String reflectYearAndMonth) {
		Map<String, String> sendMap = new HashMap<String, String>();
		sendMap.put("employeeNo", employeeNo);
		if(!utilsCheckMethod.isNullOrEmpty(reflectYearAndMonth)) {
			sendMap.put("reflectYearAndMonth", reflectYearAndMonth);
		}
		return GCS.getEmployeeInfo(sendMap);

	}
}
