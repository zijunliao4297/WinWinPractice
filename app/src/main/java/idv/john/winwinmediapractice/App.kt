package idv.john.winwinmediapractice

import android.app.Application
import idv.john.winwinmediapractice.retrofits.PostRepository
import idv.john.winwinmediapractice.ui.PostsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {
    private val mViewModelModule = module {
        viewModel {
            PostsViewModel(get(), get())
        }
        single {
            PostRepository()
        }
    }

    private val mRepoModule = module {
        single {
            PostRepository()
        }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(mViewModelModule)
        }
    }
}