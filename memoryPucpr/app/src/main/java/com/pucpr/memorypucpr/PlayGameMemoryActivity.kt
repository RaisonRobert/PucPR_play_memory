package com.pucpr.memorypucpr

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pucpr.memorypucpr.MainActivity.Companion.pontos


class PlayGameMemoryActivity : AppCompatActivity() {
    private var cards = listOf(
        R.drawable.ic_card_cat,
        R.drawable.ic_card_dog,
        R.drawable.ic_card_parrot,
        R.drawable.ic_card_cat,
        R.drawable.ic_card_dog,
        R.drawable.ic_card_parrot
    ).shuffled().toMutableList()
    private var cardsSecond = mutableListOf<Int>()
    private lateinit var adapterAnimals:ListAnimalsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_game_memory)
        adapterAnimals = ListAnimalsAdapter(cards, cardsSecond, ::onSuccess, ::onFail)
        setupRecyclerView()
        val toolbar = findViewById<Toolbar>(R.id.toolbar_play)
        this.setSupportActionBar(toolbar)
        toolbar.title = "ok"
        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun setupRecyclerView() {
        val rvAnimals = findViewById<RecyclerView>(R.id.rv_animals)
        rvAnimals.layoutManager = GridLayoutManager(this, 2)
        rvAnimals.adapter = adapterAnimals
    }
    private fun onSuccess(firstIdCard: Int){
        cardsSecond.add(firstIdCard)
        if (cardsSecond.size == 3){
            dialog()

        }
        Toast.makeText(this, "Parabéns! Você acertou!", Toast.LENGTH_SHORT).show()
        pontos += 3
    }

    private fun dialog() {
        val builder: AlertDialog.Builder = this.let {
            AlertDialog.Builder(it)
        }
        builder.apply {
            setMessage("Você fez $pontos pontos")?.setTitle("Sua pontuação")
            setPositiveButton("Ok") { _, _ ->
                finish()
            }
        }
        builder.create().show()
    }

    private fun onFail(firstIdCard: Int){
        cardsSecond.removeAll(setOf(firstIdCard))
        adapterAnimals = ListAnimalsAdapter(cards,cardsSecond, ::onSuccess, ::onFail)
        setupRecyclerView()
        pontos -= 1
        Toast.makeText(this, "Você errou!", Toast.LENGTH_SHORT).show()
    }
    companion object {
        fun startActivity(context: Context?) {
            context?.let {
                val intent = Intent(
                    context,
                    PlayGameMemoryActivity::class.java
                )
                it.startActivity(intent)
            }
        }
    }
}

