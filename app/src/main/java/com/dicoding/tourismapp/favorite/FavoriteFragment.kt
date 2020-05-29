package com.dicoding.tourismapp.favorite

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.tourismapp.MyApplication
import com.dicoding.tourismapp.R
import com.dicoding.tourismapp.core.di.DaggerCoreComponent
import com.dicoding.tourismapp.core.ui.TourismAdapter
import com.dicoding.tourismapp.detail.DetailTourismActivity
import com.dicoding.tourismapp.detail.DetailTourismViewModel
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.android.synthetic.main.fragment_home.rv_tourism
import javax.inject.Inject

class FavoriteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
//        val appComponent = (requireActivity().application as MyApplication).appComponent
//        DaggerCoreComponent.factory().create(requireActivity(), appComponent).inject(this)
    }

    @Inject
    lateinit var viewModel: FavoriteViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {

//            val factory = FavoriteViewModelFactory.getInstance(requireActivity())
//            val viewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]

            val tourismAdapter = TourismAdapter()
            tourismAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailTourismActivity::class.java)
                intent.putExtra(DetailTourismActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            viewModel.getFavoriteTourism().observe(this, Observer { dataTourism ->
                tourismAdapter.setData(dataTourism)
                view_empty.visibility = if (dataTourism.isNotEmpty()) View.GONE else View.VISIBLE
            })

            with(rv_tourism) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tourismAdapter
            }
        }
    }
}
