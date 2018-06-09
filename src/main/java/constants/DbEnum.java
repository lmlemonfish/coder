package constants;

/**
 * @ClassName DbEnum
 * @Description TODO
 * @Author LM
 * @Date 2018/6/9 0009 下午 9:07
 * @Version 1.0
 **/
public enum DbEnum {

    MYSQL("Mysql","com.mysql.jdbc.Driver"),
    ORACLE("Oracle","oracle.jdbc.driver.OracleDriver");
    ;

    private String DbName;
    private String driveName;

    DbEnum(String dbName, String driveName) {
        DbName = dbName;
        this.driveName = driveName;
    }

    public String getDbName() {
        return DbName;
    }

    public void setDbName(String dbName) {
        DbName = dbName;
    }

    public String getDriveName() {
        return driveName;
    }

    public void setDriveName(String driveName) {
        this.driveName = driveName;
    }
}
