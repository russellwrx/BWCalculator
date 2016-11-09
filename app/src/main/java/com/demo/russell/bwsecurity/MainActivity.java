package com.demo.russell.bwsecurity;

import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Debug;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.renderscript.ScriptGroup;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
//import android.widget.Toast;
//import com.google.gson.Gson;

//import com.demo.russell.bwsecurity.ApplicationVariables;


import junit.framework.Test;

import java.io.IOException;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.ceil;
import static java.lang.Math.floor;
import static java.lang.Math.min;
import static java.lang.Math.round;

public class MainActivity extends AppCompatActivity {

    Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0,btnBS,btnDot,btnClear,btnMP,btnFloat,btnPlus,btnInc,btnDec,btnReset,btnEXP,btnSetting;
    TextView txtInput,txtOutput,txtMinput,txtMarkup,txtCheques,txtPayout,txtJobs,txtMOH,txtExp,txtFloat;
    TextView txt1k,txt50,txt20,txt10,txt5;
    String UserInput="",MarkupStr="",PayoutInput="";
    Double ActionTotal=0.0,ActionOutput=0.0,ActionOutputOrig=0.0;
    Boolean PayoutChange=false;
    Double NumberOfJobs,ChequesTotal,PayoutTotal,MoneyOnHand,ExpenceTotal;
    Double notesTen,notesFifty,notesTwenty,notesThusdant,notesFive;
    ArrayList<Row> JobRows_array;
    AppStoredValus appVariables;
    private ApplicationVariables applicationVariables;

    //drawer
//    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    ArrayList<SummaryItem> rowitem;
    UserAdapter adapter;

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sPref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        appVariables.setNotes1k(Integer.parseInt(sPref.getString("thousands","30000")));
        appVariables.setNotes20(Integer.parseInt(sPref.getString("twenty","20")));
        appVariables.setNotes10(Integer.parseInt(sPref.getString("ten","10")));
        appVariables.setNotes5(Integer.parseInt(sPref.getString("five","10")));
        RefreshScreen();
//        Toast toast = Toast.makeText(getApplicationContext(), "ON Create",Toast.LENGTH_SHORT);
//        toast.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.JobRows_array = new ArrayList<Row>();  // Job Row Class
        this.appVariables = new AppStoredValus();  // Variables Class
        applicationVariables = new ApplicationVariables();   //Store-Restore Values



        txtInput = (TextView) findViewById(R.id.textViewInput);
        txtOutput = (TextView) findViewById(R.id.textViewOutput);
        txtMarkup = (TextView) findViewById(R.id.textViewMarkup);
        txtJobs = (TextView) findViewById(R.id.textViewJobs);
        txtCheques = (TextView) findViewById(R.id.textViewCheques);
        txtPayout = (TextView) findViewById(R.id.textViewPayouts);
        txtExp = (TextView) findViewById(R.id.textViewExpence);
        txtMOH = (TextView) findViewById(R.id.textViewOnHand);
        txtFloat = (TextView) findViewById(R.id.textViewFloat);

        txt1k = (TextView) findViewById(R.id.textView1k);
        txt50 = (TextView) findViewById(R.id.textView50);
        txt20 = (TextView) findViewById(R.id.textView20);
        txt10 = (TextView) findViewById(R.id.textView10);
        txt5 = (TextView) findViewById(R.id.textView5);

        txtMarkup.setMovementMethod(new ScrollingMovementMethod());


