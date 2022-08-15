package com.kito.homework15.ui.registerpage

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
import com.kito.homework15.databinding.FragmentRegisterPageBinding
import com.kito.homework15.retrofitclient.ResultHendler
import kotlinx.coroutines.launch

class RegisterPageFragment : Fragment() {
    private var binding: FragmentRegisterPageBinding? = null
    private val viewModel: RegisterPageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterPageBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.btnReg?.setOnClickListener {
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
                    etEmail.text.toString().isEmpty() -> {
                        Toast.makeText(
                            requireContext(),
                            "Email field must not be empty",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    etPass.text.toString().isEmpty() -> {
                        Toast.makeText(
                            requireContext(),
                            "Password field must not be empty",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    etUsername.text.toString().isEmpty() -> {
                        Toast.makeText(
                            requireContext(),
                            "Username field must not be empty",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    else -> {
                        viewLifecycleOwner.lifecycleScope.launch {
                            repeatOnLifecycle(Lifecycle.State.STARTED) {
                                viewModel.register(
                                    etEmail.text.toString(),
                                    etPass.text.toString()
                                ).collect {
                                    when (it) {
                                        is ResultHendler.Success -> {
                                            register()
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

    private fun register() {
        findNavController().navigate(
            RegisterPageFragmentDirections.actionRegisterPageFragmentToUserFragment()
        )
    }
}