package com.disker.promotionnews;

import android.content.Context;
import android.graphics.Point;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

// TOdo: Util. 매번 context에서 가져오지 않고, 한번만 create에서 가져오고, 이거 저장한거 그대로 계속해서 사용하기. onResume()에서 저장해도 되겠다...
public class NewsV2TabRecyclerViewAdapter extends RecyclerView.Adapter<NewsV2TabRecyclerViewAdapter.TabItemHolder>{

	private Context mContext;
	private ArrayList<NewsV2Model> mItemList;

	private OnItemClickListener itemClickListener;
	private TabItemHolder selectedItemHolder;

	public interface OnItemClickListener {
		void onItemClick(View v, int position) ;
	}

	public void setOnItemClickListener(@NonNull OnItemClickListener itemClickListener) {
		this.itemClickListener = itemClickListener;
	}

	public NewsV2TabRecyclerViewAdapter(@NonNull Context context, ArrayList<NewsV2Model> itemList) {
		super();

		mContext = context;
		mItemList = itemList;
	}

	@NonNull
	@Override
	public TabItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.promotion_news_tab_item, parent, false);
		return new TabItemHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull TabItemHolder holder, int position) {

		// 높이 계산
		Point p = Util.getScreenSize(mContext);
		ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) holder.layout.getLayoutParams();
		if(Util.isPortrait()) params.height = (int) (p.y * 0.091549);
		else params.height = (int) (p.y * 0.15);



		// 뷰 설정
		holder.background.setVisibility(View.INVISIBLE);
		holder.name.setText(mItemList.get(position).name);


		if (Util.isPortrait()) {
			float textScaledPixel = (p.x * 0.034375f) / mContext.getResources().getDisplayMetrics().scaledDensity;
			holder.name.setTextSize(textScaledPixel);

			int lastPosition = mItemList.size() - 1;
			if (position == 0) {
				setSelectedItemHolder(holder);
			} else if (position == lastPosition) {
			}
		}
		else {
			float textScaledPixel = (p.y * 0.034375f) / mContext.getResources().getDisplayMetrics().scaledDensity;
			holder.name.setTextSize(textScaledPixel);

			int lastPosition = mItemList.size() - 1;
			if (position == 0) {
				setSelectedItemHolder(holder);
				params.setMargins(0, (int) (p.y * 0.03125f), 0, 0);
			} else if (position == lastPosition) {
				params.setMargins(0, 0, 0, (int) (p.y * 0.03125f));
			}
		}

	}

	@Override
	public int getItemCount() {
		return mItemList != null ? mItemList.size() : 0;
	}

	private void setSelectedItemHolder(@NonNull TabItemHolder holder) {
		if(selectedItemHolder != null){
			selectedItemHolder.offItem();
		}
		selectedItemHolder = holder;
		selectedItemHolder.onItem();
	}


	public class TabItemHolder extends RecyclerView.ViewHolder {

		private ConstraintLayout layout;
		private ConstraintLayout textViewLayout;
		private AppCompatImageView background;
		private AppCompatImageView icon;
		private AppCompatImageView badge;
		private AppCompatTextView name;

		public TabItemHolder(@NonNull View itemView) {
			super(itemView);

			layout = (ConstraintLayout)itemView.findViewById(R.id.tabItem_layout);
			background = (AppCompatImageView)itemView.findViewById(R.id.tabItem_background);
			icon = (AppCompatImageView)itemView.findViewById(R.id.tabItem_icon);
			badge = (AppCompatImageView)itemView.findViewById(R.id.tabItem_badge);
			name = (AppCompatTextView) itemView.findViewById(R.id.tabItem_textView);

			if(Util.isPortrait()){
				textViewLayout = (ConstraintLayout)itemView.findViewById(R.id.tabItem_textView_layout);
			}

			itemView.setOnClickListener(v -> {

				int position = getAbsoluteAdapterPosition();
				if (position != RecyclerView.NO_POSITION){
					if(itemClickListener != null){
						setSelectedItemHolder(this);
						itemClickListener.onItemClick(v, position);
					}
				}
			});
		}

		private void onItem() {
			try {
				background.setVisibility(View.VISIBLE);
				name.setTextColor(mContext.getResources().getColor(R.color.colorWhite));

				if(Util.isPortrait()){
					textViewLayout.setVisibility(View.VISIBLE);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		private void offItem() {
			try {
				background.setVisibility(View.INVISIBLE);
				name.setTextColor(mContext.getResources().getColor(R.color.com_hive_sdk_promotion_news_tab_item_color));

				if(Util.isPortrait()){
					textViewLayout.setVisibility(View.GONE);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}


