package com.example.ejercicio3rrle.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ProductosApi {

    //https://www.serverbpw.com/cm/2022-2/products.php
    //https://www.serverbpw.com/cm/2022-2/product_detail.php?id=ID

    @GET
    fun getProductos(
        @Url url: String?
    ): Call<List<Producto>>

    @GET("cm/2022-2/product_detail.php")
    fun getProductoDetalles(
        @Query("id") id: String?
    ): Call<ProductoDetalles>



}