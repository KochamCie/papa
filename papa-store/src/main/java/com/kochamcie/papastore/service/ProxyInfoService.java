package com.kochamcie.papastore.service;

import com.kochamcie.papastore.entity.ProxyInfo;
import com.kochamcie.papastore.repository.ProxyInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


/**
 * @Author: rns
 * @Date: 2019/3/9 下午8:28
 * @Description: ProxyInfoService
 */
@Service
@Transactional(rollbackOn = Exception.class)
public class ProxyInfoService {

    @Autowired
    private ProxyInfoRepository proxyInfoRepository;

    public void batchSave(List<ProxyInfo> proxyInfoList){
        proxyInfoRepository.saveAll(proxyInfoList);
    }

    public Page<ProxyInfo> proxyInfoList(int page, int pageSize){
        return proxyInfoRepository.findAll(PageRequest.of(page, pageSize));
    }


}
