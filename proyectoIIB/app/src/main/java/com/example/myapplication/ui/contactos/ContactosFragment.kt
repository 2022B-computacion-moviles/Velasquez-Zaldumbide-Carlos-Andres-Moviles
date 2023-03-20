package com.example.myapplication.ui.contactos

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.Categoria
import com.example.myapplication.MainActivity
import com.example.myapplication.databinding.FragmentContactosBinding

class ContactosFragment : Fragment() {

    private var _binding: FragmentContactosBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val contactosViewModel =
            ViewModelProvider(this).get(ContactosViewModel::class.java)

        _binding = FragmentContactosBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //BOTONES
        val botonEnviar = binding.buttonEnviarComentario
        botonEnviar
            .setOnClickListener{
                Toast.makeText(activity, "MENSAJE ENVIADO", Toast.LENGTH_LONG).show()
                val intent = Intent(activity, MainActivity::class.java)

                startActivity(intent)
            }

        /*val textView: TextView = binding.textGallery
        contactosViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}