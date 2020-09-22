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
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;

class NewsFrameView extends FrameLayout {

	PromotionCustomViewRoundedCorner roundedCorner;
	Context mContext;

	WebView webView;

	// TODO : 아래 변수 .. 모델로 받기.
	boolean isForced = false;

	public NewsFrameView(@NonNull Context context, Boolean isForced) {
		super(context);
		mContext = context;
		isForced = isForced;

		ViewGroup viewGroup = (ViewGroup)View.inflate(context, R.layout.news_frame_view, this);

		roundedCorner = viewGroup.findViewById(R.id.rounded_corner);


		ConstraintLayout bottomLayout = viewGroup.findViewById(R.id.contentLayout_bottom);

		if(isForced){
			// 바텀 프레임 보임
			bottomLayout.setVisibility(View.VISIBLE);

			Display display = ((WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
			Point p = new Point();
			display.getSize(p);

			// TODO: 한쪽 방향 코너 지정 이슈.. (웹뷰 의 경우 shape로 처리도 안되고, 기타 API는 os level 분기)
			roundedCorner.setPadding(0,0,0,(int)(p.y*0.09722));

		}else{
			// 바텀 프레임 보이지 않음
			bottomLayout.setVisibility(View.INVISIBLE);
		}



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
					((NewsV2Activity) mContext).removeFrameView();
				}


			}
		});


		// 바텀에 글자 크기 지정
		// 높이 계산
		Display display = ((WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		Point p = new Point();
		display.getSize(p);

		AppCompatTextView forceTextView = findViewById(R.id.forceTextView);
		float textScaledPixel = (p.y * 0.031388f) / getResources().getDisplayMetrics().scaledDensity;
		forceTextView.setTextSize(textScaledPixel);


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
