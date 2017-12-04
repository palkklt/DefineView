package me.stefan.easybehavior;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.PaintDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.stefan.easybehavior.demo1.Adapter.FirstClassAdapter;
import me.stefan.easybehavior.demo1.Adapter.SecondClassAdapter;
import me.stefan.easybehavior.demo1.ScreenUtils;
import me.stefan.easybehavior.demo1.behavior.AppBarLayoutOverScrollViewBehavior;
import me.stefan.easybehavior.demo1.model.FirstClassItem;
import me.stefan.easybehavior.demo1.model.SecondClassItem;
import me.stefan.easybehavior.demo1.widget.NoScrollViewPager;
import me.stefan.easybehavior.demo1.widget.RoundProgressBar;
import me.stefan.easybehavior.fragment.ItemFragment;
import me.stefan.easybehavior.fragment.MyFragmentPagerAdapter;
import me.stefan.easybehavior.fragment.TestOtherValue;
import me.stefan.easybehavior.fragment.analog_quantity;
import me.stefan.easybehavior.fragment.dummy.TabEntity;
import me.stefan.easybehavior.widget.CircleImageView;

/**
 * @author stefan 邮箱：648701906@qq.com
 */
public class Demo1Activity extends AppCompatActivity {

    private ImageView mZoomIv;
    private Toolbar mToolBar;
    private ViewGroup titleContainer;
    private AppBarLayout mAppBarLayout;
    private ViewGroup titleCenterLayout;
    private RoundProgressBar progressBar;
    private ImageView mSettingIv, mMsgIv;
    private CircleImageView mAvater;
    private CommonTabLayout mTablayout;
    private NoScrollViewPager mViewPager;

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private List<Fragment> fragments;
    private int lastState = 1;

    private TextView mainTab1TV;
    private TextView mainTab2TV;
    private TextView mainTab3TV;
    private TextView mainTab4TV;
    private TextView mainTab5TV;
    private TextView mainTab6TV;

    //6个一级选项，主信息选项
    private List<FirstClassItem> firstList;
    private List<FirstClassItem> firstList2;
    private List<FirstClassItem> firstList3;
    private List<FirstClassItem> firstList4;
    private List<FirstClassItem> firstList5;
    private List<FirstClassItem> firstList6;

    private List<SecondClassItem> secondList;
    private List<SecondClassItem> secondList2;

    //6个一级选项点击以后对应弹出6个popupWindow
    private PopupWindow popupWindow;
    private PopupWindow popupWindow2;
    private PopupWindow popupWindow3;
    private PopupWindow popupWindow4;
    private PopupWindow popupWindow5;
    private PopupWindow popupWindow6;
    private ListView leftLV, rightLV;


