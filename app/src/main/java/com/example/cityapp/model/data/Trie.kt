package com.example.cityapp.model.data

class Trie<T> {
    private val root = TrieNode<T>()

    fun insert(key: String, value: T) {
        var node = root
        for (char in key) {
            node = node.children.computeIfAbsent(char) { TrieNode() }
        }
        node.value = value
    }

    fun search(prefix: String): List<T> {
        var node = root
        for (char in prefix) {
            node = node.children[char] ?: return emptyList()
        }
        return collectAllValues(node)
    }

    private fun collectAllValues(node: TrieNode<T>): List<T> {
        val results = mutableListOf<T>()
        node.value?.let { results.add(it) }
        for (child in node.children.values) {
            results.addAll(collectAllValues(child))
        }
        return results
    }

    private class TrieNode<T> {
        val children: MutableMap<Char, TrieNode<T>> = mutableMapOf()
        var value: T? = null
    }
}
