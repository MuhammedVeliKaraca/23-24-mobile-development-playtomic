package edu.ap.mobiledevelopmentproject.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import edu.ap.mobiledevelopmentproject.R
import edu.ap.mobiledevelopmentproject.databinding.FragmentProfileBinding

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val imageView = view.findViewById<ImageView>(R.id.imageView)
        val base64Image = "yourBase64EncodedImage"
        val bitmap = decodeBase64ToBitmap(base64Image)
        imageView.setImageBitmap(bitmap)

        return view
    }

    fun decodeBase64ToBitmap(encodedImage: String): Bitmap? {
        val decodedBytes = Base64.decode(encodedImage, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    }

}