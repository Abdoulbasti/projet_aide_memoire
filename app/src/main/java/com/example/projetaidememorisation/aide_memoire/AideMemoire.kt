package com.example.projetaidememorisation.aide_memoire

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

class AideMemoire : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EcranAideMemoire()
        }
    }
}

@Composable
fun EcranAideMemoire() {

    val context= LocalContext.current

    Column (
        //modifier = Modifier.fillMaxWidth(), // Cela étire la colonne pour occuper toute la largeur disponible
        //horizontalAlignment = Alignment.CenterHorizontally,
    ){
        titreActivite("Aide Memoire", Color(0xFFCC99CC))

        buttonVersActivite(ChoixJeuQuestion::class.java, "Choix Jeu de Questions et repondre au question", context)
        buttonVersActivite(ConsulterChangerStatus::class.java, "Consulter/Changer Statut", context)


        buttonVersActivite(MainActivityAideMemoire::class.java, "Retour", context)
        buttonVersActivite(MainActivityAideMemoire::class.java, "Menu principal", context)
    }

}