        btn0 = (Button) findViewById(R.id.button0);
        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);
        btn4 = (Button) findViewById(R.id.button4);
        btn5 = (Button) findViewById(R.id.button5);
        btn6 = (Button) findViewById(R.id.button6);
        btn7 = (Button) findViewById(R.id.button7);
        btn8 = (Button) findViewById(R.id.button8);
        btn9 = (Button) findViewById(R.id.button9);
        btnBS = (Button) findViewById(R.id.buttonBS);
        btnDot = (Button) findViewById(R.id.buttonDot);
        btnClear = (Button) findViewById(R.id.buttonClear);
        btnMP = (Button) findViewById(R.id.buttonMultiPlus);
        btnPlus = (Button) findViewById(R.id.buttonPlus);
        //btnTest = (Button) findViewById(R.id.btnTest);
        btnInc = (Button) findViewById(R.id.buttonInc);
        btnDec = (Button) findViewById(R.id.buttonDec);
        btnReset = (Button) findViewById(R.id.buttonReset);
        btnEXP = (Button) findViewById(R.id.buttonExp);
        btnFloat = (Button) findViewById(R.id.buttonFloat);
        btnSetting = (Button) findViewById(R.id.buttonSetting);

        btn0.setOnClickListener(RussellClickLister);
        btn1.setOnClickListener(RussellClickLister);
        btn2.setOnClickListener(RussellClickLister);
        btn3.setOnClickListener(RussellClickLister);
        btn4.setOnClickListener(RussellClickLister);
        btn5.setOnClickListener(RussellClickLister);
        btn6.setOnClickListener(RussellClickLister);
        btn7.setOnClickListener(RussellClickLister);
        btn8.setOnClickListener(RussellClickLister);
        btn9.setOnClickListener(RussellClickLister);
        btnBS.setOnClickListener(RussellClickLister);
        btnDot.setOnClickListener(RussellClickLister);
        btnClear.setOnClickListener(RussellClickLister);
        btnMP.setOnClickListener(RussellClickLister);
        btnPlus.setOnClickListener(RussellClickLister);
        btnInc.setOnClickListener(RussellClickLister);
        btnDec.setOnClickListener(RussellClickLister);
        btnReset.setOnLongClickListener(AppReset);
        btnReset.setOnClickListener(RussellClickLister);
        btnEXP.setOnClickListener(RussellClickLister);
        btnFloat.setOnClickListener(FloatAdjust);
        txtOutput.setOnLongClickListener(ChangePayoutBig);

        btnInc.setEnabled(false);
        btnDec.setEnabled(false);


            JobRows_array = applicationVariables.getData(this);   // Restore Rows
            appVariables = applicationVariables.getDataVariables(this);  //Restore Variables

        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        rowitem = new ArrayList<SummaryItem>();
        adapter = new UserAdapter(this, rowitem);

        mDrawerList.setAdapter(adapter);

        RefreshScreen();

    }


    private void drawerList(){

//        Toast toast = Toast.makeText(getApplicationContext(), "Long Press Reset",Toast.LENGTH_SHORT);
//        toast.show();

        DecimalFormat formatterdbl = new DecimalFormat("$#,##0.00");
        DecimalFormat formatterint = new DecimalFormat("$#,###");
        Double result;

        result = substruct_double(ChequesTotal,PayoutTotal);
        result = substruct_double(result,ExpenceTotal);

        SummaryItem dfloat = new SummaryItem("Float",formatterint.format(appVariables.getFloatAmout()));
        SummaryItem djobs = new SummaryItem("Jobs",NumberOfJobs.intValue() + "");
        SummaryItem dcheque = new SummaryItem("Cheques",formatterdbl.format(ChequesTotal));
        SummaryItem dpay = new SummaryItem("Payout",formatterint.format(PayoutTotal));
        SummaryItem dexpens = new SummaryItem("Expenses",formatterdbl.format(ExpenceTotal));
        SummaryItem dmoh = new SummaryItem("Money On Hand",formatterdbl.format(MoneyOnHand));
        SummaryItem dprofit = new SummaryItem("--------",formatterdbl.format(result));

        adapter.clear();
        adapter.add(dfloat);
        adapter.add(djobs);
        adapter.add(dcheque);
        adapter.add(dpay);
        adapter.add(dexpens);
        adapter.add(dmoh);
        adapter.add(dprofit);

    };

    private  View.OnLongClickListener ChangePayoutBig = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {
//            Toast toast = Toast.makeText(getApplicationContext(), "Long Press",Toast.LENGTH_SHORT);
//            toast.show();
            String outstring;
            PayoutChange=true;
            PayoutInput = txtOutput.getText().toString();
            outstring="<font color='red'>"+Double.valueOf(ActionOutput).intValue()+"</fonts>";
            txtOutput.setText(Html.fromHtml(outstring));
            return false;
        }
    };

    private View.OnClickListener FloatAdjust = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {

            startActivity(new Intent(MainActivity.this, Preferences.class));
        }
    };



    private View.OnLongClickListener AppReset = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(final View v) {
            if (v.getId()==R.id.buttonReset){
                final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext(),AlertDialog.THEME_HOLO_DARK);
                TextView dialogText = new TextView(v.getContext());
                dialogText.setText("Reset?");
                dialogText.setGravity(Gravity.CENTER);
                dialogText.setTextColor(Color.WHITE);
                builder
                        .setTitle("Application Rest")
                        .setView(dialogText)
                        .setMessage("")
                        .setIcon(android.R.drawable.ic_delete)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                JobRows_array.clear();
                                //FloatAmount=30550.0;
                                SharedPreferences sPref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

                                appVariables.setNotes1k(Integer.parseInt(sPref.getString("thousands","30000")));
                                appVariables.setNotes20(Integer.parseInt(sPref.getString("twenty","20")));
                                appVariables.setNotes10(Integer.parseInt(sPref.getString("ten","10")));
                                appVariables.setNotes5(Integer.parseInt(sPref.getString("five","10")));
                                //appVariables.setNotes1k(30);
                                //appVariables.setNotes20(20);
                                //appVariables.setNotes10(10);
                                //appVariables.setNotes5(10);
                                applicationVariables.saveData(v.getContext(), JobRows_array,appVariables);
                                RefreshScreen();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .show();
            }
            return false;
        }
    };

    private View.OnClickListener RussellClickLister = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.button0:
                    CalculatorInput("0");
                    break;
                case R.id.button1:
                    CalculatorInput("1");
                    break;
                case R.id.button2:
                    CalculatorInput("2");
                    break;
                case R.id.button3:
                    CalculatorInput("3");
                    break;
                case R.id.button4:
                    CalculatorInput("4");
                    break;
                case R.id.button5:
                    CalculatorInput("5");
                    break;
                case R.id.button6:
                    CalculatorInput("6");
                    break;
                case R.id.button7:
                    CalculatorInput("7");
                    break;
                case R.id.button8:
                    CalculatorInput("8");
                    break;
                case R.id.button9:
                    CalculatorInput("9");
                    break;
                case R.id.buttonBS:
                    CalculatorInput("BS");
                    break;
                case R.id.buttonDot:
                    CalculatorInput(".");
                    break;
                case R.id.buttonClear:
                    CalculatorInput("C");
                    break;
                case R.id.buttonMultiPlus:
                    CalculatorInput("MP");
                    break;
                case R.id.buttonPlus:
                    AddRow();
                    break;
                case R.id.buttonReset:
                    Toast toast = Toast.makeText(getApplicationContext(), "Long Press Reset",Toast.LENGTH_SHORT);
                    toast.show();
                    break;
                case R.id.buttonInc:
                    AdjustOutput("inc");
                    break;
                case R.id.buttonDec:
                    AdjustOutput("dec");
                    break;
                case R.id.buttonExp:
                    AddExpRow();
                    break;
                case R.id.buttonSetting:
