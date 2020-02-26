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
import com.example.pra_assessment1mobpro.databinding.FragmentSegitigaBinding
import kotlinx.android.synthetic.main.fragment_segitiga.*
import kotlin.math.sqrt

/**
 * A simple [Fragment] subclass.
 */
class SegitigaFragment : Fragment() {
    private lateinit var binding: FragmentSegitigaBinding
    private var alas: Double = 0.00
    private var tinggi: Double = 0.00
    private var pythagoras: Double = 0.00
    private var luasST: Double = 0.00
    private var kelilingST: Double = 0.00

    companion object {
        const val KEY_LUASST = "key_luasST"
        const val KEY_KELILINGST = "key_kelilingST"
    }


    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_segitiga, container, false)

        if (savedInstanceState != null) {
            luasST = savedInstanceState.getDouble(KEY_LUASST)
            kelilingST = savedInstanceState.getDouble(KEY_KELILINGST)
            updateNilai(luasST, kelilingST)
        }

        binding.apply {
            btnHitungST.setOnClickListener {
                if (etAlas.text.toString().isEmpty() || etTinggi.text.toString().isEmpty()) {
                    Toast.makeText(activity, "Field tidak boleh kosong", Toast.LENGTH_SHORT).show()
                } else {
                    alas = etAlas.text.toString().toDouble()
                    tinggi = etTinggi.text.toString().toDouble()
                    pythagoras = sqrt(alas * alas + tinggi * tinggi)
                    luasST = (alas * tinggi) / 2
                    kelilingST = alas + tinggi + pythagoras
//                    nilaiLuasST = luasST
//                    nilaiKelilingST = kelilingST
                    tvLuasST.text = "Luas = $luasST"
                    tvKelilingST.text = "Keliling = $kelilingST"
                }
            }

            btnShareST.setOnClickListener {
                val textLuasST = tv_luasST.text.toString()
                val textKelilingST = tv_kelilingST.text.toString()
                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_TEXT, textLuasST + "\n" + textKelilingST)
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Hasil hitung segitiga")
                startActivity(Intent.createChooser(shareIntent, "Share text via..."))
            }
        }

        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putDouble(KEY_LUASST, luasST)
        outState.putDouble(KEY_KELILINGST, kelilingST)
    }

    @SuppressLint("SetTextI18n")
    private fun updateNilai(n1: Double, n2: Double) {
        binding.tvLuasST.setText("Luas = " + n1.toString())
        binding.tvKelilingST.setText("Keliling = " + n2.toString())
    }

}
