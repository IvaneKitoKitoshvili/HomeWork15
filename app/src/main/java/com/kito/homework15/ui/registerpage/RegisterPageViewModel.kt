package com.kito.homework15.ui.registerpage

import androidx.lifecycle.ViewModel
import com.kito.homework15.RetrofitClient
import com.kito.homework15.models.Register
import com.kito.homework15.models.Request
import com.kito.homework15.retrofitclient.ResultHendler
import kotlinx.coroutines.flow.flow

class RegisterPageViewModel : ViewModel() {
    fun register(email: String, password: String) = flow<ResultHendler<Register>> {
        val answerFromServer =
            RetrofitClient.getRegisterParameter().register(Request(email, password))
        val response: ResultHendler<Register> = when {
            answerFromServer.isSuccessful -> {
                ResultHendler.Success(data = answerFromServer.body()!!)
            }
            else -> {
                ResultHendler.Error(errorMSg = answerFromServer.errorBody()!!.string())
            }
        }
        emit(response)
    }
}