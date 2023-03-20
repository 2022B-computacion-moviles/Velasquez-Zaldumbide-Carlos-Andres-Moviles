package com.example.myapplication

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.app.AppCompatActivity

class RecyclerViewAdaptadorProductosCategoria(
    private val contexto: Categoria,
    private val lista: ArrayList<productos>,
    private val recyclerView: RecyclerView,
    private val actividad: Int
): RecyclerView.Adapter<RecyclerViewAdaptadorProductosCategoria.productosCategoriaViewHolder>()  {
    inner class productosCategoriaViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val imagenProducto: ImageView = view.findViewById(R.id.imagenProductoCategoria)
        val descripcionTextView: TextView = view.findViewById(R.id.descripcion)
        val precioTextView: TextView = view.findViewById(R.id.precio)
        val cantidad: TextView = view.findViewById(R.id.cantidad)
        val button: Button
        init {
            button=view.findViewById(R.id.agregar)
            //actionButton.setOnClickListener { enviar() }
        }

    }
    public var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): productosCategoriaViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(actividad, parent, false)
        return productosCategoriaViewHolder(view)
    }

    override fun onBindViewHolder(holder: productosCategoriaViewHolder, position: Int) {
        val productos = this.lista[position]
        //holder.imagenStreamerImageButton.setImageResource(streamers.imagenStreamer)
        productos.imagenProducto?.let { holder.imagenProducto.setImageResource(it) }
        holder.descripcionTextView.text=productos.descripcion
        holder.precioTextView.text=productos.precio.toString()
        holder.cantidad.text=productos.stock.toString()

        holder.button.setOnClickListener {
            listener?.onItemClick(position)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun getItemCount()=lista.size
}