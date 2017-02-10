package com.mandevices.complexEquationsSet;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.mandevices.complexEquationsSet.model.ComplexNumber;
import com.mandevices.complexEquationsSet.model.Matrix22;
import com.mandevices.complexEquationsSet.model.Matrix33;
import com.mandevices.complexEquationsSet.model.Matrix44;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by levul on 2/5/2017.
 */

public class MyFragment extends Fragment {
    private TextView txt_fragment_id;
    private FrameLayout fragmentContainer;

    public static MyFragment newInstance(int index) {
        MyFragment fragment = new MyFragment();
        Bundle b = new Bundle();
        b.putInt("index", index);
        fragment.setArguments(b);
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int index = getArguments().getInt("index", 0);

        if (index == 1) {
            View view = inflater.inflate(R.layout.equations_set_33_fragment, container, false);
            fragmentContainer = (FrameLayout) view.findViewById(R.id.fragment_parent);
            initFragmentSet33(view);
            return view;
        } else if (index == 0) {
            View view = inflater.inflate(R.layout.equations_set_22_fragment, container, false);
            fragmentContainer = (FrameLayout) view.findViewById(R.id.fragment_parent);
            initFragment22(view);
            return view;
        } else if (index == 2) {
            View view = inflater.inflate(R.layout.equations_set_44_fragment, container, false);
            fragmentContainer = (FrameLayout) view.findViewById(R.id.fragment_parent);
            initFragment44(view);
            return view;
        } else {
            View view = inflater.inflate(R.layout.fragment_info, container, false);
            fragmentContainer = (FrameLayout) view.findViewById(R.id.fragment_parent);
            return view;
        }
    }


    private void initFragment44(View view) {
        final Button btn_solve = (Button) view.findViewById(R.id.btn_solve);
        final EditText[][] m = new EditText[4][4];
        final EditText[] c = new EditText[4];

        final TextView txt_ketqua = (TextView) view.findViewById(R.id.txt_ketqua);
        final TextView txt_result = (TextView) view.findViewById(R.id.txt_result);

        m[0][0] = (EditText) view.findViewById(R.id.m00);
        m[0][1] = (EditText) view.findViewById(R.id.m01);
        m[0][2] = (EditText) view.findViewById(R.id.m02);
        m[0][3] = (EditText) view.findViewById(R.id.m03);


        m[1][0] = (EditText) view.findViewById(R.id.m10);
        m[1][1] = (EditText) view.findViewById(R.id.m11);
        m[1][2] = (EditText) view.findViewById(R.id.m12);
        m[1][3] = (EditText) view.findViewById(R.id.m13);

        m[2][0] = (EditText) view.findViewById(R.id.m20);
        m[2][1] = (EditText) view.findViewById(R.id.m21);
        m[2][2] = (EditText) view.findViewById(R.id.m22);
        m[2][3] = (EditText) view.findViewById(R.id.m23);

        m[3][0] = (EditText) view.findViewById(R.id.m30);
        m[3][1] = (EditText) view.findViewById(R.id.m31);
        m[3][2] = (EditText) view.findViewById(R.id.m32);
        m[3][3] = (EditText) view.findViewById(R.id.m33);


        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                m[i][j].setSingleLine(true);
            }

        c[0] = (EditText) view.findViewById(R.id.c0);
        c[1] = (EditText) view.findViewById(R.id.c1);
        c[2] = (EditText) view.findViewById(R.id.c2);
        c[3] = (EditText) view.findViewById(R.id.c3);

        btn_solve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(fragmentContainer.getWindowToken(), 0);

                try {
                    ComplexNumber[][] vals = new ComplexNumber[4][4];
                    ComplexNumber[][] vals0 = new ComplexNumber[4][4];
                    ComplexNumber[][] vals1 = new ComplexNumber[4][4];
                    ComplexNumber[][] vals2 = new ComplexNumber[4][4];
                    ComplexNumber[][] vals3 = new ComplexNumber[4][4];

                    Matrix44 matA = new Matrix44();
                    Matrix44 matA0 = new Matrix44();
                    Matrix44 matA1 = new Matrix44();
                    Matrix44 matA2 = new Matrix44();
                    Matrix44 matA3 = new Matrix44();

                    ComplexNumber[] valC = new ComplexNumber[4];

                    for (int i = 0; i < 4; i++)
                        valC[i] = ComplexNumber.parseCplx(c[i].getText().toString());

                    //setup matA
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 4; j++) {
                            vals[i][j] = ComplexNumber.parseCplx(m[i][j].getText().toString());
                        }
                    }
                    matA.setMatrix(vals);
                    Log.w("DET_A", matA.det() + "");

                    //setup matA0
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 4; j++) {
                            vals0[i][j] = new ComplexNumber();
                            vals0[i][j].copyFrom(vals[i][j]);
                        }
                    }
                    for (int i = 0; i < 4; i++)
                        vals0[i][0].copyFrom(valC[i]);
                    matA0.setMatrix(vals0);
                    Log.w("DET_A0", matA0.det() + "");

                    //setup matA1
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 4; j++) {
                            vals1[i][j] = new ComplexNumber();
                            vals1[i][j].copyFrom(vals[i][j]);
                        }
                    }

                    for (int i = 0; i < 4; i++)
                        vals1[i][1].copyFrom(valC[i]);
                    matA1.setMatrix(vals1);
                    Log.w("DET_A1", matA1.det() + "");

                    //setup matA2
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 4; j++) {
                            vals2[i][j] = new ComplexNumber();
                            vals2[i][j].copyFrom(vals[i][j]);
                        }
                    }

                    for (int i = 0; i < 4; i++)
                        vals2[i][2].copyFrom(valC[i]);
                    matA2.setMatrix(vals2);
                    Log.w("DET_A2", matA2.det() + "");

                    //setup matA3
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 4; j++) {
                            vals3[i][j] = new ComplexNumber();
                            vals3[i][j].copyFrom(vals[i][j]);
                        }
                    }

                    for (int i = 0; i < 4; i++)
                        vals3[i][2].copyFrom(valC[i]);
                    matA3.setMatrix(vals3);
                    Log.w("DET_A2", matA3.det() + "");

