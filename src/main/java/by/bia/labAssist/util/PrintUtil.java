package by.bia.labAssist.util;

import org.scriptlet4docx.docx.DocxTemplater;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

public class PrintUtil {
    public static void printToServer(Map<String, Object> map, String template, String fileName){
        DocxTemplater docxTemplater = new DocxTemplater(new File(template));
        docxTemplater.process(new File(fileName + ".docx"), map);
    }

    public static InputStream downloadFile(Map<String, Object> map, String template){
        DocxTemplater docxTemplater = new DocxTemplater(new File(template));
        return docxTemplater.processAndReturnInputStream(map);
    }
}
