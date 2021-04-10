package com.santiago.themoviedbtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.santiago.themoviedbtest.databinding.ActivityMainBinding
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setupDataBinding()
    }

    private fun setupDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }


    override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }
}
