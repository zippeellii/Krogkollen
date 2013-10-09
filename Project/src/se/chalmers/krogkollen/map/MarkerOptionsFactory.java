package se.chalmers.krogkollen.map;

import android.content.res.Resources;
import android.graphics.*;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import se.chalmers.krogkollen.R;
import se.chalmers.krogkollen.pub.IPub;

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
 * @author Johan Backman
 */
public class MarkerOptionsFactory {

	private final static int	bigTextRatio	= 22;
	private final static int	smallTextRatio	= 30;
	private final static int	marginRatio		= 60;

	/**
	 * Creates marker options with the specified text and background from Android resources.
	 * 
	 * @param resources APP resources.
	 * @param pub the pub that should be displayed on the marker.
	 * @return a new google maps marker.
	 */
	public static MarkerOptions createMarkerOptions(Resources resources, IPub pub) {

		// Make the bitmap mutable, since an object retrieved from resources is set to immutable by
		// default.
		Bitmap bitmap = getBackgroundPicture(pub.getQueueTime(), resources);
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
		paint.setColor(Color.rgb(44, 44, 44));
		paint.setTextSize(minWidthHeight / bigTextRatio);
		paint.setTypeface(Typeface.SANS_SERIF);
		String mainText = pub.getName();
		if (mainText.length() > 10) {                           // if the text is too long cut it
			mainText = mainText.substring(0, 10);
		}
		canvas.drawText(mainText, 7 + (minWidthHeight / marginRatio),
				31 + (minWidthHeight / marginRatio), paint);
		paint.setColor(Color.rgb(141, 141, 141));
		paint.setTextSize(minWidthHeight / smallTextRatio);
		canvas.drawText((pub.getTodaysOpeningHours().toString()),
				7 + (minWidthHeight / marginRatio), 62 + (minWidthHeight / marginRatio), paint);

		// Finalize the markerOptions.
		MarkerOptions options = new MarkerOptions()
				.position(new LatLng(pub.getLatitude(), pub.getLongitude()))
				.icon(BitmapDescriptorFactory.fromBitmap(bitmapResult))
				.anchor(0.3f, 0.94f)
				.title(pub.getID());

		return options;
	}

	// Find the right background to use.
	private static Bitmap getBackgroundPicture(int queueTime, Resources resources) {
		switch (queueTime) {
			case 1:
				return BitmapFactory.decodeResource(resources, R.drawable.green_marker_bg);
			case 2:
				return BitmapFactory.decodeResource(resources, R.drawable.yellow_marker_bg);
			case 3:
				return BitmapFactory.decodeResource(resources, R.drawable.red_marker_bg);
			default:
				return BitmapFactory.decodeResource(resources, R.drawable.gray_marker_bg);
		}
	}
}