//                    setContentView(R.layout.float_adjust);
                    break;
                default:
                    break;
            }
        }
    };


    private void AdjustOutput(String action){
        if (action.equals("inc")){
            ActionOutput+=5.0;
        } else if (action.equals("dec")){
            ActionOutput-=5.0;
        }

        //txtOutput.setText(Html.fromHtml("Payout: $" + "<font color='#EE0000'><b>"+Output(UserInput, ActionTotal).intValue()+"</b></font>"));

        String outstring;

        if (ActionOutput.intValue()!=ActionOutputOrig.intValue())
            outstring="<font color='red'>"+Double.valueOf(ActionOutput).intValue()+"</fonts>";
        else
            outstring="<font color='yellow'>"+Double.valueOf(ActionOutput).intValue()+"</fonts>";
        txtOutput.setText(Html.fromHtml(outstring));
    }



    private void AddExpRow(){
        if (Double.parseDouble(UserInput)!=0){
            Row currentRow = new Row();
            currentRow.setExpence(Double.parseDouble(UserInput));
            currentRow.setAmount(0.0);
            currentRow.setPayout(0.0);
            JobRows_array.add(currentRow);
            NotesCount();
            applicationVariables.saveData(this, JobRows_array,appVariables);
            RefreshScreen();
        }
    }

    private void AddRow() {

        //Log.d("test: ",ActionOutput+" "+ActionTotal);
        if ((ActionTotal+Double.parseDouble(UserInput))!=0 && (ActionOutput>0 || PayoutChange)) {
            //Log.d("test: ",ActionOutput+"");
            Row currentRow = new Row();
            currentRow.setAmount(ActionTotal + Double.parseDouble(UserInput));
            currentRow.setPayout(ActionOutput);
            currentRow.setExpence(0.0);
            //Log.d("Russell", ActionOutput + " " + ActionOutputOrig);
            if (ActionOutput.intValue()!=ActionOutputOrig.intValue())
                currentRow.setWarning(true);
            else
                currentRow.setWarning(false);
            JobRows_array.add(currentRow);
            NotesCount();
            applicationVariables.saveData(this, JobRows_array,appVariables);
            RefreshScreen();
        }
    }


    private void RefreshScreen(){

        int count = 0;
        final ArrayList trcount = new ArrayList();
        final TableLayout tl = (TableLayout) findViewById(R.id.tblLayout);

        tl.removeAllViews();

        for ( final Row row : JobRows_array ){
            final TableRow tr = new TableRow(this);

            trcount.add(count);
            tr.setClickable(true);
            tr.setOnClickListener(new View.OnClickListener() {   //Create delete option for each row
                @Override
                public void onClick(final View v) {
                    v.setBackgroundColor(Color.RED);
                    DecimalFormat formatterdbl = new DecimalFormat("$#,##0.00");
                    final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext(),AlertDialog.THEME_HOLO_DARK);
                    //final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    TextView dialogMSG = new TextView(v.getContext());
                    String msgStr;
                    if (row.getExpence()==0)
                        msgStr = "\nDetails: $ " + formatterdbl.format(row.getAmount()) + " - $ " + row.getPayout();
                    else
                        msgStr = "\nExpense: $ " + row.getExpence();
                    msgStr += "\nDELETE?\n";
                    dialogMSG.setText(msgStr);
                    dialogMSG.setGravity(Gravity.CENTER);
                    dialogMSG.setTextColor(Color.WHITE);
                    //dialogMSG.setBackgroundColor(Color.BLACK);
                    builder

                            .setView(dialogMSG)
                            .setTitle("Delete Row: " + (trcount.indexOf(v.getId())+1))
                            //.setMessage("")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    //Log.d("BWSEC TABLE", "" + trcount + " " + trcount.indexOf(v.getId())); //Delete Row
                                    tl.removeView(v);
                                    JobRows_array.remove(trcount.indexOf(v.getId()));
                                    trcount.remove(trcount.indexOf(v.getId()));
                                    NotesCount();
                                    applicationVariables.saveData(v.getContext(), JobRows_array, appVariables);
                                    CalculateTotals();
                                    drawerList();

                                }
                            })
                            .setOnCancelListener(new DialogInterface.OnCancelListener() {
                                @Override
                                public void onCancel(DialogInterface dialog) {
                                    v.setBackgroundColor(ContextCompat.getColor(v.getContext(),R.color.mainbackground));
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    v.setBackgroundColor(ContextCompat.getColor(v.getContext(),R.color.mainbackground));
                                }
                            })
                            .show();

                }
            });

