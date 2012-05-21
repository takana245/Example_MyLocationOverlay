package name.takana245.examplemylocationoverlay;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.location.Location;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Projection;

public class SampleOverlay extends MyLocationOverlay {

	private Context mContext;
	private GeoPoint mGeoPoint = null;

	public SampleOverlay(Context context, MapView mapView) {
		super(context, mapView);
		this.mContext = context;
	}

	@Override
	public synchronized boolean draw(Canvas canvas, MapView mapView, boolean shadow, long when) {
		// 自作アイコン
		if (mGeoPoint != null) {
			Projection pro = mapView.getProjection();
			Point p = pro.toPixels(mGeoPoint, null);
			Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_launcher);
			canvas.drawBitmap(bitmap, p.x - (bitmap.getWidth() / 2), p.y - (bitmap.getHeight() / 2), null);
		}
		return false;

		// 標準アイコン？
		// boolean ret = super.draw(canvas, mapView, shadow, when);
		// return ret;
	}

	@Override
	public synchronized void onSensorChanged(int sensor, float[] values) {
		super.onSensorChanged(sensor, values);
		// Log.d(getClass().getSimpleName(), "call onSensorChanged");
	}

	@Override
	public synchronized void onLocationChanged(Location location) {
		super.onLocationChanged(location);
		// Log.d(getClass().getSimpleName(), "call onLocationChanged");
		int lat = (int) (location.getLatitude() * 1E6);
		int lon = (int) (location.getLongitude() * 1E6);
		mGeoPoint = new GeoPoint(lat, lon);
	}

}