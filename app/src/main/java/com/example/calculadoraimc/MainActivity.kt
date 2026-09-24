package com.example.calculadoraimc

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculadoraimc.ui.theme.CalculadoraIMCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculadoraIMCTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    IMCScreen(modifier = Modifier.padding(innerPadding),)

                }
            }
        }
    }
}

@Composable
fun IMCScreen(modifier: Modifier = Modifier) {

    var altura by remember {
        mutableStateOf("")
    }
    var peso by remember {
        mutableStateOf("")
    }
    var imc by remember {
        mutableStateOf(0.0)
    }
    var categoriaImc by remember {
        mutableStateOf("")
    }

    Column(
        modifier = modifier.fillMaxSize(),
    ) {

        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {

                // -- header --

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .background(color = colorResource(id = R.color.cor_app)),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(
                            id = R.drawable.bmi
                        ),
                        contentDescription = "Logo App",
                        modifier = Modifier.size(80.dp)
                            .padding(vertical = 16.dp)
                    )

                    Text(
                        text = "Calculadora IMC",
                        fontSize = 24.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }

                // -- formulário --

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp)
                ) {
                    //card inicial

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(350.dp)
                            .offset(y = (-30).dp),

                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFF9F6F6)
                        ),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        //Criando um column para poder unir e centralizar todos os componentes
                        Column(
                            modifier = Modifier
                                //faz a Column ocupar todo o espaço do Card.
                            .fillMaxSize()
                            //cria um espaço interno entre o conteúdo e as bordas do Card.
                            .padding(30.dp),
                            //centraliza na horizontal. Só funciona com componentes que nao
                            //contém o .fillMaxWidth
                            horizontalAlignment = Alignment.CenterHorizontally,
                            //dISTRIBUI O ESPACO
                            verticalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Text(
                                text = "Seus dados",
                                fontSize = 24.sp,
                                color = colorResource(id = R.color.cor_app),
                                fontWeight = FontWeight.Bold,
                            )

                            // input de altura
                            OutlinedTextField(
                                value = altura,
                                onValueChange = {altura = it},
                                singleLine = true,
                                //serve para ocupar o espaço todo da largura
                                modifier = Modifier.fillMaxWidth(),
                                label = {
                                    Text( text = "Altura")
                                },
                                placeholder = {
                                    Text( text = "Altura")
                                },
                                shape = RoundedCornerShape(16.dp),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = colorResource(id = R.color.cor_app),
                                    unfocusedBorderColor = colorResource(id = R.color.cor_app)
                                )
                            )

                            //input de peso
                            OutlinedTextField(
                                value = peso,
                                onValueChange = {peso = it},
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth(),
                                label = {
                                    Text( text = "Peso")
                                },
                                placeholder = {
                                    Text( text = "Peso")
                                },
                                shape = RoundedCornerShape(16.dp),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = colorResource(id = R.color.cor_app),
                                    unfocusedBorderColor = colorResource(id = R.color.cor_app)
                                )
                            )

                                //botao calcular
                                 Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceAround,

                                ) {
                                    //botao de calcular
                                    Button(
                                        onClick = {
                                            imc = calcularIMC(
                                                altura = altura.toDouble(),
                                                peso = peso.toDouble()
                                            )

                                            categoriaImc = determinarCategoriaIMC(imc)
                                        },
                                        colors = buttonColors(
                                            containerColor = colorResource(id = R.color.cor_app),
                                            contentColor = Color.White
                                        ),
                                        modifier = Modifier.width(200.dp)
                                    ) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {

                                            Text(
                                                text = "CALCULAR",
                                                fontSize = 16.sp
                                            )
                                        }
                                    }

                                    //botao de apagar
                                    Button(
                                        onClick = {

                                        },
                                        colors = buttonColors(
                                            containerColor = Color.Red,
                                            contentColor = Color.White
                                        ),
                                    ) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Icon(
                                                imageVector = Icons.Default.Delete,
                                                contentDescription = "Deletar"
                                            )
                                        }
                                    }
                                }

                        }

                    }

                    //card que informa o resultado
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),

                        colors = CardDefaults.cardColors(
                            containerColor = colorResource(id = R.color.cor_app_verde)
                        ),
                        elevation = CardDefaults.cardElevation(4.dp),
                    ) {
                        Column(
                            modifier = Modifier
                                //Faz com que ocupe o card todo
                                .fillMaxSize()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = String.format("%.1f", imc),
                                fontSize = 24.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = categoriaImc,
                                fontSize = 24.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}