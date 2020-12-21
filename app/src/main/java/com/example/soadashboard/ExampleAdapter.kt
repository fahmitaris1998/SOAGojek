package com.example.soadashboard
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.example_item.view.*


class ExampleAdapter(private val exampleList: List<datanews>) :
    RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder>() {
    private val context: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.example_item,
            parent, false)

        return ExampleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = exampleList[position]

        Picasso.with(context).
            load(currentItem.imageResource)
            .into(holder.imageView)
        holder.textView1.text = currentItem.text1
        holder.textView2.text = currentItem.text2
    }

    override fun getItemCount() = exampleList.size

    class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.image_news
        val textView1: TextView = itemView.judul
        val textView2: TextView = itemView.konten
    }
}