//            CharSequence text = String.valueOf(row.getAmount());
//            Toast toast = Toast.makeText(context, text, duration);
//            toast.show();
//

            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.MATCH_PARENT);


            Integer rowColor = 0;
            tr.setId(count);
            Double warningDiff=0.0;

            if (!row.getWarinig())
                if (row.getExpence()!=0)
                    rowColor = (getResources().getColor(R.color.gridExpense)); //Darkerblue
                else
                    rowColor = (getResources().getColor(R.color.gridNormal));  //Blue
            else {

                warningDiff = Output("0",row.getAmount())-row.getPayout();
//                rowColor = (getResources().getColor(R.color.gridWarning));  //Yellow
                rowColor = (getResources().getColor(R.color.gridNormal));  //Yellow
            }
            tr.setBackgroundColor(ContextCompat.getColor(this,R.color.mainbackground));

            //=============================

            TextView labelTV = new TextView(this);
//            labelTV.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT));
//            lp.setMargins(20,0,0,0); // left top right bottom

            labelTV.setId(10 + count);
            labelTV.setText(String.format("$%8.2f", row.getAmount()));
            labelTV.setTextColor(rowColor);
            labelTV.setTextSize(14);
            lp.weight=4;

            labelTV.setGravity(Gravity.RIGHT);


            labelTV.setLayoutParams(lp);
            tr.addView(labelTV,lp);

            //===================

            lp.setMargins(0,0,0,0);
            lp.weight=3;
            TextView valueTV = new TextView(this);
            valueTV.setId(20 + count);
            if (row.getExpence()!=0)
                valueTV.setText("$ "+row.getExpence());
            else
                valueTV.setText("$ "+row.getPayoutString());

            valueTV.setTextColor(rowColor);
            valueTV.setGravity(Gravity.RIGHT);
            valueTV.setTextSize(14);
 //           valueTV.setLayoutParams(lp);
            tr.addView(valueTV,lp);

            //===================

            TextView valueWar = new TextView(this);
            valueWar.setId(20 + count);

