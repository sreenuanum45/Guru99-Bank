package Utility;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExcelFileUtility {
    private Workbook wb;
    private FileOutputStream fo;
    private FileInputStream fi;
    private File f;
    private Sheet sh;
    private DataFormatter df;

    public ExcelFileUtility(String filepath) throws Exception
    {
        f = new File(filepath);
        fi=new FileInputStream(f);
        wb= WorkbookFactory.create(fi);
        fo=new FileOutputStream(f);
        df=new DataFormatter();
    }
    public  int numberofSheets() throws IOException {
        fi=new FileInputStream(f);
        wb= WorkbookFactory.create(f);
        int numberofsheets=wb.getNumberOfSheets();
        return numberofsheets;
    }
    public  int numberofRows(String sheetname) throws IOException {
        fi=new FileInputStream(f);
        wb= WorkbookFactory.create(f);
        sh=wb.getSheet(sheetname);
        int numberofrows=sh.getPhysicalNumberOfRows();
        return numberofrows;
    }
    public  int numberofColumns(String sheetname) throws IOException {
        fi=new FileInputStream(f);
        wb= WorkbookFactory.create(f);
        sh=wb.getSheet(sheetname);
        int numberofcoloums=sh.getRow(0).getLastCellNum();
        return numberofcoloums;
    }
    public String getcellvalue(String sheetname,int rowindex,int columnindex) throws IOException {
        fi = new FileInputStream(f);
        wb = WorkbookFactory.create(fi);
        sh=wb.getSheet(sheetname);
        df=new DataFormatter();
        String value=df.formatCellValue(sh.getRow(rowindex).getCell(columnindex));
        return(value);
    }
    public void createResultColumn(String filePath,int colindex,String sheetname) throws IOException {
        fi = new FileInputStream(f);
        wb = WorkbookFactory.create(fi);
        sh=wb.getSheet(sheetname);
        SimpleDateFormat sf=new SimpleDateFormat("dd-MMM-yyyy-hh-mm-ss");
        Date dt=new Date();
        //create results column in first row by default
        sh.getRow(0).createCell(colindex).setCellValue(sf.format(dt));
        sh.autoSizeColumn(colindex); //auto-fit
    }
    public void setCellValue(int rowindex, int colindex, String value)
    {
        try
        {
            //If row is used row
            sh.getRow(rowindex).createCell(colindex).setCellValue(value);
            sh.autoSizeColumn(colindex);
        }
        catch(NullPointerException ex)
        {
            //If row is unused or using first time
            sh.createRow(rowindex).createCell(colindex).setCellValue(value);
            sh.autoSizeColumn(colindex);
        }
    }
    public void saveAndCloseExcel() throws Exception
    {
        wb.write(fo); //save
        wb.close();
        fo.close();
        fi.close();
    }
}
