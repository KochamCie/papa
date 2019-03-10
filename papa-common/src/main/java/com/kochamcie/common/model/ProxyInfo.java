package com.kochamcie.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: rns
 * @Date: 2019/3/9 上午10:11
 * @Description: ProxyInfo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProxyInfo extends BaseObject {

    private String ip;

    private String port;

    private String location;

    private String checkTime;

}
