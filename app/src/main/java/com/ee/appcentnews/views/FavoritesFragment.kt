package com.ee.appcentnews.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ee.appcentnews.adapter.FavoritesAdapter
import com.ee.appcentnews.databinding.FragmentFavoritesBinding
import com.ee.appcentnews.model.News
import com.ee.appcentnews.utils.extensions.OnNewsItemClickListener
import com.ee.appcentnews.viewmodel.FavoritesViewModel


class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: FavoritesViewModel
    private lateinit var adapter: FavoritesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentFavoritesBinding.bind(view)
        _binding = binding

        viewModel = ViewModelProvider(this).get(FavoritesViewModel::class.java)

        adapter = FavoritesAdapter(arrayListOf(),object: OnNewsItemClickListener{
            override fun onItemClick(news: News) {
                val action = FavoritesFragmentDirections.actionFavoritesToContentFragment(news)
                findNavController().navigate(action)
            }

        })

        viewModel.getInSQLite()

        binding.favoritesRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.favoritesRecyclerView.adapter = adapter

        observeLiveData()
    }

    private fun observeLiveData(){
        viewModel.favoritesList.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            it?.let {
               adapter.updateNewsList(it)
            }
        })
    }
}