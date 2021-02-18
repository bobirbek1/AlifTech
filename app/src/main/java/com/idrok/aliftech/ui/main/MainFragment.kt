package com.idrok.aliftech.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.gson.Gson
import com.idrok.aliftech.Model
import com.idrok.aliftech.R
import com.idrok.aliftech.database.MyRoomDatabase
import com.idrok.aliftech.databinding.MainFragmentBinding
import com.idrok.aliftech.ui.main.adapter.MainAdapter


const val MODEL = "model"

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding
    private lateinit var adapter: MainAdapter
    private lateinit var gson: Gson

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)

        setAllViews()

        return binding.root
    }

    private fun setAllViews() {
        setRv()
        gson = Gson()
    }

    private fun setRv() {
        adapter = MainAdapter { model ->
            val jsonString = gson.toJson(model)
            findNavController().navigate(
                R.id.detailFragment, bundleOf(
                    MODEL to jsonString
                )
            )
        }

        binding.rvMain.adapter = adapter
        binding.rvMain.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val dataSource = MyRoomDatabase.getDatabase(requireContext()).dao()
        val factory = MainViewModelFactory(dataSource)
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
        viewModel.getAllGuides().observe(viewLifecycleOwner) { list ->
            if (list != null) {
                adapter.setData(list as ArrayList<Model>)
            } else {
                viewModel.updateData()
            }
        }
    }

}