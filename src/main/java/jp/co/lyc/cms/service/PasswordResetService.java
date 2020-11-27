package jp.co.lyc.cms.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import jp.co.lyc.cms.mapper.PasswordResetMapper;
import jp.co.lyc.cms.model.PasswordResetModel;

@Component
public class PasswordResetService {

	@Autowired
	PasswordResetMapper passwordResetMapper;
	
	/**
	 * パスワードリセットIDの確認
	 * @param passwordResetId
	 * @return
	 */
	public PasswordResetModel selectPasswordResetInfo(String passwordResetId) {
		return passwordResetMapper.selectPasswordResetInfo(passwordResetId);
	};
	
	/**
	 * パスワードリセットID失効する
	 * @param passwordResetId
	 * @return
	 */
	public void delete(String passwordResetId) {
		passwordResetMapper.delete(passwordResetId);
	};
	
	/**
	 * パスワードリセットメールの再発信
	 * @param passwordResetId
	 * @return
	 */
	public void deleteAll(String idForEmployeeNo) {
		passwordResetMapper.deleteAll(idForEmployeeNo);
	};
	
	/**
	 * パスワードリセット
	 * @param passwordResetId
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean update(HashMap<String, String> sendMap) {
		try {
			passwordResetMapper.update(sendMap);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			// TODO: handle exception
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			return false;
		}
	};
}
