package com.disker.promotionnews;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import java.util.ArrayList;

public class PromotionNewsActivity extends AppCompatActivity {

	private TabListAdapter.TabItemHolder clickedTabItem = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_promotion_news);

		if(getWindow() != null)
			Util.setOnSystemUiVisibilityChangeListener(getWindow().getDecorView());


		ListView tabList = findViewById(R.id.tabList);

		TabListAdapter tabListAdapter = new TabListAdapter(this, getTabItemList());
		tabList.setAdapter(tabListAdapter);

		tabList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if(clickedTabItem != null){
					clickedTabItem.background.setVisibility(View.INVISIBLE);
					clickedTabItem.name.setTextColor(getResources().getColor(R.color.com_hive_sdk_promotion_news_tab_item_color));
				}
				TabListAdapter.TabItemHolder item = ((TabListAdapter.TabItemHolder)view.getTag());
				item.background.setVisibility(View.VISIBLE);
				item.name.setTextColor(getResources().getColor(R.color.colorWhite));
				clickedTabItem = item;

			}
		});

	}



	// 테스트 용 더미
	private ArrayList<String> getTabItemList() {
		ArrayList<String> list = new ArrayList<>();

		list.add("tabItem1");
		list.add("tabItem2");
		list.add("tabItem3");
		list.add("tabItem1");
		list.add("tabItem2");
		list.add("tabItem3");
		list.add("tabItem1");
		list.add("tabItem2");
		list.add("tabItem3");
		list.add("tabItem1");
		list.add("tabItem2");
		list.add("tabItem3");
		list.add("tabItem1");
		list.add("tabItem2");
		list.add("tabItem3");
		list.add("tabItem1");
		list.add("tabItem2");
		list.add("tabItem3");

		return list;
	}


	private class TabListAdapter extends BaseAdapter {

		private Context mContext;
		private LayoutInflater mInflater;
		private ArrayList<String> mItemList;

		public TabListAdapter(Context context, ArrayList<String> itemList) {
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
			viewHolder.name.setText(mItemList.get(position));
			float textScaledPixel = (p.y * 0.034375f) / mContext.getResources().getDisplayMetrics().scaledDensity;
			viewHolder.name.setTextSize(textScaledPixel);


			return convertView;

		}


		public class TabItemHolder{
			public AppCompatImageView background;
			public AppCompatImageView icon;
			public AppCompatTextView name;
		}
	}
}