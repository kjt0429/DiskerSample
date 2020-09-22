package com.disker.promotionnews;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NewsBannerFragment extends Fragment {

	public NewsBannerFragment(){
		super();
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		ViewGroup viewGroup = (ViewGroup)inflater.inflate(R.layout.news_banner_fragment, container, false);

		RecyclerView bannerListView = viewGroup.findViewById(R.id.promotion_news_banner_listView);
		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
		bannerListView.setLayoutManager(linearLayoutManager);

		NewsV2BannerRecyclerViewAdapter adapter = new NewsV2BannerRecyclerViewAdapter(getActivity(), getTabItemList());
		adapter.setOnItemClickListener((v, position) -> {
			// TODO: 아래 테스트용 분기를 .. 값 따라 처리하도록 변경
			switch (position) {
				case 0:
					((NewsV2Activity) getActivity()).addView("frame-true");
					break;
				case 1:
					((NewsV2Activity) getActivity()).addView("frame-false");
					break;
				case 2:
					((NewsV2Activity) getActivity()).addView("fullscreen");
					break;
				default:
					((NewsV2Activity) getActivity()).addView("frame");
					break;

			}
		});

		bannerListView.setAdapter(adapter);


		return viewGroup;
	}

	// TODO: 테스트 용 더미
	private ArrayList<String> getTabItemList() {
		ArrayList<String> list = new ArrayList<>();

		list.add("https://developers.withhive.com/");
		list.add("https://www.google.com");
		list.add("banner");
		list.add("banner");


		return list;
	}
}
