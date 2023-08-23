package bootcamp.sparta.advencedpartymission1.data

object TodoData {
    private val _todoList = mutableListOf<Todo>()
    fun getTodoList() = _todoList
    fun setTodoList(data: Todo) = _todoList.add(data)

}

//fun CharactersList(resources: Resources) : List<MischievousGuard> {
//    return listOf(
//        MischievousGuard(
//            id = 1,
//            name = resources.getString(R.string.character_ddolgi_name),
//            image = R.drawable.character_ddolgi,
//            description = resources.getString(R.string.character_ddolgi_description)
//        ),
//        MischievousGuard(
//            id = 2,
//            name = resources.getString(R.string.character_ddunge_name),
//            image = R.drawable.character_ddunge,
//            description = resources.getString(R.string.character_ddunge_description)
//        ),
//        MischievousGuard(
//            id = 3,
//            name = resources.getString(R.string.character_hochi_name),
//            image = R.drawable.character_hochi,
//            description = resources.getString(R.string.character_hochi_description)
//        ),
//        MischievousGuard(
//            id = 4,
//            name = resources.getString(R.string.character_saechomi_name),
//            image = R.drawable.character_saechomi,
//            description = resources.getString(R.string.character_saechomi_description)
//        ),
//        MischievousGuard(
//            id = 5,
//            name = resources.getString(R.string.character_drago_name),
//            image = R.drawable.character_drago,
//            description = resources.getString(R.string.character_drago_description)
//        ),
//        MischievousGuard(
//            id = 6,
//            name = resources.getString(R.string.character_yoreungi),
//            image = R.drawable.character_yoreungi,
//            description = resources.getString(R.string.character_yoreungi_description)
//        ),
//        MischievousGuard(
//            id = 7,
//            name = resources.getString(R.string.character_macho_name),
//            image = R.drawable.character_macho,
//            description = resources.getString(R.string.character_macho_description)
//        ),
//        MischievousGuard(
//            id = 8,
//            name = resources.getString(R.string.character_mimi_name),
//            image = R.drawable.character_mimi,
//            description = resources.getString(R.string.character_mimi_description)
//        ),
//        MischievousGuard(
//            id = 9,
//            name = resources.getString(R.string.character_mungchi_name),
//            image = R.drawable.character_mungchi,
//            description = resources.getString(R.string.character_mungchi_description)
//        ),
//        MischievousGuard(
//            id = 10,
//            name = resources.getString(R.string.character_kiki_name),
//            image = R.drawable.character_kiki,
//            description = resources.getString(R.string.character_kiki_description)
//        ),
//        MischievousGuard(
//            id = 11,
//            name = resources.getString(R.string.character_gangdari_name),
//            image = R.drawable.character_gangdari,
//            description = resources.getString(R.string.character_gangdari_description)
//        ),
//        MischievousGuard(
//            id = 12,
//            name = resources.getString(R.string.character_zhingzhing_name),
//            image = R.drawable.character_zhingzhing,
//            description = resources.getString(R.string.character_zhingzhing_description)
//        ),
//    )
//}