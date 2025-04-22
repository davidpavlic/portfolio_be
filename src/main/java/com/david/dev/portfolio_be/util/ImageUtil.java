package com.david.dev.portfolio_be.util;

import java.util.Base64;

/**
 * Utility class for encoding and decoding images in Base64 format.
 * Base64 is a binary-to-text encoding scheme that represents binary data in an ASCII string format.
 * Base64 is useful to prevent binary data from being corrupted or misinterpreted when transmitted over media that are designed to deal with textual data.
 */
public class ImageUtil {

    private ImageUtil() {}

    /**
     * Decodes a Base64-encoded image string into a byte array.
     *
     * @param base64Image the Base64-encoded image string to decode
     * @return a byte array representing the decoded image, or null if the input is null or blank
     */
    public static byte[] decodeBase64Image(String base64Image) {
        return base64Image != null && !base64Image.isBlank()
                ? Base64.getDecoder().decode(base64Image)
                : null;
    }

    /**
     * Encodes a byte array into a Base64-encoded string.
     *
     * @param imageBytes the byte array representing the image to encode
     * @return a Base64-encoded string, or null if the input is null or empty
     */
    public static String encodeBase64Image(byte[] imageBytes) {
        return imageBytes != null && imageBytes.length != 0
                ? Base64.getEncoder().encodeToString(imageBytes)
                : null;
    }
}
