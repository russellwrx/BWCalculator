package com.demo.russell.bwsecurity;

import android.app.AlertDialog;
import android.content.Context;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.renderscript.ScriptGroup;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Gravity;
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
import android.widget.Toast;
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

    Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0,btnBS,btnDot,btnClear,btnMP,btnFloat,btnPlus,btnInc,btnDec,btnReset,btnEXP,btnInfo;
    TextView txtInput,txtOutput,txtMinput,txtMarkup,txtCheques,txtPayout,txtJobs,txtMOH,txtExp,txtFloat;
    TextView txt1k,txt50,txt20,txt10,txt5;
    String UserInput="",MarkupStr="";
    Double ActionTotal=0.0,ActionOutput=0.0,ActionOutputOrig=0.0;
    Double NumberOfJobs,ChequesTotal,PayoutTotal,MoneyOnHand,ExpenceTotal,FloatAmount=30550.0;
    Double notesTen,notesFifty,notesTwenty,notesThusdant,notesFive;
    ArrayList<Row> JobRows_array;
    private ApplicationVariables applicationVariables;

    //drawer
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private String[] mPlanetTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.JobRows_array = new ArrayList<Row>();
        applicationVariables = new ApplicationVariables();



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
        btnInfo = (Button) findViewById(R.id.buttonInfo);

        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

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
        btnInfo.setOnClickListener(AppInfo);
        btnInfo.setOnLongClickListener(AppTest);


        btnInc.setEnabled(false);
        btnDec.setEnabled(false);

        JobRows_array = applicationVariables.getData(this);
        FloatAmount = applicationVariables.getVariables(this);
        RefreshScreen();



    }



    private  View.OnLongClickListener AppTest = (new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            JobRows_array = applicationVariables.getData(v.getContext());
            RefreshScreen();
            return false;
        }
    });



    private View.OnClickListener AppInfo = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            final Dialog dialog = new Dialog(MainActivity.this);
//            Window window = dialog.getWindow();
            dialog.setTitle("");

//            TextView title = new TextView(v.getContext());
//            title.setText("Summary");
//            title.setBackgroundColor(Color.BLACK);
//            title.setPadding(10, 10, 10, 10);
//            title.setGravity(Gravity.CENTER);
//            title.setTextColor(Color.WHITE);
//
//            dialog.setTitle(title);

            dialog.setContentView(R.layout.application_summary_dialog);
