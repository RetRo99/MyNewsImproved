package com.example.mynewsimproved

import com.example.mynewsimproved.api.mapper.RestaurantMapper
import com.example.mynewsimproved.api.response.SearchResponse
import com.example.mynewsimproved.api.response.TopStoryArticle
import com.example.mynewsimproved.api.response.TopStoryMultimedia
import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

class MapperTest {

    private lateinit var mapper: RestaurantMapper

    @Before
    fun setup() {
        mapper = RestaurantMapper()
    }

    @Test
    fun mapEmptySearchedArticles() {
        val mapperResult = mapper.mapSearchResultToUi(arrayListOf())
        assertThat(mapperResult.size, `is`(0))
    }

    @Test
    fun mapThreeSearchedArticles() {
        val multimedia = arrayListOf(
            SearchResponse.ResponseSearch.SearchedArticle.SearchMedia("test_media1"),
            SearchResponse.ResponseSearch.SearchedArticle.SearchMedia("test_media2"),
            SearchResponse.ResponseSearch.SearchedArticle.SearchMedia("test_media3")
        )
        val mapperResult = mapper.mapSearchResultToUi(
            arrayListOf(
                SearchResponse.ResponseSearch.SearchedArticle(
                    "1234567890useless",
                    "test_url",
                    "test_abstract",
                    multimedia,
                    "section_test",
                    "subsection_test"
                ),
                SearchResponse.ResponseSearch.SearchedArticle(
                    "1234567890useless",
                    "test_url",
                    "test_abstract",
                    multimedia,
                    "section_test",
                    "subsection_test"
                ),
                SearchResponse.ResponseSearch.SearchedArticle(
                    "1234567890useless",
                    "test_url",
                    "test_abstract",
                    multimedia,
                    "section_test",
                    "subsection_test"
                )
            )
        )
        assertThat(mapperResult.size, `is`(3))
    }

    @Test
    fun mapSearchedArticlesWithCorrectUi() {
        val multimedia = arrayListOf(
            SearchResponse.ResponseSearch.SearchedArticle.SearchMedia("test_media1"),
            SearchResponse.ResponseSearch.SearchedArticle.SearchMedia("test_media2"),
            SearchResponse.ResponseSearch.SearchedArticle.SearchMedia("test_media3")
        )
        val mapperResult = mapper.mapSearchResultToUi(
            arrayListOf(
                SearchResponse.ResponseSearch.SearchedArticle(
                    "1234567890useless",
                    "test_url",
                    "test_abstract",
                    multimedia,
                    "section_test",
                    "subsection_test"
                )
            )
        )
        val article = mapperResult[0]

        assertThat(article.abstract, `is`("test_abstract"))
        assertThat(article.publishedDate, `is`("1234567890"))
        assertThat(article.section, `is`("Section_test"))
        assertThat(article.webUrl, `is`("test_url"))
        assertThat(article.photoUrl, `is`("https://static01.nyt.com/test_media1"))
    }

    @Test
    fun mapEmptyTopStoriesArticles() {
        val mapperResult = mapper.mapTopStoriesToUi(arrayListOf())
        assertThat(mapperResult.size, `is`(0))
    }

    @Test
    fun mapTheeTopStories() {

        val multimedia = arrayListOf(
            TopStoryMultimedia("test_url1"),
            TopStoryMultimedia("test_url2"),
            TopStoryMultimedia("test_url3")
        )


        val mapperResult = mapper.mapTopStoriesToUi(
            arrayListOf(
                TopStoryArticle("test_abstract", "1234567890useless", "section_test", "test_url", "subsection_test", multimedia),
                TopStoryArticle("test_abstract", "1234567890useless", "section_test", "test_url", "subsection_test", multimedia),
                TopStoryArticle("test_abstract", "1234567890useless", "section_test", "test_url", "subsection_test", multimedia)))

        assertThat(mapperResult.size, `is`(3))

    }

    @Test
    fun mapTopStoriesToCorrectUi() {
        val multimedia = arrayListOf(
            TopStoryMultimedia("test_url1"),
            TopStoryMultimedia("test_url2"),
            TopStoryMultimedia("test_url3")
        )


        val mapperResult = mapper.mapTopStoriesToUi(arrayListOf(TopStoryArticle("test_abstract", "1234567890useless", "section_test", "subsection_test", "test_url", multimedia)))

        val article = mapperResult[0]
        assertThat(article.abstract, `is`("test_abstract"))
        assertThat(article.publishedDate, `is`("1234567890"))
        assertThat(article.section, `is`("Section_test"))
        assertThat(article.webUrl, `is`("test_url"))
        assertThat(article.photoUrl, `is`("test_url1"))

    }
}


