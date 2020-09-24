package com.disker.promotionnews;

import android.content.Context;
import android.graphics.Point;
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
		this.isForced = isForced;

		ViewGroup viewGroup = (ViewGroup)View.inflate(context, R.layout.news_frame_view, this);

		roundedCorner = viewGroup.findViewById(R.id.rounded_corner);


		ConstraintLayout bottomLayout = viewGroup.findViewById(R.id.contentLayout_bottom);

		if(this.isForced){
			// 바텀 프레임 보임
			bottomLayout.setVisibility(View.VISIBLE);

			Display display = ((WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
			Point p = new Point();
			display.getSize(p);

			// TODO: 한쪽 방향 코너 지정 이슈.. (웹뷰 의 경우 shape로 처리도 안되고, 기타 API는 os level 분기)
			if(Util.isPortrait()){
				roundedCorner.setPadding(0, 0, 0, (int) (p.y * 0.05281));
			}
			else {
				roundedCorner.setPadding(0, 0, 0, (int) (p.y * 0.09722));
			}

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
		webView.loadUrl("https://namu.wiki/w/LG");


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
		Point p = Util.getScreenSize(mContext);

		AppCompatTextView forceTextView = findViewById(R.id.forceTextView);
		float textScaledPixel;
		if(Util.isPortrait()){
			textScaledPixel = (p.x * 0.031388f) / getResources().getDisplayMetrics().scaledDensity;
		}else {
			textScaledPixel = (p.y * 0.031388f) / getResources().getDisplayMetrics().scaledDensity;
		}
		forceTextView.setTextSize(textScaledPixel);


		if(Util.isPortrait()){
			roundedCorner.setScaledCornerSize(PromotionCustomViewRoundedCorner.SCALED_DIRECTION_Y, 439, 8.5f);
		}else{
			roundedCorner.setScaledCornerSize(PromotionCustomViewRoundedCorner.SCALED_DIRECTION_X, 456, 8.5f);
		}


	}

}
