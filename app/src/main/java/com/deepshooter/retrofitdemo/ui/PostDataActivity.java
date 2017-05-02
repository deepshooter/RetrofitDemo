package com.deepshooter.retrofitdemo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.deepshooter.retrofitdemo.R;
import com.deepshooter.retrofitdemo.customs.ConnectionDetector;
import com.deepshooter.retrofitdemo.customs.SnackBar;
import com.deepshooter.retrofitdemo.ui.forgotpassword.ForgotPassword;
import com.deepshooter.retrofitdemo.ui.getForgotPasswordResponse.GetForgotPasswordResponse;
import com.deepshooter.retrofitdemo.utils.OnResponseListner;
import com.deepshooter.retrofitdemo.utils.WebServices;

public class PostDataActivity extends AppCompatActivity implements OnResponseListner {


    TextView vT_postDataText ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_data);

        initializeView();
    }


    private void initializeView()
    {
        vT_postDataText = (TextView) findViewById(R.id.vT_postDataText);
        setValue();
    }

    private void setValue()
    {
        vT_postDataText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ForgotPassword forgotPassword=new ForgotPassword("ll@l.l");
                ConnectionDetector mDetector = new ConnectionDetector(PostDataActivity.this.getApplicationContext());
                if (mDetector.isConnectingToInternet()) {
                    setPassword(forgotPassword);
                }
                else {
                    SnackBar.makeText(PostDataActivity.this, getString(R.string.please_check_internet), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void onResponse(Object response, WebServices.ApiType URL, boolean isSucces) {

        if(URL== WebServices.ApiType.postForgotPassword){
            if (isSucces){
                GetForgotPasswordResponse getForgotPasswordResponse= (GetForgotPasswordResponse) response;
                if(getForgotPasswordResponse!=null){
                    if(getForgotPasswordResponse.getResponse()!=null){

                        SnackBar.makeText(PostDataActivity.this, getForgotPasswordResponse.getResponse().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    SnackBar.makeText(PostDataActivity.this, getString(R.string.no_data_available), Toast.LENGTH_SHORT).show();
                }

            }
            else{
                SnackBar.makeText(PostDataActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }

        }

    }





    private void setPassword(ForgotPassword forgotPassword) {
        WebServices<GetForgotPasswordResponse> getForgotPasswordResponseWebServices=new WebServices<>(PostDataActivity.this);
        getForgotPasswordResponseWebServices.forgotPassword(WebServices.PublicDevService, WebServices.ApiType.postForgotPassword,"en",forgotPassword);

    }
}
