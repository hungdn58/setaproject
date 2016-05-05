package com.example.hoang.newsproject.image;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hoang.newsproject.R;

/**
 * Created by hoang on 3/14/2016.
 */
public class AdvViewPager extends PagerAdapter {
    int NumberOfPages = 3;
    Context mcontext;

    public AdvViewPager(Context mcontext){
        this.mcontext = mcontext;
    }

    int res[] = {
            R.drawable.ad2,
            R.drawable.ad1,
            R.drawable.ad,
    };

    @Override
    public int getCount() {
        return NumberOfPages;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.advert_item, container, false);

        ImageView image = (ImageView) view.findViewById(R.id.image);
        image.setBackgroundResource(res[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
