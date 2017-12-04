package me.stefan.easybehavior.demo1;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by jumber on 2017/8/8.
 */

public class SaxXMLParser {
    List<Dataset> list;
    public List<Dataset>getListBySaxXMLParser(InputStream in)throws ParserConfigurationException,SAXException,IOException
    {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();

        MyHandler myHandler = new MyHandler();

        saxParser.parse(in,myHandler);
        return list;
    }
    public class  MyHandler extends DefaultHandler{
        StringBuilder sb;
        Dataset ds;
        @Override
        public void startDocument()throws SAXException{
            list = new ArrayList<Dataset>();
            sb = new StringBuilder();
        }
        @Override
        public void startElement(String uri, String localName , String qName, Attributes attributes)throws SAXException{
            if (localName.equals("DS")){
                ds = new Dataset();
            }
            sb.setLength(0);
        }
        @Override
        public void endDocument() throws SAXException{
            super.endDocument();
        }
        @Override
        public void endElement(String uri,String localName ,String qName)throws SAXException{
            if (localName.equals("DO")){

            }
        }

        @Override
        public void characters(char[] ch,int start ,int length)throws SAXException{
            sb.append(ch,start,length);
        }
    }
}
