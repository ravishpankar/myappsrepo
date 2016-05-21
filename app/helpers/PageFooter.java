package helpers;

import helpers.Util;

import java.util.Date;


/**
 * Created by admin on 5/19/2016.
 */
public class PageFooter {
    public String firstD;
    public String lastD;
    public boolean up;
    public boolean down;
    public String upId;
    public String downId;
    public String upFId;
    public String downFId;


    public static PageFooter getPageFooter(boolean bExclude, Date fFD, Date fD, Date lD) throws Exception {
        PageFooter pgF = new PageFooter();
        if (bExclude) {
            pgF.up = false;
            pgF.down = false;
            pgF.firstD = "";
            pgF.lastD = "";
            pgF.upId = "";
            pgF.downId = "";
            pgF.downFId = "";
            pgF.upFId = "";
        } else {
            if (fFD.equals(fD)) {
                pgF.down = false;
                pgF.firstD="";
            } else {
                pgF.down = true;
                pgF.firstD = Util.getNonReadableDateString(fD);
            }
            pgF.up = true;
            pgF.lastD = Util.getNonReadableDateString(lD);
            pgF.upId = "#up" + pgF.lastD;
            pgF.downId = "#down" + pgF.lastD;
            pgF.downFId = "downClick" + pgF.lastD;
            pgF.upFId = "upClick" + pgF.lastD;;
        }
        return pgF;
    }

}

