package com.example.raseedytask.helpers.alerts;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.StringRes;
import com.google.android.material.snackbar.Snackbar;


/**
 * Provides method for showing UI messages, Snackbar, toast, etc..
 */
public class UiMessagesUtils {

    /**
     * /**
     * helper method that show a toast for short duration
     *
     * @param context
     * @param msg     toast message
     */
    public static void showToast(Context context, String msg) {
        if (context == null || msg == null) {
            return;
        }
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

    }

    public void showSnackBar(View v, String snackbarText) {
        if (v == null || snackbarText == null) {
            return;
        }

        Snackbar snackbar = Snackbar.make(v, snackbarText, Snackbar.LENGTH_LONG);
        snackbar.getView().setBackgroundColor(Color.RED);
        TextView tv = snackbar.getView().findViewById(com.google.android.material.R.id.snackbar_text);
        tv.setTextColor(Color.WHITE);
        snackbar.show();
    }

    public void showSnackBar(View v, @StringRes int stringRes) {
        if (v == null) {
            return;
        }

        Snackbar snackbar = Snackbar.make(v, stringRes, Snackbar.LENGTH_LONG);
        snackbar.getView().setBackgroundColor(Color.RED);
        TextView tv = snackbar.getView().findViewById(com.google.android.material.R.id.snackbar_text);
        tv.setTextColor(Color.WHITE);
        snackbar.show();
    }

    /**
     * helper method that show a toast for short duration
     *
     * @param context
     * @param stringRes toast message string resource
     */
    public static void showToast(Context context, @StringRes int stringRes) {
        if (context == null) {
            return;
        }
        Toast.makeText(context, stringRes, Toast.LENGTH_SHORT).show();
    }

    public static void showSuccessOnSnackbar(View v, String snackbarText) {
        if (v == null || snackbarText == null) {
            return;
        }

        Snackbar snackbar = Snackbar.make(v, snackbarText, Snackbar.LENGTH_LONG);
        snackbar.getView().setBackgroundColor(Color.GREEN);
        TextView tv = snackbar.getView().findViewById(com.google.android.material.R.id.snackbar_text);
        tv.setTextColor(Color.WHITE);
        snackbar.show();
    }

}
