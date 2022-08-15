package com.kito.homework15.ui.loginpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.kito.homework15.databinding.FragmentLogInPageBinding
import com.kito.homework15.retrofitclient.ResultHendler
import kotlinx.coroutines.launch

class LogInPageFragment : Fragment() {

    private var binding: FragmentLogInPageBinding? = null
    private val viewModel: LogInPageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLogInPageBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.btnLogInOn?.setOnClickListener {
            observe()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun observe() {
        binding?.let {

            with(it) {
                when {
                    etUsername.text.toString().isEmpty() -> {
                        Toast.makeText(
                            requireContext(),
                            "Email field must not be empty",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    etPassword.text.toString().isEmpty() -> {
                        Toast.makeText(
                            requireContext(),
                            "Password field must not be empty",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    else -> {
                        viewLifecycleOwner.lifecycleScope.launch {
                            repeatOnLifecycle(Lifecycle.State.STARTED) {
                                viewModel.logIn(
                                    etUsername.text.toString(),
                                    etPassword.text.toString()
                                ).collect {
                                    when (it) {
                                        is ResultHendler.Success -> {
                                            logIn()
                                        }
                                        is ResultHendler.Error -> {
                                            Toast.makeText(
                                                requireContext(),
                                                it.errorMSg,
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun logIn() {
        findNavController().navigate(
            LogInPageFragmentDirections.actionLogInPageFragmentToUserFragment()
        )
    }
}