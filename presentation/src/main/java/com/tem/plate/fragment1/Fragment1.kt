package com.tem.plate.fragment1

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.tem.plate.R
import com.tem.plate.databinding.Fragment1Binding
import com.tem.plate.databinding.LoadingPlaceholderBinding
import com.tem.plate.util.di.ViewModelFactory
import com.tem.plate.util.structure.base.BaseFragment
import com.tem.plate.util.structure.base.BaseViewModel
import kotlinx.android.synthetic.main.fragment1.view.*
import javax.inject.Inject

class Fragment1 : BaseFragment(R.layout.fragment1) {
    override val baseViewModel: BaseViewModel get() = viewModel
    override val loadingPlaceholderBinding: LoadingPlaceholderBinding? get() = null

    @Inject
    protected lateinit var viewModelFactory: ViewModelFactory<Fragment1ViewModel>

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(Fragment1ViewModel::class.java)
    }
    private lateinit var binding: Fragment1Binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = Fragment1Binding.bind(view)
    }
}