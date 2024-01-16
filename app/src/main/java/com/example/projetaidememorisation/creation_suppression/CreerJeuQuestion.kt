package com.example.projetaidememorisation.creation_suppression

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.projetaidememorisation.AjouterNouveauSujetComposable
import com.example.projetaidememorisation.DownloadAndAddQuestionsScreen
import com.example.projetaidememorisation.GestionQuestionsScreen
import com.example.projetaidememorisation.MainActivityAideMemoire
import com.example.projetaidememorisation.buttonVersActivite
import com.example.projetaidememorisation.titreActivite

class CreerJeuQuestion : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EcranJeuQuestion()
        }
    }
}

@Composable
fun EcranJeuQuestion() {
    val context= LocalContext.current

    Column (
        //modifier = Modifier.fillMaxWidth(), // Cela étire la colonne pour occuper toute la largeur disponible
        //horizontalAlignment = Alignment.CenterHorizontally,
    ){
        titreActivite("CREER UN NOUVEAU SUJET OU IMPORTER SUJET DEPUIS INTERNET", Color(0xFFCC99CC))

        AjouterNouveauSujetComposable() // Creer un nouveau sujet

        Spacer(modifier = Modifier.height(30.dp))

        DownloadAndAddQuestionsScreen() //Importé sujet depuis internet

        buttonVersActivite(CreationSuppression::class.java, "Retour", context)
        buttonVersActivite(MainActivityAideMemoire::class.java, "Menu principal", context)
    }
}