package com.disker.promotionnews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;

public class PromotionCustomViewRoundedCorner extends FrameLayout {
	protected float CORNER_RADIUS = 15.0f;
	protected float cornerRadius;

	public PromotionCustomViewRoundedCorner(Context context) {
		super(context);
		init(context, null, 0);
	}

	public PromotionCustomViewRoundedCorner(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs, 0);
	}

	public PromotionCustomViewRoundedCorner(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context, attrs, defStyle);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
	}

	protected void init(Context context, AttributeSet attrs, int defStyle) {
		DisplayMetrics metrics = context.getResources().getDisplayMetrics();
		cornerRadius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, CORNER_RADIUS, metrics);

		TypedArray a = context.getTheme().obtainStyledAttributes(
				attrs,
				R.styleable.PromotionViewRoundedCorner,
				0, 0);
		try {
			cornerRadius = a.getDimension(R.styleable.PromotionViewRoundedCorner_round, cornerRadius);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			a.recycle();
		}

		if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1) {
			setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		}
		else
		{
			setLayerType(View.LAYER_TYPE_HARDWARE, null);       //  LG V40 device has exception.
		}

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			GradientDrawable drawable = new GradientDrawable();
			drawable.setCornerRadius(cornerRadius);
			setBackground(drawable);
			setClipToOutline(true);
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);

		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
			Path path = new Path();
			path.addRoundRect(new RectF(0f, 0f, canvas.getWidth(), canvas.getHeight()), cornerRadius, cornerRadius, Path.Direction.CW);
			canvas.clipPath(path);
		}

	}
}
