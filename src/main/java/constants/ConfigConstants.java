package constants;

public class ConfigConstants {
    public final static String FYB_BASE_URL = "http://192.168.1.14:8103";// test
    public final static String USERAPP_BASE_URL="http://192.168.1.14:8101";
    public final static String USERAPP_Beta_URL="http://121.40.129.114:8101";
    public final static String IW_USER_URL = "http://http://192.168.1.14:8101"; //test
    // final static String FYB_BASE_URL = "http://192.168.1.14:8103";
    public final static String JDBC_DRIVER_CLASS = "com.mysql.jdbc.Driver";
    public final static String JDBC_CONNECT_URL = "jdbc:mysql://192.168.1.14:3306/hims_test?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&failOverReadOnly=false";
    public final static String JDBC_USERNAME = "root";
    public final static String JDBC_PASSWORD = "qa123";
    
    public final static String FYB_UPDATE_URL = FYB_BASE_URL
            + "/rest/common/automaticUpdates.rest";
    public final static String FYB_USER_LOGIN_URL = FYB_BASE_URL
            + "/rest/uc/userLogin.rest";
    
    public final static String[] FYB_UPDATE_PARAM = { "errorCode", "message" };
    
}
