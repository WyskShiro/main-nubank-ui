package com.tem.plate.container

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.pandora.bottomnavigator.BottomNavigator
import com.tem.plate.R
import com.tem.plate.databinding.ActivityContainerBinding
import com.tem.plate.fragment1.Fragment1
import com.tem.plate.util.di.scopes.FragmentScope
import com.tem.plate.util.structure.base.BaseActivity
import com.tem.plate.util.structure.base.BaseViewModel
import com.tem.plate.util.viewmodels.Placeholder
import dagger.Module
import dagger.android.ContributesAndroidInjector

class ContainerActivity : BaseActivity() {

    override val baseViewModel: BaseViewModel get() = viewModel

    private lateinit var binding: ActivityContainerBinding
    private val viewModel: ContainerViewModel by viewModels()
    private lateinit var navigator: BottomNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_container)
        lifecycle.addObserver(viewModel)
        navigator = BottomNavigator.onCreate(
            fragmentContainer = R.id.fragment_container,
            bottomNavigationView = findViewById(R.id.bottomnav_view),
            rootFragmentsFactory = mapOf(
                R.id.fragment1 to { Fragment1() }
            ),
            defaultTab = R.id.fragment1,
            activity = this
        )
        setupUi()
        super.onCreate(savedInstanceState)
    }

    override fun onBackPressed() {
        if (!navigator.pop()) {
            super.onBackPressed()
        }
    }

    private fun setupUi() {
        // Set clicklisteners and textListeners
    }

    override fun subscribeUi() {
        super.subscribeUi()
        // Set listeners to observeAction viewmodel livedatas
    }

    private fun onNextPlaceholder(placeholder: Placeholder?) {
        placeholder?.let { binding.includedLoading.placeholderShouldAppear = it.visible() }
    }

    companion object {

        fun createIntent(context: Context): Intent {
            return Intent(context, ContainerActivity::class.java)
        }
    }
}

@Module
interface ContainerActivityModule {

    @ContributesAndroidInjector
    @FragmentScope
    fun contributesFragment1(): Fragment1
}