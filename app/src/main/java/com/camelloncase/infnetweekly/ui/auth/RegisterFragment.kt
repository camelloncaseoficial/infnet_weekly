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
import com.camelloncase.infnetweekly.util.checkEmailPattern
import com.camelloncase.infnetweekly.util.checkPasswordPattern
import com.camelloncase.infnetweekly.util.showMessageToUser
import com.camelloncase.infnetweekly.databinding.FragmentRegisterBinding
import com.camelloncase.infnetweekly.util.Resource
import com.camelloncase.infnetweekly.viewmodel.AuthenticationViewModel

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModelFactory: ViewModelProvider.AndroidViewModelFactory
    private lateinit var viewModel: AuthenticationViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        (activity as AppCompatActivity?)!!.supportActionBar?.hide()

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)

        val application = requireNotNull(this.activity).application
        viewModelFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        viewModel = ViewModelProvider(this, viewModelFactory)[AuthenticationViewModel::class.java]

        binding.signUpButton.setOnClickListener {

            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            val retypePassword = binding.repeatPasswordEditText.text.toString()

            when {
                !checkEmailPattern(email) -> {
                    showMessageToUser(context, "Enter a valid email!")
                }
                !checkPasswordPattern(password) -> {
                    showMessageToUser(context, "Weak password!")
                }
                password != retypePassword -> {
                    showMessageToUser(context, "Passwords don't matches!")
                }
                else -> {
                    viewModel.createUser(email, password)
                    viewModel.loginUser(email, password)
                }
            }
        }

        binding.cancelButton.setOnClickListener {
            navigateToInitialScreen()
        }

        viewModel.userRegistrationStatus.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    enableProgressBar(true)
                }
                is Resource.Success -> {
                    enableProgressBar(false)
                    showMessageToUser(context, "Registered Successfully!")
                    navigateToHomeScreen()
                }
                is Resource.Error -> {
                    enableProgressBar(false)
                    showMessageToUser(context, it.message.toString())
                }
            }
        })

        return binding.root
    }



    private fun navigateToHomeScreen() {
        findNavController().navigate(
            RegisterFragmentDirections.actionRegisterFragmentToNavHome()
        )
    }

    private fun navigateToInitialScreen() {
        findNavController().navigate(
            RegisterFragmentDirections.actionRegisterFragmentToInitialFragment()
        )
    }

    private fun enableProgressBar(choice: Boolean) {
        binding.registerProgressBar.isVisible = choice
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}