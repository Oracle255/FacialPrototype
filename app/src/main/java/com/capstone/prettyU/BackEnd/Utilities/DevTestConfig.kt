package com.capstone.prettyU.BackEnd.Utilities

import com.thedeanda.lorem.Lorem
import com.thedeanda.lorem.LoremIpsum
import kotlin.random.Random

class DevTestConfig {
    companion object {
        private val lorem: Lorem = LoremIpsum.getInstance()

        const val testingMode = true // Intent ke activity_debug_landing
        const val debugMode = true // Test Display, logic, binding, ETC


        // STRING GENERATOR BWT TESTING

        fun loremParagraph(lengthMin: Int, lengthMax: Int): String {
            return lorem.getTitle(lengthMin, lengthMax)
        }

        fun loremWithLength(length: Int): String {
            return lorem.getWords(length)
        }

        fun sampahStringGenerator(length: Int): String {
            val randomSampah = arrayOf(
                " (Sampah) ",
                " (Ini sampah) ",
                " (Ini Juga Sampah) ",
                " (Masih Sampah) ",
                " (I̵n̵i̴ ̸S̴a̷m̵p̶a̵h̴ ̶T̷e̵r̷k̷u̶t̸u̶k̶) ",
                " (Ini tidak sampah) "
            )

            var result = ""
            var i = 0
            while (i < length) {
                val randomer = randomSampah[Random.nextInt(0, randomSampah.size)]
                result += randomer
                i++
            }
            return result
        }
    }
}