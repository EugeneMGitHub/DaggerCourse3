package com.example.daggerp3organizaciamodulei

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.daggerp3organizaciamodulei.CreatingVM.easyWay.SomeViewModel
import com.example.daggerp3organizaciamodulei.CreatingVM.withAssisted.ViewModelwithAssisted
import com.example.daggerp3organizaciamodulei.data.Analytics
import com.example.daggerp3organizaciamodulei.data.News
import com.example.daggerp3organizaciamodulei.data.NewsRepository
import com.example.daggerp3organizaciamodulei.ui.theme.DaggerP3OrganizaciaModuleiTheme
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    private val newsId = "121"
//    private val viewModel: NewsDetailsViewModel by viewModels {
//        factory.create(newsId = newsId)
//    }

    @Inject
    lateinit var analytics: Analytics

//    @Inject
//    lateinit var factory: NewsDetailsViewModel.Factory.Factory




    @Inject
    lateinit var factoryForFactory: SomeViewModel.Factory.FactoryForFactory

    private val someViewModel: SomeViewModel by viewModels {
        factoryForFactory.create(newsId = "random News")
    }



    @Inject
    lateinit var factoryWithAssistedInject: ViewModelwithAssisted.Factory.FactoryForFactory

    private val viewModelWithAssisted: ViewModelwithAssisted by viewModels {
        factoryWithAssistedInject.create(newsId = "News with assisted")
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appComponent.inject(this)







        setContent {
            DaggerP3OrganizaciaModuleiTheme {
                // A surface container using the 'background' color from the theme


                someViewModel.getMessage()
                viewModelWithAssisted.getMessage()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {


                    Greeting("Android")
                }
            }
        }
    }

    @Inject
    fun doSomethingFun(analytics: Analytics){
        analytics.trackScreenShow()
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DaggerP3OrganizaciaModuleiTheme {
        Greeting("Android")
    }
}

/**Ступень 1*/
//class NewsDetailsViewModel(
//    private val newsId: String,
//    private val newsRepository: NewsRepository
//) : ViewModel(){
//
//    val news: Flow<News> =
//        flow<News>{ newsRepository.getNews(newsId) }
//            .shareIn(viewModelScope, SharingStarted.Lazily, replay = 1)
//
//}

//class NewsDetailViewModelFactory (
//    private val newsId: String,
//    private val newsRepository: NewsRepository
//) : ViewModelProvider.Factory {
//
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
////        require(value = modelClass == NewsDetailsViewModel::class)
//        return NewsDetailsViewModel(
//            newsId = newsId,
//            newsRepository = newsRepository
//        ) as T
//    }
//}


/**Ступень 2*/
//class NewsDetailsViewModel(
//    private val newsId: String,
//    private val newsRepository: NewsRepository
//) : ViewModel(){
//
//    val news: Flow<News> =
//        flow<News>{ newsRepository.getNews(newsId) }
//            .shareIn(viewModelScope, SharingStarted.Lazily, replay = 1)
//
//    class Factory(
//        private val newsId: String,
//        private val newsRepository: NewsRepository
//    ) : ViewModelProvider.Factory {
//
//        @Suppress("UNCHECKED_CAST")
//        override fun <T : ViewModel> create(modelClass: Class<T>): T {
////        require(value = modelClass == NewsDetailsViewModel::class)
//            return NewsDetailsViewModel(newsId = newsId, newsRepository = newsRepository) as T
//         }
//
//        class Factory @Inject constructor (val newsRepository: NewsRepository){
//            fun create(newsId: String) : NewsDetailsViewModel.Factory{
//                return NewsDetailsViewModel.Factory(newsId, newsRepository = newsRepository)
//            }
//        }
//    }
//}

/**Ступень 3*/
//class NewsDetailsViewModel(
//    private val newsId: String,
//    private val newsRepository: NewsRepository
//) : ViewModel(){
//
//    val news: Flow<News> =
//        flow<News>{ newsRepository.getNews(newsId) }
//            .shareIn(viewModelScope, SharingStarted.Lazily, replay = 1)
//
//    class Factory @AssistedInject constructor(
//       @Assisted(value = "newsId") private val newsId: String,
//        private val newsRepository: NewsRepository
//    ) : ViewModelProvider.Factory {
//
//        @Suppress("UNCHECKED_CAST")
//        override fun <T : ViewModel> create(modelClass: Class<T>): T {
////        require(value = modelClass == NewsDetailsViewModel::class)
//            return NewsDetailsViewModel(newsId = newsId, newsRepository = newsRepository) as T
//        }
//
//        @AssistedFactory
//        interface Factory {
//            fun create(@Assisted(value = "newsId") newsId: String) : NewsDetailsViewModel.Factory
//        }
//    }
//}
