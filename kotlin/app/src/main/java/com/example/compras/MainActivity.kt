package com.example.compras

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import com.example.compras.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var produtosAdapter: ArrayAdapter<String>
    private val selectedPositions = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        produtosAdapter = ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1)
        binding.listView.adapter = produtosAdapter

        binding.btnInserir.setOnClickListener {
            val produto = binding.txtProduto.text.toString()
            if(produto.isNotEmpty()) {
                produtosAdapter.add(produto)
                binding.txtProduto.text.clear()
            }else{
               binding.txtProduto.error = "Preencha um valor."
            }

            binding.listView.setOnItemClickListener { adapterView: AdapterView<*>, view: View, position: Int, id: Long ->
                val item = produtosAdapter.getItem(position)
                if (selectedPositions.contains(position)) {
                    view.setBackgroundColor(Color.TRANSPARENT) // Remove a cor de fundo
                    selectedPositions.remove(position) // Remove a posição do array
                } else {
                    view.setBackgroundColor(ContextCompat.getColor(this, R.color.red)) // Define a cor de fundo vermelha
                    selectedPositions.add(position) // Adiciona a posição selecionada ao array
                }
            }


            binding.btnRemover.setOnClickListener {
                for (position in selectedPositions.sortedDescending()) {
                    produtosAdapter.remove(produtosAdapter.getItem(position)) // Remove o item da lista pelo adapter
                    val view = binding.listView.getChildAt(position)
                    view?.setBackgroundColor(Color.TRANSPARENT) // Remove a cor de fundo
                }
                selectedPositions.clear() // Limpa o array de posições selecionadas
            }

        }
    }
}
