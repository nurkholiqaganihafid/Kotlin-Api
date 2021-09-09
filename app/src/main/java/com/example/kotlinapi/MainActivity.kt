package com.example.kotlinapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinapi.databinding.ActivityMainBinding
import com.example.kotlinapi.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val TAG: String = "MainActivity"

    private lateinit var binding: ActivityMainBinding

    lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

    }

    override fun onStart() {
        super.onStart()
        setupRecyclerView()
        getDataFromApi()
    }

    private fun setupRecyclerView(){
        mainAdapter = MainAdapter(arrayListOf(), object : MainAdapter.OnAdapterListener {
            override fun onClick(result: MainModel.Result) {
//                Toast.makeText(applicationContext, result.title,
//                    Toast.LENGTH_SHORT).show()

                //Membuat halaman inten/halaman berikutnya
                startActivity(
                    Intent(applicationContext,DetailActivity::class.java)
                        .putExtra("intent_title", result.title)
                        .putExtra("intent_image", result.image)
                )
            }

        })
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = mainAdapter
        }
    }

    private fun getDataFromApi(){

        //Menambahkan progress bar sambil menghilangkan progressbar nya
        binding.progressBar.visibility = View.VISIBLE

        ApiService.endpoint.getData()
            .enqueue(object : Callback<MainModel>{
                override fun onResponse(
                    call: Call<MainModel>,
                    response: Response<MainModel>
                ) {
                    binding.progressBar.visibility = View.GONE
                    if (response.isSuccessful){
                        showData( response.body()!! )
                    }
                }

                override fun onFailure(call: Call<MainModel>, t: Throwable) {
                    binding.progressBar.visibility = View.GONE
                    //printLog(t.toString())
                    printLog("onFailure: $t")
                }

            })
    }

    private fun printLog(message: String){
        Log.d(TAG, message)
    }

    private fun showData (data: MainModel) {
        val results = data.result
        mainAdapter.setData(results)
        /* for (result in results) {
            printLog("title: ${result.title}")
        } */
    }

}