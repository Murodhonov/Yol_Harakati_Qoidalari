package uz.umarxon.road_rules.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.umarxon.road_rules.R
import uz.umarxon.road_rules.Utils.Data

class InfoFragment : Fragment() {

    lateinit var root:View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        root =  inflater.inflate(R.layout.fragment_info, container, false)

        Data.isHome = false

        return root
    }

}