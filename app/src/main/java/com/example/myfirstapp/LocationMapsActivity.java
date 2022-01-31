package com.example.myfirstapp;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.myfirstapp.databinding.ActivityLocationMapsBinding;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.OnTokenCanceledListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;

public class LocationMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityLocationMapsBinding binding;
    private LocationManager locationManager = null;
    //private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLocationMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        ActivityResultLauncher<String[]> locationPermissionRequest =
                registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), result -> {
                    Boolean fineLocationGranted = result.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false);
                    Boolean coarseLocationGranted = result.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false);

                    if (fineLocationGranted != null && fineLocationGranted) {
                        Log.i("LocationService", "Fine Location Granted");
                    } else if (coarseLocationGranted != null && coarseLocationGranted) {
                        Log.i("LocationService", "Coarse Location Granted");
                    } else {
                        Log.e("LocationService", "Location Services Denied");
                    }
                });

        locationPermissionRequest.launch(new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        });

        //fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
/*
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Task<Location> currentLocationTask = fusedLocationClient.getCurrentLocation(LocationRequest.PRIORITY_NO_POWER, new CancellationToken() {
            @Override
            public boolean isCancellationRequested() {
                return false;
            }

            @NonNull
            @Override
            public CancellationToken onCanceledRequested(@NonNull OnTokenCanceledListener onTokenCanceledListener) {
                return null;
            }
        });

        Location currentLocation = currentLocationTask.getResult();
        double latitude = currentLocation.getLatitude();
        double longitude = currentLocation.getLongitude();

        LatLng latLng = new LatLng(latitude, longitude);
        Geocoder geocoder = new Geocoder(getApplicationContext());
        try {
            List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 1);
            String locality = addressList.get(0).getLocality();
            String country = addressList.get(0).getCountryName();
            String address = locality + ", " + country;

            mMap.addMarker(new MarkerOptions().position(latLng).title(address));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        } catch (IOException e) {
            e.printStackTrace();
        }

 */

//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
//                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//
//        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
//            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {
//                @Override
//                public void onLocationChanged(@NonNull Location location) {
//                    double latitude = location.getLatitude();
//                    double longitude = location.getLongitude();
//                    LatLng latLng = new LatLng(latitude, longitude);

//                    Geocoder geocoder = new Geocoder(getApplicationContext());
//                    try {
//                        List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 1);
//                        String locality = addressList.get(0).getLocality();
//                        String country = addressList.get(0).getCountryName();
//                        String address = locality + ", " + country;
//
//                        //mMap.addMarker(new MarkerOptions().position(latLng).title(address));
//                        //mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
        }
//        else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
//                @Override
//                public void onLocationChanged(@NonNull Location location) {
//                    double latitude = location.getLatitude();
//                    double longitude = location.getLongitude();
//                    LatLng latLng = new LatLng(latitude, longitude);
//
//                    Geocoder geocoder = new Geocoder(getApplicationContext());
//                    try {
//                        List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 1);
//                        String locality = addressList.get(0).getLocality();
//                        String country = addressList.get(0).getCountryName();
//                        String address = locality + ", " + country;
//
//                        mMap.addMarker(new MarkerOptions().position(latLng).title(address));
//                        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//        }

//    }

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
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        final double latitude = 33.75;
        final double longitude = -84.39;

        LatLng atlanta = new LatLng(latitude, longitude);

        Geocoder geocoder = new Geocoder(getApplicationContext());
        try {
            List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 1);
            String localityName = addressList.get(0).getLocality();
            String countryName = addressList.get(0).getCountryName();
            String address = localityName + ", " + countryName;

            mMap.addMarker(new MarkerOptions().position(atlanta).title(address));
//            mMap.moveCamera(CameraUpdateFactory.newLatLng(atlanta));
            float zoomLevel = 16.0f; //This goes up to 21
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(atlanta, zoomLevel));
            //mMap.animateCamera(CameraUpdateFactory.zoomIn());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}