//            window.setLayout(900,900);
            dialog.show();

            TextView startAmount = (TextView)dialog.findViewById(R.id.textViewStarting);
            TextView numofJobs = (TextView)dialog.findViewById(R.id.textViewNumberOfJobs);
            TextView cheques = (TextView)dialog.findViewById(R.id.textViewCheques);
            TextView payout = (TextView)dialog.findViewById(R.id.textViewPayouts);
            TextView expences = (TextView)dialog.findViewById(R.id.textViewExpence);
            TextView moneyofhand = (TextView)dialog.findViewById(R.id.textViewMoneyOnHand);
            TextView summary = (TextView)dialog.findViewById(R.id.textViewSummary);



            Button btnOK = (Button)dialog.findViewById(R.id.buttonOK);

            btnOK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.cancel();
                }
            });


            DecimalFormat formatterdbl = new DecimalFormat("$#,##0.00");
            DecimalFormat formatterint = new DecimalFormat("$#,###");


            startAmount.setText(formatterint.format(FloatAmount));
            numofJobs.setText(NumberOfJobs.intValue()+"");
            cheques.setText(formatterdbl.format(ChequesTotal));
            payout.setText(formatterint.format(PayoutTotal));
            expences.setText(formatterdbl.format(ExpenceTotal));
            moneyofhand.setText(formatterint.format(MoneyOnHand));

            Double result = 0.0;

            result = substruct_double(ChequesTotal,PayoutTotal);
            result = substruct_double(result,ExpenceTotal);

            summary.setText(formatterdbl.format(result));
        }
    };


    private void drawerList(){
        ArrayList mlist = new ArrayList();
        DecimalFormat formatterdbl = new DecimalFormat("$#,##0.00");
        DecimalFormat formatterint = new DecimalFormat("$#,###");


        mlist.add("Float Amount\n"+formatterint.format(FloatAmount));
        mlist.add("Jobs\n"+NumberOfJobs.intValue() + "");
        mlist.add("Cheques Amount\n"+formatterdbl.format(ChequesTotal));
        mlist.add("Payout Amount\n"+formatterint.format(PayoutTotal));
        mlist.add("Expenses Amount\n"+formatterdbl.format(ExpenceTotal));
        mlist.add("Money On Hand\n"+formatterint.format(MoneyOnHand));


        mPlanetTitle = getResources().getStringArray(R.array.planets_array);
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,R.layout.drawer_list_item, mlist));

    };


    private View.OnClickListener FloatAdjust = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {

                if (UserInput!="0") {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder
                            .setTitle("Adjust Starting Float")
                            .setMessage("Set New Float Value: " + UserInput + "550")
                            .setIcon(android.R.drawable.ic_dialog_info)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    FloatAmount = Double.parseDouble(UserInput) * 1000 + 550;
                                    applicationVariables.saveData(v.getContext(), JobRows_array, FloatAmount);
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
        }
    };



    private View.OnLongClickListener AppReset = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(final View v) {
            if (v.getId()==R.id.buttonReset){
                final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder
                        .setTitle("Reset Application")
                        .setMessage("Are you sure?")
                        .setIcon(android.R.drawable.ic_delete)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                JobRows_array.clear();
                                FloatAmount=30550.0;
                                applicationVariables.saveData(v.getContext(), JobRows_array,FloatAmount);
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
                default:
                    break;
            }
        }
    };


    private void AdjustOutput(String action){
        if (action.equals("inc")){
            ActionOutput+=5.0;
        } else {
            ActionOutput-=5.0;
        }
        //txtOutput.setText(Html.fromHtml("Payout: $" + "<font color='#EE0000'><b>"+Output(UserInput, ActionTotal).intValue()+"</b></font>"));

        String outstring;

        if (ActionOutput.intValue()!=ActionOutputOrig.intValue())
            outstring="<b>"+ActionOutput+"</b>";
        else
            outstring="<font color='#EE0000'>"+ActionOutput+"</fonts>";
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
            applicationVariables.saveData(this, JobRows_array,FloatAmount);
            RefreshScreen();
        }
    }

    private void AddRow() {

        //Log.d("test: ",ActionOutput+" "+ActionTotal);
        if ((ActionTotal+Double.parseDouble(UserInput))!=0 && ActionOutput>0) {
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
            applicationVariables.saveData(this, JobRows_array,FloatAmount);
            RefreshScreen();
        }
    }


    private void RefreshScreen(){

        int count = 0;
        final ArrayList trcount = new ArrayList();
        final TableLayout tl = (TableLayout) findViewById(R.id.tblLayout);

        tl.removeAllViews();

        for ( Row row : JobRows_array ){
            final TableRow tr = new TableRow(this);

            trcount.add(count);
            tr.setClickable(true);
            tr.setOnClickListener(new View.OnClickListener() {   //Create delete option for each row
                @Override
                public void onClick(final View v) {
                    v.setBackgroundColor(Color.RED);

                    final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext(),AlertDialog.THEME_HOLO_DARK);
                    builder
                            .setTitle("Delete Row: " + (trcount.indexOf(v.getId())+1))
                            .setMessage("Are you sure?")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    //Log.d("BWSEC TABLE", "" + trcount + " " + trcount.indexOf(v.getId())); //Delete Row
                                    tl.removeView(v);
                                    JobRows_array.remove(trcount.indexOf(v.getId()));
                                    trcount.remove(trcount.indexOf(v.getId()));
                                    NotesCount();
                                    applicationVariables.saveData(v.getContext(), JobRows_array,FloatAmount);
                                    CalculateTotals();

                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    v.setBackgroundColor(Color.parseColor("#A6FFEF"));
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

            tr.setId(count);
            if (!row.getWarinig())
                if (row.getExpence()!=0)
                    tr.setBackgroundColor(Color.parseColor("#FFFBCA")); //Darkerblue
                else
                tr.setBackgroundColor(Color.parseColor("#A6FFEF"));  //Blue
            else
                tr.setBackgroundColor(Color.parseColor("#52FFE0"));  //Yellow

            lp.setMargins(0, 0, 50, 0);
            tr.setLayoutParams(lp);

            TextView labelTV = new TextView(this);
            labelTV.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.MATCH_PARENT));

            labelTV.setId(10 + count);
            labelTV.setText(String.format("$%8.2f", row.getAmount()));
            labelTV.setTextColor(Color.BLACK);
            labelTV.setGravity(Gravity.RIGHT);
            labelTV.setTextSize(16);

            //lp.setMargins(50, 50, 60, 50);
            //labelTV.setLayoutParams(lp);
            tr.addView(labelTV);

            //valueTV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));


            TextView valueTV = new TextView(this);
            valueTV.setId(20 + count);
            if (row.getExpence()!=0)
                valueTV.setText("$ "+row.getExpence());
            else
                valueTV.setText("$ "+row.getPayoutString());
            valueTV.setTextColor(Color.BLACK);
            valueTV.setGravity(Gravity.RIGHT);
            valueTV.setTextSize(16);
            lp.setMargins(60, 0, 0, 0);
            valueTV.setLayoutParams(lp);


            tr.setGravity(Gravity.CENTER);
            tr.addView(valueTV);




            // Add the TableRow to the TableLayout
            tl.addView(tr, new TableRow.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            count++;
        }
        NotesCount();
        UserInput = "0";
        ActionTotal = 0.0;
        ActionOutput = 0.0;
        MarkupStr = "";

        input_section_refresh("0","0","","");
        CalculateTotals();
        drawerList();
    }



    private void CalculatorInput(String CalcInput){
        if (CalcInput.equals("BS")) {
            if (UserInput.length()>1 && UserInput!="0")
                UserInput = UserInput.substring(0, UserInput.length() - 1);
            else
                UserInput = "0";
        } else if (CalcInput.equals(".")){
            if (!UserInput.contains("."))
                UserInput = UserInput + CalcInput;
        } else if (CalcInput.equals("C")) {
            UserInput = "0";
            ActionTotal = 0.0;
            MarkupStr = "";
        }
        else if (CalcInput.equals("MP")) {
            ActionTotal += Double.parseDouble(UserInput);
            MarkupStr +=UserInput+"<br><font color='#167BFF'>+</font>";
            UserInput = "0";
        }
        else {
            if (UserInput.equals("0"))
                UserInput = CalcInput;

            else {
                if (!UserInput.contains("."))   //Check if input has decimal point
                    UserInput = UserInput + CalcInput;
                else {
                    if (UserInput.length()-UserInput.indexOf(".")<3)  // Accept only two digits after decimal point
                        UserInput = UserInput + CalcInput;
                }

            }
        }

        //String tmp = "<font color=#cc0029>Total: $</font><font color=#ffcc00>zweite Farbe</font>";


        String markup;
        Double minput;

        if (UserInput.equals("0")) {
            markup = MarkupStr; //    minput = String.valueOf(ActionTotal);}
        }
            else if (MarkupStr.equals(""))
            markup = UserInput;
         else
            markup = MarkupStr + UserInput;

        minput = ActionTotal + Double.parseDouble(UserInput);

        input_section_refresh(UserInput, Output(UserInput, ActionTotal).toString(),markup,minput.toString());


    }

    private void input_section_refresh(String s_input, String s_output, String s_markup, String s_total){


        if (Double.parseDouble(s_output)>0)
            s_output="<font color=#cc0029>"+s_output+"</font>";
        else
            s_output="";

        if (ActionTotal==0){
            s_markup="";
//            s_total="";
        } else
            s_markup=s_markup+"<br>------<br>Sum: "+s_total;

        txtInput.setText(s_input);
        txtOutput.setText(Html.fromHtml(s_output));
        txtMarkup.setText(Html.fromHtml(s_markup));
//        txtMinput.setText(Html.fromHtml(s_total));
    }

    private void NotesCount(){


        this.notesThusdant=30.0;
        this.notesFifty =0.0;
        this.notesTwenty=20.0;
        this.notesTen=10.0;
        this.notesFive=10.0;



        double note1k,note50,note20,note10,note5;


        this.notesThusdant=floor(FloatAmount/1000);

        for ( Row row : JobRows_array ){
            double payout;
            if (row.getExpence()!=0.0)
                payout = row.getExpence();
            else
                payout = row.getPayout();

            note1k=floor(payout/1000);
            note50=floor((payout-(note1k*1000))/50);
            note20=floor((payout-(note1k*1000)-(note50*50))/20);
            note10=floor((payout-(note1k*1000)-(note50*50)-(note20*20))/10);
            note5=floor((payout-(note1k*1000)-(note50*50)-(note20*20)-(note10*10))/5);

            if ((note1k*1000+note50*50+note20*20+note10*10+note5*5)!=payout)
                if (note5==0)
                    note5+=1;
                else {
                    note10+=1;
                    note5=0;
                }
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
        //
        if (value < 100.0) {
            btnInc.setEnabled(false);
            btnDec.setEnabled(false);
            return 0.0;
        }
        if (value > 6000)
            value = (value * .96) - 60;
         else
            value = (value * .95) - 60;
        value = round(value / 5) * 5;

        if ((comparevalue-value)<90)
            value = floor((comparevalue - 90.0) / 5)*5;
        Log.d("Debug: ", ""+ceil((comparevalue - 90.0) / 5));
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





        for (Row row : JobRows_array){
            ChequesTotal=add_double(ChequesTotal,row.getAmount());
            PayoutTotal+=row.getPayout();
            ExpenceTotal=add_double(ExpenceTotal,row.getExpence());
            if (row.getExpence()==0)
                NumberOfJobs+=1;
        }

        MoneyOnHand=FloatAmount-PayoutTotal-ExpenceTotal;

        //markup = MarkupStr + "<font color='#EE0000'>+</font>" + UserInput;
        String strjobs,stramount,strfloat,strpayout,strexpences,strmoneyonhand;
        String numcolor = "'#0F1340'";

       // "+numcolor+"

        strfloat = "Float<br><font color="+numcolor+"><b>"+FloatAmount+"</b></font>";
        strjobs = "Jobs<br><font color="+numcolor+"><b>"+NumberOfJobs.intValue()+"</b></font>";
        stramount = "Cheques<br><font color="+numcolor+"><b>"+ChequesTotal+"</b></font>";
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
