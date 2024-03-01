package com.example.daggerp3organizaciamodulei

import com.example.daggerp3organizaciamodulei.data.Analytics
import com.example.daggerp3organizaciamodulei.data.NewsRepository
import com.example.daggerp3organizaciamodulei.data.NewsRepositoryImpl
import com.example.daggerp3organizaciamodulei.data.NewsService
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Qualifier

@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)


//    fun inject(fragment: NewsDetailsFragment)
}

@Module(includes = [NetworkModule::class, AppBindModule::class])
class AppModule

@Module
class NetworkModule {

    @Provides
//    @Named("prod")
    fun provideProductionNewsService(): NewsService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://androidbrodcast.dev")
            .build()
        return retrofit.create()
    }

    @Provides
//    @Named("stage")
    @Stage
    fun provideStageNewsService(): NewsService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://stage.androidbrodcast.dev")
            .build()
        return retrofit.create()
    }


    @Provides
    fun provideDependency1() : String {
        return "dependency1"
    }

    @Provides
    fun provideRetrofitClassObject() : RetrofitClass {
        return RetrofitClass()
    }

//    @Provides
//    fun provideAnotherRetrofitClass() : RetrofitClass {
//        return RetrofitClass()
//    }


}

@Module
interface AppBindModule {

//    @Provides
//    fun provideNewsRepository(
//        newsService: NewsService,
//        analytics: Analytics
//    ) : NewsRepository {
//        return NewsRepositoryImpl(
//            newsService = newsService,
//            analytics = analytics
//        )
//    }

//    @Suppress("FunctionName")
    @Binds
    fun bindNewsRepositoryImplToNewsRepository(
        newsRepositoryImpl: NewsRepositoryImpl
    ): NewsRepository
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Prod


@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Stage


class SomeRepository @Inject constructor() {
    val someRepository  = "SomeRepository"
}

class RetrofitClass()  {
    fun getMessage() = "Message from RetrofitClass object"
}
