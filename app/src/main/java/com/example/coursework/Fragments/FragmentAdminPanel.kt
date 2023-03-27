package com.example.coursework.Fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.TextUtils.replace
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import com.example.coursework.R
import com.example.coursework.Fragments.FragmentAdminPanel
import com.example.coursework.MainDB
import com.example.coursework.databinding.ActivityMainBinding
import com.example.coursework.databinding.ActivityMainBinding.inflate
import com.example.coursework.Cars
import com.example.coursework.parking
import com.example.coursework.databinding.FragmentAdminPanelBinding.inflate
import kotlin.concurrent.thread


class FragmentAdminPanel : Fragment() {

    lateinit var binding: FragmentAdminPanel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
/*        val db = MainDB.getDB(requireContext())

        val modelInfo = view?.findViewById<Button>(R.id.modelEditText)
        val brandInfo = view?.findViewById<Button>(R.id.brandEditText)
        val colorInfo = view?.findViewById<Button>(R.id.colorEditText)
        val createYearInfo = view?.findViewById<Button>(R.id.cYearEditText)
        val regNumberInfo = view?.findViewById<Button>(R.id.regNumberEditText)
        val statusInfo = view?.findViewById<Button>(R.id.statusEditText)
        val mileageInfo = view?.findViewById<Button>(R.id.mileageEditText)
        val parkingIdInfo = view?.findViewById<Button>(R.id.parkingIdExitText)*/

        // Inflate the layout for this fragment
        //binding = FragmentAdminPanel.inflate(layoutInflater, container. false)
        return inflater.inflate(R.layout.fragment_admin_panel, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addCarInDB()
        addParkingInDB()
        deleteCar()
        deleteParking()
    }

    fun addCarInDB(){
        val db = MainDB.getDB(requireContext())
        val buttonAddCar = view?.findViewById<Button>(R.id.buttonAddCar)
        buttonAddCar?.setOnClickListener {
            val modelInfo = view?.findViewById<EditText>(R.id.modelEditText)
            val brandInfo = view?.findViewById<EditText>(R.id.brandEditText)
            val colorInfo = view?.findViewById<EditText>(R.id.colorEditText)
            val createYearInfo = view?.findViewById<EditText>(R.id.cYearEditText)
            val regNumberInfo = view?.findViewById<EditText>(R.id.regNumberEditText)
            val statusInfo = view?.findViewById<EditText>(R.id.statusEditText)
            val mileageInfo = view?.findViewById<EditText>(R.id.mileageEditText)
            val parkingIdInfo = view?.findViewById<EditText>(R.id.parkingIdExitText)

            val car = Cars(null, brandInfo?.text.toString(),modelInfo?.text.toString(), regNumberInfo?.text.toString(), createYearInfo?.text.toString().toInt(), mileageInfo?.text.toString(),colorInfo?.text.toString(),statusInfo?.text.toString(), parkingIdInfo?.text.toString().toInt())
            thread {
                db.getDao().insertCar(car)
            }

            Toast.makeText(requireContext(), "Успех",Toast.LENGTH_SHORT).show()

        }


    }

    fun addParkingInDB(){
        val db = MainDB.getDB(requireContext())
        val buttonAddParking = view?.findViewById<Button>(R.id.buttonAddParking)

        buttonAddParking?.setOnClickListener{

            val typeInfo = view?.findViewById<EditText>(R.id.typeEditText)
            val addressInfo = view?.findViewById<EditText>(R.id.addressEditText)
            val capacityInfo = view?.findViewById<EditText>(R.id.capacityEditText)

            val parks = parking(null, typeInfo?.text.toString(), capacityInfo?.text.toString(), addressInfo?.text.toString())
            thread {
                db.getDao().insertParking(parks)
            }
            Toast.makeText(requireContext(), "Успех",Toast.LENGTH_SHORT).show()


        }


    }

    fun deleteCar(){
        val db = MainDB.getDB(requireContext())
        val buttonDeleteCar = view?.findViewById<Button>(R.id.buttonDeleteCar)

        val carIdDelInfo = view?.findViewById<EditText>(R.id.carIdEditText)

        buttonDeleteCar?.setOnClickListener{
            thread {
                db.getDao().deleteCarForId(carIdDelInfo?.text.toString().toInt())
            }
        }
    }

    fun deleteParking(){
        val db = MainDB.getDB(requireContext())

        val buttonDeleteParking = view?.findViewById<Button>(R.id.buttonDeleteParking)

        val parkingIdDelInfo = view?.findViewById<EditText>(R.id.parkingIdEditText)

        buttonDeleteParking?.setOnClickListener{
            thread {
                db.getDao().deleteParkingForId(parkingIdDelInfo?.text.toString().toInt())
            }
        }


    }


}



