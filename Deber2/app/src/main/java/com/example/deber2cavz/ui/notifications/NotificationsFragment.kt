package com.example.deber2cavz.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TabHost
import android.widget.TabHost.TabSpec
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.deber2cavz.*
import com.example.deber2cavz.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val avatarUsuario=binding.idAvatarUsuario
        Glide.with(this)
            .load("https://static-cdn.jtvnw.net/user-default-pictures-uv/41780b5a-def8-11e9-94d9-784f43822e80-profile_image-70x70.png")
            .apply(RequestOptions.circleCropTransform())
            .into(avatarUsuario)

        //Recicler categorias
        val categoriasList: RecyclerView = binding.recyclerCategorias // (1)
        val listaCategorias = arrayListOf<categorias>()
        listaCategorias.add(
            categorias("Just Chatting","439K espectadores"
                ,"IRL (vida real)"
                , R.drawable.just_chatting)
        )
        listaCategorias.add(
            categorias("Grand Theft Auto V","193,6K espectadores"
                ,"Juego de aventuras"
                , R.drawable.grand_theft_auto)
        )
        listaCategorias.add(
            categorias("Special Events","22,5K espectadores"
                ,"IRL (vida real)"
                , R.drawable.special_events)
        )
        listaCategorias.add(
            categorias("Fall Guys","3,1K espectadores"
                ,"IRL (vida real)"
                , R.drawable.fall_guys)
        )
        listaCategorias.add(
            categorias("Sports","63,9K espectadores"
                ,"IRL (vida real)"
                , R.drawable.sports)
        )
        listaCategorias.add(
            categorias("VALORANT","145,9K espectadores"
                ,"Shooter"
                , R.drawable.valorant)
        )
        listaCategorias.add(
            categorias("Fornite","100,2K espectadores"
                ,"Shooter"
                , R.drawable.fornite)
        )
        listaCategorias.add(
            categorias("Minecraft","74,2K espectadores"
                ,"Juego de Aventuras"
                , R.drawable.minecraft)
        )
        listaCategorias.add(
            categorias("League of Legends","169K espectadores"
                ,"MOBA"
                , R.drawable.league_of_legends)
        )
        listaCategorias.add(
            categorias("The Forest","3,5K espectadores"
                ,"Juego de Aventuras"
                , R.drawable.the_forest)
        )
        //Recycler
        inicializarRecyclerViewStreamers(listaCategorias,categoriasList)

        return root
    }

    fun inicializarRecyclerViewStreamers(
        lista:ArrayList<categorias>,
        recyclerView: RecyclerView,
    ){
        val adaptador = categoriasAdapter(
            this,
            lista,
            recyclerView,
            R.layout.activity_explorar
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        adaptador.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}