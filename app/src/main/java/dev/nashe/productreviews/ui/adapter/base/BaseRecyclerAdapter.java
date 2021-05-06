package dev.nashe.productreviews.ui.adapter.base;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import dev.nashe.productreviews.ui.viewholders.base.BaseViewHolder;

public abstract class BaseRecyclerAdapter<T, VH extends BaseViewHolder> extends RecyclerView.Adapter<VH> {

    protected List<T> items;
    protected Callback<T> callback;
    protected SecondaryCallback<T> secondaryCallback;

    protected BaseRecyclerAdapter() {
        items = new ArrayList<>();
    }

    protected BaseRecyclerAdapter(List<T> items, Callback<T> callback) {
        this.items = items;
        setCallback(callback);
    }

    protected BaseRecyclerAdapter(Callback<T> callback) {
        this();
        setCallback(callback);
    }

    protected BaseRecyclerAdapter(SecondaryCallback<T> secondaryCallback){
        this();
        setSecondaryCallback(secondaryCallback);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.bind(items.get(position), callback);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(T[] items) {
        setItems(Arrays.asList(items));
    }

    public void setItems(Collection<T> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public void addItem(T item) {
        items.add(item);
        notifyItemInserted(getItemCount() - 1);
    }

    public void removeItem(T item) {
        if (items.contains(item)) {
            removeItem(items.indexOf(item));
        }
    }

    public void removeItem(int position) {
        items.remove(position);
        notifyItemRemoved(position);
    }

    public List<T> getItems() {
        return items;
    }

    public void setCallback(Callback<T> callback) {
        this.callback = callback;
    }

    public void setSecondaryCallback(SecondaryCallback<T> secondaryCallback){
        this.secondaryCallback = secondaryCallback;
    }

    protected  <B extends ViewDataBinding> B getBinding(ViewGroup parent, @LayoutRes int layout) {
        return DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), layout, parent,
                false);
    }

    public interface Callback<T> {
        void onItemSelected(T t);
    }

    public interface SecondaryCallback<T>{
        void onSecondaryItemSelected(T t);
    }
}