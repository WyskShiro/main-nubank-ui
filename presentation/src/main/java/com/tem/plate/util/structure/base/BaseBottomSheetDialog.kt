package com.tem.plate.util.structure.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.FrameLayout
import androidx.annotation.CallSuper
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tem.plate.R
import com.tem.plate.container.ContainerActivity
import com.tem.plate.util.extensions.observeAction
import com.tem.plate.util.extensions.observeEvent
import com.tem.plate.util.extensions.showDialog
import com.tem.plate.util.viewmodels.DialogData
import com.tem.plate.util.viewmodels.Placeholder
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseBottomSheetDialog : BottomSheetDialogFragment(), HasAndroidInjector {

    abstract val baseViewModel: BaseViewModel
    protected val parentActivity: ContainerActivity by lazy { activity as ContainerActivity }
    private var fragmentDialog: Dialog? = null

    @Inject
    protected lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

    open fun onGetDialog(dialogData: DialogData?) {
        dialogData?.let {
            fragmentDialog?.dismiss()
            fragmentDialog = activity?.showDialog(it)
        }
    }

    @CallSuper
    protected open fun subscribe() {
        baseViewModel.dialog.observeEvent(viewLifecycleOwner, ::onGetDialog)
    }

    protected fun setupExpanded() {
        dialog?.setOnShowListener {
            val d = dialog as BottomSheetDialog
            val bottomSheet =
                d.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
            val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet!!)
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            bottomSheetBehavior.peekHeight = bottomSheet.height
        }
    }
}
