package com.tem.plate.util.structure.base

import androidx.lifecycle.*
import com.tem.plate.util.ErrorHandler
import com.tem.plate.util.structure.arch.Event
import com.tem.plate.util.viewmodels.DialogData
import com.tem.plate.util.viewmodels.Placeholder
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

open class BaseViewModel : LifecycleObserver, ViewModel() {
    val toast: LiveData<Event<String>> get() = toastLiveData
    val placeholder: LiveData<Placeholder> get() = placeholderLiveData
    val dialog: LiveData<Event<DialogData>> get() = dialogLiveData

    private val placeholderLiveData = MutableLiveData<Placeholder>()
    private val toastLiveData = MutableLiveData<Event<String>>()
    private val dialogLiveData = MutableLiveData<Event<DialogData>>()

    @Inject
    protected lateinit var errorHandler: ErrorHandler

    fun setPlaceholder(placeholder: Placeholder) {
        placeholderLiveData.postValue(placeholder)
    }

    fun setToast(message: String) {
        toastLiveData.postValue(Event(message))
    }

    fun onFailure(throwable: Throwable, retryAction: (() -> Unit)? = null) {
        setDialog(throwable, retryAction)
    }

    fun setDialog(dialogData: DialogData) {
        dialogLiveData.postValue(Event(dialogData))
    }

    fun setDialog(
        throwable: Throwable,
        retryAction: (() -> Unit)? = null,
        onDismiss: (() -> Unit)? = null
    ) {
        setDialog(errorHandler.getDialogData(throwable, retryAction, onDismiss))
    }

    protected fun launchDataLoad(
        shouldLoad: Boolean = true,
        onFailure: (Throwable) -> Unit,
        block: suspend () -> Unit
    ): Job {
        return viewModelScope.launch {
            try {
                if (shouldLoad) setPlaceholder(Placeholder.Loading())
                block()
            } catch (error: Throwable) {
                onFailure(error)
            } finally {
                setPlaceholder(Placeholder.HideAll)
            }
        }
    }
}
