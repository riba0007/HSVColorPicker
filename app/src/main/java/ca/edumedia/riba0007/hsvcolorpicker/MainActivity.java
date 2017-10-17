package ca.edumedia.riba0007.hsvcolorpicker;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

import model.HSVModel;

/**
 * HSV Color Picker for Android - MAD9132 Midterm
 *
 * Main Controller
 * @author Priscila Ribas da Costa (riba0007@algonquinlive.com)
 * @version 1.0
 */
public class MainActivity extends Activity implements Observer, SeekBar.OnSeekBarChangeListener {

    private AboutDialogFragment mAboutDialog;
    private HSVModel mHSVModel;
    private TextView mColorSwatch;

    private SeekBar mHueSB;
    private SeekBar mSaturationSB;
    private SeekBar mValueSB;

    private TextView mHueTV;
    private TextView mSaturationTV;
    private TextView mValueTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize aboutDialog
        mAboutDialog = new AboutDialogFragment();

        //Initialize model
        mHSVModel = new HSVModel();
        mHSVModel.addObserver(this);

        //Initialize variables
        mColorSwatch = (TextView) findViewById(R.id.colorSwatch);
        mHueSB = (SeekBar) findViewById(R.id.hueSB);
        mSaturationSB = (SeekBar) findViewById(R.id.saturationSB);
        mValueSB = (SeekBar) findViewById(R.id.valueSB);
        mHueTV = (TextView) findViewById(R.id.hue);
        mSaturationTV = (TextView) findViewById(R.id.saturation);
        mValueTV = (TextView) findViewById(R.id.value);

        //Event listeners
        mHueSB.setOnSeekBarChangeListener(this);
        mSaturationSB.setOnSeekBarChangeListener(this);
        mValueSB.setOnSeekBarChangeListener(this);

        //Long click listener
        mColorSwatch.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                Toast.makeText( getApplicationContext(), getHSVMessage(), Toast.LENGTH_SHORT ).show();
                return true;
            }
        });

        //update view
        this.updateView();

    }

    //return a formatted message with hue, saturation and value
    private String getHSVMessage(){
        return getResources().getString(
                R.string.hsv_values, mHSVModel.getHue(),
                mHSVModel.getSaturation(),
                mHSVModel.getValue());
    }

    //update view ColorSwatch with model HSV value
    private void updateColorSwatch() {
       float[] hsvColor = {mHSVModel.getHue(), mHSVModel.getSaturation()/100.f, mHSVModel.getValue()/100.f};
       mColorSwatch.setBackgroundColor(Color.HSVToColor(hsvColor));
    }

    //update view HueSB with model Hue value
    private void updateHueSB(){
        mHueSB.setProgress( mHSVModel.getHue() );
    }

    //update view SaturationSB with model Saturation value
    private void updateSaturationSB(){
        mSaturationSB.setProgress( mHSVModel.getSaturation() );
    }

    //update view ValueSB with model Value (Brightness) value
    private void updateValueSB(){
        mValueSB.setProgress( mHSVModel.getValue() );
    }

    //update all view components with model values
    public void updateView() {
        this.updateColorSwatch();
        this.updateHueSB();
        this.updateSaturationSB();
        this.updateValueSB();
    }


    //MENU METHODS IMPLEMENTATION

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        if(item.getItemId() == R.id.action_about){
            mAboutDialog.show( getFragmentManager(), "About" );
            return true;
        }

        return false;
    }


    //OBSERVER METHODS IMPLEMENTATION

    @Override
    public void update(Observable observable, Object o) {
        this.updateView();
    }


    //SEEK BAR METHODS IMPLEMENTATION

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

        //If came from user return
        if ( !b ) {
            return;
        }

        // - Change model with input value.
        // - Update Label with current value.
        switch ( seekBar.getId() ) {
            case R.id.hueSB:
                mHSVModel.setHue(mHueSB.getProgress());
                mHueTV.setText(getResources().getString(R.string.hue_progress, i).toUpperCase());
                break;

            case R.id.saturationSB:
                mHSVModel.setSaturation(mSaturationSB.getProgress());
                mSaturationTV.setText(getResources().getString(R.string.saturation_progress, i).toUpperCase());
                break;

            case R.id.valueSB:
                mHSVModel.setValue(mValueSB.getProgress());
                mValueTV.setText(getResources().getString(R.string.value_progress, i).toUpperCase());
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

        //Change text back to original value
        switch (seekBar.getId()) {
            case R.id.hueSB:
                mHueTV.setText(getResources().getString(R.string.hue));
                break;
            case R.id.saturationSB:
                mSaturationTV.setText(getResources().getString(R.string.saturation));
                break;
            case R.id.valueSB:
                mValueTV.setText(getResources().getString(R.string.value));
                break;
        }
    }


    //BUTTON METHODS IMPLEMENTATION

    public void onColorButtonClick(View view) {
        switch (view.getId()){
            case R.id.blackButton:
                mHSVModel.asBlack();
                break;
            case R.id.redButton:
                mHSVModel.asRed();
                break;
            case R.id.limeButton:
                mHSVModel.asLime();
                break;
            case R.id.blueButton:
                mHSVModel.asBlue();
                break;
            case R.id.yellowButton:
                mHSVModel.asYellow();
                break;
            case R.id.cyanButton:
                mHSVModel.asCyan();
                break;
            case R.id.magentaButton:
                mHSVModel.asMagenta();
                break;
            case R.id.silverButton:
                mHSVModel.asSilver();
                break;
            case R.id.grayButton:
                mHSVModel.asGray();
                break;
            case R.id.maroonButton:
                mHSVModel.asMaroon();
                break;
            case R.id.oliveButton:
                mHSVModel.asOlive();
                break;
            case R.id.greenButton:
                mHSVModel.asGreen();
                break;
            case R.id.purpleButton:
                mHSVModel.asPurple();
                break;
            case R.id.tealButton:
                mHSVModel.asTeal();
                break;
            case  R.id.navyButton:
                mHSVModel.asNavy();
                break;
        }

        Toast.makeText( getApplicationContext(), getHSVMessage(), Toast.LENGTH_SHORT ).show();

    }


    //ACTIVITY METHODS IMPLEMENTATION

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        int[] hsvColor = { mHSVModel.getHue(), mHSVModel.getSaturation(), mHSVModel.getValue() };
        savedInstanceState.putIntArray("HSV_COLOR", hsvColor);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
        if (savedInstanceState != null && savedInstanceState.containsKey("HSV_COLOR")){
            int[] hsvColor = savedInstanceState.getIntArray("HSV_COLOR");

            if (hsvColor != null && hsvColor.length == 3) {
                mHSVModel.setHSV(hsvColor[0],hsvColor[1],hsvColor[2]);
            }
        }
    }
}
