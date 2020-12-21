package com.example.soadashboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.androidnetworking.interfaces.StringRequestListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_top_up.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class MainActivityTopUp : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_top_up)

        AndroidNetworking.get("https://r0jmuaauw0.execute-api.us-east-2.amazonaws.com/production/gopay")
            .setPriority(Priority.HIGH)
            .build()
            .getAsString(object : StringRequestListener {
                override fun onResponse(response: String?) {

                    val result = JSONArray(response)

                    var balance = result.getJSONObject(1).optString("saldo_gopay").toString()

                    temp.setText(balance)

                }

                override fun onError(anError: ANError?) {

                }

            })

        lanjut.setOnClickListener(View.OnClickListener { view ->

            if (nominal.text.toString() != ""){

                var totalsaldo = nominal.text.toString().toInt() + temp.text.toString().toInt()

                val jsonObject = JSONObject()
                try {
                    jsonObject.put("saldo_paylater", "10000")
                    jsonObject.put("id", "gopay_3")
                    jsonObject.put("email", "samsul@gmail.com")
                    jsonObject.put("saldo_gopay", totalsaldo.toString())
                    jsonObject.put("pin", "1111")


                } catch (e: JSONException) {
                    e.printStackTrace()
                }
                AndroidNetworking.post("https://r0jmuaauw0.execute-api.us-east-2.amazonaws.com/production/gopay")
                    .addJSONObjectBody(jsonObject)
                    .setTag("test")
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(object : JSONObjectRequestListener {
                        override fun onResponse(response: JSONObject) { // do anything with response
                        }

                        override fun onError(error: ANError) { // handle error
                        }
                    })
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }

        })



    }
}
