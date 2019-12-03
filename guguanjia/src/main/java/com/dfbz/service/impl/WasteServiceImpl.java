package com.dfbz.service.impl;

import com.dfbz.entity.Waste;
import com.dfbz.service.WasteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author hjt
 * @description
 * @date 2019/11/24
 */

@Service
@Transactional
public class WasteServiceImpl extends ServiceImpl<Waste> implements WasteService {
}
