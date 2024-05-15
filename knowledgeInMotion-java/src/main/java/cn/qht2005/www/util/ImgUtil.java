package cn.qht2005.www.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URI;
import java.net.URL;
/**
 * 图片工具类
 * @author 覃
 */
public class ImgUtil {
	/**
	 * 通过url获取图片
	 * @param url 图片url
	 * @return 图片
	 */

	public static BufferedImage getImgByUrl(String url) throws Exception {
		URL imgUrl = new URI(url).toURL(); // 图片url
		return ImageIO.read(imgUrl);
	}
}
