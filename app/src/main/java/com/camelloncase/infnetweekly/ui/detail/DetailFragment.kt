package com.camelloncase.infnetweekly.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.camelloncase.infnetweekly.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var weekAlias: TextView
    private lateinit var weekStart: TextView
    private lateinit var weekObservations: TextView
    private lateinit var weekRegular: TextView
    private lateinit var weekProject: TextView
    private lateinit var cancelButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as AppCompatActivity?)!!.supportActionBar?.show()
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        initWidgetComponents()

        val args = DetailFragmentArgs.fromBundle(requireArguments())

        binding.weekAliasTextView.text = args.weekAlias
        binding.weekStartTextView.text = args.weekStart
        binding.observationsTextView.text = args.weekObservations
        binding.regularTextView.text = args.weekRegular
        binding.projectTextView.text = args.weekProject

        binding.cancelButton.setOnClickListener {
            goToHomeScreen()
        }

        return binding.root
    }

    private fun initWidgetComponents() {
        weekAlias = binding.weekAliasTextView
        weekStart = binding.weekStartTextView
        weekObservations = binding.observationsTextView
        weekRegular = binding.regularTextView
        weekProject = binding.projectTextView
        cancelButton = binding.cancelButton
    }

    private fun goToHomeScreen() {
        findNavController().navigate(
            DetailFragmentDirections.actionDetailFragmentToNavHome()
        )
    }
}