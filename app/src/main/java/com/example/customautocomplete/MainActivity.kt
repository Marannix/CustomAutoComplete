package com.example.customautocomplete

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var city = emptyList<String>()
    private val adapter = AutoCompleteCountryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // remove background
        autoCompleteTextView.setBackgroundResource(0)
        createList()
        setAdapter()

        adapter.setCities(city)

        autoCompleteTextView.addTextChangedListener {
            adapter.filter.filter(it.toString())
        }
    }

    private fun createList() {
        city = resources.getStringArray(R.array.countries_array).toList()
    }

    private fun setAdapter() {
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.adapter = adapter
    }

}
