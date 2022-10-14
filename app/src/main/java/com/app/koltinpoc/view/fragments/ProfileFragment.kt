package com.app.koltinpoc.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.koltinpoc.R
import org.json.JSONObject


class ProfileFragment : Fragment() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = requireArguments().getString("message")
        var obj = JSONObject(data.toString())
        var nestedObject = JSONObject(obj["customFieldInputValues"].toString())
        Log.d("MESSAGE",obj["user_id"].toString())

       /* displayEmailId.text = obj["identifier"].toString()
        displayFirstName.text = nestedObject["First Name"].toString()
        displayLastName.text = nestedObject["Last Name"].toString()*/

    }


}