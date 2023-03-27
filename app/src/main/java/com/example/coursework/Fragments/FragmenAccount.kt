package com.example.coursework.Fragments 

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.coursework.MainDB
import com.example.coursework.R
import com.example.coursework.Users
import kotlin.concurrent.thread


class FragmenAccount : Fragment() {

    var pref: SharedPreferences? = null
    var login = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pref = requireContext().getSharedPreferences("TABLE", Context.MODE_PRIVATE)
        login = pref?.getString("login", "null")!!

        val loginInfo = view?.findViewById<TextView>(R.id.loginView)

        loginInfo?.text = login

    }


}