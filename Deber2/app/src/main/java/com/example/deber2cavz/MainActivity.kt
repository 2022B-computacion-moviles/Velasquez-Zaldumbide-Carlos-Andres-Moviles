package com.example.deber2cavz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        irActividad(Menu_inferior::class.java)
        /*val avatarUsuario=findViewById<ImageButton>(R.id.id_avatar_usuario)
        Glide.with(this)
            .load("https://static-cdn.jtvnw.net/user-default-pictures-uv/41780b5a-def8-11e9-94d9-784f43822e80-profile_image-70x70.png")
            .apply(RequestOptions.circleCropTransform())
            .into(avatarUsuario)

        //Recicler streamers
        val streamsList: RecyclerView = findViewById(R.id.recycler_tus_canales) // (1)
        val listaStreamers = arrayListOf<streamers>()
        listaStreamers.add(streamers("ibai","Is simply dummy text..."
            ,"Just Chatting"
            ,R.drawable.diapositiva1
            ,"https://static-cdn.jtvnw.net/jtv_user_pictures/574228be-01ef-4eab-bc0e-a4f6b68bedba-profile_image-70x70.png"))
        listaStreamers.add(streamers("Perxitaa","Is simply dummy text..."
            ,"Just Chatting"
            ,R.drawable.diapositiva2
            ,"https://static-cdn.jtvnw.net/jtv_user_pictures/6213a284-c133-41cf-ad99-ebef738eb550-profile_image-70x70.png"))
        //Recycler
        inicializarRecyclerViewStreamers(listaStreamers,streamsList)

        //Recicler streamers recomendados
        val streamsListR: RecyclerView = findViewById(R.id.recycler_canales_recomendado) // (1)
        val listaStreamersR = arrayListOf<streamers>()
        listaStreamersR.add(streamers("ibai","Is simply dummy text..."
            ,"Just Chatting"
            ,R.drawable.diapositiva1
            ,"https://static-cdn.jtvnw.net/jtv_user_pictures/574228be-01ef-4eab-bc0e-a4f6b68bedba-profile_image-70x70.png"))
        listaStreamersR.add(streamers("Perxitaa","Is simply dummy text..."
            ,"Just Chatting"
            ,R.drawable.diapositiva2
            ,"https://static-cdn.jtvnw.net/jtv_user_pictures/6213a284-c133-41cf-ad99-ebef738eb550-profile_image-70x70.png"))
        //Recycler
        inicializarRecyclerViewStreamers(listaStreamersR,streamsListR)*/


    }

    /*fun inicializarRecyclerViewStreamers(
        lista:ArrayList<streamers>,
        recyclerView: RecyclerView
    ){
        val adaptador =streamersAdapter(
            this,
            lista,
            recyclerView,
            R.layout.activity_tus_canales_vivo
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        adaptador.notifyDataSetChanged()
    }

    fun inicializarRecyclerViewStreamsRecomendados(
        lista:ArrayList<streamers>,
        recyclerView: RecyclerView
    ){
        val adaptador =streamersAdapter(
            this,
            lista,
            recyclerView,
            R.layout.activity_canales_recomendados
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        adaptador.notifyDataSetChanged()
    }*/

    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}