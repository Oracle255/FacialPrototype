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


    }
}