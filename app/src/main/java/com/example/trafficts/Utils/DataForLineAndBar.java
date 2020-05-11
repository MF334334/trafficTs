package com.example.trafficts.Utils;

import java.util.ArrayList;

/**
 * Created by yaoke on 2017/9/20.
 */


public class DataForLineAndBar {
    private static final String Title="TileName";
    private static final String LegendData = "LegendData";
    private static final String XAxisData = "XAxisData";
    private static final String SeriesData = "SeriesData";
    private static final String SeryName = "name";
    private static final String SeryData = "data";
    private static final String SeryType = "type";
    public static final String SeryType_Bar = "bar";
    public static final String SeryType_Line = "line";


    private String m_Title = "";
    public String getTile(){return m_Title;}
    public void setTitle(String title){m_Title = title;}


    private ArrayList<String> m_LegendData = new ArrayList<String>();
    private ArrayList<String> m_Type=new ArrayList<>();//用来存type类型


    private ArrayList<String> m_xAxisData= new ArrayList<String>();
    private ArrayList<ArrayList<String>> m_seriesData= new ArrayList<ArrayList<String>>();


    public ArrayList<String> getM_Type() {
        return m_Type;
    }


    public void setM_Type(ArrayList<String> m_Type) {
        this.m_Type = m_Type;
    }


    public static String getSeryType() {
        return SeryType;
    }


    public void addLegendValue(String legendValue)
    {
        m_LegendData.add(legendValue);
    }
    public ArrayList<String> getLegendData()
    {
        return m_LegendData;
    }
    public  void setLegendData(ArrayList<String> legendData)
    {
        m_LegendData = legendData;
    }


    public void addXAxisValue(String xAxisValue)
    {
        m_xAxisData.add(xAxisValue);
    }


    public ArrayList<String> getXAxisData()
    {
        return m_xAxisData;
    }
    public  void setXAxisData(ArrayList<String> xAxisData)
    {
        m_xAxisData = xAxisData;
    }


    public void addSeriesValue(ArrayList<String> seriesValue)
    {
        m_seriesData.add(seriesValue);
    }


    public ArrayList<ArrayList<String>> getSeriesData()
    {
        return m_seriesData;
    }


    public  void setSeriesData(ArrayList<ArrayList<String>> seriesData)
    {
        m_seriesData = seriesData;
    }


    public String GetJSONString()
    {
        String retJSON = new String();
        retJSON += "{";


        retJSON += "\""+Title+"\":"+"\""+m_Title+"\",";
        retJSON += "\""+LegendData+"\":[";
        for (int i = 0 ; i < m_LegendData.size();i++)
        {
            retJSON += "\"" + m_LegendData.get(i) +"\"";
            if(i < m_LegendData.size()-1)
                retJSON +=",";
        }
        retJSON +="],";


        retJSON += "\""+XAxisData+"\":[";
        for (int j = 0; j < m_xAxisData.size();j++)
        {
            retJSON += "\"" + m_xAxisData.get(j) +"\"";
            if(j < m_xAxisData.size()-1)
                retJSON +=",";
        }
        retJSON +="],";


        retJSON +="\""+SeriesData+"\":";
        retJSON +="[";
        for (int k = 0;k < m_LegendData.size();k++)
        {
            ArrayList<String> seryData = m_seriesData.get(k);
            retJSON +="{";


            retJSON += "\""+SeryType +"\":\""+m_Type.get(k)+"\",";
            retJSON += "\""+SeryName +"\":\""+m_LegendData.get(k)+"\",";
            if(m_Type.get(k).equals(SeryType_Line)){
                retJSON +=  "\""+"yAxisIndex" +"\":\""+"1"+"\",";
            }


            retJSON += "\""+SeryData+"\":[";
            for (int l = 0; l < seryData.size();l++)
            {
                retJSON += "\"" + seryData.get(l) +"\"";
                if(l < seryData.size()-1)
                    retJSON +=",";
            }
            retJSON +="]";


            retJSON +="}";
            if(k < m_LegendData.size()-1)
                retJSON+=",";
        }
        retJSON +="]";


        retJSON += "}";


        return retJSON;
    }
}
