package ru.stan.a11125.data.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import ru.stan.a11125.data.DTO.CharacterDto


const val BASE_URL = "https://harry-potter-api-en.onrender.com"
interface NetWorkApi {
    @GET("/characters")
    suspend fun getCharacters(): List<CharacterDto>

    @GET("/characters/{id}")
    suspend fun getCgaractersById(@Path("id") id: Int = 1): CharacterDto
}

object RetrofitInstance {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val searchCharacterApi: NetWorkApi =
        retrofit.create(NetWorkApi::class.java)
}