package com.me.testapplication.ui.base.activities

import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.app.AlertDialog
import com.me.testapplication.R
import com.me.testapplication.ui.base.views.BaseView
import moxy.MvpAppCompatActivity

abstract class BaseActivity : MvpAppCompatActivity(), BaseView {

    private var alertDialog: AlertDialog? = null
    private var progressBar: RelativeLayout? = null

//    override fun setContentView(layoutResID: Int) {
//        super.setContentView(layoutResID)
////        progressBar = findViewById(R.id.rl_progress)
//    }

    override fun showProgressBar(isShow: Boolean) {
        progressBar?.visibility = if (isShow) View.VISIBLE else View.GONE
    }

    override fun showMessage(title: String?, message: String) {
        if (alertDialog?.isShowing != null && !isFinishing) {
            alertDialog =
                AlertDialog.Builder(this, R.style.Widget_AppCompat_ButtonBar_AlertDialog).apply {
                    title?.let { setTitle(it) }
                    setMessage(message)
                }.show()
        }
    }
}