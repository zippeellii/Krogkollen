package se.chalmers.krogkollen.map.marker;

import android.content.res.Resources;
import android.graphics.*;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * MarkerFactory (UTF-8)
 * <p/>
 * Author: Johan Backman
 * Date: 2013-09-22
 */

public class MarkerFactory {

    /**
     * Creates marker options with the specified text and background from Android resources.
     *
     * @param resources APP resources.
     * @param resourceId id of image background.
     * @param mainText title text to be added on top of background.
     * @param denotedText title text to be added on top of background.
     * @param position where to place the marker.
     * @return a new google maps marker.
     */
    public static MarkerOptions createMarkerOptions(Resources resources, int resourceId, String mainText, String denotedText, LatLng position) {

        // Make the bitmap mutable, since an object retrieved from resources is set to immutable by default.
        Bitmap bitmap = BitmapFactory.decodeResource(resources, resourceId);
        Bitmap bitmapResult = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        bitmap.recycle();

        // Create a canvas so the text can be drawn on the image.
        Canvas canvas = new Canvas(bitmapResult);

        // Add text to canvas.
        Paint paint = new Paint();
        paint.setColor(Color.rgb(44,44,44));
        paint.setTextSize(28);
        paint.setTypeface(Typeface.SANS_SERIF);
        if (mainText.length() > 8) {                           // if the text is too long cut it
            mainText = mainText.substring(0, 8);
        }
        canvas.drawText(mainText, 10, 34, paint);
        paint.setColor(Color.rgb(141,141,141));
        paint.setTextSize(18);
        canvas.drawText(denotedText, 10, 57, paint);

        // Finalize the markerOptions.
        MarkerOptions options = new MarkerOptions()
                                .position(position)
                                .icon(BitmapDescriptorFactory.fromBitmap(bitmapResult))
                                .anchor(0.3f, 0.96f);
        return options;
    }

}
