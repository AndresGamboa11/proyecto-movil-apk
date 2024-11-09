package com.example.proyecto1

import android.app.AlertDialog
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeActivity : AppCompatActivity() {

    private lateinit var recyclerViewMovies: RecyclerView
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var imageViewSearch: ImageView
    private lateinit var textViewTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        recyclerViewMovies = findViewById(R.id.recyclerViewMovies)
        imageViewSearch = findViewById(R.id.imageViewSearch)
        textViewTitle = findViewById(R.id.textViewTitle) // Referencia al TextView del título

        // Cambiar a GridLayoutManager para mostrar 3 columnas
        recyclerViewMovies.layoutManager = GridLayoutManager(this, 3)

        // Crear una lista de películas de ejemplo
        val movies = listOf(
            Movie("Inception", "Ciencia ficción sobre sueños.", R.drawable.inception_ver12_xlg),
            Movie("The Matrix", "Película sobre realidades virtuales.", R.drawable.descarga),
            Movie("Interstellar", "Exploración del espacio y el tiempo.", R.drawable.images),
            Movie("The Dark Knight", "Película de superhéroes.", R.drawable.dark_knight),
            Movie("Dragon Ball Super: Super Hero", "Película super héroe.", R.drawable.dbshero),
            Movie("Fight Club", "Un club de lucha subterráneo.", R.drawable.fight_club),
            Movie("Dune", "Adaptación de la novela de ciencia ficción.", R.drawable.dune),
            Movie("Spider-Man: No Way Home", "Aventura de superhéroes con múltiples Spider-Men.", R.drawable.spider_man),
            Movie("Black Panther: Wakanda Forever", "Secuela de la película de Black Panther.", R.drawable.black_panther),
            Movie("Top Gun: Maverick", "Secuela del clásico de aviación.", R.drawable.top_gun),
            // Películas bélicas
            Movie("Saving Private Ryan", "Un grupo de soldados busca a un paracaidista en la Segunda Guerra Mundial.", R.drawable.saving_private_ryan),
            Movie("Full Metal Jacket", "Un retrato brutal de la guerra de Vietnam.", R.drawable.full_metal_jacket),
            Movie("1917", "Dos soldados en una misión urgente durante la Primera Guerra Mundial.", R.drawable._1917),
            Movie("Dunkirk", "Evacuación de soldados en la Segunda Guerra Mundial.", R.drawable.dunkirk),
            Movie("Black Hawk Down", "Misión fallida de las fuerzas especiales en Somalia.", R.drawable.black_hawk_down),
            // Películas de acción
            Movie("Mad Max: Fury Road", "Una lucha por la supervivencia en un mundo postapocalíptico.", R.drawable.mad_max),
            Movie("John Wick", "Un exasesino busca venganza por el asesinato de su perro.", R.drawable.john_wick),
            Movie("Die Hard", "Un policía enfrenta a terroristas en un rascacielos.", R.drawable.die_hard),
            Movie("The Avengers", "Superhéroes se unen para salvar al mundo.", R.drawable.the_avengers),
            Movie("Gladiator", "Un general romano se convierte en gladiador y busca venganza.", R.drawable.gladiator)
        )

        movieAdapter = MovieAdapter(movies)
        recyclerViewMovies.adapter = movieAdapter

        // Configurar el clic en la lupa
        imageViewSearch.setOnClickListener {
            showSearchDialog()
        }

        // Configurar el clic en el título
        textViewTitle.setOnClickListener {
            // Al hacer clic en el título, reiniciar la actividad
            recreate() // Esto refrescará la vista
        }
    }

    private fun showSearchDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Buscar Película")

        // Configurar el campo de entrada
        val input = EditText(this)
        builder.setView(input)

        builder.setPositiveButton("Buscar") { dialog, _ ->
            val query = input.text.toString()
            movieAdapter.filter(query) // Filtrar las películas según la consulta

            // Comprobar si hay resultados
            if (movieAdapter.itemCount == 0) {
                Toast.makeText(this, "No se encontraron resultados para \"$query\"", Toast.LENGTH_SHORT).show()
            }

            dialog.dismiss()
        }

        builder.setNegativeButton("Cancelar") { dialog, _ -> dialog.cancel() }
        builder.show()
    }
}
