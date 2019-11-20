package com.example.raseedytask.helpers.base;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.example.raseedytask.BR;

public class BindingViewHolder extends RecyclerView.ViewHolder {
    private final ViewDataBinding binding;

    public BindingViewHolder(ViewDataBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Object obj) {
       binding.setVariable(BR.obj, obj);
    }

    public void bind(BaseAdapter adapter) {
       binding.setVariable(BR.adapter, adapter);
        binding.executePendingBindings();
    }

}