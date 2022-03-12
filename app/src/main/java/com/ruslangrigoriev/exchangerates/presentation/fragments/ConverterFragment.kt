package com.ruslangrigoriev.exchangerates.presentation.fragments

import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatSpinner
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ruslangrigoriev.exchangerates.R
import com.ruslangrigoriev.exchangerates.databinding.ConverterFragmentBinding
import com.ruslangrigoriev.exchangerates.presentation.MainViewModel
import com.ruslangrigoriev.exchangerates.utils.*
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ConverterFragment : Fragment(R.layout.converter_fragment) {
    private val binding by viewBinding(ConverterFragmentBinding::bind)
    private val viewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSpinners(viewModel.charCodeList)
        setEditText()
        setLabelRate()
        setBtnClear()
    }

    override fun onResume() {
        super.onResume()
        binding.spinnerFrom.setSelection(viewModel.indexConvertFrom)
        binding.spinnerTo.setSelection(viewModel.indexConvertTo)
    }

    private fun setSpinners(it: List<String>) {
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, it)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        with(binding) {
            spinnerFrom.adapter = adapter
            spinnerTo.adapter = adapter
            setSpinnerListener(spinnerFrom)
            setSpinnerListener(spinnerTo)
            binding.spinnerFrom.setSelection(viewModel.indexConvertFrom)
            binding.spinnerTo.setSelection(viewModel.indexConvertTo)
        }
    }

    private fun setEditText() {
        with(binding) {
            etFrom.setText("100")
            etFrom.addTextChangedListener {
                if (etFrom.hasFocus()) {
                    calculateFrom(it)
                }
            }
            etTo.addTextChangedListener {
                if (etTo.hasFocus()) {
                    calculateTo(it)
                }
            }
        }
    }

    private fun setSpinnerListener(spinner: AppCompatSpinner) {
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (spinner.id == binding.spinnerFrom.id) {
                    viewModel.indexConvertFrom = position
                } else {
                    viewModel.indexConvertTo = position
                }
                setLabelRate()
                calculateFrom(binding.etFrom.text)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //не используется
            }
        }

    }

    private fun calculateFrom(text: Editable?) {
        if (!text.isNullOrEmpty()) {
            binding.etTo.setText(extCalculateFrom(text, viewModel))
        } else {
            binding.etTo.setText("")
        }
    }

    private fun calculateTo(text: Editable?) {
        if (!text.isNullOrEmpty()) {
            binding.etFrom.setText(extCalculateTo(text, viewModel))
        } else {
            binding.etFrom.setText("")
        }
    }

    private fun setLabelRate() {
        binding.tvRateFrom.text = extGetLabelFrom(FROM, viewModel)
        binding.tvRateTo.text = extGetLabelFrom(TO, viewModel)
    }

    private fun setBtnClear() {
        binding.btnClear.setOnClickListener {
            binding.etFrom.setText("")
            binding.etTo.setText("")
        }
    }
}



