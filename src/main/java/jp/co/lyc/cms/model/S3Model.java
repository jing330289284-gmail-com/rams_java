package jp.co.lyc.cms.model;

import org.springframework.web.multipart.MultipartFile;

public class S3Model {

	private static final long serialVersionUID = 1L;

	private String bucketName;
	private String fileKey;
	private String filePath;
	private String downLoadPath;
	private MultipartFile fileTest;
	
	public MultipartFile getFileTest() {
		return fileTest;
	}

	public void setFileTest(MultipartFile fileTest) {
		this.fileTest = fileTest;
	}

	public String getDownLoadPath() {
		return downLoadPath;
	}

	public void setDownLoadPath(String downLoadPath) {
		this.downLoadPath = downLoadPath;
	}

	public String getFileKey() {
		return fileKey;
	}

	public void setFileKey(String fileKey) {
		this.fileKey = fileKey;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

}
