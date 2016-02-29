package com.pipemonk.util;

/**
 * Created by Krishna on 2/28/16.
 */
public class PayslipUtils {

    /**
     * Validate a string.
     *
     * @param val
     * @return
     */
    public static boolean isNullOrEmpty(String val) {
        boolean flag = false;
        if (val == null || "".equals(val)) {
            flag = true;
        }
        return flag;
    }
}
