package com.xichaichai.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.xichaichai.android.databinding.ActivityMainBinding
import com.xichaichai.android.login.http.LoginRepository
import com.xichaichai.android.login.viewmodel.LoginViewModel
import com.xichaichai.android.login.viewmodel.LoginViewModelFactory

class MainActivity : AppCompatActivity() {
    private val loginViewModel: LoginViewModel by viewModels{
        LoginViewModelFactory(LoginRepository())
    }
    private val dataBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(dataBinding.root)
        loginViewModel.loginResult.observe(this){
            println("YM----->$it")
        }
        loginViewModel.uiStatus.observe(this){
            when(it){
                0 -> {
                    Toast.makeText(this,"加载失败",Toast.LENGTH_LONG).show()
                }
                1 -> {
                    Toast.makeText(this,"加载成功",Toast.LENGTH_LONG).show()
                }
            }
        }
        loginViewModel.login()//调用完后,通过liveData回传的数据进行UI更新，后期可以通过DataBinding进行双项绑定进一步解耦
    }
}