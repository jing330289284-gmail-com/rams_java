package jp.co.lyc.cms.controller;

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
import jp.co.lyc.cms.model.AccountInfoModel;
import jp.co.lyc.cms.service.AccountInfoService;
import jp.co.lyc.cms.service.UtilsService;
import jp.co.lyc.cms.validation.AccountInfoValidation;
@Controller
@RequestMapping(value = "/bankInfo")
public class AccountInfoController extends BaseController{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//口座情報
	@Autowired
	AccountInfoService bankInfoSer;
	//選択肢service
	@Autowired
	UtilsService selectutilSer;
	
	String errorsMessage = "";
	/**
	 * 画面の初期化
	 * @param onloadMol
	 * @return
	 */
	@RequestMapping(value = "/init", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> init(@RequestBody AccountInfoModel onloadMol) {
		logger.info("BankInfoController.getBankInfo:" + "初期化開始");
		HashMap<String, Object> resultMap = new HashMap<>();
		AccountInfoModel accountInfoMod = new AccountInfoModel();
		if (onloadMol.getActionType().equals("update") || onloadMol.getActionType().equals("detail")) {
			accountInfoMod = getBankBranchInfo(onloadMol.getEmployeeOrCustomerNo());
		}
		resultMap.put("bankName", selectutilSer.getBankInfo());
		resultMap.put("accountInfoMod", accountInfoMod);
		logger.info("BankInfoController.getBankInfo:" + "初期化終了");
		return resultMap;	
	}
	/**
	 * アープデート
	 * @param accountInfoModel
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> update(@RequestBody AccountInfoModel accountInfoModel) {
		errorsMessage = "";
		DataBinder binder = new DataBinder(accountInfoModel);
		binder.setValidator(new AccountInfoValidation());
		binder.validate();
		BindingResult results = binder.getBindingResult();
		Map<String, Object> result = new HashMap<>();
		if (results.hasErrors()) {
			results.getAllErrors().forEach(o -> {
				FieldError error = (FieldError) o;
				errorsMessage += error.getDefaultMessage();// エラーメッセージ
			});
			result.put("errorsMessage", errorsMessage);// エラーメッセージ
			return result;
		}
		accountInfoModel.setUpdateUser((String)getSession().getAttribute("employeeName"));
		result.put("result", bankInfoSer.update(bankInfoSer.setSendMap(accountInfoModel)));
		return result;
	}
	/**
	 * データを取得
	 * @param employeeOrCustomerNo
	 * * @param accountBelongsStatus
	 * @return
	 */
	public AccountInfoModel getBankBranchInfo(String employeeOrCustomerNo) {
		logger.info("BankInfoController.selectBankInfo:" + "検索開始");
		AccountInfoModel resultMod = bankInfoSer.selectAccountInfo(employeeOrCustomerNo);
		logger.info("BankInfoController.toroku:" + "検索終了");
		return resultMod;	
	}
}
