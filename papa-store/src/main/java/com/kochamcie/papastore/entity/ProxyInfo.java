package com.kochamcie.papastore.entity;

import com.kochamcie.common.model.BaseObject;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: rns
 * @Date: 2019/3/9 下午8:24
 * @Description: ProxyInfo
 */
@Data
@Entity
@Table(name = "ha_proxy_info")
public class ProxyInfo extends BaseObject {

    @Id
    private String ip;

    private String port;

    private String location;

    private String checkTime;

}
