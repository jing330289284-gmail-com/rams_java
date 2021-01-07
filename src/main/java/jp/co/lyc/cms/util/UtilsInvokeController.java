package jp.co.lyc.cms.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.bouncycastle.jcajce.provider.asymmetric.EC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.lyc.cms.common.BaseController;
import jp.co.lyc.cms.model.ModelClass;

@Controller
public class UtilsInvokeController extends BaseController{
	@Autowired
	UtilsController utilsController;
	
	/**
	 * 画面初期化のselect取得
	 * @param methodNameList
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(value = "/initializationPage", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<List<ModelClass>> initializationPage(@RequestBody ArrayList<String> methodNameList) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		ArrayList<List<ModelClass>> resultList = new ArrayList<List<ModelClass>>();
		for(String methodName : methodNameList) {
			Method method = utilsController.getClass().getMethod(methodName);
			@SuppressWarnings("unchecked")
			List<ModelClass> dropDown = (List<ModelClass>)method.invoke(utilsController);
			resultList.add(dropDown);
		}
		return resultList;
	}
	@RequestMapping(value = "/error", method = RequestMethod.POST)
	@ResponseBody
	public Exception error(){
		Exception e = new Exception();
		System.out.println(e);
		return e;
	}
}
