package com.example.starwarsapp.data.repository

import com.example.starwarsapp.data.model.Person
import com.example.starwarsapp.data.network.SwapiService

// ── Repository ────────────────────────────────────────────────────────────────

class PeopleRepository(
    private val service: SwapiService = SwapiService.create()
) {
    /**
     * Fetches one page of people from SWAPI.
     * Wraps the call in a Result so callers don't need try/catch.
     */
    suspend fun getPeople(page: Int = 1): Result<List<Person>> {
        return try {
            val response = service.getPeople(page)
            Result.success(response.results)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
