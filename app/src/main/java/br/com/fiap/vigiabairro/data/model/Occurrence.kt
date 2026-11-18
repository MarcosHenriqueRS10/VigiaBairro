package br.com.fiap.vigiabairro.data.model

data class Occurrence(
    val id: Int,
    val title: String,
    val description: String,
    val location: String,
    val type: String,
    val date: String
)