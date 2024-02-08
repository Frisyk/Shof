package com.dicoding.shof.favorites.favorites


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.shof.core.ui.FavoritesAdapter
import com.dicoding.shof.favorites.databinding.FragmentFavoritesBinding
import com.dicoding.shof.favorites.favorites.di.favoritesModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoritesFragment : Fragment() {

    private val favoriteViewModel: FavoritesViewModel by viewModel()

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        loadKoinModules(favoritesModule)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val favoritesAdapter = FavoritesAdapter{ game ->
                val bundle = bundleOf("gameId" to game.id)
                findNavController().navigate(com.dicoding.shof.R.id.action_fav_to_detailsFragments, bundle)
            }

            favoriteViewModel.favoritesGames.observe(viewLifecycleOwner) { data ->
                favoritesAdapter.submitList(data)
                binding.viewEmptyPlace.root.visibility = if (data.isNotEmpty()) View.GONE else View.VISIBLE
            }

            with(binding.rvGames) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = favoritesAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}