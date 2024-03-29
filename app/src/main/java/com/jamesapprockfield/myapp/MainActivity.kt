package com.jamesapprockfield.myapp

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.jamesapprockfield.ui.login.LoginActivity

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private var mGoogleSignInClient: GoogleSignInClient? = null
    private var mFirebaseAuth: FirebaseAuth? = null

    private var mFirebaseUser: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send, R.id.nav_card
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // Initialize Firebase Auth
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

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
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
}