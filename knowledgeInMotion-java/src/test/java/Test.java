import java.io.File;

public class Test {
	@org.junit.jupiter.api.Test
	public void test() {
		File file = new File("D:\\Media\\photo\\中职\\Screenshot_2023-04-12-22-35-02-715_com.tencent.mm.jpg");
		try {
			String url = cn.qht2005.www.util.AliOSSUtil.uploadFile(file);
			System.out.println(url);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
