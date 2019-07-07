package com.testapp.nick.voltbank

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.arlib.floatingsearchview.FloatingSearchView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.UiSettings
import com.testapp.nick.voltbank.Model.PoliceDataModel
import android.content.Context
import com.google.android.gms.maps.model.CameraPosition
import com.testapp.nick.voltbank.viewModel.PoliceDataViewModel
import com.whiteelephant.monthpicker.MonthPickerDialog
import java.util.*


class MapsActivity : AppCompatActivity(), OnMapReadyCallback,
    GoogleMap.OnCameraMoveStartedListener,
    GoogleMap.OnCameraMoveListener,
    GoogleMap.OnCameraMoveCanceledListener,
    GoogleMap.OnCameraIdleListener {


    private lateinit var mMap: GoogleMap
    lateinit var policeDataViewModel: PoliceDataViewModel
    lateinit var mUiSettings: UiSettings
    private val DEFAULT_LONGITUDE = -1.131592
    private val DEFAULT_LATITUDE = 52.629729
    lateinit var searchMenu: FloatingSearchView

    private var date: String = "2018-07"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)


        searchMenu = findViewById(R.id.floating_search_view)

        policeDataViewModel = ViewModelProviders.of(this).get(PoliceDataViewModel::class.java)
        setupSearchBar(this)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        policeDataViewModel.getAllCrimesList().observe(this, Observer<List<PoliceDataModel>> { policeDataList ->
            addMarkerOnMap(policeDataList)
        })
    }


    private fun setupSearchBar(context: Context) {
        searchMenu.setOnFocusChangeListener(object : FloatingSearchView.OnFocusChangeListener {
            val today = Calendar.getInstance()
            override fun onFocusCleared() {

            }

            override fun onFocus() {
                var builder = MonthPickerDialog.Builder(context, object : MonthPickerDialog.OnDateSetListener {
                    override fun onDateSet(selectedMonth: Int, selectedYear: Int) {
                        date = selectedYear.toString() + "-" + selectedMonth.toString()
                        searchMenu.setSearchText(date)
                    }
                }, today.get(Calendar.YEAR), today.get(Calendar.MONTH))

                builder.setActivatedMonth(Calendar.JULY)
                    .setMinYear(1990)
                    .setActivatedYear(2017)
                    .setMaxYear(2030)
                    .setMinMonth(Calendar.FEBRUARY)
                    .setTitle("Select trading month")
                    .setMonthRange(Calendar.FEBRUARY, Calendar.NOVEMBER)
                    .setOnMonthChangedListener { selectedMonth -> }
                    .setOnYearChangedListener { selectedYear -> }
                    .build()
                    .show()

            }
        })
    }

    fun addMarkerOnMap(policeDataList: List<PoliceDataModel>) {
        for (policeData in policeDataList) {
            mMap.addMarker(
                MarkerOptions().position(
                    LatLng(
                        policeData.location.latitude.toDouble(),
                        policeData.location.longitude.toDouble()
                    )
                ).title(
                    policeData.category
                )
            )
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mUiSettings = mMap.getUiSettings();
        mMap.moveCamera(
            CameraUpdateFactory.newLatLng(
                LatLng(DEFAULT_LATITUDE, DEFAULT_LONGITUDE)
            )
        )
        mMap.animateCamera(
            CameraUpdateFactory.newCameraPosition(
                CameraPosition.Builder()
                    .target(LatLng(DEFAULT_LATITUDE, DEFAULT_LONGITUDE))
                    .zoom(17f).build()
            )
        )
        mUiSettings.setScrollGesturesEnabled(true)
        mUiSettings.setZoomGesturesEnabled(true)
        mUiSettings.setZoomControlsEnabled(true)
        mMap.setOnCameraIdleListener(this)
        mMap.setOnCameraMoveStartedListener(this)
        mMap.setOnCameraMoveListener(this)
        mMap.setOnCameraMoveCanceledListener(this)

    }

    override fun onCameraMoveStarted(p0: Int) {

    }

    override fun onCameraMove() {

    }

    override fun onCameraMoveCanceled() {

    }

    override fun onCameraIdle() {
        policeDataViewModel.getPoliceCrimeDataFromAPI(
            date,
            mMap.cameraPosition.target.latitude,
            mMap.cameraPosition.target.longitude
        )
    }
}
