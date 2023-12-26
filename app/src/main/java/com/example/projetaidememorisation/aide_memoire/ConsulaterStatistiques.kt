package com.example.projetaidememorisation.aide_memoire

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
import com.example.projetaidememorisation.buttonVersActivite
import com.example.projetaidememorisation.titreActivite
import com.example.projetaidememorisation.ui.theme.ProjetAideMemorisationTheme

class ConsulaterStatistiques : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EcranConsulterStatistiques()
        }
    }
}

@Composable
fun EcranConsulterStatistiques() {
    val context= LocalContext.current

    Column (
        //modifier = Modifier.fillMaxWidth(), // Cela étire la colonne pour occuper toute la largeur disponible
        //horizontalAlignment = Alignment.CenterHorizontally,
    ){
        titreActivite("Consulter Statistiques", Color(0xFFCC99CC))


        buttonVersActivite(AideMemoire::class.java, "Retour", context)
        buttonVersActivite(MainActivityAideMemoire::class.java, "Menu principal", context)
    }
}