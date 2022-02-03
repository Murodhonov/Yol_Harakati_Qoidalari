package uz.umarxon.road_rules.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import uz.umarxon.road_rules.Adapter.RvAdapter.Click
import uz.umarxon.road_rules.DB.DbHelper
import uz.umarxon.road_rules.Models.User
import uz.umarxon.road_rules.R
import uz.umarxon.road_rules.databinding.FragmentItemBinding

class ViewPager(var context: Context, var click2: Click2) : RecyclerView.Adapter<ViewPager.Vh>() {
    inner class Vh(var itemRv: FragmentItemBinding) : RecyclerView.ViewHolder(itemRv.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(FragmentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {

        val list = DbHelper(context).getAllMember()
        val list2 = ArrayList<User>()

        when (position) {
            0 -> {
                for (i in list.indices){
                    if (list[i].type == "Ogohlantiruvchi"){
                        list2.add(list[i])
                    }
                }
            }
            1 -> {
                for (i in list.indices){
                    if (list[i].type == "Imtiyozli"){
                        list2.add(list[i])
                    }
                }
            }
            2 -> {
                for (i in list.indices){
                    if (list[i].type == "Ta'qiqlovchi"){
                        list2.add(list[i])
                    }
                }
            }
            3 -> {
                for (i in list.indices){
                    if (list[i].type == "Buyuruvchi"){
                        list2.add(list[i])
                    }
                }

            }
        }
        holder.itemRv.rv.adapter = RvAdapter(context, list2, 2, object : Click {
                override fun clicking(user: User) {
                    click2.clicking(user)
                }
                override fun like(user: User,image:ImageView) {
                    click2.like(user,image)
                }
                override fun change(user: User) {
                    click2.change(user)
                }
                override fun remove(user: User) {
                    click2.remove(user)
                }
            })
    }

    override fun getItemCount(): Int = 4

    interface Click2 {
        fun clicking(user: User)
        fun like(user: User,i:ImageView)
        fun remove(user: User)
        fun change(user: User)
    }

}
