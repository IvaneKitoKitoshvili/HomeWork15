package com.kito.homework15.ui.user

import android.util.Log.d
import androidx.lifecycle.ViewModel

class UserViewModel: ViewModel() {

    override fun onCleared() {
        d("KITOTEST", "USERVIEWMODELONDESTROY")
        super.onCleared()
    }
}