package com.thedevelopercat.sonic.ui.fragments

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.thedevelopercat.sonic.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.dialog_fragment_bitmap_image_viewer.*

class SonicImageViewerDialogFragment : DialogFragment() {

    var bitmap: Bitmap? = null
    var imageUrl: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_fragment_bitmap_image_viewer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (bitmap != null)
            image.setImageBitmap(bitmap)
        if (imageUrl != null)
            Picasso.get().load(imageUrl).into(image)
    }
}