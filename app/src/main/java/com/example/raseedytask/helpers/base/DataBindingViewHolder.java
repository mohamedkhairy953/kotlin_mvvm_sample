package com.example.raseedytask.helpers.base;


import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public class DataBindingViewHolder<T> extends RecyclerView.ViewHolder {
    private final ViewDataBinding binding;

    public DataBindingViewHolder(ViewDataBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(T item) {
   //     binding.setVariable(BR.obj, item);
    }

    public void bind(BaseListAdapter adapter) {
     //   binding.setVariable(BR.adapter, adapter);
        binding.executePendingBindings();
    }
}
