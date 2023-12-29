package com.example.projetaidememorisation

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projetaidememorisation.aide_memoire.AideMemoire
import com.example.projetaidememorisation.creation_suppression.CreationSuppression
import com.example.projetaidememorisation.creation_suppression.sujets_importation.PJson
import com.example.projetaidememorisation.creation_suppression.sujets_importation.Sujet
import com.example.projetaidememorisation.data.SujetsQuestions
import com.example.projetaidememorisation.parametrage.Parametrage
//import androidx.compose.runtime.flow.collectAsState
import com.google.gson.Gson
import kotlinx.coroutines.launch

class MainActivityAideMemoire : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            //InterfaceSuppressionSujetsOuQuestions()
            //AjouterNouveauSujetComposable()

            //MonEcran()
        }
    }
}

//OK
@Composable
fun InterfaceSuppressionSujetsOuQuestions() {
    val context = LocalContext.current
    val viewModel: ViewModelBDD = viewModel(
        factory = ViewModelBDDFactory(context.applicationContext as JeuQuestionnaireApplication)
    )

    val sujets by viewModel.sujets.observeAsState(listOf())
    val questionsParSujet by viewModel.questionsParSujet.observeAsState(mapOf())
    val expandedState = rememberSaveable { mutableStateOf(mutableMapOf<Int, Boolean>()) }

    LazyColumn {
        items(sujets) { sujet ->
            val isExpanded = expandedState.value[sujet.sujetId] ?: false

            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = sujet.nomSujet,
                        modifier = Modifier.weight(1f)
                    )
                    IconButton(
                        onClick = {
                            expandedState.value = expandedState.value.toMutableMap().apply {
                                this[sujet.sujetId] = !isExpanded
                            }
                        }
                    ) {
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "Déplier/Réduire")
                    }
                    Spacer(Modifier.width(8.dp))
                    Button(onClick = { viewModel.supprimerSujetParNom(sujet.nomSujet) }) {
                        Text("Supprimer Sujet")
                    }
                }
                if (isExpanded) {
                    questionsParSujet[sujet.sujetId]?.forEach { question ->
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(start = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = question.texteQuestion,
                                modifier = Modifier.weight(1f)
                            )
                            Spacer(Modifier.width(8.dp))
                            Button(onClick = { viewModel.supprimerQuestionParTexteEtSujet(question.texteQuestion, sujet.nomSujet) }) {
                                Text("Supprimer Question")
                            }
                        }
                    }
                }
            }
        }
    }
}



//Ok
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AjouterNouveauSujetComposable() {
    // Contexte local et ViewModel
    val context = LocalContext.current
    val viewModel: ViewModelBDD = viewModel(
        factory = ViewModelBDDFactory(context.applicationContext as JeuQuestionnaireApplication)
    )
    var nomSujet by rememberSaveable { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        // Champ de texte pour le nom du sujet
        OutlinedTextField(
            value = nomSujet,
            onValueChange = { nomSujet = it },
            label = { Text("Nom du Sujet") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        // Bouton pour ajouter un sujet
        Button(
            onClick = {
                if (nomSujet.isNotBlank()) {
                    viewModel.ajouterNouveauSujet(nomSujet)
                    nomSujet = "" // Réinitialiser le champ après l'ajout
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = nomSujet.isNotBlank()
        ) {
            Text("Ajouter Sujet")
        }
    }
}

@Composable
fun titreActivite(titre : String, couleur : Color) {
    Row(
        modifier = Modifier
            .background(couleur)
            .fillMaxWidth()
            .height(50.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically

    ) {
        Text(text = titre,
            style = TextStyle(
            fontSize = 20.sp, // Taille de police de 20sp (changement ici)
            fontWeight = FontWeight.Bold,
            color = Color.White
            )
        )
    }
}


@Composable
fun buttonVersActivite(activityClass: Class<*>, text : String, context : Context) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.width(5.dp))
        Button(onClick = {
            val lien = Intent(context,  activityClass)
            context.startActivity(lien)
        }) { Text(text = text) }
    }
}




//@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MonEcran() {
    val context= LocalContext.current

    Column (
        //modifier = Modifier.fillMaxWidth(), // Cela étire la colonne pour occuper toute la largeur disponible
        //horizontalAlignment = Alignment.CenterHorizontally,
    ){
        val modif = Modifier.width(5.dp)
        val arrangement = Arrangement.Center
        titreActivite("PAGE D'ACCEUIL JEU QUESTIONNAIRE", Color(0xFFCC99CC))


        // Première ligne de boutons
        buttonVersActivite(AideMemoire::class.java, "Aide Memoire", context)
        buttonVersActivite(CreationSuppression::class.java, "Creation/Supression jeu de donnée", context)
        buttonVersActivite(Parametrage::class.java, "Parametrage jeu", context)
    }
}