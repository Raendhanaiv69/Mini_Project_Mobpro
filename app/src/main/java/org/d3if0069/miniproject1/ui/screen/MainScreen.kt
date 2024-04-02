package org.d3if0069.miniproject1.ui.screen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if0069.miniproject1.R
import org.d3if0069.miniproject1.model.gambar
import org.d3if0069.miniproject1.navigation.Screen
import org.d3if0069.miniproject1.ui.theme.MiniProject1Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(0xFFFF8080),
                    titleContentColor = Color(0xFFFFFFFF),
                ),
                actions = {
                    IconButton(onClick = {navController.navigate(Screen.About.route)}) {
                        Icon(
                            imageVector =Icons.Outlined.Info,
                            contentDescription = stringResource(R.string.About_aplikasi),
                            tint = Color(0xFFFFFFFF),
                        )
                    }
                }
            )
        }
    ) { padding ->
        ScreenContent(Modifier.padding(padding))
    }
}

@SuppressLint("StringFormatInvalid")
@Composable
fun ScreenContent(modifier: Modifier) {
    var amount by rememberSaveable { mutableStateOf("") }
    var amountError by rememberSaveable { mutableStateOf(false) }

    var amountInDollar by rememberSaveable { mutableStateOf("") }
    var amountInDollarError by rememberSaveable { mutableStateOf(false) }

    val currencyOptions = listOf("Baht", "Won")
    var selectedCurrency by rememberSaveable { mutableStateOf(currencyOptions[0]) }

    var conversionResult by rememberSaveable { mutableStateOf(0f) }

    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.converter_intro),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text(text = stringResource(R.string.amount_rupiah)) },
            trailingIcon = { IconPicker(amountError) },
            supportingText = { ErrorHint(amountError) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = amountInDollar,
            onValueChange = {
                amountInDollar = it
                amountInDollarError = false
            },
            label = { Text(text = stringResource(R.string.amount_dolar)) },
            trailingIcon = {
                if (amountInDollarError) Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
            },
            isError = amountInDollarError,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            modifier = Modifier.padding(top = 6.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            currencyOptions.forEach { currency ->
                Row(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(selected = selectedCurrency == currency, onClick = { selectedCurrency = currency },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Color(0xFFFF8080),
                    )
                    )
                    Text(text = currency, style = MaterialTheme.typography.bodyLarge)

                }
            }
        }
        Button(
            onClick = {
                val actualAmount = if (amountInDollar.isNotEmpty()) amountInDollar.toFloatOrNull() else amount.toFloatOrNull()
                val inDollar = amountInDollar.isNotEmpty()

                if (actualAmount == null) {
                    if (inDollar) amountInDollarError = true else amountError = true
                    return@Button
                }
                conversionResult = convertCurrency(actualAmount, selectedCurrency, inDollar)
            },

            modifier = Modifier.padding(top = 8.dp),
            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFF8080),
            )

        ) {
            Text(text = stringResource(id = (R.string.Convert)))


        }
        if (conversionResult != 0f) {
            Divider(modifier = Modifier.padding(vertical = 8.dp), thickness = 1.dp)
            Text(
                text = "Result: $conversionResult $selectedCurrency",
                style = MaterialTheme.typography.titleLarge
            )
        }
        Button(
            onClick = {
                      shareData(
                          context = context,
                          message = context.getString(R.string.share_template,
                              amount, amountInDollar,conversionResult,selectedCurrency)
                      )
            },
            modifier = modifier.padding(top = 8.dp),
            contentPadding = PaddingValues(horizontal=32.dp, vertical=16.dp),
                    colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF8080),
        )
        ) {
            Text(text = stringResource(id = (R.string.share)))

        }
    }
}

@Composable
fun IconPicker(isError: Boolean) {
    if (isError) {
        Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
    } else {
        // No icon for correct input
    }
}

@Composable
fun ErrorHint(isError: Boolean) {
    if (isError) {
        Text("Please enter a valid amount")
    }
}

private fun convertCurrency(amount: Float, currency: String, inDollar: Boolean): Float {
    if (inDollar) {
        // Asumsi nilai tukar dari Dolar ke Baht dan Dolar ke Won
        val conversionRateToBaht = 33f
        val conversionRateToWon = 1200f
        return when (currency) {
            "Baht" -> amount * conversionRateToBaht
            "Won" -> amount * conversionRateToWon
            else -> 0f
        }
    } else {
        // Nilai tukar dari Rupiah ke Baht dan Rupiah ke Won
        val conversionRateToBaht = 0.0023f
        val conversionRateToWon = 0.085f
        return when (currency) {
            "Baht" -> amount * conversionRateToBaht
            "Won" -> amount * conversionRateToWon
            else -> 0f
        }
    }
}

private fun shareData(context: Context, message: String){
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
    }
    if (shareIntent.resolveActivity(context.packageManager) != null) {
        context.startActivity(shareIntent)
    }
}

private val data = getData()
private fun getData(): List<gambar> {
    return listOf(
        gambar("drawable/akuu", R.drawable.akuu),
    )
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ScreenPreview() {
    MiniProject1Theme {
        MainScreen(rememberNavController())
    }
}
