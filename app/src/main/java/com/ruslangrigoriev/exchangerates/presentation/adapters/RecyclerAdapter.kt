package com.ruslangrigoriev.exchangerates.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.ruslangrigoriev.exchangerates.databinding.ListItemBinding
import com.ruslangrigoriev.exchangerates.data.dto.Currency
import java.util.*

class RecyclerAdapter(
    private val onItemClicked: (position: Int) -> Unit,
) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(), Filterable {

    var dataList: MutableList<Currency> = mutableListOf()
        set(value) {
            field = value
            filteredList = dataList
            notifyDataSetChanged()
        }
    var filteredList: MutableList<Currency> = mutableListOf()


    inner class ViewHolder(
        private val binding: ListItemBinding,
        private val onItemClicked: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val selectedItem = filteredList[absoluteAdapterPosition]
                onItemClicked(dataList.indexOf(selectedItem))
            }
        }

        fun bind(currency: Currency) {
            with(binding) {
                tvCharCode.text = currency.charCode
                tvName.text = currency.name
                tvNominal.text = currency.nominal.toString()
                tvValue.text = currency.value.toString()
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ViewHolder(binding, onItemClicked)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(filteredList[position])
    }

    override fun getItemCount(): Int {
        return filteredList.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()

                if (charSearch.isEmpty()) {
                    filteredList = dataList
                } else {
                    val resultList = mutableListOf<Currency>()
                    for (currency in dataList) {
                        if (currency.name.lowercase(Locale.ROOT)
                                .contains(charSearch.lowercase(Locale.ROOT))
                        ) {
                            resultList.add(currency)
                        }
                    }
                    filteredList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredList = results?.values as MutableList<Currency>
                notifyDataSetChanged()
            }

        }
    }
}