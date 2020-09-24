package com.disker.promotionnews;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

public class PromotionCustomViewRoundedCorner extends FrameLayout {

	public static final int SCALED_DIRECTION_X = 0;
	public static final int SCALED_DIRECTION_Y = 1;

	protected float CORNER_RADIUS = 15.0f;
	protected float cornerRadius;

	protected Context mContext;

	public PromotionCustomViewRoundedCorner(Context context) {
		super(context);
		mContext = context;
		init(context, null, 0);
	}

	public PromotionCustomViewRoundedCorner(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		init(context, attrs, 0);
	}

	public PromotionCustomViewRoundedCorner(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
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

		Log.d("disker11",cornerRadius+"");

		if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1) {
			setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		}
		else
		{
			setLayerType(View.LAYER_TYPE_HARDWARE, null);       //  LG V40 device has exception.
		}

		setCornerRadius(cornerRadius);
	}

	public void setCornerRadius(float radius){
		cornerRadius = radius;

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			GradientDrawable drawable = new GradientDrawable();
			drawable.setCornerRadius(cornerRadius);

			setBackground(drawable);
			setClipToOutline(true);

		}
	}

	/**
	 * 입력된 축에 대해 기준 사이즈 만큼 확대된 round 설
	 * @param scaledDirection SCALED_DIRECTION_X, SCALED_DIRECTION_Y
	 * @param dp 기준 축 사이즈 (dp)
	 * @param round 기준 라운드 사이즈 (dp)
	 */
	public void setScaledCornerSize(int scaledDirection, float dp, float round){

		if(getViewTreeObserver().isAlive()){
			getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
				@Override
				public boolean onPreDraw() {
					if(getMeasuredHeight() == 0)
						return true;

					if(getViewTreeObserver().isAlive())
						getViewTreeObserver().removeOnPreDrawListener(this);

					if(scaledDirection == SCALED_DIRECTION_X){

					}else{

					}

					float ratio = 0.f;
					switch (scaledDirection){
						case SCALED_DIRECTION_X:
							float x_dp = convertPxToDp(mContext, getMeasuredWidth());
							ratio = x_dp / dp;
							break;

						case SCALED_DIRECTION_Y:
							float y_dp = convertPxToDp(mContext, getMeasuredHeight());
							ratio = y_dp / dp;
							break;

						default:
							// TODO: 잘못된 사이즈 로그 찍기
							break;
					}
					setCornerRadius(convertDpToPx(mContext, ratio*round));

					return false;
				}
			});
		}
	}

	public static float convertPxToDp(Context context, float px){
		Resources resources = context.getResources();
		DisplayMetrics metrics = resources.getDisplayMetrics();
		return px / ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
	}

	private float convertDpToPx(Context context, float dp) {
		DisplayMetrics metrics = context.getResources().getDisplayMetrics();
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, metrics);
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
