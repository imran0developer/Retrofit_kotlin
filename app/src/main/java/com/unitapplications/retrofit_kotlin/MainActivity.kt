package com.unitapplications.retrofit_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import retrofit2.Response
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView : TextView = findViewById(R.id.textView)
        val apiSet : ApiSet = ApiClient
            .getClient()
            .create(ApiSet::class.java)
        val responseLiveData : LiveData<Response<AlbumModel>> = liveData {
            val response:Response<AlbumModel> = apiSet.getAlbum()
                emit(response)
        }
        responseLiveData.observe(this, Observer {
            val responses :MutableListIterator<AlbumModelItem>? = it.body()?.listIterator()
            if (responses!=null){
                while (responses.hasNext()){
                    val  albumModelItem :AlbumModelItem = responses.next()
                    Log.d("res1",albumModelItem.title)
                    val result: String = " "+ albumModelItem.title+" \n"
                    textView.append(result)
                }
            }
        })

    }
}