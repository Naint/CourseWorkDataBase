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
import com.example.coursework.Cars
import com.example.coursework.MainDB
import com.example.coursework.R
import com.example.coursework.Users
import kotlin.concurrent.thread

class FragmentAutorization : Fragment() {

    var pref : SharedPreferences? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        return inflater.inflate(R.layout.fragment_autorization, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = MainDB.getDB(requireContext())
        val buttonLogin = view?.findViewById<Button>(R.id.loginButton)
        val loginInfo = view?.findViewById<EditText>(R.id.loginText)
        val passwordInfo = view?.findViewById<EditText>(R.id.passwordText)

        pref = requireContext().getSharedPreferences("TABLE", Context.MODE_PRIVATE)
        var role : String = "admin"


        buttonLogin?.setOnClickListener{

            //val car = Cars(null, brandInfo?.text.toString(),modelInfo?.text.toString(), regNumberInfo?.text.toString(), createYearInfo?.text.toString().toInt(), mileageInfo?.text.toString(),colorInfo?.text.toString(),statusInfo?.text.toString(), parkingIdInfo?.text.toString().toInt())
            val user = Users(loginInfo?.text.toString(),passwordInfo?.text.toString(), role, " ", " ", " ", " ", -1)
            thread {
                db.getDao().insertUser(user)
                saveLogin(loginInfo?.text.toString())
                saveRole(role)
            }

            val searchFragment = FragmentSearch()
            setCurrentFragment(searchFragment)


        }


    }

    fun saveLogin(login : String){
        val editor = pref?.edit()
        editor?.putString("login", login)
        editor?.apply()
    }

    fun saveRole(role : String){
        val editor = pref?.edit()
        editor?.putString("role", role)
        editor?.apply()
    }

    private fun setCurrentFragment(Fragment: Fragment){
        getParentFragmentManager().beginTransaction().apply {
            replace(R.id.container, Fragment)
            commit()

        }
    }


}