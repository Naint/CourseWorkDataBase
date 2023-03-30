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


        }




    private fun setCurrentFragment(Fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, Fragment)
            commit()

        }
    }





}
