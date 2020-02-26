package com.example.pra_assessment1mobpro


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.pra_assessment1mobpro.databinding.FragmentPersegiPanjangBinding
import kotlinx.android.synthetic.main.fragment_persegi_panjang.*

/**
 * A simple [Fragment] subclass.
 */
class PersegiPanjangFragment : Fragment() {
    private lateinit var binding: FragmentPersegiPanjangBinding
    private var panjang: Double = 0.00
    private var lebar: Double = 0.00
    private var luasPP: Double = 0.00
    private var kelilingPP: Double = 0.00

    companion object {
        const val KEY_LUASPP = "key_luasPP"
        const val KEY_KELILINGPP = "key_kelilingPP"
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_persegi_panjang, container, false)

        if (savedInstanceState != null) {
            luasPP = savedInstanceState.getDouble(KEY_LUASPP)
            kelilingPP = savedInstanceState.getDouble(KEY_KELILINGPP)
            updateNilai(luasPP, kelilingPP)
        }

        binding.apply {
            btnHitungPP.setOnClickListener {
                if (etPanjang.text.toString().isEmpty() || etLebar.text.toString().isEmpty()) {
                    Toast.makeText(activity, "Field tidak boleh kosong", Toast.LENGTH_SHORT).show()
                } else {
                    panjang = etPanjang.text.toString().toDouble()
                    lebar = etLebar.text.toString().toDouble()
                    luasPP = panjang * lebar
                    kelilingPP = 2 * (panjang + lebar)
//                    nilaiLuasPP = luasPP
//                    nilaiKelilingPP = kelilingPP
                    tvLuasPP.text = "Luas = $luasPP"
                    tvKelilingPP.text = "Keliling = $kelilingPP"
                }
            }

            btnSharePP.setOnClickListener {
                val textLuasPP = tv_luasPP.text.toString()
                val textKelilingPP = tv_kelilingPP.text.toString()
                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_TEXT, textLuasPP + "\n" + textKelilingPP)
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Hasil hitung persegi panjang")
                startActivity(Intent.createChooser(shareIntent, "Share text via..."))
            }
        }

        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putDouble(KEY_LUASPP, luasPP)
        outState.putDouble(KEY_KELILINGPP, kelilingPP)
    }

    @SuppressLint("SetTextI18n")
    private fun updateNilai(n1: Double, n2: Double) {
        binding.tvLuasPP.setText("Luas = " + n1.toString())
        binding.tvKelilingPP.setText("Keliling = " + n2.toString())
    }

//Intent, start activity, choose...
}
