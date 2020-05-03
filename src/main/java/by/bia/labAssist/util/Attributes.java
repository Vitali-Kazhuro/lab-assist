package by.bia.labAssist.util;

import javax.servlet.http.HttpSession;

public enum Attributes {
    TEST_REPORT("testReport"),
    APPLICANT("applicant"),
    SAMPLING_AUTHORITY("samplingAuthority"),
    OBJECT_OF_STUDY("objectOfStudy"),
    REGULATORY_DOCUMENT("regulatoryDocument"),
    NORM_LIST("normList"),
    SAMPLE_NORM_LIST("sampleNormList"),
    SAMPLE("sample"),
    EDIT_SAMPLE("editSample"),
    NEW("new"),
    PRINT_MAP("printMap");


    Attributes(String title) {
        this.title = title;
    }

    private final String title;

    public static void clearSession(HttpSession session){
        for (Attributes attribute: Attributes.values()){
            if(session.getAttribute(attribute.title)!= null){
                session.removeAttribute(attribute.title);
            }
        }
    }

    /*public String getTitle() {
        return title;
    }*/

}
