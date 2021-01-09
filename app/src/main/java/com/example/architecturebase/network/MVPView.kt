package com.example.architecturebase.network

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecturebase.adapter.MainAdapter
import com.example.architecturebase.databinding.FragmentRecyclerBinding


class MVPView : Fragment(), MVPContract.IView {

    val mainAdapter = MainAdapter()

    var binding: FragmentRecyclerBinding? = null

    private val presenter: MVPContract.IPresenter = MVPPresenter(this)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecyclerBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Логично ли то, что я вынес это в отдельную функцию?
        showPost()

    }

    override fun showPost() {
        binding?.mainRV?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mainAdapter
        }

        binding?.listSRL?.isRefreshing = true
        presenter.getPost()

        binding?.listSRL?.setOnRefreshListener {
            mainAdapter.items = emptyList()
            presenter.getPost()


        }
    }
}


