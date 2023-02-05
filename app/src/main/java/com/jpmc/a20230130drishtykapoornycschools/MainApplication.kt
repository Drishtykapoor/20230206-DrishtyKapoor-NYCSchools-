package com.jpmc.a20230130drishtykapoornycschools

import com.jpmc.a20230130drishtykapoornycschools.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class MainApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerApplicationComponent.builder().application(this).addContext(this).build()

}