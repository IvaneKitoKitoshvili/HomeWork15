package com.kito.homework15.ui.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kito.homework15.BaseFragment
import com.kito.homework15.databinding.FragmentUserBinding
import com.kito.homework15.databinding.FragmentWelcomePageBinding

class UserFragment : BaseFragment<FragmentUserBinding, UserViewModel>(
    FragmentUserBinding::inflate,
    UserViewModel::class.java, false
) {
    override fun getStart() {
    }

}