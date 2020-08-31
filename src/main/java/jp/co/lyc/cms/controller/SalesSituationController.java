package jp.co.lyc.cms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.lyc.cms.model.SalesSituationModel;
import jp.co.lyc.cms.service.SalesSituationService;

@Controller
@CrossOrigin(origins = "http://127.0.0.1:3000")
@RequestMapping(value = "/salesSituation")
public class SalesSituationController {

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
	public int updateSalesSituation(@RequestBody SalesSituationModel model) {

		logger.info("updateSalesSituation:" + "検索開始");
		int index = 0;
		try {
			index = salesSituationService.insertSalesSituation(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("updateSalesSituation" + "検索結束");
		return index;
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


}
