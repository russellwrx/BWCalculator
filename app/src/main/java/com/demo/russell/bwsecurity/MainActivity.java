package com.demo.russell.bwsecurity;

import android.app.AlertDialog;
//import android.content.Context;
import android.content.DialogInterface;
//import android.content.SharedPreferences;
//import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
//import android.preference.PreferenceManager;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
//import android.view.Menu;
//import android.view.MenuItem;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
//import android.widget.Toast;
//import com.google.gson.Gson;

//import com.demo.russell.bwsecurity.ApplicationVariables;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.floor;
import static java.lang.Math.min;
import static java.lang.Math.round;

public class MainActivity extends AppCompatActivity {

    Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0,btnBS,btnDot,btnClear,btnMP,btnFloat,btnPlus,btnTest,btnInc,btnDec,btnReset,btnEXP;
    TextView txtInput,txtOutput,txtMinput,txtMarkup,txtCheques,txtPayout,txtJobs,txtMOH;
    TextView txt1k,txt50,txt20,txt10,txt5;
    String UserInput="",MarkupStr="";
    Double ActionTotal=0.0,ActionOutput=0.0,ActionOutputOrig=0.0;
    Double NumberOfJobs,ChequesTotal,PayoutTotal,MoneyOnHand,ExpenceTotal,FloatAmount=30550.0;
    Double notesTen,notesFifty,notesTwenty,notesThusdant,notesFive;
    ArrayList<Row> JobRows_array;
    TableLayout tl;
    private ApplicationVariables applicationVariables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.JobRows_array = new ArrayList<Row>();
        applicationVariables = new ApplicationVariables();



        txtInput = (TextView) findViewById(R.id.textViewInput);
        txtOutput = (TextView) findViewById(R.id.textViewOutput);
        txtMinput = (TextView) findViewById(R.id.textViewMultiInput);
        txtMarkup = (TextView) findViewById(R.id.textViewMarkup);
        txtJobs = (TextView) findViewById(R.id.textViewJobs);
        txtCheques = (TextView) findViewById(R.id.textViewCheques);
        txtPayout = (TextView) findViewById(R.id.textViewPayouts);
        txtMOH = (TextView) findViewById(R.id.textViewOnHand);
        txt1k = (TextView) findViewById(R.id.textView1k);
        txt50 = (TextView) findViewById(R.id.textView50);
        txt20 = (TextView) findViewById(R.id.textView20);
        txt10 = (TextView) findViewById(R.id.textView10);
        txt5 = (TextView) findViewById(R.id.textView5);



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
        btnTest = (Button) findViewById(R.id.btnTest);
        btnInc = (Button) findViewById(R.id.buttonInc);
        btnDec = (Button) findViewById(R.id.buttonDec);
        btnReset = (Button) findViewById(R.id.buttonReset);
        btnEXP = (Button) findViewById(R.id.buttonExp);
        btnFloat = (Button) findViewById(R.id.buttonFloat);



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
        btnTest.setOnClickListener(RussellClickLister);
        btnInc.setOnClickListener(RussellClickLister);
        btnDec.setOnClickListener(RussellClickLister);
        btnReset.setOnLongClickListener(AppReset);
        btnEXP.setOnClickListener(RussellClickLister);
        btnFloat.setOnClickListener(FloatAdjust);

        tl = (TableLayout) findViewById(R.id.tblLayout);
        btnInc.setEnabled(false);
        btnDec.setEnabled(false);

