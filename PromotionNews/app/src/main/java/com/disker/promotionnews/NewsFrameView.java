package com.disker.promotionnews;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

class NewsFrameView extends FrameLayout {

	public NewsFrameView(@NonNull Context context) {
		super(context);

		ViewGroup viewGroup = (ViewGroup)View.inflate(context, R.layout.news_frame_view, this);
	}


}
