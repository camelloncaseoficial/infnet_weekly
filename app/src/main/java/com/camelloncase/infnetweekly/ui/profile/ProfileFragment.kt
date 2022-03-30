package com.camelloncase.infnetweekly.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.camelloncase.infnetweekly.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser

        binding.valueNameTextView.text = currentUser?.displayName
        binding.valueEmailTextView.text = currentUser?.email

        Glide.with(this).load(currentUser?.photoUrl).into(binding.imageView)

        binding.button.setOnClickListener {
            auth.signOut()
            navigateToInitialScreen()
        }

        return binding.root
    }

    private fun navigateToInitialScreen() {
        findNavController().navigate(
            ProfileFragmentDirections.actionNavProfileToNavInitial()
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}