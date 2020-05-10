package com.tem.plate.container

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tem.domain.entity.RecyclerItem
import com.tem.plate.databinding.ActivityContainerBinding
import com.tem.plate.util.di.ViewModelFactory
import com.tem.plate.util.extensions.getLineDivider
import com.tem.plate.util.extensions.getScreenHeight
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
    private var initialY = 0f

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
        viewModel.isExpanded.observeAction(this, ::flipMenusVisibility)
    }

    private fun setupUi() {
        setupRecyclerView()
        binding.imageViewArrow.setOnClickListener {
            viewModel.toggleExpand()
        }
        binding.expandLevel = 0f
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

    private fun flipMenusVisibility(visibility: Boolean?) {
        visibility?.let {
            if (initialY == 0f) {
                initialY = binding.customViewAccountStatus.y
            }
            val positionAnimator = if (it) {
                ValueAnimator.ofFloat(getScreenHeight(), initialY)
            } else {
                ValueAnimator.ofFloat(initialY, getScreenHeight())
            }
            val alphaAnimator = if(it) {
                ValueAnimator.ofFloat(1f, 0f)
            } else {
                ValueAnimator.ofFloat(0f, 1f)
            }
            positionAnimator.addUpdateListener {
                binding.customViewAccountStatus.y = it.animatedValue as Float
            }
            alphaAnimator.addUpdateListener {
                binding.expandLevel = it.animatedValue as Float
            }
            AnimatorSet().apply {
                play(positionAnimator).with(alphaAnimator)
                duration = 2000
                start()
            }
        }
    }
}