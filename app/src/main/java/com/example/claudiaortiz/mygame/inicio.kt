package com.example.claudiaortiz.mygame

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_inicio.*

class inicio : AppCompatActivity() {

    private lateinit var txtname:EditText
    private lateinit var txtapellido:EditText
    private lateinit var txtemail:EditText
    private lateinit var txtpasword:EditText
    private lateinit var progressBar:ProgressBar
    private lateinit var dbReference:DatabaseReference
    private lateinit var database:FirebaseDatabase
    private lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        btninicio.setOnClickListener {
            val intent: Intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        txtname= findViewById(R.id.txtname)
        txtapellido= findViewById(R.id.txtapellido)
        txtemail= findViewById(R.id.txtemail)
        txtpasword= findViewById(R.id.txtpasword)

        progressBar= ProgressBar (this)

        database= FirebaseDatabase.getInstance()
        auth=FirebaseAuth.getInstance()

        dbReference=database.reference.child("User")
    }

    fun register (view:View){

    }

    private fun createNewAccount(){
        val name:String=txtname.text.toString()
        val lastname:String=txtapellido.text.toString()
        val email:String=txtemail.text.toString()
        val contraseña:String=txtpasword.text.toString()
    }
}
