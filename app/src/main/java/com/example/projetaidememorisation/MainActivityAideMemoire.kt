package com.example.projetaidememorisation

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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import com.example.projetaidememorisation.aide_memoire.AideMemoire
import com.example.projetaidememorisation.creation_suppression.CreationSuppression
import com.example.projetaidememorisation.parametrage.Parametrage

class MainActivityAideMemoire : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MonEcran()
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
        Text(text = titre, style = TextStyle(
            fontSize = 20.sp, // Taille de police de 20sp (changement ici)
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        )
    }
}

/*@Composable
fun QuitAppButton(c: Context,saisie: String) {
    val activity = LocalContext.current
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.width(5.dp))
        Button(onClick = {
            val a= c as Activity
            val intent = Intent()
            //val intent = Intent(this, MainActivityAideMemoire::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            //intent.putExtra("nom", saisie) //ajouter des données à l'Intent
            a.setResult(Activity.RESULT_OK, intent) // enregistrer le résultat de l'activité
            a.finish() //terminer l'activité fille
        }) {
            Text("Quitter l'application")
        }
    }
}*/

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