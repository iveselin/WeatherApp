package hr.ferit.iveselin.weatherapp.util;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ImageLoader {

    public static final String BASE_URL = "http://openweathermap.org/img/w/";

    public static void loadImage(Context context, String url, ImageView iv) {
        Picasso.with(context).load(BASE_URL + url + ".png").into(iv);
    }
}
