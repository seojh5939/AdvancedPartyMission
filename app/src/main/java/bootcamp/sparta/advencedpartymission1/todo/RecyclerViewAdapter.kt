package bootcamp.sparta.advencedpartymission1.todo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import bootcamp.sparta.advencedpartymission1.R
import bootcamp.sparta.advencedpartymission1.data.Todo

class RecyclerViewAdapter(list: List<Todo>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){
    private val mList : List<Todo>

    init {
        mList = list
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val image = view.findViewById<ImageView>(R.id.iv_item_image)
        val title = view.findViewById<TextView>(R.id.tv_item_title)
        val content = view.findViewById<TextView>(R.id.tv_item_content)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_item_recyclerview, parent, false)
        val holder = ViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = mList[position].title
        mList[position].image?.let { holder.image.setImageDrawable(it) }
        holder.content.text = mList[position].contents
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}