package es.upm.fi.dia.oeg.ncbo.galaxy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class extract {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static void main(String[] args) throws ClientProtocolException, IOException {
		
//		http://www.bioontology.org/wiki/index.php/View_Extraction
//		./viewextractor/{ontology version id}[?{args}]&apikey={YourAPIKey} 
			    
		String api_key = args [0]; 
	    String ontologyversionid =  args [1]; // 35686
	    String conceptid = args [2]; // E800-E999.9
	    String filterrelations = args [3]; // PAR,isa,CHD,inverse_isa,SUBSETMEMBER,SubClass,SuperClass,[R]SIB,SI
	    String ontologyname = args [4]; // http://who.int/icd9
	    
		HttpClient client = new DefaultHttpClient();
		
//		HttpGet get = new HttpGet("http://rest.bioontology.org/bioportal/viewextractor/35686/" +
//				"?conceptid=E800-E999.9&filterrelations=PAR,isa,CHD,inverse_isa,SUBSETMEMBER,SubClass,SuperClass," +
//				"[R]SIB,SIB&existontology=true&ontologyname=http://who.int/icd9&apikey=74c12fc6-9423-455a-a619-b94f47d1951b");
		
		HttpGet get = new HttpGet("http://rest.bioontology.org/bioportal/viewextractor/" + ontologyversionid +
				"/?conceptid="+ conceptid +"&filterrelations=" + filterrelations +
				"&existontology=true&ontologyname="+ ontologyname + "&apikey=" + api_key);

		HttpResponse response = client.execute(get);
		HttpEntity entity = response.getEntity();
		if (entity != null) {	
			InputStream instream = entity.getContent();
			InputStreamReader is=new InputStreamReader(instream);
			BufferedReader br=new BufferedReader(is);
			String read=br.readLine();
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			while(read!=null){
//			    System.out.println(read);
				bw.write(read);
				bw.newLine();
			    read=br.readLine();
			}
			bw.close();
			instream.close();
		}

	}

}
