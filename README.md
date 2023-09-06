# 1.1.12.5
правильные файлы это 1.1.12.5 и папка1 файл stan_atilovich.harry_potter_and_retrofit.presentation также находиться в инете
1)empty views Activity
2) сщздаем Fragment with ViewModel
3)B build gradlre 

buildFeatures{
        viewBinding true
    }
чтобы надуть вью байдинг
и 

implementation 'androidx.fragment:fragment-ktx:1.6.1'
чтобы фрагмент переходил в другой фрагмент

4)binding в mainActivity

override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
создаем и надувает вью байдинг
5)binding в mainFragment


полная запись
private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

старая запись

private val viewModel: MainViewModel by viewModels()
 private var _binding: FragmentMainBinding? = null
    private val binding get()  = _binding!!
создаем и надуваем вью байдинг

6)override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container,false)
        return  binding.root

    } сдесь загружается то что мы хотим загрузить раздуваем теперь тут вместо майн фрагмента выходит другой фрагмент

7)onActivityCreated можно его удалить т.к это момент когда он создался что делать а мы уже его надули другими данными
8)делаем тут же
override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    } а это мы что будет делать активити когда его закрыывают оно удаляется

9) в активити майн лайаут 
вместо текс вью делаем <androidx.fragment.app.FragmentContainerView
и даем айди "@+id/fragment_container" он будет контейнером в котором все будет нарисовано передано другой фрагмент 
android:name="ru.stan.garry11122.MainFragment" незыбываем,  ну и еще сам майн активити даем ему айди android:id="@+id/container" то что он теперь у нас контейнер других контейнеров
10) fragment main фрагмент майн надо у текставью дать id уго айди @+id/textView
11) d vfybatcnt <uses-permission android:name="android.permission.INTERNET"/> не забываем подлючаем интернет

12 минут закончено дальше теперь


1)проект состоит из 3 слоем data()все данные аккомулированы тут в нем есть сети network(данные из сети),dataслой(данные из хранилища), domain(не зависит ни от data , это основная бизнес логика приложения тут мы получаем информацию из сервера и сохраняем ее в data ) и потом все это идет в presentation(тут будет все отображаться . это то что видит в конечном итоге пользователь) они передает во ViewModel потом оно передает в MainFragment 

2) создаем data, создаем network, создаем presentation
3) в presentation  перекидываем MainFragment и MainViewModel(можно и MainActivity но в нетологии мы его оставляли в главной папке)
4)domain мы будем описывать что мы хотим сделать в качестве интерфейса
5)data.network будет реализация этого интерфейса как мы будем получать данные будет библиотека ретрофит Retrofit
6) presentation.MainViewModel будет стекаться вся информация из dataили network в ViewModel и  будет передаваться в mainFragment onCreateView будут тут потоки они будут прослушивать и эта информация будет изображаться то что увидит пользователь

7)ставим интернет в андройд манифест 
<uses-permission android:name="android.permission.INTERNET"/>

8) ищем апи который унас будет
9) создаем новую папку model в domain и там будет файл character(персонаж) 
data class CharacterModel(
    val id: Int = 0,
    val name: String ="",
    val hogwartsHouse: String= "",
    val imageUrl: String =""
    )

10)создаем в domain папаку repository и файл CharacterRepository это означает какой именно репозиторий и от кого он в репозитории пишем что именно repository будет у нас делать функции без тела

interface CharacterRepository {

 suspend fun getCharacters(): List<CharacterModel>
   suspend fun getCharacterById(id: Int): CharacterModel

11)теперь Character в repository надо перевести в DTO(Dta transfer object)- это обьект модели который переводим 
cоздаем папку DTO и файл data class CharacterDto B data
12)создаем репозитори импл в дате потом CharacterRepositorory  написать конкретную его реализацию создаем CharacterRepositororyImpl(реализация)
13)class CharacterRepositoryImpl : CharacterRepository импл зависит от characterRepository
14)вытаскиваем именно реализации Implementation нажимаем
16) в network папке создаем файл NetworkApi(что бы прописать get(получить надо написать interface))interface SearchCharactersApi 

