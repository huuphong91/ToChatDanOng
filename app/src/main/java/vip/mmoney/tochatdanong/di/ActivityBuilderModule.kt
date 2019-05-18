package vip.mmoney.tochatdanong.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import vip.mmoney.tochatdanong.MainActivity

@Module
abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector
    internal abstract fun contributeMainActivity(): MainActivity
}