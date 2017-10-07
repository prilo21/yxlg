package com.yxlg.base.util;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.yxlg.base.upload.util.UploadByUrl;

/**
 * @author alison.liu
 * @version <br>
 *          <p>
 *          类的描述
 *          </p>
 */
public class QRCodeUtil {
	
	private static final Logger log = LoggerFactory.getLogger(QRCodeUtil.class);
	private static final String CHARSET = "utf-8";
	@SuppressWarnings("unused")
	private static final String FORMAT_NAME = "PNG";
	// 二维码尺寸
	private static final int QRCODE_SIZE = 200;
	// LOGO宽度
	private static final int WIDTH = 40;
	// LOGO高度
	private static final int HEIGHT = 40;
	
	/**
	 * 生成二维码
	 * 
	 * @param qrCodeUniqueId
	 * @return
	 */
	public static String generateQrCode(String qrCodeUniqueId) {
		// 格式化时间为 yyyyMMddHHmmss
		
		String qrCodePath = FileLoadUtil.encodeName();// 七牛上的路径
		String qrCodePathAndName = "";
		try {
			qrCodePathAndName = QRCodeUtil.encode(qrCodeUniqueId, qrCodePath);
			log.info("=======二维码生成。保存路径：" + qrCodePathAndName + "=========");
		} catch (Exception e) {
			log.error("异常", e);
		}
		return qrCodePathAndName;
	}
	
	/**
	 * 生成二维码
	 * 
	 * @param qrCodeUniqueId
	 * @return
	 */
	public static String generateQrCode(String qrCodeUniqueId,
			String qrCodePath) {
			
		String qrCodePathAndName = "";
		try {
			qrCodePathAndName = QRCodeUtil.encode(qrCodeUniqueId, qrCodePath);
			log.info("=======二维码生成。保存路径：" + qrCodePathAndName + "=========");
		} catch (Exception e) {
			log.error("异常", e);
		}
		return qrCodePathAndName;
	}
	
	/**
	 * 生成二维码
	 * 
	 * @param qrCodeUniqueId
	 * @param imgSourcce
	 * @return
	 */
	public static String generateQrCode(String qrCodeUniqueId,
			InputStream imgSource) {
			
		String qrCodePathAndName = "";
		String qrCodePath = FileLoadUtil.encodeName();// 七牛上的路径
		try {
			qrCodePathAndName = QRCodeUtil.encode(qrCodeUniqueId, imgSource,
					qrCodePath, true);
			log.info("=======二维码生成。保存路径：" + qrCodePathAndName + "=========");
		} catch (Exception e) {
			log.error("异常", e);
		}
		return qrCodePathAndName;
	}
	
	/**
	 * 生成二维码
	 * 
	 * @param qrCodeUniqueId
	 * @param imgSourcce
	 * @return
	 */
	public static String generateQrCode(String qrCodeUniqueId, String imgSource,
			String dd) {
			
		return "";
	}
	
	/**
	 * 
	 * @param qrCodeUniqueId
	 *            二维码存放的内容
	 * @param logoPathAndName
	 * @param qrCodePath
	 * @return
	 */
	public static String generateQrCode(String qrCodeUniqueId,
			InputStream imgSource, String qrCodePath) {
			
		String qrCodePathAndName = "";
		try {
			qrCodePathAndName = QRCodeUtil.encode(qrCodeUniqueId, imgSource,
					qrCodePath, true);
			log.info("=======二维码生成。保存路径：" + qrCodePathAndName + "=========");
		} catch (Exception e) {
			log.error("异常", e);
		}
		return qrCodePathAndName;
	}
	
	/**
	 * 返回生成的二维码路径（不带logo）
	 * 
	 * @param content
	 * @param destPath
	 * @param needCompress
	 * @return
	 * @throws Exception
	 */
	public static String encode(String content, String destPath)
			throws Exception {
			
		return QRCodeUtil.encode(content, null, destPath, false);
	}
	
	/**
	 * 返回生成的二维码路径（带logo）
	 * 
	 * @param content
	 *            内容
	 * @param imgSourcce
	 *            logo图片
	 * @param destPath
	 *            生成路径
	 * @param needCompress
	 *            是否缩放
	 * @return
	 * @throws Exception
	 */
	public static String encode(String content, InputStream imgSource,
			String destPath, boolean needCompress) throws Exception {
			
		BufferedImage image = QRCodeUtil.createImage(content, imgSource,
				needCompress);
		return UploadByUrl.uploadImage(image, destPath);
	}
	
