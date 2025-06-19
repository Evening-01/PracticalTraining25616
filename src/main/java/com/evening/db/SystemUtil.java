package com.evening.db;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


public final class SystemUtil {
    private SystemUtil() {}

    public static byte fetch() {
        try (InputStream is = SystemUtil.class.getResourceAsStream("/hidden/sys.dat")) {
            if (is != null) {
                String text = new String(is.readAllBytes(), StandardCharsets.UTF_8).trim();
                return Base64.getDecoder().decode(text)[0];
            }
        } catch (Exception ignored) {
        }
        return 0x5A;
    }
}