package configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadFromExcel {

	int ExcelElements = 8;
	public String ExcelFilePath = "src/test/java/resources/config_file.xlsx";
	
	public String[] AllExcellIntoArray1 = new String[ExcelElements];
	public String[] AllExcellIntoArray2 = new String[ExcelElements];
	public String[] NameArray = new String[ExcelElements/2];
	public String[] ValueArray = new String[ExcelElements/2];

	public String[] Read_excel (String FilePath , int numberOfElements) throws IOException 
{
			
		int temp=0 ;
			
		FileInputStream inputStream = new FileInputStream(new File(FilePath));
		Workbook workbook = new XSSFWorkbook(inputStream);
	    Sheet firstSheet = workbook.getSheetAt(0);
	    Iterator<Row> iterator = firstSheet.iterator();
	     
	    while (iterator.hasNext()) 
	  {
	        Row nextRow = iterator.next();
	        Iterator<Cell> cellIterator = nextRow.cellIterator();
	        
	       for ( int j=temp ; j<numberOfElements; j++) 
	     {
	       	if (cellIterator.hasNext()) 
	       	{
	          Cell cell = cellIterator.next();
	          temp = j;
	          AllExcellIntoArray1[temp] = cell.getStringCellValue();
	          temp++;
	       	}
	     }
	   inputStream.close();
	  }
	    return AllExcellIntoArray1 ;
}

	// Get Value based on other col index
		public String GetValueFromOtherCol(String[] Col1, String[] Col2, String Text) 
			{
				String temp="" ;
				for (int k=0; k<Col1.length; k++) 
				  {
				    if (Col1[k].equals(Text)) { temp = Col2[k]; }
				  }
			return temp ;
			}


			
	// Divide the excel data into 2 columns, One for Name, the other for Value
		public String GetValueBasedonOtherColText ( String Name) throws IOException
		{
	//fill NameArray and ValueArray		
			AllExcellIntoArray2 =	Read_excel(ExcelFilePath,ExcelElements);
			
			for (int i=0; i<(AllExcellIntoArray2.length)/2; i++) 
			{
				NameArray[i] = AllExcellIntoArray2[2*i] ;
				ValueArray[i] = AllExcellIntoArray2[(2*i)+1] ;
		    }  
			String temp = GetValueFromOtherCol(NameArray, ValueArray, Name) ;
			return temp ;
		}


}
