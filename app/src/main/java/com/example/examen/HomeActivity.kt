package com.example.examen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

enum class ProviderType{
    Basic
}
class HomeActivity : AppCompatActivity() {
        private lateinit var emailTextView : TextView
        private lateinit var proveedoTextView : TextView
        private lateinit var logcerrarbutton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val bundle:Bundle? = intent.extras
        val email:String? = bundle?.getString("email")
        val provider: String? = bundle?.getString("provider")
        setup(email ?:" " , provider ?:"")
    }
    private fun setup(email: String, provider: String){

        title = "Inicio"
        emailTextView.text = email
        proveedoTextView.text = provider

        logcerrarbutton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }
    }
}