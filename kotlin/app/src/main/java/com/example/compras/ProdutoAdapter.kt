package com.example.compras

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import androidx.core.text.HtmlCompat
import com.example.compras.databinding.ActivityMainBinding

class ProdutoAdapter(context: Context, items: MutableList<MainActivity.Item>) :
    ArrayAdapter<MainActivity.Item>(context, R.layout.list_item, items) {

    private lateinit var binding: ActivityMainBinding
    private lateinit var produtosAdapter: ProdutoAdapter

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)

        val itemTextView = view.findViewById<TextView>(R.id.txt_item)
        val deleteButton = view.findViewById<Button>(R.id.btn_apagar)

        val item = getItem(position)
        if (item != null) {
            val boldNome = "<b>Produto:</b> ${item.nome}"
            val boldPreco = "<b>Valor:</b> R$${item.preco}"
            val fullText = "$boldNome <br> $boldPreco"
            itemTextView.text = HtmlCompat.fromHtml(fullText, HtmlCompat.FROM_HTML_MODE_LEGACY)
        }

        deleteButton.setOnClickListener {
            remove(getItem(position))
            notifyDataSetChanged()
            (context as MainActivity).updateValorTotal()
        }

        return view
    }
}



