package com.template.dto;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import lombok.Data;

import java.util.List;

/**
 * 通用消息封装 - 对一些零碎的消息封装
 * @author lc
 * @since 2023/1/12
 */
@ProtobufClass
@Data
public class MessageDto {
    private long longData;
    private float floatData;
    private String strData;
    private boolean boolData;
    private int intData;
    /** long列表 **/
    List<Long> longList;
    /** int列表 **/
    List<Integer> intList;
}
