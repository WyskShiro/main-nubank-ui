package com.tem.plate.container

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tem.domain.entity.RecyclerItem
import com.tem.plate.databinding.ActivityContainerBinding
import com.tem.plate.util.di.ViewModelFactory
import com.tem.plate.util.extensions.getLineDivider
import com.tem.plate.util.extensions.observeAction
import com.tem.plate.util.structure.base.BaseActivity
import com.tem.plate.util.structure.base.BaseViewModel
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
        MainOptionsAdapter()
    }
    private val actionOptionsAdapter by lazy {
        ActionOptionsAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContainerBinding.inflate(layoutInflater)
        setupUi()
        setupViewPager()
        setContentView(binding.root)
    }

    override fun subscribeUi() {
        super.subscribeUi()
        viewModel.mainOptions.observeAction(this, ::onMainOptions)
        viewModel.actionOptions.observeAction(this, ::onActionOptions)
    }

    private fun setupUi() {
        setupRecyclerView()
        // Set clicklisteners and textListeners
    }

    private fun onMainOptions(mainOptions: List<RecyclerItem>?) {
        mainOptionsAdapter.submitList(mainOptions)
    }

    private fun onActionOptions(actionOptions: List<RecyclerItem>?) {
        actionOptionsAdapter.submitList(actionOptions)
    }

    private fun setupRecyclerView() {
        binding.recyclerViewMainOptions.apply {
            layoutManager = LinearLayoutManager(this@ContainerActivity)
            adapter = mainOptionsAdapter
            addItemDecoration(getLineDivider())
        }
        binding.recyclerViewActionOptions.apply {
            layoutManager =
                LinearLayoutManager(this@ContainerActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = actionOptionsAdapter
        }
    }

    private fun setupViewPager() {
        binding.customViewAccountStatus.setAdapter(supportFragmentManager)
    }
}