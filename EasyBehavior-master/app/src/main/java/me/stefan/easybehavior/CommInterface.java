package me.stefan.easybehavior;

/**
 * Created by jumber on 2017/7/17.
 */

public class CommInterface {
    public void EnableReport(String rptName){

    }

    public void DisableReport(String rptName){

    }

    public SValue ReadData(String dataname){
        return s;
    }

    public boolean WriteData(SValue value){
        return  true;
    }


    private  boolean b;
    private  int i;
    private  float f;

    private  SValue s;

    public enum Value{
        B, I, F;
    }
    private class SValue{
        public byte type;
        Value bvalue = Value.B;
        Value ivalue = Value.I;
        Value fvalue = Value.F;
    }
}
