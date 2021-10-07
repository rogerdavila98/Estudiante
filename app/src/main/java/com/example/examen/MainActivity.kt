package com.example.examen

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import java.security.Provider

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var signbutton :Button
    private lateinit var loginbutton :Button
    private lateinit var emaileditText :EditText
    private lateinit var passwordeditText :EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    setup()

    }
    private fun setup(){
        title = "Autenticacion"

        signbutton.setOnClickListener {
            if(emaileditText.text.isNotEmpty() && passwordeditText.text.isNotEmpty()){

                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(emaileditText.text.toString(),
                       passwordeditText.text.toString()).addOnCompleteListener {

                    if(it.isSuccessful){
                        showHome(it.result?.user?.email ?: "", ProviderType.Basic)

                    }else{
                        showAlert()
                    }
                }
            }

        }

        loginbutton.setOnClickListener {
            if(emaileditText.text.isNotEmpty() && passwordeditText.text.isNotEmpty()){

                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(emaileditText.text.toString(),
                        passwordeditText.text.toString()).addOnCompleteListener {

                    if(it.isSuccessful){
                        showHome(it.result?.user?.email ?: "", ProviderType.Basic)
                    }else{
                        showAlert()
                    }
                    }
            }
        }


    }
    private  fun showAlert(){
        val builder  = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("se ha producido un error")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }
    private fun showHome(email: String, provider: ProviderType){
        val homeIntent = Intent(this, HomeActivity::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(homeIntent)


}
}