//                    txt_ketqua.setVisibility(View.VISIBLE);
//                    txt_result.setVisibility(View.VISIBLE);

                    if (matA.det().isZero()) txt_result.setText("Hệ phương trình không có nghiệm");
                    else {

                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle("Kết quả")
                                .setMessage("b0 = " + matA0.det().divide(matA.det()).toString() + "\n" +
                                        "b1 = " + matA1.det().divide(matA.det()) + "\n" +
                                        "b2 = " + matA2.det().divide(matA.det()) + "\n" +
                                        "b3 = " + matA3.det().divide(matA.det()))
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                }).create().show();
                    }

                } catch (Exception ex) {
                    Log.e("ERROR", ex.toString());

                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("Lỗi");
                    builder.setMessage("Vui lòng nhập đầy đủ các ô và thử lại! (kể cả số 0)");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    builder.create().show();
                }

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void initFragment22(View view) {
        final Button btn_solve = (Button) view.findViewById(R.id.btn_solve);
        final EditText[][] n = new EditText[2][2];
        final EditText[] c = new EditText[2];

        final TextView txt_ketqua = (TextView) view.findViewById(R.id.txt_ketqua);
        final TextView txt_result = (TextView) view.findViewById(R.id.txt_result);

        n[0][0] = (EditText) view.findViewById(R.id.m00);
        n[0][1] = (EditText) view.findViewById(R.id.m01);

        n[1][0] = (EditText) view.findViewById(R.id.m10);
        n[1][1] = (EditText) view.findViewById(R.id.m11);

        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++) {
                n[i][j].setSingleLine(true);
            }

        c[0] = (EditText) view.findViewById(R.id.c0);
        c[1] = (EditText) view.findViewById(R.id.c1);

        btn_solve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(fragmentContainer.getWindowToken(), 0);

                try {
                    ComplexNumber[][] vals = new ComplexNumber[2][2];
                    ComplexNumber[][] vals0 = new ComplexNumber[2][2];
                    ComplexNumber[][] vals1 = new ComplexNumber[2][2];

                    Matrix22 matA = new Matrix22();
                    Matrix22 matA0 = new Matrix22();
                    Matrix22 matA1 = new Matrix22();

                    ComplexNumber[] valC = new ComplexNumber[3];

                    for (int i = 0; i < 2; i++)
                        valC[i] = ComplexNumber.parseCplx(c[i].getText().toString());

                    //setup matA
                    for (int i = 0; i < 2; i++) {
                        for (int j = 0; j < 2; j++) {
                            vals[i][j] = ComplexNumber.parseCplx(n[i][j].getText().toString());
                        }
                    }
                    matA.setMatrix(vals);
                    Log.w("DET_A", matA.det() + "");

                    //setup matA0
                    for (int i = 0; i < 2; i++) {
                        for (int j = 0; j < 2; j++) {
                            vals0[i][j] = new ComplexNumber();
                            vals0[i][j].copyFrom(vals[i][j]);
                        }
                    }
                    for (int i = 0; i < 2; i++)
                        vals0[i][0].copyFrom(valC[i]);
                    matA0.setMatrix(vals0);
                    Log.w("DET_A0", matA0.det() + "");

                    //setup matA1
                    for (int i = 0; i < 2; i++) {
                        for (int j = 0; j < 2; j++) {
                            vals1[i][j] = new ComplexNumber();
                            vals1[i][j].copyFrom(vals[i][j]);
                        }
                    }

                    for (int i = 0; i < 2; i++)
                        vals1[i][1].copyFrom(valC[i]);
                    matA1.setMatrix(vals1);
                    Log.w("DET_A1", matA1.det() + "");


                    txt_ketqua.setVisibility(View.VISIBLE);
                    txt_result.setVisibility(View.VISIBLE);

                    if (matA.det().isZero()) txt_result.setText("Hệ phương trình không có nghiệm");
                    else {
                        txt_result.setText("b0 = " + matA0.det().divide(matA.det()).toString() + "\n" +
                                "b1 = " + matA1.det().divide(matA.det()));
                    }

                } catch (Exception ex) {
                    Log.e("ERROR", ex.toString());

                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("Lỗi");
                    builder.setMessage("Vui lòng nhập đầy đủ các ô và thử lại! (kể cả số 0)");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    builder.create().show();
                }

            }
        });
    }

    private void initFragmentSet33(View view) {
        final Button btn_solve = (Button) view.findViewById(R.id.btn_solve);
        final EditText[][] m = new EditText[3][3];
        final EditText[] c = new EditText[3];

        final TextView txt_ketqua = (TextView) view.findViewById(R.id.txt_ketqua);
        final TextView txt_result = (TextView) view.findViewById(R.id.txt_result);

        m[0][0] = (EditText) view.findViewById(R.id.m00);
        m[0][1] = (EditText) view.findViewById(R.id.m01);
        m[0][2] = (EditText) view.findViewById(R.id.m02);

        m[1][0] = (EditText) view.findViewById(R.id.m10);
        m[1][1] = (EditText) view.findViewById(R.id.m11);
        m[1][2] = (EditText) view.findViewById(R.id.m12);

        m[2][0] = (EditText) view.findViewById(R.id.m20);
        m[2][1] = (EditText) view.findViewById(R.id.m21);
        m[2][2] = (EditText) view.findViewById(R.id.m22);

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                m[i][j].setSingleLine(true);
            }

        c[0] = (EditText) view.findViewById(R.id.c0);
        c[1] = (EditText) view.findViewById(R.id.c1);
        c[2] = (EditText) view.findViewById(R.id.c2);

        btn_solve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(fragmentContainer.getWindowToken(), 0);

                try {
                    ComplexNumber[][] vals = new ComplexNumber[3][3];
                    ComplexNumber[][] vals0 = new ComplexNumber[3][3];
                    ComplexNumber[][] vals1 = new ComplexNumber[3][3];
                    ComplexNumber[][] vals2 = new ComplexNumber[3][3];

                    Matrix33 matA = new Matrix33();
                    Matrix33 matA0 = new Matrix33();
                    Matrix33 matA1 = new Matrix33();
                    Matrix33 matA2 = new Matrix33();

                    ComplexNumber[] valC = new ComplexNumber[3];

                    for (int i = 0; i < 3; i++)
                        valC[i] = ComplexNumber.parseCplx(c[i].getText().toString());

                    //setup matA
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            vals[i][j] = ComplexNumber.parseCplx(m[i][j].getText().toString());
                        }
                    }
                    matA.setMatrix(vals);
                    Log.w("DET_A", matA.det() + "");

                    //setup matA0
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            vals0[i][j] = new ComplexNumber();
                            vals0[i][j].copyFrom(vals[i][j]);
                        }
                    }
                    for (int i = 0; i < 3; i++)
                        vals0[i][0].copyFrom(valC[i]);
                    matA0.setMatrix(vals0);
                    Log.w("DET_A0", matA0.det() + "");

                    //setup matA1
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            vals1[i][j] = new ComplexNumber();
                            vals1[i][j].copyFrom(vals[i][j]);
                        }
                    }

                    for (int i = 0; i < 3; i++)
                        vals1[i][1].copyFrom(valC[i]);
                    matA1.setMatrix(vals1);
                    Log.w("DET_A1", matA1.det() + "");

                    //setup matA2
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            vals2[i][j] = new ComplexNumber();
                            vals2[i][j].copyFrom(vals[i][j]);
                        }
                    }

                    for (int i = 0; i < 3; i++)
                        vals2[i][2].copyFrom(valC[i]);
                    matA2.setMatrix(vals2);
                    Log.w("DET_A2", matA2.det() + "");

                    txt_ketqua.setVisibility(View.VISIBLE);
                    txt_result.setVisibility(View.VISIBLE);

                    if (matA.det().isZero()) txt_result.setText("Hệ phương trình không có nghiệm");
                    else {
                        txt_result.setText("b0 = " + matA0.det().divide(matA.det()).toString() + "\n" +
                                "b1 = " + matA1.det().divide(matA.det()) + "\n" +
                                "b2 = " + matA2.det().divide(matA.det()));
                    }

                } catch (Exception ex) {
                    Log.e("ERROR", ex.toString());

                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("Lỗi");
                    builder.setMessage("Vui lòng nhập đầy đủ các ô và thử lại! (kể cả số 0)");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    builder.create().show();
                }

            }
        });


    }

    /**
     * Called when a fragment will be displayed
     */
    public void willBeDisplayed() {
        // Do what you want here, for example animate the content
        if (fragmentContainer != null) {
            Animation fadeIn = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
            fragmentContainer.startAnimation(fadeIn);
        }
    }

    /**
     * Called when a fragment will be hidden
     */
    public void willBeHidden() {
        if (fragmentContainer != null) {
            Animation fadeOut = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_out);
            fragmentContainer.startAnimation(fadeOut);
        }
    }
}
