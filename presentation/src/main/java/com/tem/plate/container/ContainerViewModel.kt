package com.tem.plate.container

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tem.domain.entity.RecyclerItem
import com.tem.domain.interactor.GetActionOptions
import com.tem.domain.interactor.GetMainOptions
import com.tem.plate.util.structure.base.BaseViewModel
import javax.inject.Inject

class ContainerViewModel @Inject constructor(
    private val getMainOptions: GetMainOptions,
    private val getActionOptions: GetActionOptions
) : BaseViewModel() {

    val mainOptions: LiveData<List<RecyclerItem>> get() = _mainOptions
    val actionOptions: LiveData<List<RecyclerItem>> get() = _actionOptions

    private val _mainOptions by lazy { MutableLiveData<List<RecyclerItem>>() }
    private val _actionOptions by lazy { MutableLiveData<List<RecyclerItem>>() }

    init {
        _mainOptions.postValue(getMainOptions.execute())
        _actionOptions.postValue(getActionOptions.execute())
    }
}