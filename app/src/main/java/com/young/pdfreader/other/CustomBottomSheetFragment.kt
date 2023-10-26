package com.young.pdfreader.other

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.FragmentActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.young.pdfreader.R
import com.young.pdfreader.data.DOWNLOAD_REQUEST_CODE
import com.young.pdfreader.data.UPLOAD_REQUEST_CODE

/**
 * Create by Young on 09/11/2021
 **/
class CustomBottomSheetFragment : BottomSheetDialogFragment() {
    companion object {
        const val TAG = "CustomBottomSheetFragment"
        fun getInstance(act: FragmentActivity, args: Bundle?): CustomBottomSheetFragment {
            val fragment = CustomBottomSheetFragment()
            fragment.show(
                act.supportFragmentManager,
                fragment.tag
            )
            fragment.apply {
                fragment.arguments = args
            }
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.bottom_sheet_fragment, container)
        Log.d(TAG,"onCreateView")
        return view.rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        Log.d(TAG,"onViewCreated")
        val uploadTV = view.findViewById<AppCompatTextView>(R.id.bottom_sheet_upload)
        val downloadTV = view.findViewById<AppCompatTextView>(R.id.bottom_sheet_download)
        uploadTV.setOnClickListener {
            dismiss()
            activity?.finish()
            val uploadIntent = Intent(activity, UploadActivity::class.java)
            startActivityForResult(uploadIntent, UPLOAD_REQUEST_CODE)
        }
        downloadTV.setOnClickListener {
            dismiss()
            activity?.finish()
            val downloadIntent = Intent(activity, DownloadActivity::class.java)
            startActivityForResult(downloadIntent, DOWNLOAD_REQUEST_CODE)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,"onStart")
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        Log.d(TAG,"onCreateOptionsMenu")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"onResume")
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        Log.d(TAG,"onPrepareOptionsMenu")
    }
    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        activity?.finish()
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        activity?.finish()
    }
}