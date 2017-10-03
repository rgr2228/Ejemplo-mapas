package co.edu.udea.compumovil.gr02_20172.mapexample;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private static final LatLngBounds UDEA_BOUNDS = new LatLngBounds.Builder().include(new LatLng(6.264868, -75.567448))
            .include(new LatLng(6.265682, -75.571590))
            .include(new LatLng(6.270855, -75.570376))
            .include(new LatLng(6.269528, -75.566097))
            .build();

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
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true); //Consultar ubicación mientras se tengan permisos
        }
        final LatLng sydney = new LatLng(-34, 151); //Se declaran nuevas coordenadas
        LatLng udea= new LatLng(6.267721, -75.567669); //Se declaran nuevas coordenadas
        LatLng udea1= new LatLng(6.266890, -75.567758); //Se declaran nuevas coordenadas
        LatLng udea2= new LatLng(6.266935, -75.568747); //Se declaran nuevas coordenadas
        mMap.addMarker(new MarkerOptions().position(udea).title("Universidad de Antioquia").snippet("Punto 1")); //Se agrega un nuevo marcador
        mMap.addMarker(new MarkerOptions().position(udea1).title("Universidad de Antioquia").snippet("Punto 2")); //Se agrega un nuevo marcador
        mMap.addMarker(new MarkerOptions().position(udea2).title("Universidad de Antioquia").snippet("Punto 3")); //Se agrega un nuevo marcador
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(udea,10f)); //Cambiar la posición de la cámara con un determinado zoom
        mMap.getUiSettings().setZoomControlsEnabled(true); //Se habilitan los botones de zoom
        mMap.getUiSettings().setCompassEnabled(true); //Se habilita la brújula del mapa
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() { //Se agrega un evento al presionar un marcador
            @Override
            public boolean onMarkerClick(Marker marker) {
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            return true;
            }
        });
        mMap.addCircle(new CircleOptions().center(udea).fillColor(Color.GREEN).radius(15)); //Se agrega un círculo alrededor de las coordenadas dadas
        PolylineOptions camino = new PolylineOptions().add(udea,udea1,udea2);//Se crea una polilínea
        mMap.addPolyline(camino).setColor(Color.GREEN);//Se agrega en el mapa la polilínea creada
    }
}
