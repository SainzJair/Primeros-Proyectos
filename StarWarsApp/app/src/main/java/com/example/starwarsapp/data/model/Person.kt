package com.example.starwarsapp.data.model

import com.google.gson.annotations.SerializedName

// ── SWAPI People ──────────────────────────────────────────────────────────────

data class PeopleResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Person>
)

data class Person(
    val name: String,
    val height: String,
    val mass: String,
    val hair_color: String,
    val skin_color: String,
    val eye_color: String,
    val birth_year: String,
    val gender: String,
    val homeworld: String,
    val films: List<String>,
    val species: List<String>,
    val vehicles: List<String>,
    val starships: List<String>,
    val url: String
) {
    // Extract numeric ID from SWAPI URL → used to build avatar image URL
    val id: Int
        get() = url.trimEnd('/').substringAfterLast('/').toIntOrNull() ?: 1

    // Public avatar from a free placeholder service keyed to character id
    val avatarUrl: String
        get() = "https://picsum.photos/seed/sw$id/200/200"

    // Friendly gender label
    val genderLabel: String
        get() = when (gender.lowercase()) {
            "male"   -> "♂ Masculino"
            "female" -> "♀ Femenino"
            "n/a"    -> "N/A"
            else     -> gender.replaceFirstChar { it.uppercase() }
        }
}
