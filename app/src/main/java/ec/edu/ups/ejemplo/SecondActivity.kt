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

        banco = banco.shuffled()

        var indicePregunta = 0
        var respuestasCorrectas = 0

        val username = intent.getStringExtra("username")
        val txtUsername: TextView = findViewById(R.id.txtUsername)

        val txtPregunta: TextView = findViewById(R.id.txtPregunta)
        val btnOptionOne: Button = findViewById(R.id.btnOptionOne)
        val btnOptionTwo: Button = findViewById(R.id.btnOptionTwo)
        val btnOptionThree: Button = findViewById(R.id.btnOpcionThree)
        val imgResultado: ImageView = findViewById(R.id.imgResultado)


        fun setQuestion() {
            txtPregunta.setText(banco.get(indicePregunta).valor)
            btnOptionOne.setText(banco.get(indicePregunta).respuestas[0].valor)
            btnOptionTwo.setText(banco.get(indicePregunta).respuestas[1].valor)
            btnOptionThree.setText(banco.get(indicePregunta).respuestas[2].valor)
            txtUsername.text = "Puntuación de ${username}: ${respuestasCorrectas}"
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

        fun buttonClick(opcion: Int) {
            imgResultado.setImageResource(banco.get(indicePregunta).respuestas.get(opcion).imagenId)
            selectAnswer(opcion)
        }

        btnOptionOne.setOnClickListener(View.OnClickListener {
            buttonClick(0)
        })

        btnOptionTwo.setOnClickListener(View.OnClickListener {
            buttonClick(1)
        })

        btnOptionThree.setOnClickListener(View.OnClickListener {
            buttonClick(2)
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

var banco: List<Pregunta> = listOf<Pregunta>(
    Pregunta("El Mundial de Qatar será la edición número:", listOf(
        Respuesta("21", R.drawable.qatar),
        Respuesta("22", R.drawable.qatar),
        Respuesta("23", R.drawable.qatar),
    ) , 1),
    Pregunta("Qatar se convierte en el país número__ en albergar el Mundial", listOf(
        Respuesta("16", R.drawable.qatar),
        Respuesta("17", R.drawable.qatar),
        Respuesta("18", R.drawable.qatar),
    ) , 2),
    Pregunta("¿Cuántos estadios habrán en el Mundial de Qatar?", listOf(
        Respuesta("8", R.drawable.estadio),
        Respuesta("9", R.drawable.estadio),
        Respuesta("10", R.drawable.estadio),
    ) , 0),
    Pregunta("¿Cuál es la selección con mas copas mundiales ganadas?", listOf(
        Respuesta("Brasil", R.drawable.copa_mundial),
        Respuesta("Alemania", R.drawable.copa_mundial),
        Respuesta("Argentina", R.drawable.copa_mundial),
    ) , 0),
    Pregunta("¿Quién es el jugador latino que ha anotado más goles en la historia de los mundiales?", listOf(
        Respuesta("Gabriel Batistuta", R.drawable.ronaldo_nazario),
        Respuesta("Ronaldo Nazario", R.drawable.ronaldo_nazario),
        Respuesta("Diego Maradona", R.drawable.ronaldo_nazario),
    ) , 1),
    Pregunta("¿Cómo se llamó la mascota de la Copa Mundial de 1982 en España?", listOf(
        Respuesta("Juanito", R.drawable.naranjito),
        Respuesta("Fuleco", R.drawable.naranjito),
        Respuesta("Naranjito", R.drawable.naranjito),
    ) , 2),
    Pregunta("Contra que equipo Diego Maradona hizo el gol con “la mano de Dios”", listOf(
        Respuesta("Italia", R.drawable.maradona),
        Respuesta("Alemania", R.drawable.maradona),
        Respuesta("Inglaterra", R.drawable.maradona),
    ) , 2),
    Pregunta("El goleador del primer Mundial de fútbol, disputado en 1930 en Uruguay, fue…", listOf(
        Respuesta("Bigode", R.drawable.guillermo_stabile),
        Respuesta("Alcides Ghiggia", R.drawable.guillermo_stabile),
        Respuesta("Guillermo Stábile", R.drawable.guillermo_stabile),
    ) , 2),
    Pregunta("¿Cómo se le llamó a la famosa victoria de Uruguay sobre Brasil en la final de 1950?", listOf(
        Respuesta("El triunfo del siglo", R.drawable.maracanazo),
        Respuesta("El Maracanazo", R.drawable.maracanazo),
        Respuesta("El Matabrasileirao", R.drawable.maracanazo),
    ) , 1),
    Pregunta("¿Sabes qué país tenía que organizar el Mundial de 1986 y tuvo que renunciar?", listOf(
        Respuesta("Argentina", R.drawable.colombia),
        Respuesta("Colombia", R.drawable.colombia),
        Respuesta("Uruguay", R.drawable.colombia),
    ) , 1),
    Pregunta("¿Cuál es el jugador latino que, junto al alemán Lothar Matthaeus, tiene más participaciones en el mundial?", listOf(
        Respuesta("Pelé (Brasil)", R.drawable.antonio_carbajal),
        Respuesta("Javier Zanetti (Argentina)", R.drawable.antonio_carbajal),
        Respuesta("Antonio Carbajal (México)", R.drawable.antonio_carbajal),
    ) , 2),
    Pregunta("¿Dónde se disputó la Copa Mundial de 1998?", listOf(
        Respuesta("Italia", R.drawable.francia),
        Respuesta("Francia", R.drawable.francia),
        Respuesta("Estados Unidos", R.drawable.francia),
    ) , 1),
    Pregunta("La selección de fútbol de Cuba participó en una Copa del Mundo. ¿Sabes en qué año fue?", listOf(
        Respuesta("Suiza 1954", R.drawable.francia),
        Respuesta("Francia 1938", R.drawable.francia),
        Respuesta("México 1970", R.drawable.francia),
    ) , 1),
    Pregunta("¿Quién fue el jugador que marcó el gol que le dio el triunfo a España en la final de la Copa del Mundo del 2010 en Sudáfrica?", listOf(
        Respuesta("Andrés Iniesta", R.drawable.iniesta),
        Respuesta("David Villa", R.drawable.iniesta),
        Respuesta("Sergio Ramos", R.drawable.iniesta),
    ) , 0),
    Pregunta("¿Cuál es la selección que más finales de la Copa Mundial ha disputado?", listOf(
        Respuesta("Brasil", R.drawable.alemania),
        Respuesta("Alemania", R.drawable.alemania),
        Respuesta("Italia", R.drawable.alemania),
    ) , 1),
)

