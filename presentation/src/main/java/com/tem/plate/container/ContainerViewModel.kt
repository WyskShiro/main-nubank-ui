package com.tem.plate.container

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tem.domain.entity.RecyclerItem
import com.tem.domain.interactor.GetMainOptions
import com.tem.plate.util.structure.base.BaseViewModel
import javax.inject.Inject

class ContainerViewModel @Inject constructor(
    private val getMainOptions: GetMainOptions
) : BaseViewModel() {

    val mainOptions: LiveData<List<RecyclerItem>> get() = _mainOptions

    private val _mainOptions by lazy { MutableLiveData<List<RecyclerItem>>() }

    init {
        _mainOptions.postValue(getMainOptions.execute())
    }
}