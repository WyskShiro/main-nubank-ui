package com.tem.plate.fragment1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.tem.plate.R
import com.tem.plate.creditcard.FragmentCreditCardViewModel
import com.tem.plate.databinding.FragmentCreditCardBinding
import com.tem.plate.util.di.ViewModelFactory
import com.tem.plate.util.structure.base.BaseFragment
import com.tem.plate.util.structure.base.BaseViewModel
import javax.inject.Inject

class FragmentCreditCard : BaseFragment(R.layout.fragment_credit_card) {
    override val baseViewModel: BaseViewModel get() = viewModel

    @Inject
    protected lateinit var viewModelFactory: ViewModelFactory<FragmentCreditCardViewModel>

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(FragmentCreditCardViewModel::class.java)
    }

    private var _binding: FragmentCreditCardBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreditCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}