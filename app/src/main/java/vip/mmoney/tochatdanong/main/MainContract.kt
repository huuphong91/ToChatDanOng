package vip.mmoney.tochatdanong.main

import vip.mmoney.tochatdanong.base.BasePresenter
import vip.mmoney.tochatdanong.base.BaseView
import vip.mmoney.tochatdanong.model.Chapter

interface MainContract {
    interface View : BaseView<Presenter> {
        fun showChapters(chapters: List<Chapter>)
    }

    interface Presenter : BasePresenter<View> {
        fun loadChapters()
    }
}