package ru.stan.a11125.domain.useCase

import ru.stan.a11125.domain.model.CharacterModel
import ru.stan.a11125.domain.repository.CharacterRepository

class GetCharacterListUseCase(
    private val repo: CharacterRepository
) {
    suspend fun getCharacterList(): List<CharacterModel> {
        return repo.getCharacters()
    }
}