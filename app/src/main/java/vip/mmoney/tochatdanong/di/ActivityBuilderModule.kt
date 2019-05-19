package vip.mmoney.tochatdanong.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import vip.mmoney.tochatdanong.ChapterContentActivity
import vip.mmoney.tochatdanong.main.MainActivity
import vip.mmoney.tochatdanong.SplashScreenActivity
import vip.mmoney.tochatdanong.main.MainModule

@Module
abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainModule::class])
    internal abstract fun contributeMainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector
    internal abstract fun contributeSplashScreenActivity(): SplashScreenActivity

    @ActivityScope
    @ContributesAndroidInjector
    internal abstract fun contributeChapterContentActivity(): ChapterContentActivity
}