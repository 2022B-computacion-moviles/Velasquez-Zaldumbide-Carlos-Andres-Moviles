package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView

class Categoria : AppCompatActivity(),RecyclerViewAdaptadorProductosCategoria.OnItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categoria)

        var idPapeleria = intent.extras?.getInt("id")?: "No encontrado"
        idPapeleria=idPapeleria.toString().toInt()
        val listaProductos = arrayListOf<productos>()
        when(idPapeleria){
            1->{
                //PRODUCTOS TECNOLOGIA
                listaProductos.add(
                    productos("bateria"
                        ,1.35
                        ,true
                        ,10
                        ,R.drawable.tecbateria)
                )
                listaProductos.add(
                    productos("Mouse"
                        ,25.0
                        ,true
                        ,15
                        ,R.drawable.tecmouse)
                )
                listaProductos.add(
                    productos("Pila"
                        ,1.0
                        ,true
                        ,20
                        ,R.drawable.tecpila)
                )
                listaProductos.add(
                    productos("Tarjeta micro SD"
                        ,7.0
                        ,true
                        ,6
                        ,R.drawable.tectarjeta)
                )
                listaProductos.add(
                    productos("Teclado"
                        ,20.35
                        ,true
                        ,10
                        ,R.drawable.tecteclado)
                )
            }
            2->{
                //PRODUCTOS ARTE
                listaProductos.add(
                    productos("Acrilico"
                        ,0.75
                        ,true
                        ,40
                        ,R.drawable.arteacrilico)
                )
                listaProductos.add(
                    productos("Juego geometrico"
                        ,1.00
                        ,true
                        ,19
                        ,R.drawable.artegeometrico)
                )
                listaProductos.add(
                    productos("Graduador"
                        ,0.50
                        ,true
                        ,10
                        ,R.drawable.arteggraduador)
                )
                listaProductos.add(
                    productos("Oleo"
                        ,1.10
                        ,true
                        ,12
                        ,R.drawable.arteoleo)
                )
                listaProductos.add(
                    productos("Regla 30cm"
                        ,0.75
                        ,true
                        ,12
                        ,R.drawable.arteregla)
                )
            }
            3->{
                //PRODUCTOS BAZAR
                listaProductos.add(
                    productos("Alfires"
                        ,0.35
                        ,true
                        ,13
                        ,R.drawable.bazaralfiler)
                )
                listaProductos.add(
                    productos("Bolas de espuma flex N 10"
                        ,0.25
                        ,true
                        ,17
                        ,R.drawable.bazarbolas)
                )
                listaProductos.add(
                    productos("Confeti"
                        ,1.29
                        ,true
                        ,23
                        ,R.drawable.bazarconfeti)
                )
                listaProductos.add(
                    productos("Paleta de helados"
                        ,0.75
                        ,true
                        ,19
                        ,R.drawable.bazarpaleta)
                )
                listaProductos.add(
                    productos("Stickers"
                        ,0.55
                        ,true
                        ,13
                        ,R.drawable.bazarsticker)
                )
            }
            4->{
                //PRODUCTOS CUADERNO
                listaProductos.add(
                    productos("Cuaderno andaluz 100 hojas uni"
                        ,1.35
                        ,true
                        ,11
                        ,R.drawable.cuadernoandaluz)
                )
                listaProductos.add(
                    productos("Cuaderno de dibujo"
                        ,1.10
                        ,true
                        ,20
                        ,R.drawable.cuadernodibujo)
                )
                listaProductos.add(
                    productos("Cuaderno de diseño"
                        ,1.25
                        ,true
                        ,22
                        ,R.drawable.cuadernodiseno)
                )
                listaProductos.add(
                    productos("Cuaderno papelesa"
                        ,0.80
                        ,true
                        ,7
                        ,R.drawable.cuadernopapelesa)
                )
                listaProductos.add(
                    productos("Cuaderno Parbulario"
                        ,1.55
                        ,true
                        ,13
                        ,R.drawable.cuadernoparbulario)
                )
            }
            5->{
                //PRODUCTOS ESCOLARES
                listaProductos.add(
                    productos("Acuarelas"
                        ,1.00
                        ,true
                        ,20
                        ,R.drawable.escolaracuarela)
                )
                listaProductos.add(
                    productos("Esfero rojo punta gruesa"
                        ,0.45
                        ,true
                        ,28
                        ,R.drawable.escolarboligrafo)
                )
                listaProductos.add(
                    productos("Lapiz rojo"
                        ,0.80
                        ,true
                        ,24
                        ,R.drawable.escolarlapiz)
                )
                listaProductos.add(
                    productos("Silicon liquida"
                        ,0.75
                        ,true
                        ,23
                        ,R.drawable.escolarsilicon)
                )
                listaProductos.add(
                    productos("Sacapuntas"
                        ,0.35
                        ,true
                        ,10
                        ,R.drawable.escolarsacapuntas)
                )
            }
            6->{
                //PRODUCTOS PAPELERIA
                listaProductos.add(
                    productos("Hoja bond unidad"
                        ,0.02
                        ,true
                        ,100
                        ,R.drawable.papeleriabond)
                )
                listaProductos.add(
                    productos("Papel brillante A4"
                        ,0.50
                        ,true
                        ,10
                        ,R.drawable.papeleriabrilante)
                )
                listaProductos.add(
                    productos("Hoja a cuadro"
                        ,0.70
                        ,true
                        ,25
                        ,R.drawable.papeleriacuadriculado)
                )
                listaProductos.add(
                    productos("Hoja Iris"
                        ,0.50
                        ,true
                        ,10
                        ,R.drawable.papeleriairis)
                )
                listaProductos.add(
                    productos("Pliego papel periodico"
                        ,0.15
                        ,true
                        ,13
                        ,R.drawable.papeleriaperiodico)
                )
            }
        }

        //Se instancia y se llama al recycler
        val recyclerView = findViewById<RecyclerView>(R.id.rv_productos)
        inicializarRecyclerView(listaProductos,recyclerView)

        //Boton para regresar
        val botonRegresar = findViewById<ImageButton>(R.id.regresar)
        botonRegresar
            .setOnClickListener{
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
    }
    fun inicializarRecyclerView(
        lista:ArrayList<productos>,
        recyclerView: RecyclerView
    ){
        val adaptador = RecyclerViewAdaptadorProductosCategoria(
            this,
            lista,
            recyclerView,
            R.layout.activity_categoriasrecycler
        )
        adaptador.listener = this
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.GridLayoutManager(this,2)
        adaptador.notifyDataSetChanged()
    }
    override fun onItemClick(position: Int) {
        val intent = Intent(this, Carrito::class.java)
        startActivity(intent)
    }
}
