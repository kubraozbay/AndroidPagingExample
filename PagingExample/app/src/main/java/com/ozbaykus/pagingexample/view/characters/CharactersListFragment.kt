package com.ozbaykus.pagingexample.view.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.ozbaykus.pagingexample.databinding.FragmentSearchBinding
import com.ozbaykus.pagingexample.view.adapter.CharactersListAdapter
import com.ozbaykus.pagingexample.util.PagingLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharactersListFragment : Fragment() {
    private val viewModel: CharactersViewModel by viewModels()
    private var charactersListAdapter: CharactersListAdapter = CharactersListAdapter()
    private lateinit var _binding: FragmentSearchBinding
    private val binding get() = _binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRv()
        lifecycleScope.launch {
            viewModel.charactersLiveData(
            ).observe(viewLifecycleOwner, {
                charactersListAdapter.submitData(lifecycle, it)
            })
        }
    }

    private fun initRv() {
        binding.recyclerViewResult.apply {
            adapter = charactersListAdapter
            layoutManager = GridLayoutManager(activity, 2)
            adapter = charactersListAdapter.withLoadStateHeaderAndFooter(
                header = PagingLoadStateAdapter(charactersListAdapter),
                footer = PagingLoadStateAdapter(charactersListAdapter)
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }
}