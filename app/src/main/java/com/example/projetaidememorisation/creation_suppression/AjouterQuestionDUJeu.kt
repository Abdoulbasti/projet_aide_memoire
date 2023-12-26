package com.example.projetaidememorisation.creation_suppression

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.projetaidememorisation.MainActivityAideMemoire
import com.example.projetaidememorisation.aide_memoire.AideMemoire
import com.example.projetaidememorisation.buttonVersActivite
import com.example.projetaidememorisation.titreActivite
import com.example.projetaidememorisation.ui.theme.ProjetAideMemorisationTheme

class AjouterQuestionDUJeu : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EcranAjouterQuestionAuJeu()
        }
    }
}

@Composable
fun EcranAjouterQuestionAuJeu() {
    val context= LocalContext.current

    Column (
        //modifier = Modifier.fillMaxWidth(), // Cela étire la colonne pour occuper toute la largeur disponible
        //horizontalAlignment = Alignment.CenterHorizontally,
    ){
        titreActivite("Ajouter un jeu de quesion", Color(0xFFCC99CC))


        buttonVersActivite(CreationSuppression::class.java, "Retour", context)
        buttonVersActivite(MainActivityAideMemoire::class.java, "Menu principal", context)
    }
}