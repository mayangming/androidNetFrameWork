package com.xichaichai.android.login.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.xichaichai.android.base.HttpResult
import com.xichaichai.android.login.bean.LoginBean
import com.xichaichai.android.login.http.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception


class LoginViewModel(
    private val loginRepository: LoginRepository
): ViewModel() {
    private val _loginResult = MutableLiveData<List<LoginBean>>()

    val loginResult: LiveData<List<LoginBean>> = _loginResult

    private val _uiStatus = MutableLiveData<Int>()//页面状态 0，表示加载失败 1 成功

    val uiStatus: LiveData<Int> = _uiStatus

    fun login(){
        viewModelScope.launch(context = Dispatchers.IO){
            try {
                when(val result = loginRepository.login()){
                    is HttpResult.Success -> {
                        viewModelScope.launch(context = Dispatchers.Main) {
                            _loginResult.value = result.data!!
                            _uiStatus.value = 1
                        }
                    }
                    is HttpResult.Error -> {
                        viewModelScope.launch(context = Dispatchers.Main) {
                            _uiStatus.value = 0
                        }
                    }
                }
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}
class LoginViewModelFactory(private val loginRepository: LoginRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(LoginRepository::class.java).newInstance(loginRepository)
    }
}