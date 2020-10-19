package jp.co.lyc.cms.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sun.mail.util.MailSSLSocketFactory;

import jp.co.lyc.cms.model.EmailModel;
import jp.co.lyc.cms.model.EmployeeModel;
import jp.co.lyc.cms.model.ModelClass;
import jp.co.lyc.cms.service.UtilsService;
import net.sf.json.JSONObject;

@Controller
public class UtilsController {

	@Autowired
	UtilsService utilsService;

	/**
	 * 国籍を取得
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getNationalitys", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getNationalitys() {
		List<ModelClass> list = utilsService.getNationalitys();
		return list;
	}

	/**
	 * 営業結果パターン
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getSalesPuttern", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getSalesPuttern() {
		List<ModelClass> list = utilsService.getSalesPuttern();
		return list;
	}

	/**
	 * 特別ポイント
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getSpecialPoint", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getSpecialPoint() {
		List<ModelClass> list = utilsService.getSpecialPoint();
		return list;
	}

	/**
	 * 契約区分
	 * 
	 * @return
	 */

	@RequestMapping(value = "/getCustomerContractStatus", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getCustomerContractStatus() {
		Properties properties = getProperties();
		String customerContractStatus = properties.getProperty("customerContract");
		List<ModelClass> list = getStatus(customerContractStatus);
		return list;
	}

	/**
	 * 社員形式を取得
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getStaffForms", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getStaffForms() {
		List<ModelClass> list = utilsService.getStaffForms();
		return list;
	}

	/**
	 * 在留資格を取得
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getVisa", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getVisa() {
		List<ModelClass> list = utilsService.getVisa();
		return list;
	}

	/**
	 * 開発言語を取得
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getTechnologyType", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getTechnologyType(@RequestBody EmployeeModel emp) {
		Map<String, String> sendMap = getParam(emp);
		List<ModelClass> list = utilsService.getTechnologyType(sendMap);
		return list;
	}

	/**
	 * レベル
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getLevel", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getLevel() {
		List<ModelClass> list = utilsService.getLevel();
		return list;
	}

	/**
	 * 日本語レベルを取得
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getJapaneseLevel", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getJapaneseLevel() {
		List<ModelClass> list = utilsService.getJapaneseLevel();
		return list;
	}

	/**
	 * お客様性質を取得
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getCompanyNature", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getCompanyNature() {
		List<ModelClass> list = utilsService.getCompanyNature();
		return list;
	}

	/**
	 * 職位を取得
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getPosition", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getPosition() {
		List<ModelClass> list = utilsService.getPosition();
		return list;
	}

	/**
	 * 上位お客様連想
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getTopCustomerDrop", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getTopCustomerDrop() {
		List<ModelClass> list = utilsService.getTopCustomerDrop();
		return list;
	}

	/**
	 * 部門名前連想
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getDepartmentMasterDrop", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getDepartmentMasterDrop() {
		List<ModelClass> list = utilsService.getDepartmentMasterDrop();
		return list;
	}

	/**
	 * 銀行名検索
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getBankInfo", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getBankInfo() {
		List<ModelClass> list = utilsService.getBankInfo();
		return list;
	}

	/**
	 * 支店情報検索
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getBankBranchInfo", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getBankBranchInfo(@RequestBody HashMap<String, String> sendMap) {
		List<ModelClass> list = utilsService.getBankBranchInfo(sendMap);
		return list;
	}

	/**
	 * 支払サイト検索
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getPaymentsite", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getPaymentsite() {
		List<ModelClass> list = utilsService.getPaymentsite();
		return list;
	}

	/**
	 * 性別区別
	 * 
	 * @return
	 */

	@RequestMapping(value = "/getGender", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getGender() {
		Properties properties = getProperties();
		String gender = properties.getProperty("gender");
		List<ModelClass> list = getStatus(gender);
		return list;
	}

	/**
	 * approvalを取得する
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getApproval", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getApproval() {
		Properties properties = getProperties();
		String approval = properties.getProperty("approval");
		List<ModelClass> list = getStatus(approval);
		return list;
	}

	/**
	 * CheckSectionを取得する
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getCheckSection", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getCheckSection() {
		Properties properties = getProperties();
		String checkSection = properties.getProperty("checkSection");
		List<ModelClass> list = getStatus(checkSection);
		return list;
	}
	/**
	 * CheckSectionを取得する
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getRound", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getRound() {
		Properties properties = getProperties();
		String round = properties.getProperty("round");
		List<ModelClass> list = getStatus(round);
		return list;
	}
	/**
	 * 上場
	 * 
	 * @return
	 */

	@RequestMapping(value = "/getListedCompany", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getListedCompany() {
		Properties properties = getProperties();
		String listedCompany = properties.getProperty("listedCompany");
		List<ModelClass> list = getStatus(listedCompany);
		return list;
	}

	/**
	 * 住宅
	 * 
	 * @return
	 */

	@RequestMapping(value = "/getHousing", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getHousing() {
		Properties properties = getProperties();
		String housing = properties.getProperty("housing");
		List<ModelClass> list = getStatus(housing);
		return list;
	}

	/**
	 * 精算時間
	 * 
	 * @return
	 */

	@RequestMapping(value = "/getPayOffRange", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getPayOffRange() {
		Properties properties = getProperties();
		String payOffRange = properties.getProperty("payOffRange");
		List<ModelClass> list = getStatus(payOffRange);
		return list;
	}

	/**
	 * マスターを取得
	 * 
	 * @return
	 */

	@RequestMapping(value = "/getMaster", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getMaster() {
		Properties properties = getProperties();
		String master = properties.getProperty("master");
		List<ModelClass> list = getStatus(master);
		return list;
	}

	/**
	 * 社員
	 * 
	 * @return
	 */

	@RequestMapping(value = "/getEmployeeStatus", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getEmployeeStatus() {
		Properties properties = getProperties();
		String employee = properties.getProperty("employee");
		List<ModelClass> list = getStatus(employee);
		return list;
	}

	/**
	 * 新人
	 * 
	 * @return
	 */

	@RequestMapping(value = "/getNewMember", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getNewMember() {
		Properties properties = getProperties();
		String newMember = properties.getProperty("newMember");
		List<ModelClass> list = getStatus(newMember);
		return list;
	}

	/**
	 * 口座種類
	 * 
	 * @return
	 */

	@RequestMapping(value = "/getAccountType", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getAccountType() {
		Properties properties = getProperties();
		String accountType = properties.getProperty("accountType");
		List<ModelClass> list = getStatus(accountType);
		return list;
	}

	/**
	 * 口座所属
	 * 
	 * @return
	 */

	@RequestMapping(value = "/getAccountBelongs", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getAccountBelongs() {
		Properties properties = getProperties();
		String accountBelongs = properties.getProperty("accountBelongs");
		List<ModelClass> list = getStatus(accountBelongs);
		return list;
	}

	/**
	 * 資格
	 * 
	 * @return
	 */

	@RequestMapping(value = "/getQualificationType", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getQualificationType() {
		Properties properties = getProperties();
		String qualificationType = properties.getProperty("qualificationType");
		List<ModelClass> list = getStatus(qualificationType);
		return list;
	}

	/**
	 * ボーナス
	 * 
	 * @return
	 */

	@RequestMapping(value = "/getBonus", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getBonus() {
		Properties properties = getProperties();
		String bonus = properties.getProperty("bonus");
		List<ModelClass> list = getStatus(bonus);
		return list;
	}

	/**
	 * 社会保険
	 * 
	 * @return
	 */

	@RequestMapping(value = "/getInsurance", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getInsurance() {
		Properties properties = getProperties();
		String insurance = properties.getProperty("insurance");
		List<ModelClass> list = getStatus(insurance);
		return list;
	}

	/**
	 * 住宅ステータス
	 * 
	 * @return
	 */

	@RequestMapping(value = "/getHousingStatus", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getHousingStatus() {
		Properties properties = getProperties();
		String HousingStatus = properties.getProperty("housingStatus");
		List<ModelClass> list = getStatus(HousingStatus);
		return list;
	}

	/**
	 * 営業ステータス
	 * 
	 * @return
	 */

	@RequestMapping(value = "/getSalesStatus", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getSalesStatus() {
		Properties properties = getProperties();
		String salesProgressCode = properties.getProperty("salesProgressCode");
		List<ModelClass> list = getStatus(salesProgressCode);
		return list;
	}

	/**
	 * 営業優先度
	 * 
	 * @return
	 */

	@RequestMapping(value = "/getSalesPriorityStatus", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getSalesPriorityStatus() {
		Properties properties = getProperties();
		String salesPriorityStatus = properties.getProperty("salesPriorityStatus");
		List<ModelClass> list = getStatus(salesPriorityStatus);
		return list;
	}

	/**
	 * 営業担当選ぶ
	 * 
	 * @return
	 */

	@RequestMapping(value = "/getSalesPerson", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getSalesPerson() {
		List<ModelClass> list = utilsService.getSalesPerson();
		return list;
	}

	/**
	 * 営業状況取得
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getSalesProgress", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getSalesProgress() {
		List<ModelClass> list = utilsService.getSalesProgress();
		return list;
	}
	
	/**
	 * 日本語ラベル状況取得
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getJapaneaseConversationLevel", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getJapaneaseConversationLevel() {
		List<ModelClass> list = utilsService.getJapaneaseConversationLevel();
		return list;
	}
	
	/**
	 * 英語状況取得
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getEnglishConversationLevel", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getEnglishConversationLevel() {
		List<ModelClass> list = utilsService.getEnglishConversationLevel();
		return list;
	}
	
	/**
	 * 対応工程況取得
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getProjectPhase", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getProjectPhase() {
		List<ModelClass> list = utilsService.getProjectPhase();
		return list;
	}

	/**
	 * 場所取る
	 * 
	 * @return
	 */

	@RequestMapping(value = "/getStation", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getStation() {
		List<ModelClass> list = utilsService.getStation();
		return list;
	}
	/**
	 * 費用区分取る
	 * 
	 * @return
	 */

	@RequestMapping(value = "/getCostClassification", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getCostClassification() {
		List<ModelClass> list = utilsService.getCostClassification();
		return list;
	}
	/**
	 *交通手段を取る
	 * 
	 * @return
	 */

	@RequestMapping(value = "/getTransportation", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getTransportation() {
		List<ModelClass> list = utilsService.getTransportation();
		return list;
	}
	
	/**
	 * 業種を取る
	 * 
	 * @return
	 */

	@RequestMapping(value = "/getTypeOfIndustry", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getTypeOfIndustry() {
		List<ModelClass> list = utilsService.getTypeOfIndustry();
		return list;
	}

	public List<ModelClass> getStatus(String string) {
		JSONObject sJson = JSONObject.fromObject(string);
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) sJson;
		List<ModelClass> list = new ArrayList<ModelClass>();
		for (Entry<String, String> entry : map.entrySet()) {
			ModelClass statusModel = new ModelClass();
			statusModel.setCode(entry.getKey());
			statusModel.setName(entry.getValue());
			statusModel.setValue(entry.getKey());
			statusModel.setText(entry.getValue());
			list.add(statusModel);
		}
		return list;
	}

	/**
	 * 入社区分を取得
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getIntoCompany", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getIntoCompany() {
		List<ModelClass> list = utilsService.getIntoCompany();
		return list;
	}

	/**
	 * 役割を取得
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getSiteMaster", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getSiteMaster() {
		List<ModelClass> list = utilsService.getSiteMaster();
		return list;
	}

	/**
	 * お客様を取得
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getCustomer", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getCustomer() {
		List<ModelClass> list = utilsService.getCustomer();
		return list;
	}

	/**
	 * トップお客様を取得
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getTopCustomer", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getTopCustomer() {
		List<ModelClass> list = utilsService.getTopCustomer();
		return list;
	}

	/**
	 * 開発言語を取得
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getDevelopLanguage", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getDevelopLanguage() {
		List<ModelClass> list = utilsService.getDevelopLanguage();
		return list;
	}

	/**
	 * 職種を取得
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getOccupation", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getOccupation() {
		List<ModelClass> list = utilsService.getOccupation();
		return list;
	}

	/**
	 * 部署を取得
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getDepartment", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getDepartment() {
		List<ModelClass> list = utilsService.getDepartment();
		return list;
	}

	/**
	 * 権限を取得
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getAuthority", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getAuthority() {
		List<ModelClass> list = utilsService.getAuthority();
		return list;
	}

	/**
	 * 英語を取得
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getEnglishLevel", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getEnglishLevel() {
		List<ModelClass> list = utilsService.getEnglishLevel();
		return list;
	}

	/**
	 * 資格を取得
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getQualification", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getQualification() {
		List<ModelClass> list = utilsService.getQualification();
		return list;
	}
	
	/**
	 * 社員氏名（BP社員ない）を取得する
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getEmployeeNameNoBP", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getEmployeeNameNoBP() {
		List<ModelClass> list = utilsService.getEmployeeNameNoBP();
		return list;
	}

	/**
	 * 条件を取得
	 * 
	 * @param emp
	 * @return
	 */
	public Map<String, String> getParam(EmployeeModel emp) {
		Map<String, String> sendMap = new HashMap<String, String>();
		String developmentLanguageNo1 = emp.getDevelopLanguage1();// 開発言語1
		if (developmentLanguageNo1 != null && developmentLanguageNo1.length() != 0) {
			sendMap.put("developmentLanguageNo1", developmentLanguageNo1);
		}
		return sendMap;
	}

	/**
	 * パスワードを取得
	 * 
	 * @param emp
	 * @return
	 */
	@RequestMapping(value = "/getPassword", method = RequestMethod.POST)
	@ResponseBody
	public String getPassword(@RequestBody EmployeeModel emp) {
		return utilsService.getPassword(emp.getEmployeeNo());
	}

	/**
	 * 社員氏名を取得する
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getEmployeeName", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getEmployeeName() {
		List<ModelClass> list = utilsService.getEmployeeName();
		return list;
	}

	/**
	 * パスワードリセット
	 * 
	 * @param emp
	 * @return
	 */
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	@ResponseBody
	public boolean resetPassword(@RequestBody EmployeeModel emp) {
		HashMap<String, String> sendMap = new HashMap<>();
		sendMap.put("employeeNo", emp.getEmployeeNo());
		sendMap.put("password", emp.getPassword());
		sendMap.put("oldPassword", emp.getOldPassword());
		return utilsService.resetPassword(sendMap);
	}

	/**
	 * 採番
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getNO", method = RequestMethod.POST)
	@ResponseBody
	public String getNO(@RequestBody ModelClass mo) {
		Map<String, String> sendMap = new HashMap<String, String>();
		// sendMap.put("columnName", "customerNo");
		// sendMap.put("typeName", "C");
		// sendMap.put("table", "employee_site_information");
		String columnName = mo.getColumnName();// 列名は採番番号名です
		String typeName = mo.getTypeName();// 採番番号のタイプ
		String table = mo.getName();// テーブル
		if (columnName != null && columnName.length() != 0) {
			sendMap.put("columnName", columnName);
		}
		if (typeName != null && typeName.length() != 0) {
			sendMap.put("typeName", typeName);
		}
		if (table != null && table.length() != 0) {
			sendMap.put("table", table);
		}
		String no = utilsService.getNO(sendMap);
		return no;
	}

	/**
	 * xmlを読み
	 * 
	 * @return
	 */
	public Properties getProperties() {
		Resource resource = new ClassPathResource("system.properties");
		Properties props = null;
		try {
			props = PropertiesLoaderUtils.loadProperties(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props;
	}

	public final static String UPLOAD_PATH_PREFIX = "c:/file/";

	public Map<String, Object> upload(MultipartFile uploadFile, Map<String, Object> sendMap, String key, String Info) {
		if (uploadFile == null) {
			sendMap.put(key, "");
			return sendMap;
		}
		String realPath = new String(UPLOAD_PATH_PREFIX + sendMap.get("employeeNo") + "_"
				+ sendMap.get("employeeFristName") + sendMap.get("employeeLastName"));
		File file = new File(realPath);
		if (!file.isDirectory()) {
			file.mkdirs();
		}
		String fileName = uploadFile.getOriginalFilename();
		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
		String newName = sendMap.get("employeeFristName").toString() + sendMap.get("employeeLastName").toString() + "_"
				+ Info + "." + suffix;
		try {
			File newFile = new File(file.getAbsolutePath() + File.separator + newName);
			uploadFile.transferTo(newFile);
			sendMap.put(key, realPath + "/" + newName);
			return sendMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sendMap;

	}

	@RequestMapping(value = "/download", method = RequestMethod.POST)
	@ResponseBody
	public void downloadTemplateFile(@RequestBody ModelClass model, HttpServletResponse response) throws IOException {
		String filePath = model.getName();
		InputStream is = null;
		BufferedInputStream bis = null;
		OutputStream os = null;
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				return;
			}
			is = new FileInputStream(file);
			os = response.getOutputStream();
			bis = new BufferedInputStream(is);
			// 设置响应头信息
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream; charset=UTF-8");
			StringBuffer contentDisposition = new StringBuffer("attachment; filename=\"");
			String fileName = new String(file.getName().getBytes(), "utf-8");
			contentDisposition.append(fileName).append("\"");
			response.setHeader("Content-disposition", contentDisposition.toString());
			// 边读边写
			byte[] buffer = new byte[500];
			int i;
			while ((i = bis.read(buffer)) != -1) {
				os.write(buffer, 0, i);
			}
			os.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (os != null)
					os.close();
				if (bis != null)
					bis.close();
				if (is != null)
					is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * メールを発送する
	 * 
	 * @param emailMod
	 */
	public void EmailSend(EmailModel emailMod) {
		Session session = null;
		try {
			// 创建一个资源文件
			Properties properties = new Properties();
			// 显示日志
			properties.setProperty("mail.debug", "true");
			// 邮箱类别
			properties.setProperty("mail.host", "smtp.lolipop.jp");
			// 设定验证开启
			properties.setProperty("mail.smtp.auth", "true");
			// 发送 接受方式
			properties.setProperty("mail.transpot.prococol", "smtp");
			// 设置请求服务器端口号
			properties.put("mail.smtp.port", 587);
			// 设置ssl加密服务开启
			properties.setProperty("mail.smtp.ssl.enable", "smtp");
			// 创建加密证书
			MailSSLSocketFactory sf = new MailSSLSocketFactory();
			// properties 底层调用的的是put方法
			properties.put("Mail.smtp.ssl.socketFactory", sf);
			// 获取具有以上属性的邮件session --->连接池
			session = Session.getInstance(properties);
			// 创建获取连接
			Transport transport = session.getTransport();
			// 进行连接
			transport.connect(emailMod.getUserName(), emailMod.getPassword());
			// 创建一个信息
			Message message = new MimeMessage(session);
			// 设定发送方
			message.setFrom(new InternetAddress(emailMod.getUserName()));
			// 设置主题内容
			message.setSubject(emailMod.getSubject());
			message.setContent(emailMod.getContext(), "text/html;charset=utf-8");
			;
			String[] addresss = emailMod.getToAddress().split(",");
			int len = addresss.length;
			Address[] adds = new Address[len];
			for (int i = 0; i < len; i++) {
				adds[i] = new InternetAddress(addresss[i]);
			}

			// 发送邮件
			transport.sendMessage(message, adds);
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * enterPeriodを取得する
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getEnterPeriod", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getEnterPeriod() {
		Properties properties = getProperties();
		String enterPeriod = properties.getProperty("enterPeriod");
		List<ModelClass> list = getStatus(enterPeriod);
		return list;
	}
	
    /**
             * 日数計算
     * @param startTime ： 開始時間
     * @param endTime  ： 終了時間
     * @return   
     */
    public static int caculateTotalTime(String startTime,String endTime) {
        SimpleDateFormat formatter =   new SimpleDateFormat( "yyyy-MM-dd");
        Date date1=null;
        Date date = null;
        Long l = 0L;
        try {
            date = formatter.parse(startTime);
            long ts = date.getTime();
            date1 =  formatter.parse(endTime);
            long ts1 = date1.getTime();

            l = (ts - ts1) / (1000 * 60 * 60 * 24);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return l.intValue();
    }
	/**
	 * 状況変動ステータスを取得する
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getSituationChange", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getSituationChange() {

		List<ModelClass> list = utilsService.getSituationChange();
		return list;
	}
	
	/**
	 * serverIP
	 * 
	 * @return
	 */

	@RequestMapping(value = "/getServerIP", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getServerIP() {
		Properties properties = getProperties();
		String serverIP = properties.getProperty("serverIP");
		List<ModelClass> list = getStatus(serverIP);
		return list;
	}
}
