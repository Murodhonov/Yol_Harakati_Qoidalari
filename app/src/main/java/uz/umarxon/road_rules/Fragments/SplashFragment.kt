package uz.umarxon.road_rules.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import uz.umarxon.road_rules.MainActivity
import uz.umarxon.road_rules.R
import uz.umarxon.road_rules.Utils.Data
import uz.umarxon.road_rules.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    lateinit var binding : FragmentSplashBinding
    lateinit var root:View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        root = inflater.inflate(R.layout.fragment_splash, container, false)
        binding = FragmentSplashBinding.bind(root)

        binding.root.setOnClickListener {
            findNavController().navigate(R.id.mainFragment)
        }

        Data.isHome = false

        return root
    }

    override fun onStart() {
        super.onStart()
        (activity as MainActivity?)?.isVisible(false)
        val anim = AnimationUtils.loadAnimation(root.context,R.anim.anim)
        binding.root.startAnimation(anim)

        anim.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                findNavController().navigate(R.id.mainFragment)
            }

            override fun onAnimationRepeat(p0: Animation?) {

            }
        })
    }
}