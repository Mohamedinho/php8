package com.example.webservicephp8.beans

data class Etudiant(
    val id: Int = 0,
    val nom: String,
    val prenom: String,
    val ville: String,
    val sexe: String
) {
    override fun toString(): String {
        return "Etudiant(id=$id, nom='$nom', prenom='$prenom', ville='$ville', sexe='$sexe')"
    }
}