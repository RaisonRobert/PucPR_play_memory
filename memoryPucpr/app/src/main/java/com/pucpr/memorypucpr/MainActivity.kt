package com.pucpr.memorypucpr

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupLayout()
    }
    private fun setupLayout() {
      findViewById<Button>(R.id.btn_play).setOnClickListener {
          PlayGameMemoryActivity.startActivity(this)
      }
        updatePoint()
    }
    fun updatePoint(){
        findViewById<TextView>(R.id.txt_point).text = "Seus Pontos: $pontos"
    }

    override fun onResume() {
        super.onResume()
        updatePoint()
    }
    companion object {
        var pontos: Int = 0

    }
}