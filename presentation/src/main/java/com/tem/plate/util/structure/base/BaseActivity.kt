package com.tem.plate.util.structure.base

import android.app.Dialog
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.tem.plate.util.extensions.observeEvent
import com.tem.plate.util.extensions.shortToast
import com.tem.plate.util.extensions.showDialog
import com.tem.plate.util.viewmodels.DialogData
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity(), HasAndroidInjector {

    abstract val baseViewModel: BaseViewModel
    private var dialog: Dialog? = null

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        subscribeUi()
    }

    open fun subscribeUi() {
        with(baseViewModel) {
            toast.observeEvent(this@BaseActivity, ::onNextToast)
            dialog.observeEvent(this@BaseActivity, ::onNextDialog)
        }
    }

    private fun onNextToast(text: String?) {
        text?.let {
            shortToast(it)
        }
    }

    protected fun onNextDialog(dialogData: DialogData?) {
        dialog?.dismiss()
        dialog = dialogData?.let { showDialog(it) }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}