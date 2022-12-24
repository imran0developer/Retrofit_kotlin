package com.unitapplications.retrofit_kotlin

import retrofit2.Response
import retrofit2.http.GET

interface ApiSet {
    @GET("/albums")
    suspend fun getAlbum():Response<AlbumModel>

}