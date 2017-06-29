package moneytrackerjune17.loftschool.com.loftschoolmoneytrackerjune17;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andreysinetskiy on 29.06.17.
 */
class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder> {
    final List<Item> items = new ArrayList<>();

    ItemsAdapter() {
        items.add(new Item("Молоко", 35));
        items.add(new Item("Зубная щетка", 1500));
        items.add(new Item("Сковородка с антипригарным покрытием", 55));
        items.add(new Item("Молоко", 35));
        items.add(new Item("Зубная щетка", 1500));
        items.add(new Item("Сковородка с антипригарным покрытием", 55));
        items.add(new Item("Молоко", 35));
        items.add(new Item("Зубная щетка", 1500));
        items.add(new Item("Сковородка с антипригарным покрытием", 55));
        items.add(new Item("Молоко", 35));
        items.add(new Item("Зубная щетка", 1500));
        items.add(new Item("Сковородка с антипригарным покрытием", 55));
    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        final Item item = items.get(position);
        holder.name.setText(item.name);
        holder.price.setText(String.valueOf(item.price) + "\u20bd");
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView name, price;

        ItemViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            price = (TextView) itemView.findViewById(R.id.price);
        }
    }
}

