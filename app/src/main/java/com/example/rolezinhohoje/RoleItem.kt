package com.example.rolezinhohoje

import java.io.Serializable

data class RoleItem(
    val id: Int,
    val nome: String,
    val categoria: String,
    val avaliacao: Double,
    val descricao: String?,
    val endereco: String? = null
) : Serializable