package com.code93.puntoplus;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.ColorRes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Tools {

    public static void setSystemBarColor(Activity act, @ColorRes int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = act.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(act.getResources().getColor(color));
        }
    }

    public static void setSystemBarLight(Activity act) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View view = act.findViewById(android.R.id.content);
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
        }
    }

    public static String DateToStr(Date date, String formatString) {
        String str = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat(formatString);// formatString
            str = format.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String getLocalTime() {
        return DateToStr(new Date(), "HHmmss");
    }

    public static String getLocalFormatTime() {
        return DateToStr(new Date(), "HH:mm:ss");
    }

    public static String getLocalDate() {
        return DateToStr(new Date(), "yyyyMMdd");
    }

    public static String getLocalFormatDate() {
        return DateToStr(new Date(), "yyyy/MM/dd");
    }

    public static String getLocalDateTime() {
        return DateToStr(new Date(), "yyyyMMddHHmmss");
    }

    public static String getFormatTime(String time) {
        return time.substring(0,2)+":"+time.substring(2,4)+":"+time.substring(4,6);
    }
    public static String getFormatDate(String date) {
        return date.substring(6,8)+"/"+date.substring(4,6)+"/"+date.substring(0,4);
    }

    public static String checkNull(String strText) {
        if (strText == null) {
            strText = "   ";
        }
        return strText;
    }
}
