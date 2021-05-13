package com.example.myapplication.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.myapplication.R
import com.example.myapplication.ui.fragment.ThirdAdditionalFragment
import com.example.myapplication.ui.fragment.HomeFragment
import com.example.myapplication.ui.fragment.SecondAdditionalFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

     val homeFragment by lazy {
        HomeFragment()
    }

    private lateinit var secondFragment: Fragment

    private lateinit var thirdFragment: Fragment

    val fm: FragmentManager = supportFragmentManager
    var active: Fragment = homeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        fm.beginTransaction().add(R.id.fragment_container, homeFragment, "homeFragment").commit()
    }

// to handle the navigation between the fragment's hide and show process to maintain the data
    private val mOnNavigationItemSelectedListener: BottomNavigationView.OnNavigationItemSelectedListener =
        object : BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(@NonNull item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.navigation_home -> {
                        fm.beginTransaction().hide(active).show(homeFragment).commit()
                        active = homeFragment
                        return true
                    }
                    R.id.navigation_Plus -> {
                        if (this@MainActivity::secondFragment.isInitialized) {
                            fm.beginTransaction().hide(active).show(secondFragment).commit()
                            active = secondFragment
                        } else {
                            secondFragment = SecondAdditionalFragment()
                            fm.beginTransaction()
                                .add(R.id.fragment_container, secondFragment, "secondFragment")
                                .hide(active).commit()
                            active = secondFragment
                        }
                        return true
                    }
                    R.id.navigation_notifications -> {
                        if (this@MainActivity::thirdFragment.isInitialized) {
                            fm.beginTransaction().hide(active).show(thirdFragment).commit()
                            active = thirdFragment
                        } else {
                            thirdFragment = ThirdAdditionalFragment()
                            fm.beginTransaction()
                                .add(R.id.fragment_container, thirdFragment, "thirdFragment")
                                .hide(active).commit()
                            active = thirdFragment
                        }

                        return true
                    }
                }
                return false

            }
        }



}