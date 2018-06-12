package entity;

import com.google.common.base.Strings;

/**
 * @ClassName ConInfo
 * @Description TODO 连接成功后的数据库信息(单例)
 * @Author LM
 * @Date 2018/6/7 0007 下午 5:37
 * @Version 1.0
 **/
public class ConInfo {

    private static ConInfo conInfo;
    //lock对象
    private static Object object = new Object();

    private String driver;
    private String url;
    private String userName;
    private String password;//TODO V1.0之后再加入加密

    private ConInfo() {
    }

    private ConInfo(String driver, String url, String userName, String password) {
        this.driver = driver;
        this.url = url;
        this.userName = userName;
        this.password = password;
    }

    public static ConInfo getInstance() {

        return getInstance(null,null,null,null);
    }

    public static ConInfo getInstance(String driver, String url, String userName, String password) {

        if (conInfo == null) {
            //双重锁定
            //保证线程安全
            synchronized (object) {
                if (conInfo == null) {
                    conInfo = new ConInfo(driver,url,userName,password);
                    return conInfo;
                }
            }
        }
        //表示再一次请求连接,参数可能改变,更新单例对象
        if (!Strings.isNullOrEmpty(driver)
                && !Strings.isNullOrEmpty(url)
                && !Strings.isNullOrEmpty(userName)
                && !Strings.isNullOrEmpty(password)) {
            conInfo.setDriver(driver);
            conInfo.setUrl(url);
            conInfo.setUserName(userName);
            conInfo.setPassword(password);
        }

        return conInfo;
    }

    /**
     * 是否连接成功
     * @return
     */
    public static boolean isConSuccess() {
        if(conInfo != null
                && !Strings.isNullOrEmpty(conInfo.getDriver())
                && !Strings.isNullOrEmpty(conInfo.getUrl())
                && !Strings.isNullOrEmpty(conInfo.getUserName())
                && !Strings.isNullOrEmpty(conInfo.getPassword())) {
            return true;
        }
        return false;
    }


    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ConInfo{" +
                "driver='" + driver + '\'' +
                ", url='" + url + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
