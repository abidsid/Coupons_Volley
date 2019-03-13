package com.demo.meijertest.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ImageSpan;
import android.util.Base64;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.meijertest.models.ListOfCouponsBean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Admin -24 on 29-12-2016.
 */

public class HFAUtils {
    public static String BASE_URL = "https://meijerkraig.azurewebsites.net/api/Products?code=34lgBae%2FxIEnqksQpkn3w9F0JTKCafuiCr0ejLNLvBzlOlOZBa1CMA%3D%3D";

    public static List<ListOfCouponsBean> list = new ArrayList<>();
    public static List<ListOfCouponsBean> availCouponList = new ArrayList<>();

    public static void justify(final TextView textView) {
        try {
            final AtomicBoolean isJustify = new AtomicBoolean(false);
            final String textString = textView.getText().toString();
            final TextPaint textPaint = textView.getPaint();
            final SpannableStringBuilder builder = new SpannableStringBuilder();
            textView.post(new Runnable() {
                @Override
                public void run() {
                    if (!isJustify.get()) {
                        final int lineCount = textView.getLineCount();
                        final int textViewWidth = textView.getWidth();
                        for (int i = 0; i < lineCount; i++) {
                            int lineStart = textView.getLayout().getLineStart(i);
                            int lineEnd = textView.getLayout().getLineEnd(i);
                            String lineString = textString.substring(lineStart, lineEnd);
                            if (i == lineCount - 1) {
                                builder.append(new SpannableString(lineString));
                                break;
                            }
                            String trimSpaceText = lineString.trim();
                            String removeSpaceText = lineString.replaceAll(" ", "");
                            float removeSpaceWidth = textPaint.measureText(removeSpaceText);
                            float spaceCount = trimSpaceText.length() - removeSpaceText.length();
                            float eachSpaceWidth = (textViewWidth - removeSpaceWidth) / spaceCount;
                            SpannableString spannableString = new SpannableString(lineString);
                            for (int j = 0; j < trimSpaceText.length(); j++) {
                                char c = trimSpaceText.charAt(j);
                                if (c == ' ') {
                                    Drawable drawable = new ColorDrawable(0x00ffffff);
                                    drawable.setBounds(0, 0, (int) eachSpaceWidth, 0);
                                    ImageSpan span = new ImageSpan(drawable);
                                    spannableString.setSpan(span, j, j + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                }
                            }
                            builder.append(spannableString);
                        }
                        textView.setText(builder);
                        isJustify.set(true);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isOnline(Activity activity) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        boolean isNetworkConnected = activeNetworkInfo != null && activeNetworkInfo.isConnected();
        return isNetworkConnected;
    }

    public static boolean isOnlineAvailable(Context activity) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        boolean isNetworkConnected = activeNetworkInfo != null && activeNetworkInfo.isConnected();
        return isNetworkConnected;
    }

    public static void showExitSnackBar(View view, final Activity context) {
        Snackbar snackbar = Snackbar
                .make(view, "Do you want to exit from registration?", Snackbar.LENGTH_LONG)
                .setAction("Yes", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       /* Snackbar snackbar1 = Snackbar.make(view, "Message is restored!", Snackbar.LENGTH_SHORT);
                        snackbar1.show();*/
                        context.finish();
                    }
                });
        snackbar.show();
    }

    public static void showKeyboard(Activity activity) {
        if (activity != null) {
            activity.getWindow()
                    .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    public static void hideKeyboard(Activity activity) {
        if (activity != null) {
            activity.getWindow()
                    .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        }
    }

    public static ProgressDialog showProgressDialog(Context context, String message) {
        ProgressDialog m_Dialog = new ProgressDialog(context);
        m_Dialog.setMessage(message);
        m_Dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        m_Dialog.setCancelable(false);
        m_Dialog.show();
        return m_Dialog;
    }


    public static void showToast(Activity activity, String message) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
    }

    public static String maskMobileNo(String mobileNo) {
//        String existingCCNmbr = "4114360123456785";
        int i = 0;
        StringBuffer temp = new StringBuffer();
        while (i < (mobileNo.length())) {
            if (i > mobileNo.length() - 5) {
                temp.append(mobileNo.charAt(i));
            } else {
                temp.append("X");
            }
            i++;
        }
        System.out.println(temp);
        return temp.toString();
    }

    public static float distFrom(double lat1, double lng1, double lat2, double lng2) {
        double earthRadius = 6371000;
        // meters
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        float dist = (float) (earthRadius * c);
        return dist / 1000;
    }

    public static String maskString(String strText, int start, int end, char maskChar)
            throws Exception {
        if (strText == null || strText.equals(""))
            return "";
        if (start < 0)
            start = 0;
        if (end > strText.length())
            end = strText.length();
        if (start > end)
            throw new Exception("End index cannot be greater than start index");
        int maskLength = end - start;
        if (maskLength == 0)
            return strText;
        StringBuilder sbMaskString = new StringBuilder(maskLength);
        for (int i = 0; i < maskLength; i++) {
            sbMaskString.append(maskChar);
        }
        return strText.substring(0, start)
                + sbMaskString.toString()
                + strText.substring(start + maskLength);
    }


    public static Bitmap convertBase64ToBitmap(String imageUrl) {
        byte[] decodedString = Base64.decode(imageUrl, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedByte;
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public static String capitalize(String capString) {
        StringBuffer capBuffer = new StringBuffer();
        Matcher capMatcher = Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(capString);
        while (capMatcher.find()) {
            capMatcher.appendReplacement(capBuffer, capMatcher.group(1).toUpperCase() + capMatcher.group(2).toLowerCase());
        }
        return capMatcher.appendTail(capBuffer).toString();
    }

    public static String[] FileReading(Activity av, int filename) {
        StringBuffer buf = new StringBuffer();
        InputStream is = null;
        String arr[] = null;
        try {
            String str = "";
            is = av.getResources().openRawResource(filename);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(is));
            if (is != null) {
                while ((str = reader.readLine()) != null) {
                    buf.append(str + "\n");
                }
            }
            arr = buf.toString().split("\\n");
        } catch (IOException e) {
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException io) {
            }
        }
        return arr;
    }


    public static String currentMonthYear() {
        String month = "";
        Calendar c = Calendar.getInstance();
        c.get(Calendar.MONTH);
        c.get(Calendar.YEAR);
        SimpleDateFormat sdf = new SimpleDateFormat("MMM-yy", Locale.ENGLISH);
        month = sdf.format(c.getTime());
        return month;
    }

    public static String previousMonthYear() {
        String month = "";
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        c.get(Calendar.YEAR);
        c.get(Calendar.MONTH);
        SimpleDateFormat sdf = new SimpleDateFormat("MMM-yy", Locale.ENGLISH);
        month = sdf.format(c.getTime());
        return month;
    }
   /* public static String convertingDateFormat(String datTime) { //2018-9-24.15.25. 56. 0
        String date = "";
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd.hh.mm.ss a", Locale.ENGLISH);
        try {
            Date d1 = sf.parse(datTime);
            SimpleDateFormat opFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
            date = opFormat.format(d1);
            Logger.logD("Date", "date-->" + date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }*/

    public static void blinkTextView(View view) {
        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(150); //You can manage the time of the blink with this parameter
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        view.startAnimation(anim);
    }

    public static String getCurrentDateTime() {
        String dateTime = "";
        Calendar c = Calendar.getInstance();
//        2019-02-08.01.02.59 PM  ---> 08-02-2019 10:00:00 AM
        SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy hh.mm.ss a", Locale.ENGLISH);
        dateTime = sf.format(c.getTime());
        return dateTime;
    }


    public static boolean doesUserHavePermission(Activity activity, String permission) {
        int result = 0/*= activity.checkCallingOrSelfPermission(permission);*/;
        return result == ContextCompat.checkSelfPermission(activity, permission);
    }

    public static void requestPermission(Activity activity, String permission) {
        ActivityCompat.requestPermissions(activity, new String[]{permission}, 1);

    }

}
