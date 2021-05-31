package com.example.bin2dec

import android.content.ClipData
import android.content.ClipDescription.MIMETYPE_TEXT_PLAIN
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bin2dec.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    fun buttonInput(view: View) {
        binding.binary.append((view as Button).text)
    }

    fun clear(view: View) {
        var length = binding.binary.getText().length
        var text = binding.binary.getText().toString()
        if (length > 0) {
            binding.binary.setText(text.substring(0, length - 1));
            binding.binary.setSelection(binding.binary.length())
        }
    }

    fun convert(view: View) {

        var binary: String? = binding.binary.text.toString()
        if (binary == "") {
            binary = "0"
        } else {
            var value = false
            binary?.forEach {
                value = it.isLetter()
            }
        }
        var decimal = binding.decimal

        var convert = Integer.parseInt(binary, 2)
        decimal.setText(convert.toString())
    }

    fun clearAll(view: View) {
        binding.binary.setText("")
    }

    fun clipboard(view: View) {
        val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        var text = clipboardManager.primaryClip.toString()

        var paste = clipboardManager.primaryClip?.getItemAt(0)?.text.toString()
        binding.binary.setText(paste)
        binding.binary.setSelection(binding.binary.length())

    }

    fun copy(view: View) {
        var textField = binding.decimal.getText().toString()
        val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("label", textField)
        clipboardManager.setPrimaryClip(clip)
        Toast.makeText(getApplicationContext(), "Número: $textField copiado para o clipboard",
            Toast.LENGTH_SHORT).show()
    }
}