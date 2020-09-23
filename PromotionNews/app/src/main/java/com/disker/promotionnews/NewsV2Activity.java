package com.disker.promotionnews;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NewsV2Activity extends AppCompatActivity {

	private ArrayList<Fragment> fragments = new ArrayList<>();

	FragmentManager fm;
	FragmentTransaction tran;

	FrameLayout frameLayout;

	FrameLayout fullscreenFrameLayout;


	FrameLayout newsV2View;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_promotion_news_v2);

		if(getWindow() != null)
			Util.setOnSystemUiVisibilityChangeListener(getWindow().getDecorView());

		Intent intent = getIntent();
		String forced = intent.getStringExtra("forced");

/*


		FrameLayout contentViewLayout = findViewById(R.id.contentViewLayout);
		ConstraintLayout tapLayout = findViewById(R.id.tabLayout);
		ConstraintLayout bottomLayout = findViewById(R.id.contentLayout_bottom);

		if (forced.equals("true")) {
			// 바텀 프레임 보임
			bottomLayout.setVisibility(View.VISIBLE);

			Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
			Point p = new Point();
			display.getSize(p);

			// TODO: 한쪽 방향 코너 지정 이슈.. (웹뷰 의 경우 shape로 처리도 안되고, 기타 API는 os level 분기)
			contentViewLayout.setPadding(0, 0, 0, (int) (p.y * 0.09722));
			tapLayout.setPadding(0, 0, 0, (int) (p.y * 0.09722));

		} else {
			// 바텀 프레임 보이지 않음
			bottomLayout.setVisibility(View.INVISIBLE);
		}



*/
/*
		생성자에서 무언의 뷰 모델을 받아서 수정하기
		 *//*

*/
		ArrayList<NewsV2Model> dummyArray = getTabItemList();

/*
		for (NewsV2Model data : dummyArray) {

			if (data.url.equals("")) {
				fragments.add(new NewsBannerFragment());
			} else {
				fragments.add(new NewsWebFragment(data));
			}

		}

		setFrag(0);


		*/
/* Set Tab-List */

		RecyclerView tabList = findViewById(R.id.tabList);

		LinearLayoutManager linearLayoutManager;
		if(Util.isPortrait()){
			linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
		}else{
			linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
		}

		tabList.setLayoutManager(linearLayoutManager);

		NewsV2TabRecyclerViewAdapter adapter = new NewsV2TabRecyclerViewAdapter(this, dummyArray);
		adapter.setOnItemClickListener((v, position) -> {}/*setFrag(position)*/);

		tabList.setAdapter(adapter);
/*

		// TODO
		frameLayout = findViewById(R.id.frameLayout_frameLayout);





		fullscreenFrameLayout = findViewById(R.id.fullscreen_frameLayout);


		// 바텀에 글자 크기 지정
		// 높이 계산
		Point p = Util.getScreenSize(this);

		AppCompatTextView forceTextView = findViewById(R.id.forceTextView);
		float textScaledPixel = (p.y * 0.031388f) / getResources().getDisplayMetrics().scaledDensity;
		forceTextView.setTextSize(textScaledPixel);


*/
		// 우측 클로즈 버튼
		AppCompatImageView frame_close_btn = findViewById(R.id.frame_close_btn);

		// TODO: 애니메이션 처리해야함
		frame_close_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});


	}

	public void addView(String type) {
		switch (type) {
			case "frame-true":
				NewsFrameView view = new NewsFrameView(this,true);
				newsV2View = view; // TODO remove
				frameLayout.addView(view);
				break;
			case "frame-false":
				NewsFrameView view2 = new NewsFrameView(this,false);
				newsV2View = view2; // TODO remove
				frameLayout.addView(view2);
				break;
			case "fullscreen":
				NewsV2FullScreenView view3 = new NewsV2FullScreenView(this);
				newsV2View = view3;
				fullscreenFrameLayout.addView(view3);
				break;
			default:
				break;
		}


	}

	// TODO: 예외처리 하기
	public void removeFrameView(){
		frameLayout.removeView(newsV2View);
	}

	// TODO: 예외처리 하기
	public void removeFullscreenView(){
		fullscreenFrameLayout.removeView(newsV2View);
	}

	public View getViewByPosition(int pos, ListView listView) {
		final int firstListItemPosition = listView.getFirstVisiblePosition();
		final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

		if (pos < firstListItemPosition || pos > lastListItemPosition) {
			return listView.getAdapter().getView(pos, null, listView);
		} else {
			final int childIndex = pos - firstListItemPosition;
			return listView.getChildAt(childIndex);
		}
	}

	// 테스트 용 더미
	private ArrayList<NewsV2Model> getTabItemList() {
		ArrayList<NewsV2Model> list = new ArrayList<>();

		list.add(new NewsV2Model("개발자 사이트","https://developers.withhive.com/"));
		list.add(new NewsV2Model("구글","https://www.google.com"));
		list.add(new NewsV2Model("이벤트",""));
		list.add(new NewsV2Model("이벤트2",""));
		list.add(new NewsV2Model("이벤트3",""));
		list.add(new NewsV2Model("이벤트4",""));


		return list;
	}

	public void setFrag(int n) {
		fm = getSupportFragmentManager();
		tran = fm.beginTransaction();

		tran.replace(R.id.contentViewLayout, fragments.get(n));
		tran.commit();

	}




}