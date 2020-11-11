package jp.co.lyc.cms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.lyc.cms.common.BaseController;
import jp.co.lyc.cms.model.SalesPointSetModel;
import jp.co.lyc.cms.model.SalesProfitModel;
import jp.co.lyc.cms.service.SalesProfitService;

@Controller
@CrossOrigin(origins = "http://127.0.0.1:3000")
public class SalesProfitController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	SalesProfitService salesProfitService;

	String errorsMessage = "";

	@RequestMapping(value = "/getSalesProfitInfo", method = RequestMethod.POST)
	@ResponseBody
	public List<SalesProfitModel> getSalesPointInfo(@RequestBody SalesProfitModel salesProfitModel) {
		// List<SalesPointSetModel> siteList = new ArrayList<SalesPointSetModel>();
		// Map<String, Object> sendMap = new HashMap<String, Object>();
		// String employee = salesPointSetModel.getEmployee();
		// String newMember = salesPointSetModel.getNewMember();
		// String customerContract = salesPointSetModel.getCustomerContract();
		// if (employee != null && employee.length() != 0) {
		// sendMap.put("employee", employee);
		// }
		// if (newMember != null && newMember.length() != 0) {
		// sendMap.put("newMember", newMember);
		// }
		// if (customerContract != null && customerContract.length() != 0) {
		// sendMap.put("customerContract", customerContract);
		// }
		// try {
		// siteList = salesPointSetService.getSalesPointInfo(sendMap);
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		List<SalesProfitModel> siteList = salesProfitService.getSalesProfitInfo(salesProfitModel);

		// SalesProfitModel sm = new SalesProfitModel();
		// sm.setRowNo("1");
		// sm.setYearAndMonth("10月");
		// sm.setEmployeeName("テスト社員");
		// sm.setEmployeeStatus("在籍");
		// siteList.add(sm);
//		SalesProfitModel sm = new SalesProfitModel();
//		sm.setRowNo("1");
//		sm.setYearAndMonth(salesProfitModel.getStartDate());
//		sm.setEmployeeName(salesProfitModel.getEmployeeName());
//		sm.setEmployeeStatus(salesProfitModel.getEmployeeStatus());
//		siteList.add(sm);

		logger.info("SalesProfitController.getSalesPointInfo:" + "検索結束");
		return siteList;
	}

	@RequestMapping(value = "/getSalesProfitInfo", method = RequestMethod.POST)
	@ResponseBody
	public List<SalesProfitModel> getSalesInfo(@RequestBody SalesProfitModel salesProfitModel) {

		// List<SalesProfitModel> siteList =
		// salesProfitService.getSalesProfitInfo(salesProfitModel);

		logger.info("SalesProfitController.getSalesPointInfo:" + "検索結束");
		return null;
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

		sendMap.put("updateUser", loginSession.getAttribute("employeeName"));
		return sendMap;
	}
}
