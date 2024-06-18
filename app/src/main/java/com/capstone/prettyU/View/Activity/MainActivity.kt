package com.capstone.prettyU.View.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.capstone.prettyU.R
import com.capstone.prettyU.View.Fragment.FaceScanFragment
import com.capstone.prettyU.View.Fragment.MainPageFragment
import com.capstone.prettyU.View.Fragment.ProfileFragment
import com.capstone.prettyU.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var fragmentManager: FragmentManager
    lateinit var bottomNav : BottomNavigationView
    //private lateinit var rvItem: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bottomNav = binding.bottomNav

        fragmentManager = supportFragmentManager
        loadFragment(MainPageFragment())
        navigationListener()
    }

    // TODO: set animasi
    private fun playAnimation() {

    }


    // Fragment switch

    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView,fragment)
        transaction.commit()
    }

    private fun navigationListener() {
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    loadFragment(MainPageFragment())
                    true
                }
                R.id.message -> {
                    loadFragment(FaceScanFragment())
                    true
                }

                else -> {loadFragment(ProfileFragment())
                    true}
            }
        }
    }
}