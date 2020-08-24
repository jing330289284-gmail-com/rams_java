package jp.co.lyc.cms.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.lyc.cms.mapper.UtilsMapper;
import jp.co.lyc.cms.model.ModelClass;

@Component
public class UtilsService {
	@Autowired
	UtilsMapper utilsMapper;

	/**
	 * 国籍を取得
	 * 
	 * @return
	 */
	public List<ModelClass> getNationalitys() {
		List<ModelClass> list = utilsMapper.getNationalitys();
		return list;
	}

	/**
	 * 社員形式を取得
	 * 
	 * @return
	 */
	public List<ModelClass> getStaffForms() {
		List<ModelClass> list = utilsMapper.getStaffForms();
		return list;
	}

	/**
	 * 在留資格を取得
	 * 
	 * @return
	 */
	public List<ModelClass> getVisa() {
		List<ModelClass> list = utilsMapper.getVisa();
		return list;
	}

	/**
	 * 開発言語を取得
	 * 
	 * @param sendMap
	 * 
	 * @return
	 */
	public List<ModelClass> getTechnologyType(Map<String, String> sendMap) {
		List<ModelClass> list = utilsMapper.getTechnologyType(sendMap);
		return list;
	}

	/**
	 * 日本語レベルを取得
	 * 
	 * @return
	 */
	public List<ModelClass> getJapaneseLevel() {
		List<ModelClass> list = utilsMapper.getJapaneseLevel();
		return list;
	}

	/**
	 * お客様を取得
	 * 
	 * @return
	 */
	public List<ModelClass> getCustomer() {
		List<ModelClass> list = utilsMapper.getCustomer();
		return list;
	}

	/**
	 * トップお客様を取得
	 * 
	 * @return
	 */
	public List<ModelClass> getTopCustomer() {
		List<ModelClass> list = utilsMapper.getTopCustomer();
		return list;
	}

	/**
	 * 開発言語を取得
	 * 
	 * @return
	 */
	public List<ModelClass> getDevelopLanguage() {
		List<ModelClass> list = utilsMapper.getDevelopLanguage();
		return list;
	}

	/**
	 * 入社区分を取得
	 * 
	 * @return
	 */
	public List<ModelClass> getIntoCompany() {
		List<ModelClass> list = utilsMapper.getIntoCompany();
		return list;
	}

	/**
	 * 役割 を取得
	 * 
	 * @return
	 */
	public List<ModelClass> getSiteMaster() {
		List<ModelClass> list = utilsMapper.getSiteMaster();
		return list;
	}

	/**
	 * 職種を取得
	 * 
	 * @return
	 */
	public List<ModelClass> getOccupation() {
		List<ModelClass> list = utilsMapper.getOccupation();
		return list;
	}

	/**
	 * 部署を取得
	 * 
	 * @return
	 */
	public List<ModelClass> getDepartment() {
		List<ModelClass> list = utilsMapper.getDepartment();
		return list;
	}

	/**
	 * 権限を取得
	 * 
	 * @return
	 */
	public List<ModelClass> getAuthority() {
		List<ModelClass> list = utilsMapper.getAuthority();
		return list;
	}

	/**
	 * 英語を取得
	 * 
	 * @return
	 */
	public List<ModelClass> getEnglishLevel() {
		List<ModelClass> list = utilsMapper.getEnglishLevel();
		return list;
	}

	/**
	 * 資格を取得
	 * 
	 * @return
	 */
	public List<ModelClass> getQualification() {
		List<ModelClass> list = utilsMapper.getQualification();
		return list;
	}

	/**
	 * 採番
	 * 
	 * @param sendMap
	 * 
	 * @return
	 */
	public String getNO(Map<String, String> sendMap) {
		String no = utilsMapper.getNO(sendMap);
		return no;
	}

	/**
	 * レベル
	 * 
	 * @return
	 */
	public List<ModelClass> getLevel() {
		return utilsMapper.getLevel();
	}

	/**
	 * お客様性質
	 * 
	 * @return
	 */
	public List<ModelClass> getCompanyNature() {
		return utilsMapper.getCompanyNature();
	}

	/**
	 * 職位
	 * 
	 * @return
	 */
	public List<ModelClass> getPosition() {
		return utilsMapper.getPosition();
	}

	/**
	 * 上位お客様連想
	 * 
	 * @return
	 */
	public List<ModelClass> getTopCustomerDrop() {
		return utilsMapper.getTopCustomerDrop();
	}

	/**
	 * 部門名前連想
	 * 
	 * @return
	 */
	public List<ModelClass> getDepartmentMasterDrop() {
		return utilsMapper.getDepartmentMasterDrop();
	}

	/**
	 * 銀行名検索
	 * 
	 * @return
	 */
	public List<ModelClass> getBankInfo() {
		return utilsMapper.getBankInfo();
	}

	/**
	 * 支店情報検索
	 * 
	 * @return
	 */
	public List<ModelClass> getBankBranchInfo(HashMap<String, String> sendMap) {
		return utilsMapper.getBankBranchInfo(sendMap);
	}

	/**
	 * 支払サイト検索
	 * 
	 * @return
	 */
	public List<ModelClass> getPaymentsite() {
		return utilsMapper.getPaymentsite();
	}

	/**
	 * パスワード取得
	 * 
	 * @return
	 */
	public String getPassword(String employeeNo) {
		return utilsMapper.getPassword(employeeNo);
	}

	/**
	 * パスワードリセット
	 * 
	 * @return
	 */
	public boolean resetPassword(HashMap<String, String> sendMap) {
		String oldPassword = getPassword(sendMap.get("employeeNo"));
		if (md5Password(sendMap.get("oldPassword")).equals(oldPassword)) {
			utilsMapper.resetPassword(sendMap);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * MD5暗号化
	 * 
	 * @param password
	 * @return
	 */
	public static String md5Password(String password) {
		try {
			// 得到一个信息摘要器
			MessageDigest digest = MessageDigest.getInstance("md5");
			byte[] result = digest.digest(password.getBytes());
			StringBuffer buffer = new StringBuffer();
			// 把每一个byte 做一个与运算 0xff;
			for (byte b : result) {
				// 与运算
				int number = b & 0xff;// 加盐
				String str = Integer.toHexString(number);
				if (str.length() == 1) {
					buffer.append("0");
				}
				buffer.append(str);
			}
			// 标准的md5加密后的结果
			return buffer.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * 営業担当
	 * 
	 * @return
	 */
	public List<ModelClass> getSalesPerson() {
		return utilsMapper.getSalesPerson();
	}
	
	/**
	 * 場所
	 * 
	 * @return
	 */
	public List<ModelClass> getStation() {
		return utilsMapper.getStation();
	}
}
