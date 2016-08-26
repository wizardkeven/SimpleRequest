import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.commons.codec.binary.Base64; 


public class MainTest {

	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		String usridString = "wizardkeven@live.comHDECHALLENGE003";
		byte[] data = usridString.getBytes("ASCII");
		String usridss = new String(data);
//		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        int T0 = 0;
        int STEP = 30;
        String steps;
        long testTime = System.currentTimeMillis();
//        testTime =20000000000L;
		long tt = (testTime-T0)/STEP;
		steps = Long.toHexString(tt).toUpperCase();
        String sa = "Aladdin";
        String sb = "open sesame";
        String dd = sa +":"+sb;
		while (steps.length() < 16) steps = "0" + steps;
		String usrid = String.format("%040x", new BigInteger(1, usridss.getBytes()));
		String onetimePW = TOTP.generateTOTP(usrid, steps, "10","HmacSHA512");
		String one = newTOTP.generateTOTP(usrid, steps, "10","HmacSHA512");
		System.out.println(onetimePW);
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		try {
//            URL url = new URL ("http://hdechallenge-solve.appspot.com/challenge/003/endpoint");
            HttpPost request = new HttpPost("http://hdechallenge-solve.appspot.com/challenge/003/endpoint");
            String userNameString = "wizardkeven@live.com";

//            String userpass   = "userid"+ ":" +"password";
//            String userid      = "wizardkeven@live.com";
//            String password    = onetimePW;
            String ddString = userNameString+":"+one;

//            String encoding =  new String(org.apache.commons.codec.binary.Base64.encodeBase64(onetimePW));
//            request.addHeader("/challenge/003/endpoint HTTP/1.1");
            Base64 b = new Base64();
            byte[] bytesEncoded = Base64.encodeBase64(ddString .getBytes());
//            String encoding = Base64.encodeBase64String(ddString.getBytes());
            String encoding = new String(bytesEncoded);
//            System.out.println("Lenth of code is : "+ encoding.length()+":"+tttString.length()+"\n"+encoding);
            StringEntity param = new StringEntity("{\"contact_email\":\"wizardkeven@live.com\", \"github_url\":\"https://gist.github.com/wizardkeven/6394a63daa761f4f7e5ca4ca18ea1674\"}");
            request.addHeader("Authorization", "Basic "+encoding);
            request.addHeader("Host","admissionchallenge.example.com");
            request.addHeader("Accept","*/*");
//            request.addHeader("charset", "UTF-8");
            
            request.addHeader("Content-Type", "application/json");
//            request.addHeader("Content-Length", param.toString().length()+"");
            request.setEntity(param);
            System.out.println(request);
            HttpResponse response = httpClient.execute(request);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("POST");
//            connection.setDoOutput(true);
//            
//            connection.setRequestProperty  ("Authorization", "Basic " + encoding);
//            InputStream content = (InputStream)connection.getInputStream();
//            BufferedReader in   = 
//                new BufferedReader (new InputStreamReader (content));
//            String line;
//            while ((line = response) != null) {
                System.out.println(response);
//            } 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
