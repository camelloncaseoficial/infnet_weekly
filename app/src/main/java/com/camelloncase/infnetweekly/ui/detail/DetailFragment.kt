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
    private lateinit var day: TextView
    private lateinit var schoolDay: TextView
    private lateinit var observations: TextView
    private lateinit var regular: TextView
    private lateinit var project: TextView
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

        binding.dayTextView.text = args.day
        binding.schoolDayTextView.text = args.schoolDay
        binding.observationsTextView.text = args.observations
        binding.regularTextView.text = args.regular
        binding.projectTextView.text = args.project

        binding.cancelButton.setOnClickListener {
            goToHomeScreen()
        }

        return binding.root
    }

    private fun initWidgetComponents() {
        day = binding.dayTextView
        schoolDay = binding.schoolDayTextView
        observations = binding.observationsTextView
        regular = binding.regularTextView
        project = binding.projectTextView
        cancelButton = binding.cancelButton
    }

    private fun goToHomeScreen() {
        findNavController().navigate(
            DetailFragmentDirections.actionDetailFragmentToNavHome()
        )
    }
}