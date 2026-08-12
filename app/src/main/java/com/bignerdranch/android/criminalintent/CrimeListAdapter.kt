package com.bignerdranch.android.criminalintent

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.criminalintent.databinding.ListItemCrimeBinding
import com.bignerdranch.android.criminalintent.databinding.ListItemSeriousCrimeBinding
import com.google.android.material.snackbar.Snackbar

private const val VIEW_TYPE_NORMAL = 0
private const val VIEW_TYPE_POLICE = 1

abstract class CrimeHolder(itemView: android.view.View): RecyclerView.ViewHolder(itemView) {
    abstract fun bind(crime: Crime)
}

class NormalCrimeHolder(private val binding: ListItemCrimeBinding) : CrimeHolder(binding.root) {
    override fun bind(crime: Crime) {
        binding.crimeTitle.text = crime.title
        binding.crimeDate.text = crime.date.toString()

        binding.root.setOnClickListener {
            Snackbar.make(
                binding.root,
                "${crime.title} clicked!",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }
}

class SeriousCrimeHolder(private val binding: ListItemSeriousCrimeBinding) : CrimeHolder(binding.root) {
    override fun bind(crime: Crime) {
        binding.crimeTitle.text = crime.title
        binding.crimeTitle.setTextColor(ContextCompat.getColor(binding.root.context, R.color.maroon))
        binding.crimeDate.text = crime.date.toString()

        binding.root.setOnClickListener {
            Snackbar.make(
                binding.root,
                "${crime.title} clicked! (police)",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }
}

class CrimeListAdapter(private val crimes: List<Crime>) : RecyclerView.Adapter<CrimeHolder>() {
    override fun getItemViewType(position: Int): Int {
        return when {
            crimes[position].requiresPolice -> VIEW_TYPE_POLICE
            else -> VIEW_TYPE_NORMAL
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CrimeHolder {
        val inflater = LayoutInflater.from(parent.context)

        return if (viewType == VIEW_TYPE_NORMAL) {
            val binding = ListItemCrimeBinding.inflate(inflater, parent, false)
            NormalCrimeHolder(binding)
        } else {
            val binding = ListItemSeriousCrimeBinding.inflate(inflater, parent, false)
            SeriousCrimeHolder(binding)
        }
    }

    override fun onBindViewHolder(
        holder: CrimeHolder,
        position: Int
    ) {
        val crime = crimes[position]
        holder.bind(crime)
    }

    override fun getItemCount(): Int {
        return crimes.size
    }

}