package ru.stan.a11125.data.RepositoryImpl

import ru.stan.a11125.data.mapper.CharacterMapper
import ru.stan.a11125.data.network.RetrofitInstance
import ru.stan.a11125.domain.model.CharacterModel
import ru.stan.a11125.domain.repository.CharacterRepository

class CharacterRepositoryImpl: CharacterRepository {
    private val mapper = CharacterMapper()

    override suspend fun getCharacters(): List<CharacterModel> {
        return mapper.mapListDtoToListModel(
            RetrofitInstance.searchCharacterApi.getCharacters())
    }

    override suspend fun getCharacterById(id: Int): CharacterModel {
        return mapper.mapDtoToModel(
            RetrofitInstance.searchCharacterApi.getCgaractersById(id)
        )
    }
}