package com.ee.appcentnews.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ee.appcentnews.databinding.FragmentNewSourceBinding

class NewSourceFragment : Fragment() {

    private var _binding: FragmentNewSourceBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewSourceBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentNewSourceBinding.bind(view)
        _binding = binding


        loadUrl()
        backSetOnClickListener()
    }

    private fun backSetOnClickListener(){
        binding.backIv.setOnClickListener{
            findNavController().popBackStack()
        }
    }

    private fun loadUrl() {
        arguments?.let {
            val args = NewSourceFragmentArgs.fromBundle(it)
            binding.webView.loadUrl(args.urlArg.toString())
        }
    }
}