package com.ee.appcentnews.views

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.ee.appcentnews.databinding.FragmentContentBinding
import com.ee.appcentnews.model.News
import com.ee.appcentnews.viewmodel.ContentViewModel


class ContentFragment : Fragment() {

    private var _binding: FragmentContentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ContentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentContentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentContentBinding.bind(view)
        _binding = binding

        viewModel = ViewModelProvider(this).get(ContentViewModel::class.java)

        findNavController().enableOnBackPressed(true)

        argumentsNews()?.let { checkLikedArticle(it) }
        bindArguments()
        setOnClickListeners()
        observeLiveData()
    }


    private fun setOnClickListeners(){
        sourceBtnSetOnClickListener()
        toolBarSetOnClickListener()
    }

    private fun sourceBtnSetOnClickListener(){
        binding.sourceBtn.setOnClickListener {
            val action = ContentFragmentDirections.actionContentFragmentToNewSourceFragment(
                argumentsNews()?.url
            )
            findNavController().navigate(action)
        }
    }


    private fun toolBarSetOnClickListener(){
        binding.customToolbar.backIv.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.customToolbar.likeIv.setOnClickListener{
            argumentsNews()?.let { it1 -> viewModel.storeInSQLite(it1,requireContext()) }
        }
        binding.customToolbar.unlikeIv.setOnClickListener{
            argumentsNews()?.let { it1 -> viewModel.deleteFromSQLite(it1.uuid) }
        }
        binding.customToolbar.shareIv.setOnClickListener{
            argumentsNews()?.let { it1 -> shareNews(it1.url) }
        }
    }
    private fun shareNews(url: String?) {
        val clipboardManager = requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("Haber Linki", url)
        clipboardManager.setPrimaryClip(clipData)
        Toast.makeText(requireContext(), "Haber linki kopyalandı", Toast.LENGTH_SHORT).show()
    }

    private fun argumentsNews() : News? {
        arguments?.let {
            val args = ContentFragmentArgs.fromBundle(it).newsArg
            return args
        }
        return null
    }

    private fun bindArguments() {
        arguments?.let {
            val args = ContentFragmentArgs.fromBundle(it).newsArg
            Glide.with(requireView()).load(args.urlToImage).fitCenter().into(binding.contentImageIv)
            binding.contentHeaderTv.text = args.title.toString()
            binding.authorTv.text = args.author.toString()
            binding.dateTv.text = args.publishedAt.toString()
            binding.contentTv.text = args.content.toString()
        }
    }

    private fun observeLiveData(){
        viewModel.liked.observe(viewLifecycleOwner, Observer {
            if(it){
                binding.customToolbar.unlikeIv.visibility = View.VISIBLE
            }else{
                binding.customToolbar.unlikeIv.visibility = View.GONE
            }
        })
    }

    private fun checkLikedArticle(args: News){
        viewModel.getFromSQLite(args)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}