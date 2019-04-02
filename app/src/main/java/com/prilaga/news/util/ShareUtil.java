//package com.prilaga.news.util;
//
//import android.content.Intent;
//import android.net.Uri;
//import android.support.annotation.NonNull;
//import android.support.annotation.StringRes;
//import android.widget.Toast;
//import com.sonejka.news.App;
//
///**
// * Created by Oleg Tarashkevich on 05/04/2017.
// */
//
//public final class ShareUtil {
//
//    public static void goToWeb(@NonNull String webSite) {
//        if (TextUtil.isNotEmpty(webSite)) {
//            try {
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.setData(Uri.parse(webSite));
//                App.getContext().startActivity(intent);
//            } catch (Throwable throwable) {
//                Logger.e(throwable);
//            }
//        }
//    }
//
//    public static void shareText(String mailText, @StringRes int appName) {
//
//        Intent emailIntent = new Intent(Intent.ACTION_SEND);
//        emailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//        emailIntent.setType("text/plain");
//        emailIntent.putExtra(Intent.EXTRA_SUBJECT, App.getContext().getString(appName));
//        emailIntent.putExtra(Intent.EXTRA_TEXT, mailText);
//
//        try {
//            Intent new_intent = Intent.createChooser(emailIntent, "Share with");
//            new_intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            App.getContext().startActivity(new_intent);
//        } catch (android.content.ActivityNotFoundException ex) {
//            Toast.makeText(App.getContext(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
//        }
//
//    }
//}
