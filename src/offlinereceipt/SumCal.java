/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package offlinereceipt;

import java.io.FileInputStream;

/**
 *
 * @author madhu.lingala
 */
public class SumCal {
    public void getBranchName(String branchname){
        System.out.println("Hey! you are selected.."+branchname);
        
    }
}

//    public void Calculate(){
//
//    
//
//    
//        try {
//                /** Creating Input Stream**/
//                FileInputStream myInput = new FileInputStream(f.toString());
//
//        /** Create a POIFSFileSystem object**/
//        POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);
//
//        /** Create a workbook using the File System**/
//        HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);
//
//        /** Get the first sheet from workbook**/
//        HSSFSheet mySheet = myWorkBook.getSheetAt(0);
//
//        /** We now need something to iterate through the cells.**/
//        Iterator rowIter = mySheet.rowIterator();
//        HSSFRow myRow = (HSSFRow) rowIter.next();
//
//        List l = new ArrayList();
//        List l2 = new ArrayList();
//        List l3 = new ArrayList();
//        while (rowIter.hasNext()) {
//            //rowIter.next();
//            myRow = (HSSFRow) rowIter.next();
//            HSSFCell pv1 = myRow.getCell(6);
//            Double d = pv1.getNumericCellValue();
//            l.add(d);
//            HSSFCell pv2 = myRow.getCell(7);
//            Double d2 = pv2.getNumericCellValue();
//            l2.add(d2);
//            HSSFCell pv3 = myRow.getCell(8);
//            Double d3 = pv3.getNumericCellValue();
//            l3.add(d3);
//        }
//        loanSum = 0.0;
//        Iterator it1 = l.iterator();
//        while (it1.hasNext()) {
//            Double d = (Double) it1.next();
//            loanSum += d;
//
//        }
//
//        savingSum = 0.0;
//        Iterator it2 = l2.iterator();
//        while (it2.hasNext()) {
//            Double d = (Double) it2.next();
//            savingSum += d;
//
//        }
//        feeSum = 0.0;
//        Iterator it3 = l3.iterator();
//        while (it3.hasNext()) {
//            Double d = (Double) it3.next();
//            feeSum += d;
//
//        }
//        
//
//    }
//    
//    catch (Exception e1) { 
//        e1.printStackTrace();
//    }
//}
//*/