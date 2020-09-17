package com.disker.promotionnews;

import android.os.Build;
import android.view.View;

class Util {

	/**
	 * 뷰에 HIVE 시스템 UI 플래그 설정
	 * @param view
	 */
	public static void setSystemUiVisibility(View view) {
		if(view == null){
			return;
		}
		view.setSystemUiVisibility(getHiveUiFlags());
	}


	/**
	 * 뷰에 HIVE 시스템 UI 플래그 변경 및 리스너 등록
	 * @param view
	 */
	public static void setOnSystemUiVisibilityChangeListener(View view) {
		if (view == null) {
			return;
		}

		setSystemUiVisibility(view);

		view.setOnSystemUiVisibilityChangeListener(visibility -> {
			if (visibility != getHiveUiFlags()) {
				setSystemUiVisibility(view);
			}
		});
	}


	/**
	 * Return using system ui-flag in hive
	 *
	 * @return 설정된 시스템 UI 플래그 값 반환. 그렇지 않으면, 기본값 반환
	 */
	private static int getHiveUiFlags() {
		int uiFlags = 0x0;

		if (uiFlags == 0x0) {
			uiFlags = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
					| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
					| View.SYSTEM_UI_FLAG_LAYOUT_STABLE
					| View.SYSTEM_UI_FLAG_FULLSCREEN
					| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
				uiFlags = uiFlags | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
			}
		}

		return uiFlags;
	}
}
