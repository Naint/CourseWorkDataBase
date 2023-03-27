package com.example.coursework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coursework.Fragments.*
import com.example.coursework.databinding.ActivityMainBinding
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = MainDB.getDB(this);

        val bookingFragment = FragmentBooking()
        val searchFragment = FragmentSearch()
        val moreFragment = FragmentMore()
        val accountFragment = FragmenAccount()
        val adminPanelFragment = FragmentAdminPanel()
        val autorizationFragment = FragmentAutorization()

/*        var carList = ArrayList<String>()
        carList.add("Honda Civic, 2017")
        carList.add("Subaru Levorg, 2017")
        val listView = findViewById<ListView>(R.id.listView)
        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, carList)
        listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, carList)*/

        setCurrentFragment(autorizationFragment)

        binding.bNav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_myBooking -> setCurrentFragment(bookingFragment)
                R.id.nav_account -> setCurrentFragment(accountFragment)
                R.id.nav_more -> setCurrentFragment(moreFragment)
                R.id.nav_searchCar -> setCurrentFragment(searchFragment)
            }
            true
        }

/*        val car = Cars(null, "Kia","Rio", "Р043НР", 2015, "25.000","Синий","Активен", 1)
        thread {
            db.getDao().insertCar(car)
        }*/




        }




    private fun setCurrentFragment(Fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, Fragment)
            commit()

        }
    }





}



/*lateinit var binding: ActivityMainBinding

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
    val db = MainDB.getDB(this);



    binding.btnSave.setOnClickListener{
        val car = Cars(null, "Honda","Civic", "Р041НР", 2017, "20.000","Серый","Активен", 1 )
        val item = parking(null, binding.edType.getText().toString(), binding.edCapacity.getText().toString(), binding.edAddress.getText().toString())
        val contract = Contracts(null, "02.09.2002","03.09.2002","200","restless", 1)
        val rta = Rta(null, "03.09.2002","пиздец",2,1)
        val usr = Users("restыleskss","vlad2002","admin","fio","229","2","have",1)

        Thread{
            db.getDao().insertItem(item)
            db.getDao().insertCar(car)
            db.getDao().insertUser(usr)
            db.getDao().insertRta(rta)
            db.getDao().insertContracts(contract)
        }.start()

    }*/


