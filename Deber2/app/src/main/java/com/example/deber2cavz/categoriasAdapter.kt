package com.example.deber2cavz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.deber2cavz.ui.notifications.NotificationsFragment

class categoriasAdapter(
    private val contexto: NotificationsFragment,
    private val lista: ArrayList<categorias>,
    private val recyclerView: RecyclerView,
    private val actividad: Int
): RecyclerView.Adapter<categoriasAdapter.categoriasViewHolder>() {
    inner class categoriasViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val imagenCategoriaImagenView: ImageView = view.findViewById(R.id.captura_videoR)
        val categoriaExTextView: TextView = view.findViewById(R.id.tv_categoriaEx)
        val espectadoresTextView: TextView = view.findViewById(R.id.tv_espectadores)
        val subcategoriaButton: Button = view.findViewById(R.id.btn_subCategoria)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): categoriasViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(actividad, parent, false)
        return categoriasViewHolder(view)
    }

    override fun onBindViewHolder(holder: categoriasViewHolder, position: Int) {
        val categorias = this.lista[position]
        holder.imagenCategoriaImagenView.setImageResource(categorias.imagenCategoria)
        holder.categoriaExTextView.text=categorias.categoria
        holder.espectadoresTextView.text=categorias.espectadores
        holder.subcategoriaButton.text=categorias.subcategoria
    }

    override fun getItemCount()=lista.size
}