//            if (row.getExpence()!=0)
//                valueWar.setText("$ "+row.getExpence());
//            else
//                valueWar.setText("$ "+row.getPayoutString());
            if (warningDiff>0.0)
//                valueWar.setText(Html.fromHtml(warningDiff.intValue()+" "+"<font color='#ffff00'>"+"\u25bc"+"</font>"));
                valueWar.setText(Html.fromHtml("<font color='#ffff00'>"+warningDiff.intValue()+"</font>"));
            else if (warningDiff==0.0)
                valueWar.setText(Html.fromHtml("<font color='#00ff50'>"+"\u2713"+"</font>"));
//                valueWar.setText(Html.fromHtml("<font color='#00ff50'>"+warningDiff.intValue()+"</font>"));
            else
//                valueWar.setText(Html.fromHtml(warningDiff.intValue()+" "+"<font color='#ff2a00'>"+"\u25b2"+"</font>"));
                valueWar.setText(Html.fromHtml("<font color='#ff3b00'>"+warningDiff.intValue()+"</font>"));
//            valueWar.setText("\u25bc");
            valueWar.setTextColor(rowColor);
            valueWar.setGravity(Gravity.CENTER_VERTICAL);
            valueWar.setGravity(Gravity.CENTER);
            valueWar.setTextSize(10);

            lp.weight=1;
            valueWar.setLayoutParams(lp);

            tr.setGravity(Gravity.CENTER);
            tr.addView(valueWar,lp);

            //=====================



            // Add the TableRow to the TableLayout
