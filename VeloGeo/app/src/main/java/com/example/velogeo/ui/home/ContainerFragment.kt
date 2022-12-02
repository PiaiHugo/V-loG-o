package com.example.velogeo.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.velogeo.R
import com.example.velogeo.models.ApiDataTransport
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.material.tabs.TabItem

class ContainerFragment : Fragment() {
    private lateinit var apiTransport: ApiDataTransport

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments.let {
            apiTransport = it?.getParcelable<ApiDataTransport>("ApiDataTransport")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_container, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val idCarte = view.findViewById(R.id.carte) as Button
        val idList = view.findViewById(R.id.liste) as Button

        idCarte.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.container, MapsFragment.newInstance(apiTransport))
                .commit()
        }
        idCarte.performClick()

        idList.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.container, FragmentList.newInstance(apiTransport))
                .commit()
        }
    }
}