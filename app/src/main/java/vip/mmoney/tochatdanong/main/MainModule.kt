package vip.mmoney.tochatdanong.main

import dagger.Module
import dagger.Binds
import vip.mmoney.tochatdanong.di.ActivityScope


@Module
abstract class MainModule {

    @ActivityScope
    @Binds
    internal abstract fun taskPresenter(presenter: MainPresenter): MainContract.Presenter
}