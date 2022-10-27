package ec.edu.ups.ejemplo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputNombre: EditText = findViewById(R.id.inputNombre)
        val btnLogin : Button = findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener(View.OnClickListener {
            val username: String = inputNombre.text.toString()
            if (username != "") {
                val toast = Toast.makeText(applicationContext, "Bienvenido ${username}", Toast.LENGTH_SHORT)
                toast.show()
                val intent = Intent(this, SecondActivity::class.java).apply {
                    putExtra("username", username)
                }
                startActivity(intent)
            } else {
                val toast = Toast.makeText(applicationContext, "Debe ingresar un nombre", Toast.LENGTH_SHORT)
                toast.show()
            }
        })
    }
}