package com.jamesapprockfield.ui.login

import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.content.Intent
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.jamesapprockfield.myapp.MainActivity
import com.jamesapprockfield.myapp.R
import kotlinx.android.synthetic.main.activity_login.*

open class LoginActivity : AppCompatActivity(),  GoogleApiClient.OnConnectionFailedListener  {
     private var mGoogleApiClient: GoogleApiClient? = null
     lateinit var mGoogleSignInOptions: GoogleSignInOptions
     private val RC_SIGN_IN = 9001
     private var mFirebaseAuth: FirebaseAuth? = null
     var mProgressDialog: ProgressDialog? = null


    override fun onConnectionFailed(p0: ConnectionResult) {
         TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
     }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)


        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        mGoogleApiClient = GoogleApiClient.Builder(this)
            .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
            .addApi<GoogleSignInOptions>(Auth.GOOGLE_SIGN_IN_API, gso)
            .build()


        mFirebaseAuth = FirebaseAuth.getInstance()

        sign_in_button.setOnClickListener{
            signIn()
        }
    }

    public override fun onStart() {
        super.onStart()
        val currentUser = mFirebaseAuth?.getCurrentUser()

    }

    private fun signIn() {
        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }


   override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)

            if (result.isSuccess) {
                // Google Sign In was successful, authenticate with Firebase
                val account = result.signInAccount
                firebaseAuthWithGoogle(account!!)
            } else {
            }
        }

    }



    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        mFirebaseAuth?.signInWithCredential(credential)?.addOnCompleteListener(this,
            OnCompleteListener<AuthResult> { task ->
                if (!task.isSuccessful) {
                    Toast.makeText(
                        this, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
            })
    }


}
