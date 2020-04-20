package pl.krko.coronastats.ui.mainactivity

import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pl.krko.coronastats.R
import pl.krko.coronastats.databinding.ActivityMainBinding
import pl.krko.coronastats.model.objects.Corona


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
    }

    private fun initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupRecyclerView()

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        initObservers()
    }

    private fun initObservers() {
        viewModel.allCoronaObjects.observe(this, Observer {
            recyclerView.adapter = viewModel.adapter
        })

        viewModel.openCoronaDetails.observe(this, Observer {
            //val intent = Intent(this, DetailsActivity::class.java)
            //intent.putExtra("country", it.country)

            //startActivity(intent)
            showMoreInfDialog(it)
        })

        viewModel.showToastAction.observe(this, Observer {

        })
    }

    private fun showMoreInfDialog(corona: Corona) {
        AlertDialog.Builder(this)
            .setTitle("Dodatkowe dane")
            .setMessage("Dzisiejsz zgony: ${corona.todayDeaths}\n" +
                    "Aktywne zarażenia: ${corona.active}\n" +
                    "W stanie ciężkim: ${corona.critical}\n" +
                    "Zgony/milion: ${corona.deathsPerOneMillion}\n" +
                    "Testy: ${corona.tests}\n" +
                    "Testy/milion: ${corona.testsPerOneMillion}"
            )
            .setCancelable(true)
            .show()
    }

    private fun setupRecyclerView() {
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    fun displayDialog(builder: AlertDialog.Builder) {
        builder.show()
    }
}
