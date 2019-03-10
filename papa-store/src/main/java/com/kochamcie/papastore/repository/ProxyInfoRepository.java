package com.kochamcie.papastore.repository;

import com.kochamcie.papastore.entity.ProxyInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: rns
 * @Date: 2019/3/9 下午8:27
 * @Description: ProxyInfoRepository
 */
@Repository
public interface ProxyInfoRepository extends JpaRepository<ProxyInfo, String> {





}
