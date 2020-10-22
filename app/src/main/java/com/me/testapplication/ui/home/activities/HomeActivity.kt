package com.me.testapplication.ui.home.activities

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.me.testapplication.BuildConfig
import com.me.testapplication.R
import com.me.testapplication.ui.base.activities.BaseActivity
import com.me.testapplication.ui.base.adapters.*
import com.me.testapplication.ui.base.views.BaseView
import com.me.testapplication.ui.hello.activities.HelloActivity
import com.me.testapplication.ui.home.adapters.HomeCategoriesRVAdapter
import com.me.testapplication.ui.home.presenters.HomePresenter
import com.me.testapplication.utils.commons.OnSwipeTouchListener
import com.me.testapplication.utils.transformers.CircleTransform
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.content_toolbar.*
import moxy.ktx.moxyPresenter
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType
import java.util.*

@StateStrategyType(OneExecutionStateStrategy::class)
interface HomeView : BaseView {

    fun onUpdateList(list: List<BaseModel>)
    fun onUpdateMenu(list: List<BaseModel>)
    fun onUpdateUser(name: String, avatar: String, location: String)
    fun successLogout()
}

class HomeActivity : BaseActivity(), HomeView, BaseRVAdapter.BaseListClickListener<BaseModel> {

    private val presenter: HomePresenter by moxyPresenter { HomePresenter() }

    private lateinit var rvAdapter: HomeCategoriesRVAdapter
    private lateinit var menuAdapter: NavigationMenuAdapter

    private var isMenuOpen = false
    private var isGrid = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        rvAdapter = HomeCategoriesRVAdapter(this)
        rvAdapter.listClickListener = this
        rv_home_main.layoutManager = LinearLayoutManager(this)
        rv_home_main.adapter = rvAdapter

        menuAdapter = NavigationMenuAdapter(this)
        menuAdapter.listClickListener = this
        rv_main_menu.layoutManager = LinearLayoutManager(this)
        rv_main_menu.adapter = menuAdapter
        presenter.updateMenu()

        iv_main_open_menu.setOnClickListener {
            openMenu()
        }

        iv_main_close_menu.setOnClickListener {
            closeMenu()
        }

        ll_logout.setOnClickListener {
            presenter.logout()
        }

        tv_version_code.text = String.format(
            Locale.getDefault(),
            resources.getString(R.string.version),
            BuildConfig.VERSION_NAME
        )

        val context = this
        val touchListener = object : OnSwipeTouchListener(context) {

            override fun onSwipeRight() {
                super.onSwipeRight()
                openMenu()
            }

            override fun onSwipeLeft() {
                super.onSwipeLeft()
                closeMenu()
            }
        }

        ll_menu.setOnTouchListener(touchListener)
        rv_home_main.setOnTouchListener(touchListener)
    }

    override fun onResume() {
        super.onResume()
        presenter.updateData()
    }

    private fun openMenu() {
        val openAnimator =
            AnimatorInflater.loadAnimator(this, R.animator.open_animator) as AnimatorSet
        openAnimator.setTarget(ll_home)

        if (!isMenuOpen) {
            ll_home.background =
                ResourcesCompat.getDrawable(resources, R.drawable.rect_white_rounded, this.theme)
            openAnimator.start()
            isMenuOpen = true
        }
    }

    private fun closeMenu() {
        val closeAnimator =
            AnimatorInflater.loadAnimator(this, R.animator.close_animator) as AnimatorSet
        closeAnimator.setTarget(ll_home)

        if (isMenuOpen) {
            closeAnimator.start()
            ll_home.background =
                ResourcesCompat.getDrawable(resources, R.color.white_fff, this.theme)
            isMenuOpen = false
        }
    }

    override fun onUpdateList(list: List<BaseModel>) {
        rvAdapter.update(list)
    }

    override fun onListClick(view: View, item: BaseModel, position: Int) {
        when (item) {
            is MenuItemModel -> presenter.updateMenu(position)
        }
    }

    override fun onUpdateUser(name: String, avatar: String, location: String) {
        tv_name.text = name
        tv_location.text = location

        Picasso.get()
            .load(avatar)
            .transform(
                CircleTransform(
                    2 * Resources.getSystem().displayMetrics.density,
                    R.color.blue_3a4
                )
            )
            .placeholder(R.drawable.ic_avatar_placeholder)
            .into(iv_avatar)
    }

    override fun successLogout() {
        startActivity(HelloActivity.newIntent(this))
        finish()
    }

    override fun onUpdateMenu(list: List<BaseModel>) {
        menuAdapter.update(list)
        menuAdapter.notifyDataSetChanged()
    }

    companion object {

        fun newIntent(context: Context) = Intent(context, HomeActivity::class.java)
    }
}