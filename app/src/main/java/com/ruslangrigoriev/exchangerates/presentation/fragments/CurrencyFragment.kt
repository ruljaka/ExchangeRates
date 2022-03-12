package com.ruslangrigoriev.exchangerates.presentation.fragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ruslangrigoriev.exchangerates.R
import com.ruslangrigoriev.exchangerates.databinding.CurrencyFragmentBinding
import com.ruslangrigoriev.exchangerates.presentation.MainActivity
import com.ruslangrigoriev.exchangerates.presentation.MainViewModel
import com.ruslangrigoriev.exchangerates.presentation.ResultState.*
import com.ruslangrigoriev.exchangerates.presentation.adapters.RecyclerAdapter
import com.ruslangrigoriev.exchangerates.utils.formatDate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyFragment : Fragment(R.layout.currency_fragment) {
    private val binding by viewBinding(CurrencyFragmentBinding::bind)
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var adapter: RecyclerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setupRecyclerView()
        subscribeUi()
        setSearch()
    }

    private fun subscribeUi() {
        viewModel.viewState.observe(viewLifecycleOwner, {
            when (it) {
                Loading -> {
                    showLoading(true)
                }
                is Failure -> {
                    showToast(it.errorMessage)
                    showLoading(false)
                }
                is Success -> {
                    showLoading(false)
                    bindUI(it)
                }
            }
        })
    }

    private fun bindUI(state: Success) {
        with(state.currencyRatesInfo) {
            adapter.dataList = currency
            binding.tvRateLabel.text = getString(R.string.rate_label, date.formatDate())
            binding.tvLastUpdate.text = getString(R.string.update_label, updated)
        }
    }

    private fun setupRecyclerView() {
        adapter = RecyclerAdapter { position -> onListItemClick(position) }
        binding.recyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = adapter
    }


    private fun showToast(errorMessage: String?) {
        Toast.makeText(
            activity, errorMessage ?: "Unknown Error", Toast.LENGTH_SHORT
        ).show()
    }

    private fun showLoading(loading: Boolean) {
        if (loading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun onListItemClick(position: Int) {
        viewModel.indexConvertTo = position
        (activity as MainActivity).setCurrentTab(1)
    }

    private fun setSearch() {
        binding.searchView.apply {
            setOnClickListener {
                binding.searchView.isIconified = false
            }
            setOnCloseListener {
                adapter.filter.filter("")
                true
            }
            onActionViewExpanded()
            queryHint = "Search"
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (binding.searchView.hasFocus()) {
                        adapter.filter.filter(newText)
                    }
                    return false
                }
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.update -> {
                viewModel.updateCurrencyRates()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}