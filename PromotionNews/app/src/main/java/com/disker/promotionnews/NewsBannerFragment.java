package com.disker.promotionnews;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class NewsBannerFragment extends Fragment {

	public NewsBannerFragment(){
		super();
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		ViewGroup viewGroup = (ViewGroup)inflater.inflate(R.layout.news_banner_fragment, container, false);

		ListView listView = viewGroup.findViewById(R.id.promotion_news_banner_listView);
		NewsBannerListAdapter adapter = new NewsBannerListAdapter(getContext(), getTabItemList()); //TODO
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				// TODO: 아래 테스트용 분기를 .. 값 따라 처리하도록 변경
				switch (position){
					case 0:
						((PromotionNewsV2Activity)getActivity()).addView("frame-true");
						break;
					case 1:
						((PromotionNewsV2Activity)getActivity()).addView("frame-false");
						break;
					case 2:
						((PromotionNewsV2Activity)getActivity()).addView("fullscreen");
						break;
					default:
						((PromotionNewsV2Activity)getActivity()).addView("frame");
						break;

				}

			}
		});



		return viewGroup;
	}

	// 테스트 용 더미
	private ArrayList<String> getTabItemList() {
		ArrayList<String> list = new ArrayList<>();

		list.add("https://developers.withhive.com/");
		list.add("https://www.google.com");
		list.add("banner");
		list.add("banner");


		return list;
	}

	private class NewsBannerListAdapter extends BaseAdapter {

		private Context mContext;
		private LayoutInflater mInflater;
		private ArrayList<String> mItemList;

		public NewsBannerListAdapter(Context context, ArrayList<String> itemList) {
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
			NewsBannerItemHolder viewHolder;

			if(convertView == null){
				convertView = mInflater.inflate(R.layout.promotion_news_banner_item, parent, false);

				Log.d("jintae", convertView.toString());

				// 레이아웃 매핑
				viewHolder = new NewsBannerItemHolder();
				viewHolder.imageView = (AppCompatImageView) convertView.findViewById(R.id.promotion_news_banner_item_imageView);

				convertView.setTag(viewHolder);

			}else{
				viewHolder = (NewsBannerItemHolder) convertView.getTag();

			}
			// 높이 계산
			Display display = ((WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
			Point p = new Point();
			display.getSize(p);

			ViewGroup.LayoutParams params = convertView.getLayoutParams();
			params.height = (int) (p.y * 0.275);
			convertView.setLayoutParams(params);

			// 각 뷰 설정
			/*viewHolder.background.setVisibility(View.INVISIBLE);
			viewHolder.name.setText(mItemList.get(position));*/

			//viewHolder.imageView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));


			Log.d("jintae","BANNER FRAGMENT");
			return convertView;

		}


		public class NewsBannerItemHolder {

			AppCompatImageView imageView;

		}
	}
}
