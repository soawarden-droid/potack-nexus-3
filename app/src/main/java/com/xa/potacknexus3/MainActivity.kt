package com.xa.potacknexus3

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bg = Color.rgb(10, 14, 23)
        val accent = Color.rgb(74, 229, 221)
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(28, 40, 28, 28)
            setBackgroundColor(bg)
        }
        fun label(value: String, size: Float, color: Int): TextView =
            TextView(this).apply { text = value; textSize = size; setTextColor(color); setPadding(0, 8, 0, 14) }
        root.addView(label("POTACK NEXUS 3.0", 28f, accent))
        root.addView(label("Created by xa  •  ALPHA 1", 15f, Color.LTGRAY))
        root.addView(label("Offline command laboratory. No Telegram account is connected; commands only show previews.", 16f, Color.WHITE))
        val entry = EditText(this).apply {
            hint = "Try .help or clean 50"
            setSingleLine(true)
            setTextColor(Color.WHITE)
            setHintTextColor(Color.GRAY)
        }
        root.addView(entry)
        val result = label("Ready. Enter a command.", 17f, Color.WHITE)
        val run = Button(this).apply {
            text = "PREVIEW COMMAND"
            setOnClickListener {
                val parsed = CommandParser.parse(entry.text.toString())
                result.text = "${parsed.title}\n\n${parsed.detail}"
            }
        }
        root.addView(run)
        root.addView(result)
        val scroll = ScrollView(this).apply { addView(root) }
        setContentView(scroll)
    }
}
