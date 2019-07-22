package com.testapp.nick.voltbank

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.testapp.nick.voltbank.DB.PoliceDataRepository
import com.testapp.nick.voltbank.Model.PoliceDataModel
import com.testapp.nick.voltbank.viewModel.PoliceDataViewModel
import org.junit.Rule
import org.junit.Test

class PoliceDataViewModelTest {
    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val viewStateObserver: Observer<PoliceDataModel> = mock()
    private val mockMatchAndTransactionRepository: PoliceDataRepository = mock()

    @Test
    fun testCryptoListUseCases_getCryptoList_Completed() {
       /* whenever(mockMatchAndTransactionRepository.getLiveDataCrimes())
            .thenReturn()*/
    }
}
