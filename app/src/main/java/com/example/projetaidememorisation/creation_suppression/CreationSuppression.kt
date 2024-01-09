package com.example.projetaidememorisation.creation_suppression

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.projetaidememorisation.MainActivityAideMemoire
import com.example.projetaidememorisation.buttonVersActivite
import com.example.projetaidememorisation.titreActivite

class CreationSuppression : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EcranCreationSuppression()
        }
    }
}

@Composable
fun EcranCreationSuppression() {
    val context= LocalContext.current

    Column (
        //modifier = Modifier.fillMaxWidth(), // Cela étire la colonne pour occuper toute la largeur disponible
        //horizontalAlignment = Alignment.CenterHorizontally,
    ){
        titreActivite("Creations et Suppressions", Color(0xFFCC99CC))

        buttonVersActivite(CreerJeuQuestion::class.java, "Creer un nouveau Sujet", context)

        buttonVersActivite(AjouterQuestionDUJeu::class.java, "Ajouter une Question", context)

        buttonVersActivite(SupprimerJeuQuestion::class.java, "Supprimer Sujet ou Question d'un sujet", context)






        buttonVersActivite(MainActivityAideMemoire::class.java, "Retour", context)
        buttonVersActivite(MainActivityAideMemoire::class.java, "Menu principal", context)
    }

}