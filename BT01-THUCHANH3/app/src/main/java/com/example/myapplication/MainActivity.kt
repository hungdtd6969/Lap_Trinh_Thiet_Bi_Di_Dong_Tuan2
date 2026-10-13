package com.example.myapplication


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Thực hành 03",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    var num1 by remember { mutableStateOf("") }
    var num2 by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }


    Column(modifier = modifier
        .fillMaxSize()
        .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = " $name!",
            style = MaterialTheme.typography.titleLarge
        )


        OutlinedTextField(
            value = num1,
            onValueChange = { newValue -> num1 = newValue
            },
            label = { Text("Số thứ nhất") },
            singleLine = true, //
            modifier = Modifier.fillMaxWidth()
        )


        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            // Nút Cộng
            Button(
                onClick = {
                    val n1 = num1.toDoubleOrNull() ?: 0.0
                    val n2 = num2.toDoubleOrNull() ?: 0.0
                    result = (n1 + n2).toString()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE53935)),
                modifier = Modifier.weight(1f)
            ) {
                Text("+")
            }

            // Nút Trừ
            Button(
                onClick = {
                    val n1 = num1.toDoubleOrNull() ?: 0.0
                    val n2 = num2.toDoubleOrNull() ?: 0.0
                    result = (n1 - n2).toString()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFB8C00)),
                modifier = Modifier.weight(1f)
            ) {
                Text("-")
            }

            // Nút Nhân
            Button(
                onClick = {
                    val n1 = num1.toDoubleOrNull() ?: 0.0
                    val n2 = num2.toDoubleOrNull() ?: 0.0
                    result = (n1 * n2).toString()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5E35B1)),
                modifier = Modifier.weight(1f)
            ) {
                Text("*")
            }

            // Nút Chia
            Button(
                onClick = {
                    val n1 = num1.toDoubleOrNull() ?: 0.0
                    val n2 = num2.toDoubleOrNull() ?: 0.0
                    result = if (n2 != 0.0) (n1 / n2).toString() else "Không thể chia cho 0"
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF212121)),
                modifier = Modifier.weight(1f)
            ) {
                Text("/")
            }
        }

        OutlinedTextField(
            value = num2,
            onValueChange = { num2 = it },
            label = { Text("Số thứ hai") },
            modifier = Modifier.fillMaxWidth()
        )

        // Hiển thị kết quả tính toán
        Text(
            text = "Kết quả: $result",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.align(Alignment.Start)
        )
    }
}




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}