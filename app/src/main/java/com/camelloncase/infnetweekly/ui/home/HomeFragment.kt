package com.camelloncase.infnetweekly.ui.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.camelloncase.infnetweekly.adapter.NotificationAdapter
import com.camelloncase.infnetweekly.databinding.AppBarMainBinding
import com.camelloncase.infnetweekly.databinding.FragmentHomeBinding
import com.camelloncase.infnetweekly.model.Notification
import com.camelloncase.infnetweekly.repository.NotificationApiRepository
import com.camelloncase.infnetweekly.viewmodel.NotificationViewModel
import com.camelloncase.infnetweekly.viewmodel.NotificationViewModelFactory
import com.google.firebase.auth.FirebaseAuth
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds

class HomeFragment : Fragment(), NotificationAdapter.OnItemClickListener  {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: NotificationViewModel
    private lateinit var notificationRecyclerView: RecyclerView
    private lateinit var auth: FirebaseAuth
    private lateinit var notificationAdapter: NotificationAdapter
    lateinit var adView : AdView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        verifyLoggedUser()

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        MobileAds.initialize(requireContext()) {}
        adView = binding.adView
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        setupRecyclerView()

        val repository = NotificationApiRepository()
        val viewModelFactory = NotificationViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[NotificationViewModel::class.java]

        viewModel.getAllNotificationByYear(2022, "week_create_date", "desc")

        viewModel.allNotificationsByYear.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                response.body()?.let { notificationAdapter.setData(it) }
            } else {
                Toast.makeText(context, response.code(), Toast.LENGTH_SHORT).show()
            }
        })

        return binding.root
    }

    private fun verifyLoggedUser() {
        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        Handler(Looper.getMainLooper()).postDelayed({
            if (user == null) {
                navigateToInitialScreen()
            }
        }, 2000)
    }

    private fun setupRecyclerView() {
        notificationAdapter = NotificationAdapter(this)
        notificationRecyclerView = binding.recyclerView
        notificationRecyclerView.adapter = notificationAdapter
        notificationRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun goToManagementScreen(notification: Notification) {

        val action = HomeFragmentDirections.actionNavHomeToDetailFragment()
        action.weekAlias = notification.weekAlias
        action.weekStart = notification.weekStart
        action.weekObservations = notification.weekObservations
        action.weekRegular = notification.weekRegularTypicalActivities
        action.weekProject = notification.weekProjectTypicalActivities

        findNavController().navigate(action)
    }

    private fun navigateToInitialScreen() {
        findNavController().navigate(
            HomeFragmentDirections.actionNavHomeToNavInitial()
        )
    }

    override fun onClick(notification: Notification, position: Int) {
        goToManagementScreen(notification)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}