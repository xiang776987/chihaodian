package com.yq.util;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 正方形二维码生成工具类
 * littlehow 2018/5/27
 */
public class QrCodeImageUtils {
    public static final int JPG = BufferedImage.TYPE_INT_RGB;
    public static final int GIF = BufferedImage.TYPE_INT_ARGB;
    /** 扩展信息 */
    private static final Map<EncodeHintType, Object> DEFAULT_HINT = new HashMap<EncodeHintType, Object>();
    /**正方形二维码的默认宽度*/
    private static final int DEFAULT_WIDTH = 300;
    /** 背景色 */
    private static final Color backgroundColor = Color.WHITE;
    /** 二维码颜色 */
    private static final Color contentColor = Color.BLACK;
    /** 图片类型映射 */
    private static final Map<Integer, String> IMAGE_TYPE_MAPPING = new HashMap<>();

    static {
        //不设置默认也为L,可以修改
        DEFAULT_HINT.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        //jpg
        IMAGE_TYPE_MAPPING.put(JPG, "jpeg");
        //gif
        IMAGE_TYPE_MAPPING.put(GIF, "gif");
    }

    /**
     * 创建二维码
     * @param info
     * @return
     */
    public static byte[] createQrCode(String info, InputStream logo, int imageType) {
        if (imageType != JPG && imageType != GIF) {
            throw new RuntimeException("图片类型必须是:QrCodeImageUtils(JPG/PNG)");
        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        //进行绘图
        drawImage(info, outputStream, DEFAULT_WIDTH, logo, imageType);
        //返回二进制数据
        return outputStream.toByteArray();
    }

    /**
     * 创建二维码文件
     * @param info
     * @param logo
     * @param imageType
     * @param fileName
     */
    public static void createQrCodeAsFile(String info, InputStream logo, int imageType, String fileName) {
        if (imageType != JPG && imageType != GIF) {
            throw new RuntimeException("图片类型必须是:QrCodeImageUtils(JPG/GIF)");
        }
        OutputStream outputStream;
        try {
            outputStream = new FileOutputStream(fileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("指定文件不存在:" + fileName);
        }
        //进行绘图
        drawImage(info, outputStream, DEFAULT_WIDTH, logo, imageType);
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 绘图
     * @param info  二维码信息
     * @param outputStream 输出流
     * @param width 宽度和高度(因为是正方形)
     * @param logo  logo图标流
     * @param imageType 图片类型 (jpeg:rgb),(gif:argb)
     */
    public static void drawImage(String info, OutputStream outputStream, int width,
                                 InputStream logo, int imageType) {
        BitMatrix bitMatrix = getBitMatrix(info, width);
        BufferedImage image = new BufferedImage(width, width, imageType);
        Graphics g = image.getGraphics();
        /** 设置背景色 */
        g.setColor(backgroundColor);
        /** 填充 */
        g.fillRect(0, 0, width, width);
        /** 设置内容色 */
        g.setColor(contentColor);
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < width; j++) {
                if(bitMatrix.get(i, j)) {
                    /** 描点 */
                    g.drawRect(i, j, 1, 1);
                }
            }
        }
        /** 绘制logo */
        drawLogo(g, logo, width);
        /** 释放绘图资源 */
        g.dispose();
        try {
            /** 输出图片 */
            ImageIO.write(image, IMAGE_TYPE_MAPPING.get(imageType), outputStream);
        } catch (IOException e) {
            throw new RuntimeException("绘制二维码信息失败", e);
        }
    }



    /**
     * 回执logo
     * @param g
     * @param logoStream
     * @param width
     */
    private static void drawLogo(Graphics g, InputStream logoStream, int width) {
        if (logoStream == null) return;
        try {
            /** logo */
            BufferedImage logo = ImageIO.read(logoStream);
            int logoX = (width - logo.getWidth(null)) / 2;
            int logoY = (width - logo.getHeight(null)) / 2;
            g.drawImage(logo, logoX, logoY, null);
        } catch (Throwable t) {
            throw new RuntimeException("回执logo失败", t);
        }
    }

    /**
     * 获取二维码需要的描点
     * @param info
     * @param width
     * @return
     */
    private static BitMatrix getBitMatrix(String info, int width) {
        QRCodeWriter writer = new QRCodeWriter();
        try {
            return writer.encode(info, BarcodeFormat.QR_CODE, width, width, DEFAULT_HINT);
        } catch (WriterException e) {
            throw new RuntimeException("生成二维码信息异常:" + info, e);
        }
    }

    public static String getImageType(int imageType) {
        return IMAGE_TYPE_MAPPING.get(imageType);
    }

}
