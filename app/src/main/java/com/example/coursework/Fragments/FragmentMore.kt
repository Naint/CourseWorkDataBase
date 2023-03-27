package com.example.coursework.Fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils.replace
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.coursework.R
import com.example.coursework.Fragments.*
import androidx.fragment.app.FragmentManager


class FragmentMore : Fragment() {

    var pref: SharedPreferences? = null
    var role = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_more, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonAdminPanel = view?.findViewById<Button>(R.id.buttonAdminPanel)

        checkRole()
        buttonAdminPanel?.setOnClickListener {
            val adminPanelFragment = FragmentAdminPanel()
            setCurrentFragment(adminPanelFragment)
        }

    }

    private fun checkRole(){
        val buttonAdminPanel = view?.findViewById<Button>(R.id.buttonAdminPanel)
        pref = requireContext().getSharedPreferences("TABLE", Context.MODE_PRIVATE)
        role = pref?.getString("role", "null")!!

        if(role == "user")
            buttonAdminPanel?.visibility = View.INVISIBLE
    }

    private fun setCurrentFragment(Fragment: Fragment){
        getParentFragmentManager().beginTransaction().apply {
            replace(R.id.container, Fragment)
            commit()

        }
    }



}