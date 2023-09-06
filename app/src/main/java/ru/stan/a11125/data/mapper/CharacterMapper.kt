package ru.stan.a11125.data.mapper

import ru.stan.a11125.data.DTO.CharacterDto
import ru.stan.a11125.domain.model.CharacterModel

class CharacterMapper {
    fun mapDtoToModel(characterDto: CharacterDto)= CharacterModel(
        id = characterDto.id,
        name = characterDto.name,
        hogwartsHouse = characterDto.hogwartsHouse,
        imageUrl = characterDto.imageUrl
    )

    fun mapListDtoToListModel(dtoList:List<CharacterDto>): List<CharacterModel>{
        var resList = mutableListOf<CharacterModel>()
        dtoList.forEach { resList.add(mapDtoToModel(it)) }
        return resList
    }
}