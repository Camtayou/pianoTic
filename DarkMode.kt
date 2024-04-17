package com.example.pianotiles

object DarkMode {
    var isDarkMode = false
        private set

    private val observers = mutableListOf<DarkModeObserver>()

    fun toggle() {
        isDarkMode = !isDarkMode // Toggle dark mode
        notifyObservers() // Notify observers about the change
    }

    fun addObserver(observer: DarkModeObserver) {
        observers.add(observer)
    }

    fun removeObserver(observer: DarkModeObserver) {
        observers.remove(observer)
    }

    private fun notifyObservers() {
        observers.forEach { it.onDarkModeChanged(isDarkMode) }
    }
}

interface DarkModeObserver {
    fun onDarkModeChanged(isDarkMode: Boolean)
}
