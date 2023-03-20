package com.example.myapplication.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.Categoria
import com.example.myapplication.databinding.FragmentHomeBinding

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

        //BOTONES
        val botonTecnologia = binding.tecnologia
        botonTecnologia
            .setOnClickListener{
                val intent = Intent(activity,Categoria::class.java)
                intent.putExtra("id",1)
                startActivity(intent)
            }
        val botonArte = binding.arte
        botonArte
            .setOnClickListener{
                val intent = Intent(activity,Categoria::class.java)
                intent.putExtra("id",2)
                startActivity(intent)
            }
        val botonBazar = binding.bazar
        botonBazar
            .setOnClickListener{
                val intent = Intent(activity,Categoria::class.java)
                intent.putExtra("id",3)
                startActivity(intent)
            }
        val botonCuaderno = binding.cuaderno
        botonCuaderno
            .setOnClickListener{
                val intent = Intent(activity,Categoria::class.java)
                intent.putExtra("id",4)
                startActivity(intent)
            }
        val botonEscoalres = binding.escolares
        botonEscoalres
            .setOnClickListener{
                val intent = Intent(activity,Categoria::class.java)
                intent.putExtra("id",5)
                startActivity(intent)
            }
        val botonPapeleria = binding.Papeleria
        botonPapeleria
            .setOnClickListener{
                val intent = Intent(activity,Categoria::class.java)
                intent.putExtra("id",6)
                startActivity(intent)
            }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}