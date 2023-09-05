package com.example.coding10

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MainItems(
    val aIcon: Int,
    val aIcon2: Int,
    val aName: String,
    val aAge: String,
    val aNumber: String,
    val aEmail: String,
    val aBloodType: String,
    val aMemo: String) : Parcelable
val dataList = mutableListOf<MainItems>().apply {
    add(MainItems(R.drawable.baby1,R.drawable.baby1_2,"올리비아","13개월","010-1621-1422","asjk@naver.com","A형","ㄷ겨쇼ㅜㅈ디ㅑㄷ어"))
    add(MainItems(R.drawable.baby2,R.drawable.baby2_2,"지우","3살","010-1423-1460","mmk@naver.com","A형","코로나로 힘겨운 요즘인데 평온한 하루 보내시길 바랍니다."))
    add(MainItems(R.drawable.baby3,R.drawable.baby3_2,"서연","4살","010-8647-8953","wld@naver.com","B형","사계절의 즐거움으로 가득한 하루 되세요. :)"))
    add(MainItems(R.drawable.baby4,R.drawable.baby4_2,"하윤","2살","010-1531-4620","qkf@naver.com","B형","행복한 시간들이 함께하길 바래요!"))
    add(MainItems(R.drawable.baby5,R.drawable.baby5_2,"민서","3살","010-7485-9462","werd@naver.com","AB형","이웃님! 오늘도 어김없이 찾아와 주셔서 감사합니다."))
    add(MainItems(R.drawable.baby6,R.drawable.baby6_2,"벤틀리","3살","010-8615-4286","zlsk@naver.com","AB형","나는 벤틀리! 이렇게 만나뵙게 되어 너무 기뻐요!"))
    add(MainItems(R.drawable.baby7,R.drawable.baby7_2,"윌리엄","6살","010-1682-9782","jki@naver.com","O형","아녕하세요. 윌리엄이에오. 엉아는 울지 않아여!"))
    add(MainItems(R.drawable.baby8,R.drawable.baby8_2,"엠마","2살","010-7681-1827","kkj@naver.com","O형","니ㅏㅓㄹ@#\$ㅓㅁㄴㅇ랴ㅕ587"))
    add(MainItems(R.drawable.baby9,R.drawable.baby9_2,"이사벨라","5살","010-4715-8562","ggik@naver.com","AB형","방문해주셔서 고마워요~~"))
    add(MainItems(R.drawable.baby10,R.drawable.baby10_2,"이한","130일","010-9846-6847","jiaj@naver.com","O형","다 읽으셨다면 좋아요 꾹~!"))
    add(MainItems(R.drawable.baby11,R.drawable.baby11,"혜은","130일","010-9846-6847","jiaj@naver.com","O형","다 읽으셨다면 좋아요 꾹~!"))
    add(MainItems(R.drawable.baby12,R.drawable.baby12,"민채","130일","010-9846-6847","jiaj@naver.com","O형","다 읽으셨다면 좋아요 꾹~!"))
    add(MainItems(R.drawable.baby13,R.drawable.baby13,"민준","130일","010-9846-6847","jiaj@naver.com","O형","다 읽으셨다면 좋아요 꾹~!"))
    add(MainItems(R.drawable.baby14,R.drawable.baby14,"미나","130일","010-9846-6847","jiaj@naver.com","O형","다 읽으셨다면 좋아요 꾹~!"))
    add(MainItems(R.drawable.baby15,R.drawable.baby15,"수연","130일","010-9846-6847","jiaj@naver.com","O형","다 읽으셨다면 좋아요 꾹~!"))
    add(MainItems(R.drawable.baby16,R.drawable.baby16,"수희","130일","010-9846-6847","jiaj@naver.com","O형","다 읽으셨다면 좋아요 꾹~!"))
    add(MainItems(R.drawable.baby17,R.drawable.baby17,"연서","130일","010-9846-6847","jiaj@naver.com","O형","다 읽으셨다면 좋아요 꾹~!"))
    add(MainItems(R.drawable.baby18,R.drawable.baby18,"지영","130일","010-9846-6847","jiaj@naver.com","O형","다 읽으셨다면 좋아요 꾹~!"))
    add(MainItems(R.drawable.baby19,R.drawable.baby19,"지연","130일","010-9846-6847","jiaj@naver.com","O형","다 읽으셨다면 좋아요 꾹~!"))
    add(MainItems(R.drawable.baby20,R.drawable.baby20,"지혜","130일","010-9846-6847","jiaj@naver.com","O형","다 읽으셨다면 좋아요 꾹~!"))
    add(MainItems(R.drawable.baby21,R.drawable.baby21,"스칼렛","130일","010-9846-6847","jiaj@naver.com","O형","다 읽으셨다면 좋아요 꾹~!"))
    add(MainItems(R.drawable.baby22,R.drawable.baby22,"바이든","130일","010-9846-6847","jiaj@naver.com","O형","다 읽으셨다면 좋아요 꾹~!"))
    add(MainItems(R.drawable.baby23,R.drawable.baby23,"해준","130일","010-9846-6847","jiaj@naver.com","O형","다 읽으셨다면 좋아요 꾹~!"))
    add(MainItems(R.drawable.baby24,R.drawable.baby24,"학준","130일","010-9846-6847","jiaj@naver.com","O형","다 읽으셨다면 좋아요 꾹~!"))
    add(MainItems(R.drawable.baby25,R.drawable.baby25,"하나","130일","010-9846-6847","jiaj@naver.com","O형","다 읽으셨다면 좋아요 꾹~!"))
    add(MainItems(R.drawable.boy,R.drawable.boy,"비욘세","130일","010-9846-6847","jiaj@naver.com","O형","다 읽으셨다면 좋아요 꾹~!"))
}