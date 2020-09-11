package jp.co.lyc.cms.mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import jp.co.lyc.cms.model.PasswordResetModel;

@Mapper
public interface PasswordResetMapper {

	/**
	 * パスワードリセットIDの確認
	 * @param passwordResetId
	 * @return
	 */
	public PasswordResetModel selectPasswordResetInfo(String passwordResetId);
	
	/**
	 * パスワードリセットID失効する
	 * @param passwordResetId
	 * @return
	 */
	public void delete(String passwordResetId);
	
	/**
	 * パスワードリセットメールの再発信
	 * @param passwordResetId
	 * @return
	 */
	public void deleteAll(String IdForEmployeeNo);
	
	/**
	 * パスワードリセット
	 * @param passwordResetId
	 * @return
	 */
	public void update(HashMap<String, String> sendMap);
}
