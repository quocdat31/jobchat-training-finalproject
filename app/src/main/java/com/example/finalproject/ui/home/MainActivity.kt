package com.example.finalproject.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.finalproject.R
import com.example.finalproject.homePresenter
import com.example.finalproject.ui.home.main_tab_fragment.chat.ChatFragment
import com.example.finalproject.ui.home.main_tab_fragment.contact.ContactFragment
import com.example.finalproject.ui.home.main_tab_fragment.home.HomeFragment
import com.example.finalproject.ui.home.main_tab_fragment.personal.PersonalFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*


class MainActivity : AppCompatActivity(), PersonalFragment.OnFragmentInteractionListener,
    HomeContract.View {

    companion object {
        fun getInstance(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    private val presenter by lazy { homePresenter() }
    var navigator = MainNavigator(this)

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
                R.id.nav_home -> selectedFragment =
                    HomeFragment()
                R.id.nav_chat -> selectedFragment =
                    ChatFragment()
                R.id.nav_personal -> selectedFragment =
                    PersonalFragment()
                R.id.nav_contact -> selectedFragment = ContactFragment()
            }
            if (selectedFragment != null) {
                supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    selectedFragment
                ).commit()
            }
            true
        }


    override fun onPersonalFragmentLogOutButtonClick() {
        presenter.logOut()
    }

    override fun onLogOut() {
        navigator.navigateLoginScreen()
    }

}
