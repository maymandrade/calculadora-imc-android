package com.example.calculadoraimc

//Modularizar o exercicio, colocar o calculo para acontecer fora do onClick, para que o codigo fique mais
//organizado e reutilizavel


import kotlin.math.pow

fun calcularIMC(altura: Double, peso: Double): Double{
    var imc: Double = 0.0

    imc = peso / (altura / 100).pow(2.0)

    return imc
}

fun determinarCategoriaIMC(imc: Double): String {

    var categoria: String = ""

    if (imc < 18.5) {
        categoria = "Abaixo do peso"
    } else if (imc >= 18.5 && imc < 25.0) {
        categoria = "Peso ideal"
    } else if (imc >= 25.0 && imc < 30.0) {
        categoria = "Levemente acima do peso"
    } else if (imc >= 30.0 && imc < 35.0) {
        categoria = "Obesidade grau I"
    } else if (imc >= 35.0 && imc < 40.0) {
        categoria = "Obesidade grau II"
    } else {
        categoria = "Obesidade grau III"
    }

    return categoria
}
