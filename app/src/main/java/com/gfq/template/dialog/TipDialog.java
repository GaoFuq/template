package com.gfq.template.dialog;

import android.content.Context;

import com.gfq.template.R;
import com.gfq.template.databinding.DialogTipBinding;
import com.gfq.dialog.base.BaseRoundDialog;


public abstract class TipDialog {
    private Context context;

    public TipDialog(Context context) {
        this.context = context;
        init();
    }

    private void init() {
        BaseRoundDialog<DialogTipBinding> dialog = new BaseRoundDialog<>(context);
        DialogTipBinding binding = dialog.bindView(R.layout.dialog_tip);
        binding.tvContent.setText(setContentText());
        binding.tvCancel.setOnClickListener(v -> {
            onCancel();
            dialog.dismiss();
        });
        binding.tvConfirm.setOnClickListener(v -> {
            onConfirm();
            dialog.dismiss();
        });
        dialog.show();
    }

    protected abstract void onConfirm();

    protected abstract void onCancel();

    protected abstract String setContentText();


}

