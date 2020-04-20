package pl.krko.coronastats.ui.mainactivity.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.krko.coronastats.databinding.ItemCoronalistviewBinding
import pl.krko.coronastats.model.objects.Corona


class CoronaAdapter(
    var data: MutableList<Corona>,
    private val itemLongClickHandler: (corona: Corona) -> Unit,
    private val itemClickHandler: (corona: Corona) -> Unit
) : RecyclerView.Adapter<CoronaAdapter.CstViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CstViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: ItemCoronalistviewBinding =
            ItemCoronalistviewBinding.inflate(layoutInflater, parent, false)
        return CstViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: CstViewHolder, position: Int) {
        holder.bind(data[position], itemLongClickHandler, itemClickHandler)
    }

    class CstViewHolder(private val binding: ItemCoronalistviewBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            corona: Corona,
            itemLongClickHandler: (corona: Corona) -> Unit,
            itemClickHandler: (corona: Corona) -> Unit
        ) {
            binding.corona = corona
            binding.executePendingBindings()

            binding.root.setOnLongClickListener {
                itemLongClickHandler(corona)
                true
            }

            binding.root.setOnClickListener {
                itemClickHandler(corona)
                true
            }
        }
    }
}