        JobRows_array = applicationVariables.getData(this);
        FloatAmount = applicationVariables.getVariables(this);
        RefreshScreen();
    }

    private View.OnClickListener FloatAdjust = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder
                        .setTitle("Adjust Starting Float")
                        .setMessage("Set New Float Value: "+UserInput+"550")
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FloatAmount = Double.parseDouble(UserInput) * 1000 + 550;
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
                case R.id.btnTest:
                    RunTest();
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
        txtOutput.setText("Total: " + ActionOutput);
    }

    private void RunTest(){

        JobRows_array = applicationVariables.getData(this);
        RefreshScreen();
    }

    private void AddExpRow(){
        if (Double.parseDouble(UserInput)!=0){
            Row currentRow = new Row();
            currentRow.setExpence(Double.parseDouble(UserInput));
            JobRows_array.add(currentRow);
            NotesCount();
            applicationVariables.saveData(this, JobRows_array,FloatAmount);
            RefreshScreen();
        }
    }

    private void AddRow() {

        //Log.d("test: ",ActionOutput+" "+ActionTotal);
        if ((ActionTotal+Double.parseDouble(UserInput))!=0) {
            //Log.d("test: ",ActionOutput+"");
            Row currentRow = new Row();
            currentRow.setAmount(ActionTotal + Double.parseDouble(UserInput));
            currentRow.setPayout(ActionOutput);
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
        tl.removeAllViews();
        for ( Row row : JobRows_array ){
            TableRow tr = new TableRow(this);

            tr.setClickable(true);
            tr.setOnClickListener(new View.OnClickListener() {   //Create delete option for each row
                @Override
                public void onClick(final View v) {
                    v.setBackgroundColor(Color.RED);

                    final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext(),AlertDialog.THEME_HOLO_DARK);
                    builder
                            .setTitle("Delete Row: " + (v.getId()+1))
                            .setMessage("Are you sure?")
                            //.setView()
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    //Log.d("BWSEC TABLE", "Yes"); //Delete Row
                                    tl.removeView(v);
                                    JobRows_array.remove(v.getId());
                                    NotesCount();
                                    applicationVariables.saveData(v.getContext(), JobRows_array,FloatAmount);
                                    CalculateTotals();

                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    v.setBackgroundColor(Color.GREEN);
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
            lp.setMargins(10, 12, 10, 12);

            //int tcolor = Color.BLUE;



            tr.setId(count);
            if (!row.getWarinig())
                if (row.getExpence()!=0)
                    tr.setBackgroundColor(Color.BLUE);
                else
                tr.setBackgroundColor(Color.GREEN);
            else
                tr.setBackgroundColor(Color.YELLOW);
            //tr.setGravity(Gravity.RIGHT);
            tr.setLayoutParams(lp);

            TextView labelTV = new TextView(this);
            labelTV.setId(10 + count);
            labelTV.setText(String.format("$%8.2f", row.getAmount()));
            labelTV.setTextColor(Color.BLACK);
            labelTV.setGravity(Gravity.RIGHT);
            labelTV.setTextSize(16);
            labelTV.setLayoutParams(lp);

            tr.addView(labelTV);


            TextView valueTV = new TextView(this);
            valueTV.setId(20 + count);
            //valueTV.setText(String.format("$%8.2f", row.getPayout()));
            if (row.getExpence()!=0)
                valueTV.setText("$ "+row.getExpence());
            else
                valueTV.setText("$ "+row.getPayoutString());
            valueTV.setTextSize(16);
            valueTV.setTextColor(Color.BLACK);
            valueTV.setGravity(Gravity.RIGHT);


            lp.setMargins(20, 5, 20, 5);
            valueTV.setLayoutParams(lp);
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
        MarkupStr = "";

        txtInput.setText(UserInput);
        txtOutput.setText("Payout: -");
        txtMarkup.setText("-");
        txtMinput.setText("");

        CalculateTotals();
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
            if (MarkupStr.equals(""))
                MarkupStr += UserInput;
            else
                MarkupStr += "<font color='#EE0000'>+</font>"+ UserInput;
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
        double minput;

        if (UserInput.equals("0"))
            markup = MarkupStr; //    minput = String.valueOf(ActionTotal);
        else if (MarkupStr.equals(""))
            markup = UserInput;
         else
            markup = MarkupStr + "<font color='#EE0000'>+</font>" + UserInput;

        minput = ActionTotal + Double.parseDouble(UserInput);

        txtInput.setText(UserInput);
        txtOutput.setText(Html.fromHtml("Payout: $" + "<font color='#EE0000'><b>"+Output(UserInput, ActionTotal).intValue()+"</b></font>"));
        txtMarkup.setText(Html.fromHtml(markup));
        txtMinput.setText("Total: $" + minput);

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
                note5+=1;
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
        txt1k.setText("1000\n" + notesThusdant.intValue());
        txt50.setText("50\n"+notesFifty.intValue());
        txt20.setText("20\n"+notesTwenty.intValue());
        txt10.setText("10\n" + notesTen.intValue());
        txt5.setText("5\n"+notesFive.intValue());


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
            value = value * .96 - 60;
         else
            value = value * .95 - 60;
        value = round(value / 5) * 5;

        if ((comparevalue-value)<90)
            ActionOutput = comparevalue-90.0;
        else
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
            ChequesTotal+=row.getAmount();
            PayoutTotal+=row.getPayout();
            ExpenceTotal+=row.getExpence();
            if (row.getExpence()==0)
                NumberOfJobs+=1;
        }
        txtCheques.setText("Cheques\n"+ChequesTotal);
        txtPayout.setText("Payout\n"+PayoutTotal.intValue());
        txtJobs.setText("Jobs\n"+NumberOfJobs.intValue());
        txtMOH.setText("On Hand\n"+(FloatAmount-PayoutTotal-ExpenceTotal));
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
