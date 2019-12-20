package com.example.customautocomplete

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cities_auto_complete_row.view.*

// https://www.androidhive.info/RxJava/android-rxjava-instant-search-local-remote-databases/
// https://www.youtube.com/watch?v=ocM1Yw_ktqM&t=4s
class AutoCompleteCountryAdapter : RecyclerView.Adapter<AutoCompleteCountryAdapter.ViewHolder>(), Filterable {

    private var cities = emptyList<String>()
    private var cityListFiltered = emptyList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cities_auto_complete_row, parent, false))

    }

    override fun getItemCount(): Int {
        return cityListFiltered.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (cityListFiltered.isNotEmpty()) {
            holder.bind(cityListFiltered[position])
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val charString = charSequence.toString()
                cityListFiltered = if (charString.isEmpty()) {
                    cities
                } else {
                    val filteredList = ArrayList<String>()
                    for (row in cities) {
                        if (row.toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row)
                        }
                    }

                    filteredList
                }

                val filterResults = FilterResults()
                filterResults.values = cityListFiltered
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence?, filterResults: FilterResults?) {
                cityListFiltered = filterResults?.values as List<String>
                notifyDataSetChanged()
            }
        }
    }

    fun setCities(cities: List<String>) {
        this.cities = cities
        this.notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(cities: String) {
            itemView.cityName.text = cities
            itemView.setOnClickListener {
                Log.d("lol", cities)
            }
        }
    }

}