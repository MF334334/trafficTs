package com.example.trafficts.Utils;

import android.webkit.WebView;
import android.widget.Toast;

public class WebToastUtil {
    public static void toast(WebView webView, String str) {
        Toast.makeText(webView.getContext(), str + "", Toast.LENGTH_SHORT).show();
    }
}
