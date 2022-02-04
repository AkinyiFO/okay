package com.ibrajix.directcurrencyconverter.ui

import android.R.attr.fragment
import android.content.Intent.getIntent
import android.os.Bundle
import android.text.TextUtils.replace
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibrajix.directcurrencyconverter.LogApplication
import com.ibrajix.directcurrencyconverter.R
import com.ibrajix.directcurrencyconverter.adapter.LogListAdapter
import com.ibrajix.directcurrencyconverter.databinding.ItemLogsBinding
import com.ibrajix.directcurrencyconverter.viewmodel.LogViewModel
import com.ibrajix.directcurrencyconverter.viewmodel.LogViewModelFactory


class ItemLogs : Fragment() {
    private val viewModel: LogViewModel by activityViewModels {
        LogViewModelFactory(
            (activity?.application as LogApplication).database.singleLogDao()
        )
    }

    private var _binding: ItemLogsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ItemLogsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = LogListAdapter {

        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerView.adapter = adapter
        // Attach an observer on the allItems list to update the UI automatically when the data
        // changes.
        viewModel.allItems.observe(this.viewLifecycleOwner) { items ->
            items.let {
                adapter.submitList(it)
            }
        }

    }


}
