package burp;

import java.util.Arrays;
import java.util.List;

/**
 * Project: fakeIP
 * Date:2021/5/21 上午11:56
 *
 * @author CoolCat
 * @version 1.0.0
 * Github:https://github.com/TheKingOfDuck
 * When I wirting my code, only God and I know what it does. After a while, only God knows.
 */
public class Config {
    public static List<String> HEADER_LIST = Arrays.asList(
            "X-Forwarded-For","X-Forwarded","Forwarded-For","Forwarded","X-Requested-With","X-Forwarded-Proto", "X-Forwarded-Host",
            "X-remote-IP","X-remote-addr","True-Client-IP","X-Client-IP","Client-IP","X-Real-IP",
            "Ali-CDN-Real-IP","Cdn-Src-Ip","Cdn-Real-Ip","CF-Connecting-IP","X-Cluster-Client-IP",
            "WL-Proxy-Client-IP", "Proxy-Client-IP","Fastly-Client-Ip","True-Client-Ip","X-Originating-IP",
            "X-Host","X-Custom-IP-Authorization"
    );

    public static String AUTOXFF = "X-Forwarded-For";
    public static String AUTOXFFVALUE = "$RandomIp$";
}
