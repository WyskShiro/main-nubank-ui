package com.tem.plate.container

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.animation.doOnEnd
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import com.tem.domain.entity.RecyclerItem
import com.tem.plate.container.accountstatus.AccountStatusAdapter
import com.tem.plate.container.actionoptions.ActionOptionsAdapter
import com.tem.plate.container.mainoptions.MainOptionsAdapter
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
    private val accountStatusAdapter by lazy {
        AccountStatusAdapter()
    }
    private var initialHeightAccountStatus = 0f
    private var initialYAccountStatus = 0f
    private var initialHeightActionOptions = 0f
    private var initialYActionMenu = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContainerBinding.inflate(layoutInflater)
        setupUi()
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
    }

    private fun onMainOptions(mainOptions: List<RecyclerItem>?) {
        mainOptionsAdapter.submitList(mainOptions)
    }

    private fun onActionOptions(actionOptions: List<RecyclerItem>?) {
        actionOptionsAdapter.submitList(actionOptions)
        accountStatusAdapter.submitList(actionOptions)
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
        binding.recyclerViewAccountStatus.apply {
            layoutManager = LinearLayoutManager(this@ContainerActivity)
            adapter = accountStatusAdapter
        }
    }

    private fun flipMenusVisibility(visibility: Boolean?) {
        visibility?.let { _isVisible ->
            if (initialHeightAccountStatus == 0f) {
                initialHeightAccountStatus = binding.recyclerViewAccountStatus.height.toFloat()
            }
            if (initialYAccountStatus == 0f) {
                initialYAccountStatus = binding.recyclerViewAccountStatus.y
            }
            if (initialHeightActionOptions == 0f) {
                initialHeightActionOptions = binding.recyclerViewActionOptions.height.toFloat()
            }
            if (initialYActionMenu == 0f) {
                initialYActionMenu = binding.recyclerViewActionOptions.y
            }
            val heightAnimatorActionOptions = if (_isVisible) {
                ValueAnimator.ofFloat(1f, initialHeightActionOptions)
            } else {
                ValueAnimator.ofFloat(initialHeightActionOptions, 1f)
            }
            val yAnimatorAccountStatus = if (_isVisible) {
                ValueAnimator.ofFloat(initialYActionMenu, initialYAccountStatus)
            } else {
                ValueAnimator.ofFloat(initialYAccountStatus, initialYActionMenu)
            }
            val heightAnimatorAccountStatus = if (_isVisible) {
                ValueAnimator.ofFloat(initialHeightActionOptions, initialHeightAccountStatus)
            } else {
                ValueAnimator.ofFloat(initialHeightAccountStatus, initialHeightActionOptions)
            }
            heightAnimatorAccountStatus.addUpdateListener {
                binding.recyclerViewAccountStatus.layoutParams.apply {
                    height = (it.animatedValue as Float).toInt()
                }.also {
                    binding.recyclerViewAccountStatus.layoutParams = it
                }
            }
            heightAnimatorActionOptions.addUpdateListener {
                binding.recyclerViewActionOptions.layoutParams.apply {
                    val value = (it.animatedValue as Float).toInt()
                    height = value
                    val shouldChange = value == 1
                    val isDifferentFromNow = shouldChange && binding.recyclerViewActionOptions.isInvisible
                    binding.recyclerViewActionOptions.isGone = shouldChange && isDifferentFromNow
                }.also {
                    binding.recyclerViewActionOptions.layoutParams = it
                }
            }
            yAnimatorAccountStatus.addUpdateListener {
                binding.recyclerViewAccountStatus.y = it.animatedValue as Float
            }
            AnimatorSet().apply {
                duration = 1000
                play(yAnimatorAccountStatus).with(heightAnimatorActionOptions)
                    .with(heightAnimatorAccountStatus)
                start()
            }
            if(!_isVisible) {
                val linearSmoothScroller = object : LinearSmoothScroller(this) {
                    override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
                        return 30f / displayMetrics.densityDpi
                    }
                }
                linearSmoothScroller.targetPosition = 0
                binding.recyclerViewAccountStatus.layoutManager!!.startSmoothScroll(linearSmoothScroller)
            }
            binding.isVisible = !_isVisible
        }
    }
}