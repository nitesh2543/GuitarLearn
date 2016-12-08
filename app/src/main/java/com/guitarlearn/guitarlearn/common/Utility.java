package com.guitarlearn.guitarlearn.common;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.guitarlearn.guitarlearn.R;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

public class Utility {

    private static final String MIME_TYPE = "text/html";
    private static final String PLANE_TEXT = "plain/text";

    /**
     * Display a simple alert dialog with the given text and title.
     *
     * @param context Android context in which the dialog should be displayed
     * @param message Alert dialog message
     */
    public static void showAlert(Context context, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getString(R.string.app_name));
        builder.setMessage(message);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    public static String getDeviceId(Context ctx) {
        return Settings.Secure.getString(ctx.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }


    public static String getURL(Map<String, String> params, String url) {
        StringBuilder builder = new StringBuilder(url);
        if (!url.contains("?"))
            builder.append("?");
        try {
            Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> param = iterator.next();
                builder.append(URLEncoder.encode(param.getKey(), "UTF-8")).append('=').append(URLEncoder.encode(param.getValue(), "UTF-8"));
                if (iterator.hasNext()) {
                    builder.append('&');
                }
            }
            return builder.toString();
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("Encoding not supported: " + "UTF-8", uee);
        }
    }

   /* public static void showIntentShare(Context ctx, Object item, String type) {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType(PLANE_TEXT);
        switch (type) {
            case Constants.BundleKeys.PHOTO:
                Photo photo = (Photo) item;
                sharingIntent.putExtra(Intent.EXTRA_TEXT, photo.title + "\n\n" + photo.detailURL + "\n\n----\nShared via AZAM NewsApp\n----");
                break;
            case Constants.BundleKeys.VIDEO:
                Video video = (Video) item;
                sharingIntent.putExtra(Intent.EXTRA_TEXT, video.name + "\n\n" + video.url + "\n\n----\nShared via AZAM NewsApp\n----");
                break;
            case Constants.BundleKeys.NEWS:
                NewsItem newsItem = (NewsItem) item;
                sharingIntent.putExtra(Intent.EXTRA_TEXT, newsItem.title + "\n\n" + newsItem.detailURL + "\n\n----\nShared via AZAM NewsApp\n----");
                break;
        }
        ctx.startActivity(Intent.createChooser(sharingIntent, "Share using"));
    }*/

    public static void showToast(Context context, String message) {
        Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_LONG).show();

    }

    public static void setPortraitOrientation(Context ctx) {
        ((Activity) ctx).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);


    }

    public static void setLandscapeOrientation(Context ctx) {
        ((Activity) ctx).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
    }

    public static String getText(EditText editText) {
        return editText.getText().toString().trim();
    }


    public static void requestFocus(Context ctx, View view) {
        if (view.requestFocus()) {
            ((Activity) ctx).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    public static void hideKeyboard(Context ctx) {
        View view = ((Activity) ctx).getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) ((Activity) ctx).getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public static boolean isValidEmail(String email) {
        if (TextUtils.isEmpty(email))
            return false;
        else
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static void startActivity(Activity activity, Intent intent) {
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

    /*public static boolean validateEmail(Context ctx, EditText editText) {
        String email = editText.getText().toString().trim();
        if (!isValidEmail(email)) {
            editText.setError(ctx.getString(R.string.err_msg_email));
            requestFocus(ctx, editText);
            return false;
        }
        return true;
    }*/

    public static boolean isEmpty(Context ctx, EditText editText, String error) {
        if (TextUtils.isEmpty(editText.getText().toString().trim())) {
            editText.setError(error);
            Utility.requestFocus(ctx, editText);
            return false;
        }
        return true;
    }

    public static boolean isValidMobileOrSmartCardNumber(Context ctx, EditText editText, String error) {
        if (editText.getText().toString().trim().length() < 12) {
            editText.setError(error);
            Utility.requestFocus(ctx, editText);
            return false;
        }
        return true;
    }

   /* public static boolean isLoggedIn(Activity activity) {
        return !TextUtils.isEmpty(PreferenceManager.getsInstance(activity).getAccessToken());
    }*/


    /*public static boolean validatePassword(Context ctx, EditText inputPassword) {
        if (inputPassword.getText().toString().trim().length() < 8) {
            inputPassword.setError(ctx.getString(R.string.err_msg_password_length));
            requestFocus(ctx, inputPassword);
            return false;
        }
        return true;
    }*/

    public static boolean isValidMobileNo(Context ctx, EditText editText, String error) {
        if (editText.getText().toString().trim().length() != 12) {
            editText.setError(error);
            Utility.requestFocus(ctx, editText);
            return false;
        }
        return true;
    }

    public static boolean isValidSmartCardNo(Context ctx, EditText editText, String error) {
        if (editText.getText().toString().trim().length() != 12) {
            editText.setError(error);
            Utility.requestFocus(ctx, editText);
            return false;
        }
        return true;
    }


    /**
     * Opens Calendar Activity
     *
     * @param context
     * @param title
     * @param startTime in milliseconds
     * @param endTime   in milliseconds
     */

    public static void openCalendar(Context context, String title, long startTime, long endTime) {
        Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra("beginTime", startTime);
        intent.putExtra("allDay", false);
        intent.putExtra("endTime", endTime);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }
}
