package com.tencent.sgz.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tencent.sgz.AppContext;
import com.tencent.sgz.AppDataProvider;
import com.tencent.sgz.R;
import com.tencent.sgz.bean.News;
import com.tencent.sgz.common.UIHelper;
import com.tencent.sgz.entity.Article;
import com.tencent.sgz.ui.BaseActivity;

import java.util.ArrayList;

public class ILoveViennaLiaoSliderAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Article> items;

    public ILoveViennaLiaoSliderAdapter(Activity context,ArrayList<Article> items){
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.items = items;
    }

    @Override
    public int getCount() {
        //return items.size();
        //http://my.oschina.net/xsk/blog/119167
        return Integer.MAX_VALUE;
    }

    //TODO:
    @Override
    public Object instantiateItem(ViewGroup arg0, int position) {

        if(items.size()==0){
            return null;
        }

        int posi = position % items.size();

        View view = inflater.inflate(R.layout.viewflow_image_item, null);
        ImageView iv =(ImageView) view.findViewById(R.id.imgView);
        final Article item = items.get(posi);
        String url = item.getCover();
        if(null==url||url.equals("")){
            url = AppDataProvider.URL.DEFAULT_SLIDE_IMG;
        }
        UIHelper.showLoadImage(iv, url, "图片加载失败" + url);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                News news = new News();
                news.setTitle(item.getTitle());
                news.setDesc(item.getDesc());
                news.setFace(item.getCover());
                news.setUrl(AppDataProvider.assertUrl( AppContext.Instance, item.getUrl()));
                news.setCateName(item.getCateName());
                UIHelper.showNewsDetailByInstance(context, news);
            }
        });
        arg0.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup arg0, int arg1, Object arg2) {
        arg0.removeView((View) arg2);
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1) {

    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public void startUpdate(ViewGroup arg0) {

    }

    @Override
    public void finishUpdate(ViewGroup arg0) {

    }

    public void updateData(ArrayList<Article> data){
        this.items = data;
        this.notifyDataSetChanged();
    }
}
