package com.tem.plate.util.structure.base

import android.app.Dialog
import android.content.Context
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.tem.plate.container.ContainerActivity
import com.tem.plate.util.extensions.observeEvent
import com.tem.plate.util.extensions.showDialog
import com.tem.plate.util.viewmodels.DialogData
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment(@LayoutRes layoutRes: Int) : Fragment(layoutRes), HasAndroidInjector {

    abstract val baseViewModel: BaseViewModel
    protected val parentActivity: ContainerActivity by lazy { activity as ContainerActivity }
    private var dialog: Dialog? = null

    open fun onGetDialog(dialogData: DialogData?) {
        dialogData?.let {
            dialog?.dismiss()
            dialog = activity?.showDialog(it)
        }
    }

    @Inject
    protected lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

    @CallSuper
    protected open fun subscribe() {
        baseViewModel.dialog.observeEvent(viewLifecycleOwner, ::onGetDialog)
    }
}
