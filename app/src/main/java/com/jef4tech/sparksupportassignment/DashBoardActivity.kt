package com.jef4tech.sparksupportassignment

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jef4tech.sparksupportassignment.databinding.ActivityDashBoardBinding
import com.jef4tech.sparksupportassignment.ui.adapters.DashBoardAdapter
import com.jef4tech.sparksupportassignment.utils.Extensions
import com.jef4tech.sparksupportassignment.viewModel.DashBoardViewModel

class DashBoardActivity : AppCompatActivity() {
    private var uiState:Boolean=false
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
        val name = sharedPref.getString("firstname","")+" "+sharedPref.getString("lastname","")
        val email =  sharedPref.getString("email","")
        binding.edUserName.text=name
        binding.editUserMail.text=email
        access?.let { dashBoardViewModel.getDashBoardData(it) }
        setupRecyclerView()
        setupUI()
        delayUi()
        if (uiState){
            binding.recyclerview.visibility= View.GONE
            binding.userView.visibility =  View.VISIBLE
        }

//        Log.i("vlog", "onCreate: $name")
//        sharedPref.getString("lastname","")
//        sharedPref.getString("username","")
//        sharedPref.getString("email","")
//        sharedPref.getString("access","")
//        sharedPref.getString("refresh","")
//        binding.textView2.text=name
        binding.logout.setOnClickListener {
            logout()
        }
        dashBoardViewModel.errorMessage.observe(this, androidx.lifecycle.Observer { it ->

            if(it!=null){
                Extensions.alertDialog(this,it,"ERROR MESSAGE")
            }
        })

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
    private fun logout(){
        val preferences = getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        val editor = preferences.edit()
        editor.clear()
        editor.apply()
        finish()
    }
    private fun delayUi() {
        val handler = Handler()
        handler.postDelayed(Runnable {  binding.recyclerview.visibility= View.GONE
            binding.userView.visibility =  View.VISIBLE
                                     }, 4000)
    }
}
