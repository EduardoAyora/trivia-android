package ec.edu.ups.ejemplo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

class EndActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end)
        val puntuacion: String? = intent.getStringExtra("puntuacion")
        Log.i("Puntuacion", puntuacion.toString())

        val btnReiniciar: Button = findViewById(R.id.btnReiniciar)
        val txtPuntuacion: TextView = findViewById(R.id.txtPuntuacion)
        txtPuntuacion.setText("Tu puntuación es ${puntuacion}/10")

        btnReiniciar.setOnClickListener(View.OnClickListener {
            newGame()
        })
    }

    fun newGame() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}