    private void init(){
        findView();
        initData();
        initPopup();
        initData2();
        initPopup2();
        initData3();
        initPopup3();
        initData4();
        initPopup4();
        initData5();
        initPopup5();
        initData6();
        initPopup6();

        OnClickListenerImpl l = new OnClickListenerImpl();
        mainTab1TV.setOnClickListener(l);
        mainTab2TV.setOnClickListener(l);
        mainTab3TV.setOnClickListener(l);
        mainTab4TV.setOnClickListener(l);
        mainTab5TV.setOnClickListener(l);
        mainTab6TV.setOnClickListener(l);
    }
    class OnClickListenerImpl implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.func_order:
                    tab1OnClick();
                    break;
                case R.id.func_integral:
                    tab2OnClick();
                    break;
                case R.id.func_payment:
                    tab3OnClick();
                    break;
                case R.id.func_invite:
                    tab4OnClick();
                    break;
                case R.id.main_tab5:
                    tab5OnClick();
                    break;
                case R.id.main_tab6:
                    tab6OnClick();
                    break;
                default:
                    break;
            }
        }
    }

    private void tab1OnClick() {
        mainTab1TV.setBackgroundColor(Color.GRAY);
        if (popupWindow.isShowing()) {
            popupWindow.dismiss();
        } else {
            popupWindow.showAsDropDown(findViewById(R.id.func_order));
            popupWindow.setAnimationStyle(-1);
        }
    }
    private void tab2OnClick(){
        mainTab2TV.setBackgroundColor(Color.GRAY);
        if (popupWindow2.isShowing()){
            popupWindow2.dismiss();;
        }else{
            popupWindow2.showAsDropDown(findViewById(R.id.func_integral));
            popupWindow2.setAnimationStyle(-1);
        }
    }
    private void tab3OnClick() {
        mainTab3TV.setBackgroundColor(Color.GRAY);
        if (popupWindow3.isShowing()) {
            popupWindow3.dismiss();
        } else {
            popupWindow3.showAsDropDown(findViewById(R.id.func_payment));
            popupWindow3.setAnimationStyle(-1);
        }
    }
    private void tab4OnClick(){
        mainTab4TV.setBackgroundColor(Color.GRAY);
        if (popupWindow4.isShowing()){
            popupWindow4.dismiss();
        }else{
            popupWindow4.showAsDropDown(findViewById(R.id.func_invite));
            popupWindow4.setAnimationStyle(-1);
        }
    }
    private void tab5OnClick(){
        mainTab5TV.setBackgroundColor(Color.GRAY);
        if (popupWindow5.isShowing()){
            popupWindow5.dismiss();
        }else{
            popupWindow5.showAsDropDown(findViewById(R.id.main_tab5));
            popupWindow5.setAnimationStyle(-1);
        }
    }
    private void tab6OnClick(){
        mainTab6TV.setBackgroundColor(Color.GRAY);
        if (popupWindow6.isShowing()){
            popupWindow6.dismiss();
        }else{
            popupWindow6.showAsDropDown(findViewById(R.id.main_tab6));
            popupWindow6.setAnimationStyle(-1);
        }
    }
    private void findView(){
        mainTab1TV = (TextView) findViewById(R.id.func_order);
        mainTab2TV = (TextView) findViewById(R.id.func_integral);
        mainTab3TV = (TextView) findViewById(R.id.func_payment);
        mainTab4TV = (TextView) findViewById(R.id.func_invite);
        mainTab5TV = (TextView) findViewById(R.id.main_tab5);
        mainTab6TV = (TextView) findViewById(R.id.main_tab6);
    }
    private void initData() {
        firstList = new ArrayList<FirstClassItem>();
        //1
        ArrayList<SecondClassItem> secondList1 = new ArrayList<SecondClassItem>();
        secondList1.add(new SecondClassItem(101, "模拟量"));
        secondList1.add(new SecondClassItem(102, "开关量"));
        secondList1.add(new SecondClassItem(103, "SV状态"));
        secondList1.add(new SecondClassItem(104, "GOOSE状态"));
        secondList1.add(new SecondClassItem(105, "状态监测"));
        secondList1.add(new SecondClassItem(106, "通道信息"));
        secondList1.add(new SecondClassItem(107, "告警信息"));
        secondList1.add(new SecondClassItem(108, "保护功能状态"));
        firstList.add(new FirstClassItem(1, "保护状态", secondList1));
        //2
        ArrayList<SecondClassItem> secondList2 = new ArrayList<SecondClassItem>();
        secondList2.add(new SecondClassItem(201, "设备参数定值"));
        secondList2.add(new SecondClassItem(202, "保护定值"));
        firstList.add(new FirstClassItem(2, "查看定值", secondList2));
        //3
        ArrayList<SecondClassItem> secondList3 = new ArrayList<SecondClassItem>();
        secondList3.add(new SecondClassItem(301, "功能压板"));
        secondList3.add(new SecondClassItem(302, "SV接收软压板"));
        secondList3.add(new SecondClassItem(303, "间隔接收软压板"));
        firstList.add(new FirstClassItem(3, "压板状态", secondList3));
        //4
        firstList.add(new FirstClassItem(4, "版本信息", new ArrayList<SecondClassItem>()));
        //5
        ArrayList<SecondClassItem> secondList5 = new ArrayList<SecondClassItem>();
        secondList5.add(new SecondClassItem(501, "对时方式"));
        secondList5.add(new SecondClassItem(502, "通信参数"));
        firstList.add(new FirstClassItem(5, "装置设置", secondList5));
    }
    private void initData2() {
        firstList2 = new ArrayList<FirstClassItem>();
        //1
        ArrayList<SecondClassItem> secondList1 = new ArrayList<SecondClassItem>();
        secondList1.add(new SecondClassItem(101, "功能软压板"));
        secondList1.add(new SecondClassItem(102, "SV接受软压板"));
        secondList1.add(new SecondClassItem(103, "间隔接受软压板"));
        secondList1.add(new SecondClassItem(104, "GOOSE发送软压板"));
        secondList1.add(new SecondClassItem(105, "GOOSE接受软压板"));
        secondList1.add(new SecondClassItem(106, "隔离刀闸强制软压板"));
        firstList2.add(new FirstClassItem(1, "压板投退", secondList1));
        //2
        firstList2.add(new FirstClassItem(2, "切换定值区", new ArrayList<SecondClassItem>()));
        //3
        firstList2.add(new FirstClassItem(3, "复归指示灯", new ArrayList<SecondClassItem>()));
    }

    private void initData3() {
        firstList3 = new ArrayList<FirstClassItem>();
        //1
        firstList3.add(new FirstClassItem(1, "动作报告", new ArrayList<SecondClassItem>()));
        //2
        firstList3.add(new FirstClassItem(2, "告警报告", new ArrayList<SecondClassItem>()));
        //3
        firstList3.add(new FirstClassItem(3, "变位报告", new ArrayList<SecondClassItem>()));
        //4
        firstList3.add(new FirstClassItem(4, "操作报告", new ArrayList<SecondClassItem>()));
    }

    private void initData4() {
        firstList4 = new ArrayList<FirstClassItem>();
        firstList4.add(new FirstClassItem(1, "设备参数定值", new ArrayList<SecondClassItem>()));
        firstList4.add(new FirstClassItem(2, "保护定值", new ArrayList<SecondClassItem>()));
        firstList4.add(new FirstClassItem(3, "分区复制", new ArrayList<SecondClassItem>()));
    }

    private void initData5() {
        firstList5 = new ArrayList<FirstClassItem>();
        firstList5.add(new FirstClassItem(1, "开出传动", new ArrayList<SecondClassItem>()));
        firstList5.add(new FirstClassItem(2, "不停电传动", new ArrayList<SecondClassItem>()));
        firstList5.add(new FirstClassItem(3, "通信对点", new ArrayList<SecondClassItem>()));
        firstList5.add(new FirstClassItem(4, "厂家调试", new ArrayList<SecondClassItem>()));
    }

    private void initData6() {
        firstList6 = new ArrayList<FirstClassItem>();
        firstList6.add(new FirstClassItem(1, "修改时钟", new ArrayList<SecondClassItem>()));
        firstList6.add(new FirstClassItem(2, "对时方式", new ArrayList<SecondClassItem>()));
        firstList6.add(new FirstClassItem(3, "通信参数", new ArrayList<SecondClassItem>()));
    }

    private void initPopup() {
        popupWindow = new PopupWindow(this);
        View view = LayoutInflater.from(this).inflate(R.layout.popup_layout, null);
        leftLV = (ListView) view.findViewById(R.id.pop_listview_left);
        rightLV = (ListView) view.findViewById(R.id.pop_listview_right);

        popupWindow.setContentView(view);
        popupWindow.setBackgroundDrawable(new PaintDrawable());
        popupWindow.setFocusable(true);
        popupWindow.setHeight(ScreenUtils.getScreenH(this) * 2 / 3);
        popupWindow.setWidth(ScreenUtils.getScreenW(this) * 1 / 3);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                mainTab1TV.setBackgroundColor(Color.WHITE);
                leftLV.setSelection(0);
                rightLV.setSelection(0);
            }
        });

        //为了方便扩展，这里的两个ListView均使用BaseAdapter.如果分类名称只显示一个字符串，建议改为ArrayAdapter.
        //加载一级分类
        final FirstClassAdapter firstAdapter = new FirstClassAdapter(this, firstList);
        leftLV.setAdapter(firstAdapter);

        //加载左侧第一行对应右侧二级分类
        secondList = new ArrayList<SecondClassItem>();
        secondList.addAll(firstList.get(0).getSecondList());


        final SecondClassAdapter secondAdapter = new SecondClassAdapter(this, secondList);
        rightLV.setAdapter(secondAdapter);

        //左侧ListView点击事件
        leftLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //二级数据
                List<SecondClassItem> list2 = firstList.get(position).getSecondList();
                //如果没有二级类，则直接跳转
                if (list2 == null || list2.size() == 0) {
                    popupWindow.dismiss();
                    int firstId = firstList.get(position).getId();
                    String selectedName = firstList.get(position).getName();
                    return;
                }

                FirstClassAdapter adapter = (FirstClassAdapter) (parent.getAdapter());
                //如果上次点击的就是这一个item，则不进行任何操作
                if (adapter.getSelectedPosition() == position) {
                    return;
                }

                //根据左侧一级分类选中情况，更新背景色
                adapter.setSelectedPosition(position);
                adapter.notifyDataSetChanged();

                //显示右侧二级分类
                updateSecondListView(list2, secondAdapter);
            }
        });

        //右侧ListView点击事件
        rightLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //关闭popupWindow，显示用户选择的分类
                popupWindow.dismiss();
                int firstPosition = firstAdapter.getSelectedPosition();
                int firstId = firstList.get(firstPosition).getId();
                int secondId = firstList.get(firstPosition).getSecondList().get(position).getId();
                String selectedName = firstList.get(firstPosition).getSecondList().get(position).getName();
                if (secondId == 101){
                    android.app.Fragment fragment = new analog_quantity();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.frag_container,fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }else {
                    Fragment fragment = new TestOtherValue();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.frag_container,fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            }
        });
    }




    private void initPopup2(){
        popupWindow2 = new PopupWindow(this);
        View view = LayoutInflater.from(this).inflate(R.layout.popup_layout,null);
        leftLV = (ListView) view.findViewById(R.id.pop_listview_left);
        rightLV = (ListView) view.findViewById(R.id.pop_listview_right);
        popupWindow2.setContentView(view);
        popupWindow2.setBackgroundDrawable(new PaintDrawable());
        popupWindow2.setFocusable(true);
        popupWindow2.setHeight(ScreenUtils.getScreenH(this) * 2 / 3);
        popupWindow2.setWidth(ScreenUtils.getScreenW(this) * 1 / 3);
        popupWindow2.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
//                darkView.startAnimation(animOut);
//                darkView.setVisibility(View.GONE);
                mainTab2TV.setBackgroundColor(Color.WHITE);
                leftLV.setSelection(0);
                rightLV.setSelection(0);
            }
        });
        final FirstClassAdapter firstAdapter = new FirstClassAdapter(this, firstList2);
        leftLV.setAdapter(firstAdapter);

        secondList2 = new ArrayList<SecondClassItem>();
        secondList2.addAll(firstList2.get(0).getSecondList());
        final SecondClassAdapter secondAdapter = new SecondClassAdapter(this, secondList2);
        rightLV.setAdapter(secondAdapter);

        leftLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //二级数据
                List<SecondClassItem> list2 = firstList2.get(position).getSecondList();
                //如果没有二级类，则直接跳转
                if (list2 == null || list2.size() == 0) {
                    popupWindow2.dismiss();

                    int firstId = firstList2.get(position).getId();
                    String selectedName = firstList2.get(position).getName();
                    //handleResult(firstId, -1, selectedName);
                    return;
                }
                FirstClassAdapter adapter = (FirstClassAdapter) (parent.getAdapter());
                //如果上次点击的就是这一个item，则不进行任何操作
                if (adapter.getSelectedPosition() == position) {
                    return;
                }
                //根据左侧一级分类选中情况，更新背景色
                adapter.setSelectedPosition(position);
                adapter.notifyDataSetChanged();

                //显示右侧二级分类
                updateSecondListView(list2, secondAdapter);
            }
        });
        rightLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //关闭popupWindow，显示用户选择的分类
                popupWindow2.dismiss();
                int firstPosition = firstAdapter.getSelectedPosition();
                int firstId = firstList2.get(firstPosition).getId();
                int secondId = firstList2.get(firstPosition).getSecondList().get(position).getId();
                String selectedName = firstList2.get(firstPosition).getSecondList().get(position).getName();
                //handleResult2(firstId, secondId, selectedName);
            }
        });
    }

    private void initPopup3() {
        popupWindow3 = new PopupWindow();
        View view = LayoutInflater.from(this).inflate(R.layout.popup_signal_layout, null);
        leftLV = (ListView) view.findViewById(R.id.left_lv);
        popupWindow3.setContentView(view);
        popupWindow3.setBackgroundDrawable(new PaintDrawable());
        popupWindow3.setFocusable(true);
        popupWindow3.setHeight(ScreenUtils.getScreenH(this) * 2 / 5);
        popupWindow3.setWidth(ScreenUtils.getScreenW(this) * 1 / 6);
        popupWindow3.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                mainTab3TV.setBackgroundColor(Color.WHITE);
                leftLV.setSelection(0);
            }
        });
        final FirstClassAdapter firstAdapter = new FirstClassAdapter(this, firstList3);
        leftLV.setAdapter(firstAdapter);

        leftLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                popupWindow3.dismiss();

                int firstId = firstList3.get(position).getId();
                String selectedName = firstList3.get(position).getName();

                FirstClassAdapter adapter = (FirstClassAdapter) (parent.getAdapter());
                //如果上次点击的就是这一个item，则不进行任何操作
                if (adapter.getSelectedPosition() == position) {
                    return;
                }
                //根据左侧一级分类选中情况，更新背景色
                adapter.setSelectedPosition(position);
                adapter.notifyDataSetChanged();

                //显示右侧二级分类
