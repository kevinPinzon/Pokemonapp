package com.example.pokemonapp.ui.components.dialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.pokemonapp.databinding.DialogAddteamBinding

class AddTeamDialog(
    private val onSubmitClickListener: (String) -> Unit
): DialogFragment() {
    private lateinit var binding : DialogAddteamBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogAddteamBinding.inflate(LayoutInflater.from(context))

        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)


        binding.bAddTeam.setOnClickListener {
            onSubmitClickListener.invoke(binding.etName.text.toString())
            dismiss()
        }

        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }
}