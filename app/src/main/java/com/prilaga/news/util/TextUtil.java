package com.prilaga.news.util;
import android.text.TextUtils;
import androidx.annotation.ArrayRes;
import androidx.annotation.PluralsRes;
import androidx.annotation.StringRes;
import com.prilaga.data.utils.DataUtil;

/**
 * Created by Oleg Tarashkevich on 01.04.17.
 */

public final class TextUtil {

    /**
     * Get string resources
     */
    public static String string(@StringRes int resid) {
        return DataUtil.getInstance().getContext().getResources().getString(resid);
    }

    public static String string(@StringRes int resid, Object... formatArgs) {
        return DataUtil.getInstance().getContext().getResources().getString(resid, formatArgs);
    }

    public static String[] stringArray(@ArrayRes int resid) {
        return DataUtil.getInstance().getContext().getResources().getStringArray(resid);
    }

    public static String quantityString(@PluralsRes int id, int quantity, Object... formatArgs) {
        return DataUtil.getInstance().getContext().getResources().getQuantityString(id, quantity, formatArgs);
    }

    public static boolean isEmpty(CharSequence str){
        return TextUtils.isEmpty(str);
    }

    public static boolean isNotEmpty(CharSequence str){
        return !TextUtils.isEmpty(str);
    }

    // avoid for Picasso: java.lang.IllegalArgumentException: Path must not be empty.
    public static String getEmptyUrl(String url){
       return TextUtil.isEmpty(url) ? null : url;
    }
}
