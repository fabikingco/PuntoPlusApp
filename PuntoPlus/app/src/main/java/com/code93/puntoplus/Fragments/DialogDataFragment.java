package com.code93.puntoplus.Fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.code93.puntoplus.R;


public class DialogDataFragment extends DialogFragment {

    private int request_code = 0;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private int InputType = android.text.InputType.TYPE_CLASS_TEXT;
    private int MaxLeng = 0;
    private boolean isMonto = false;

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public void setmListener(OnFragmentInteractionListener mListener) {
        this.mListener = mListener;
    }

    public DialogDataFragment() {
        // Required empty public constructor
    }

    public void setRequestCode(int request_code) {
        this.request_code = request_code;
    }

    public void setInputType(int InputType){
        this.InputType = InputType;
    }

    public void setMaxLeng(int maxLeng) {
        MaxLeng = maxLeng;
    }

    public void setMonto(boolean monto) {
        isMonto = monto;
    }

    public static DialogDataFragment newInstance(String param1, String param2) {
        DialogDataFragment fragment = new DialogDataFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private View rootView;
    private TextView tvToolbar;
    private EditText etDato;
    private ImageView bt_close;
    private Button bt_save;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_dialog_data, container, false);
        tvToolbar = rootView.findViewById(R.id.tvToolbar);
        etDato = rootView.findViewById(R.id.etDato);
        etDato.setInputType(InputType);
        if (MaxLeng > 0) {
            etDato.setFilters(new InputFilter[]{new InputFilter.LengthFilter(MaxLeng)});
        }

        if (isMonto) {
            etDato.addTextChangedListener(new TextWatcher() {
                private boolean isChanged = false;

                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    if (isChanged) {
                        return;
                    }
                    String str = editable.toString();
                    isChanged = true;
                    String cuttedStr = str;
                    for (int i = str.length() - 1; i >= 0; i--) {
                        char c = str.charAt(i);
                        if ('.' == c) {
                            cuttedStr = str.substring(0, i) + str.substring(i + 1);
                            break;
                        }
                    }
                    int NUM = cuttedStr.length();
                    int zeroIndex = -1;
                    for (int i = 0; i < NUM - 2; i++) {
                        char c = cuttedStr.charAt(i);
                        if (c != '0') {
                            zeroIndex = i;
                            break;
                        } else if (i == NUM - 3) {
                            zeroIndex = i;
                            break;
                        }
                    }
                    if (zeroIndex != -1) {
                        cuttedStr = cuttedStr.substring(zeroIndex);
                    }
                    if (cuttedStr.length() < 3) {
                        cuttedStr = "0" + cuttedStr;
                    }
                    cuttedStr = cuttedStr.substring(0, cuttedStr.length() - 2)
                            + "." + cuttedStr.substring(cuttedStr.length() - 2);

                    etDato.setText(cuttedStr);
                /*numeroTotal = Double.valueOf(amountServiceCost);
                numeroTotal = numeroTotal + Double.valueOf(cuttedStr);
                String auxNum = String.valueOf(numeroTotal);

                if (auxNum.length() >= 3) {
                    String[] parts = auxNum.split("\\.");
                    String p = parts[1];
                    if (p.length() == 1)
                        auxNum += "0";
                }*/
                    etDato.setSelection(etDato.length());
                    isChanged = false;
                }
            });
        }

        bt_close = rootView.findViewById(R.id.bt_close);
        bt_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        bt_save = rootView.findViewById(R.id.bt_save);
        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!etDato.getText().toString().trim().equals("")){
                    if (mListener != null) {
                        mListener.onFragmentInteraction(request_code, etDato.getText().toString());
                    }
                    dismiss();
                }

            }
        });



        tvToolbar.setText(mParam1);
        etDato.setHint(mParam2);

        return rootView;
    }

    private void enviarData() {

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(int requestCode, Object obj);
    }
}
