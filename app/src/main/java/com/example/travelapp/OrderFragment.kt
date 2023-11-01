package com.example.travelapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.example.travelapp.databinding.FragmentOrderBinding


/**
 * A simple [Fragment] subclass.
 * Use the [OrderFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OrderFragment : Fragment() {
    private lateinit var binding: FragmentOrderBinding

    //isi dari spinner atau pilihan jenis tiketnya
    private val order = arrayOf(
        "First Class Ticket",
        "Business Class Ticket",
        "Economy Class Ticket"
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            val adapterOrder = ArrayAdapter(
                requireActivity(),
                android.R.layout.simple_spinner_item,order
            )

            //spinner berbentuk dropdown
            adapterOrder.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            spinnerOrder.adapter = adapterOrder

            spinnerOrder.onItemSelectedListener=
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?, view: View?, position: Int, id: Long
                    ) {
                        val selectedOrder = order[position]
                        Log.d("Pilih Tiket", selectedOrder)
                    }
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }
                }
//ketika button buy diklik maka akan kembali ke halaman ticket
            btnBuy2.setOnClickListener {
                val selectedOrder = spinnerOrder.selectedItem.toString()
                if (selectedOrder.isNotBlank()) {
                    findNavController().navigateUp()
                }
            }
        }
    }
}
