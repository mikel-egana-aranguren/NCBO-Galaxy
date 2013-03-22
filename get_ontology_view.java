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
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

public class get_ontology_view {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static void main(String[] args) throws ClientProtocolException, IOException {
//		http://www.bioontology.org/wiki/index.php/BioPortal_REST_services#Download_a_specific_ontology_view_based_on_the_ontology_view_version_id
//		Download a specific ontology view based on the ontology view version id
		
		String api_key = args [0]; 
		String ontology_view_id = args [1]; 
		
		HttpClient client = new DefaultHttpClient();
//		http://rest.bioontology.org/bioportal/ontologies/download/43072?apikey=YourAPIKey
		HttpGet get = new HttpGet("http://rest.bioontology.org/bioportal/ontologies/download/" + ontology_view_id + "?apikey=" + api_key);
		
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
