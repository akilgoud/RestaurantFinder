package com.cloudfull.restaraunts.view

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import com.cloudfull.restaraunts.R
import com.cloudfull.restaraunts.databinding.FragmentRestaurantDetailsDialogBinding
import com.cloudfull.restaraunts.model.data.entities.RestaurantDetails
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class RestaurantDetailsDialogFragment : BottomSheetDialogFragment() {
    var restaurantDetails: RestaurantDetails? = null
    private var binding: FragmentRestaurantDetailsDialogBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentRestaurantDetailsDialogBinding =
            FragmentRestaurantDetailsDialogBinding.inflate(inflater, container, false)
        binding.details = restaurantDetails
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.callTextView?.setOnClickListener {
            context?.let { context ->
                binding?.details?.formattedPhoneNumber?.let { phone ->
                    invokePhoneCall(context, phone)
                }
            }
        }
    }

    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSIONS_REQUEST_CALL_PHONE) {
            if (permissions[0] == Manifest.permission.CALL_PHONE && grantResults[0]
                == PackageManager.PERMISSION_GRANTED
            ) {
                context?.let { context ->
                    binding?.details?.formattedPhoneNumber?.let { phone ->
                        invokePhoneCall(context, phone)
                    }
                }
            }
        }
    }

    private fun invokePhoneCall(context: Context, phoneNumber: String) {
        if (Build.VERSION.SDK_INT > 22) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissions(
                    arrayOf(Manifest.permission.CALL_PHONE),
                    PERMISSIONS_REQUEST_CALL_PHONE
                )
                return
            }
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:+" + phoneNumber.trim())
            startActivity(callIntent)
        } else {
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:+" + phoneNumber.trim())
            startActivity(callIntent)
        }
    }

    companion object {
        private const val PERMISSIONS_REQUEST_CALL_PHONE = 100_2
    }
}