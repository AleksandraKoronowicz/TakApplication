package com.application.tak.takapplication.data_access;

import android.util.Base64;
import com.application.tak.takapplication.data_model.Client_V;
import com.application.tak.takapplication.data_model.Task_V;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.lang.Object;
import java.util.List;

/**
 * Created by azielinska on 23/05/2017.
 */
public class Config
{
    public static final String SERVER_NAME = "http://klasterinnowacjispolecznych.pl/";
    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd_HHmm");
    public static final String ApplicationId = "9736362253343859";
    public static Client_V LoggedInClient = null;
    public static Client_V LoggedInStudent = null;
    public static List<Task_V> ClientTasks = null;
    public static List<Task_V> StudentTask = null;

    private static final String ALGO = "AES";
    private static final byte[] keyValue = new String(ApplicationId).getBytes();

    public static String encrypt(String Data) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data.getBytes());

        String encryptedValue =  Base64.encodeToString(encVal,Base64.DEFAULT);
        return encryptedValue;
    }

    public static String decrypt(String encryptedData) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = Base64.decode(encryptedData, Base64.DEFAULT);
        byte[] decValue = c.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }

    private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(keyValue, ALGO);
        return key;
    }
}
