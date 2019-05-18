package vip.mmoney.tochatdanong.base

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import vip.mmoney.tochatdanong.di.DaggerAppComponent

class BaseApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}