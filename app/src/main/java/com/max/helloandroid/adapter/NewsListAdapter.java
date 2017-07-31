package com.max.helloandroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.max.helloandroid.R;
import com.max.helloandroid.bean.NewsBean;

import java.util.List;

/**
 * Author: WangHuaGui
 * Date: 2017/7/13 14:48
 * Describe: 新闻列表适配
 */

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.BaseViewHolder> {
    private Context mContext;
    private static final int SMALL_IMG = 1;
    private static final int THREE_IMG = 2;
    private List<NewsBean.HeadLineBean> mHeadLineBeanList;
    private OnItemClickListener mOnItemClickListener;
    private LayoutInflater mLayoutInflater;

    public NewsListAdapter(Context mContext, List<NewsBean.HeadLineBean> mHeadLineBeanList) {
        this.mContext = mContext;
        this.mHeadLineBeanList = mHeadLineBeanList;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 根据viewType返回不同的view，此viewtype从getItemViewType方法中获得
        View view = null;
        if (viewType == THREE_IMG) {
            view = mLayoutInflater.inflate(R.layout.item_news_three_pic, parent, false);
            return new ThreeImgViewHolder(view);
        } else {
            view = mLayoutInflater.inflate(R.layout.item_news_normal, parent, false);
            return new SmallImgViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(final BaseViewHolder holder, final int position) {
        NewsBean.HeadLineBean headLineBean = mHeadLineBeanList.get(position);
        // 如果有额外图片存在
        List<NewsBean.HeadLineBean.ImgextraBean> imgextraBeenlist = headLineBean.getImgextra();
        String imageSrc = headLineBean.getImgsrc();
        String title = headLineBean.getTitle();
        String source = headLineBean.getSource();
        String postTime = headLineBean.getPtime();

        if (getItemViewType(position) == THREE_IMG) {
            ThreeImgViewHolder threeImgViewHolder = (ThreeImgViewHolder) holder;
            // 三张图片的情况
            setNetPicture(imageSrc, threeImgViewHolder.one_image);
            for (int j = 0; j < imgextraBeenlist.size(); j++) {
                if (j == 0) {
                    setNetPicture(imgextraBeenlist.get(j).getImgsrc(), threeImgViewHolder.two_image);
                } else if (j == 1) {
                    setNetPicture(imgextraBeenlist.get(j).getImgsrc(), threeImgViewHolder.three_image);
                }
            }
        } else if (getItemViewType(position) == SMALL_IMG) {
            // 标准视图的情况
            SmallImgViewHolder smallImgViewHolder = (SmallImgViewHolder) holder;
            // 设置图片
            setNetPicture(imageSrc, smallImgViewHolder.item_news_tv_img);
        }
        holder.item_news_tv_title.setText(title);
        holder.item_news_tv_time.setText(postTime);
        holder.item_news_tv_source.setText(source);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(holder.itemView, position);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return mHeadLineBeanList.size();
    }

    @Override
    public int getItemViewType(int position) {
        int viewType = SMALL_IMG;
        NewsBean.HeadLineBean headLineBean = mHeadLineBeanList.get(position);
        List<NewsBean.HeadLineBean.ImgextraBean> imgextraBeanlist = headLineBean.getImgextra();
        if (imgextraBeanlist != null && imgextraBeanlist.size() > 1) {
            viewType = THREE_IMG;
        }
        return viewType;
    }

    /**
     * 设置Item点击监听
     *
     * @param listener
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }


    private void setNetPicture(String url, ImageView img) {
        Glide.with(mContext)
                .load(url)
                .placeholder(R.mipmap.im_default_bg_small)
                .crossFade()
                .into(img);
    }

    /**
     * 基类BaseViewHolder，拥有三种视图都有的标题及底部标签
     */
    class BaseViewHolder extends RecyclerView.ViewHolder {
        public TextView item_news_tv_title;
        // 底部布局视图
        public TextView item_news_tv_time;
        public TextView item_news_tv_arrow;
        public TextView item_news_tv_source;

        public BaseViewHolder(View itemView) {
            super(itemView);
            item_news_tv_title = (TextView) itemView.findViewById(R.id.item_news_tv_title);
            item_news_tv_time = (TextView) itemView.findViewById(R.id.item_news_tv_time);
            item_news_tv_arrow = (TextView) itemView.findViewById(R.id.item_news_tv_arrow);
            item_news_tv_source = (TextView) itemView.findViewById(R.id.item_news_tv_source);
        }
    }


    /**
     * 拥有三张图片的布局
     */
    class ThreeImgViewHolder extends BaseViewHolder {

        public ImageView one_image;
        public ImageView two_image;
        public ImageView three_image;

        public ThreeImgViewHolder(View itemView) {
            super(itemView);
            one_image = (ImageView) itemView.findViewById(R.id.one_image);
            two_image = (ImageView) itemView.findViewById(R.id.two_image);
            three_image = (ImageView) itemView.findViewById(R.id.three_image);
        }
    }

    /**
     * 正常布局
     */
    class SmallImgViewHolder extends BaseViewHolder {

        public ImageView item_news_tv_img;

        public SmallImgViewHolder(View itemView) {
            super(itemView);
            item_news_tv_img = (ImageView) itemView.findViewById(R.id.item_news_tv_img);
        }
    }

}
