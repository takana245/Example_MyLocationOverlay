package name.takana245.examplemylocationoverlay;

import java.util.List;

import android.os.Bundle;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class ExampleMyLocationOverlayActivity extends MapActivity {

	private MapView mMapView;
	private SampleOverlay mOverlay;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mMapView = (MapView) findViewById(R.id.myMap);
		mMapView.setEnabled(true);
		mMapView.setClickable(true);
		mMapView.setBuiltInZoomControls(true);
		mMapView.setSatellite(false);

		mOverlay = new SampleOverlay(this, mMapView);
		mOverlay.enableMyLocation();

		List<Overlay> list = mMapView.getOverlays();
		list.add(mOverlay);

	}

	@Override
	protected void onDestroy() {
		mOverlay.disableMyLocation();
		super.onDestroy();
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}