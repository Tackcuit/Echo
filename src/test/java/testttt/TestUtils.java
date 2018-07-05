package testttt;

import com.qf.echo.utils.OrderIdGenerator;
import org.junit.Test;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2018/6/26.
 */

public class TestUtils {
	@Test
	public void test01(){
		OrderIdGenerator.createOrderId();
	}

	@Test
	public void test(){
		//网页爬虫
		try {
			BufferedReader bufr = new BufferedReader(new FileReader("D:\\regex\\mail.txt"));
			String line = null;
			String regex = "\\w+@\\w+(\\.\\w+)+";
			Pattern p = Pattern.compile(regex);
			while ((line = bufr.readLine()) != null){

				Matcher matcher = p.matcher(line);
				while (matcher.find()){
					String group = matcher.group();
					System.out.println(group);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void test02(){
		try {
			URL url = new URL("http://tieba.baidu.com/p/5581567097");
			URLConnection conn = url.openConnection();
			InputStream inputStream = conn.getInputStream();
			BufferedReader bufr = new BufferedReader(new InputStreamReader(inputStream));

			String line = null;
			String regex = "\\w+@\\w+(\\.\\w+)+";
			Pattern p = Pattern.compile(regex);
			while ((line = bufr.readLine()) != null){

				Matcher matcher = p.matcher(line);
				while (matcher.find()){
					String group = matcher.group();
					System.out.println(group);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
