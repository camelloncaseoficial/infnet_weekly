package com.camelloncase.infnetweekly.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.camelloncase.infnetweekly.util.showMessageToUser
import com.camelloncase.infnetweekly.databinding.FragmentLoginBinding
import com.camelloncase.infnetweekly.util.Resource
import com.camelloncase.infnetweekly.viewmodel.AuthenticationViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModelFactory: ViewModelProvider.AndroidViewModelFactory
    private lateinit var viewModel: AuthenticationViewModel
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        (activity as AppCompatActivity?)!!.supportActionBar?.hide()

        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        val application = requireNotNull(this.activity).application
        viewModelFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        viewModel = ViewModelProvider(this, viewModelFactory)[AuthenticationViewModel::class.java]

        auth = Firebase.auth

        binding.recoveryAccountTextView.setOnClickListener {
            navigateToRecoverScreen()
        }

        binding.cancelButton.setOnClickListener{
            navigateToInitialScreen()
        }

        binding.signInAppCompatButton.setOnClickListener {

            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            when {
                email.isEmpty() -> showMessageToUser(context, "Email is empty!")
                password.isEmpty() -> showMessageToUser(context, "Password is empty!")
                else -> viewModel.loginUser(email, password)
            }
        }

        viewModel.userSignInStatus.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    enableProgressBar(true)
                }
                is Resource.Success -> {
                    enableProgressBar(false)
                    navigateToHomeScreen()
                    showMessageToUser(context, "Logged In Successfully")
                }
                is Resource.Error -> {
                    enableProgressBar(false)
                    showMessageToUser(context, it.message.toString())
                }
            }
        })

        return binding.root
    }

    private fun navigateToRecoverScreen() {
        findNavController().navigate(
            LoginFragmentDirections.actionLoginFragmentToRecoverFragment()
        )
    }

    private fun navigateToHomeScreen() {
        findNavController().navigate(
            LoginFragmentDirections.actionLoginFragmentToNavHome()
        )
    }

    private fun navigateToInitialScreen() {
        findNavController().navigate(
            LoginFragmentDirections.actionLoginFragmentToInitialFragment()
        )
    }

    private fun enableProgressBar(choice: Boolean) {
        binding.loginProgressBar.isVisible = choice
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}