const val BASE_URL = "https://harry-potter-api-en.onrender.com"
interface NetWorkApi {
    @GET("/characters")
    suspend fun getCharacters(): List<CharacterDto>

    @GET("/characters/{id}")
    suspend fun getCgaractersById(@Path("id") id: Int = 1): CharacterDto
}

17) будем использовать корутины поэтому пишем suspend

@GET("/characters")
    suspend fun getCharacters(): List<CharacterDto>

    @GET("/characters/{id}")
    suspend fun getCharactersById(@Path("id") id: Int = 1): CharacterDto

обьекты которые мы будем получать в этом слое они будут Dto Api это аналог Cracter из repository

18) чтобы работал get надо подключить библиотеку retrofit  поэтому заходил gradle app и пишем
//retrofit
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-moshi:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    //Moshi
    implementation 'com.squareup.moshi:moshi:1.14.0'
    implementation 'com.squareup.moshi:moshi-kotlin:1.14.0'
    kapt 'com.squareup.moshi:moshi-kotlin-codegen:1.14.0'
    //Gson
    implementation 'com.google.code.gson:gson:2.10.1'

моши тоже самое что и джейсон но мы в нетологии использовали джсон. 
json переобразовывает котлин обьекты в json и обратно 
пример
1. Сериализация объекта в JSON:

```kotlin
data class Person(val name: String, val age: Int)

val person = Person("John Doe", 25)
val gson = Gson()
val json = gson.toJson(person)
println(json)
```

В результате получим JSON строку:

```
{"name":"John Doe","age":25}
```

2. Десериализация JSON в объект:

