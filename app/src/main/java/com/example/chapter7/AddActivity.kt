package com.example.chapter7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.chapter7.databinding.ActivityAddBinding
import com.example.chapter7.databinding.ActivityMainBinding
import com.google.android.material.chip.Chip

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        val types = listOf(
            "명사", "동사", "대명사", "형용사", "부사", "감탄사", "전치사", "접속사"
        )
        binding.typeChipGroup.apply {
            types.forEach { text -> // 컬렉션 반복문
                addView(createChip(text))
            }
        }
    }

    private fun createChip(text: String): Chip {
        return Chip(this).apply { // chipGroup에서 chip을 하나만 선택 가능하게끔 만들어야함(radioGroup과 동일)
            setText(text)
            isCheckable = true
            isClickable = true
        }
    }

    private fun add() {
        val text = binding.textInputEditText.text.toString()
        val mean = binding.meanTextInputEditText.text.toString()
        val type = findViewById<Chip>(binding.typeChipGroup.checkedChipId).text.toString()
        val word = Word(text, mean, type)

        Thread {
            AppDatabase.getInstance(this)?.wordDao()?.insert(word)
            runOnUiThread {
                Toast.makeText(this, "저장을 완료했습니다.", Toast.LENGTH_SHORT).show()
            }
            finish() //AddActivity 종료
        }.start()
    }
}