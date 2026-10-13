package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Xếp loại học lực",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


fun getGradeRank(score: Double): String {
    return if (score < 0 || score > 10) {
        "Không hợp lệ"
    } else if (score >= 8.0) {
        "Giỏi"
    } else if (score >= 6.5) {
        "Khá"
    } else if (score >= 5.0) {
        "Trung bình"
    } else {
        "Yếu"
    }
}

fun getGradeColor(rank: String): Color {
    return if (rank == "Giỏi") {
        Color(0xFF2E7D32)
    } else if (rank == "Khá") {
        Color(0xFF1976D2)
    } else if (rank == "Trung bình") {
        Color(0xFFED6C02)
    } else if (rank == "Yếu") {
        Color(0xFFD32F2F)
    } else {
        Color(0xFF9E9E9E)
    }
}

fun getGradeBackgroundColor(rank: String): Color {
    return if (rank == "Giỏi") {
        Color(0xFFE8F5E9)
    } else if (rank == "Khá") {
        Color(0xFFE3F2FD)
    } else if (rank == "Trung bình") {
        Color(0xFFFFF3E0)
    } else if (rank == "Yếu") {
        Color(0xFFFFEBEE)
    } else {
        Color(0xFFF5F5F5)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    // KHAI BÁO DỮ LIỆU ĐẦU HÀM
    val mainScore = 8.5
    val mainRank = getGradeRank(mainScore)
    val extraScores = listOf(9.0, 7.0, 5.5, 3.0, 11.0)

    // TOÀN BỘ GIAO DIỆN NẰM TRONG 1 COLUMN LỚN
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF2F4F8))
    ) {
        // THANH TIÊU ĐỀ
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF1E3A6E))
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            Text(
                text = name,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // PHẦN NỘI DUNG CUỘN BÊN DƯỚI
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // KHỐI 1: THẺ ĐIỂM CHÍNH (8.5)
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Điểm trung bình", fontSize = 13.sp, color = Color.Gray)

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = "$mainScore", fontSize = 36.sp, fontWeight = FontWeight.Bold)

                    Spacer(modifier = Modifier.height(16.dp))

                    // Ô xếp loại Giỏi
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(getGradeBackgroundColor(mainRank), RoundedCornerShape(8.dp))
                            .padding(vertical = 12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = mainRank,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = getGradeColor(mainRank)
                        )
                    }
                }
            }

            // TIÊU ĐỀ PHỤ
            Text(
                text = "KIỂM TRA THÊM",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )

            // KHỐI 2: THẺ DANH SÁCH KIỂM TRA THÊM
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(8.dp)) {
                    // Vòng lặp for đơn giản
                    for (score in extraScores) {
                        val rank = getGradeRank(score)
                        val scoreText = if (score % 1.0 == 0.0) score.toInt().toString() else score.toString()

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Điểm $scoreText", fontSize = 14.sp)

                            Box(
                                modifier = Modifier
                                    .background(getGradeBackgroundColor(rank), RoundedCornerShape(6.dp))
                                    .padding(horizontal = 10.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = rank,
                                    fontSize = 12.sp,
                                    color = getGradeColor(rank)
                                )
                            }
                        }
                    }
                }
            }
        }
    } // Đóng ngoặc Column tổng
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Xếp loại học lực")
    }
}