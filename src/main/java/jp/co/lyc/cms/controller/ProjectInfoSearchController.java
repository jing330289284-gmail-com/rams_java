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

import jp.co.lyc.cms.mapper.ProjectInfoMapper;
import jp.co.lyc.cms.mapper.ProjectInfoSearchMapper;
import jp.co.lyc.cms.model.ProjectInfoModel;
import jp.co.lyc.cms.service.ProjectInfoSearchService;
import jp.co.lyc.cms.service.ProjectInfoService;
import jp.co.lyc.cms.validation.ProjectInfoValidation;

@Controller
@RequestMapping(value = "/projectInfoSearch")
public class ProjectInfoSearchController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ProjectInfoSearchService projectInfoSearchService;
	
	@Autowired
	ProjectInfoSearchMapper projectInfoSearchMapper;
	
	String errorsMessage = "";
	
	/**
	 * 画面初期化
	 * @param projectInfoModel
	 * @return
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> search(@RequestBody ProjectInfoModel projectInfoModel) {
		logger.info("projectInfoSearchController.search:" + "初期化開始");
		Map<String, Object> result = new HashMap<String, Object>();
		errorsMessage = "";
		DataBinder binder = new DataBinder(projectInfoModel);
		binder.setValidator(new ProjectInfoValidation());
		binder.validate();
		BindingResult results = binder.getBindingResult();
		if (results.hasErrors()) {
			results.getAllErrors().forEach(o -> {
				FieldError error = (FieldError) o;
				errorsMessage += error.getDefaultMessage();// エラーメッセージ
			});
			result.put("errorsMessage", errorsMessage);// エラーメッセージ
			logger.info("projectInfoSearchController.onloadPage:" + "登録終了");
			return result;
		}
		ArrayList<ProjectInfoModel> resultList =  projectInfoSearchService.getProjectInfo(projectInfoModel);
		if(resultList.size() == 0) {
			result.put("errorsMessage","該当データなし");
			return result;
		}
		result.put("projectInfoList",resultList);
		return result;
	}
	/**
	 * 案件情報の削除
	 * @param projectInfoModel
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete(@RequestBody ProjectInfoModel projectInfoModel) {
		logger.info("projectInfoSearchController.delete:" + "初期化開始");
		Map<String, Object> result = new HashMap<String, Object>();
		errorsMessage = "";
		DataBinder binder = new DataBinder(projectInfoModel);
		binder.setValidator(new ProjectInfoValidation());
		binder.validate();
		BindingResult results = binder.getBindingResult();
		if (results.hasErrors()) {
			results.getAllErrors().forEach(o -> {
				FieldError error = (FieldError) o;
				errorsMessage += error.getDefaultMessage();// エラーメッセージ
			});
			result.put("errorsMessage", errorsMessage);// エラーメッセージ
			logger.info("projectInfoSearchController.delete:" + "登録終了");
			return result;
		}
		boolean res = projectInfoSearchService.delete(projectInfoModel);
		if(res) {
			result.put("message", "削除成功");
		}else {
			result.put("errorsMessage", "削除失敗");
		}
		return result;
	}
}
