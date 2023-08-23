package com.example.assesmenttest.problemsolve

import android.util.Log

fun countWords(input: String): List<String> {
    val separators = setOf(' ', '.', ',', ';', ':', '!', '?', '(', ')')

    val words = mutableListOf<String>()
    var word = ""
    for (char in input) {
        if (char in separators) {
            if (word.isNotEmpty()) {
                words.add(word.toLowerCase())
                word = ""
            }
        } else {
            word += char
        }
    }
    if (word.isNotEmpty()) {
        words.add(word.toLowerCase())
    }

    val uniqueWords = mutableListOf<String>()
    val wordCounts = mutableListOf<Int>()

    // Count occurrences of each word
    for (word in words) {
        val index = uniqueWords.indexOf(word)
        if (index == -1) {
            uniqueWords.add(word)
            wordCounts.add(1)
        } else {
            wordCounts[index]++
        }
    }

    // Combine words and counts into a list of strings
    val result = mutableListOf<String>()
    for (i in uniqueWords.indices) {
        result.add("${uniqueWords[i]}: ${wordCounts[i]}")
    }

    return result
}

fun callProblemSolveMethod() {
    val input = "The cat and the hat."
    val wordCounts = countWords(input)
    wordCounts.forEach {
        Log.e("CountWords",it)
        println(it)
    }
}
