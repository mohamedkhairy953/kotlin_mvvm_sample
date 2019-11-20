package com.example.raseedytask.helpers.binding;

import android.widget.ImageView;
import androidx.databinding.BindingAdapter;
import com.bumptech.glide.Glide;

/**
 * Created by Mohamed Khaled on Thu, 16/Aug/2018 at 4:17 PM.
 * <p>
 * mohamed.khaled@apptcom.com
 * linkedin.com/in/mohamed5aled
 */
public class BindingAdapters {

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext()).asDrawable().load(imageUrl).into(view);
    }


}
