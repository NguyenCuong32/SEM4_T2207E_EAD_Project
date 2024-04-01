package com.uni.ead_project.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.Objects;

public class GenerateQRCode {
    public static String prettyObject(Object object){
        try {
            ObjectMapper mapper = new ObjectMapper();

            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    public static String generateQRCode(String data, int width,  int height){
        StringBuilder result = new StringBuilder();
        if (!data.isEmpty()){
            ByteArrayOutputStream os = new ByteArrayOutputStream();


            try {
                QRCodeWriter writer = new QRCodeWriter();
                BitMatrix bitMatrix = writer.encode(data, BarcodeFormat.QR_CODE, width, height);

                BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
                ImageIO.write(bufferedImage, "png", os);

                result.append("data:image/png;base64,");
                result.append(new String(Base64.getEncoder().encode(os.toByteArray())));
            }catch (Exception e){
                e.printStackTrace();
            }

        }

        return result.toString();
    }
}
