package com.example.webservicephp8.network

object ApiConfig {
    private const val BASE_URL = "http://10.0.2.2/projet/ws"

    const val URL_ADD = "$BASE_URL/createEtudiant.php"
    const val URL_LOAD = "$BASE_URL/loadEtudiant.php"
    const val URL_DELETE = "$BASE_URL/deleteEtudiant.php"
    const val URL_UPDATE = "$BASE_URL/updateEtudiant.php"
}