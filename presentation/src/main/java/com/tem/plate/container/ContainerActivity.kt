package com.tem.plate.container

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.graphics.Point
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
    private var initialTranslationY = 0f

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
        viewModel.isExpanded.observeAction(this) {
            it?.let {
                if (initialTranslationY == 0f) {
                    val a = IntArray(2)
                    binding.customViewAccountStatus.getLocationOnScreen(a)
                    initialTranslationY = binding.customViewAccountStatus.y
                }
                if (it) {
                    val display = windowManager.defaultDisplay
                    val size = Point()
                    display.getSize(size)
                    val positionAnimator = ValueAnimator.ofFloat(
                        size.y.toFloat(),
                        initialTranslationY
                    )
                    positionAnimator.addUpdateListener {
                        val value = it.animatedValue as Float
                        binding.customViewAccountStatus.y = value
                    }

                    val alphaAnimator = ValueAnimator.ofFloat(1f, 0f)
                    alphaAnimator.addUpdateListener {
                        val value = it.animatedValue as Float
                        binding.expandLevel = value
                    }

                    val animatorSet = AnimatorSet()
                    animatorSet.play(positionAnimator).with(alphaAnimator)
                    animatorSet.duration = 2000
                    animatorSet.start()
                } else {
                    val display = windowManager.defaultDisplay
                    val size = Point()
                    display.getSize(size)
                    val positionAnimator = ValueAnimator.ofFloat(
                        initialTranslationY,
                        size.y.toFloat()
                    )
                    positionAnimator.addUpdateListener {
                        val value = it.animatedValue as Float
                        binding.customViewAccountStatus.y = value
                    }

                    val alphaAnimator = ValueAnimator.ofFloat(0f, 1f)
                    alphaAnimator.addUpdateListener {
                        val value = it.animatedValue as Float
                        binding.expandLevel = value
                    }

                    val animatorSet = AnimatorSet()
                    animatorSet.play(positionAnimator).with(alphaAnimator)
                    animatorSet.duration = 2000
                    animatorSet.start()
                }
            }
        }
    }

    private fun setupUi() {
        setupRecyclerView()
        binding.imageViewArrow.setOnClickListener {
            viewModel.toggleExpand()
        }
        binding.expandLevel = 0f
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