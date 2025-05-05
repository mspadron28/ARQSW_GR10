package com.example.conversionclient

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class NextActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)

        val buttonCentimetersToFeet = findViewById<Button>(R.id.buttonCentimetersToFeet)
        buttonCentimetersToFeet.setOnClickListener {
            val intent = Intent(this, CentimetersToFeet::class.java)
            startActivity(intent)
        }

        val buttonFeetToCentimeters = findViewById<Button>(R.id.buttonFeetToCentimeters)
        buttonFeetToCentimeters.setOnClickListener {
            val intent = Intent(this, FeetToCentimeters::class.java)
            startActivity(intent)
        }

        val buttonMetersToYards = findViewById<Button>(R.id.buttonMetersToYards)
        buttonMetersToYards.setOnClickListener {
            val intent = Intent(this, MetersToYards::class.java)
            startActivity(intent)
        }

        val buttonYardsToMeters = findViewById<Button>(R.id.buttonYardsToMeters)
        buttonYardsToMeters.setOnClickListener {
            val intent = Intent(this, YardsToMeters::class.java)
            startActivity(intent)
        }

        val buttonInchesToCentimeters = findViewById<Button>(R.id.buttonInchesToCentimeters)
        buttonInchesToCentimeters.setOnClickListener {
            val intent = Intent(this, InchesToCentimeters::class.java)
            startActivity(intent)
        }

        val buttonCentimetersToInches = findViewById<Button>(R.id.buttonCentimetersToInches)
        buttonCentimetersToInches.setOnClickListener {
            val intent = Intent(this, CentimetersToInches::class.java)
            startActivity(intent)
        }
    }
}
