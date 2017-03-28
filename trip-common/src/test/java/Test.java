import com.yin.trip.common.util.HttpUtil;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by yinfeng on 2017/3/17 0017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class Test {

    @org.junit.Test
    public void testHttpUtil(){
        String url = "http://api.map.baidu.com/geocoder/v2/";
        String param = "callback=renderReverse&location=39.983424,116.322987" +
                "&output=xml&pois=1&ak=caR16D6gFwi9voPC3H32RlXrNShv4Fvn"
                + "&mcode=49:A8:2E:E4:F5:39:65:2D:6D:A4:00:EC:8F:01:35:83:E8:26:F1:01;com.yin.trip";

        String result = HttpUtil.sendGet(url,param);
        System.out.print(result);
    }

    @org.junit.Test
    public void testBaiduApi(){
        String address = "世界之窗";

    }
}
