package com.dagger.hilt.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dagger.hilt.R
import com.dagger.hilt.data.models.People
import com.dagger.hilt.databinding.RecyclerItemPeopleBinding

class PeopleAdapter(
    private var mList: List<People> = ArrayList(),
    private val onPeopleClickListener: (people: People) -> Unit
) : RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder =
        PeopleViewHolder.create(parent, onPeopleClickListener)

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) =
        holder.bind(mList[position])

    override fun getItemCount(): Int = mList.size

    fun setPeoples(peopleList: List<People>) {
        mList = peopleList
        notifyDataSetChanged()
    }

    class PeopleViewHolder internal constructor(
        private val binding: RecyclerItemPeopleBinding,
        private val onPeopleClickListener: (people: People) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        internal fun bind(people: People) {
            binding.people = people
            binding.root.setOnClickListener { onPeopleClickListener(people) }
        }

        companion object {
            internal fun create(
                parent: ViewGroup,
                onPeopleClickListener: (people: People) -> Unit
            ) = PeopleViewHolder(binding = DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.recycler_item_people,
                        parent,
                        false
                    ),
                    onPeopleClickListener = onPeopleClickListener,
                )
        }
    }
}
