package exception;

/**
 * @ClassName BusinessException
 * @Description TODO 自定义异常
 * @Author LM
 * @Date 2018/6/9 0009 下午 7:19
 * @Version 1.0
 **/
public class BusinessException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    //出现异常的具体字符串值
    private String value;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message,String value) {
        super(message);
        this.value = value;
    }
}
