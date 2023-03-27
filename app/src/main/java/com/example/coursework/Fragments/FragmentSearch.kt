package com.example.coursework.Fragments

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.asLiveData
import com.example.coursework.*
import com.example.coursework.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class FragmentSearch : Fragment() {

    var pref: SharedPreferences? = null
    var login = ""
    var startDate = "24.03 22:00"
    var endDate = "25.03 22:00"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pref = requireContext().getSharedPreferences("TABLE", Context.MODE_PRIVATE)
        login = pref?.getString("login", "null")!!

        createList()
    }



    fun createList(){
        var carList = ArrayList<String>()
        var fullInfoCarList = ArrayList<String>()
        val db = MainDB.getDB(requireContext())

        db.getDao().getAllItem().asLiveData().observe(requireActivity()){
            it.forEach{
                val text = "${it.carBrand} ${it.model}"
                val fullInfoText = "Марка: ${it.carBrand} \nМодель: ${it.model} \nГод выпуска: ${it.createYear} \nЦвет: ${it.color} \nНомер: ${it.regNumber}"
                carList.add(text);
                fullInfoCarList.add(fullInfoText)
            }
        }

        val listView = view?.findViewById<ListView>(R.id.listView2)
        val adapter = activity?.let {
            ArrayAdapter<String>(
                it,
                android.R.layout.simple_list_item_1,
                carList
            )
        }

        listView?.adapter = adapter
        listView?.setOnItemClickListener{parent, view, position, id ->

            //Toast.makeText(requireActivity(), "Pressed item position ${position}", Toast.LENGTH_SHORT).show()
            val builder = AlertDialog.Builder(requireActivity())
            //builder.setTitle("Alert")
            builder.setMessage(fullInfoCarList.get(position))

            builder.setNeutralButton("Бронь", DialogInterface.OnClickListener { dialog, which ->



                //val user = Users(loginInfo?.text.toString(),passwordInfo?.text.toString(), role, " ", " ", " ", " ", 1)
                val user = Users(login, "123", "role", "","",""," ", position)
                val contract = Contracts(null, startDate, endDate, "500$", login, position)
                thread {
                    db.getDao().updateUser(user)

                }
                thread {
                    db.getDao().insertContracts(contract)
                }
                Toast.makeText(requireContext(), "Успех",Toast.LENGTH_SHORT).show()


            })
            builder.show()

        }

    }




}