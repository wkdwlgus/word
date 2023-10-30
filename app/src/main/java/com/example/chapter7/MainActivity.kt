package com.example.chapter7

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chapter7.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), WordAdapter.ItemClickListener { // WordAdapter.ItemClickListener 인터페이스 구현
    private lateinit var binding: ActivityMainBinding
    private lateinit var wordAdapter: WordAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) // getLayoutInflater. inflating: 레이아웃의 클래스화 -> 뷰 객체 (binding) 생성, layoutInflater : LayoutInflater 의 인스턴스. 뷰 계층 구조 생성
        setContentView(binding.root)

        initRecyclerView()
        binding.addButton.setOnClickListener {
            Intent(this, AddActivity::class.java).let {
                startActivity(it)
            }
        }

    }

    private fun initRecyclerView() {

        wordAdapter = WordAdapter(mutableListOf(), this) // 어댑터 안에 데이터 넣기
        binding.wordRecyclerView.apply {
            adapter = wordAdapter // 어댑터 연결, 리사이클러뷰에 데이터 추가
            layoutManager = LinearLayoutManager( // 레이아웃매니저 설정
                applicationContext,
                LinearLayoutManager.VERTICAL,
                false
            )
            val dividerItemDecoration = // 레이아웃 구분자
                DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL)
            addItemDecoration(dividerItemDecoration) // 또는 아이템 자체에다가 divider 을 넣을 때도 있음.
        }

    }

    override fun onClick(word: Word) {  // wordAdapter.ItemClickListener 인터페이스의 메서드 오버라이드
        Toast.makeText(this, "${word.text} 가 클릭됐습니다.", Toast.LENGTH_SHORT).show()
    }

}