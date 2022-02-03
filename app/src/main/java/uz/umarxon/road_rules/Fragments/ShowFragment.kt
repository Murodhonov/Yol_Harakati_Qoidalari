package uz.umarxon.road_rules.Fragments

import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.android.synthetic.main.fragment_show.view.*
import kotlinx.android.synthetic.main.fragment_show.view.back
import kotlinx.android.synthetic.main.fragment_show.view.name
import uz.umarxon.road_rules.MainActivity
import uz.umarxon.road_rules.R
import uz.umarxon.road_rules.Utils.Data
import uz.umarxon.road_rules.databinding.FragmentShowBinding

class ShowFragment : Fragment() {

    lateinit var root:View
    lateinit var binding:FragmentShowBinding

    override fun onStart() {
        super.onStart()
        (activity as MainActivity?)?.isVisible(false)
        Data.isHome = false
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        root = inflater.inflate(R.layout.fragment_show, container, false)
        binding = FragmentShowBinding.inflate(LayoutInflater.from(context))

        val byte = arguments?.getString("image")
        val name = arguments?.getString("name","null")
        val about = arguments?.getString("about","null")

        root.image_view.setImageURI(Uri.parse(byte))
        root.header_title.text = name
        root.name.text = name
        root.txt_msg.text = about

        root.back.setOnClickListener{
            findNavController().popBackStack()
        }

        return root
    }
}