package com.cfcp.incc.utils;

import org.springframework.beans.factory.annotation.Value;

public class AlipayConfig {
	//应用ID，您的APPID，收款账号即是您的APPID对于支付宝账号
	public static String app_id = "2018112262311191";
	//商户私钥，您的PKCS8格式RSA2私钥
	public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCSNS0qCR58A34DhUXDdr/jui1rNA62NQRcF6jDdQSUuJgZOPPDWotB13IkcMNoJjCnlz/7/5gTFUM7i3fabmmISFD5ZL4NVoC4ACu97nNJ6eA7xmC8DG/zDva340C2caP/9MQrseCvD3OGRFfwHHivWMPlIlu4QaoHS6WL14knMouJ+6pgvCjK1V7te4nI1CG7uWtfProvpyUYk5xIg2/CrNyNlPcO8UK7146RIRPEGHJ9gc0EZCrfxJuVQVK1DdyYuIDXI140mIQbM8cZU6K1nOsjkBikKDtFfVKrAlGUK3WMvxfLcpB4bRfr+ZzZIjDavATCdil2CrpfWCSJnG6NAgMBAAECggEAV6jUTFitU1NQsVGtczT2ddYBNz4999avp3tz7sRxG63VW4H+/MiDp9VBlUGalw4Wq9SOQ0uKZR6hccj78hWy5kfdbLmPDTYo9Kj9kOGvc8hqbcxR4U1frY3yNGxnNzvJRUP+fegnOy57aCv/wXGAljkVMoM4FLAh/j/HoKF4lOvb/ewbcVqp/1osfuuCgnwMgSW/LA+2AkiqAkHvK3zEgar7QYzs0wtA/4OgCdu6q4fuqR6+Tp5IyXyMDrb963q5HVPTRarSpUGtPO/AmDHhBwRWZ7qT0JyYVOQaHHp20C48Br1hDPZRnBWehnbFBYrmxaGhIlV9QlwUkoPdbReWTQKBgQDJE/C7OvXGL5QYjyLfft+b/hAwMbOFzCF8B9b1f4xAeZS+PMGJnRzr3hrCkIMLKRQsjvW0Oq96LNKVmhm30aqALan+Ry1Fik405LDbTvFzgV31jRz8hd4nOyNzM043MxCrjlU3Le6Y87aOUmJ30Mpu4Jd3J+uZR2VbwDtgo6Vt6wKBgQC6JIZB1bxNz1sGFThfj2IvgvHhLBrMV32hv9au63G1cVkiNv+mYyZ5wZ7bLE2OEsSRFXQAQYkEOYvcuAkGeE0ERI8b11jlik4mb+WfSI844uOJMdK8Wc8k6VlWsppxyE3s1kcP0Lw4tNhzkK6pRMcjhmsAx2GKOMmxKiftEwlfZwKBgQC3Tp9aAEEIQF2RGgPfnUj6a12UoeqX4eTwrjDvsyxHps7LspV4/6ra96qLjrR4GyXSDS2+9PT9uxjq2wXxBomZuodLUNXbnF75ZzY3Z7tD4j6m2NMA/oTsXMRPhAkc10euLd5PpGbsNtlI25/6vHOfKs3KCbGFFYo4X4GB8w2fqwKBgQCL+tOLxrOov/pvhJ20HxtzJ0HGV2h/qnurm5xSRnRoQikJddqiwYU5osVXhOV9pdd0U3G8j4v+RVhMT4lZ+DXSs8v63+LWGz/R3+wJ8ILkAJujnHghdFg2JdUtQXqrfjo7lJgz6IDjE/yCS5dNa49lw7vMBiPv0ey68jy8J8iwmQKBgFugqM5HcAmOij1yExD+oM1D0u6v+9E9xzRJ2MulBHj0ZxHQbyMffUXAQacM98tfLPKiXF9h69IxFr8XoLuQ4JyrWze+5eSZ2g6/jpm1S053MeIcP6eIjqS4i+RfJFOZaFa4+RiYkbZTDVjFu5EePEKxMxPwXbtzUDdBF8eSWWe0";//�̳̲鿴��ȡ��ʽ���������ã�
    //支付宝公钥，查看地址：https://openhome.alipay.com/platform/appDaily.htm?tab=info
	public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAj/7Qc62dXQBK3ukSAkcRr5GRT3+QO5mXT21EcZGyaFUGzH0cAn6vIDl0QI0PFcG4xB4re9cTz9dYNnE5pFsOgyobS8SPtbUlHnUmUySwpqkXAztBEBvreIoxuTcNTxoOC6BkWk98odAqW6xxLuOLsi3eST1SLWU8UJ8p6pmwIbySK0TBxWIhbifPutY1/Kl6KazW4unwWA+yeNTp6sMoX/PXIAzAjQe3vdyUWfBcGxu5VJWdoUbMoEU01Nky7leKA+iEjiK/vl007tODpewuYNBzRmGJVelP0/hFu303VqShLfLDez4JD5lBKxXt0gw+r4hgiaMtM2HkosG0rFwBQQIDAQAB";//�̳̲鿴��ȡ��ʽ���������ã�
	//public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkjUtKgkefAN+A4VFw3a/47otazQOtjUEXBeow3UElLiYGTjzw1qLQddyJHDDaCYwp5c/+/+YExVDO4t32m5piEhQ+WS+DVaAuAArve5zSengO8ZgvAxv8w72t+NAtnGj//TEK7Hgrw9zhkRX8Bx4r1jD5SJbuEGqB0uli9eJJzKLifuqYLwoytVe7XuJyNQhu7lrXz66L6clGJOcSINvwqzcjZT3DvFCu9eOkSETxBhyfYHNBGQq38SblUFStQ3cmLiA1yNeNJiEGzPHGVOitZzrI5AYpCg7RX1SqwJRlCt1jL8Xy3KQeG0X6/mc2SIw2rwEwnYpdgq6X1gkiZxujQIDAQAB";//�̳̲鿴��ȡ��ʽ���������ã�
	//服务器异步通知页面路径 需 http://格式的完整路径，不能加？id=123这类自定义路径
	public static String notify_url = "http://localhost:8081/incc/alipay/alipayNotifyNotice.action";
	//页面跳转同步通知页面路径 需http://格式的完整路径，不能加？id=123这类自定义路径
	public static String return_url = "http://localhost:8081/incc/alipay/alipayReturnNotice.action";
	//签名方式
	public static String sign_type = "RSA2";
	//字符编码格式
	public static String charset = "utf-8";
	//支付宝网关
	//public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";//ע�⣺ɳ����Ի�������ʽ����Ϊ��https://openapi.alipay.com/gateway.do
	public static String gatewayUrl = "https://openapi.alipay.com/gateway.do";//ע�⣺ɳ����Ի�������ʽ����Ϊ��https://openapi.alipay.com/gateway.do

	@Value("${pay.log_path}")
	private String pay_log_path;
	private String pl = FileUtils.createFile(pay_log_path);
	public  String log_path = pl;
	//操作日志
	//public static String log_path ="D:\\test\\pay\\";


}
