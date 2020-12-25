package jp.co.lyc.cms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.lyc.cms.common.BaseController;
import jp.co.lyc.cms.model.SalesPointSetModel;
import jp.co.lyc.cms.service.SalesPointSetService;

@Controller
public class SalesPointSetController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	SalesPointSetService salesPointSetService;
	String errorsMessage = "";

	@RequestMapping(value = "/salesPointInsert", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> salesPointInsert(@RequestBody SalesPointSetModel salesPointSetModel) {
		Map<String, Object> result = new HashMap<>();
		errorsMessage = "";

		// 登陆处理
		if (insert(putData(salesPointSetModel))) {
			result.put("result", true);
		} else {
			result.put("result", false);
		}
		logger.info("SalesPointSetController.salesPointInsert:" + "追加結束");
		return result;

	}

	/**
	 * 修正ボタン
	 * 
	 * @return
	 */
	@RequestMapping(value = "/salesPointUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> salesPointUpdate(@RequestBody SalesPointSetModel salesPointSetModel) {
		logger.info("SalesPointSetController.salesPointUpdate:" + "修正開始");
		// チェック
		Map<String, Object> result = new HashMap<>();
//		if (checkHave(salesPointSetModel) == false) {
//			result.put("errorsMessage", StatusCodeToMsgMap.getErrMsgbyCode("MSG008"));// エラーメッセージ
//			return result;
//		}
		// 修正处理
		if (update(salesPointSetModel)) {
			result.put("result", true);
		} else {
			result.put("result", false);
		}
		logger.info("SalesPointSetController.salesPointUpdate:" + "修正結束");
		return result;
	}

	/**
	 * insert
	 * 
	 * @return
	 */

	public boolean insert(Map<String, Object> sendMap) {
		return salesPointSetService.salesPointInsert(sendMap);
	}

	/**
	 * update
	 * 
	 * @return
	 */

	public boolean update(SalesPointSetModel salesPointSetModel) {
		return salesPointSetService.salesPointUpdate(putData(salesPointSetModel));
	}

	/**
	 * 削除ボタン
	 * 
	 * @return
	 */
	@RequestMapping(value = "/salesPointDelete", method = RequestMethod.POST)
	@ResponseBody
	public boolean delete(@RequestBody SalesPointSetModel salesPointSetModel) {
		HashMap<String, Object> sendMap = new HashMap<>();
		sendMap.put("no", salesPointSetModel.getNo());
		return salesPointSetService.salesPointDelete(sendMap);
	}

	@RequestMapping(value = "/getSalesPointInfo", method = RequestMethod.POST)
	@ResponseBody
	public List<SalesPointSetModel> getSalesPointInfo(@RequestBody SalesPointSetModel salesPointSetModel) {
		List<SalesPointSetModel> siteList = new ArrayList<SalesPointSetModel>();
		Map<String, Object> sendMap = new HashMap<String, Object>();
		String employee = salesPointSetModel.getEmployee();
		String newMember = salesPointSetModel.getNewMember();
		String customerContract = salesPointSetModel.getCustomerContract();
		if (employee != null && employee.length() != 0) {
			sendMap.put("employee", employee);
		}
		if (newMember != null && newMember.length() != 0) {
			sendMap.put("newMember", newMember);
		}
		if (customerContract != null && customerContract.length() != 0) {
			sendMap.put("customerContract", customerContract);
		}
		try {
			siteList = salesPointSetService.getSalesPointInfo(sendMap);

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("GetEmployeeInfoController.getEmployeeInfo:" + "検索結束");
		return siteList;
	}

	/**
	 * データ整理
	 * 
	 * @return
	 */
	public Map<String, Object> putData(SalesPointSetModel salesPointSetModel) {
		HttpSession loginSession = getSession();
		Map<String, Object> sendMap = new HashMap<String, Object>();
		String no = salesPointSetModel.getNo();
		String employee = salesPointSetModel.getEmployee();
		String newMember = salesPointSetModel.getNewMember();
		String customerContract = salesPointSetModel.getCustomerContract();
		String level = salesPointSetModel.getLevel();
		String salesPuttern = salesPointSetModel.getSalesPuttern();
		String specialPoint = salesPointSetModel.getSpecialPoint();
		String point = salesPointSetModel.getPoint();
		String specialPointNo = salesPointSetModel.getSpecialPointNo();
		String remark = salesPointSetModel.getRemark();

		if (no != null && no.length() != 0) {
			sendMap.put("no", no);
		}
		if (employee != null && employee.length() != 0) {
			sendMap.put("employee", employee);
		}
		if (newMember != null && newMember.length() != 0) {
			sendMap.put("newMember", newMember);
		}
		if (customerContract != null && customerContract.length() != 0) {
			sendMap.put("customerContract", customerContract);
		}
		if (level != null && level.length() != 0) {
			sendMap.put("level", level);
		}
		if (salesPuttern != null && salesPuttern.length() != 0) {
			sendMap.put("salesPuttern", salesPuttern);
		}
		if (specialPoint != null && specialPoint.length() != 0) {
			sendMap.put("specialPoint", specialPoint);
		}
		if (point != null && point.length() != 0) {
			sendMap.put("point", point);
		}
		if (remark != null && remark.length() != 0) {
			sendMap.put("remark", remark);
		}
		if (specialPointNo != null && specialPointNo.length() != 0) {
			sendMap.put("specialPointNo", specialPointNo);
		}

		sendMap.put("updateUser", loginSession.getAttribute("employeeName"));
		return sendMap;
	}
}
