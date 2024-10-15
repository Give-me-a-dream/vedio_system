package com.example.vediosystem.service.serviceImpl;

import com.example.vediosystem.dao.SortDao;
import com.example.vediosystem.domain.VSort;
import com.example.vediosystem.service.SortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class SortServiceImpl implements SortService {

    @Autowired
    SortDao sortDao;

    @Override
    public List<VSort> getAllSort() {
        return sortDao.selectList(null);
    }
}
