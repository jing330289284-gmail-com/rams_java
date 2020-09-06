package jp.co.lyc.cms.mapper;

import java.util.HashMap;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import jp.co.lyc.cms.model.EmployeeModel;

@Mapper
public interface Login2Mapper {
	
	/**
	 * ログイン
	 * @param sendMap
	 * @return
	 */
	
	public EmployeeModel getEmployeeModel(Map<String, String> sendMap);
	
	/**
	 * ログイン社員番号のメールを取得
	 * @param employeeNo
	 * @return
	 */
	public String getEmployeeCompanyMail(String employeeNo);
	
	/**
	 * パスワードリセットURLをインサート
	 * @param employeeNo
	 * @return
	 */
	public void insert(HashMap<String, String> sendMap);
}
