package com.example.numberguessinggame

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.numberguessinggame.ui.theme.NumberGuessingGameTheme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NumberGuessingGameTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    GuessingGameLayout()
                }
            }
        }
    }
}


@Composable
fun GuessingGameLayout(modifier: Modifier = Modifier){
    val AnswerNumber = (1..100).random()
    var gameHintResult by remember { mutableStateOf("None") }

    var amountInput by remember { mutableStateOf("") }

    val amount = amountInput.toDoubleOrNull() ?: 0.0
    Column(
        modifier = Modifier.padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.game_intro2),
            style = MaterialTheme.typography.displaySmall
        )
        Spacer(modifier = Modifier.height(75.dp))

        GuessNumberField(
            value = amountInput,
            onValueChange = { amountInput = it },
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(75.dp))
//        Text(
//            text = stringResource(R.string.guess_number , amount ),
//            style = MaterialTheme.typography.displaySmall
//        )
        Text(
            text = stringResource(R.string.game_hint , gameHintResult),
            style = MaterialTheme.typography.displaySmall
        )
        Button(onClick = {
            gameHintResult = checkGuessNumber(amount, AnswerNumber)
        })
        {
            Text(stringResource(R.string.play_again))
        }
    }
}

private fun checkGuessNumber(guess: Double, answer: Int): String {
    return when {
        guess == answer.toDouble() -> "Correct!"
        guess > answer.toDouble() -> "Higher"
        else -> "Lower"
    }
}

@Preview
@Composable
fun GuessingGameThemeApp() {
    GuessingGameLayout(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuessNumberField(value : String ,
                     onValueChange: (String) -> Unit,
                     modifier: Modifier = Modifier){
    var amountInput by remember { mutableStateOf("Your Guess") }
    val amount = amountInput.toDoubleOrNull() ?: 0.0
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
    )
}

