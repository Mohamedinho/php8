package com.example.webservicephp8

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.example.webservicephp8.beans.Etudiant
import com.example.webservicephp8.network.ApiConfig
import com.example.webservicephp8.network.VolleySingleton
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListEtudiantScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var etudiants by remember { mutableStateOf(listOf<Etudiant>()) }
    var showDeleteDialog by remember { mutableStateOf<Etudiant?>(null) }
    var showEditDialog by remember { mutableStateOf<Etudiant?>(null) }
    
    fun loadEtudiants() {
        val request = StringRequest(Request.Method.GET, ApiConfig.URL_LOAD,
            { response ->
                val type = object : TypeToken<List<Etudiant>>() {}.type
                etudiants = Gson().fromJson(response, type)
            },
            { error -> Log.e("VOLLEY", "Erreur : " + error.message) }
        )
        VolleySingleton.getInstance(context).addToRequestQueue(request)
    }

    fun deleteEtudiant(id: Int) {
        val request = object : StringRequest(Method.POST, ApiConfig.URL_DELETE,
            { response ->
                Toast.makeText(context, "Supprimé !", Toast.LENGTH_SHORT).show()
                loadEtudiants()
            },
            { error -> Toast.makeText(context, "Erreur suppression", Toast.LENGTH_SHORT).show() }
        ) {
            override fun getParams(): Map<String, String> = mapOf("id" to id.toString())
        }
        VolleySingleton.getInstance(context).addToRequestQueue(request)
    }

    fun updateEtudiant(etudiant: Etudiant) {
        val request = object : StringRequest(Method.POST, ApiConfig.URL_UPDATE,
            { response ->
                Toast.makeText(context, "Modifié !", Toast.LENGTH_SHORT).show()
                loadEtudiants()
            },
            { error -> Toast.makeText(context, "Erreur modification", Toast.LENGTH_SHORT).show() }
        ) {
            override fun getParams(): Map<String, String> = mapOf(
                "id" to etudiant.id.toString(),
                "nom" to etudiant.nom,
                "prenom" to etudiant.prenom,
                "ville" to etudiant.ville,
                "sexe" to etudiant.sexe
            )
        }
        VolleySingleton.getInstance(context).addToRequestQueue(request)
    }

    LaunchedEffect(Unit) {
        loadEtudiants()
    }

    // Dialogue de Suppression
    showDeleteDialog?.let { etudiant ->
        AlertDialog(
            onDismissRequest = { showDeleteDialog = null },
            title = { Text("Confirmer la suppression") },
            text = { Text("Voulez-vous vraiment supprimer ${etudiant.nom} ${etudiant.prenom} ?") },
            confirmButton = {
                TextButton(onClick = {
                    deleteEtudiant(etudiant.id)
                    showDeleteDialog = null
                }) { Text("SUPPRIMER", color = MaterialTheme.colorScheme.error) }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = null }) { Text("ANNULER") }
            }
        )
    }

    // Dialogue de Modification (Simplifié)
    showEditDialog?.let { etudiant ->
        var nom by remember { mutableStateOf(etudiant.nom) }
        var prenom by remember { mutableStateOf(etudiant.prenom) }
        
        AlertDialog(
            onDismissRequest = { showEditDialog = null },
            title = { Text("Modifier l'étudiant") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    TextField(value = nom, onValueChange = { nom = it }, label = { Text("Nom") })
                    TextField(value = prenom, onValueChange = { prenom = it }, label = { Text("Prénom") })
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    updateEtudiant(etudiant.copy(nom = nom, prenom = prenom))
                    showEditDialog = null
                }) { Text("MODIFIER") }
            },
            dismissButton = {
                TextButton(onClick = { showEditDialog = null }) { Text("ANNULER") }
            }
        )
    }

    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Liste des Étudiants", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(etudiants) { etudiant ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp).fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(text = "${etudiant.nom} ${etudiant.prenom}", style = MaterialTheme.typography.titleMedium)
                            Text(text = "${etudiant.ville} - ${etudiant.sexe}", style = MaterialTheme.typography.bodySmall)
                        }
                        Row {
                            IconButton(onClick = { showEditDialog = etudiant }) {
                                Icon(Icons.Default.Edit, contentDescription = "Modifier")
                            }
                            IconButton(onClick = { showDeleteDialog = etudiant }) {
                                Icon(Icons.Default.Delete, contentDescription = "Supprimer")
                            }
                        }
                    }
                }
            }
        }
        
        Button(
            onClick = { loadEtudiants() },
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 16.dp)
        ) {
            Text("Actualiser")
        }
    }
}