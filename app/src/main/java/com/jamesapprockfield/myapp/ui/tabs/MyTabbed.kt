package com.jamesapprockfield.myapp.ui.tabs

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.jamesapprockfield.myapp.R
import com.jamesapprockfield.myapp.SettingsActivity
import com.jamesapprockfield.myapp.ui.tabs.ui.main.SectionsPagerAdapter
import com.jamesapprockfield.ui.login.LoginActivity

class MyTabbed : AppCompatActivity() {

    private var mGoogleSignInClient: GoogleSignInClient? = null
    private var mFirebaseAuth: FirebaseAuth? = null

    private var mFirebaseUser: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_my_tabbed)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        val fab: FloatingActionButton = findViewById(R.id.fab)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        mFirebaseAuth = FirebaseAuth.getInstance()
        mFirebaseUser = mFirebaseAuth!!.currentUser
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_logout -> {
                val alert = AlertDialog.Builder(this)
                alert.setPositiveButton("Yes") { dialog, which ->
                    mFirebaseAuth?.signOut()
                    FirebaseAuth.getInstance().signOut()
                    mGoogleSignInClient?.signOut()
                    val myIntent = Intent(this, LoginActivity::class.java)
                    startActivity(myIntent)
                }
                alert.setNegativeButton("No") { dialog, which ->
                    dialog.cancel()
                }
                alert.setTitle("Log Out")
                alert.setMessage("Are you sure you want to log out?")
                alert.setIcon(R.drawable.common_google_signin_btn_icon_dark)
                alert.show()
            }

            R.id.action_settings ->{
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}