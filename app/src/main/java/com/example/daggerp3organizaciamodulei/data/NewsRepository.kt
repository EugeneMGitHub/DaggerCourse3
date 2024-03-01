package com.example.daggerp3organizaciamodulei.data

import com.example.daggerp3organizaciamodulei.Stage
import javax.inject.Inject

interface NewsRepository {

    suspend fun getNews(newsId: String): News
}

class NewsRepositoryImpl @Inject constructor(
//    @Stage private val newsService: NewsService,
    private val newsService: NewsService,
    private val analytics: Analytics,
) : NewsRepository {

    override suspend fun getNews(newsId: String): News {
        analytics.trackNewsRequest(newsId)
        return newsService.news(newsId)
    }
}

//class NewsRepositoryImpl @Inject constructor(
//    @Stage private val newsService: NewsService,
//    private val analytics: Analytics,
//) : NewsRepository {
//
//    override suspend fun getNews(newsId: String): News {
//        analytics.trackNewsRequest(newsId)
//        return newsService.news(newsId)
//    }
//}