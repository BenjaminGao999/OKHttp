package com.adnonstop.okhttp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.adnonstop.okhttp.R;
import com.adnonstop.okhttp.http.OkHttpManager;
import com.adnonstop.okhttp.http.OkHttpUICallback;
import com.adnonstop.okhttp.module.Person;
import com.adnonstop.okhttp.module.Student;
import com.adnonstop.okhttp.module.TestData;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;

public class MainActivity extends AppCompatActivity {
    private String url = "http://192.168.3.91:8888/services/mission/searchMissionInstanceGroupByDay";
    private String postJson = "{\"size\":20,\"page\":1,\"appName\":\"testName\",\"receiverId\":111}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        fastJsonPractice();

//        genericTest();

//        instancesOfTest();

        parameterizedTypeTest();
    }

    private void parameterizedTypeTest() {
        try {
            OkHttpManager.getInstance().postAsyncJson(url, postJson, new OkHttpUICallback.ResultCallback<TestData>() {


                @Override
                public void onSuccess(TestData result) {
                    Toast.makeText(MainActivity.this, result.toString(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(Call call, IOException e) {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void instancesOfTest() {
        Student yang = new Student("yang", 2);
        boolean b = yang instanceof Person;
        Toast.makeText(this, "" + b, Toast.LENGTH_SHORT).show();
    }

    private <T> void genericTest() {
        Student[] students = new Student[2];
        students[0] = new Student("ming", 1);
        students[1] = new Student("hua", 2);
        T[] ts;

        ts = (T[]) students;

        T t = ts[0];

        String s = t.toString();


        Toast.makeText(this, "" + s, Toast.LENGTH_SHORT).show();

        ArrayList<Student>[] lists = new ArrayList[2];
        ArrayList<Student> list = new ArrayList<>();
        list.add(students[0]);
        list.add(students[0]);
        lists[0] = list;
        list = new ArrayList<>();
        list.add(students[0]);
        list.add(students[0]);
        lists[1] = list;

        ArrayList<Student[]> list1 = new ArrayList<>();

        MainActivity[] mainActivities = new MainActivity[1];

        boolean[] booleens = new boolean[1];


    }

    private void fastJsonPractice() {

        Model model = new Model();
        model.id = 1001;
        model.name = "gaotie";

        // {"id":1001,"name":"gaotie"}
        String text_normal = JSON.toJSONString(model);

        // [1001,"gaotie"]
        String text_beanToArray = JSON.toJSONString(model, SerializerFeature.BeanToArray);

// support beanToArray & normal mode
//        JSON.parseObject(text_beanToArray, Feature.SupportArrayToBean);
//        JSONObject jsonObject = JSON.parseObject(text_normal, Feature.SupportArrayToBean);
//        int id = jsonObject.getIntValue("id");
//        String name = jsonObject.getString("name");
//        Toast.makeText(this, "id = " + id + ",name = " + name, Toast.LENGTH_SHORT).show();

//        JSONArray array = JSON.parseArray(text_beanToArray);
//        JSONArray array = JSON.parseArray(text_beanToArray);
//        int intValue = array.getIntValue(0);
//        String string = array.getString(1);
//        Toast.makeText(this, "intValue = " + intValue + ",string = " + string, Toast.LENGTH_SHORT).show();


    }

    class Model {
        public int id;
        public String name;
    }

}
