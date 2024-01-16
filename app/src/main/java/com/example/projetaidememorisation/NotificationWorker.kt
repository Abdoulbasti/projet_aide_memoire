package com.example.projetaidememorisation

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationManagerCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.core.app.NotificationCompat
import com.example.projetaidememorisation.aide_memoire.ChoixJeuQuestion


class NotificationWorker(appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {

    companion object {
        const val CHANNEL_ID = "channel_rappel"
    }

    override suspend fun doWork(): Result {
        val status = inputData.getInt("STATUS", 1)

        // Créer un Intent pour ouvrir ChoixJeuQuestion à partir de la notification
        val intent = Intent(applicationContext, ChoixJeuQuestion::class.java)
        val pendingIntent = PendingIntent.getActivity(
            applicationContext,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Construire et envoyer la notification
        val notificationManager = NotificationManagerCompat.from(applicationContext)
        val notification = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setSmallIcon(R.drawable.un_icone) // Assurez-vous que cette icône existe dans vos ressources
            .setContentTitle("Rappel apprentissage")
            .setContentText("Rappel pour les notifications ayant le statut $status")
            .setContentIntent(pendingIntent) // Définir le PendingIntent
            .setAutoCancel(true) // Fermer la notification après un clic
            .build()

        try {
            notificationManager.notify(status, notification)
        } catch (e: SecurityException) {
            // Gérer l'exception
        }

        return Result.success()
    }
}