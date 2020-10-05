package jp.co.lyc.cms.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.lyc.cms.common.BaseController;
import jp.co.lyc.cms.model.SendLettersConfirmModel;
import jp.co.lyc.cms.service.SendLettersConfirmService;

@Controller
@RequestMapping(value = "/sendLettersConfirm")
public class SendLettersConfirm  extends BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	SendLettersConfirmService sendLettersConfirmService;

	/**
	 * データを取得
	 * 
	 * @param emp
	 * @return List
	 */

	@RequestMapping(value = "/getSalesEmps", method = RequestMethod.POST)
	@ResponseBody
	public List<SendLettersConfirmModel> getSalesEmps(@RequestBody SendLettersConfirmModel model) {

		logger.info("getSalesEmps:" + "検索開始");
		List<SendLettersConfirmModel> sendLettersConfirmModelList = new ArrayList<SendLettersConfirmModel>();
		try {
			sendLettersConfirmModelList = sendLettersConfirmService.getSalesEmps(model.getEmployeeNos());
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("getSalesEmps" + "検索結束");
		return sendLettersConfirmModelList;
	}
}