package jp.co.lyc.cms.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface S3AccessKeyMapper {
	
	/**
	 * S3 パスワード取得
	 * @param 
	 * @return　password
	 */
	
	public String getS3password();
}
