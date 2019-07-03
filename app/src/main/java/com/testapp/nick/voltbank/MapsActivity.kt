package com.testapp.nick.voltbank

import android.app.ProgressDialog
import android.location.Location
import android.location.LocationListener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.common.ConnectionResult

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.testapp.nick.voltbank.utils.NetworkCheck
import com.google.android.gms.maps.UiSettings
import com.testapp.nick.voltbank.Model.PoliceDataModel


class MapsActivity : AppCompatActivity(), OnMapReadyCallback , LocationListener{

    private lateinit var mMap: GoogleMap
    lateinit var policeDataViewModel : PoliceDataViewModel
    lateinit var progressDialog : ProgressDialog
    lateinit var mUiSettings: UiSettings
    private val DEFAULT_LONGITUDE = -1.131592
    private val DEFAULT_LATITUDE = 52.629729

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        progressDialog = NetworkCheck.getProgressDialog(
            this,
            "Please wait..."
        )

        policeDataViewModel = ViewModelProviders.of(this).get(PoliceDataViewModel::class.java)

        if(NetworkCheck.isInternetConnected(this)
            && NetworkCheck.isGoogleAvaliable(this) == ConnectionResult.SUCCESS
        )
        {
            policeDataViewModel.getPoliceCrimeDataFromAPI(
                "2017-02",
                "52.629729",
                "-1.131592"
            )
        }
        else
        {
            Toast.makeText(this,"No internet found", Toast.LENGTH_LONG).show()
        }


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
            mapFragment.getMapAsync(this)

        policeDataViewModel.getAllCrimesList().observe(this, Observer<List<PoliceDataModel>> { policeDataList ->
            addMarkerOnMap(policeDataList)
        })
    }

    fun addMarkerOnMap(policeDataList: List<PoliceDataModel>) {
       /* for(policeData in policeDataList) {
            mMap.addMarker(MarkerOptions().position(LatLng(DEFAULT_LATITUDE, DEFAULT_LONGITUDE)).title("Marker in London"))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(unitedKingdom))
        }*/
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
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mUiSettings = mMap.getUiSettings();
        // Add a marker in Sydney and move the camera
        val unitedKingdom = LatLng(DEFAULT_LATITUDE, DEFAULT_LONGITUDE)
        mMap.addMarker(MarkerOptions().position(unitedKingdom).title("Marker in London"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(unitedKingdom))

        mUiSettings.setScrollGesturesEnabled(true)
        mUiSettings.setZoomGesturesEnabled(true)
        mUiSettings.setZoomControlsEnabled(true)
    }

    override fun onLocationChanged(location: Location?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onProviderEnabled(provider: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onProviderDisabled(provider: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
