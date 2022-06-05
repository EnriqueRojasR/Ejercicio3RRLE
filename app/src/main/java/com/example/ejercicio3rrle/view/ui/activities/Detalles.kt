package com.example.ejercicio3rrle.view.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.ejercicio3rrle.databinding.ActivityDetalllesBinding
import com.example.ejercicio3rrle.model.ProductoDetalles
import com.example.ejercicio3rrle.model.ProductosApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Detalles : AppCompatActivity() {
    private lateinit var  binding :ActivityDetalllesBinding

    private val BASE_URL = "https://www.serverbpw.com/"
    private val LOGTAG = "LOGS"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalllesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras

        val id = bundle?.getString("id", "0")

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val productosApi: ProductosApi = retrofit.create(ProductosApi::class.java)

        val call: Call<ProductoDetalles> = productosApi.getProductoDetalles(id)

        call.enqueue(object: Callback<ProductoDetalles>{
            override fun onResponse(
                call: Call<ProductoDetalles>,
                response: Response<ProductoDetalles>
            ) {
                with(binding){
                    pbConexion.visibility= View.INVISIBLE

                    tvTitle.text = response.body()?.name

                    Glide.with(this@Detalles)
                        .load(response.body()?.ima_ul)
                        .into(ivImage)

                    tvLongDesc.text = response.body()?.desc
                }
            }

            override fun onFailure(call: Call<ProductoDetalles>, t: Throwable) {
                Log.d(LOGTAG, "Error")
                binding.pbConexion.visibility = View.INVISIBLE
                Toast.makeText(this@Detalles, "No hay conexion", Toast.LENGTH_LONG).show()
            }

        })


    }
}