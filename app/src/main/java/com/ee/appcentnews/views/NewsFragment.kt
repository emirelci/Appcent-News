package com.ee.appcentnews.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ee.appcentnews.adapter.NewsAdapter
import com.ee.appcentnews.databinding.FragmentNewsBinding
import com.ee.appcentnews.model.News
import com.ee.appcentnews.utils.extensions.OnNewsItemClickListener
import com.ee.appcentnews.viewmodel.NewsViewModel


class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: NewsViewModel
    private lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentNewsBinding.bind(view)
        _binding = binding

        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        viewModel.getDataFromAPI()

        adapter = NewsAdapter(arrayListOf(), object: OnNewsItemClickListener{
            override fun onItemClick(news: News) {
                val action = NewsFragmentDirections.actionNewsToContentFragment(news)
                findNavController().navigate(action)
            }
        })

        binding.newsRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.newsRecyclerView.adapter = adapter

        searchNews()
        observeLiveData()

    }

    private fun searchNews(){
        binding.newsSearchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.getQueryDataFromAPI(query!!)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return true
            }

        })
    }

    private fun observeLiveData(){
        viewModel.newsList.observe(viewLifecycleOwner, Observer { news ->
            news?.let {
                adapter.updateNewsList(news)
            }
        })

    }

    override fun onResume() {
        super.onResume()

    }






}