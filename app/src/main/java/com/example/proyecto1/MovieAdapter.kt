package com.example.proyecto1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieAdapter(private var movies: List<Movie>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var fullMoviesList: List<Movie> = movies.toList() // Lista completa para filtrar

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageViewMovie)
        val titleTextView: TextView = view.findViewById(R.id.textViewTitle)
        val descriptionTextView: TextView = view.findViewById(R.id.textViewDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.titleTextView.text = movie.title
        holder.descriptionTextView.text = movie.description

        // Cargar imagen utilizando Glide
        Glide.with(holder.itemView.context)
            .load(movie.imageResId)
            .into(holder.imageView)
    }

    override fun getItemCount() = movies.size

    // Método para filtrar la lista de películas
    fun filter(query: String) {
        movies = if (query.isEmpty()) {
            fullMoviesList // Restaurar la lista completa si no hay búsqueda
        } else {
            val filteredList = fullMoviesList.filter {
                it.title.contains(query, ignoreCase = true) // Filtrar por título
            }
            if (filteredList.isEmpty()) {
                // Si no hay resultados, puedes manejar el feedback aquí si lo deseas
                // Por ejemplo, podrías usar un Toast en la actividad para informar al usuario
            }
            filteredList
        }
        notifyDataSetChanged() // Notificar cambios en la vista
    }
}
