package com.example.deber2cavz

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet.Layout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.deber2cavz.ui.home.HomeFragment

class streamersAdapter(
    private val contexto: HomeFragment,
    private val lista: ArrayList<streamers>,
    private val recyclerView: RecyclerView,
    private val actividad: Int
): RecyclerView.Adapter<streamersAdapter.streamersViewHolder>(){
    inner class streamersViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val imagenStreamImageButton:ImageView = view.findViewById(R.id.captura_video)
        val imagenStreamerImageButton:ImageView = view.findViewById(R.id.imagen_streamer)
        val nombreTextView: TextView = view.findViewById(R.id.tv_nombre_streamer)
        val titleTextView: TextView = view.findViewById(R.id.tv_tituloStream)
        val categoriaTextView: TextView = view.findViewById(R.id.tv_categoria)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): streamersViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(actividad, parent, false)
        return streamersViewHolder(view)
    }

    override fun onBindViewHolder(holder: streamersViewHolder, position: Int) {
        val streamers = this.lista[position]
        Glide.with(contexto).load(streamers.imagenStreamer)
            .apply(RequestOptions.circleCropTransform())
            .into(holder.imagenStreamerImageButton)
        holder.imagenStreamImageButton.setImageResource(streamers.imagenStream)
        //holder.imagenStreamerImageButton.setImageResource(streamers.imagenStreamer)
        holder.nombreTextView.text=streamers.nombre
        holder.titleTextView.text=streamers.tituloStream
        holder.categoriaTextView.text=streamers.categoria
    }

    override fun getItemCount()=lista.size
}