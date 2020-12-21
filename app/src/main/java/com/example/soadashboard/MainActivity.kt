package com.example.soadashboard

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.androidnetworking.interfaces.StringRequestListener
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    private val TAG = "MyActivity"
    val list1 = ArrayList<datanews>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//       val exampleList = generateDummyList(3)
        //recycler_view.adapter = ExampleAdapter(exampleList)

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setNestedScrollingEnabled(false);
        recycler_view.setHasFixedSize(true)

        recycler_view2.layoutManager = LinearLayoutManager(this,OrientationHelper.HORIZONTAL,false);
        recycler_view2.setNestedScrollingEnabled(false);
        recycler_view2.setHasFixedSize(true)
        getnews()

        topupgopay.setOnClickListener(View.OnClickListener { view ->
            val intent = Intent(this,MainActivityTopUp::class.java)
            startActivity(intent)
        })

        AndroidNetworking.get("https://r0jmuaauw0.execute-api.us-east-2.amazonaws.com/production/gopay")
            .setPriority(Priority.HIGH)
            .build()
            .getAsString(object : StringRequestListener{
                override fun onResponse(response: String?) {

                    val result = JSONArray(response)
                    val balance = "Rp" + result.getJSONObject(1).optString("saldo_gopay").toString()
                    tv_balance.setText(balance)
                }

                override fun onError(anError: ANError?) {

                }

            })
        promo.setOnClickListener(View.OnClickListener { view ->
            val intent = Intent(this,Main3Activity::class.java)
            startActivity(intent)
            finish()
        })


    }

//    private fun generateDummyList(size: Int): List<datanews> {
//
//        val list = ArrayList<datanews>()
//
//        for (i in 0 until size) {
//
//            val item = datanews(R.drawable.gopay, "Tenang Belanja #dirumahaja", "Bersama GoFood, Gomart dan Goshop, gunakan opsi pengantaran tanpa kontak fisik")
//            list += item
//        }
//
//        return list
//    }

    private fun getnews(){
        AndroidNetworking.initialize(this);

        AndroidNetworking.get("https://r0jmuaauw0.execute-api.us-east-2.amazonaws.com/production/news")
            .setPriority(Priority.HIGH)
            .build()
            .getAsString(object : StringRequestListener{
                override fun onResponse(response: String?) {

                    val result = JSONArray(response)
                    for (i in 0 until result.length()){
                        val content = result.getJSONObject(i).optString("content").toString().substring(0,100) + "..."
                        list1.add(datanews(result.getJSONObject(i).optString("src_img").toString(),result.getJSONObject(i).optString("title_news").toString(),content))
                    }

                    recycler_view.adapter = ExampleAdapter(list1)
                    recycler_view2.adapter = Adapternews(list1)

                }

                override fun onError(anError: ANError?) {

                }

            })
    }

}
