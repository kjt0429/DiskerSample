package com.disker.promotionnews;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NewsWebFragment extends Fragment {

	private String tmpName;

	public NewsWebFragment() {
		super();
	}

	public NewsWebFragment(String tmpName){
		this.tmpName = tmpName;
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		ViewGroup viewGroup = (ViewGroup)inflater.inflate(R.layout.news_web_fragment, container, false);

		WebView webView = viewGroup.findViewById(R.id.webView);
		/*webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
		webView.getSettings().setUseWideViewPort(true);*/

		// Initial webview
		webView.setWebViewClient(new WebViewClient());
// Enable JavaScript
		webView.getSettings().setJavaScriptEnabled(true);
// Enable Zoom
		//webView.getSettings().setBuiltInZoomControls(true);
		//webView.getSettings().setSupportZoom(true);
// Adjust web display
		webView.getSettings().setLoadWithOverviewMode(true);
		webView.getSettings().setUseWideViewPort(true);

		webView.loadUrl(tmpName);

		return viewGroup;
	}
}
