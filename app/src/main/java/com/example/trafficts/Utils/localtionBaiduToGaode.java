package com.example.trafficts.Utils;

import java.math.BigDecimal;

public class localtionBaiduToGaode {
    public static double[] baidu2AMap(double lat, double lon) {
        try {
            if (lat != 0 && lon != 0) {
                double var4 = 0.006401062D;
                double var6 = 0.0060424805D;
                double[] var2 = null;

                for (int var3 = 0; var3 < 2; ++var3) {
                    var2 = new double[2];
                    double var16 = lon - var4;
                    double var18 = lat - var6;
                    double[] var29 = new double[2];
                    double var24 = Math.cos(b(var16) + Math.atan2(var18, var16)) * (a(var18) + Math.sqrt(var16 * var16 + var18 * var18)) + 0.0065D;
                    double var26 = Math.sin(b(var16) + Math.atan2(var18, var16)) * (a(var18) + Math.sqrt(var16 * var16 + var18 * var18)) + 0.006D;
                    var29[1] = (c(var24));
                    var29[0] = (c(var26));
                    var2[1] = (c(lon + var16 - var29[1]));
                    var2[0] = (c(lat + var18 - var29[0]));
                    var4 = lon - var2[1];
                    var6 = lat - var2[0];
                }

                return var2;
            }
        } catch (Throwable var28) {
            // ll.a(var28, "OffsetUtil", "B2G");
        }

        return new double[]{lat, lon};
    }

    private static double a = 3.141592653589793D;

    private static double a(double var0) {
        return Math.sin(var0 * 3000.0D * (a / 180.0D)) * 2.0E-5D;
    }

    private static double b(double var0) {
        return Math.cos(var0 * 3000.0D * (a / 180.0D)) * 3.0E-6D;
    }

    private static double c(double var0) {
        return (new BigDecimal(var0)).setScale(8, 4).doubleValue();
    }

}
