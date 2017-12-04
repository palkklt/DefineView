package me.stefan.easybehavior.demo1;

import android.app.admin.DeviceAdminInfo;

import java.util.Map;

/**
 * Created by jumber on 2017/8/7.
 */

public class Dataset {
    private String dsname;
    private String Do;
    private int magDa;
    private int angDa;

    public void EnableReport(String rptName){

    }
    public void DisableReport(String rptName){

    }
//    private Dataset getDateset(Dataset dataset){
//        return dataset;
//    }

    public void setName(String dsname){
        this.dsname = dsname;
    }
    public String getName(){
        return dsname;
    }

    public void setMagDa(int magDa){
        this.magDa = magDa;
    }
    public int getMagDa(){
        return magDa;
    }

    public void setAngDa(int angDa){
        this.angDa = angDa;
    }

    public int getAngDa(){
        return angDa;
    }

    public void setDO(String Do){
        this.Do = Do;
    }
    public String getDo(){
        return Do;
    }

    public Dataset(String dsname,String Do ,int magDa ,int angDa){
        super();
        this.dsname = dsname;
        this.Do = Do;
        this.magDa = magDa;
        this.angDa = angDa;
    }

    public Dataset(){
        super();
    }
}

//class EDo extends ECom {
//    private Map<String,EDA> m_plDA;
//
//}
//class EDA extends ECom{
//    private Map<String,EDA> m_plChild;
//
//}

//class ECom{
//    public String name;
//    public String refrence;
//    public String m_strValue;
//    public String m_strType;
//    public int m_nType;
//    public int m_nSize;
//}