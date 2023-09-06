package com.example.coding10

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.text.Collator
import java.util.Locale

open class CommonItems
@Parcelize
data class MainItems(
    val aIcon: Uri,
    val aIcon2: Uri,
    val aName: String,
    val aAge: String,
    val aNumber: String,
    val aEmail: String,
    val aBloodType: String,
    val aMemo: String,
    var category: String = "",
    var favorite: Boolean = false) : Parcelable, CommonItems()

data class MainTitle(val str: String) : CommonItems()
data class MainCategory(val str: String) : CommonItems()

val dataList = mutableListOf<CommonItems>().apply {
    add(MainItems(getUri(R.drawable.baby1),getUri(R.drawable.baby1_2),"올리비아","13개월","010-1621-1422","asjk@naver.com","A형","ㄷ겨쇼ㅜㅈ디ㅑㄷ어"))
    add(MainItems(getUri(R.drawable.baby2),getUri(R.drawable.baby2_2),"지우","3살","010-1423-1460","mmk@naver.com","A형","코로나로 힘겨운 요즘인데 평온한 하루 보내시길 바랍니다."))
    add(MainItems(getUri(R.drawable.baby3),getUri(R.drawable.baby3_2),"서연","4살","010-8647-8953","wld@naver.com","B형","사계절의 즐거움으로 가득한 하루 되세요. :)"))
    add(MainItems(getUri(R.drawable.baby4),getUri(R.drawable.baby4_2),"하윤","2살","010-1531-4620","qkf@naver.com","B형","행복한 시간들이 함께하길 바래요!"))
    add(MainItems(getUri(R.drawable.baby5),getUri(R.drawable.baby5_2),"민서","3살","010-7485-9462","werd@naver.com","AB형","이웃님! 오늘도 어김없이 찾아와 주셔서 감사합니다."))
    add(MainItems(getUri(R.drawable.baby6),getUri(R.drawable.baby6_2),"벤틀리","3살","010-8615-4286","zlsk@naver.com","AB형","나는 벤틀리! 이렇게 만나뵙게 되어 너무 기뻐요!"))
    add(MainItems(getUri(R.drawable.baby7),getUri(R.drawable.baby7_2),"윌리엄","6살","010-1682-9782","jki@naver.com","O형","아녕하세요. 윌리엄이에오. 엉아는 울지 않아여!"))
    add(MainItems(getUri(R.drawable.baby8),getUri(R.drawable.baby8_2),"엠마","2살","010-7681-1827","kkj@naver.com","O형","니ㅏㅓㄹ@#\$ㅓㅁㄴㅇ랴ㅕ587"))
    add(MainItems(getUri(R.drawable.baby9),getUri(R.drawable.baby9_2),"이사벨라","5살","010-4715-8562","ggik@naver.com","AB형","방문해주셔서 고마워요~~"))
    add(MainItems(getUri(R.drawable.baby10),getUri(R.drawable.baby10_2),"이한","130일","010-9846-6847","jiaj@naver.com","O형","다 읽으셨다면 좋아요 꾹~!"))
    add(MainItems(getUri(R.drawable.baby11),getUri(R.drawable.baby11),"혜은","130일","010-9846-6847","jiaj@naver.com","O형","다 읽으셨다면 좋아요 꾹~!"))
    add(MainItems(getUri(R.drawable.baby12),getUri(R.drawable.baby12),"민채","130일","010-9846-6847","jiaj@naver.com","O형","다 읽으셨다면 좋아요 꾹~!"))
    add(MainItems(getUri(R.drawable.baby13),getUri(R.drawable.baby13),"민준","130일","010-9846-6847","jiaj@naver.com","O형","다 읽으셨다면 좋아요 꾹~!"))
    add(MainItems(getUri(R.drawable.baby14),getUri(R.drawable.baby14),"미나","130일","010-9846-6847","jiaj@naver.com","O형","다 읽으셨다면 좋아요 꾹~!"))
    add(MainItems(getUri(R.drawable.baby15),getUri(R.drawable.baby15),"수연","130일","010-9846-6847","jiaj@naver.com","O형","다 읽으셨다면 좋아요 꾹~!"))
    add(MainItems(getUri(R.drawable.baby16),getUri(R.drawable.baby16),"수희","130일","010-9846-6847","jiaj@naver.com","O형","다 읽으셨다면 좋아요 꾹~!"))
    add(MainItems(getUri(R.drawable.baby17),getUri(R.drawable.baby17),"연서","130일","010-9846-6847","jiaj@naver.com","O형","다 읽으셨다면 좋아요 꾹~!"))
    add(MainItems(getUri(R.drawable.baby18),getUri(R.drawable.baby18),"지영","130일","010-9846-6847","jiaj@naver.com","O형","다 읽으셨다면 좋아요 꾹~!"))
    add(MainItems(getUri(R.drawable.baby19),getUri(R.drawable.baby19),"지연","130일","010-9846-6847","jiaj@naver.com","O형","다 읽으셨다면 좋아요 꾹~!"))
    add(MainItems(getUri(R.drawable.baby20),getUri(R.drawable.baby20),"지혜","130일","010-9846-6847","jiaj@naver.com","O형","다 읽으셨다면 좋아요 꾹~!"))
    add(MainItems(getUri(R.drawable.baby21),getUri(R.drawable.baby21),"스칼렛","130일","010-9846-6847","jiaj@naver.com","O형","다 읽으셨다면 좋아요 꾹~!"))
    add(MainItems(getUri(R.drawable.baby22),getUri(R.drawable.baby22),"바이든","130일","010-9846-6847","jiaj@naver.com","O형","다 읽으셨다면 좋아요 꾹~!"))
    add(MainItems(getUri(R.drawable.baby23),getUri(R.drawable.baby23),"해준","130일","010-9846-6847","jiaj@naver.com","O형","다 읽으셨다면 좋아요 꾹~!"))
    add(MainItems(getUri(R.drawable.baby24),getUri(R.drawable.baby24),"학준","130일","010-9846-6847","jiaj@naver.com","O형","다 읽으셨다면 좋아요 꾹~!"))
    add(MainItems(getUri(R.drawable.baby25),getUri(R.drawable.baby25),"하나","130일","010-9846-6847","jiaj@naver.com","O형","다 읽으셨다면 좋아요 꾹~!"))
    add(MainItems(getUri(R.drawable.boy),getUri(R.drawable.boy),"비욘세","130일","010-9846-6847","jiaj@naver.com","O형","다 읽으셨다면 좋아요 꾹~!"))
}

