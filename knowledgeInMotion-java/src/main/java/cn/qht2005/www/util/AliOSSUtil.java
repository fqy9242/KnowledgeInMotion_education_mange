package cn.qht2005.www.util;

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
	private static Map<String, Object> pro;
	public static String ENDPOINT;
	public static final String ACCESS_KEY_ID;
	public static final String ACCESS_KEY_SECRET;

	static {
		ENDPOINT = pro.get("endpoint").toString();
		Yaml yaml = new Yaml();
		// 读入文件
		InputStream resourceAsStream = AliOSSUtil.class.getClassLoader().getResourceAsStream("aliyunOss.yml");
		pro = yaml.loadAs(resourceAsStream, AliOSSUtil.class);
		ENDPOINT = pro.get("endpoint").toString();
		ACCESS_KEY_ID = pro.get("accessKeyId").toString();
		ACCESS_KEY_SECRET = pro.get("accessKeySecret").toString();
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
		OSS ossClient = new OSSClientBuilder().build(endpoint, credentialsProvider);
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
