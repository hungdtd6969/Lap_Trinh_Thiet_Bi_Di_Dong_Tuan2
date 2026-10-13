package com.example.forifnull

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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.forifnull.ui.theme.ForIfNullTheme
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ForIfNullTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ClassGradeScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

data class Student(
    val name: String,
    val score: Double?
)

fun getGradeRank(score: Double?): String {
    if (score == null) return "Chưa có điểm"
    return when {
        score < 0 || score > 10 -> "Không hợp lệ"
        score >= 8.0 -> "Giỏi"
        score >= 6.5 -> "Khá"
        score >= 5.0 -> "Trung bình"
        else -> "Yếu"
    }
}

fun getGradeColor(rank: String): Color {
    return when (rank) {
        "Giỏi" -> Color(0xFF2E7D32)
        "Khá" -> Color(0xFF1976D2)
        "Trung bình" -> Color(0xFFED6C02)
        "Yếu" -> Color(0xFFD32F2F)
        else -> Color(0xFF9E9E9E)
    }
}

@Composable
fun ClassGradeScreen(modifier: Modifier = Modifier) {
    val students = listOf(
        Student("Nguyễn Văn An", 9.0),
        Student("Trần Thị Bình", 7.2),
        Student("Lê Minh Chi", null),
        Student("Phạm Quốc Dũng", 4.5),
        Student("Võ Thanh Em", 5.8)
    )

    var totalScore = 0.0
    var validCount = 0
    var excellentCount = 0
    var nullCount = 0
    var maxScore = 0.0

    for (student in students) {
        if (student.score == null) {
            nullCount++
        } else {
            totalScore += student.score
            validCount++

            if (student.score >= 8.0) {
                excellentCount++
            }

            if (student.score > maxScore) {
                maxScore = student.score
            }
        }
    }

    val averageScore = if (validCount > 0) totalScore / validCount else 0.0

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF2F4F8))
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF1E3A6E))
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            Text(
                text = "Bảng điểm lớp",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        //nd
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // THẺ THỐNG KÊ
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF1E3A6E))
                    .padding(16.dp)
            ) {
                Column {
                    Text("Điểm trung bình lớp", color = Color.White.copy(alpha = 0.7f), fontSize = 13.sp)

                    Spacer(modifier = Modifier.height(4.dp))

                    // In điểm trung bình làm tròn 2 chữ số thập phân (6.63)
                    Text(
                        text = String.format(Locale.US, "%.2f", averageScore),
                        color = Color.White,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Giỏi", color = Color.White.copy(alpha = 0.7f), fontSize = 12.sp)
                            Text("$excellentCount", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        }
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Chưa có điểm", color = Color.White.copy(alpha = 0.7f), fontSize = 12.sp)
                            Text("$nullCount", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        }
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Cao nhất", color = Color.White.copy(alpha = 0.7f), fontSize = 12.sp)
                            Text(
                                text = if (validCount > 0) String.format(Locale.US, "%.1f", maxScore) else "—",
                                color = Color.White,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            } // Đóng Box Thẻ Thống Kê

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "DANH SÁCH · ${students.size} SINH VIÊN",
                color = Color.Gray,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            // DANH SÁCH SINH VIÊN (dùng for)
            for (student in students) {
                val rank = getGradeRank(student.score)

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.White)
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(student.name, fontSize = 16.sp, fontWeight = FontWeight.Medium)
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = rank,
                                color = getGradeColor(rank),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Text(
                            text = student.score?.let {
                                if (it % 1.0 == 0.0) it.toInt().toString() else it.toString()
                            } ?: "—",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ClassGradeScreenPreview() {
    ForIfNullTheme { ClassGradeScreen() }
}