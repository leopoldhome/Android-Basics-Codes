package cn.itcast.dialog;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void click1(View v){
    	//创建对话框builder
    	AlertDialog.Builder builder = new Builder(this);
    	builder.setIcon(android.R.drawable.ic_dialog_alert);
    	builder.setTitle("警告！");
    	builder.setMessage("欲练此功必先自宫，张yao你知道吗");
    	
    	//添加确定按钮
    	builder.setPositiveButton("确定", new OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(MainActivity.this, "感谢使用本软件，再见", 0).show();
				
			}
    	});
    	
    	//添加取消按钮
    	builder.setNegativeButton("取消", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(MainActivity.this, "若不自宫，一定不成功", 0).show();
				
			}
		});
    	
    	//创建对话框
    	AlertDialog ad = builder.create();
    	//显示对话框
    	ad.show();
    }
    
    public void click2(View v){
    	AlertDialog.Builder builder = new Builder(this);
    	builder.setTitle("请选择您的性别");
    	
    	final String[] items = {
    			"男",
    			"女",
    			"张光翟"
    	}; 
    	//添加选项
    	builder.setSingleChoiceItems(items, -1, new OnClickListener() {
			
    		//which:用户点击的选项的索引
    		//dialog:触发这个onClick方法的那个对话框
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(MainActivity.this, items[which], 0).show();
				//销毁对话框
				dialog.dismiss();
			}
		});
    	//显示对话框
    	builder.show();
    }
    
    public void click3(View v){
    	AlertDialog.Builder builder = new Builder(this);
    	builder.setTitle("请选择你认为长得帅的人");
    	
    	final String[] items = {
    			"侃哥",
    			"侃爷",
    			"赵帅哥",
    			"赵老师",
    	};
    	final boolean[] checkedItems = {
    			true,
    			false,
    			true,
    			false,
    	};
    	//设置多选选项
    	builder.setMultiChoiceItems(items, checkedItems, new OnMultiChoiceClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				checkedItems[which] = isChecked;
			}
		});
    	builder.setPositiveButton("确定", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				
				String text = "";
				for (int i = 0; i < checkedItems.length; i++) {
					text += checkedItems[i]? items[i] + "," : "";
				}
				Toast.makeText(MainActivity.this, text, 0).show();
				
			}
		});
    	builder.show();
    }
    
    public void click4(View v){
    	final ProgressDialog pd = new ProgressDialog(this);
    	//设置进度条的样式
    	pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
    	//设置进度条最大值
    	pd.setMax(100);
    	pd.setTitle("正在自宫中，请稍后");
    	Thread t = new Thread(){
    		@Override
    		public void run() {
    			try {
    				for (int i = 0; i <= 100; i++) {
						pd.setProgress(i);
    					sleep(50);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			pd.dismiss();
    		}
    	};
    	t.start();
    	pd.show();
    }
}
