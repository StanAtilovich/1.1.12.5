package ru.stan.a11125.domain.repository

import ru.stan.a11125.domain.model.CharacterModel

interface CharacterRepository {
    suspend fun getCharacters(): List<CharacterModel>
    suspend fun getCharacterById(id: Int): CharacterModel
}