package pl.krko.coronastats.ui.details

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import pl.krko.coronastats.R
import pl.krko.coronastats.databinding.ActivityDetailsAdctivityBinding

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsAdctivityBinding
    private lateinit var viewModel: DetailsActivityViewModel

    private var country: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()

        parseIntentData(intent)
    }

    private fun parseIntentData(intent: Intent?) {

        val extras = intent?.extras

        if(extras != null) {
            country = intent.extras?.getString("country")
        }

        if(country.isNullOrEmpty()) {
            Toast.makeText(this, "Błąd identyfikatora kraju", Toast.LENGTH_LONG).show()
            finish()
        } else {
            viewModel.country = country
        }
    }

    private fun initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details_adctivity)

        viewModel = ViewModelProvider(this).get(DetailsActivityViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        initObservers()
    }

    private fun initObservers() {

    }
}
