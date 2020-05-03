package by.bia.labAssist.util;

import org.scriptlet4docx.docx.DocxTemplater;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

public class PrintUtil {
    /*private DocxTemplater docxTemplater;

    public PrintUtil(String path) {
        this.docxTemplater = new DocxTemplater(new File(path));
    }*/

    public static void print(Map<String, Object> map, String template, String fileName){
        DocxTemplater docxTemplater = new DocxTemplater(new File(template));
        docxTemplater.process(new File(fileName + ".docx"), map);
    }

    /*public static void printActAndProtocol(Map<String, Object> map, String path, String fileName){
        DocxTemplater docxTemplater = new DocxTemplater(new File(path));
        docxTemplater.process(new File(fileName + ".docx"), map);
    }*/


    public static InputStream downloadFile(Map<String, Object> map, String template){
        DocxTemplater docxTemplater = new DocxTemplater(new File(template));

        //docxTemplater.process(new File(fileName + ".docx"), map);
        return docxTemplater.processAndReturnInputStream(map);
    }


}
