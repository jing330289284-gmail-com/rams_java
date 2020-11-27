package jp.co.lyc.cms.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

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
import jp.co.lyc.cms.model.BranchModel;
import jp.co.lyc.cms.service.BranchInsertService;
import jp.co.lyc.cms.util.StatusCodeToMsgMap;
import jp.co.lyc.cms.validation.BranchInsertValidation;



@Controller
@RequestMapping(value = "/branchInsert")
public class BranchInsertController extends BaseController{
	@Autowired
	BranchInsertService branchInsertService;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	String errorsMessage = "";
	
	@RequestMapping(value = "/toroku", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> toroku(@RequestBody BranchModel branchModel) {
		errorsMessage="";
		DataBinder binder = new DataBinder(branchModel);
		binder.setValidator(new BranchInsertValidation());
		binder.validate();
		BindingResult results = binder.getBindingResult();
		Map<String, Object> resulterr = new HashMap<>();
		if (results.hasErrors()) {
			results.getAllErrors().forEach(o -> {
				FieldError error = (FieldError) o;
				errorsMessage += error.getDefaultMessage();// エラーメッセージ
			});

			resulterr.put("errorsMessage", errorsMessage);// エラーメッセージ
			return resulterr;
		}else {
		logger.info("MasterInsertController.toroku:" + "追加開始");
		// チェック
		Map<String, Object> result = new HashMap<>();
		if (checkHave(branchModel) == false) {
			result.put("errorsMessage", StatusCodeToMsgMap.getErrMsgbyCode("MSG008"));// エラーメッセージ
			return result;
		}
		if (insert(branchModel)) {
			result.put("result", true);
		} else {
			result.put("result", false);
		}
		logger.info("MasterInsertController.toroku:" + "追加結束");
		return result;
		}
	}
	
	/**
	 * インサート
	 * 
	 * @return
	 */
	public boolean insert(BranchModel branchModel) {
		HttpSession loginSession = getSession();
		HashMap<String, Object> sendMap = new HashMap<>();
		sendMap.put("updateUser", loginSession.getAttribute("employeeName"));
		sendMap.put("bankBranchCode",branchModel.getBankBranchCode());
		sendMap.put("bankBranchName",branchModel.getBankBranchName());
		sendMap.put("bankCode",branchModel.getBankCode());
		return branchInsertService.insertMaster(sendMap);

	}
	
	/**
	 * あるかどうかのチェック
	 * 
	 * @return
	 */
	public boolean checkHave(BranchModel branchModel) {
		return branchInsertService.checkHave(branchModel);

	}
}
