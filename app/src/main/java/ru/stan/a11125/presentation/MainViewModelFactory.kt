package ru.stan.a11125.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.stan.a11125.data.RepositoryImpl.CharacterRepositoryImpl
import ru.stan.a11125.domain.useCase.GetCharacterListUseCase
import ru.stan.a11125.domain.useCase.GetCharacterUseCase

class MainViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            val repo = CharacterRepositoryImpl()
            val useCase = GetCharacterUseCase(repo)
            val useCase2 = GetCharacterListUseCase(repo)
            return MainViewModel(repo,useCase2,useCase) as T
        }
        throw IllegalArgumentException("неизвестное имя классса")
    }
}