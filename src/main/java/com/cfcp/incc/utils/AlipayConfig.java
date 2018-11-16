package com.cfcp.incc.utils;

public class AlipayConfig {
	//应用ID，您的APPID，收款账号即是您的APPID对于支付宝账号
	public static String app_id = "2016092000558095";
	//商户私钥，您的PKCS8格式RSA2私钥
	public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDEkZAOdhQ7dOv8o1EUU8s12bX2HIBWZd4Mhagt0sc7/pj18DJtyE8w5v0nNrEPKYInZaLuzmhHv/9piyA46hw6JeaKG04W+8RhmRgCiRrxVbGP9OpyUm/kd5xQdeq9wHCYLVcj+rDoeHR6SMlcIGUYE56Fnix2o+rgI1XLdu2UVdyQZFPtu0i3RRBJQUQFs4an3iLZmzomwF2gkPNAgk7VU7kdo5bMHJ0Zf9mHO8Gj3mzrq1ArPhVwKkH3vHGs7zXTCFMnpZ+3Wc1HK9IJOEK1H2CBCEhyaBR7Nmg7p17oJis11wAwj5mDjRZyIvgJbZEcpQpHshcHWpojrYes0LLNAgMBAAECggEAdBAeputBdvnKfRlRPldbnSg3MGITxO/Q9EPW0Xeq+9mQJ2bz365uU8mPoB/C29j+aJSyZ56oI/VRsdfb5r8Ue6D1RoZozVlK0vW6H4Uky2p1tTI/W9EhEB0vxrJZMED4OmJLGYtj+V9mJu3jNupJxrWO3AZQ6pxOiplShxhpnzMo5c00WzxtAgmk3u23v9f6bIt+eQQctcV2CzGraGF4detgYaPv8riROlEo/gjZyM4pLpN4cdnb2Z8ZQvzIJcokTORqLRVaQ5s92VKApmkaTgTlUaNPYgpQFqq6JreISMhbhw2g9QzP8ulg9TeX+It00DborRv8bJj6MWcXSsaOYQKBgQD5lmXB31QGpnvNECPVd3lp1x7bATra8TUBcwkYfHLU++YGoIFoGcWF/IHaFfBLTuM3Lfhptqf3+cleBzMaTBkfof9Orul6RJc2t2Cva3Ghz0En2UtdF9h8///cvFa4GYpsknlkWTs/TuUbj6iygqXnIgWSm3yE0/fNhxP0hmrk7wKBgQDJnnIyvBlnOrZFBM69ltsLKU4bMwU/3WjGSfQYnz67ljAadDanbaPAxdx5Y09uzjvRkytCTHQm90kAgWf6Q/oP5lpRJouDHq2evCSDW5uK4wPvXQDQ2w1vJUvr3We70PxbnmIN7jVAiej/7AAjcQ7QKrUbqW0h8TGIo8oMwpg8AwKBgQDPHeiJjvuanTNlQ3yJG6EsnLDlSsr9f2beMgkdQcDFT9heLXnV2nUYsCdNxCDpTaiuVL6tcz9GQAnDtrPIodfDcVp9gBcOOLdHV7urNr1Qar4GBINO2If1izdXhQ9trPw7Bv4nsQjCi4UCbx+uK07jVE9Njywwyfn8m5iKtbUlDwKBgCV4gRjUxJPe4YkqP5IUu1jzsER10v/vkk1n6Sm6TtpmK0J/Xrj3M3vcSgF1Z4ncYlms1FlaBCxJwROK+VwVzh+uXBLA6yTPSs05gj+3gszpLA4MZt5qF7EUaWvIlkLdsEkGwop8PwFjoB4QKmZr2KjvhwFBF7s7LE2Mwzxj3g8pAoGAXgiZQkorW6li64hHkFmalTD/GnlGyst2CwQkd/bZJpmSCNFd4doYX6LeC+xPgf8sKTIRKnUJ4zshS+aC79Q+J33dOhMy0Op15BvTfKVEua4vVZ73Sn+ho4XAJWrNxDopDfoejpBCNFYQR5wrI687eP07C/01/vPln2MK1eh9zHM=";//�̳̲鿴��ȡ��ʽ���������ã�
    //支付宝公钥，查看地址：https://openhome.alipay.com/platform/appDaily.htm?tab=info
	public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA2H9X1XhXFl1j1QVr7M3NyXoBhB+LkJSvwtcRb/NLU1lRwx/jXPrT9howmhq7JN54YrJz6WybeIIE2hPeDKkBq5cfjmmCn5ZQJKHV+Fmxme8xyN7v6qRdn+vj/4peT1QdHwHiK4qTKTqoDY8EnLG9WPEotQnPOFKdGq78hSstVlFfRULaMubm5lmSHZ0dDazeGWGwzB5Y3znLSFn347E8dKrxOaACx2iZfbhO9bxF6q4Zx33/eS/NV7iFlXbqzY3nmSVvDBiUDJb9DZDOJ16vf9JnDxJQgCDVloDQRdKjUeoTyxBnU5qSl6+Eom9pKEIB58UJ+EN/JLXgTlsqthfFtwIDAQAB";//�̳̲鿴��ȡ��ʽ���������ã�
	//服务器异步通知页面路径 需 http://格式的完整路径，不能加？id=123这类自定义路径
	public static String notify_url = "http://localhost:8081/alipay/alipayNotifyNotice.action";
	//页面跳转同步通知页面路径 需http://格式的完整路径，不能加？id=123这类自定义路径
	public static String return_url = "http://localhost:8081/alipay/alipayReturnNotice.action";
	//签名方式
	public static String sign_type = "RSA2";
	//字符编码格式
	public static String charset = "utf-8";
	//支付宝网关
	//public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";//ע�⣺ɳ����Ի�������ʽ����Ϊ��https://openapi.alipay.com/gateway.do
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";//ע�⣺ɳ����Ի�������ʽ����Ϊ��https://openapi.alipay.com/gateway.do
	//操作日志
	public static String log_path ="D:\\test\\pay\\";


}
