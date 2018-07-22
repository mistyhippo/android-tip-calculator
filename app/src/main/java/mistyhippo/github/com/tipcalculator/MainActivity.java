package mistyhippo.github.com.tipcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.etBillAmount)
    EditText etBillAmount;
    @BindView(R.id.tvTipPercent)
    TextView tvTipPercent;
    @BindView(R.id.tvTipAmount)
    TextView tvTipAmount;
    @BindView(R.id.tvBillTotalAmount)
    TextView tvBillTotalAmount;
    @BindView(R.id.tvBillAmount)
    TextView tvBillAmount;
    @BindView(R.id.vRatingButtons)
    View vRatingButtons;
    @BindView(R.id.ibRegularService)
    ImageButton ibRegularService;
    @BindView(R.id.ibGoodService)
    ImageButton ibGoodService;
    @BindView(R.id.ibExcellentService)
    ImageButton ibExcellentService;
    @BindView(R.id.rlRatingButtons)
    LinearLayout rlRatingButtons;
    @BindView(R.id.vRatingSeparator)
    View vRatingSeparator;
    @BindView(R.id.tvServiceRating)
    TextView tvServiceRating;
    @BindView(R.id.llTipInformation)
    LinearLayout llTipInformation;
    @BindView(R.id.vBillSeparator)
    View vBillSeparator;
    @BindView(R.id.tvBillTotal)
    TextView tvBillTotal;
    @BindView(R.id.activity_main)
    RelativeLayout activityMain;


    float percentage = 0;
    float tipTotal = 0;
    float finalBillAmount = 0;
    float totalBillAmount = 0;

    float DEFAULT_TIP_PERCENTAGE = 15;
    float REGULAR_TIP_PERCENTAGE = 10;
    float EXCELLENT_TIP_PERCENTAGE = 20;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setTipValues();
    }

    private void setTipValues() {

        tvTipPercent.setText(getString(R.string.main_msg_tippercent, percentage));
        tvTipAmount.setText(getString(R.string.main_msg_tiptotal, tipTotal));
        tvBillTotalAmount.setText(getString(R.string.main_msg_billtotalresult, finalBillAmount));
    }

    @OnClick({R.id.ibRegularService, R.id.ibGoodService, R.id.ibExcellentService})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ibRegularService:
                percentage = REGULAR_TIP_PERCENTAGE;
                break;
            case R.id.ibGoodService:
                percentage = DEFAULT_TIP_PERCENTAGE;
                break;
            case R.id.ibExcellentService:
                percentage = EXCELLENT_TIP_PERCENTAGE;
                break;
        }
        calculateFinalBill();
        setTipValues();
    }

    @OnTextChanged(R.id.etBillAmount)
    public void OnTextChanged() {
        calculateFinalBill();
        setTipValues();
    }

    private void calculateFinalBill() {

        if (percentage == 0)
            percentage = DEFAULT_TIP_PERCENTAGE;

        if (!etBillAmount.getText().toString().equals("") && !etBillAmount.getText().toString().equals("."))
            totalBillAmount = Float.valueOf(etBillAmount.getText().toString());
        else
            totalBillAmount = 0;

        tipTotal = (totalBillAmount * percentage) / 100;
        finalBillAmount = totalBillAmount + tipTotal;


    }


}


