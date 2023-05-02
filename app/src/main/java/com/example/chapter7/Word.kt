package com.example.chapter7

import androidx.room.Entity
import androidx.room.PrimaryKey

/*데이터를 홀딩하기 위한 클래스. 하나 이상의 프로퍼티가 필요*/


@Entity(tableName = "word") // tableName 을 지정하지 않으면 Entity 에서 알아서 지정해줌.
data class Word(
    val text: String,
    val mean: String,
    val type: String,
    /*database 에서 사용하기 위해선 key 가 필요*/
    @PrimaryKey(autoGenerate = true) val id: Int = 0 // autoGenerate : 아이디 값을 자동으로 생성
)
