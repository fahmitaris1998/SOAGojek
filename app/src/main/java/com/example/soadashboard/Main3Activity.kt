package com.example.soadashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.StringRequestListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main3.*
import org.json.JSONArray

class Main3Activity : AppCompatActivity() {
    val list1 = ArrayList<datanews>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        recycler_view3.layoutManager = LinearLayoutManager(this)
        recycler_view3.setNestedScrollingEnabled(false);
        recycler_view3.setHasFixedSize(true)

        AndroidNetworking.get("https://r0jmuaauw0.execute-api.us-east-2.amazonaws.com/production/promo")
            .setPriority(Priority.HIGH)
            .build()
            .getAsString(object : StringRequestListener {
                override fun onResponse(response: String?) {

                    val result = JSONArray(response)
                    for (i in 0 until result.length()){

                        list1.add(datanews("https://giladiskon-uploads.s3-ap-southeast-1.amazonaws.com/images/deals/gojek-promo-giladiskon-promosi-637042256843941711.jpg",result.getJSONObject(i).optString("nama_promo").toString(),"Valid until "+result.getJSONObject(i).optString("valid_date").toString()))
                    }

                    recycler_view3.adapter = Adapterpromo(list1)

                }

                override fun onError(anError: ANError?) {

                }

            })
    }
}
