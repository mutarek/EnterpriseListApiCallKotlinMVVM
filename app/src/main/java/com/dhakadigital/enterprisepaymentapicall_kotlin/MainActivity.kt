package com.dhakadigital.enterprisepaymentapicall_kotlin

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.ContentLoadingProgressBar
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dhakadigital.enterprisepaymentapicall_kotlin.adaptetrs.EnterpriseListAdapters
import com.dhakadigital.enterprisepaymentapicall_kotlin.models.response.EnterpriseListResponse
import com.dhakadigital.enterprisepaymentapicall_kotlin.models.response.Payload
import com.dhakadigital.enterprisepaymentapicall_kotlin.view_model.EnterpriseViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var btnSend: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar : ProgressBar
    private lateinit var enterpriseViewModel: EnterpriseViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        enterpriseViewModel = EnterpriseViewModel()
        subscribe()
        btnSend = findViewById(R.id.btnLoadData)
        recyclerView = findViewById(R.id.enterpriseListRecyclerView)
        progressBar = findViewById(R.id.progressBar)

        btnSend.setOnClickListener {
            lifecycleScope.launch {
                enterpriseViewModel.getWeatherData();
            }
        }
    }

    private fun subscribe() {
        enterpriseViewModel.isLoading.observe(this) { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        enterpriseViewModel.isError.observe(this) { isError ->

        }

        enterpriseViewModel.weatherData.observe(this) { weatherData ->
            progressBar.visibility = View.GONE
            if (weatherData != null) {
                setResultText(weatherData)
            }
        }
    }

    private fun setResultText(enterpriseData: EnterpriseListResponse) {
       if(enterpriseData.issuccess){
           setRecyllerView(enterpriseData.payload)
       }
        else{
           Toast.makeText(this, enterpriseData.message, Toast.LENGTH_SHORT).show()
       }
    }

    private fun setRecyllerView(payload: List<Payload>) {
        val adapter = EnterpriseListAdapters(payload)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
    }
}