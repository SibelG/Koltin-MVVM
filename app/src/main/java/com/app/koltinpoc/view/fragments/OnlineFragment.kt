package com.app.koltinpoc.view.fragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.Button
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.koltinpoc.R
import com.app.koltinpoc.databinding.FragmentOnlineBinding
import com.app.koltinpoc.di.DBRepository
import com.app.koltinpoc.utils.Constants
import com.app.koltinpoc.utils.Constants.API_KEY
import com.app.koltinpoc.utils.Constants.LANGUAGE
import com.app.koltinpoc.utils.DataHandler
import com.app.koltinpoc.utils.LogData
import com.app.koltinpoc.view.adapter.NewsAdapter
import com.app.koltinpoc.viewModel.OnlineViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class OnlineFragment :Fragment(R.layout.fragment_online),View.OnClickListener, SearchView.OnQueryTextListener {

    private lateinit var binding: FragmentOnlineBinding

    @Inject
    lateinit var newsAdapter: NewsAdapter

    val viewModel: OnlineViewModel by viewModels()
    lateinit var dbRepository:DBRepository



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOnlineBinding.bind(view)
        init()

        viewModel.topHeadlines.observe(viewLifecycleOwner) { dataHandler ->
            when (dataHandler) {
                is DataHandler.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    newsAdapter.differ.submitList(dataHandler.data?.articles)
                }
                is DataHandler.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    LogData("onViewCreated: ERROR " + dataHandler.message)
                }
                is DataHandler.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    LogData("onViewCreated: LOADING..")

                }
            }

        }
        viewModel.topCategories.observe(viewLifecycleOwner) { dataHandler ->
            when (dataHandler) {
                is DataHandler.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    newsAdapter.differ.submitList(dataHandler.data?.articles)
                }
                is DataHandler.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    LogData("onViewCreated: ERROR " + dataHandler.message)
                }
                is DataHandler.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    LogData("onViewCreated: LOADING..")

                }
            }

        }


        binding.btn1.setOnClickListener(this)
        binding.btn2.setOnClickListener(this)
        binding.btn3.setOnClickListener(this)
        binding.btn4.setOnClickListener(this)
        binding.btn5.setOnClickListener(this)
        binding.btn6.setOnClickListener(this)
        binding.btn7.setOnClickListener(this)



        //viewModel.getTopHeadlines()
        //viewModel.getTopCategories("general")


    }
    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.search_menu, menu)

        val search = menu?.findItem(R.id.menu_search)
        val searchView = search?.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)
        super.onCreateOptionsMenu(menu, menuInflater);
    }
    override fun onQueryTextSubmit(query: String?): Boolean {
        // it will triggered when
        // we submit the written test
        return true
    }
    // this function will triggered when we
    // write even a single char in search view
    override fun onQueryTextChange(query: String?): Boolean {
        if(query != null){
            searchDatabase(query)
        }
        return true
    }
    // We have just created this function for searching our database
    private fun searchDatabase(query: String) {
        // %" "% because our custom sql query will require that
        val searchQuery = "%$query%"

        dbRepository.searchDatabase(searchQuery).observe(this) { list ->
            list.let {
                newsAdapter.setData(it)
            }
        }
    }

    private fun init() {

        newsAdapter.onArticleClicked {
            val bundle = Bundle().apply {
                putParcelable("article_data", it)

            }
            findNavController().navigate(
                R.id.action_onlineFragment_to_articleDetailsFragment,
                bundle
            )
        }

        binding.recyclerView.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }

    }

    override fun onClick(v: View?) {
        val button = v as Button
        var category = button.text.toString().toLowerCase()


        Constants.apply {
            when (category) {
                    ALL -> {
                        viewModel.getTopCategories(ALL)
                    }
                    HEALTH -> {
                        viewModel.getTopCategories(HEALTH)
                    }
                    TECH -> {
                        viewModel.getTopCategories(TECH)
                    }
                    ENTERTAINMENT -> {
                        viewModel.getTopCategories(ENTERTAINMENT)
                    }
                    BUSINESS -> {
                        viewModel.getTopCategories(BUSINESS)
                    }
                    GENERAL -> {
                        viewModel.getTopCategories(GENERAL)
                    }
                    else -> {
                        viewModel.getTopCategories(SPORT)
                    }
                }
            }
    }
/*val button = v as Button
val category = button.text.toString()
/* progressDialog.setTitle("Fetching News Of $category")
progressDialog.show()*/
viewModel.getTopCategories(category)*/
}

