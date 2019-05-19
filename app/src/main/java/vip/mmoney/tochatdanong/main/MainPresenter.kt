package vip.mmoney.tochatdanong.main

import androidx.annotation.Nullable
import vip.mmoney.tochatdanong.services.DataService
import javax.inject.Inject

class MainPresenter @Inject constructor() : MainContract.Presenter {

    @Nullable
    private var mView: MainContract.View? = null

    override fun loadChapters() {
        mView?.showChapters(DataService.chapters)
    }

    override fun takeView(view: MainContract.View) {
        mView = view
    }

    override fun dropView() {
        if (mView != null) {
            mView = null
        }
    }
}