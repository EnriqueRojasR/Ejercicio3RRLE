package com.example.ejercicio3rrle.model

import com.google.gson.annotations.SerializedName

class ProductoDetalles {

    @SerializedName("name")
    var name: String? = null

    @SerializedName("imag_url")
    var ima_ul: String? = null

    @SerializedName("desc")
    var desc: String? = null

}