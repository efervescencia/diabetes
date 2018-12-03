package packMLDiabetes;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;

public class MLDiabetes {
	

		static Instances data = null;
	 
	  public Instances createDataset(String directoryPath, String pClass, ArrayList<Attribute> atts) throws Exception {
	 
		System.out.println("directorio: "+directoryPath);  

	    File dir = new File(directoryPath);
	    String[] files = dir.list();
	    InputStreamReader is = null;
	      
	    for (int i = 0; i < files.length; i++) {
	      if (files[i].endsWith(".txt")) {
	    try {
	      double[] newInst = new double[2];
	     
	      newInst[0] = (double)data.attribute(0).indexOfValue(pClass);
	      
	      File txt = new File(directoryPath + File.separator + files[i]);
	      is = new InputStreamReader(new FileInputStream(txt));
	      StringBuffer txtStr = new StringBuffer();
	      int c;
	      while ((c = is.read()) != -1) {
	        txtStr.append((char)c);
	      }
	      newInst[1] = (double)data.attribute(1).addStringValue(txtStr.toString());
	      data.add(new Instance(1.0, newInst));
	    } catch (Exception e) {
	      System.err.println("failed to convert file: " + directoryPath + File.separator + files[i]);
	    }
	      }
	    }
	    is.close();
	    return data;
	}

	public static void main(String[] args) {
		

	}

}
