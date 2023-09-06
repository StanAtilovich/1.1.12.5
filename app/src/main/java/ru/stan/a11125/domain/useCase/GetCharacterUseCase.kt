package ru.stan.a11125.domain.useCase

import ru.stan.a11125.domain.model.CharacterModel
import ru.stan.a11125.domain.repository.CharacterRepository

class GetCharacterUseCase(
    private val repo: CharacterRepository
) {
    suspend fun getCharacter(id: Int = 1): CharacterModel {
        return repo.getCharacterById(id)
    }
}