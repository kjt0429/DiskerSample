package com.disker.promotionnews;

import android.content.Context;
import android.graphics.Point;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NewsV2BannerRecyclerViewAdapter extends RecyclerView.Adapter<NewsV2BannerRecyclerViewAdapter.ItemHolder>{

	private Context mContext;
	private ArrayList<String> mItemList;

	private OnItemClickListener itemClickListener;

	public interface OnItemClickListener {
		void onItemClick(View v, int position) ;
	}

	public void setOnItemClickListener(@NonNull OnItemClickListener itemClickListener) {
		this.itemClickListener = itemClickListener;
	}

	public NewsV2BannerRecyclerViewAdapter(@NonNull Context context, ArrayList<String> itemList) {
		super();

		this.mContext = context;
		this.mItemList = itemList;
	}

	@NonNull
	@Override
	public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.promotion_news_banner_item, parent, false);
		return new ItemHolder(view);


	}

	@Override
	public void onBindViewHolder(@NonNull ItemHolder holder, int position) {

		// 높이 계산
		Point p = Util.getScreenSize(mContext);
		ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) holder.layout.getLayoutParams();
		params.height = (int) (p.y * 0.275);

		if (position == 0) {
			params.setMargins(0, (int) (p.y * 0.01875f), 0, 0);
		}

	}

	@Override
	public int getItemCount() {
		return mItemList != null ? mItemList.size() : 0;
	}

	public class ItemHolder extends RecyclerView.ViewHolder{

		private ConstraintLayout layout;
		private AppCompatImageView itemImageView;
		private AppCompatImageView giftImageView;

		public ItemHolder(@NonNull View itemView) {
			super(itemView);

			layout = (ConstraintLayout) itemView.findViewById(R.id.bannerItem_layout);
			itemImageView = (AppCompatImageView) itemView.findViewById(R.id.promotion_news_banner_item_imageView);
			giftImageView = (AppCompatImageView) itemView.findViewById(R.id.promotion_news_banner_item_gift_imageView);

			itemView.setOnClickListener(v -> {

				int position = getAbsoluteAdapterPosition();
				if (position != RecyclerView.NO_POSITION) {
					if (itemClickListener != null) {
						itemClickListener.onItemClick(v, position);
					}
				}
			});
		}
	}
}
