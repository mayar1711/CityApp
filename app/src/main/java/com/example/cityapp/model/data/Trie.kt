package com.example.cityapp.model.data

class TrieNode {
    val children = mutableMapOf<Char, TrieNode>()
    val cities = mutableListOf<City>()
}

class Trie {
    private val root = TrieNode()

    fun insert(city: City) {
        var node = root
        city.name.lowercase().forEach { char ->
            node = node.children.getOrPut(char) { TrieNode() }
            node.cities.add(city)
        }
    }

    fun search(prefix: String): List<City> {
        var node = root
        prefix.lowercase().forEach { char ->
            node = node.children[char] ?: return emptyList()
        }
        return node.cities.sortedBy { it.name }
    }
}
