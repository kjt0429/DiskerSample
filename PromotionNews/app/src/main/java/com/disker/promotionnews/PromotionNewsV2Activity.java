package com.disker.promotionnews;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class PromotionNewsV2Activity extends AppCompatActivity {

	private TabListAdapter.TabItemHolder clickedTabItem = null;

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
		for (NewsV2Model data : dummyArray) {

			if (data.url.equals("")) {
				fragments.add(new NewsBannerFragment());
			} else {
				fragments.add(new NewsWebFragment(data));
			}

		}

/*
		setFrag(0);

		*/


		ListView tabList = findViewById(R.id.tabList);

		TabListAdapter tabListAdapter = new TabListAdapter(this, dummyArray);
		tabList.setAdapter(tabListAdapter);

/*
		tabList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				if (position != 0) {
					View v = tabList.getChildAt(0);
					TabListAdapter.TabItemHolder item = ((TabListAdapter.TabItemHolder) v.getTag());
					item.offItem();
				}

				if (clickedTabItem != null) {
					clickedTabItem.offItem();
				}


				TabListAdapter.TabItemHolder item = ((TabListAdapter.TabItemHolder) view.getTag());
				item.onItem();
				clickedTabItem = item;


				// todo check
				setFrag(position);

			}
		});


		// TODO
		frameLayout = findViewById(R.id.frameLayout_frameLayout);

		AppCompatImageView frame_close_btn = findViewById(R.id.frame_close_btn);

		// TODO: 애니메이션 처리해야함
		frame_close_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});


		fullscreenFrameLayout = findViewById(R.id.fullscreen_frameLayout);


		// 바텀에 글자 크기 지정
		// 높이 계산
		Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		Point p = new Point();
		display.getSize(p);

		AppCompatTextView forceTextView = findViewById(R.id.forceTextView);
		float textScaledPixel = (p.y * 0.031388f) / getResources().getDisplayMetrics().scaledDensity;
		forceTextView.setTextSize(textScaledPixel);

*/


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

	public void setFrag(int n){    //프래그먼트를 교체하는 작업을 하는 메소드를 만들었습니다
		fm = getSupportFragmentManager();
		tran = fm.beginTransaction();

		tran.replace(R.id.contentViewLayout, fragments.get(n));
		tran.commit();

	}

	// TODO (disker) : 나중에 정확한 값 받아오도록 하기. 현재는 True 가 세로 모드
	public Boolean getOrientation(){
		return true;
	}

	private class TabListAdapter extends BaseAdapter {

		private Context mContext;
		private LayoutInflater mInflater;
		private ArrayList<NewsV2Model> mItemList;

		public TabListAdapter(Context context, ArrayList<NewsV2Model> itemList) {
			super();

			this.mContext = context;
			this.mItemList = itemList;

			this.mInflater = (LayoutInflater) context.getSystemService((Context.LAYOUT_INFLATER_SERVICE));

		}

		@Override
		public int getCount() {
			return mItemList.size();
		}

		@Override
		public Object getItem(int position) {
			return mItemList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TabItemHolder viewHolder;

			if(convertView == null){
				convertView = mInflater.inflate(R.layout.promotion_news_tab_item, parent, false);

				// 레이아웃 매핑
				viewHolder = new TabItemHolder();
				viewHolder.background = (AppCompatImageView) convertView.findViewById(R.id.tabItem_background);
				viewHolder.name = (AppCompatTextView) convertView.findViewById(R.id.tabItem_textView);
				//viewHolder = (ImageView) convertView.find ~`
				//

				convertView.setTag(viewHolder);

			}else{
				viewHolder = (TabItemHolder) convertView.getTag();

			}
			// 높이 계산
			Display display = ((WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
			Point p = new Point();
			display.getSize(p);

			ViewGroup.LayoutParams params = convertView.getLayoutParams();
			params.height = (int) (p.y * 0.15);
			convertView.setLayoutParams(params);

			// 각 뷰 설정
			viewHolder.background.setVisibility(View.INVISIBLE);
			viewHolder.name.setText(mItemList.get(position).name);
			float textScaledPixel = (p.y * 0.034375f) / mContext.getResources().getDisplayMetrics().scaledDensity;
			viewHolder.name.setTextSize(textScaledPixel);


			if(position==0){
				viewHolder.onItem();
			}

			return convertView;

		}


		public class TabItemHolder{
			public AppCompatImageView background;
			public AppCompatImageView icon;
			public AppCompatTextView name;

			public void onItem(){
				try {
					background.setVisibility(View.VISIBLE);
					name.setTextColor(getResources().getColor(R.color.colorWhite));
				}catch (Exception e){
					e.printStackTrace();
				}
			}

			public void offItem(){
				try {
					background.setVisibility(View.INVISIBLE);
					name.setTextColor(getResources().getColor(R.color.com_hive_sdk_promotion_news_tab_item_color));
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		}
	}

	class NewsV2Model{
		String name;
		String url;

		public NewsV2Model(String name, String url){
			this.name = name;
			this.url = url;
		}
	}

}