	/**
	 * 创建二维码图片
	 * 
	 * @param content
	 * @param imgPath
	 * @param needCompress
	 * @return
	 * @throws Exception
	 */
	public static BufferedImage createImage(String content,
			InputStream imgSource, boolean needCompress) throws Exception {
			
		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
		hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
		hints.put(EncodeHintType.MARGIN, 2);
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
				BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE, hints);
		int width = bitMatrix.getWidth();
		int height = bitMatrix.getHeight();
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y,
						bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
			}
		}
		if (imgSource == null) {
			return image;
		}
		// 插入图片
		QRCodeUtil.insertImage(image, imgSource, needCompress);
		return image;
	}
	
	/**
	 * @author Alison.Liu
	 * @date 2015/6/10
	 *       在二维码中插入logo
	 * @param source
	 *            二维码图片
	 * @param imgSourcce
	 *            logo图片
	 * @param needCompress
	 *            是否压缩
	 * @throws Exception
	 */
	private static void insertImage(BufferedImage source, InputStream imgSource,
			boolean needCompress) throws Exception {
			
		Image src = ImageIO.read(imgSource);
		int width = src.getWidth(null);
		int height = src.getHeight(null);
		if (needCompress) { // 压缩LOGO
			if (width > WIDTH) {
				width = WIDTH;
			}
			if (height > HEIGHT) {
				height = HEIGHT;
			}
			Image image = src.getScaledInstance(width, height,
					Image.SCALE_SMOOTH);
			BufferedImage tag = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics g = tag.getGraphics();
			g.drawImage(image, 0, 0, null); // 绘制缩小后的图
			g.dispose();
			src = image;
		}
		// 插入LOGO
		Graphics2D graph = source.createGraphics();
		int x = (QRCODE_SIZE - width) / 2;
		int y = (QRCODE_SIZE - height) / 2;
		graph.drawImage(src, x, y, width, height, null);
		Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
		graph.setStroke(new BasicStroke(3f));
		graph.draw(shape);
		graph.dispose();
	}
	
	/**
	 * 当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)
	 * 
	 * @author lanyuan
	 *         Email: mmm333zzz520@163.com
	 * @date 2013-12-11 上午10:16:36
	 * @param destPath
	 *            存放目录
	 */
	public static void mkdirs(String destPath) {
		
		File file = new File(destPath);
		// 当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)
		if (!file.exists() && !file.isDirectory()) {
			file.mkdirs();
		}
	}
	
	/**
	 * 解析二维码
	 * 
	 * @param file
	 *            二维码图片
	 * @return
	 * @throws Exception
	 */
	public static String decode(File file) throws Exception {
		
		BufferedImage image;
		image = ImageIO.read(file);
		if (image == null) {
			return null;
		}
		BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(
				image);
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
		Result result;
		Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>();
		hints.put(DecodeHintType.CHARACTER_SET, CHARSET);
		result = new MultiFormatReader().decode(bitmap, hints);
		String resultStr = result.getText();
		return resultStr;
	}
	
	/**
	 * 解析二维码
	 * 
	 * @param path
	 *            二维码图片地址
	 * @return
	 * @throws Exception
	 */
	public static String decode(String path) throws Exception {
		
		return QRCodeUtil.decode(new File(path));
	}
	
	/**
	 * Michael.Sun
	 * 2016.11.15
	 * 创建多个二维码的压缩包
	 * @param contentList
	 * @param prefix
	 * @param zipName
	 * 			zipName仅为文件名，不包含路径及扩展名
	 * @return
	 */
	/*public static File createZipQrcode(List<String> contentList, String prefix, String zipName) {
	
		List<File> fileList = new ArrayList<File>();
		String temporaryPath = System.getProperty("java.io.tmpdir");
		int index = 1;
		for (String content : contentList) {
			try {
				fileList.add(FileUtil.createFileThroughInputStream(
						ImageUtil.getInputStream(createImage(content, null, false)), temporaryPath
								+ File.separator + prefix + index++ + ".png"));
			} catch (Exception e) {
				throw new BusinessException("创建二维码异常", e);
			}
		}
		return ZipUtil.zipMultiFile(fileList, temporaryPath + zipName + ".zip");
	}*/
}
