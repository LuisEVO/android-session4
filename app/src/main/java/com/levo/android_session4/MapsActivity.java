package com.levo.android_session4;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;
        CustomInfoWindowAdapter customInfoWindowAdapter = new CustomInfoWindowAdapter(this);
        mMap.setInfoWindowAdapter(customInfoWindowAdapter);

        /*

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);

        */
        // mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(cibertecMiraflores, 20));
        // mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(cibertecSanIsidro, 15));

        // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cibertecMiraflores, 20));
        // mMap.animateCamera(CameraUpdateFactory.zoomIn());
        // mMap.animateCamera(CameraUpdateFactory.zoomTo(13), 3000, null);

        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {

                LatLng cibertecMiraflores = new LatLng(-12.1222648,-77.0304797);
                LatLng cibertecSanIsidro = new LatLng(-12.1041327,-77.0472384);
                mMap.addMarker(new MarkerOptions()
                        .position(cibertecMiraflores)
                        .title("Cibertec")
                        .snippet("Sede Miraflores")
                        .draggable(true)
                );

                mMap.addMarker(new MarkerOptions()
                        .position(cibertecSanIsidro)
                        .title("Cibertec")
                        .snippet("Sede San Isidro")
                        .draggable(true)
                        //.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.i_marker)));

                LatLngBounds latLngBounds = new LatLngBounds.Builder()
                        .include(cibertecMiraflores)
                        .include(cibertecSanIsidro)
                        .build();

                mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 5));

                mMap.addPolyline(new PolylineOptions()
                        .add(cibertecMiraflores, cibertecSanIsidro)
                        .width(5)
                        .color(Color.GREEN));


                mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
                    @Override
                    public void onMarkerDragStart(Marker marker) {

                    }

                    @Override
                    public void onMarkerDrag(Marker marker) {

                    }

                    @Override
                    public void onMarkerDragEnd(Marker marker) {
                        Toast.makeText(MapsActivity.this, "Lat: " + marker.getPosition().latitude + "Long: " + marker.getPosition().longitude, Toast.LENGTH_LONG).show();

                    }
                });
            }
        });
    }
}
