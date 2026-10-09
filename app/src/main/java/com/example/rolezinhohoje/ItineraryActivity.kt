package com.example.rolezinhohoje

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rolezinhohoje.databinding.ActivityItineraryBinding

class ItineraryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityItineraryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItineraryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nomeGrupo = intent.getStringExtra("NOME_GRUPO") ?: "Grupo sem nome"
        val totalAprovados = intent.getIntExtra("TOTAL_APROVADOS", 0)
        val localEscolhido = intent.getStringExtra("LOCAL_ESCOLHIDO") ?: "Nenhum local"

        binding.tvNomeGrupo.text = "Grupo: $nomeGrupo"
        binding.tvTotalAprovados.text = "Total de locais aprovados: $totalAprovados"
        binding.tvLocalEscolhido.text = localEscolhido

        binding.btnVoltar.setOnClickListener {
            finish()
        }
    }
}