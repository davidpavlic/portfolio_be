package com.david.dev.portfolio_be.util;

import java.util.Base64;

public class ImageUtil {

    public static byte[] decodeBase64Image(String base64Image) {
        return base64Image != null && !base64Image.isEmpty() ? Base64.getDecoder().decode(base64Image) : null;
    }

    public static String encodeBase64Image(byte[] imageBytes) {
        return imageBytes != null ? Base64.getEncoder().encodeToString(imageBytes) : null;
    }
}
