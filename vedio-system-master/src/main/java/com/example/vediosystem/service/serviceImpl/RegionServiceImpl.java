package com.example.vediosystem.service.serviceImpl;

import com.example.vediosystem.dao.RegionDao;
import com.example.vediosystem.dao.SortDao;
import com.example.vediosystem.domain.Region;
import com.example.vediosystem.domain.VSort;
import com.example.vediosystem.service.RegionService;
import com.example.vediosystem.service.SortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RegionServiceImpl implements RegionService {

    @Autowired
    RegionDao regionDao;

    @Override
    public List<Region> getAllRegion() {
        return regionDao.selectList(null);
    }
}
