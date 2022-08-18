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
import androidx.viewbinding.ViewBinding
import com.kito.homework15.BaseFragment
import com.kito.homework15.databinding.FragmentLogInPageBinding
import com.kito.homework15.databinding.FragmentUserBinding
import com.kito.homework15.retrofitclient.ResultHendler
import com.kito.homework15.ui.user.UserViewModel
import kotlinx.coroutines.launch

class LogInPageFragment : BaseFragment <FragmentLogInPageBinding, LogInPageViewModel> (
    FragmentLogInPageBinding::inflate,
    LogInPageViewModel::class.java, false){
    override fun getStart() {
    }

}