package com.example.velogeo.ui.home

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.provider.Settings.Global
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.velogeo.R
import com.example.velogeo.models.ApiCoord
import com.example.velogeo.models.ApiDataTransport
import com.example.velogeo.models.DataStations
import com.example.velogeo.utils.Constant
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.tabs.TabItem
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MapsFragment : Fragment() {
    private lateinit var apiTransport: ApiDataTransport

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        if (::apiTransport.isInitialized){
            var i=0;
            while(i<apiTransport.data.stations!!.size){


                    val tempLatLng = LatLng(apiTransport.data.stations!![i].lat, apiTransport.data.stations!![i].lon)
                googleMap.addMarker(
                    MarkerOptions().position(tempLatLng).title(apiTransport.data.stations!![i].name)

                )
                googleMap.animateCamera(CameraUpdateFactory.zoomTo(18.0f));
                googleMap.moveCamera(
                    CameraUpdateFactory.newLatLngZoom(
                        tempLatLng,
                        10F
                    ))
                i++
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments.let {
            apiTransport = it?.getParcelable<ApiDataTransport>(Constant.ARG_APITRANSPORT)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    companion object {
        @JvmStatic
        fun newInstance(apiTransport: ApiDataTransport) =
            MapsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(Constant.ARG_APITRANSPORT, apiTransport)
                }
            }
    }
}