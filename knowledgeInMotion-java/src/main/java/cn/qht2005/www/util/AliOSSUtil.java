package cn.qht2005.www.util;

import cn.qht2005.www.pojo.AliOssConfig;
import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.aliyuncs.exceptions.ClientException;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * @author 覃
 */
public class AliOSSUtil{
	private Map<String, Object> pro;
	public static String ENDPOINT;
	public static String ACCESS_KEY_ID;
	public static String ACCESS_KEY_SECRET;


	static {
		Yaml yaml = new Yaml();
		AliOssConfig aliOssConfig = yaml.loadAs(AliOSSUtil.class.getClassLoader().getResourceAsStream("aliyunOss.yml"), AliOssConfig.class);
		ENDPOINT = aliOssConfig.getEndpoint();
		ACCESS_KEY_ID = aliOssConfig.getAccessKeyId();
		ACCESS_KEY_SECRET = aliOssConfig.getAccessKeySecret();
	}

	/**
	 * 上传文件
	 * @param file 文件
	 * @return 文件url
	 */
	public static String uploadFile(File file) throws Exception{
		// 配置密钥
		DefaultCredentialProvider credentialsProvider  = new DefaultCredentialProvider(ACCESS_KEY_ID, ACCESS_KEY_SECRET);
		String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";
		// 填写Bucket名称，例如examplebucket。
		String bucketName = "qht-file";
		// 新的文件名
		String newFileName = UUID.randomUUID() + file.getName().substring(file.getName().lastIndexOf("."));
		// 创建OSSClient实例。
		OSS ossClient = new OSSClientBuilder().build(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
		InputStream inputStream = new FileInputStream(file);
		// 创建PutObjectRequest对象。
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, newFileName, inputStream);
		// 创建PutObject请求。
		PutObjectResult result = ossClient.putObject(putObjectRequest);
		// 设置URL过期时间
		Date expiration = new Date(new Date().getTime() + 3600 * 1000L * 24 * 365); // 1年
		// 生成签名URL。
		GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, newFileName, HttpMethod.GET);
		// 设置过期时间。
		request.setExpiration(expiration);
		// 生成签名URL（HTTP GET请求）。
		URL signedUrl = ossClient.generatePresignedUrl(request);
		// 关闭OSSClient。
		ossClient.shutdown();
		// 返回url
		return signedUrl.toString();
	}
}
