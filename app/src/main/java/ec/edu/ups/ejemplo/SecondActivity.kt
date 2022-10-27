package ec.edu.ups.ejemplo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        var indicePregunta = 0
        val banco: List<Pregunta> = listOf<Pregunta>(
            Pregunta("1?", listOf(Respuesta("1", R.drawable.messi),Respuesta("2", R.drawable.ronaldo)) , 0),
            Pregunta("2?", listOf(Respuesta("3", R.drawable.messi),Respuesta("4", R.drawable.ronaldo)) , 0),
            Pregunta("3?", listOf(Respuesta("5", R.drawable.messi),Respuesta("6", R.drawable.ronaldo)) , 0),
        )

        var respuestasCorrectas = 0

        val username = intent.getStringExtra("username")
        val txtUsername: TextView = findViewById(R.id.txtUsername)
        txtUsername.text = "Bienvenido ${username}"

        val txtPregunta: TextView = findViewById(R.id.txtPregunta)
        val btnOptionOne: Button = findViewById(R.id.btnOptionOne)
        val btnOptionTwo: Button = findViewById(R.id.btnOptionTwo)
        val imgResultado: ImageView = findViewById(R.id.imgResultado)


        fun setQuestion() {
            txtPregunta.setText(banco.get(indicePregunta).valor)
            btnOptionOne.setText(banco.get(indicePregunta).respuestas[0].valor)
            btnOptionTwo.setText(banco.get(indicePregunta).respuestas[1].valor)
        }

        setQuestion()

        fun selectAnswer(selectedAnswer: Int) {
            if (selectedAnswer == banco.get(indicePregunta).indiceRespuestaCorrecta) {
                val toast = Toast.makeText(applicationContext, "Correcto!", Toast.LENGTH_LONG)
                toast.show()
                respuestasCorrectas += 1
            } else {
                val toast = Toast.makeText(applicationContext, "Incorrecto!", Toast.LENGTH_LONG)
                toast.show()
            }
            if (indicePregunta == banco.size - 1) {
                val intent = Intent(this, EndActivity::class.java).apply {
                    putExtra("puntuacion", respuestasCorrectas.toString())
                }
                return startActivity(intent)
            }
            indicePregunta += 1
            setQuestion()
        }

        btnOptionOne.setOnClickListener(View.OnClickListener {
            val opcion = 0
            imgResultado.setImageResource(banco.get(indicePregunta).respuestas.get(opcion).imagenId)
            selectAnswer(opcion)
        })

        btnOptionTwo.setOnClickListener(View.OnClickListener {
            val opcion = 1
            imgResultado.setImageResource(banco.get(indicePregunta).respuestas.get(opcion).imagenId)
            selectAnswer(opcion)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.game_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuReiniciar -> {
                newGame()
                true
            }
            R.id.menuSalir -> {
                exit()
                true
            } else -> super.onOptionsItemSelected(item)
        }
    }

    fun newGame() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun exit() {
        this.finishAffinity()
    }
}

class Pregunta(valor: String, respuestas: List<Respuesta>, indiceRespuestaCorrecta: Int) {
    val valor: String = valor
        get() = field

    val respuestas: List<Respuesta> = respuestas
        get() = field

    val indiceRespuestaCorrecta: Int = indiceRespuestaCorrecta
        get() = field
}

class Respuesta(valor: String, imagenId: Int) {
    val valor: String = valor
        get() = field

    val imagenId: Int = imagenId
        get() = field
}

