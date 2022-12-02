package com.example.velogeo.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.velogeo.R
import com.example.velogeo.models.ApiDataTransport
import com.example.velogeo.models.DataStations

class transportAdapter(context: Context, resource: Int, objects: Array<DataStations>)
    : ArrayAdapter<DataStations>(context, resource, objects)
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var convertView = convertView
        val myViewHolder: ViewHolder // déclaratiotn

        if(convertView == null) {
            Log.e("RestaurantAdapter", "convertView IF")
            // afficher le layout item_restaurant*/
            convertView = LayoutInflater.from(context).inflate(R.layout.item_champ, null)

            myViewHolder = ViewHolder() // instance

            myViewHolder.nom = convertView!!.findViewById(R.id.textView)
            myViewHolder.capacite = convertView!!.findViewById(R.id.textView2)
            myViewHolder.codePostal = convertView!!.findViewById(R.id.textView3)
            // myViewHolder.textViewCategory = convertView.findViewById(R.id.textViewCategory)

            // enregistrement de l'instance ViewHolder
            // convertView.setTag(myViewHolder) // en java
            convertView.tag = myViewHolder // en kotlin
        } else {
            myViewHolder = convertView.tag as ViewHolder
            Log.e("RestaurantAdapter", "convertView ELSE")
        }

        // afficher les données dans textViewTitle et textViewCategory
        val item/*: Restaurant*/ = getItem(position)/* as Restaurant*/

        myViewHolder.nom?.setText("Nom de la station : " + item!!.name)

        myViewHolder.capacite?.setText("Nombre de vélo : " + item!!.capacity.toString())
        myViewHolder.codePostal?.setText("Code Postal : " + item!!.post_code)

        //myViewHolder.textViewCategory?.setText(item!!.category)

        return convertView
    }

    class ViewHolder {
        var nom: TextView? = null
        var capacite: TextView? = null
        var codePostal: TextView? = null
        // var textViewCategory: TextView? = null
    }
}