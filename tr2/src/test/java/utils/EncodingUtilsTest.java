package utils;

import com.tr.utils.EncodingUtils;

import java.io.UnsupportedEncodingException;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-12-19 10:36
 */
public class EncodingUtilsTest {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String origin = "eyJtb2JpbGVLZXlzZXRJZCI6ImNhYTk2ODYwLTE4Y2MtNGI2NC1hMDBmLTU3MTgwYzg4ZTU4YSIsImVuY3J5cHRlZERhdGEiOiJzZ29MRDNBYzYzZkJBUXI3blhJKzVMVjBIYkZnanhxbmZQeWtveVc0R2U2ZHhlaVltUEVMVVJWWTJDaUFQeEcwTDBpOGJWandpVU5NN1VkZG9OSUZoWWRzR3d1emwyU1hhRWx4VnVhMGRZYy9xaUkxREZGODJRbzhxTUluM0sxWWV6VTlOVjE1bnlsUHFsVGpqdm9CaUsyZkdSQVpqVmpNVlVuUE5SbWFGZ0Q3TDJNSmp4d3RpVUhzamtmNjRUYWMrdFYwcHRxYXpsS1lkVlpOSlBycFJxcFlMYnlHWXo1TW5jbktCN2dxMTVWQUFhMzlhVWlHVDZ5VnVZeXQ1UFRENjFDbnVzUVRpblE4OTRPNFR1RE1XVU1LM2Z1VnZQVUdtMEVxYVExd05CWURhU2d4SmJzQjdycVA5UFdXSnkySzB1ZlFGcGErWWVZVEd0dHpIYW03VzF1MEdocmxFUjdFUk1NYmlDOVJqTm5qSS9LVXRjY1ZzeERrK1NzaTZWaEFhWThaK0lQZzRXOD0iLCJyZXNwb25zZUhvc3QiOiJzZXJ2aWNlcy5tYXN0ZXJjYXJkLmNvbS9tdGYvbWRlcyJ9";

        byte[] bytes = EncodingUtils.base64Decode(origin);
        String plain = new String(bytes, "UTF-8");

        System.out.println(plain);

    }
}
