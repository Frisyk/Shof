package com.dicoding.shof.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.dicoding.shof.R
import com.dicoding.shof.core.data.remote.network.ApiResponse
import com.dicoding.shof.core.domain.model.Games
import com.dicoding.shof.databinding.FragmentDetailsBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class DetailsFragment : Fragment() {
    private val detailsViewModel: DetailsViewModel by viewModel()

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        val gameId = arguments?.getInt("gameId")
        if (gameId != null) {
            showDetails(gameId)
        }
        return binding.root
    }

    private fun showDetails(id: Int) {
        lifecycleScope.launch {
            detailsViewModel.getDetailsGame(id).observe(viewLifecycleOwner) {
                when (it) {
                    is ApiResponse.Success -> {
                        val gameDetails = it.data
                        binding.progressIndicator.visibility = View.GONE
                        binding.tvName.text = gameDetails.name
                        binding.tvdetail.text = gameDetails.description
                        binding.rating.text = getString(
                            com.dicoding.shof.core.R.string.rating,
                            gameDetails.rating.toString()
                        )
                        binding.released.text = gameDetails.released
                        binding.ivLogo.let { image ->
                            Glide.with(image.context)
                                .load(gameDetails.backgroundImage)
                                .into(image)
                        }
                        binding.ivdetail.let { image ->
                            Glide.with(image.context)
                                .load(gameDetails.backgroundImageAdditional)
                                .into(image)
                        }

                        val game = Games(
                            gameDetails.id,
                            gameDetails.name,
                            gameDetails.released,
                            gameDetails.rating,
                            gameDetails.backgroundImage
                        )
                        binding.btnFav.visibility = View.VISIBLE
                        detailsViewModel.isFavGame(gameDetails.id).observe(viewLifecycleOwner) { favorite ->
                            setStatusFavorite(favorite)
                            binding.btnFav.setOnClickListener{
                                if (!favorite) detailsViewModel.setFavoriteGame(game) else detailsViewModel.deleteFavGame(game)
                            }
                        }
                    }

                    is ApiResponse.Error -> {
                        val errorMessage = it.errorMessage
                        showSnackBar(errorMessage)
                    }

                    is ApiResponse.Empty -> {
                        binding.viewEmptyDetails.root.visibility = View.VISIBLE
                    }
                }
            }

        }

    }
    private fun showSnackBar(message: String) {
        Snackbar.make(
            binding.linearLayout,
            message,
            Snackbar.LENGTH_SHORT
        ).show()
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.btnFav.setIconResource(R.drawable.ic_favorites)
            binding.btnFav.text = getString(R.string.added_to_fav)
        } else {
            binding.btnFav.setIconResource(R.drawable.ic_favorites_border)
            binding.btnFav.text = getString(R.string.add_to_fav)
        }
    }

}