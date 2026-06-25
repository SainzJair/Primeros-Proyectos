package com.example.gamecatalog.data

import com.example.gamecatalog.R

data class Game(
    val id: Int,
    val title: String,
    val platform: String,
    val imageRes: Int,
    val emoji: String,
    val rating: String,
    val description: String
)

val gameList = listOf(
    Game(1, "Elden Ring", "PS5 · PC · Xbox", R.drawable.ic_launcher_foreground, "🌌",
        "9.8", "Un RPG de mundo abierto desarrollado por FromSoftware. Explora Las Tierras Intermedias."),
    Game(2, "God of War: Ragnarök", "PS5 · PS4", R.drawable.ic_launcher_foreground, "🔥",
        "9.5", "Kratos y Atreus se embarcan en un peligroso viaje para descubrir la verdad sobre la profecía."),
    Game(3, "The Legend of Zelda: TotK", "Nintendo Switch", R.drawable.ic_launcher_foreground, "🌿",
        "9.6", "Link explora Hyrule y los cielos en la secuela de Breath of the Wild."),
    Game(4, "Marvel's Spider-Man 2", "PS5", R.drawable.ic_launcher_foreground, "🕷️",
        "9.2", "Peter Parker y Miles Morales se enfrentan al simbionte Venom en Nueva York."),
    Game(5, "Cyberpunk 2077: Phantom Liberty", "PC · PS5 · Xbox", R.drawable.ic_launcher_foreground, "🤖",
        "9.0", "Expansión épica de Cyberpunk 2077. V acepta una misión peligrosa en Dogtown."),
    Game(6, "Baldur's Gate 3", "PC · PS5", R.drawable.ic_launcher_foreground, "🧙",
        "9.7", "RPG basado en D&D con decisiones que cambian el mundo. Larian Studios en su mejor momento."),
    Game(7, "The Last of Us Part I", "PS5 · PC", R.drawable.ic_launcher_foreground, "🌲",
        "9.3", "Remake completo del clásico de Naughty Dog. Joel y Ellie en un mundo post-apocalíptico."),
    Game(8, "Hogwarts Legacy", "PS5 · PC · Xbox · Switch", R.drawable.ic_launcher_foreground, "⚡",
        "8.8", "Explora Hogwarts en el siglo XIX. Aprende magia y descubre secretos ancestrales."),
    Game(9, "Final Fantasy XVI", "PS5 · PC", R.drawable.ic_launcher_foreground, "⚔️",
        "9.0", "Una historia épica de cristales, Dominantes y Eikons. El RPG de acción más oscuro de la saga."),
    Game(10, "Starfield", "PC · Xbox", R.drawable.ic_launcher_foreground, "🚀",
        "8.5", "Bethesda te lleva al espacio. Explora más de 1000 planetas en la Colonia Espacial.")
)