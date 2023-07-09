package com.example.compras

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.compras.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var produtosAdapter: ProdutoAdapter
    data class Item(val nome: String, val preco: String)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        produtosAdapter = ProdutoAdapter(this, mutableListOf())
        binding.listView.adapter = produtosAdapter

        binding.btnInserir.setOnClickListener {
            val nomeProduto = binding.txtProduto.text.toString()
            val preco = binding.txtPreco.text.toString().toFloatOrNull()

            if (nomeProduto.isNotEmpty() && preco != null) {
                val precoFormatado = "%.2f".format(preco)
                val item = Item(nomeProduto, precoFormatado)
                produtosAdapter.add(item)
                binding.txtProduto.text.clear()
                binding.txtPreco.text.clear()
                updateValorTotal()
            } else {
                binding.txtProduto.error = "Preencha um nome."
                binding.txtPreco.error = "Preencha um valor numérico válido."
            }


        }
    }
    fun updateValorTotal() {
        var valorTotal = 0.0
        for (i in 0 until produtosAdapter.count) {
            val item = produtosAdapter.getItem(i)
            valorTotal += item?.preco?.toFloat() ?: 0f
        }
        val valorTotalFormatado = "%.2f".format(valorTotal)
        binding.txtValorTotal.text = "Valor Total: R$$valorTotalFormatado"

        if (produtosAdapter.count == 0) {
            valorTotal = 0.0
            binding.txtValorTotal.text = "Valor Total: R$$valorTotal"
        }
    }
}


