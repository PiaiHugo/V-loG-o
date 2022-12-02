package com.example.velogeo.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.velogeo.R
import com.example.velogeo.models.ApiDataTransport
import com.example.velogeo.utils.Constant
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AccueilFragment : Fragment() {

    private lateinit var apiTransport: ApiDataTransport
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_accueil, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonCarte : Button=view.findViewById(R.id.Carte)
        val buttonSecu : Button=view.findViewById(R.id.button2)
        val buttonComment : Button=view.findViewById(R.id.button3)
        val queue = Volley.newRequestQueue(context)
        buttonSecu.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.securite-routiere.gouv.fr/chacun-son-mode-de-deplacement/dangers-de-la-route-velo/bien-circuler-velo"))
            startActivity(intent)

        }
        buttonComment.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.infotbm.com/fr/v3-comment-ca-marche.html"))
            startActivity(intent)

        }
        val url = String.format(Constant.URL_STATION)
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { json ->
                Log.i("JSON", "succès: $json")

                    parseJson(json)

                //Log.i("api", apiTransport.data.toString())
                //parseJson(json)
            },
            Response.ErrorListener { error ->
                val json = String(error.networkResponse.data)

                Log.i("JSON", "erreur: $json")
                //parseJson(json)
            }
        )

        queue.add(stringRequest)


        buttonCarte.setOnClickListener {
            findNavController().navigate(AccueilFragmentDirections.actionAccueilFragmentToContainerFragment(apiTransport))
        }

    }
    fun parseJson(json: String?) {
        apiTransport = Gson().fromJson(json, ApiDataTransport::class.java)

    }



}