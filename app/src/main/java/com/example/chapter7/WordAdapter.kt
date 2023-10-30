package com.example.chapter7

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chapter7.databinding.ItemWordBinding

/*RecyclerView.Adapter<VH> 이고, VH는 viewHolder*/
/*사용하는 데이터 (어댑터가 사용하는 데이터): 데이터클래스를 담고있는 컬렉션타입의 데이터*/
class WordAdapter(
    private val list: MutableList<Word>,
    private val itemClickListener: ItemClickListener? = null, //itemClickListener 가 참조하는 ItemClickListener 객체의 내부 상태는 변경될 수 있음.
) : RecyclerView.Adapter<WordAdapter.WordViewHolder>() {
    /*클래스 필요. But 다른데서 쓸 일이 없으므로 이너클래스로*/
    /*implement(구현) 해야하는 메서드*/
    /*뷰홀더 만들기. 뷰홀더는 아이템 뷰를 가지고 있음 -> 뷰홀더 만들기 전에 아이템뷰 레이아웃을 만들어줘야함.*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
//      Adpater는 Activity와 달리 Context를 상속받지 않았음. layoutInflater는 context가 제공하는 메서드여서 Adapter는 LayoutInflater를 인스턴스화 해서 사용해야함
        val inflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater // 뷰는 context를 파라미터로 입력받음. 따라서 ViewGroup인 parent는 context를 가지고 있음. Context는 화면 요소를 사용하기 위해 필요한 클래스.
        /*val inflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)
        if (inflater is LayoutInflater) {
            val binding = ItemWordBinding.inflate(inflater, parent, false) //부모 뷰(레이아웃)에 추가 x, 걍 반환만
            return WordViewHolder(binding) // WordViewHolder 인스턴스를 리턴
        }*/
        // var C = A as B 라고 하면 C 가 B로 캐스팅되는거 뿐만 아니라, A도 B로 다운캐스팅됨. (A가 super B가 sub)
        val binding = ItemWordBinding.inflate(inflater, parent, false) //부모 뷰(레이아웃)에 추가 x, 걍 반환만
        return WordViewHolder(binding) // WordViewHolder 인스턴스를 리턴
    }
    /*만들어진 뷰홀더로 데이터와 UI를 바인딩. position 은 데이터의 위치를 나타냄*/
    /*WordViewHolder 객체를 holder 변수로 참조하여 파라미터로 사용*/
    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val word = list[position]
        holder.bind(word)
        holder.itemView.setOnClickListener { itemClickListener?.onClick(word) }


        /*holder.binding.apply {
            val word = list[position] //Word 객체 참조
            *//*데이터 - ui 연결*//*
            textTextView.text = word.text
            meanTextView.text = word.mean
            typeChip.text = word.type
        } */
    }
    /*데이터의 개수(리스트 길이)를 카운트*/
    override fun getItemCount(): Int {
        return list.size
    }
    /*뷰홀더 클래스. 뷰홀더는 화면에 그려질 뷰를 가지고있음.*/
    /*RecyclerView.ViewHolder: RecyclerView 내부에서 ViewHolder 역할을 수행하는 모든 클래스가 상속받아야 하는 기본 클래스*/
    /*주생성자를 생성하면서 동시에 해당 클래스의 프로퍼티를 생성*/
    /*뷰홀더에 데이터를 바인딩하는 함수를 아예 뷰홀더 클래스에서 만들수도 있음. 이러면 binding 변수를 private 으로 지정할 수 있어서 코드가 좀 더 클린해짐.*/
    class WordViewHolder(private val binding: ItemWordBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(word: Word) {
            binding.apply {
                /*데이터 - ui 연결*/
                textTextView.text = word.text
                meanTextView.text = word.mean
                typeChip.text = word.type
            }
        }
    }

    interface ItemClickListener {
        fun onClick(word: Word)
    }
}