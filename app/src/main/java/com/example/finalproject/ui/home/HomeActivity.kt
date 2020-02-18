package com.example.finalproject.ui.home

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.finalproject.R
import com.example.finalproject.homePresenter
import com.example.finalproject.ui.home.home_tab_fragment.ChatFragment
import com.example.finalproject.ui.home.home_tab_fragment.HomeFragment
import com.example.finalproject.ui.home.home_tab_fragment.PersonalFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity(), ChatFragment.OnFragmentInteractionListener,
    HomeFragment.OnFragmentInteractionListener, PersonalFragment.OnFragmentInteractionListener, HomeContract.View {

    companion object {
        fun getInstance(context: Context): Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }

    private val presenter by lazy { homePresenter() }
    var navigator = HomeNavigator(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        homeBottomNavigation.setOnNavigationItemSelectedListener(navListener)
        presenter.setView(this)

    }

    private val navListener: BottomNavigationView.OnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            var selectedFragment: Fragment? = null
            when (item.itemId) {
                R.id.nav_home -> selectedFragment = HomeFragment()
                R.id.nav_chat -> selectedFragment = ChatFragment()
                R.id.nav_personal -> selectedFragment = PersonalFragment()
            }
            if (selectedFragment != null) {
                supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    selectedFragment
                ).commit()
            }
            true
        }

    override fun onFragmentInteraction(uri: Uri) = Unit

    override fun onLogOutButtonClick() {
        presenter.onLogOutButtonClick()
    }

    override fun onLogOut() {
        navigator.navigateLoginScreen()
    }

}
