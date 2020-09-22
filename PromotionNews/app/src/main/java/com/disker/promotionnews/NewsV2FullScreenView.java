package com.disker.promotionnews;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;

public class NewsV2FullScreenView extends FrameLayout {

	Context mContext;

	WebView webView;


    public NewsV2FullScreenView(@NonNull Context context) {
        super(context);
        mContext = context;

	    ViewGroup viewGroup = (ViewGroup) View.inflate(context, R.layout.news_v2_fullscreen_view, this);

	    webView = viewGroup.findViewById(R.id.webView);

	    webView.setWebViewClient(new WebViewClient());
// Enable JavaScript
	    webView.getSettings().setJavaScriptEnabled(true);
// Enable Zoom
	    //webView.getSettings().setBuiltInZoomControls(true);
	    //webView.getSettings().setSupportZoom(true);
// Adjust web display
	    webView.getSettings().setLoadWithOverviewMode(true);
	    webView.getSettings().setUseWideViewPort(true);
	    webView.loadUrl("https://developers.withhive.com/");

	    AppCompatImageView backBtn = viewGroup.findViewById(R.id.back_imageView);
	    backBtn.setOnClickListener(new OnClickListener() {
		    @Override
		    public void onClick(View v) {

			    if (webView.canGoBack()) {
				    webView.goBack();
			    } else {
				    ((NewsV2Activity) mContext).removeFullscreenView();
			    }


		    }
	    });

	    AppCompatImageView closeBtn = viewGroup.findViewById(R.id.close_imageView);
	    closeBtn.setOnClickListener(new OnClickListener() {
		    @Override
		    public void onClick(View v) {
			    ((NewsV2Activity)mContext).finish();
		    }
	    });

    }
}