//                updateSecondListView(list2, secondAdapter);
            }
        });
    }
    private void initPopup4() {
        popupWindow4 = new PopupWindow();
        View view = LayoutInflater.from(this).inflate(R.layout.popup_signal_layout, null);
        leftLV = (ListView) view.findViewById(R.id.left_lv);
        popupWindow4.setContentView(view);
        popupWindow4.setBackgroundDrawable(new PaintDrawable());
        popupWindow4.setFocusable(true);
        popupWindow4.setHeight(ScreenUtils.getScreenH(this) * 2 / 6);
        popupWindow4.setWidth(ScreenUtils.getScreenW(this) * 1 / 6);
        popupWindow4.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                mainTab4TV.setBackgroundColor(Color.WHITE);
                leftLV.setSelection(0);
            }
        });
        final FirstClassAdapter firstAdapter = new FirstClassAdapter(this, firstList4);
        leftLV.setAdapter(firstAdapter);
        leftLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                popupWindow4.dismiss();

                int firstId = firstList4.get(position).getId();
                String selectedName = firstList4.get(position).getName();
                //handleResult(firstId, -1, selectedName);

                FirstClassAdapter adapter = (FirstClassAdapter) (parent.getAdapter());
                //如果上次点击的就是这一个item，则不进行任何操作
                if (adapter.getSelectedPosition() == position) {
                    return;
                }
                //根据左侧一级分类选中情况，更新背景色
                adapter.setSelectedPosition(position);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void initPopup5() {
        popupWindow5 = new PopupWindow();
        View view = LayoutInflater.from(this).inflate(R.layout.popup_signal_layout, null);
        leftLV = (ListView) view.findViewById(R.id.left_lv);
        popupWindow5.setContentView(view);
        popupWindow5.setBackgroundDrawable(new PaintDrawable());
        popupWindow5.setFocusable(true);
        popupWindow5.setHeight(ScreenUtils.getScreenH(this) * 2 / 5);
        popupWindow5.setWidth(ScreenUtils.getScreenW(this) * 1 / 6);
        popupWindow5.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
//                darkView.startAnimation(animOut);
//                darkView.setVisibility(View.GONE);
                mainTab5TV.setBackgroundColor(Color.WHITE);
                leftLV.setSelection(0);
            }
        });
        final FirstClassAdapter firstAdapter = new FirstClassAdapter(this, firstList5);
        leftLV.setAdapter(firstAdapter);
        leftLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                popupWindow5.dismiss();

                int firstId = firstList5.get(position).getId();
                String selectedName = firstList5.get(position).getName();
                //handleResult(firstId, -1, selectedName);

                FirstClassAdapter adapter = (FirstClassAdapter) (parent.getAdapter());
                //如果上次点击的就是这一个item，则不进行任何操作
                if (adapter.getSelectedPosition() == position) {
                    return;
                }
                //根据左侧一级分类选中情况，更新背景色
                adapter.setSelectedPosition(position);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void initPopup6() {
        popupWindow6 = new PopupWindow();
        View view = LayoutInflater.from(this).inflate(R.layout.popup_signal_layout, null);
        leftLV = (ListView) view.findViewById(R.id.left_lv);
        popupWindow6.setContentView(view);
        popupWindow6.setBackgroundDrawable(new PaintDrawable());
        popupWindow6.setFocusable(true);

        int[] location = new int[2];
        view.getLocationOnScreen(location);
        popupWindow6.setHeight(ScreenUtils.getScreenH(this) * 2 / 6);
        popupWindow6.setWidth(ScreenUtils.getScreenW(this) * 1 / 6);

        popupWindow6.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
//                darkView.startAnimation(animOut);
//                darkView.setVisibility(View.GONE);
                mainTab6TV.setBackgroundColor(Color.WHITE);
                leftLV.setSelection(0);
            }
        });
        final FirstClassAdapter firstAdapter = new FirstClassAdapter(this, firstList6);
        leftLV.setAdapter(firstAdapter);
        leftLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                popupWindow6.dismiss();

                int firstId = firstList6.get(position).getId();
                String selectedName = firstList6.get(position).getName();
                // handleResult(firstId, -1, selectedName);

                FirstClassAdapter adapter = (FirstClassAdapter) (parent.getAdapter());
                //如果上次点击的就是这一个item，则不进行任何操作
                if (adapter.getSelectedPosition() == position) {
                    return;
                }
                //根据左侧一级分类选中情况，更新背景色
                adapter.setSelectedPosition(position);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void updateSecondListView(List<SecondClassItem> list2,
                                      SecondClassAdapter secondAdapter) {
        secondList.clear();
        secondList.addAll(list2);
        secondAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo1);

        findId();
        initListener();
        //initTab();
        initStatus();
        init();
    }

    /**
     * 初始化id
     */
    private void findId() {
        mZoomIv = (ImageView) findViewById(R.id.uc_zoomiv);
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        titleContainer = (ViewGroup) findViewById(R.id.title_layout);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.appbar_layout);
        titleCenterLayout = (ViewGroup) findViewById(R.id.title_center_layout);
        progressBar = (RoundProgressBar) findViewById(R.id.uc_progressbar);
        mSettingIv = (ImageView) findViewById(R.id.uc_setting_iv);
        mMsgIv = (ImageView) findViewById(R.id.uc_msg_iv);
        mAvater = (CircleImageView) findViewById(R.id.uc_avater);
        mTablayout = (CommonTabLayout) findViewById(R.id.uc_tablayout);
        mViewPager = (NoScrollViewPager) findViewById(R.id.uc_viewpager);
    }

    /**
     * 初始化tab
     */
    private void initTab() {
//        fragments = getFragments();
//        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),
//                fragments, getNames());
//
//        mTablayout.setTabData(mTabEntities);
//        mViewPager.setAdapter(myFragmentPagerAdapter);
    }

    /**
     * 绑定事件
     */
    private void initListener() {
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                float percent = Float.valueOf(Math.abs(verticalOffset)) / Float.valueOf(appBarLayout.getTotalScrollRange());
                if (titleCenterLayout != null && mAvater != null && mSettingIv != null && mMsgIv != null) {
                    titleCenterLayout.setAlpha(percent);
                    StatusBarUtil.setTranslucentForImageView(Demo1Activity.this, (int) (255f * percent), null);
                    if (percent == 0) {
                        groupChange(1f, 1);
                    } else if (percent == 1) {
                        if (mAvater.getVisibility() != View.GONE) {
                            mAvater.setVisibility(View.GONE);
                        }
                        groupChange(1f, 2);
                    } else {
                        if (mAvater.getVisibility() != View.VISIBLE) {
                            mAvater.setVisibility(View.VISIBLE);
                        }
                        groupChange(percent, 0);
                    }

                }
            }
        });
        AppBarLayoutOverScrollViewBehavior myAppBarLayoutBehavoir = (AppBarLayoutOverScrollViewBehavior)
                ((CoordinatorLayout.LayoutParams) mAppBarLayout.getLayoutParams()).getBehavior();
        myAppBarLayoutBehavoir.setOnProgressChangeListener(new AppBarLayoutOverScrollViewBehavior.onProgressChangeListener() {
            @Override
            public void onProgressChange(float progress, boolean isRelease) {
                progressBar.setProgress((int) (progress * 360));
                if (progress == 1 && !progressBar.isSpinning && isRelease) {
                    // 刷新viewpager里的fragment
                }
                if (mMsgIv != null) {
                    if (progress == 0 && !progressBar.isSpinning) {
                        mMsgIv.setVisibility(View.VISIBLE);
                    } else if (progress > 0 && mSettingIv.getVisibility() == View.VISIBLE) {
                        mMsgIv.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });
        mTablayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                mTablayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 初始化状态栏位置
     */
    private void initStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4以下不支持状态栏变色
            //注意了，这里使用了第三方库 StatusBarUtil，目的是改变状态栏的alpha
            StatusBarUtil.setTransparentForImageView(Demo1Activity.this, null);
            //这里是重设我们的title布局的topMargin，StatusBarUtil提供了重设的方法，但是我们这里有两个布局
            //TODO 关于为什么不把Toolbar和@layout/layout_uc_head_title放到一起，是因为需要Toolbar来占位，
            // 防止AppBarLayout折叠时将title顶出视野范围
            int statusBarHeight = getStatusBarHeight(Demo1Activity.this);
            CollapsingToolbarLayout.LayoutParams lp1 = (CollapsingToolbarLayout.LayoutParams) titleContainer.getLayoutParams();
            lp1.topMargin = statusBarHeight;
            titleContainer.setLayoutParams(lp1);
            CollapsingToolbarLayout.LayoutParams lp2 = (CollapsingToolbarLayout.LayoutParams) mToolBar.getLayoutParams();
            lp2.topMargin = statusBarHeight;
            mToolBar.setLayoutParams(lp2);
        }
    }

    /**
     * @param alpha
     * @param state 0-正在变化 1展开 2 关闭
     */
    public void groupChange(float alpha, int state) {
        lastState = state;

        mSettingIv.setAlpha(alpha);
        mMsgIv.setAlpha(alpha);

        switch (state) {
            case 1://完全展开 显示白色
                mMsgIv.setImageResource(R.drawable.icon_msg);
                mSettingIv.setImageResource(R.drawable.icon_setting);
                mViewPager.setNoScroll(false);
                break;
            case 2://完全关闭 显示黑色
                mMsgIv.setImageResource(R.drawable.icon_msg_black);
                mSettingIv.setImageResource(R.drawable.icon_setting_black);
                mViewPager.setNoScroll(false);
                break;
            case 0://介于两种临界值之间 显示黑色
                if (lastState != 0) {
                    mMsgIv.setImageResource(R.drawable.icon_msg_black);
                    mSettingIv.setImageResource(R.drawable.icon_setting_black);
                }
                mViewPager.setNoScroll(true);
                break;
        }
    }


    /**
     * 获取状态栏高度
     * ！！这个方法来自StatusBarUtil,因为作者将之设为private，所以直接copy出来
     *
     * @param context context
     * @return 状态栏高度
     */
    private int getStatusBarHeight(Context context) {
        // 获得状态栏高度
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }

    /**
     * 假数据
     *
     * @return
     */
    public String[] getNames() {
        String[] mNames = new String[]{"Weather", "Moon", "Like", "Fans"};
        for (String str : mNames) {
            mTabEntities.add(new TabEntity(String.valueOf(new Random().nextInt(200)), str));
        }

        return mNames;
    }

//    public List<Fragment> getFragments() {
//        List<Fragment> fragments = new ArrayList<>();
//        fragments.add(new ItemFragment());
//        fragments.add(new ItemFragment());
//        fragments.add(new ItemFragment());
//        fragments.add(new ItemFragment());
//        return fragments;
//    }
}
