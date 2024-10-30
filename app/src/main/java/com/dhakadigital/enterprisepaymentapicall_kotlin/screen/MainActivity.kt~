package com.dhakadigital.enterprisepaymentapicall_kotlin.screen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dhakadigital.enterprisepaymentapicall_kotlin.adaptetrs.EnterpriseListAdapters
import com.dhakadigital.enterprisepaymentapicall_kotlin.databinding.ActivityMainBinding
import com.dhakadigital.enterprisepaymentapicall_kotlin.models.response.EnterpriseListResponse
import com.dhakadigital.enterprisepaymentapicall_kotlin.models.response.Payload
import com.dhakadigital.enterprisepaymentapicall_kotlin.view_model.EnterpriseViewModel
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    private lateinit var enterpriseViewModel: EnterpriseViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enterpriseViewModel = EnterpriseViewModel()
        subscribe()

    }

    private fun subscribe() {
        enterpriseViewModel.isLoading.observe(this) { isLoading ->
            showShimmer(binding)
        }

        enterpriseViewModel.isError.observe(this) { isError ->

        }

        enterpriseViewModel.enterpriseListData.observe(this) { enterpriseData ->
            if (enterpriseData != null) {
                setResultText(enterpriseData)
            }
            //Loading bar
            binding.progressBar.visibility = View.GONE
            binding.progressLinear.visibility = View.GONE
            binding.enterpriseListRecyclerView.visibility = View.VISIBLE
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
        binding.enterpriseListRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.enterpriseListRecyclerView.setHasFixedSize(true)
        binding.enterpriseListRecyclerView.adapter = adapter
        hideShimmer(binding)

        adapter.onItemClick = {
            enterpriseViewModel.checkMarchantList(it.id)
            showShimmer(binding)
            enterpriseViewModel.checkMerchantData.observe(this) { checkMerchantData ->
                if (checkMerchantData != null) {
                    hideShimmer(binding)
                    if(checkMerchantData.issuccess){
                        Toast.makeText(this, checkMerchantData.statusCode.toString(), Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Toast.makeText(this, checkMerchantData.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}

fun hideShimmer(binding: ActivityMainBinding) {
    binding.shimmerView.stopShimmer()
    binding.shimmerView.visibility = View.GONE
    //dsd,v,ds
    //i dont know
    //have to work with ml kit google
}

fun showShimmer(binding: ActivityMainBinding) {
    binding.shimmerView.startShimmer()
    binding.shimmerView.visibility = View.VISIBLE

}