//            TableRow.LayoutParams lptr = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.MATCH_PARENT);
//            lptr.setMargins(0,100,0,0);
            tl.addView(tr,new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.MATCH_PARENT));

            count++;
        }
        NotesCount();
        UserInput = "0";
        ActionTotal = 0.0;
        ActionOutput = 0.0;
        MarkupStr = "";
        PayoutInput = "";
        PayoutChange = false;

        input_section_refresh("0","0","",0.0);
        CalculateTotals();
        drawerList();
    }



    private void CalculatorInput(String CalcInput){
        if (PayoutChange) {
            if (CalcInput.equals("BS")) {
                if (PayoutInput.length()>1 && !PayoutInput.equals("0"))
                    PayoutInput = PayoutInput.substring(0,PayoutInput.length()-1);
                else
                    PayoutInput = "0";
            } else if (CalcInput.equals("C")){
                UserInput = "0";
                ActionTotal = 0.0;
                MarkupStr = "";
                ActionOutput = 0.0;
                PayoutChange = false;
                PayoutInput = "0.0";
                input_section_refresh("0","0","",0.0);
                return;
            } else if (!CalcInput.equals(".") && !CalcInput.equals("MP")){
                PayoutInput = PayoutInput + CalcInput;
            }
            ActionOutput = Double.parseDouble(PayoutInput);
            AdjustOutput("Update");
        } else {
            if (CalcInput.equals("BS")) {
                if (UserInput.length() > 1 && !UserInput.equals("0"))
                    UserInput = UserInput.substring(0, UserInput.length() - 1);
                else
                    UserInput = "0";
            } else if (CalcInput.equals(".")) {
                if (!UserInput.contains("."))
                    UserInput = UserInput + CalcInput;
            } else if (CalcInput.equals("C")) {
                UserInput = "0";
                ActionTotal = 0.0;
                MarkupStr = "";
            } else if (CalcInput.equals("MP")) {
                ActionTotal += Double.parseDouble(UserInput);
                MarkupStr += UserInput + "<br><font color='#167BFF'>+</font>";
//              MarkupStr +=UserInput+"<br><font color='#FFFFFF'>+</font>";
                UserInput = "0";
            } else {
                if (UserInput.equals("0"))
                    UserInput = CalcInput;

                else {
                    if (!UserInput.contains(".")) {   //Check if input has decimal point
                        if (UserInput.length() < 5)
                            UserInput = UserInput + CalcInput;
                    } else {
                        if (UserInput.length() - UserInput.indexOf(".") < 3)  // Accept only two digits after decimal point
                            UserInput = UserInput + CalcInput;

                    }

                }
            }


            //String tmp = "<font color=#cc0029>Total: $</font><font color=#ffcc00>zweite Farbe</font>";


            String markup;
            Double minput;


            if (UserInput.equals("0")) {
                markup = MarkupStr; //    minput = String.valueOf(ActionTotal);}
            } else if (MarkupStr.equals(""))
                markup = UserInput;
            else
                markup = MarkupStr + UserInput;

            minput = ActionTotal + Double.parseDouble(UserInput);

//          input_section_refresh(UserInput, Output(UserInput, ActionTotal).toString(), markup, minput.toString());

            input_section_refresh(UserInput, Output(UserInput, ActionTotal).toString(), markup, minput);
        }
    }

    private void input_section_refresh(String s_input, String s_output, String s_markup, Double s_total){

//        String outputColor;
        DecimalFormat formatterdbl = new DecimalFormat("$#,##0.00");
//        DecimalFormat formatterdbl0 = new DecimalFormat("$#,##0.");
        Double OutputToDouble = Double.parseDouble(s_output);


        if (OutputToDouble>0)
            s_output="<font color='yellow'>"+Double.valueOf(OutputToDouble).intValue()+"</font>";
        else
            s_output="";

        if (ActionTotal==0){
            s_markup="";
//            s_total="";
        } else
            s_markup=s_markup+"<br>------<br>Sum: "+formatterdbl.format(s_total);
        if (s_input.indexOf(".") > 0  && s_input.indexOf(".") < s_input.length()-1)
            s_input = s_input.split("\\.")[0]+"."+"<font color='grey'><small><small>"+s_input.split("\\.")[1] + "</small></small></font>";
//        Log.d("RRR:",s_input.indexOf(".")+ " " + s_input.length());


        txtInput.setText(Html.fromHtml(s_input));
        txtOutput.setText(Html.fromHtml(s_output));


        txtMarkup.setText(Html.fromHtml(s_markup));
//        txtMinput.setText(Html.fromHtml(s_total));
    }

    private void NotesCount(){


        this.notesThusdant=appVariables.getNotes1k().doubleValue();
        this.notesFifty =0.0;
        this.notesTwenty=appVariables.getNotes20().doubleValue();
        this.notesTen=appVariables.getNotes10().doubleValue();
        this.notesFive=appVariables.getNotes5().doubleValue();



        double note1k,note50,note20,note10,note5;


    //    this.notesThusdant=floor(FloatAmount/1000);

        for ( Row row : JobRows_array ){
            double payout;
            if (row.getExpence()!=0.0)
                payout = ceil(row.getExpence()/5)*5;
            else
                payout = row.getPayout();

            note1k=floor(payout/1000);

            note50=floor((payout-(note1k*1000))/50);
            note20=floor((payout-(note1k*1000)-(note50*50))/20);
            note10=floor((payout-(note1k*1000)-(note50*50)-(note20*20))/10);
            note5=floor((payout-(note1k*1000)-(note50*50)-(note20*20)-(note10*10))/5);

            notesThusdant-=note1k;
            notesFifty-=note50;
            notesTwenty-=note20;
            notesTen-=note10;
            notesFive-=note5;

            if (notesFifty<0){
                notesFifty=20+notesFifty;
                notesThusdant-=1;
            }
        }

        String s1,s2,s3,s4,s5;


        s1 = "$1K<br><b>" + notesThusdant.intValue()+"<b>";
        s2 = "$50<br><b>"+notesFifty.intValue()+"<b>";
        s3 = "$20<br><b>"+notesTwenty.intValue()+"<b>";
        s4 = "$10<br><b>"+notesTen.intValue()+"<b>";
        s5 = "$5<br><b>"+notesFive.intValue()+"<b>";

        txt1k.setText(Html.fromHtml(s1));
        txt50.setText(Html.fromHtml(s2));
        txt20.setText(Html.fromHtml(s3));
        txt10.setText(Html.fromHtml(s4));
        txt5.setText(Html.fromHtml(s5));


    }

    private Double Output(String CalcInput, Double actionTotal){

        double value = Double.parseDouble(CalcInput)+actionTotal;
        double comparevalue = value;

        SharedPreferences sPref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        Integer range1 = Integer.parseInt(sPref.getString("rangeAmount1","6000"));
        Integer range2 = Integer.parseInt(sPref.getString("rangeAmount2","15000"));

        Integer rate1 = Integer.parseInt(sPref.getString("rate1","5"));
        Integer rate2 = Integer.parseInt(sPref.getString("rate2","4"));
        Integer rate3 = Integer.parseInt(sPref.getString("rate3","3"));




        //
        if (value < 100.0) {
            btnInc.setEnabled(false);
            btnDec.setEnabled(false);
            ActionOutput=0.0;
            return 0.0;
        }


        if (value>=range2) // more then 14000 at 3%
            value = ((value * (100-rate3)/100))-60;
        else if (value>=range1) // more then 12000
            value = ((value * (100-rate2)/100))-60;
        else
            value = ((value * (100-rate1)/100))-60;

        value = round(value / 5) * 5;

        if ((comparevalue-value)<90)
            value = floor((comparevalue - 90.0) / 5)*5;
//        Log.d("Debug: ", ""+ceil((comparevalue - 90.0) / 5));
        ActionOutput = value;
        btnInc.setEnabled(true);
        btnDec.setEnabled(true);
        ActionOutputOrig=ActionOutput;
        return ActionOutput;
    }

    private void CalculateTotals(){
        NumberOfJobs=0.0;
        ChequesTotal=0.0;
        PayoutTotal=0.0;
        MoneyOnHand=0.0;
        ExpenceTotal=0.0;
        DecimalFormat formatterdbl = new DecimalFormat("$#,##0.00");






        for (Row row : JobRows_array){
            ChequesTotal=add_double(ChequesTotal,row.getAmount());
            PayoutTotal+=row.getPayout();
            ExpenceTotal=add_double(ExpenceTotal,row.getExpence());
            if (row.getExpence()==0)
                NumberOfJobs+=1;
        }

        MoneyOnHand=appVariables.getFloatAmout()-PayoutTotal-ExpenceTotal;

        //markup = MarkupStr + "<font color='#EE0000'>+</font>" + UserInput;
        String strjobs,stramount,strfloat,strpayout,strexpences,strmoneyonhand;
//        String numcolor = "'#0F1340'";
        String numcolor = "'#000000'";
       // "+numcolor+"

        strfloat = "Float<br><font color="+numcolor+"><b>"+appVariables.getFloatAmout()+"</b></font>";
        strjobs = "Jobs<br><font color="+numcolor+"><b>"+NumberOfJobs.intValue()+"</b></font>";
        stramount = "Cheques<br><font color="+numcolor+"><b>"+formatterdbl.format(ChequesTotal)+"</b></font>";
        strpayout = "Payout<br><font color="+numcolor+"><b>"+PayoutTotal.intValue()+"</b></font>";
        strexpences = "Expences<br><font color="+numcolor+"><b>"+ExpenceTotal+"</b></font>";
        strmoneyonhand = "On Hand<br><font color="+numcolor+"><b>"+MoneyOnHand+"</b></font>";


        txtFloat.setText(Html.fromHtml(strfloat));
        txtCheques.setText(Html.fromHtml(stramount));
        txtPayout.setText(Html.fromHtml(strpayout));
        txtJobs.setText(Html.fromHtml(strjobs));
        txtMOH.setText(Html.fromHtml(strmoneyonhand));
        txtExp.setText(Html.fromHtml(strexpences));
    }

    private Double add_double(Double val1, Double val2){
        BigDecimal bval1 = new BigDecimal(val1.toString());
        BigDecimal bval2 = new BigDecimal(val2.toString());
        BigDecimal calculation = bval1.add(bval2);
        return calculation.doubleValue();
    }
    private Double substruct_double(Double val1, Double val2){
        BigDecimal bval1 = new BigDecimal(val1.toString());
        BigDecimal bval2 = new BigDecimal(val2.toString());
        BigDecimal calculation = bval1.subtract(bval2);
        return calculation.doubleValue();
    }

}
