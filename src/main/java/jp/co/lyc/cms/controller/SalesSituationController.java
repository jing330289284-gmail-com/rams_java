package jp.co.lyc.cms.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.ListUtils;
import org.castor.core.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.amazonaws.internal.FIFOCache;
import com.amazonaws.util.StringUtils;

import ch.qos.logback.core.joran.conditional.IfAction;
import jp.co.lyc.cms.common.BaseController;
import jp.co.lyc.cms.model.SalesSituationModel;
import jp.co.lyc.cms.model.SalesContent;
import jp.co.lyc.cms.service.SalesSituationService;
import jp.co.lyc.cms.validation.SalesSituationValidation;

@Controller
@RequestMapping(value = "/salesSituation")
public class SalesSituationController  extends BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	SalesSituationService salesSituationService;

	/**
	 * データを取得
	 * 
	 * @param emp
	 * @return List
	 */

	@RequestMapping(value = "/getSalesSituation", method = RequestMethod.POST)
	@ResponseBody
	public List<SalesSituationModel> getSalesSituation(@RequestBody SalesSituationModel model) {

		logger.info("getSalesSituation:" + "検索開始");
		List<SalesSituationModel> salesSituationList = new ArrayList<SalesSituationModel>();
		try {
			salesSituationList = salesSituationService.getSalesSituationModel(model.getSalesYearAndMonth());
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("getSalesSituation" + "検索結束");
		return salesSituationList;
	}
	
	@RequestMapping(value = "/updateSalesSituation", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateSalesSituation(@RequestBody SalesSituationModel model) {

		model.setUpdateUser(getSession().getAttribute("employeeName").toString());
		logger.info("updateSalesSituation:" + "検索開始");
		String[] errorsMessage = new String[]{""};
		DataBinder binder = new DataBinder(model);
		binder.setValidator(new SalesSituationValidation());
		binder.validate();
		BindingResult results = binder.getBindingResult();
		Map<String, Object> result = new HashMap<>();
		if (results.hasErrors()) {
			results.getAllErrors().forEach((o) -> {
				FieldError error = (FieldError) o;
				errorsMessage[0] += error.getDefaultMessage();// エラーメッセージ
			});
			result.put("errorsMessage", errorsMessage[0]);// エラーメッセージ
			return result;
		}
		int index = 0;
		try {
			index = salesSituationService.insertSalesSituation(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		result.put("updateREcords", index);
		logger.info("updateSalesSituation" + "検索結束");
		return result;
	}

	/**
	 * データを取得
	 * 
	 * @param emp
	 * @return List
	 */

	@RequestMapping(value = "/updateEmployeeSiteInfo", method = RequestMethod.POST)
	@ResponseBody
	public int updateEmployeeSiteInfo(@RequestBody SalesSituationModel model) {


		model.setUpdateUser(getSession().getAttribute("employeeName").toString());
		logger.info("updateSalesSituation:" + "検索開始");
		int index = 0;
		int index1 = 0;
		try {
			index = salesSituationService.updateEmployeeSiteInfo(model);
			index1 = salesSituationService.updateSalesSituation(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("updateSalesSituation" + "検索結束");
		return index+index1;
	}
	
	/**
	 * データを取得
	 * 
	 * @param emp
	 * @return List
	 */

	@RequestMapping(value = "/getPersonalSalesInfo", method = RequestMethod.POST)
	@ResponseBody
	public List<SalesSituationModel> getPersonalSalesInfo(@RequestBody SalesSituationModel model) {

		logger.info("getPersonalSalesInfo:" + "検索開始");
		int count = salesSituationService.getCount(model.getEmployeeNo());
		List<SalesSituationModel> salesSituationList = new ArrayList<SalesSituationModel>();
		if(count==0) {
			try {
				salesSituationList = salesSituationService.getPersonalSalesInfo(model.getEmployeeNo());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			try {
				salesSituationList = salesSituationService.getPersonalSalesInfoFromT019(model.getEmployeeNo());
				/* 2020/12/09 STRAT 張棟*/
				//　時間のフォーマットを変更する。例えば「202009->2020/09」
				if (salesSituationList != null && salesSituationList.size() != 0 &&
						!StringUtils.isNullOrEmpty(salesSituationList.get(0).getTheMonthOfStartWork())) {
						salesSituationList.get(0).setTheMonthOfStartWork(DateFormat(salesSituationList.get(0).getTheMonthOfStartWork()));
				} else {
					// 取るデータがnullの時
					
				}
				/* 2020/12/09 END 張棟*/
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		

		logger.info("updateSalesSituation" + "検索結束");
		return salesSituationList;
	}
	
	/**
	 * データを取得
	 * 
	 * @param emp
	 * @return List
	 */

	@RequestMapping(value = "/updateEmployeeAddressInfo", method = RequestMethod.POST)
	@ResponseBody
	public int updateEmployeeAddressInfo(@RequestBody SalesSituationModel model) {


		model.setUpdateUser(getSession().getAttribute("employeeName").toString());
		logger.info("getPersonalSalesInfo:" + "検索開始");
		int index =0;
		try {
			index = salesSituationService.updateEmployeeAddressInfo(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("updateSalesSituation" + "検索結束");
		return index;
	}
	
	@RequestMapping(value = "/updateSalesSentence", method = RequestMethod.POST)
	@ResponseBody
	public int updateSalesSentence(@RequestBody SalesContent model) {


		model.setUpdateUser(getSession().getAttribute("employeeName").toString());
		logger.info("getPersonalSalesInfo:" + "検索開始");
		int index =0;
		try {
			index = salesSituationService.updateSalesSentence(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("updateSalesSituation" + "検索結束");
		return index;
	}
	

	/**
	 * 文字列の実装
	 * */
	private String DateFormat(String date) {
		String dateStr = date.substring(0, 4)
		+ "/"
		+ date.substring(4, 6);
		return dateStr;
	}

}
