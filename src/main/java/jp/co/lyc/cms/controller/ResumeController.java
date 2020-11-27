package jp.co.lyc.cms.controller;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import jp.co.lyc.cms.common.BaseController;
import jp.co.lyc.cms.model.ResumeModel;
import jp.co.lyc.cms.service.ResumeService;

@Controller
@RequestMapping(value = "/resume")
public class ResumeController extends BaseController { 

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	ResumeService resumeService;
	/**
	 * 検索
	 * @param topCustomerMod
	 * @return
	 */
	@RequestMapping(value = "/selectResume", method = RequestMethod.POST)
	@ResponseBody
	public List<ResumeModel> selectResume(ResumeModel resumeModel) {
		resumeModel.setEmployeeNo(getSession().getAttribute("employeeNo").toString());
		logger.info("ResumeController.selectResume:" + "検索開始");
		 List<ResumeModel>  checkMod = resumeService.selectResume(resumeModel);
		logger.info("ResumeController.selectResume:" + "検索終了");
		return checkMod;
	}
	@RequestMapping(value = "/selectEmployeeName", method = RequestMethod.POST)
	@ResponseBody
	public ResumeModel selectEmployeeName(ResumeModel resumeModel) {
		logger.info("CostRegistrationController.selectEmployeeName:" + "検索開始");
		String employeeName;
		employeeName=resumeService.selectEmployeeName(getSession().getAttribute("employeeNo").toString());
		resumeModel.setEmployeeName(employeeName);
		logger.info("CostRegistrationController.selectEmployeeName:" + "検索終了");
		return resumeModel;
	}
	/**
	 * アップデート
	 * @param topCustomerMod
	 * @return
	 */
	public final static String UPLOAD_PATH_PREFIX_resumeInfo = "c:"+File.separator+"file"+File.separator+"履歴書"+File.separator;
	@RequestMapping(value = "/insertResume", method = RequestMethod.POST)
	@ResponseBody
	public boolean insertResume(@RequestParam(value = "emp", required = false) String JSONEmp,
			@RequestParam(value = "filePath1", required = false) MultipartFile filePath1,
			@RequestParam(value = "filePath2", required = false) MultipartFile filePath2
			) throws Exception {
		//resumeInfo 旧ファイルパス　resumeNameファイル名　filePath新ファイルパス
		//ファイル退避(旧ファイルパス/XXX履歴.ｘxxForChangeName)→新ファイルパス存在→新ファイルアップロード,旧ファイルパス削除→新ファイルパス存在しない→改名→SQL実行
		logger.info("ResumeController.insertResume:" + "追加開始");
		JSONObject jsonObject = JSON.parseObject(JSONEmp);
		ResumeModel resumeModel = JSON.parseObject(jsonObject.toJSONString(), new TypeReference<ResumeModel>() {
		});
		resumeModel.setEmployeeNo(getSession().getAttribute("employeeNo").toString());
		resumeModel.setEmployeeName(getSession().getAttribute("employeeName").toString()); 
		String realPath = new String(UPLOAD_PATH_PREFIX_resumeInfo + resumeModel.getEmployeeNo() + "_"
				+ resumeModel.getEmployeeName());
		//ファイル退避
		if(!resumeModel.getResumeInfo1().equals("")) {
			rename(resumeModel,true,1);
		}
		if(!resumeModel.getResumeInfo2().equals("")) {
			rename(resumeModel,true,2);
		}
		String newFile1="";
		String newFile2="";
		if(filePath1==null)  {
			//新ファイルパス存在しない→改名
			if(!resumeModel.getResumeInfo1().equals("")) {
				rename(resumeModel,false,1);
			}
		}else{
			//新ファイルパス存在→新ファイルアップロード→旧ファイルパス削除
			try {
				newFile1=upload(resumeModel.getResumeName1(),filePath1,realPath);
				if(!resumeModel.getResumeInfo1().equals("")) {
					delete(resumeModel,1);
				}
			} catch (Exception e) {
				return false;
			}
		}
		if(filePath2==null)  {
			//新ファイルパス存在しない→改名
			if(!resumeModel.getResumeInfo2().equals("")) {
				rename(resumeModel,false,2);
			}
		}else{
			//新ファイルパス存在→新ファイルアップロード→旧ファイルパス削除
			try {
				newFile2=upload(resumeModel.getResumeName2(),filePath2,realPath);
				if(!resumeModel.getResumeInfo2().equals("")) {
					delete(resumeModel,2);
				}
			} catch (Exception e) {
				return false;
			}
		}
		//SQL実行
		resumeModel.setResumeInfo1(newFile1);
		resumeModel.setResumeInfo2(newFile2);
		boolean result  = resumeService.insertResume(resumeModel);
		logger.info("ResumeController.insertResume:" + "追加結束");
		return result;
	}
	//ファイルリネーム
	private boolean rename(ResumeModel resumeModel,boolean flag,int fileNo) {
		if(flag) {	//旧ファイルを一時退避
			if(fileNo==1){
				String oldPath= new String(resumeModel.getResumeInfo1());
				File oldFile = new File(oldPath);
				try {
					oldFile.renameTo(new File(oldPath + "ForChangeName"));
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}else if(fileNo==2){
				String oldPath= new String(resumeModel.getResumeInfo2());
				File oldFile = new File(oldPath);
				try {
					oldFile.renameTo(new File(oldPath + "ForChangeName"));
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
		}else {	//ファイル変更なし、フロントの名前にする
			String realPath = new String(UPLOAD_PATH_PREFIX_resumeInfo + resumeModel.getEmployeeNo() + "_"
					+ resumeModel.getEmployeeName());
			if(fileNo==1){
				String suffix = resumeModel.getResumeInfo1().substring(resumeModel.getResumeInfo1().lastIndexOf(".") + 1);
				String newName = resumeModel.getResumeName1()+ "." + suffix;
				String oldPath= new String(resumeModel.getResumeInfo1()+ "ForChangeName");
				File oldFile = new File(oldPath);
				try {
						oldFile.renameTo(new File(realPath+ File.separator + newName));
					} catch (Exception e) {
						e.printStackTrace();
						return false;
					}
			}else if(fileNo==2){
				String suffix = resumeModel.getResumeInfo2().substring(resumeModel.getResumeInfo2().lastIndexOf(".") + 1);
				String newName = resumeModel.getResumeName2()+ "." + suffix;
				String oldPath= new String(resumeModel.getResumeInfo2()+ "ForChangeName");
				File oldFile = new File(oldPath);
				try {
						oldFile.renameTo(new File(oldPath+ File.separator + newName));
					} catch (Exception e) {
						e.printStackTrace();
						return false;
					}
				}
			}
		}
		return true;
	}
	public String upload(String resumeName,MultipartFile resumeFile,String realPath) {
		File file = new File(realPath);
		if (!file.isDirectory()) {
			file.mkdirs();
		}
		String fileName =resumeFile.getOriginalFilename();
		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
		String newName = resumeName+ "." + suffix;
		try {
			File newFile = new File(file.getAbsolutePath() + File.separator + newName);
			resumeFile.transferTo(newFile);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return realPath+File.separator+newName;
	}
	public boolean delete(ResumeModel resumeModel,int fileNo) {
		File file = null ;
		boolean  flag=false;
		if(fileNo==1) {
			 file = new File(resumeModel.getResumeInfo1()+ "ForChangeName");
		}
		else if(fileNo==2) {
			 file = new File(resumeModel.getResumeInfo2()+ "ForChangeName");
		}
	    if (file.isFile() && file.exists()) {
	        file.delete();  
	        flag= true;  
	    }
	    return flag;
	}
}
