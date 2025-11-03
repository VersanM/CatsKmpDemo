package com.example.catskmpdemo.feature.dashboard.data.dto

import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class BreedDtoParsingTest {
    @Test
    fun parseSampleCatArray_firstBreedFields() {
        val json = """[ { "breeds": [ { "weight": { "imperial": "6 - 11", "metric": "3 - 5" }, "id": "bomb", "name": "Bombay", "temperament": "Affectionate, Dependent, Gentle, Intelligent, Playful", "origin": "United States", "description": "Some desc", "life_span": "12 - 16" } ], "id": "BkksyH95Z", "url": "https://cdn2.thecatapi.com/images/BkksyH95Z.jpg", "width": 1920, "height": 1080 } ]"""
        val parsed = Json { ignoreUnknownKeys = true }.decodeFromString(ListSerializer(CatDto.serializer()), json)
        assertEquals(1, parsed.size)
        val cat = parsed.first()
        assertEquals("BkksyH95Z", cat.id)
        assertTrue(cat.breeds.isNotEmpty())
        val breed = cat.breeds.first()
        assertEquals("bomb", breed.id)
        assertEquals("Bombay", breed.name)
        assertEquals("6 - 11", breed.weight?.imperial)
        assertEquals("3 - 5", breed.weight?.metric)
        assertEquals("12 - 16", breed.lifeSpan)
        assertNotNull(breed.temperament)
        assertNotNull(breed.origin)
    }
}

