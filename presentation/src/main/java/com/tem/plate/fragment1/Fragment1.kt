package com.tem.plate.fragment1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.tem.plate.R
import com.tem.plate.databinding.Fragment1Binding
import com.tem.plate.util.di.ViewModelFactory
import com.tem.plate.util.structure.base.BaseFragment
import com.tem.plate.util.structure.base.BaseViewModel
import kotlinx.android.synthetic.main.fragment1.view.*
import javax.inject.Inject

class Fragment1 : BaseFragment(R.layout.fragment1) {
    override val baseViewModel: BaseViewModel get() = viewModel

    @Inject
    protected lateinit var viewModelFactory: ViewModelFactory<Fragment1ViewModel>

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(Fragment1ViewModel::class.java)
    }

    private var _binding: Fragment1Binding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = Fragment1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}