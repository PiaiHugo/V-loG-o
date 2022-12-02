package com.example.velogeo.ui.home

import android.os.Bundle
import android.provider.ContactsContract.Data
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.example.velogeo.R
import com.example.velogeo.adapter.transportAdapter
import com.example.velogeo.models.ApiDataTransport
import com.example.velogeo.models.DataStations
import com.example.velogeo.utils.Constant


class FragmentList : Fragment() {

    private lateinit var apiTransport: ApiDataTransport
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            apiTransport = it?.getParcelable<ApiDataTransport>(Constant.ARG_APITRANSPORT)!!
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listViewData: ListView =view.findViewById(R.id.ListeStation)
        if (::apiTransport.isInitialized){

            listViewData.adapter = transportAdapter(
                requireContext(),
                R.layout.item_champ,
                apiTransport.data.stations!!
            )

        }

    }
    companion object {

        fun newInstance(apiTransport: ApiDataTransport) =
           FragmentList().apply {
                arguments = Bundle().apply {
                    putParcelable(Constant.ARG_APITRANSPORT, apiTransport)
                }
            }
    }
}