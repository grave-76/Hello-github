import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import com.bw.excel.common.utils.StreamUtil;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

/**
 * 二维码工具类
 * @author yuhuihuang
 *
 * 2021-6-25 13:34:06
 */
public class QRCodeUtil
{

    private static final Logger logger = LoggerFactory.getLogger(QRCodeUtil.class);

    // 二维码颜色  
    private static final int BLACK = 0xFF000000;
    // 二维码颜色  
    private static final int WHITE = 0xFFFFFFFF;
    // 二维码宽 
    private static final int qrWidth = 175;
    // 二维码高
    private static final int qrHeight = 175;
    // 二维码生成格式
    private static final String imageType = "png";
    // LOGO宽度
    private static final int logoWidth = 30;
    // LOGO高度
    private static final int logoHeight = 30;
    
    private static final String imagePath = "/erc.png";
    
    /** 
     * 生成二维码BufferedImage
     * @param text    二维码内容
     */
    public static BufferedImage codeBufferCreate(String text)
    {
        Map<EncodeHintType, Object> his = new HashMap<EncodeHintType, Object>();
        his.put(EncodeHintType.CHARACTER_SET, "utf-8");
        his.put(EncodeHintType.MARGIN, 1);
        BufferedImage image = null;
        try
        {
            // 1、生成二维码  
            BitMatrix encode = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, qrWidth, qrHeight, his);

            // 2、获取二维码宽高  
            int codeWidth = encode.getWidth();
            int codeHeight = encode.getHeight();

            // 3、将二维码放入缓冲流  
            image = new BufferedImage(codeWidth, codeHeight, BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < codeWidth; i++)
            {
                for (int j = 0; j < codeHeight; j++)
                {
                    // 4、循环将二维码内容定入图片  
                    image.setRGB(i, j, encode.get(i, j) ? BLACK : WHITE);
                }
            }
            
        } catch (Exception e)
        {
            logger.error("生成二维码图片失败", e);
        } 
        return image;
    }
    
    
    /** 
     * 生成二维码
     * @param text    二维码内容
     */
    public static String codeCreate(String text)
    {
        Map<EncodeHintType, Object> his = new HashMap<EncodeHintType, Object>();
        his.put(EncodeHintType.CHARACTER_SET, "utf-8");
        his.put(EncodeHintType.MARGIN, 1);
        String base64 = null;
        ByteArrayOutputStream stream = null;
        try
        {
            // 1、生成二维码  
            BitMatrix encode = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, qrWidth, qrHeight, his);

            // 2、获取二维码宽高  
            int codeWidth = encode.getWidth();
            int codeHeight = encode.getHeight();

            // 3、将二维码放入缓冲流  
            BufferedImage image = new BufferedImage(codeWidth, codeHeight, BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < codeWidth; i++)
            {
                for (int j = 0; j < codeHeight; j++)
                {
                    // 4、循环将二维码内容定入图片  
                    image.setRGB(i, j, encode.get(i, j) ? BLACK : WHITE);
                }
            }

            //5、插入图片
            QRCodeUtil.insertImage(image);
            //6、将二维码写入图片  
            stream = new ByteArrayOutputStream();
            ImageIO.write(image, imageType, stream);
            base64 = Base64.getEncoder().encodeToString(stream.toByteArray());
            stream.flush();
        } catch (Exception e)
        {
            logger.error("生成二维码图片失败", e);
        } finally
        {
            StreamUtil.closeStream(stream);
        }
        return "data:image/png;base64," + base64;
    }

    private static void insertImage(BufferedImage source) throws Exception
    {
        ClassPathResource classPathResource = new ClassPathResource(imagePath);
        InputStream inputStreamImg = classPathResource.getInputStream();
        Image src = ImageIO.read(inputStreamImg);
        int width = src.getWidth(null);
        int height = src.getHeight(null);
        // 压缩LOGO
        if (width > logoWidth)
        {
            width = logoWidth;
        }
        if (height > logoHeight)
        {
            height = logoHeight;
        }
        Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = tag.getGraphics();
        g.drawImage(image, 0, 0, null); // 绘制缩小后的图
        g.dispose();
        src = image;
        // 插入LOGO
        Graphics2D graph = source.createGraphics();
        int x = (qrWidth - width) / 2;
        int y = (qrHeight - height) / 2;
        graph.drawImage(src, x, y, width, height, null);
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
        graph.setStroke(new BasicStroke(3f));
        graph.draw(shape);
        graph.dispose();
    }
}