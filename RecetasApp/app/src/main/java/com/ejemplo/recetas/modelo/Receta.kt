package com.ejemplo.recetas.modelo

/**
 * Modelo de datos que representa una Receta.
 * Unidad 3 — Data Classes
 */
data class Receta(
    val id: Int,
    val nombre: String,
    val descripcionCorta: String,
    val descripcionLarga: String,
    val tiempoPreparacion: String,
    val porciones: Int,
    val dificultad: String,
    val categoria: String,
    val emoji: String,
    val ingredientes: List<String>,
    val pasos: List<String>,
    val colorFondo: Long          // Color en formato 0xFFRRGGBB
)

/**
 * Repositorio de recetas de ejemplo.
 * Unidad 3 — Fuente de datos estática para LazyColumn / LazyVerticalGrid
 */
object RepositorioRecetas {

    val listaDeRecetas: List<Receta> = listOf(
        Receta(
            id = 1,
            nombre = "Tacos de Carnitas",
            descripcionCorta = "Deliciosos tacos mexicanos con carne de cerdo confitada.",
            descripcionLarga = "Los tacos de carnitas son un ícono de la cocina mexicana. " +
                    "La carne de cerdo se cocina lentamente en su propia grasa hasta obtener " +
                    "una textura jugosa por dentro y crujiente por fuera. " +
                    "Se sirven en tortillas de maíz con cebolla, cilantro y salsa verde.",
            tiempoPreparacion = "2 h 30 min",
            porciones = 6,
            dificultad = "Media",
            categoria = "Plato principal",
            emoji = "🌮",
            ingredientes = listOf(
                "1 kg de carne de cerdo (pierna o paleta)",
                "1 naranja (jugo)",
                "1 limón (jugo)",
                "4 dientes de ajo",
                "1 cebolla mediana",
                "2 cucharaditas de sal",
                "1 cucharadita de comino",
                "Tortillas de maíz al gusto",
                "Cebolla y cilantro para servir",
                "Salsa verde al gusto"
            ),
            pasos = listOf(
                "Cortar la carne de cerdo en trozos grandes de unos 5 cm.",
                "En una olla grande, colocar la carne con el ajo, la cebolla partida, sal y comino.",
                "Agregar el jugo de naranja y limón. Cubrir con agua.",
                "Cocinar a fuego medio-alto hasta que el agua se evapore (aprox. 1.5 h).",
                "Una vez evaporada el agua, dejar que la carne se fría en su propia grasa hasta dorar.",
                "Desmenuzar la carne con dos tenedores.",
                "Servir en tortillas calientes con cebolla, cilantro y salsa."
            ),
            colorFondo = 0xFFFF6B35
        ),
        Receta(
            id = 2,
            nombre = "Guacamole Clásico",
            descripcionCorta = "El auténtico guacamole mexicano fresco y cremoso.",
            descripcionLarga = "El guacamole es una de las salsas más famosas del mundo. " +
                    "Preparado con aguacates maduros, cilantro, cebolla, chile y jugo de limón, " +
                    "es perfecto como botana con totopos o como acompañamiento de cualquier platillo.",
            tiempoPreparacion = "15 min",
            porciones = 4,
            dificultad = "Fácil",
            categoria = "Botana",
            emoji = "🥑",
            ingredientes = listOf(
                "3 aguacates maduros",
                "1 tomate mediano picado",
                "1/2 cebolla blanca picada finamente",
                "1 chile serrano (opcional)",
                "Jugo de 2 limones",
                "Sal al gusto",
                "Cilantro fresco al gusto"
            ),
            pasos = listOf(
                "Partir los aguacates a la mitad y retirar el hueso.",
                "Extraer la pulpa con una cuchara y colocarla en un tazón.",
                "Machacar el aguacate con un tenedor hasta obtener la textura deseada.",
                "Agregar el jugo de limón inmediatamente para evitar la oxidación.",
                "Incorporar la cebolla, tomate, chile y cilantro.",
                "Sazonar con sal y mezclar bien.",
                "Servir de inmediato con totopos."
            ),
            colorFondo = 0xFF4CAF50
        ),
        Receta(
            id = 3,
            nombre = "Pozole Rojo",
            descripcionCorta = "Caldo tradicional con maíz cacahuazintle y chile guajillo.",
            descripcionLarga = "El pozole es uno de los platillos más representativos de México. " +
                    "Este caldo espeso elaborado con maíz cacahuazintle, carne de cerdo y una " +
                    "deliciosa salsa de chiles secos es perfecto para reuniones familiares. " +
                    "Se acompaña con tostadas, lechuga, rábanos y orégano.",
            tiempoPreparacion = "3 h",
            porciones = 8,
            dificultad = "Alta",
            categoria = "Sopa",
            emoji = "🍲",
            ingredientes = listOf(
                "800 g de maíz cacahuazintle precocido",
                "600 g de carne de cerdo (cabeza o maciza)",
                "5 chiles guajillo secos",
                "3 chiles ancho secos",
                "4 dientes de ajo",
                "1/2 cebolla",
                "2 cucharaditas de orégano seco",
                "Sal al gusto",
                "Lechuga, rábanos, tostadas para acompañar"
            ),
            pasos = listOf(
                "Cocer la carne de cerdo con ajo, cebolla y sal por 1.5 horas.",
                "Desvenar y remojar los chiles en agua caliente por 20 minutos.",
                "Licuar los chiles con ajo, cebolla y un poco del caldo de cocción.",
                "Colar la salsa y freírla en un poco de aceite.",
                "Agregar la salsa al caldo de carne junto con el maíz.",
                "Cocinar a fuego medio por 30 minutos más.",
                "Servir caliente con los acompañamientos."
            ),
            colorFondo = 0xFFC62828
        ),
        Receta(
            id = 4,
            nombre = "Chiles en Nogada",
            descripcionCorta = "El platillo patriótico de México con los colores de la bandera.",
            descripcionLarga = "Los chiles en nogada son considerados el platillo más representativo " +
                    "de la gastronomía mexicana. Chiles poblanos rellenos de picadillo de carne " +
                    "y frutas, cubiertos con nogada de nuez de castilla, adornados con granada " +
                    "y perejil. Simbolizan los colores de la bandera mexicana.",
            tiempoPreparacion = "4 h",
            porciones = 6,
            dificultad = "Alta",
            categoria = "Plato principal",
            emoji = "🫑",
            ingredientes = listOf(
                "6 chiles poblanos grandes",
                "300 g de carne de res molida",
                "300 g de carne de cerdo molida",
                "2 duraznos picados",
                "2 peras picadas",
                "1 manzana picada",
                "50 g de pasas",
                "50 g de piñones",
                "200 g de nuez de castilla",
                "200 ml de crema",
                "100 g de queso de cabra",
                "Granada y perejil para decorar"
            ),
            pasos = listOf(
                "Asar los chiles directamente en el fuego hasta que la piel se queme.",
                "Envolver en bolsa plástica y dejar sudar 15 minutos. Pelar y desvenar.",
                "Preparar el picadillo sofriendo la carne con todas las frutas y especias.",
                "Rellenar los chiles con el picadillo.",
                "Para la nogada: licuar la nuez con crema y queso de cabra hasta obtener salsa.",
                "Servir los chiles cubiertos con nogada, granada y perejil."
            ),
            colorFondo = 0xFF1A237E
        ),
        Receta(
            id = 5,
            nombre = "Tamales de Rajas",
            descripcionCorta = "Esponjosos tamales de maíz con rajas de chile poblano.",
            descripcionLarga = "Los tamales son uno de los alimentos más ancestrales de México. " +
                    "Esta versión con rajas de chile poblano y queso oaxaca es un favorito " +
                    "vegetariano. La masa de maíz esponjosa envuelta en hoja de maíz crea " +
                    "una experiencia culinaria única e inigualable.",
            tiempoPreparacion = "2 h",
            porciones = 20,
            dificultad = "Media",
            categoria = "Antojo",
            emoji = "🫔",
            ingredientes = listOf(
                "500 g de masa para tamales",
                "200 g de manteca de cerdo",
                "1 taza de caldo de pollo",
                "1 cucharadita de sal",
                "1 cucharadita de polvo para hornear",
                "4 chiles poblanos asados y en rajas",
                "200 g de queso oaxaca deshebrado",
                "Hojas de maíz remojadas"
            ),
            pasos = listOf(
                "Batir la manteca hasta que esté muy esponjosa (aprox. 10 min).",
                "Agregar la masa poco a poco alternando con el caldo.",
                "Incorporar sal y polvo para hornear. Batir hasta que la masa flote en agua.",
                "Extender masa en hojas de maíz. Colocar rajas y queso al centro.",
                "Doblar y cerrar los tamales.",
                "Cocer en vaporera por 1 hora hasta que la masa se desprenda de la hoja."
            ),
            colorFondo = 0xFF6D4C41
        ),
        Receta(
            id = 6,
            nombre = "Churros con Chocolate",
            descripcionCorta = "Crujientes churros bañados en chocolate caliente.",
            descripcionLarga = "Los churros son el postre mexicano por excelencia para acompañar " +
                    "con una taza de chocolate caliente. La masa frita espolvorada con azúcar " +
                    "y canela crea una combinación irresistible. Perfectos para el desayuno " +
                    "o como merienda en cualquier momento del día.",
            tiempoPreparacion = "45 min",
            porciones = 4,
            dificultad = "Fácil",
            categoria = "Postre",
            emoji = "🍫",
            ingredientes = listOf(
                "1 taza de agua",
                "1 taza de harina",
                "1 cucharadita de sal",
                "Aceite para freír",
                "Azúcar y canela para espolvorear",
                "200 g de chocolate de mesa",
                "1 taza de leche"
            ),
            pasos = listOf(
                "Hervir el agua con la sal. Retirar del fuego y agregar la harina de golpe.",
                "Revolver vigorosamente hasta obtener una masa sin grumos.",
                "Colocar la masa en una manga pastelera con duya de estrella.",
                "Calentar abundante aceite a 180°C.",
                "Freír los churros hasta dorar (3-4 min por lado).",
                "Escurrir y espolvorear con azúcar y canela.",
                "Preparar el chocolate caliente y servir de acompañamiento."
            ),
            colorFondo = 0xFF795548
        )
    )
}
