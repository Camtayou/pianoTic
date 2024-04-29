package com.example.pianotiles

// Définition d'un objet singleton DarkMode
object DarkMode {
    // Propriété pour stocker l'état du mode sombre. Par défaut, il est désactivé (false).
    // Le setter est privé pour empêcher la modification directe de l'extérieur de cet objet.
    var isDarkMode = false
        private set

    // Liste des observateurs qui sont notifiés lorsque l'état du mode sombre change.
    private val observers = mutableListOf<DarkModeObserver>()

    // Méthode pour basculer l'état du mode sombre.
    fun toggle() {
        isDarkMode = !isDarkMode // Inverse l'état actuel du mode sombre.
        notifyObservers() // Notifie tous les observateurs du changement.
    }

    // Méthode privée pour notifier tous les observateurs du changement d'état du mode sombre.
    private fun notifyObservers() {
        // Parcourt tous les observateurs et appelle leur méthode onDarkModeChanged avec le nouvel état.
        observers.forEach { it.onDarkModeChanged(isDarkMode) }
    }
}

// Interface pour les observateurs du mode sombre.
interface DarkModeObserver {
    // Méthode appelée lorsque l'état du mode sombre change.
    fun onDarkModeChanged(isDarkMode: Boolean)
}

