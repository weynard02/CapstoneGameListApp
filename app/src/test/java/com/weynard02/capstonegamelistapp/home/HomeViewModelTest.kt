package com.weynard02.capstonegamelistapp.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.weynard02.capstonegamelistapp.utils.DataDummy
import com.weynard02.capstonegamelistapp.utils.getOrAwaitValue
import com.weynard02.core.data.GameRepository
import com.weynard02.core.data.Resource
import com.weynard02.core.domain.usecase.GameInteractor
import com.weynard02.core.domain.usecase.GameUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var gameRepository: GameRepository
    private lateinit var gameUseCase: GameUseCase
    private lateinit var homeViewModel: HomeViewModel
    private val dummyGames = DataDummy.generateDummyGame()

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)

        MockitoAnnotations.openMocks(this)
        gameUseCase = GameInteractor(gameRepository)
        `when`(gameUseCase.getAllGame()).thenReturn(flowOf(Resource.Success(dummyGames)))

        homeViewModel = HomeViewModel(gameUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when Get Games Should Not Be Null and Return Success`() {
        val actualGames = homeViewModel.games.getOrAwaitValue()
        Assert.assertNotNull(actualGames)
        Assert.assertTrue(actualGames is Resource.Success)
        Assert.assertEquals(dummyGames.size, (actualGames as Resource.Success).data?.size)
    }
}
