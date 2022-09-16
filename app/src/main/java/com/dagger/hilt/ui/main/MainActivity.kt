package com.dagger.hilt.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.dagger.hilt.R
import com.dagger.hilt.databinding.ActivityMainBinding
import com.dagger.hilt.ui.adapter.PeopleAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: PeopleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initPeopleAdapter()
        binding.viewModel = mainViewModel
        mainViewModel.getPeoples()
        observePeopleLiveData()
    }

    private fun initPeopleAdapter() {
        // this creates a vertical layout Manager
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        // This will pass the ArrayList to our Adapter
        adapter = PeopleAdapter(onPeopleClickListener = {
            Toast.makeText(this, "Name: ${it.firstName}", Toast.LENGTH_LONG).show()
        })
        // Setting the Adapter with the recyclerview
        binding.recyclerview.adapter = adapter
    }

    private fun observePeopleLiveData() {
        mainViewModel.peoples.observe(this) { result ->
            if(result.isSuccess()) {
                result.data?.let { adapter.setPeoples(it) }
            }
        }
        mainViewModel.loadingVisibility.observe(this) { result ->
            when {
                result == View.GONE -> binding.progress.visibility = View.GONE
                result == View.VISIBLE -> binding.progress.visibility = View.VISIBLE
            }
        }
        mainViewModel.error.observe(this) { result ->
            if(result) {
                Toast.makeText(this, "Failed to Load", Toast.LENGTH_LONG).show()
            }
        }
    }
}