package com.example.deber2cavz.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.deber2cavz.*
import com.example.deber2cavz.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val avatarUsuario=binding.idAvatarUsuario
        Glide.with(this)
            .load("https://static-cdn.jtvnw.net/user-default-pictures-uv/41780b5a-def8-11e9-94d9-784f43822e80-profile_image-70x70.png")
            .apply(RequestOptions.circleCropTransform())
            .into(avatarUsuario)

        //Recicler streamers
        val streamsList: RecyclerView = binding.recyclerTusCanales // (1)
        val listaStreamers = arrayListOf<streamers>()
        listaStreamers.add(
            streamers("ibai","Is simply dummy text..."
            ,"Just Chatting"
            ,R.drawable.diapositiva1
            ,"https://static-cdn.jtvnw.net/jtv_user_pictures/574228be-01ef-4eab-bc0e-a4f6b68bedba-profile_image-150x150.png")
        )
        listaStreamers.add(
            streamers("Perxitaa","Is simply dummy text..."
            ,"Just Chatting"
            ,R.drawable.diapositiva2
            ,"https://static-cdn.jtvnw.net/jtv_user_pictures/6213a284-c133-41cf-ad99-ebef738eb550-profile_image-70x70.png")
        )
        listaStreamers.add(
            streamers("Reborn_Live","Is simply dummy text..."
                ,"Just Chatting"
                ,R.drawable.diapositiva3
                ,"https://static-cdn.jtvnw.net/jtv_user_pictures/7075a754-4779-4ae5-bd3e-3bde4cd844f4-profile_image-70x70.png")
        )
        listaStreamers.add(
            streamers("auronplay","Is simply dummy text..."
                ,"Just Chatting"
                ,R.drawable.diapositiva4
                ,"https://static-cdn.jtvnw.net/jtv_user_pictures/bf1d52e3-b46b-4c07-9b4f-a1ab8c4e40f8-profile_image-70x70.png")
        )
        listaStreamers.add(
            streamers("juansguarnizo","Is simply dummy text..."
                ,"Just Chatting"
                ,R.drawable.diapositiva1
                ,"https://static-cdn.jtvnw.net/jtv_user_pictures/74586414-e27b-4347-89c5-109e42ac3e1d-profile_image-70x70.png")
        )
        listaStreamers.add(
            streamers("biyin_","Is simply dummy text..."
                ,"Just Chatting"
                ,R.drawable.diapositiva2
                ,"https://static-cdn.jtvnw.net/jtv_user_pictures/d116626c-2cb1-4d04-8dc0-a385f098a6f4-profile_image-70x70.png")
        )
        listaStreamers.add(
            streamers("VioletaG","Is simply dummy text..."
                ,"Just Chatting"
                ,R.drawable.diapositiva3
                ,"https://static-cdn.jtvnw.net/jtv_user_pictures/f125faca-67cd-4980-83c8-dd85576707c1-profile_image-70x70.png")
        )
        listaStreamers.add(
            streamers("AriGameplays","Is simply dummy text..."
                ,"Just Chatting"
                ,R.drawable.diapositiva4
                ,"https://static-cdn.jtvnw.net/jtv_user_pictures/a71759f1-11c4-4b0d-bec5-35a480b34958-profile_image-70x70.png")
        )
        listaStreamers.add(
            streamers("8ocho","Is simply dummy text..."
                ,"Just Chatting"
                ,R.drawable.diapositiva1
                ,"https://static-cdn.jtvnw.net/jtv_user_pictures/843fc46f-a5f4-4c9b-9429-ed9b1d640385-profile_image-70x70.png")
        )
        listaStreamers.add(
            streamers("rivers_gg","Is simply dummy text..."
                ,"Just Chatting"
                ,R.drawable.diapositiva2
                ,"https://static-cdn.jtvnw.net/jtv_user_pictures/0e6f8782-d5b9-4a51-ae8a-9c952c213487-profile_image-70x70.png")
        )
        //Recycler
        inicializarRecyclerViewStreamers(listaStreamers,streamsList)

        return root
    }
    fun inicializarRecyclerViewStreamers(
        lista:ArrayList<streamers>,
        recyclerView: RecyclerView,
    ){
        val adaptador = streamersAdapter(
            this,
            lista,
            recyclerView,
            R.layout.activity_tus_canales_vivo
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