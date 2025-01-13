package com.arketeam.addenplus.config;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.arketeam.addenplus.constant.DataSourceType;

import jakarta.servlet.http.HttpServletRequest;

public class RoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        DataSourceType dataSourceType = DataSourceType.POOL1;
        if (RequestContextHolder.getRequestAttributes() != null) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                    .getRequest();
            dataSourceType = (DataSourceType) request.getAttribute("database");
        }
        return dataSourceType;
    }
    public void initDataSources(DataSource dataSource1, DataSource dataSource2) {
        Map<Object, Object> dsMap = new HashMap<Object, Object>();
        dsMap.put(DataSourceType.POOL1, dataSource1);
        dsMap.put(DataSourceType.POOL2, dataSource2);
        this.setTargetDataSources(dsMap);
    }
}
