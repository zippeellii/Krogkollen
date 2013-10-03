package se.chalmers.krogkollen.map;

import android.content.res.Resources;
import android.graphics.*;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/*
 * This file is part of Krogkollen.
 *
 * Krogkollen is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Krogkollen is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Krogkollen.  If not, see <http://www.gnu.org/licenses/>.
 */


 /**
 * Builds settings for markers in Google Maps V2 for Android.
 *
 * Author: Johan Backman
 * Date: 2013-09-22
 */
class MarkerOptionsFactory {

    private final static int bigTextRatio = 22;
    private final static int smallTextRatio = 30;
    private final static int marginRatio = 60;

    /**
     * Creates marker options with the specified text and background from Android resources.
     *
     * @param resources APP resources.
     * @param resourceId id of image background.
     * @param mainText title text to be added on top of background.
     * @param denotedText title text to be added on top of background.
     * @param position where to place the marker.
     * @param pubId identification number for a pub.
     * @return a new google maps marker.
     */
    public static MarkerOptions createMarkerOptions(Resources resources, int resourceId, String mainText,
                                                    int denotedText, LatLng position, String pubId) {

        // Make the bitmap mutable, since an object retrieved from resources is set to immutable by default.
        Bitmap bitmap = BitmapFactory.decodeResource(resources, resourceId);
        Bitmap bitmapResult = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        bitmap.recycle();

        // Create a canvas so the text can be drawn on the image.
        Canvas canvas = new Canvas(bitmapResult);

        // Values used in the scaling of text and padding.
        int screenWidth = resources.getDisplayMetrics().widthPixels;
        int screenHeight = resources.getDisplayMetrics().heightPixels;
        int minWidthHeight = Math.min(screenHeight, screenWidth);

        // Add text to canvas.
        Paint paint = new Paint();
        paint.setColor(Color.rgb(44,44,44));
        paint.setTextSize(minWidthHeight / bigTextRatio);
        paint.setTypeface(Typeface.SANS_SERIF);
        if (mainText.length() > 10) {                           // if the text is too long cut it
            mainText = mainText.substring(0, 10);
        }
        canvas.drawText(mainText, 7 + (minWidthHeight/marginRatio), 31 + (minWidthHeight/marginRatio), paint);
        paint.setColor(Color.rgb(141,141,141));
        paint.setTextSize(minWidthHeight / smallTextRatio);
        canvas.drawText((denotedText + ""), 7 + (minWidthHeight/marginRatio), 62 + (minWidthHeight/marginRatio), paint);

        // Finalize the markerOptions.
        MarkerOptions options = new MarkerOptions()
                                .position(position)
                                .icon(BitmapDescriptorFactory.fromBitmap(bitmapResult))
                                .anchor(0.3f, 0.94f)
                                .title(pubId);

        return options;
    }

}