//val sortList = displayData()

fun getUri(resid: Int): Uri = Uri.parse("android.resource://" + R::class.java.`package`?.name + "/" + resid)

/**
 * 소팅을 위해 필요한 첫글자 추출하는 함수
 */
fun getSortingKey(str: String): String {
    val firstChar = str.firstOrNull() ?: return str
    return when {
        firstChar.isLetter() && firstChar.isLowerCase() -> firstChar.toString()
        firstChar.isLetter() && firstChar.isUpperCase() -> firstChar.toString().toLowerCase()
        firstChar.isDigit() -> firstChar.toString()
        else -> getChosung(firstChar).toString()
    }
}

/**
 * 초성 추출하는 함수
 */
fun getChosung(c: Char): Char {
    val hanBegin = 0xAC00
    val hanLast = 0xD7A3
    val chosungBase = 0x1100

    if (c.toInt() < hanBegin || c.toInt() > hanLast) {
        return c
    }

    val hanOffset = c.toInt() - hanBegin
    val chosungOffset = hanOffset / (21 * 28)

    return (chosungBase + chosungOffset).toChar()
}

/**
 * datalist 정렬함수
 */
fun sortData(): List<CommonItems> {
    dataList.forEach {
        it as MainItems
        it.category = getSortingKey(it.aName)
    }

    val collator = Collator.getInstance(Locale.KOREAN)

    return dataList.sortedWith(
        compareBy(
            {
                it as MainItems
                it.category
            },
            {
                it as MainItems
                collator.compare(it.aName, it.aName)
            })
    )
}

/**
 * 소팅한 리스트와 타이틀 및 카테고리 목록을 추가한 실제 화면에 뿌려지는 디스플레이용 리스트
 */
fun displayData(): List<CommonItems>  {
    val sortedList = sortData() //정렬
    var displayList = mutableListOf<CommonItems>()

    displayList.add(MainTitle("즐겨찾기 목록"))
    val (favoriteItems, nonFavoriteItems) = sortedList.partition { (it as MainItems).favorite }
    displayList.addAll(favoriteItems)
    displayList.add(MainTitle("연락처"))

    var currentCategory = if (nonFavoriteItems.isNotEmpty()) (nonFavoriteItems[0] as MainItems).category else ""
    displayList.add(MainCategory(currentCategory))

    nonFavoriteItems.forEach {
        val item = it as MainItems
        if (item.category != currentCategory) {
            displayList.add(MainCategory(item.category))
            currentCategory = item.category
        }
        displayList.add(item)
    }

    return displayList
}