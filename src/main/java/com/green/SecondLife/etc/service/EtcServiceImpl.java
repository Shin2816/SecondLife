package com.green.SecondLife.etc.service;

import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EtcServiceImpl implements EtcService{
    private final SqlSessionTemplate sessionTemplate;

}
