@file:Suppress("DEPRECATION")

package com.dicoding.shof.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.dicoding.shof.core.data.remote.network.ApiResponse
import com.dicoding.shof.core.domain.model.Games
import com.dicoding.shof.core.domain.usecase.GamesUseCase
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var gamesUseCase: GamesUseCase

    @Mock
    private lateinit var observer: Observer<ApiResponse<List<Games>>>

    @Captor
    private lateinit var captor: ArgumentCaptor<ApiResponse<List<Games>>>

    private lateinit var viewModel: HomeViewModel

    @OptIn(DelicateCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate) // Set the main dispatcher for testing
        MockitoAnnotations.openMocks(this)
        viewModel = HomeViewModel(gamesUseCase)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain() // Reset main dispatcher after testing
        mainThreadSurrogate.close()
    }

    private fun <T> capture(argumentCaptor: ArgumentCaptor<T>): T = argumentCaptor.capture()

    @Test
    fun `getAllGames success`() = runBlockingTest {
        // Given
        val dummyGames = listOf(Games(1, "Dummy Game", "", 0.0, ""))
        val dummyResponse = ApiResponse.Success(dummyGames)
        val query = "query"

        `when`(gamesUseCase.getAllGames(query)).thenReturn(flowOf(dummyResponse))

        // When
        viewModel.query.value = query
        viewModel.searchResult.observeForever(observer)

        // Then
        verify(observer, timeout(1000)).onChanged(capture(captor))
        assertEquals(dummyResponse, captor.value)

        // Cleanup
        viewModel.searchResult.removeObserver(observer)
    }

    @Test
    fun `getAllGames error`() = runBlockingTest {
        // Given
        val query = "query"
        val dummyError = "Error message"
        val dummyResponse = ApiResponse.Error(dummyError)

        `when`(gamesUseCase.getAllGames(query)).thenReturn(flowOf(dummyResponse))

        // When
        viewModel.query.value = query
        viewModel.searchResult.observeForever(observer)

        // Then
        verify(observer, timeout(1000)).onChanged(capture(captor))
        assertEquals(dummyResponse, captor.value)

        // Cleanup
        viewModel.searchResult.removeObserver(observer)
    }

}
