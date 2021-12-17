package my.edu.tarc.demo2.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import my.edu.tarc.demo2.MainActivity
import my.edu.tarc.demo2.R


class LoginActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        val currentuser = auth.currentUser
        if(currentuser != null) {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
        }
        login()
    }

    private fun login() {

        buttonLogin.setOnClickListener {

            if(TextUtils.isEmpty(editTextTextEmail.text.toString())){
                editTextTextEmail.setError("Please enter email")
                return@setOnClickListener
            }
            else if(TextUtils.isEmpty(editTextTextPassword.text.toString())){
                editTextTextPassword.setError("Please enter password")
                return@setOnClickListener
            }


          auth.signInWithEmailAndPassword(editTextTextEmail.text.toString(), editTextTextPassword.text.toString())
                .addOnCompleteListener {
                    if(it.isSuccessful) {
                        Toast.makeText(this@LoginActivity, "Login Successful ", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this@LoginActivity, "Login failed, please try again! ", Toast.LENGTH_LONG).show()
                    }
                }

        }

        textViewRegister.setOnClickListener{
            startActivity(Intent(this@LoginActivity, RegistrationActivity::class.java))

        }
    }
}