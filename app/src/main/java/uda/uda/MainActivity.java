package uda.uda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tencent.mm.sdk.modelmsg.GetMessageFromWX;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXTextObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import java.util.UUID;

import wxapi.WXEntryActivity;

public class MainActivity extends AppCompatActivity {

    private Button test;
    private Button test2;
    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        regToWx();
        test = (Button) findViewById(R.id.test);
        test2 = (Button) findViewById(R.id.test2);
        bundle = getIntent().getExtras();
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WXTextObject textObject = new WXTextObject();
                textObject.text = "测试微信";

                WXMediaMessage msg = new WXMediaMessage();
                msg.mediaObject = textObject;
                msg.description = "测试微信";

                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.transaction = String.valueOf(System.currentTimeMillis());
                req.message = msg;
                iwxapi.sendReq(req);
            }
        });

        test2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Intent intent = new Intent(MainActivity.this, WXEntryActivity.class);

                final SendAuth.Req req = new SendAuth.Req();
                //Final SendAuth.Req req = new SendAuth.Req();
                req.scope = "snsapi_userinfo";
                req.state = UUID.randomUUID().toString();
                iwxapi.sendReq(req);
               /* WXTextObject textObject = new WXTextObject();
                textObject.text = "resp";

                WXMediaMessage msg = new WXMediaMessage();
                msg.description = "resp";

                GetMessageFromWX.Resp resp = new GetMessageFromWX.Resp();
               // resp.transaction = new GetMessageFromWX.Req(bundle).transaction;
                resp.message = msg;
                iwxapi.sendResp(resp);*/
            }
        });



    }

    private static final String APP_ID="wx2c4f36ce0b07313";
    private IWXAPI iwxapi;
    private void regToWx(){

        iwxapi = WXAPIFactory.createWXAPI(this,APP_ID,true);
        iwxapi.registerApp(APP_ID);

    }




}
