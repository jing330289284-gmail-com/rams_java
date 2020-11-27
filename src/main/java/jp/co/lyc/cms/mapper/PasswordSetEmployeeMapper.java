package jp.co.lyc.cms.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PasswordSetEmployeeMapper {
	
	/**
	 * 既存パスワード検索
	 * @param password
	 * @return
	 */
	public ArrayList<String> selectPassword(String password);
	
	/**
	 * パスワードリセット
	 */
	public void update(HashMap<String, String> sendMap);
}
