package com.kito.homework15.ui.loginpage

import androidx.lifecycle.ViewModel
import com.kito.homework15.RetrofitClient.getLoginParameter
import com.kito.homework15.models.LogIn
import com.kito.homework15.models.Request
import com.kito.homework15.retrofitclient.ResultHendler
import kotlinx.coroutines.flow.flow

class LogInPageViewModel : ViewModel() {
    fun logIn(email: String, password: String)= flow<ResultHendler<LogIn>> {
        val answerFromServer = getLoginParameter().login(Request(email, password))
        val response: ResultHendler<LogIn> = when {
            answerFromServer.isSuccessful -> {
                ResultHendler.Success(data = answerFromServer.body()!!)
            }
            else -> {
                ResultHendler.Error(errorMSg = answerFromServer.errorBody()!!.string())
            }
        }
        emit (response)
    }
}