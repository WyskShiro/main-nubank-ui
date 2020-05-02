package com.tem.plate.container

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.tem.plate.R
import com.tem.plate.databinding.ActivityContainerBinding
import com.tem.plate.fragment1.Fragment1
import com.tem.plate.util.di.scopes.FragmentScope
import com.tem.plate.util.extensions.shortToast
import com.tem.plate.util.structure.base.BaseActivity
import com.tem.plate.util.structure.base.BaseViewModel
import com.tem.plate.util.viewmodels.Placeholder
import dagger.Module
import dagger.android.ContributesAndroidInjector

class ContainerActivity : BaseActivity() {

    override val baseViewModel: BaseViewModel get() = viewModel

    private lateinit var binding: ActivityContainerBinding
    private val viewModel by lazy {
        ViewModelProvider(this).get(ContainerViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContainerBinding.inflate(layoutInflater)
        lifecycle.addObserver(viewModel)
        setupUi()
        setContentView(binding.root)
    }

    private fun setupUi() {
        // Set clicklisteners and textListeners
    }
}

@Module
interface ContainerActivityModule {

    @ContributesAndroidInjector
    @FragmentScope
    fun contributesFragment1(): Fragment1
}