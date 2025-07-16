package com.example.swit.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.swit.ui.theme.SwitTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

//TODO "РАЗОБРАТЬ ЛОГИКУ "
@Composable
fun UsernameScreen(
    onUsernameSaved: () -> Unit // навигация после успешного сохранения
) {
    var name by remember { mutableStateOf("") }
    val context = LocalContext.current

    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Как тебя называть?",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 16.dp),
                    color = MaterialTheme.colorScheme.primary
                )

                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Имя") },
                    modifier = Modifier.fillMaxWidth()
                )

                Button(
                    onClick = {
                        if (name.isNotBlank()) {
                            val userId = FirebaseAuth.getInstance().currentUser?.uid
                            if (userId != null) {
                                val db = Firebase.firestore
                                val user = mapOf(
                                    "uid" to userId,
                                    "username" to name
                                )
                                db.collection("users").document(userId)
                                    .set(user)
                                    .addOnSuccessListener {
                                        Toast.makeText(context, "Имя сохранено!", Toast.LENGTH_SHORT).show()
                                        onUsernameSaved()
                                    }
                                    .addOnFailureListener {
                                        Toast.makeText(context, "Ошибка: ${it.message}", Toast.LENGTH_SHORT).show()
                                    }
                            } else {
                                Toast.makeText(context, "Пользователь не найден", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Toast.makeText(context, "Введите имя", Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    Text("Сохранить")
                }
            }
        }
    }
}


