package uz.umarxon.road_rules.Fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import uz.umarxon.road_rules.Adapter.CustomSpinner
import uz.umarxon.road_rules.DB.DbHelper
import uz.umarxon.road_rules.MainActivity
import uz.umarxon.road_rules.Models.User
import uz.umarxon.road_rules.R
import uz.umarxon.road_rules.Utils.Data
import uz.umarxon.road_rules.databinding.FragmentAddBinding
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class  AddFragment : Fragment() {

    lateinit var binding : FragmentAddBinding
    lateinit var root:View
    var image:String = ""
    val CODE_REQUEST = 1

    override fun onStart() {
        super.onStart()
        (activity as MainActivity?)?.isVisible(false)

        Data.isHome = false
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        root = inflater.inflate(R.layout.fragment_add, container, false)
        binding = FragmentAddBinding.inflate(LayoutInflater.from(context))

        root.imageAdd.setOnClickListener {
            getImageContent.launch("image/*")
        }

        root.back.setOnClickListener{
            findNavController().popBackStack()
        }

        root.imageAdd.setImageResource(R.drawable.placeholder_image1)

        root.save.setOnClickListener {
            val name = root.name.text.toString()
            val about = root.description.text.toString()
            val type = when(root.spinner.selectedItemPosition){
                0->{
                    "empty"
                }
                1->{
                    "Ogohlantiruvchi"
                }
                2->{
                    "Imtiyozli"
                }
                3->{
                    "Ta'qiqlovchi"
                }
                4->{
                    "Buyuruvchi"
                }
                else -> {
                    "empty"
                }
            }
            val liked = "unliked"

            if (name != "") {
                if (about != "") {
                    if (image != "") {
                        if(type != "empty"){
                            DbHelper(root.context).addMember(User(image, name, about, type, liked))
                            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
                            findNavController().popBackStack()
                        }else{
                            Toast.makeText(context, "Belgi turini tanlang", Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        Snackbar.make(root,"Empty Image" ,Snackbar.LENGTH_LONG).show()
                    }
                }else{
                    Snackbar.make(root,"Empty About" ,Snackbar.LENGTH_LONG).show()
                }
            }else{
                Snackbar.make(root,"Empty Name" ,Snackbar.LENGTH_LONG).show()
            }
        }


        return root
    }

    @SuppressLint("SimpleDateFormat")
    private val getImageContent = registerForActivityResult(ActivityResultContracts.GetContent()){ uri: Uri ->
        uri ?: return@registerForActivityResult
        root.imageAdd.setImageURI(uri)

        val inputStream = activity?.contentResolver?.openInputStream(uri)
        val simpleDateFormat = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val file = File(activity?.filesDir,"${simpleDateFormat}rasm.jpg")
        val fileOutputStream = FileOutputStream(file)
        inputStream?.copyTo(fileOutputStream)

        inputStream?.close()
        fileOutputStream.close()

        image = file.absolutePath
    }
}