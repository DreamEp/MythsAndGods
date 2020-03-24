package fr.esilv.mythsandgods.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import fr.esilv.mythsandgods.R;

public class WebViewFragment extends Fragment {
    private WebView mWebView;
    private String website;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_webview, container, false);
        website = getArguments().getString("key_website");

        mWebView = (WebView) v.findViewById(R.id.webview);
        mWebView.loadUrl(website);

        //enable javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        //force links and redirects to open in the webview instead of new browser
        mWebView.setWebViewClient(new WebViewClient());

        return v;
    }
}
