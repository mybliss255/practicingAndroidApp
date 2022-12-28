package com.example.myboardgameapplication.ui.collection

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myboardgameapplication.R
import com.example.myboardgameapplication.data.model.BoardGame
import com.example.myboardgameapplication.ui.collection.ui.theme.MyBoardgameApplicationTheme

@Composable
fun BoardGame(boardGame: BoardGame) {
    Card(
        modifier = Modifier
            .fillMaxWidth().height(120.dp)
            .padding(10.dp).clickable{ },
        elevation = 10.dp
    ){
        Row(
            modifier = Modifier
            ,verticalAlignment = Alignment.CenterVertically
        ) {
            val imageModifier = Modifier
                .size(90.dp)
                .clip(RoundedCornerShape(40.dp))

            Image(
                painterResource(R.drawable.komaanddice),
                null,
                contentScale = ContentScale.Crop,
                modifier = imageModifier
            )

            Column {
                Text(text = boardGame.title,fontSize = 35.sp,)
                Text(
                    text = "プレイヤー数: ${boardGame.minPlayer}-${boardGame.maxPlayer}",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
                Text(text = "プレイ時間　: ${boardGame.playTime} ", fontSize = 16.sp, color = Color.Gray)
            }
        }
    }
}

@Composable
fun BoardGameList(boardgames: List<BoardGame>) {
    Column {
        boardgames.forEach { boardgame ->
            BoardGame(boardgame)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val sampleBoardGame = BoardGame("カタン", 2, 4, 60, 1)
    val sampleList = mutableListOf(sampleBoardGame, sampleBoardGame, sampleBoardGame)

    MyBoardgameApplicationTheme {

        Column {
            BoardGameList(boardgames = sampleList)
        }
    }
}