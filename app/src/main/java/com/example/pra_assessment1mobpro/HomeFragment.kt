package com.example.pra_assessment1mobpro


import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.pra_assessment1mobpro.databinding.FragmentHomeBinding

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false
        )
        binding.apply {
            btnPP.setOnClickListener { v: View ->
                //                v.findNavController()
//                    .navigate(HomeFragmentDirections.actionHomeFragmentToPersegiPanjangFragment())
                view?.findNavController()
                    ?.navigate(R.id.action_homeFragment_to_persegiPanjangFragment)
            }
            btnST.setOnClickListener { v: View ->
                //                v.findNavController()
//                    .navigate(HomeFragmentDirections.actionHomeFragmentToSegitigaFragment())
                view?.findNavController()
                    ?.navigate(R.id.action_homeFragment_to_persegiPanjangFragment)
            }
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item!!,
            view!!.findNavController()
        )
                || super.onOptionsItemSelected(item)
    }
}
