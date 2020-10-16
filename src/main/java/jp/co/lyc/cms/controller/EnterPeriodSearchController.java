package jp.co.lyc.cms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
import jp.co.lyc.cms.common.BaseController;
import jp.co.lyc.cms.mapper.EnterPeriodSearchMapper;
import jp.co.lyc.cms.model.EnterPeriodSearchModel;
import jp.co.lyc.cms.service.EnterPeriodSearchService;
import jp.co.lyc.cms.validation.EnterPeriodSearchValidation;

@Controller
@RequestMapping(value = "/enterPeriodSearch")
public class EnterPeriodSearchController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EnterPeriodSearchService enterPeriodSearchService;
	@Autowired
	EnterPeriodSearchMapper enterPeriodSearchMapper;
	
	String errorsMessage = "";

	/**
	 * 検索ボタン
	 * @param enterPeriodSearchModel
	 * @return
	 */
	@RequestMapping(value = "/selectEnterPeriodData", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectEnterPeriodData(@RequestBody 
			EnterPeriodSearchModel enterPeriodSearchModel) {
		errorsMessage = "";
		logger.info("EnterPeriodSearchController.selectEnterPeriodData:" + "検索開始");
		DataBinder binder = new DataBinder(enterPeriodSearchModel);
		binder.setValidator(new EnterPeriodSearchValidation());
		binder.validate();
		BindingResult results = binder.getBindingResult();
		Map<String, Object> result = new HashMap<>();
		if (results.hasErrors()) {
			results.getAllErrors().forEach(o -> {
				FieldError error = (FieldError) o;
				errorsMessage += error.getDefaultMessage();// エラーメッセージ
			});
			result.put("errorsMessage", errorsMessage);// エラーメッセージ
			logger.info("EnterPeriodSearchController.selectEnterPeriodData:" + "検索終了");
			return result;
		}
		HashMap<String, String> sendMap = enterPeriodSearchService.getSendMap(enterPeriodSearchModel);
		ArrayList<EnterPeriodSearchModel> resultList = new ArrayList<EnterPeriodSearchModel>();
		if(enterPeriodSearchModel.getEnterPeriodKbn().equals("0")) {
			//入社の場合
			resultList = enterPeriodSearchService.selectEnterPeriodData(sendMap);
		}else if(enterPeriodSearchModel.getEnterPeriodKbn().equals("1")){
			//入場の場合
		}else if(enterPeriodSearchModel.getEnterPeriodKbn().equals("2")){
			//ボーナスの場合
		}		
		if(resultList == null || resultList.size() == 0) {
			errorsMessage += "今月データがないです";// エラーメッセージ
			result.put("errorsMessage", errorsMessage);// エラーメッセージ
		}else {
			result.put("enterPeriodList", resultList);
		}
		logger.info("EnterPeriodSearchController.selectEnterPeriodData:" + "検索終了");
		return result;
	}
}
