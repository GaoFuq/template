package com.gfq.template.databinding;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.gfq.template.utils.DensityUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BindingCustomAttribute {

    /*
      <ImageView
           android:id="@+id/iv_front_cover"
           android:layout_width="match_parent"
           android:layout_height="wrap_content"
           android:visibility="gone"

           error="@{@drawable/input_close}"
           placeHolder="@{@drawable/input_close}"
           imageUrl="@{personal.shop_cover}"
           />
           //这三个属性必须同时存在，缺一不可。否则报错。
     */
    @BindingAdapter({"imageUrl", "placeHolder", "error"})
    public static void loadImage(ImageView view, String url, Drawable holderDrawable, Drawable errorDrawable) {
        //设置图片圆角角度
        RoundedCorners roundedCorners = new RoundedCorners(DensityUtil.dp2px(10));
        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners).override(300, 300);
        Glide.with(view.getContext())
                .load(url)
                .placeholder(holderDrawable)
                .error(errorDrawable)
                .apply(options)
                .into(view);
    }

    @BindingAdapter({"url", "placeHolder", "error"})
    public static void loadImageNoRound(ImageView view, String url, Drawable holderDrawable, Drawable errorDrawable) {
        //设置图片圆角角度
//        RoundedCorners roundedCorners = new RoundedCorners(DensityUtil.dp2px(0));
//        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners).override(300, 300);
        Glide.with(view.getContext())
                .load(url)
                .placeholder(holderDrawable)
                .error(errorDrawable)

                .into(view);
    }


    @BindingAdapter({"timeText"})
    public static void setTimeText(TextView view, long time) {
        time = time * 1000;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        String format = simpleDateFormat.format(new Date(time));
        view.setText(format);
    }


}
