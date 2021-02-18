package com.idrok.aliftech.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.idrok.aliftech.Model
import com.idrok.aliftech.R
import com.idrok.aliftech.databinding.DetailFragmentBinding
import com.idrok.aliftech.ui.main.MODEL

class DetailFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel
    private lateinit var binding: DetailFragmentBinding
    private lateinit var gson: Gson

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.detail_fragment, container, false)
        setAllViews()
        return binding.root
    }

    private fun setAllViews() {
        gson = Gson()
        val jsonString = arguments?.getString(MODEL, "")
        if (!jsonString.isNullOrEmpty()) {
            val model = gson.fromJson(jsonString, Model::class.java)
            binding.model = model
        } else {
            Toast.makeText(requireContext(), "data can not found", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
    }

}