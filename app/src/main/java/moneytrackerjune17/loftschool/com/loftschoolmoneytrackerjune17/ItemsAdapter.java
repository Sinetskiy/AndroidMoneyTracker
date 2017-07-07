package moneytrackerjune17.loftschool.com.loftschoolmoneytrackerjune17;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static moneytrackerjune17.loftschool.com.loftschoolmoneytrackerjune17.Item.TYPE_EXPENSE;

/**
 * Created by andreysinetskiy on 29.06.17.
 */
class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder> {

//    private Context context;

    final List<Item> items = new ArrayList<>();
    private SparseBooleanArray selectedItems = new SparseBooleanArray();

//    public ItemsAdapter(Context context) {
//        this.context = context;
//    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        final Item item = items.get(position);
        holder.name.setText(item.name);
        holder.price.setText(String.format("%s\u20BD", String.valueOf(item.price)));
        holder.container.setActivated(selectedItems.get(position, false));
       // animate(holder);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void clear() {
        items.clear();
    }

    public void addAll(List<Item> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public void updateId(Item item, int id) {
        this.items.add(id, item);
        notifyDataSetChanged();
    }

    void add(Item item) {
        items.add(0, item);
        notifyItemInserted(0);
    }

    Item remove(int pos) {
        final Item item = items.remove(pos);
        notifyItemRemoved(pos);
        return item;
    }

    void toggleSelection(int pos) {
        if (selectedItems.get(pos, false)) {
            selectedItems.delete(pos);
        } else {
            selectedItems.put(pos, true);
        }
        notifyItemChanged(pos);
    }

    List<Integer> getSelectedItems() {
        List<Integer> items = new ArrayList<>(selectedItems.size());
        for (int i = 0; i < selectedItems.size(); i++) {
            items.add(selectedItems.keyAt(i));
        }
        return items;
    }

    void clearSelections() {
        selectedItems.clear();
        notifyDataSetChanged();
    }

    int getSelectedItemCount() {
        return selectedItems.size();
    }

//    private void animate(RecyclerView.ViewHolder viewHolder) {
//
//        final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(context, R.anim.anticipate_overshoot_interpolator);
//        viewHolder.itemView.setAnimation(animAnticipateOvershoot);
//    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView name, price;
        private final View container;

        ItemViewHolder(View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.item_container);
            name = (TextView) itemView.findViewById(R.id.name);
            price = (TextView) itemView.findViewById(R.id.price);
        }

    }

}


