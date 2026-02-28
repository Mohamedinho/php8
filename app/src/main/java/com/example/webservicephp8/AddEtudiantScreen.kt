package com.example.webservicephp8

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.example.webservicephp8.network.ApiConfig
import com.example.webservicephp8.network.VolleySingleton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEtudiantScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var nom by remember { mutableStateOf("") }
    var prenom by remember { mutableStateOf("") }
    var ville by remember { mutableStateOf("Marrakech") }
    var sexe by remember { mutableStateOf("homme") }
    val villes = listOf("Marrakech", "Rabat", "Casablanca", "Agadir", "Tanger")
    
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(text = "Nom :", color = Color(0xFF388E3C), fontSize = 17.sp)
        OutlinedTextField(
            value = nom,
            onValueChange = { nom = it },
            modifier = Modifier.fillMaxWidth()
        )

        Text(text = "Prénom :", color = Color(0xFF388E3C), fontSize = 17.sp)
        OutlinedTextField(
            value = prenom,
            onValueChange = { prenom = it },
            modifier = Modifier.fillMaxWidth()
        )

        Text(text = "Ville :", color = Color(0xFF388E3C), fontSize = 17.sp)
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = ville,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor().fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                villes.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            ville = item
                            expanded = false
                        }
                    )
                }
            }
        }

        Text(text = "Sexe :", color = Color(0xFF388E3C), fontSize = 17.sp)
        Row(Modifier.selectableGroup()) {
            Row(
                Modifier
                    .selectable(
                        selected = (sexe == "homme"),
                        onClick = { sexe = "homme" },
                        role = Role.RadioButton
                    )
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(selected = (sexe == "homme"), onClick = null)
                Text("Homme", modifier = Modifier.padding(start = 8.dp))
            }
            Row(
                Modifier
                    .selectable(
                        selected = (sexe == "femme"),
                        onClick = { sexe = "femme" },
                        role = Role.RadioButton
                    )
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(selected = (sexe == "femme"), onClick = null)
                Text("Femme", modifier = Modifier.padding(start = 8.dp))
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                if (nom.isEmpty() || prenom.isEmpty()) {
                    Toast.makeText(context, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                val request = object : StringRequest(
                    Request.Method.POST, ApiConfig.URL_ADD,
                    { response ->
                        Log.d("RESPONSE", response)
                        Toast.makeText(context, "Étudiant ajouté !", Toast.LENGTH_SHORT).show()
                        // On vide les champs
                        nom = ""
                        prenom = ""
                    },
                    { error -> 
                        Log.e("VOLLEY", "Erreur : " + error.message)
                        Toast.makeText(context, "Erreur de connexion au serveur", Toast.LENGTH_LONG).show()
                    }
                ) {
                    override fun getParams(): Map<String, String> {
                        val params = HashMap<String, String>()
                        params["nom"] = nom
                        params["prenom"] = prenom
                        params["ville"] = ville
                        params["sexe"] = sexe
                        return params
                    }
                }
                VolleySingleton.getInstance(context).addToRequestQueue(request)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("ADD")
        }
    }
}