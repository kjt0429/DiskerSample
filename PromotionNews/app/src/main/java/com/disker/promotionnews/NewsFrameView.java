package com.disker.promotionnews;

import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;

class NewsFrameView extends FrameLayout {

	PromotionCustomViewRoundedCorner roundedCorner;
	Context mContext;

	WebView webView;

	public NewsFrameView(@NonNull Context context) {
		super(context);
		mContext = context;

		ViewGroup viewGroup = (ViewGroup)View.inflate(context, R.layout.news_frame_view, this);

		roundedCorner = viewGroup.findViewById(R.id.rounded_corner);

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
					((PromotionNewsActivity) mContext).removeView();
				}


			}
		});


		setRadiusSizeRatio();
	}

	/**
	 * 비율에 따라 코너 라운드 처리
	 * 기준 사이즈 screen(480 * 320), radius(8.5dp)
	 */
	private void setRadiusSizeRatio() {

		float defaultRadiusDP = 8.5f;
		float defaultHeight = 320f;

		Display display = ((WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		Point p = new Point();
		display.getSize(p);

		float ratio = p.y / convertDpToPx(defaultHeight);

		defaultRadiusDP *= ratio;

		roundedCorner.setCornerRadius(convertDpToPx(defaultRadiusDP));

	}

	private float convertDpToPx(float dp) {
		DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, metrics);
	}




}
