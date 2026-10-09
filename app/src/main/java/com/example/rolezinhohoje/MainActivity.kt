package com.example.rolezinhohoje

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.example.rolezinhohoje.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var indiceAtual = 0
    private var dX = 0f
    private var initialX = 0f

    private val listaDeRoles = listOf(
        RoleItem(1, "Toca do Urso (Colorado)", "Cervejaria & Bar", 4.8, "Chopp artesanal direto da fonte, ambiente aberto e porções variadas.", "Rod. Anhanguera, km 308"),
        RoleItem(2, "Vila Dionísio", "Pub & Rock", 4.7, "Música ao vivo, coxinhas tradicionais e grande variedade de cervejas.", "Rua Sete de Setembro, 2023"),
        RoleItem(3, "Choperia Pinguim", "Tradicional", 4.5, "O chopp mais famoso da cidade no centro histórico.", "Rua General Osório, 389"),
        RoleItem(4, "Outback RibeirãoShopping", "Restaurante", 4.6, "Ribs com molho barbecue e chopp na caneca trincando.", "Av. Col. Fernando Ferreira Leite, 1540"),
        RoleItem(5, "Milwaukee American Bar", "Bar & Shows", 4.6, "Estilo americano com hambúrgueres artesanais e rock ao vivo.", "Av. Francisco Junqueira, 2843")
    )

    private val rolesAprovados = mutableListOf<RoleItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        atualizarCard()
        configurarGestoTinder()

        binding.btnRecusar.setOnClickListener {
            executarAcaoDescarte()
        }

        binding.btnBora.setOnClickListener {
            executarAcaoAceite()
        }

        binding.btnVerItinerario.setOnClickListener {
            abrirItinerario()
        }
    }

    private fun atualizarCard() {
        binding.cardRole.translationX = 0f
        binding.cardRole.rotation = 0f
        binding.cardRole.alpha = 1f

        if (indiceAtual < listaDeRoles.size) {
            val item = listaDeRoles[indiceAtual]
            binding.tvNomeLocal.text = item.nome
            binding.tvCategoria.text = "${item.categoria} • Nota ${item.avaliacao}"
            binding.tvDescricao.text = item.descricao ?: "Sem descrição disponível."
            binding.tvEndereco.text = item.endereco ?: "Endereço não informado."
            binding.btnBora.isEnabled = true
            binding.btnRecusar.isEnabled = true
        } else {
            binding.tvNomeLocal.text = "Fim das opções"
            binding.tvCategoria.text = ""
            binding.tvDescricao.text = "Todas as sugestões de Ribeirão Preto foram avaliadas pelo grupo."
            binding.tvEndereco.text = ""
            binding.btnBora.isEnabled = false
            binding.btnRecusar.isEnabled = false
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun configurarGestoTinder() {
        binding.cardRole.setOnTouchListener { view, event ->
            if (indiceAtual >= listaDeRoles.size) return@setOnTouchListener false

            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    dX = view.x - event.rawX
                    initialX = view.x
                    true
                }
                MotionEvent.ACTION_MOVE -> {
                    val newX = event.rawX + dX
                    val deltaX = newX - initialX
                    view.translationX = deltaX
                    view.rotation = deltaX * 0.05f
                    true
                }
                MotionEvent.ACTION_UP -> {
                    val deltaX = view.translationX
                    if (deltaX > 180) {
                        executarAcaoAceite()
                    } else if (deltaX < -180) {
                        executarAcaoDescarte()
                    } else {
                        view.animate().translationX(0f).rotation(0f).setDuration(200).start()
                    }
                    true
                }
                else -> false
            }
        }
    }

    private fun executarAcaoAceite() {
        rolesAprovados.add(listaDeRoles[indiceAtual])
        binding.cardRole.animate()
            .translationX(1400f)
            .rotation(35f)
            .alpha(0f)
            .setDuration(250)
            .withEndAction {
                indiceAtual++
                atualizarCard()
            }
            .start()
    }

    private fun executarAcaoDescarte() {
        binding.cardRole.animate()
            .translationX(-1400f)
            .rotation(-35f)
            .alpha(0f)
            .setDuration(250)
            .withEndAction {
                indiceAtual++
                atualizarCard()
            }
            .start()
    }

    private fun abrirItinerario() {
        val intent = Intent(this, ItineraryActivity::class.java).apply {
            putExtra("NOME_GRUPO", "Galera de Ribeirão")
            putExtra("TOTAL_APROVADOS", rolesAprovados.size)
            val localPrincipal = rolesAprovados.firstOrNull()?.nome ?: "Nenhum escolhido"
            putExtra("LOCAL_ESCOLHIDO", localPrincipal)
        }
        startActivity(intent)
    }
}