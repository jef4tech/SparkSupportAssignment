package com.jef4tech.sparksupportassignment

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jef4tech.sparksupportassignment.databinding.ActivityDashBoardBinding
import com.jef4tech.sparksupportassignment.ui.adapters.DashBoardAdapter
import com.jef4tech.sparksupportassignment.viewModel.DashBoardViewModel

class DashBoardActivity : AppCompatActivity() {
    private var PRIVATE_MODE = 0
    private val TAG="DashBoardActivity11"
    private val PREF_NAME = "user"
    private lateinit var binding: ActivityDashBoardBinding
    private lateinit var dashBoardViewModel: DashBoardViewModel
    private lateinit var dashBoardAdapter: DashBoardAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dashBoardViewModel = ViewModelProvider(this)[DashBoardViewModel::class.java]
        val sharedPref: SharedPreferences = getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        binding = ActivityDashBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val access = sharedPref.getString("access","")
        val name = sharedPref.getString("firstname","")
        binding.textView.text=name
        access?.let { dashBoardViewModel.getDashBoardData(it) }
        setupRecyclerView()
        setupUI()
//        Log.i("vlog", "onCreate: $name")
//        sharedPref.getString("lastname","")
//        sharedPref.getString("username","")
//        sharedPref.getString("email","")
//        sharedPref.getString("access","")
//        sharedPref.getString("refresh","")
//        binding.textView2.text=name




    }

    private fun setupUI() {
        //observe the list and update the recycler view
                dashBoardViewModel.dashBoardData.observe(this, androidx.lifecycle.Observer {

                    if(it!=null){ dashBoardAdapter.setData(it) }
                })
    }

    //setup recycler view
    private fun setupRecyclerView()=binding.recyclerview.apply {
        dashBoardAdapter= DashBoardAdapter()
        adapter=dashBoardAdapter
        layoutManager= LinearLayoutManager(context)
        setHasFixedSize(true)
        addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }
}