```kotlin
val json = """{"name":"John Doe","age":25}"""
val gson = Gson()
val person = gson.fromJson(json, Person::class.java)
println(person.name) // Выведет "John Doe"
println(person.age) // Выведет 25


19) ещебыла подключена кодо генерация id 'org.jetbrains.kotlin.kapt' поэтому подключаем ее в градле в самом верху, и не забыть гет импрортировать в нетыорк апи.

20)DTO CharacterDTO прописываем 
@JsonClass(generateAdapter = true)
data class CharacterDto(
    @Json(name = "id")
    val id: Int,
    @Json(name = "character")
    val name: String,
    @Json(name="hogwartsHouse")
    val hogwartsHouse: String,
    @Json(name = "image")
    val imageUrl: String


21) теперь надо написать конечную инстанцию ретрофита в NetworkApi

мы написали object потому что это означает что мы создали обьект сразу получаем к нему ссылку полный доступ и он запускат потом searchCharacterApi 
 
object RetrofitInstance {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

здесь мы создали ретрофит

а внизу тут он запускает все


    val searchCharacterApi: NetWorkApi =
        retrofit.create(NetWorkApi::class.java)
} 
и не забыть про 
const val BASE_URL = "https://harry-potter-api-en.onrender.com"
он в самом начале прописываеться


22)domain слой тут должна ьыть лписана вся бизнем логика. поэтому тут создаем папку usecase и в ней файл 
class GetCharacterListUseCase(
    private val repo: CharacterRepository
) {
    suspend fun getCharacterList(): List<CharacterModel> {
        return repo.getCharacters()
    }
} и второй файл 
class GetCharacterUseCase(
    private val repo: CharacterRepository
) {
    suspend fun getCharacter(id: Int = 1): CharacterModel {
        return repo.getCharacterById(id)
             }
}

репозиторий котрые мы создавали надо заинжетить сюда 

23)после этого в presentation MainViewModel он от viewModel
и реализуем usecase class MainViewModel(
    private val repository: CharacterRepositoryImpl,
    private val getCharacterListUseCase: GetCharacterListUseCase,
    private val getCharacterUseCase: GetCharacterUseCase
) : ViewModel() {

24) теперь создаем специальные mapper он будет в data т.к в CharacterRepository СрфкфсеукВещ hfpyst b xnj,s(cparacterdto разные в чтобы) моши работал нужен класс который передалает это в одно
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
он там конвектирует создает и делает одну функцию чтоб она была похожа друг на друга дата слой будет (fun mapDtoToModel вот это)ссылаться на домейн слой это нормально а наоборот нельзя

25) И тут как раз создаеться CharacterModel в domain слое папка model файл characterModel

26) и в DTO Надо создатьCharacterRepositoryImpl
он будет идти от mapper
class CharacterRepositoryImpl : CharacterRepository {
    private val mapper = CharacterMapper()

    override suspend fun getCharacters(): List<CharacterModel> {
        return mapper.mapListDtoToListModel(
            RetrofitInstance.searchCharacterApi.getCharacters())
    }

    override suspend fun getCharacterById(id: Int): CharacterModel {
        return mapper.mapDtoToModel(
            RetrofitInstance.searchCharacterApi.getCharactersById(id)
                  )
    }
}


27) потом в MainViewModel создаем потоки _state и state чтоб _State не переписывался чтоб он был не изменяемым он будет типом данных ProgressState поэтому создаем в presentation создаем ProgressState

sealed class ProgressState{
    object Loading: ProgressState()
    object Success: ProgressState()
}

28) а в MainViewModel 
class MainViewModel(
    //в будующем будет фабрика
) : ViewModel() {

    private var _state: MutableStateFlow<ProgressState> = MutableStateFlow(ProgressState.Success)
    var state = _state.asStateFlow()

    private var _character: MutableStateFlow<CharacterModel> =
        MutableStateFlow(CharacterModel())
    var character = _character.asStateFlow()

    private var _characterList: MutableStateFlow<List<CharacterModel>> =
        MutableStateFlow(mutableListOf())
    var characterList = _characterList.asStateFlow()


29)
class MainViewModel(
   //тут в будушем будет поключаться фабрика
) : ViewModel() {

    private var _state: MutableStateFlow<ProgressState> = MutableStateFlow(ProgressState.Success)
    var state = _state.asStateFlow()

    private var _character: MutableStateFlow<CharacterModel> =
        MutableStateFlow(CharacterModel())
    var character = _character.asStateFlow()

    private var _characterList: MutableStateFlow<List<CharacterModel>> =
        MutableStateFlow(mutableListOf())
    var characterList = _characterList.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = ProgressState.Loading
            try {
             _character.value = getCharacterUseCase.getCharacter(1)
             _characterList.value = getCharacterListUseCase.getCharacterList()
            } catch (t: Throwable) {
                Log.e(TAG, "${t.message}", t)
            }
            _state.value = ProgressState.Success
        }
    }

    fun randomCharacter() {
           _character.value = _characterList.value.random()
        viewModelScope.launch {
            _state.value = ProgressState.Loading
            try {
                val listSize = _characterList.value.size
             // _character.value = getCharacterUseCase.getCharacter((1..listSize).random())
            } catch (t: Throwable) {
                Log.e(TAG, "${t.message}", t)
            }
            _state.value = ProgressState.Success
        }

    }
}

init это чтоб _state работал его функции она suspend функция потэтому она запускаться с корутины или со спенд функции поэтому viewModelScope.launch 
30) потом эта информаци подтягиваеться в MainFragment 
override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.character.collect {
                binding.tvName.text = it.name
                binding.tvHouse.text = it.hogwartsHouse
                binding.imageCharacter.load(it.imageUrl)
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect {
                binding.progressBar.isVisible = it is ProgressState.Loading
            }
        }

        binding.buttonRandomCharacter.setOnClickListener {
            viewModel.randomCharacter()
        }
    }
        binding.buttonRandomCharacter.setOnClickListener {
            viewModel.randomCharacter()
        }

31) не забываем в MainFragment  private val viewModel: MainViewModel by viewModels {} 

32)подклюяаем в градле
 //OkHttp
    implementation 'com.squareup.okhttp3:okhttp:4.11.0'
    implementation 'com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.10'
он отправляет какой запрс мы отправили чтобы его подлключит до конца смотри видео 1 время 1-55

33) в презентайщион от вначале отправляеться во вьюмодел потм в фрагмент

34) переходим к отресовке майнлауфут сделаем просто <?xml version="1.0" encoding="utf-8"?>

<androidx.fragment.app.FragmentContainerView xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/fragment_container"
    android:name="stan_atilovich.harry_potter_and_retrofit.presentation.MainFragment"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />

35)  а фрагмент сделаем это 
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".presentation.MainFragment">

    <androidx.appcompat.widget.AppCompatImageView
        android:id="@+id/image_character"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="16dp"
        android:layout_marginTop="16dp"
        android:layout_marginEnd="16dp"
        android:layout_marginBottom="8dp"
        app:layout_constraintBottom_toTopOf="@+id/tv_name"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <TextView
        android:id="@+id/tv_name"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="MainActivity"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <TextView
        android:id="@+id/tv_house"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="16dp"
        android:layout_marginTop="16dp"
        android:layout_marginEnd="16dp"
        android:text="TextView"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/tv_name" />

    <Button
        android:id="@+id/buttonRandomCharacter"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="16dp"
        android:layout_marginTop="150dp"
        android:text="@string/button_text"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/tv_house" />

    <ProgressBar
        android:id="@+id/progressBar"
        style="?android:attr/progressBarStyle"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="16dp"
        app:layout_constraintBottom_toBottomOf="@+id/buttonRandomCharacter"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toEndOf="@+id/buttonRandomCharacter"
        app:layout_constraintTop_toTopOf="@+id/buttonRandomCharacter" />

36) 
подключаем 
    //Coil загрузка картинок типа glide
    implementation 'io.coil-kt:coil:0.13.0'

37) теперь делаем рандомно. добавляем баттон рисуем его он уже есть.

38) в майн фрагмент вначале пишем кнопку binding.buttonRandomCharacter.setOnClickListener {
            viewModel.randomCharacter()
        }

39) в майнмодел пишем вот это 
fun randomCharacter() {
           _character.value = _characterList.value.random()
        viewModelScope.launch {
            _state.value = ProgressState.Loading
            try {
                val listSize = _characterList.value.size
             // _character.value = getCharacterUseCase.getCharacter((1..listSize).random())
            } catch (t: Throwable) {
                Log.e(TAG, "${t.message}", t)
            }
            _state.value = ProgressState.Success
        }

    }

40) mainViewModel не работает потому что я не создал фабрику
во вью модели есть своя иньекция здесь нужно создавать фабрику . другие классы мы просто могли 

class MainViewModel(
    private val repository: CharacterRepositoryImpl,
    private val getCharacterListUseCase: GetCharacterListUseCase,
    private val getCharacterUseCase: GetCharacterUseCase
) : ViewModel() {

41) потом этот viewModel мы подкрепляем к MainFragment/

в mainFragment у нас нет фабрики пишем ее в  private val viewModel: MainViewModel by viewModels() в скобках
 private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory()
    }       
42) Создаем этот класс MainViewModelFactory()

ass MainViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
           val repo = CharacterRepositoryImpl()
                 val useCase = GetCharacterUseCase(repo)
                     val useCase2 = GetCharacterListUseCase(repo)
            return MainViewModel(repo,useCase2,useCase) as T
        }
        throw IllegalArgumentException("неизвестное имя классса")
    }
43) MainFragment не дописан что то пропущено
вот это
private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }


полная его запись
class MainFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory()
    }

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.character.collect {
                binding.tvName.text = it.name
                binding.tvHouse.text = it.hogwartsHouse
                binding.imageCharacter.load(it.imageUrl)
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect {
                binding.progressBar.isVisible = it is ProgressState.Loading
            }
        }

        binding.buttonRandomCharacter.setOnClickListener {
            viewModel.randomCharacter()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
