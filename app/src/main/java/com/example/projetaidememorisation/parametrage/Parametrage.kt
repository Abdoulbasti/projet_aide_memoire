package com.example.projetaidememorisation.parametrage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.projetaidememorisation.MainActivityAideMemoire
import com.example.projetaidememorisation.aide_memoire.ChoixJeuQuestion
import com.example.projetaidememorisation.buttonVersActivite
import com.example.projetaidememorisation.titreActivite

class Parametrage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EcranParametrage()
        }
    }
}

@Composable
fun EcranParametrage() {
    val context= LocalContext.current

    Column (
        //modifier = Modifier.fillMaxWidth(), // Cela étire la colonne pour occuper toute la largeur disponible
        //horizontalAlignment = Alignment.CenterHorizontally,
    ){
        titreActivite("Parametrage", Color(0xFFCC99CC))

        buttonVersActivite(ChoixCouleurFond::class.java, "Choix Couleur de fond", context)
        buttonVersActivite(ChoixTaillePolice::class.java, "Choix taille de police", context)
        buttonVersActivite(ChoixTempsReponse::class.java, "Choix temps de reponse", context)

        buttonVersActivite(MainActivityAideMemoire::class.java, "Retour", context)
        buttonVersActivite(MainActivityAideMemoire::class.java, "Menu principal", context)
    }
}