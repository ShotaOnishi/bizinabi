package com.example.apple.bizinabi.Activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.PermissionChecker;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.apple.bizinabi.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nifty.cloud.mb.core.DoneCallback;
import com.nifty.cloud.mb.core.FindCallback;
import com.nifty.cloud.mb.core.NCMBException;
import com.nifty.cloud.mb.core.NCMBObject;
import com.nifty.cloud.mb.core.NCMBQuery;

import java.util.List;

import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by apple on 2017/10/21.
 */

public class MapFragment extends Fragment implements OnMapReadyCallback, ActivityCompat.OnRequestPermissionsResultCallback, LocationListener {
    private final static String BACKGROUND_COLOR = "background_color";

    private static final int MY_LOCATION_REQUEST_CODE = 1000;
    private GoogleMap mMap;
    private LocationManager myLocationManager;

    public static MapFragment newInstance(@ColorRes int IdRes) {
        MapFragment frag = new MapFragment();
        Bundle b = new Bundle();
        b.putInt(BACKGROUND_COLOR, IdRes);
        frag.setArguments(b);
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(com.example.apple.bizinabi.R.layout.fragment_map, null);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.fragmet_map);

        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
//        SupportMapFragment mapFragment = (SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
        linearLayout.setBackgroundResource(getArguments().getInt(BACKGROUND_COLOR));
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (PermissionChecker.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            myLocationManager = (LocationManager) getContext().getSystemService(LOCATION_SERVICE);
            String provider = getProvider();
            Location lastLocation = myLocationManager.getLastKnownLocation(provider);
            if (lastLocation != null) {
                setLocation(lastLocation);
            }
            mMap.setMyLocationEnabled(true);
            Toast.makeText(getContext(), "Provider=" + provider, Toast.LENGTH_SHORT).show();
            myLocationManager.requestLocationUpdates(provider, 0, 0, this);

            //美人スポットにピンを立てる処理
            LatLng kanazwaw_station = new LatLng(36.578057, 136.64866);
            googleMap.addMarker(new MarkerOptions()
                    .position(kanazwaw_station)
                    .title("This is Bijin Spot!")
                    .snippet("可愛い 15人\n美人 40人\n着物 30人")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

            LatLng kenrokuenn = new LatLng(36.562128, 136.662652);
            googleMap.addMarker(new MarkerOptions()
                    .position(kenrokuenn)
                    .title("This is Bijin Spot!")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

            LatLng ohmityo_itiba = new LatLng(36.565689, 136.6597);
            googleMap.addMarker(new MarkerOptions()
                    .position(ohmityo_itiba)
                    .title("This is Bijin Spot!")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

            LatLng kanazawa_center_post_office = new LatLng(36.571332, 136.645497);
            googleMap.addMarker(new MarkerOptions()
                    .position(kanazawa_center_post_office)
                    .title("This is Bijin Spot!")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

            LatLng oyama_shrine = new LatLng(36.566073, 136.655425);
            googleMap.addMarker(new MarkerOptions()
                    .position(oyama_shrine)
                    .title("This is Bijin Spot!")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

            LatLng ozaki_shrine = new LatLng(36.569233, 136.657471);
            googleMap.addMarker(new MarkerOptions()
                    .position(ozaki_shrine)
                    .title("This is Bijin Spot!")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

            getGeoData();


        } else {
            setDefaultLocation();
            confirmPermission();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == MY_LOCATION_REQUEST_CODE) {
            // よくサンプルコードでは以下のように引数でパーミッションチェックしています。
            //if (permissions[0].equals(Manifest.permission.ACCESS_FINE_LOCATION) &&
            //        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // 一方、パーミッションの許可ダイアログで「許可」された場合、このコールバックメソッド以降で現在位置の取得処理を
            // 行う必要があります。
            // 現在位置の取得はrequestLocationUpdatesを実行する必要がありますが、パーミッションチェックをやれとエラーが出ます。
            // そこで、このメソッドに到達した時点ではすでにパーミッションが許可/拒否されていますので、引数でなくとも
            // heckSelfPermissionを実行すればエラーも解消されますし良いかなと思って、以下のようにしています。
            if (PermissionChecker.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                mMap.setMyLocationEnabled(true);
                myLocationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
                myLocationManager.requestLocationUpdates(getProvider(), 0, 0, this);
            } else {
                Toast.makeText(getContext(), "権限を取得できませんでした。", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        Toast.makeText(getContext(), "LocationChanged実行", Toast.LENGTH_SHORT).show();
        setLocation(location);
        try {
            myLocationManager.removeUpdates(this);
        } catch (SecurityException e) {
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    private String getProvider() {
        Criteria criteria = new Criteria();
        return myLocationManager.getBestProvider(criteria, true);
    }

    private void confirmPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION)) {
            new AlertDialog.Builder(getContext()).setTitle("パーミッション説明")
                    .setMessage("このアプリを実行するには位置情報の権限を与えてやる必要です。よろしくお願い致します。")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // trueもfalseも結局同じrequestPermissionsを実行しているので一つにまとめるべきかも
                            ActivityCompat.requestPermissions(getActivity(),
                                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                                    MY_LOCATION_REQUEST_CODE);
                        }
                    })
                    .create()
                    .show();
        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_LOCATION_REQUEST_CODE);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            myLocationManager.removeUpdates(this);
        } catch (SecurityException e) {
            // removeUpdatesを使用する場合もパーミッションチェックをするか、このようにSecurityExceptionをキャッチする対応が必要です。
            // onRequestPermissionsResultでパーミッションチェックを例にしたのでこちらはSecurityExceptionで対応します。
            // 何もしてませんが、本当は例外に応じた後続処理を書く必要があります。
        }
    }


    private void setDefaultLocation() {
        LatLng tokyo = new LatLng(35.681298, 139.766247);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tokyo, 10));
    }

    private void setLocation(Location location) {
        LatLng myLocation = new LatLng(location.getLatitude(), location.getLongitude());
//        mMap.addMarker(new MarkerOptions().position(myLocation).title("now Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 14));
        SharedPreferences data = getActivity().getSharedPreferences("DataSave", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = data.edit();
        editor.putString("Location_latitude", String.valueOf(myLocation.latitude));
        editor.putString("Location_longitude", String.valueOf(myLocation.longitude));
        editor.apply();

    }


    public void getGeoData(){
        NCMBQuery<NCMBObject> query = new NCMBQuery<>("Spot");
        Location southwest = new Location("location");
        southwest.setLatitude(20.2531);
        southwest.setLongitude(122.5601);
        Location northeast = new Location("location");
        northeast.setLatitude(45.3326);
        northeast.setLongitude(153.5911);
        query.whereWithinGeoBox("geo", southwest, northeast);
        query.findInBackground(new FindCallback<NCMBObject>() {
            @Override
            public void done(List<NCMBObject> results, NCMBException e) {
                if (e != null) {
                    //検索失敗時の処理
                } else {
                    //検索成功時の処理
                    for(NCMBObject spot : results) {
                        // Type取得
                        Log.d("Main", "type:" + spot.getInt("type"));
                        // 緯度軽度取得
                        Location geo = spot.getGeolocation("geo");
                        Log.d("Main", geo.getLatitude() + " " + geo.getLongitude());
                        float coror = 0;
                        switch (spot.getInt("type")){
                            case 0:
                                coror = BitmapDescriptorFactory.HUE_BLUE;
                                break;
                            case 1:
                                coror = BitmapDescriptorFactory.HUE_RED;
                                break;
                            case 2:
                                coror = BitmapDescriptorFactory.HUE_GREEN;
                                break;
                        }

                        LatLng ozaki_shrine = new LatLng(geo.getLatitude(), geo.getLongitude());
                        mMap.addMarker(new MarkerOptions()
                                .position(ozaki_shrine)
                                .title("This is Saved Spot!")
                                .icon(BitmapDescriptorFactory.defaultMarker(coror)));

                    }
                }
            }
        });
    }
}
