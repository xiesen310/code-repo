package top.xiesen.verify.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * @Description
 * @className top.xiesen.verify.pojo.LogEvent
 * @Author 谢森
 * @Email xiesen@zork.com.cn
 * @Date 2020/3/20 12:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogEvent {
    @NotNull
    private String businessId;
    private String type;

    @NotNull
    private Long timestamp;
    private String source;
    private String hosts;

    @NotNull
    private String ip;
    private Long offset;

    @NotNull
    private String message;

    /**
     * dimensions
     */
    private Map<String, String> dim;
    private Map<String, String> tags;



}
