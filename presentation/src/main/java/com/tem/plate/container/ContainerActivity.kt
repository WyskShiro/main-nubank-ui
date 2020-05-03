package com.tem.plate.container

import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.tem.domain.entity.RecyclerItem
import com.tem.plate.R
import com.tem.plate.databinding.ActivityContainerBinding
import com.tem.plate.fragment1.Fragment1
import com.tem.plate.util.di.ViewModelFactory
import com.tem.plate.util.di.scopes.FragmentScope
import com.tem.plate.util.extensions.getLineDivider
import com.tem.plate.util.extensions.observeAction
import com.tem.plate.util.structure.base.BaseActivity
import com.tem.plate.util.structure.base.BaseViewModel
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Inject

class ContainerActivity : BaseActivity() {

    override val baseViewModel: BaseViewModel get() = viewModel

    private lateinit var binding: ActivityContainerBinding

    @Inject
    protected lateinit var viewModelFactory: ViewModelFactory<ContainerViewModel>

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(ContainerViewModel::class.java)
    }
    private val mainOptionsAdapter by lazy {
        MainAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContainerBinding.inflate(layoutInflater)
        setupUi()
        setContentView(binding.root)
    }

    override fun subscribeUi() {
        super.subscribeUi()
        viewModel.mainOptions.observeAction(this, ::onMainOptions)
    }

    private fun setupUi() {
        setupRecyclerView()
        // Set clicklisteners and textListeners
    }

    private fun onMainOptions(mainOptions: List<RecyclerItem>?) {
        mainOptionsAdapter.submitList(mainOptions)
    }

    private fun setupRecyclerView() {
        binding.recyclerViewMainOptions.apply {
            layoutManager = LinearLayoutManager(this@ContainerActivity)
            adapter = mainOptionsAdapter
            addItemDecoration(getLineDivider())
        }
    }
}

@Module
interface ContainerActivityModule {

    @ContributesAndroidInjector
    @FragmentScope
    fun contributesFragment1(): Fragment1
}