package uz.umarxon.road_rules.Fragments

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.delete_dialog.view.*
import kotlinx.android.synthetic.main.fragment_favorite.view.*
import uz.umarxon.road_rules.Adapter.RvAdapter
import uz.umarxon.road_rules.DB.DbHelper
import uz.umarxon.road_rules.MainActivity
import uz.umarxon.road_rules.Models.User
import uz.umarxon.road_rules.R
import uz.umarxon.road_rules.Utils.Data
import uz.umarxon.road_rules.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {

    lateinit var root :View
    lateinit var binding : FragmentFavoriteBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        root = inflater.inflate(R.layout.fragment_favorite, container, false)
        binding = FragmentFavoriteBinding.inflate(layoutInflater)


        return root
    }

    override fun onStart() {
        super.onStart()

        (activity as MainActivity?)?.isVisible(true)

        Data.isHome = true
        val list = DbHelper(root.context).getAllMember()
        val list2 = ArrayList<User>()

        for (i in list.indices){
            if (list[i].liked == "like"){
                list2.add(list[i])
            }
        }

        root.rv.adapter = RvAdapter(root.context, list2, 2, object : RvAdapter.Click {
                override fun clicking(user: User) {
                    findNavController().navigate(R.id.showFragment, bundleOf("image" to user.image,"name" to user.name,"about" to user.about))
                }
                override fun like(user: User,image:ImageView) {
                    if (user.liked == "like"){
                        user.liked = "unliked"
                        DbHelper(root.context).updateMember(user)
                    }else{
                        user.liked = "like"
                        DbHelper(root.context).updateMember(user)
                    }

                    val list1 = DbHelper(root.context).getAllMember()
                    val list3 = ArrayList<User>()

                    for (i in list1.indices){
                        if (list1[i].liked == "like"){
                            list3.add(list1[i])
                        }
                    }

                    root.rv.adapter = RvAdapter(root.context, list3, 2, object : RvAdapter.Click {
                        override fun clicking(user: User) {
                            findNavController().navigate(R.id.showFragment, bundleOf("image" to user.image,"name" to user.name,"about" to user.about))
                        }
                        override fun like(user: User,image:ImageView) {
                            if (user.liked == "like"){
                                user.liked = "unliked"
                                DbHelper(root.context).updateMember(user)
                                onStart()
                            }else{
                                user.liked = "like"
                                DbHelper(root.context).updateMember(user)
                                onStart()
                            }
                        }

                        override fun remove(user: User) {
                            val d = BottomSheetDialog(root.context)

                            val i = LayoutInflater.from(root.context).inflate(R.layout.delete_dialog,null,false)

                            d.setContentView(i)

                            i.btnYoq.setOnClickListener {
                                d.hide()
                            }
                            i.btnHa.setOnClickListener {
                                d.hide()
                                DbHelper(root.context).deleteMember(user)
                                onStart()
                            }

                            d.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

                            d.setCancelable(true)

                            d.show()



                        }

                        override fun change(user: User) {
                            findNavController().navigate(R.id.editorFragment, bundleOf("user1" to user))
                        }
                    })

                }

            override fun remove(user: User) {
                val d = BottomSheetDialog(root.context)

                val i = LayoutInflater.from(root.context).inflate(R.layout.delete_dialog,null,false)

                d.setContentView(i)

                i.btnYoq.setOnClickListener {
                    d.hide()
                }
                i.btnHa.setOnClickListener {
                    d.hide()
                    DbHelper(root.context).deleteMember(user)
                    onStart()
                }

                d.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

                d.setCancelable(true)

                d.show()



            }

            override fun change(user: User) {
                findNavController().navigate(R.id.editorFragment, bundleOf("user1" to user))
            }
        })
    }



}