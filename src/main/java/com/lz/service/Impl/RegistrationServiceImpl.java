package com.lz.service.Impl;



import com.lz.Dao.RegistrationDao;
import com.lz.pojo.dto.RegistrationDTO;
import com.lz.pojo.result.PageResult;
import com.lz.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

/**
 * @author lz
 */
@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private RegistrationDao registrationDao;

    /**
     * 列表
     *
     * @param currentPage 当前页面
     * @param pageSize    页面大小
     * @param name        名字
     * @param status      地位
     * @param date        日期
     *
     * @return {@code PageResult}
     */
    @Override
    public PageResult list(int currentPage, int pageSize, String name,
                           String status, Date date) {
        System.out.println("date:" + date);
        int offset = (currentPage - 1) * pageSize ;
        List<RegistrationDTO> registrations = registrationDao.getRegistrationsWithDetails(pageSize, offset, name,
                                                                                          status, date);
        int registrationsTotal = registrationDao.getRegistrationsTotal(name,
                                                                       status, date);
        return new PageResult(registrationsTotal, registrations);
    }
}