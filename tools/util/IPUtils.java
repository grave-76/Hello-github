import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class IPUtils
{
    private IPUtils()
    {

    }

    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr()的原因是有可能用户使用了代理软件方式避免真实IP地址,
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值 如果使用了nginx代理
     * 记得在代理中把真实IP转发过来。
     * @return ip
     */
    public static String getRealIP(HttpServletRequest request)
    {
        String[] headers =
        { "X-Forwarded-For", "X-Real-IP", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_CLIENT_IP",
                "HTTP_X_FORWARDED_FOR" };

        String ip;
        for (String header : headers)
        {
            ip = request.getHeader(header);
            if (!isUnknow(ip))
            {
                return getMultistageReverseProxyIp(ip);
            }
        }

        ip = request.getRemoteAddr();
        // 本机
        if ("127.0.0.1".equals(ip))
        {
            try
            {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e)
            {
                e.printStackTrace();
            }
        }
        return ip;
    }

    /**
     * 从多级反向代理中获得第一个非unknown IP地址
     * @param ip 获得的IP地址
     * @return 第一个非unknown IP地址
     */
    private static String getMultistageReverseProxyIp(String ip)
    {
        // 多级反向代理检测
        if (ip != null && ip.indexOf(",") > 0)
        {
            final String[] ips = ip.trim().split(",");
            for (String subIp : ips)
            {
                if (!isUnknow(subIp))
                {
                    ip = subIp;
                    break;
                }
            }
        }
        return ip;
    }

    /**
     * 检测给定字符串是否为未知，多用于检测HTTP请求相关<br>
     * @param checkString 被检测的字符串
     * @return 是否未知
     */
    private static boolean isUnknow(String checkString)
    {
        return StringUtil.isEmpty(checkString) || "unknown".equalsIgnoreCase(checkString);
    }
	
}