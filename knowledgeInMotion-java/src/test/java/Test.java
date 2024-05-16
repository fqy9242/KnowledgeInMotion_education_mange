import cn.qht2005.www.pojo.AliOssConfig;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.util.Map;

public class Test {
	@org.junit.jupiter.api.Test
	public void test() {
		File file = new File("C:\\Users\\Administrator\\Desktop\\a.txt");
		try {
			String url = cn.qht2005.www.util.AliOSSUtil.uploadFile(file);
			System.out.println(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@org.junit.jupiter.api.Test
	public void test2() {
		Yaml yaml = new Yaml();
		AliOssConfig aliOssConfig = yaml.loadAs(getClass().getResourceAsStream("aliyunOss.yml"), AliOssConfig.class);
		System.out.println(aliOssConfig.getAccessKeyId());

	}
}
