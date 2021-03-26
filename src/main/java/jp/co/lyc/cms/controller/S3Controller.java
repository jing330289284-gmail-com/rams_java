package jp.co.lyc.cms.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.transfer.MultipleFileUpload;
import com.amazonaws.services.s3.transfer.TransferManager;

import jp.co.lyc.cms.common.BaseController;
import jp.co.lyc.cms.model.S3Model;

@Controller
@RequestMapping(value = "/s3Controller")
public class S3Controller extends BaseController {

	final String AWS_ACCESS_KEY = "AKIAUCIQPBQYR2626LF3"; // 【你的 access_key】
	final String AWS_SECRET_KEY = "wIJriCBMib1Jc6gDCnQOcGQ6c7LBdqijczY3LbIo"; // 【你的 aws_secret_key】
	final String BUCKET_NAME = "ramsstoragedevices"; // 【你的bucket名字】

	@RequestMapping(value = "/createBucket", method = RequestMethod.POST)
	@ResponseBody
	public void createBucket(@RequestBody S3Model model) {
		AmazonS3 s3 = new AmazonS3Client(new BasicAWSCredentials(AWS_ACCESS_KEY, AWS_SECRET_KEY));
		try {
			s3.createBucket(model.getBucketName());
		} catch (AmazonS3Exception e) {
			System.err.print(e.getErrorMessage());
		}
	}

	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	@ResponseBody
	public void uploadFile(@RequestBody S3Model model) {
		AmazonS3 s3 = new AmazonS3Client(new BasicAWSCredentials(AWS_ACCESS_KEY, AWS_SECRET_KEY));

		try {
			s3.putObject(BUCKET_NAME, model.getFileKey(), new File(model.getFilePath()));
		} catch (AmazonS3Exception e) {
			System.err.print(e.getErrorMessage());
		}
	}

	@RequestMapping(value = "/downloadTest", method = RequestMethod.POST)
	@ResponseBody
	public InputStream downloadTest(@RequestBody S3Model model) {
		AmazonS3 s3 = new AmazonS3Client(new BasicAWSCredentials(AWS_ACCESS_KEY, AWS_SECRET_KEY));
		String folderPath = model.getDownLoadPath().substring(0, model.getDownLoadPath().lastIndexOf("//"));
		File folder = new File(folderPath);
		if (!folder.exists() && !folder.isDirectory()) {
			folder.mkdirs();
			System.out.println("创建文件夹");
		} else {
			System.out.println("文件夹已存在");
		}

		return testdownload(s3, BUCKET_NAME, model.getFileKey(), model.getDownLoadPath());
	}

	public static InputStream testdownload(AmazonS3 s3Client, String bucketName, String key, String targetFilePath) {
		S3Object object = s3Client.getObject(new GetObjectRequest(bucketName, key));
		InputStream input = null;

		if (object != null) {
			System.out.println("Content-Type: " + object.getObjectMetadata().getContentType());
			try {
				// 获取文件流
				input = object.getObjectContent();

			} finally {
				if (input != null) {
					try {
						input.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return input;
	}

	@RequestMapping(value = "/downloadFile", method = RequestMethod.POST)
	@ResponseBody
	public void downloadFile(@RequestBody S3Model model) {
		AmazonS3 s3 = new AmazonS3Client(new BasicAWSCredentials(AWS_ACCESS_KEY, AWS_SECRET_KEY));
		String folderPath = model.getDownLoadPath().substring(0, model.getDownLoadPath().lastIndexOf("//"));
		File folder = new File(folderPath);
		if (!folder.exists() && !folder.isDirectory()) {
			folder.mkdirs();
			System.out.println("创建文件夹");
		} else {
			System.out.println("文件夹已存在");
		}

		amazonS3Downloading(s3, BUCKET_NAME, model.getFileKey(), model.getDownLoadPath());
	}

	public static void amazonS3Downloading(AmazonS3 s3Client, String bucketName, String key, String targetFilePath) {
		S3Object object = s3Client.getObject(new GetObjectRequest(bucketName, key));
		if (object != null) {
			System.out.println("Content-Type: " + object.getObjectMetadata().getContentType());
			InputStream input = null;
			FileOutputStream fileOutputStream = null;
			byte[] data = null;
			try {
				// 获取文件流
				input = object.getObjectContent();
				data = new byte[input.available()];
				int len = 0;
				fileOutputStream = new FileOutputStream(targetFilePath);
				while ((len = input.read(data)) != -1) {
					fileOutputStream.write(data, 0, len);
				}
				System.out.println("下载文件成功");
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (fileOutputStream != null) {
					try {
						fileOutputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (input != null) {
					try {
						input.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
